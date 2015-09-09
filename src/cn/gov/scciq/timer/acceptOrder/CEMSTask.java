package cn.gov.scciq.timer.acceptOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 系统接单任务
 * @author chao.xu
 *
 */
public class CEMSTask extends TimerTask{

    private static Log log = LogFactory.getLog(CEMSTask.class);
    EBean bean=new EBean();
    @Override
    public void run() {
        // TODO Auto-generated method stub
        log.info("开始从集中审单系统中读取报检数据");
        try {
            List<CEMSDto> cemsDeclList = getCEMS();
            handleCEMSDecl(cemsDeclList);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("numberone", e);
        }
    }
    
    /**
     * 步骤1
     */
    public List<CEMSDto> getCEMS(){
        List<CEMSDto> cemsDeclList = CEMSDao.getCEMS();
        return cemsDeclList;
    }
    
    /**
     * 步骤2
     */
    private void handleCEMSDecl(List<CEMSDto> cemsDeclList){
        for(CEMSDto cemsDeclDto :  cemsDeclList){                //步骤2
            
            String declNo = cemsDeclDto.getDECL_NO();
            String seqNum = cemsDeclDto.getSEQ_NUM();
           
            log.info("处理报检单： SEQ_NUM="+seqNum + "----DECL_NO="+declNo);
            String entCode = "";
            if(!getCEMSDeclData(declNo, seqNum, entCode)){       //步骤2.1， 若执行失败，直接返回到步骤2
                continue;
            }  
            entCode=bean.getEntCode();
            System.out.println("ni"+entCode);
            getCEMSGoodsData(seqNum);                            //步骤2.2
            int retCode = declFlagAction(declNo, seqNum);        //步骤2.3， 返回0跳转到步骤2， 返回1跳转到步骤2.4，返回2跳转到2.6
            if(retCode == 0){
                continue;
            }
            else if(retCode == 1){
                if(!checkEntExists(declNo, seqNum, entCode)){        //步骤2.4，若执行失败，直接返回到步骤2
                    continue;
                }
                if(!saveDeclInfo(declNo, seqNum)){                   //步骤2.5，若执行失败，直接返回到步骤2
                    continue;
                }
            }
            updateControlReturnFlg(seqNum);                      //步骤2.6
            checkDeclProductSampling(declNo);                    //步骤2.7
            checkRapidRelease(declNo, seqNum);                   //步骤2.8
        }
    }
    
    /**
     * 步骤2.1
     */
    private boolean getCEMSDeclData( String declNo, String seqNum, String entCode){
        CEMSDeclDataDto cemsDeclData = CEMSDao.getCEMSDeclData(seqNum);
        if(cemsDeclData == null){
            return false;
        }else{
            int retCode = FRMDao.saveCIQDeclInfo(cemsDeclData);
            System.out.println("ret "+retCode);
            if(retCode != 0){
                CEMSDao.saveReturnFlg(declNo, "b");
                 System.out.println("seq "+seqNum);
                CEMSDao.updateControlReturnFlg(seqNum, "1", "A");
                return false;
            }else{
                entCode = cemsDeclData.getProd_Reg_No();
                bean.setEntCode(entCode);
                System.out.println("the entcode"+entCode);
                return true;
            }
        }
    }
    
    /**
     * 步骤2.2
     */
    private void getCEMSGoodsData(String seqNum){
        List<CEMSGoodsDataDto> cemsGoodsDataList = CEMSDao.getCEMSGoodsData(seqNum);
        for(CEMSGoodsDataDto cemsGoodsData : cemsGoodsDataList){
            FRMDao.saveCIQGoodsInfo(cemsGoodsData);
        }
    }
    
    /**
     * 步骤2.3
     * 返回0跳转到步骤2， 返回1跳转到步骤2.4，返回2跳转到2.6
     */
    private int declFlagAction(String declNo, String seqNum){
        int retCode = FRMDao.declFlagAction(declNo);
        if(retCode == 1){
            //进入步骤2.4
            return 1;
        }else if(retCode == 2 || retCode == 3){
            CEMSDao.updateControlReturnFlg(seqNum, "1", "A");
            return 0; //返回到步骤2
        }else if(retCode == 4){
            FRMDao.calculateAllDeclProductSampling(declNo);
            //进入步骤2.6
            updateControlReturnFlg(seqNum);
            return 2;
        }
        return 0;
    }
    
    /**
     * 步骤2.4
     * @param entCode
     */
    private boolean checkEntExists(String declNo, String seqNum, String entCode){
        int retCode = FRMDao.checkEntExists(entCode);
        if(retCode == 0){
            FRMDao.saveDeclInfoAbnormal(declNo,1); 
            CEMSDao.saveReturnFlg(declNo, "b");
            CEMSDao.updateControlReturnFlg(seqNum, "2", "9");
            return false; //返回步骤2
        }else{
            List<CEMSDeclExchangeDto> cemsDeclExchangeList = CEMSDao.getCEMSDeclExchange(seqNum);
            for(CEMSDeclExchangeDto cemsDeclExchange : cemsDeclExchangeList){
                FRMDao.saveDeclProductFromEnt(cemsDeclExchange);
            }
            return true;          
        }
    }
    
