package cn.gov.scciq.bussiness.entProduct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.DefaultResultUtil;

public class EntProductService {

	private static final Log log = LogFactory.getLog(EntProductService.class);

	/**
	 * 企业产品查询
	 * 
	 * @return
	 */
	public static JSONObject getEntProductByQuery(String data, int draw, int start, int length) {
		String rapidRelease = "0";
		String greenChanel = "0";
		String orderWord = "EntCode";
		String orderDirection = "DESC";
		EntProductReqDto dto = null;
		if (data != null && !"".equals(data)) {
			dto =(EntProductReqDto) JSONObject.toBean(JSONObject.fromObject(data), EntProductReqDto.class);
		}else{
			dto = new EntProductReqDto();
		}
		Map<Integer, Object> rsMap = EntProductDao.getEntProductByQuery(dto.getEntCode(), dto.getEntName(), dto.getEntProductName(), 
				rapidRelease, greenChanel, start, length, orderWord, orderDirection);
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject result = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		return result;
	}

	/**
	 * 根据企业代码和企业产品自编号取得详细数据，包括产品特征数据、原料数据、辅料数据、添加剂数据
	 * 
	 * @param entCode
	 * @param entProductCode
	 * @return
	 */
	public static JSONObject getEntProductDetailByID(String entCode,
			String entProductCode) {
		EntProductDetailDto dto = EntProductDao.getEntProductDetailByID(entCode, entProductCode);		
		JSONObject jo = DefaultResultUtil.getDefaultResult(dto);
		return jo;
	}
	public static JSONObject getEntProductDetailForMng(String entCode,
			String entProductCode,String productCode,String countryCode) {
		EntProductDetailDto dto = EntProductDao.getEntProductDetailForMng(entCode, entProductCode, productCode, countryCode);	
		JSONObject jo = DefaultResultUtil.getDefaultResult(dto);
		return jo;
	}

	public static JSONObject setEntProductRapidRelease(String entCode,
			String productCode, String countryCode, String entProductCode,String rapidRelease,String greenChanel) {
		
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.Ent_Product);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		String retStr = EntProductDao.setEntProductRapidRelease(entCode, productCode, countryCode, entProductCode,rapidRelease,greenChanel);
		if("".equals(retStr)){
			retStr="true";
		}
		JSONObject jo = DefaultResultUtil.getModificationResult(retStr);
		return jo;
	}
	public static JSONObject setEntProductReleaseMode(String entCode,
			String productCode, String countryCode, String entProductCode,String rapidRelease,String greenChanel,String materialRelease,String ratioRelease) {
		
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.Ent_Product);
	if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		String retStr = EntProductDao.setEntProductReleaseMode(entCode, productCode, countryCode, entProductCode, rapidRelease, greenChanel, materialRelease, ratioRelease);
		if("".equals(retStr)){
			retStr="true";
		}
		JSONObject jo = DefaultResultUtil.getModificationResult(retStr);
		return jo;
	}

	public static JSONObject setEntProductGreenChanel(String entCode,
			String productCode, String countryCode, String entProductCode) {
		String retStr = EntProductDao.setEntProductGreenChanel(entCode, productCode, countryCode, entProductCode);
		JSONObject jo = DefaultResultUtil.getModificationResult(retStr);
		return jo;
	}
	
	/**
	 * 导出excel文件的默认文件名
	 */
	public static String getExcelName(String filename){
		String excelName = "";
		if("RapidRelease".equals(filename)){
			excelName = "监控放行清单";
		}else if("GreenChanel".equals(filename)){
			excelName = "绿色通道清单";
		}else if("MaterialBatch".equals(filename)){
			excelName = "原料验放清单";
		}
		return excelName;
	}
	
	/**
	 * 导出excel文件的标题行
	 */
	public static List<String> getExcelTitles(String filename){
		String[] titles = {"企业代码","企业名称","国家代码","国家名称","企业产品名称","产品自编号","产品编号","风险等级","抽检比"};
		return new ArrayList<String>(Arrays.asList(titles));
	}

	/**
	 * 导出excel文件的数据
	 */
	@SuppressWarnings("unchecked")
	public static List<EntProductExcelDto> getExcelData(String filename){
		String rapidRelease = "0";
		String greenChanel = "0";
		String orderWord = "EntCode";
		String orderDirection = "DESC";
		List<EntProductResDto> resList = new ArrayList<EntProductResDto>();
		List<EntProductExcelDto> excelList = new ArrayList<EntProductExcelDto>();
		if("RapidRelease".equals(filename)){
			rapidRelease = "1";
			Map<Integer, Object> rsMap = EntProductDao.getEntProductByQuery("", "", "", rapidRelease, greenChanel, 0, 0, orderWord, orderDirection);
			resList = (List<EntProductResDto>) rsMap.get(2);
		}else if("GreenChanel".equals(filename)){
			greenChanel = "1";
			Map<Integer, Object> rsMap = EntProductDao.getEntProductByQuery("", "", "", rapidRelease, greenChanel, 0, 0, orderWord, orderDirection);
		    resList = (List<EntProductResDto>) rsMap.get(2);
		}else if("MaterialBatch".equals(filename)){
			greenChanel = "2";
			Map<Integer, Object> rsMap = EntProductDao.getEntProductByQuery("", "", "", rapidRelease, greenChanel, 0, 0, orderWord, orderDirection);
		    resList = (List<EntProductResDto>) rsMap.get(2);
		}
		for(EntProductResDto res : resList){
			excelList.add(new EntProductExcelDto(res.getEntCode(), res.getEntName(), res.getCountryCode(), res.getCountryName(),
					res.getEntProductName(), res.getEntProductCode(), res.getProductCode(), res.getRiskLevelDesc(), res.getLowerSamplingRatio()+"-"+res.getUpperSamplingRatio()));
		}
		return excelList;
	}
}
