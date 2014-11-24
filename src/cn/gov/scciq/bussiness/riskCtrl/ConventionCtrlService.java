package cn.gov.scciq.bussiness.riskCtrl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.DefaultResultUtil;

public class ConventionCtrlService {
    
    private static Log log=LogFactory.getLog(ConventionCtrlService.class);

    public JSONObject getConvCtrl(String data, int draw, int start, int length) {
        // TODO Auto-generated method stub
        ConventionCtrlRuleReqDto convCtrl = (ConventionCtrlRuleReqDto)JSONObject.toBean(JSONObject.fromObject(data), ConventionCtrlRuleReqDto.class);
        String orderWord = "ConvCtrlID";
        String orderDirection = "DESC";
        Map<Integer, Object> rsMap = ConventionCtrlDao.getConvCtrl(convCtrl.getProductClassCode(), convCtrl.getProductSubclassCode(), convCtrl.getMaterialClassCode(),convCtrl.getMaterialSubclassCode() ,
                convCtrl.getMaterialCode(), convCtrl.getMaterialSourceCode(), convCtrl.getProcessingMethodCode() , convCtrl.getPackageTypeCode(), convCtrl.getIntendedUseCode() , convCtrl.getCountryCode(),
                convCtrl.getProductCode(), convCtrl.getControlOrgCode(), convCtrl.getControlDeptCode(), start, length, orderWord, orderDirection);
        int recordsTotal = (Integer)rsMap.get(1);
        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
        JSONObject result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
        return result;
    }
    
    public JSONObject saveConvCtrl(String data){
        ConventionCtrlRuleReqDto convCtrl = (ConventionCtrlRuleReqDto)JSONObject.toBean(JSONObject.fromObject(data),ConventionCtrlRuleReqDto.class);
        String retStr = ConventionCtrlDao.saveConvCtrl(convCtrl.getConvCtrlID(), convCtrl.getProductClassCode(), convCtrl.getProductSubclassCode(),
                convCtrl.getProductCode(), convCtrl.getMaterialClassCode(), convCtrl.getMaterialSubclassCode(), convCtrl.getMaterialCode(), 
                convCtrl.getMaterialSourceCode(), convCtrl.getProcessingMethodCode(), convCtrl.getPackageTypeCode(), convCtrl.getIntendedUseCode(), convCtrl.getCountryCode(), 
                convCtrl.getDifferenceCode(), convCtrl.getControlOrgCode(), convCtrl.getControlDeptCode(), convCtrl.getControlOperatorCode());
        if("".equals(retStr)){
            retStr = "true";
        }
        JSONObject result = DefaultResultUtil.getModificationResult(retStr);
        return result;
    }
    
    public JSONObject getConvCtrlDetailByID(String convCtrlID){
        ConvCtrlRuleDetailDto dto = ConventionCtrlDao.getConvCtrlDetailByID(convCtrlID);
        return JSONObject.fromObject(dto);
    }
    
    /**
     * 删除常规布控
     * @param convCtrlId
     * @return
     */
    public JSONObject delConvCtrl(String convCtrlId) {
        // TODO Auto-generated method stub
        boolean retCode = ConventionCtrlDao.delConvCtrlById(convCtrlId);
        JSONObject jo = DefaultResultUtil.getModificationResult(retCode+"");
        return jo;
    }
    
    public JSONObject getConvCtrlItem(String convCtrlId){
        List<ConventionCtrlItemResDto> list = ConventionCtrlDao.getConvCtrlItem(convCtrlId);
        JSONArray ja = JSONArray.fromObject(list);
        JSONObject result = DefaultResultUtil.getDefaultResult(ja);
        return result;
    }