    /**
     * 步骤2.5
     */
    private boolean saveDeclInfo(String declNo, String seqNum){
        int retCode = FRMDao.saveDeclInfo(declNo);
        System.out.println("retcc "+retCode);
        if(retCode == 0){
            String exchange = FRMDao.getDeclProductFromEnt(declNo);
           
            if(exchange != null){
                try {
                    List<CEMSDeclProductDto> declProductList = parseDeclProductFromEnt(exchange);
                    for(CEMSDeclProductDto dto : declProductList){
                        int code = FRMDao.saveDeclProduct(declNo, dto.getEntProductCode(), dto.getBaseCode(), dto.getGoodsNo());
                        if(code != 0){
                            CEMSDao.saveReturnFlg(declNo, "b");
                            CEMSDao.updateControlReturnFlg(seqNum, "1", "A");
                            return false;  //返回到步骤2
                        }
                    }
                    int code = FRMDao.checkDeclProductPerfect(declNo);
                    if(code != 0){
                    	FRMDao.saveDeclInfoAbnormal(declNo,3);
                    	CEMSDao.saveReturnFlg(declNo, "b");
                    	CEMSDao.updateControlReturnFlg(seqNum, "1", "A");
                    }else{
                    	FRMDao.calculateAllDeclProductSampling(declNo);
                        CEMSDao.saveReturnFlg(declNo, "a");
                    }
                                       
                } catch (Exception e) {
                    // 解析厂检单异常
                    log.error(e);
                    FRMDao.saveDeclInfoAbnormal(declNo,2); 
                    CEMSDao.saveReturnFlg(declNo, "b");
                    CEMSDao.updateControlReturnFlg(seqNum, "1", "A");
                    return false; //返回到步骤2
                }
            }
        }else{
        	System.out.println("seqcc "+seqNum);
            CEMSDao.saveReturnFlg(declNo, "b");
            CEMSDao.updateControlReturnFlg(seqNum, "1", "A");
            return false; //返回到步骤2
        }
        return true;
    }
    
    /**
     * 步骤2.6
     */
    public void updateControlReturnFlg(String seqNum){
        CEMSDao.updateControlReturnFlg(seqNum, "1", "A");
    }
    
    /**
     * 步骤2.7
     */
    public void checkDeclProductSampling(String declNo){
        int retCode = FRMDao.checkDeclProductSampling(declNo);
        if(retCode == 1){
            CEMSDao.saveReturnFlg(declNo, "c");
        }
    }
    
    /**
     * 步骤2.8
     */
    public void checkRapidRelease(String declNo, String seqNum){
        int retInt=FRMDao.checkRapidRelease(declNo);
        if(retInt==1){
        	FRMDao.rapidRelease(declNo);
        	CEMSDao.saveReturnFlg(declNo, "e");
            CEMSDao.updateControlReturnFlg(seqNum, "2", "0");
        }
        
    }
    
    /**
     * 解析厂检单
     * @param exchange
     * @throws Exception 
     */
	private List<CEMSDeclProductDto> parseDeclProductFromEnt(String exchange)
			throws Exception {
		log.info("解析前的厂检单：" + exchange);
		exchange = exchange.replaceAll("\\s+", "").replaceAll("\\\\", "/")
				.replaceAll("，", ",").replaceAll("；", ";").trim();
		log.info("去除不规范字符后的厂检单：" + exchange);
		List<CEMSDeclProductDto> declProductList = new ArrayList<CEMSDeclProductDto>();
		CEMSDeclProductDto declProduct = null;
		String[] list = exchange.split(";");
		String reg = "\\S+=\\S+/?";
		// 解析厂检单每一行
		for (String line : list) {
			if (line.matches(reg)) {
				String[] ones = line.split("=");
				String goodsNo = ones[0];
				// 基地备案号不为空
				if (ones[1].contains("/")) {
					String entProductCode = ones[1].split("/")[0];
					String baseCodes = ones[1].split("/")[1];
					for (String baseCode : baseCodes.split(",")) {
						declProduct = new CEMSDeclProductDto(entProductCode,baseCode, goodsNo);
						declProductList.add(declProduct);
					}
					// 基地备案号为空
				} else {
					String entProductCode = ones[1];
					declProduct = new CEMSDeclProductDto(entProductCode, "", goodsNo);
					declProductList.add(declProduct);
				}
			} else {
				throw new Exception("厂检单格式异常!");
			}
		}
		log.info("解析后的厂检单：");
		for (CEMSDeclProductDto dto : declProductList) {
			String reg2 = "\\d+";
			if (!dto.getGoodsNo().matches(reg2)) {
				throw new Exception("厂检单格式异常!");
			} 
			log.info("解析后每一个：" + dto);
		}
		return declProductList;
	}

    public static void main(String[] args) {
       /* String exchange ="ji1=001\\NB001,NB002,NB003;\n\r"+
                "1=002/NB004,NB005;\n\r"+
                "荷藕2=003/NB006,NB007,NB008;\n\r"+
                "3=004;";*/
        String exchange ="1=010;2=014;3=015;4=016;5=018;6=020;7=024;8=027;9=042;10=043;11=044;12=045;13=047;14=048;15=049";
        try {
            new CEMSTask().parseDeclProductFromEnt(exchange);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
