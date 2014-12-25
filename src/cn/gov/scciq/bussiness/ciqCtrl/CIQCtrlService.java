package cn.gov.scciq.bussiness.ciqCtrl;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.bussiness.convCtrl.ConvCtrlItemLimitDto;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.DefaultResultUtil;

/**
 * 应急布控
 * @author chao.xu
 *
 */
public class CIQCtrlService {
    
    public static JSONObject getCIQControl(String data, int draw, int start, int length){
        CIQCtrlReqDto ciqCtrl = null;
        if(data != null && !"".equals(data)){
            ciqCtrl = (CIQCtrlReqDto)JSONObject.toBean(JSONObject.fromObject(data), CIQCtrlReqDto.class);
        }else{
            ciqCtrl = new CIQCtrlReqDto();
        }
        String orderWord = "CIQControlID";
        String orderDirection = "DESC";
        Map<Integer, Object> rsMap = CIQCtrlDao.getCIQControl( ciqCtrl.getControlName(), ciqCtrl.getControlReason(),ciqCtrl.getIfExec(),
                ciqCtrl.getIfCheck(), ciqCtrl.getControlOrgCode(), ciqCtrl.getControlDeptCode(), start, length, orderWord, orderDirection);
        int recordsTotal = (Integer)rsMap.get(1);
        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
        JSONObject result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
        return result;
    }
    
    public static JSONObject getCIQCtrlDetailByID(String ciqCtrlID){
        CIQCtrlDetailResDto dto = CIQCtrlDao.getCIQCtrlDetailByID(ciqCtrlID);
        return JSONObject.fromObject(dto);
    }
    
    public static JSONObject saveCIQControl(String data){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        CIQCtrlReqDto ciqCtrl = (CIQCtrlReqDto)JSONObject.toBean(JSONObject.fromObject(data),CIQCtrlReqDto.class);
        String retStr = CIQCtrlDao.saveCIQControl(ciqCtrl.getCiqControlID(), ciqCtrl.getControlName(), ciqCtrl.getControlReason(),
                ciqCtrl.getDeadline(), ciqCtrl.getRemarks(), ContextUtil.getOrgCode(), ContextUtil.getDeptCode(), 
                ContextUtil.getOperatorCode());
        if("".equals(retStr)){
            retStr = "true";
        }
        JSONObject result = DefaultResultUtil.getModificationResult(retStr);
        return result;  
    }
    
    public static JSONObject deleteCIQControl(String ciqControlID){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        boolean retCode = CIQCtrlDao.deleteCIQControl(ciqControlID);
        JSONObject jo = DefaultResultUtil.getModificationResult(retCode+"");
        return jo;
    }    
    
    public static JSONObject getCIQControlCondition(String ciqControlID){
        List<CIQCtrlConditionDto> list = CIQCtrlDao.getCIQControlCondition(ciqControlID);
        JSONArray ja = JSONArray.fromObject(list);
        JSONObject result = DefaultResultUtil.getDefaultResult(ja);
        return result;
    }
    
    public static JSONObject saveCIQControlCondition(String data){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        CIQCtrlConditionDto ciqCtrlCondition = (CIQCtrlConditionDto)JSONObject.toBean(JSONObject.fromObject(data),CIQCtrlConditionDto.class);
        String retStr = CIQCtrlDao.saveCIQControlCondition(ciqCtrlCondition.getCiqControlConditionID(), ciqCtrlCondition.getCiqControlID(), ciqCtrlCondition.getOrderNo(), ciqCtrlCondition.getLeftLogic(),
                ciqCtrlCondition.getRightLogic(),ciqCtrlCondition.getDefinedField(),ciqCtrlCondition.getCalculationDesc(), ciqCtrlCondition.getKeywords(), ciqCtrlCondition.getKeywordsDesc());
        if("".equals(retStr)){
            retStr = "true";
        }
        JSONObject result = DefaultResultUtil.getModificationResult(retStr);
        return result;  
    }

    public static JSONObject deleteCIQControlCondition(String ciqControlConditionID){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        boolean retCode = CIQCtrlDao.deleteCIQControlCondition(ciqControlConditionID);
        JSONObject jo = DefaultResultUtil.getModificationResult(retCode+"");
        return jo;
    }   
    
    public static JSONObject deleteCIQControlItem(String ciqControlItemID){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        boolean retCode = CIQCtrlDao.deleteCIQControlItem(ciqControlItemID);
        JSONObject jo = DefaultResultUtil.getModificationResult(retCode+"");
        return jo;
    }   
    
    public static JSONObject getCIQControlItem(String ciqControlID){
        List<CIQCtrlItemResDto> list = CIQCtrlDao.getCIQControlItem(ciqControlID);
        JSONArray ja = JSONArray.fromObject(list);
        JSONObject result = DefaultResultUtil.getDefaultResult(ja);
        return result;
    }