    public JSONObject saveConvCtrlItem(String data) {
        // TODO Auto-generated method stub
        String retStr = "";
        ConventionCtrlItemReqDto convCtrlItem = (ConventionCtrlItemReqDto)JSONObject.toBean(JSONObject.fromObject(data),ConventionCtrlItemReqDto.class);
        //新增布控项目
        if(convCtrlItem.getConvCtrlItemID().equals("0")){
            String convCtrlItemID = ConventionCtrlDao.saveConvCtrlItem("0", convCtrlItem.getConvCtrlID(), convCtrlItem.getItemCode(), convCtrlItem.getItemName(),
                    convCtrlItem.getDetectionStd(), convCtrlItem.getMonitoringReason(), convCtrlItem.getUnqualifyRatio(), convCtrlItem.getHazardLevel(),
                    convCtrlItem.getCountryReactLevel(), convCtrlItem.getLimitType(), convCtrlItem.getDetectionLimit(), convCtrlItem.getLimitUnit(), 
                    convCtrlItem.getOrgCode(), convCtrlItem.getDeptCode());
            if(!convCtrlItemID.equals(ConstantStr.SAVE_ERROR_MSG) && Integer.parseInt(convCtrlItemID) > 0 ){
                JSONArray itemLimitList = convCtrlItem.getItemLimitList();
                for(int i = 0; i < itemLimitList.size(); i++){
                    JSONObject jo = itemLimitList.getJSONObject(i);
                    ConvCtrlItemLimitDto itemLimit = (ConvCtrlItemLimitDto)JSONObject.toBean(jo, ConvCtrlItemLimitDto.class);
                    ConventionCtrlDao.saveConvCtrlItmeLimit(convCtrlItemID, itemLimit.getCountryCode(), itemLimit.getCountryName(),itemLimit.getDetectionLimit(),itemLimit.getLimitUnit());
                }
            }else{
               retStr = ConstantStr.SAVE_ERROR_MSG;
            }
        }
        //修改布控项目
        else{
            retStr = ConventionCtrlDao.saveConvCtrlItem(convCtrlItem.getConvCtrlItemID(), convCtrlItem.getConvCtrlID(), convCtrlItem.getItemCode(), convCtrlItem.getItemName(),
                    convCtrlItem.getDetectionStd(), convCtrlItem.getMonitoringReason(), convCtrlItem.getUnqualifyRatio(), convCtrlItem.getHazardLevel(),
                    convCtrlItem.getCountryReactLevel(), convCtrlItem.getLimitType(), convCtrlItem.getDetectionLimit(), convCtrlItem.getLimitUnit(), 
                    convCtrlItem.getOrgCode(), convCtrlItem.getDeptCode());
            if(retStr.equals("")){
                JSONArray itemLimitList = convCtrlItem.getItemLimitList();
                for(int i = 0; i < itemLimitList.size(); i++){
                    JSONObject jo = itemLimitList.getJSONObject(i);
                    ConvCtrlItemLimitDto itemLimit = (ConvCtrlItemLimitDto)JSONObject.toBean(jo, ConvCtrlItemLimitDto.class);
                    ConventionCtrlDao.saveConvCtrlItmeLimit(convCtrlItem.getConvCtrlItemID(), itemLimit.getCountryCode(), itemLimit.getCountryName(),itemLimit.getDetectionLimit(),itemLimit.getLimitUnit());
                }
            }
        }
        if("".equals(retStr)){
            retStr = "true";
        }
        JSONObject result = DefaultResultUtil.getModificationResult(retStr);
        return result;
    }


    /**
     * 根据布控项目id，获取布控项目详细数据及其限量表数据
     * @param convCtrlItemID
     * @return
     */
    public JSONObject getConvCtrlItemDetailByID(String convCtrlItemID){
        ConvCtrlItemDetailDto dto = ConventionCtrlDao.getConvCtrlItemDetailByID(convCtrlItemID);
        JSONObject jo = new JSONObject();
        jo.put("ctrlItem", JSONObject.fromObject(dto.getItemDetail()));
        jo.put("itemLimit", JSONArray.fromObject(dto.getItemLimitList()));
        return jo;
    }
    
    /**
     * 删除布控项目
     * @param convCtrlItemID
     * @return
     */
    public JSONObject delConvCtrlItem(String convCtrlItemID) {
        // TODO Auto-generated method stub
        boolean retCode = ConventionCtrlDao.delConvCtrlItem(convCtrlItemID);
        JSONObject jo = DefaultResultUtil.getModificationResult(retCode+"");
        return jo;
    }
}