    public static JSONObject getCIQCtrlItemDetailByID(String ciqCtrlItemID){
        CIQCtrlItemDetailDto dto = CIQCtrlDao.getCIQCtrlItemDetailByID(ciqCtrlItemID);
        JSONObject jo = new JSONObject();
        jo.put("ctrlItem", JSONObject.fromObject(dto.getItemDetail()));
        jo.put("itemLimit", JSONArray.fromObject(dto.getItemLimitList()));
        return jo;
    }
    
    
    public static JSONObject saveCIQControlItem(String data){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        String retStr = "";
        CIQCtrlItemReqDto ciqCtrlItem = (CIQCtrlItemReqDto)JSONObject.toBean(JSONObject.fromObject(data),CIQCtrlItemReqDto.class);
        //新增布控项目
        if(ciqCtrlItem.getCiqControlItemID().equals("0")){
            String ciqCtrlItemID = CIQCtrlDao.saveCIQCtrlItem(ciqCtrlItem.getCiqControlItemID(), ciqCtrlItem.getCiqControlID(), ciqCtrlItem.getItemCode(), ciqCtrlItem.getItemName(), ciqCtrlItem.getDetectionStd(),
                    ciqCtrlItem.getSamplingMode(), ciqCtrlItem.getSamplingParamValue(), ciqCtrlItem.getLimitType(),ciqCtrlItem.getDetectionLimit(), ciqCtrlItem.getLimitUnit(), ContextUtil.getOrgCode(), ContextUtil.getDeptCode());
            Pattern pattern = Pattern.compile("\\d+$");
            Matcher matcher = pattern.matcher(ciqCtrlItemID);
            if(matcher.matches()){
                JSONArray itemLimitList = ciqCtrlItem.getItemLimitList();
                for(int i = 0; i < itemLimitList.size(); i++){
                    JSONObject jo = itemLimitList.getJSONObject(i);
                    ConvCtrlItemLimitDto itemLimit = (ConvCtrlItemLimitDto)JSONObject.toBean(jo, ConvCtrlItemLimitDto.class);
                    CIQCtrlDao.saveCIQCtrlItmeLimit(ciqCtrlItemID, itemLimit.getCountryCode(), itemLimit.getCountryName(),itemLimit.getDetectionLimit(),itemLimit.getLimitUnit());
                }
            }else{
               retStr = ciqCtrlItemID;
            }
        }
        //修改布控项目
        else{
            retStr = CIQCtrlDao.saveCIQCtrlItem(ciqCtrlItem.getCiqControlItemID(), ciqCtrlItem.getCiqControlID(), ciqCtrlItem.getItemCode(), ciqCtrlItem.getItemName(), ciqCtrlItem.getDetectionStd(),
                    ciqCtrlItem.getSamplingMode(), ciqCtrlItem.getSamplingParamValue(), ciqCtrlItem.getLimitType(),ciqCtrlItem.getDetectionLimit(), ciqCtrlItem.getLimitUnit(), ContextUtil.getOrgCode(), ContextUtil.getDeptCode());
            if(retStr.equals("")){
                JSONArray itemLimitList = ciqCtrlItem.getItemLimitList();
                for(int i = 0; i < itemLimitList.size(); i++){
                    JSONObject jo = itemLimitList.getJSONObject(i);
                    ConvCtrlItemLimitDto itemLimit = (ConvCtrlItemLimitDto)JSONObject.toBean(jo, ConvCtrlItemLimitDto.class);
                    CIQCtrlDao.saveCIQCtrlItmeLimit(ciqCtrlItem.getCiqControlItemID(), itemLimit.getCountryCode(), itemLimit.getCountryName(),itemLimit.getDetectionLimit(),itemLimit.getLimitUnit());
                }
            }
        }
        if("".equals(retStr)){
            retStr = "true";
        }
        JSONObject result = DefaultResultUtil.getModificationResult(retStr);
        return result;
    }
    
    public static JSONObject checkCIQControlCondition(String ciqControlID){
        String retStr = CIQCtrlDao.checkCIQControlCondition(ciqControlID);
        if("".equals(retStr)){
            retStr = "true";
        }
        JSONObject result = DefaultResultUtil.getModificationResult(retStr);
        return result; 
    }
    
    public static JSONObject updateCIQControlValid(String ciqControlID){
        String retStr = CIQCtrlDao.updateCIQControlValid(ciqControlID);
        if("".equals(retStr)){
            retStr = "true";
        }
        JSONObject result = DefaultResultUtil.getModificationResult(retStr);
        return result; 
    }

}
