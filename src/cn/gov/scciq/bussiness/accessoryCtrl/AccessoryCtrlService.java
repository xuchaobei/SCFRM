package cn.gov.scciq.bussiness.accessoryCtrl;

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

public class AccessoryCtrlService {
    
    public static JSONObject getAccessoryControl(String data, int draw, int start, int length){
        int startIndex = 0;
        int pageSize = 0;
        String orderWord = "AccessoryControlID";
        String orderDirection = "DESC";
        Map<Integer, Object> rsMap = AccessoryCtrlDao.getAccessoryControl(data, startIndex, pageSize, orderWord, orderDirection);
        int recordsTotal = (Integer)rsMap.get(1);
        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
        JSONObject result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
        return result;
    }
    
    public static JSONObject getAccessoryControlDetailByID(String accessoryControlID){
        AccessoryCtrlDetailResDto dto = AccessoryCtrlDao.getAccessoryControlDetailByID(accessoryControlID);
        return JSONObject.fromObject(dto);
    }
    
    public static JSONObject saveAccessoryControl(String data){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        AccessoryCtrlReqDto accessoryCtrl = (AccessoryCtrlReqDto)JSONObject.toBean(JSONObject.fromObject(data),AccessoryCtrlReqDto.class);
        String retStr = AccessoryCtrlDao.saveAccessoryControl(accessoryCtrl.getAccessoryControlID(), accessoryCtrl.getAccessoryName(), accessoryCtrl.getProductClassCode(), accessoryCtrl.getProductSubclassCode(),
                accessoryCtrl.getMaterialClassCode(), accessoryCtrl.getMaterialSubclassCode(), accessoryCtrl.getMaterialCode(), accessoryCtrl.getCountryCode(), 
                ContextUtil.getOrgCode(), ContextUtil.getDeptCode(), ContextUtil.getOperatorCode());
        if("".equals(retStr)){
            retStr = "true";
        }
        JSONObject result = DefaultResultUtil.getModificationResult(retStr);
        return result;  
    }
    
    public static JSONObject delAccessoryControl(String accessoryControlID){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        boolean retCode = AccessoryCtrlDao.delAccessoryControl(accessoryControlID);
        JSONObject jo = DefaultResultUtil.getModificationResult(retCode+"");
        return jo;
    }    
    
    public static JSONObject delAccessoryControlItem(String accessoryControlItemID){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        boolean retCode = AccessoryCtrlDao.delAccessoryControlItem(accessoryControlItemID);
        JSONObject jo = DefaultResultUtil.getModificationResult(retCode+"");
        return jo;
    }   
    
    public static JSONObject getAccessoryControlItem(String accessoryControlID){
        List<AccessoryCtrlItemResDto> list = AccessoryCtrlDao.getAccessoryControlItem(accessoryControlID);
        JSONArray ja = JSONArray.fromObject(list);
        JSONObject result = DefaultResultUtil.getDefaultResult(ja);
        return result;
    }

    public static JSONObject getAccessoryControlItemDetailByID(String accessoryControlItemID){
        AccessoryCtrlItemDetailDto dto = AccessoryCtrlDao.getAccessoryControlItemDetailByID(accessoryControlItemID);
        JSONObject jo = new JSONObject();
        jo.put("ctrlItem", JSONObject.fromObject(dto.getItemDetail()));
        jo.put("itemLimit", JSONArray.fromObject(dto.getItemLimitList()));
        return jo;
    }
    
    
    public static JSONObject saveAccessoryControlItem(String data){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        String retStr = "";
        AccessoryCtrlItemReqDto accessoryCtrlItem = (AccessoryCtrlItemReqDto)JSONObject.toBean(JSONObject.fromObject(data),AccessoryCtrlItemReqDto.class);
        //新增布控项目
        if(accessoryCtrlItem.getAccessoryControlItemID().equals("0")){
            String accessoryCtrlItemID = AccessoryCtrlDao.saveAccessoryControlItem(accessoryCtrlItem.getAccessoryControlItemID() , accessoryCtrlItem.getAccessoryControlID(), accessoryCtrlItem.getItemCode(),
                    accessoryCtrlItem.getItemName(), accessoryCtrlItem.getDetectionStd(),accessoryCtrlItem.getSamplingRatio(),accessoryCtrlItem.getLimitType(),  accessoryCtrlItem.getDetectionLimit(), accessoryCtrlItem.getLimitUnit(), 
                    ContextUtil.getOrgCode(), ContextUtil.getDeptCode());
            Pattern pattern = Pattern.compile("\\d+$");
            Matcher matcher = pattern.matcher(accessoryCtrlItemID);
            if(matcher.matches()){
                JSONArray itemLimitList = accessoryCtrlItem.getItemLimitList();
                for(int i = 0; i < itemLimitList.size(); i++){
                    JSONObject jo = itemLimitList.getJSONObject(i);
                    ConvCtrlItemLimitDto itemLimit = (ConvCtrlItemLimitDto)JSONObject.toBean(jo, ConvCtrlItemLimitDto.class);
                    AccessoryCtrlDao.saveAccessoryControlItemLimit(accessoryCtrlItemID, itemLimit.getCountryCode(), itemLimit.getCountryName(),itemLimit.getDetectionLimit(),itemLimit.getLimitUnit());
                }
            }else{
               retStr = accessoryCtrlItemID;
            }
        }
        //修改布控项目
        else{
            retStr = AccessoryCtrlDao.saveAccessoryControlItem(accessoryCtrlItem.getAccessoryControlItemID() , accessoryCtrlItem.getAccessoryControlID(), accessoryCtrlItem.getItemCode(),
                    accessoryCtrlItem.getItemName(), accessoryCtrlItem.getDetectionStd(),accessoryCtrlItem.getSamplingRatio(),accessoryCtrlItem.getLimitType(),  accessoryCtrlItem.getDetectionLimit(), accessoryCtrlItem.getLimitUnit(), 
                    ContextUtil.getOrgCode(), ContextUtil.getDeptCode());
            if(retStr.equals("")){
                JSONArray itemLimitList = accessoryCtrlItem.getItemLimitList();
                for(int i = 0; i < itemLimitList.size(); i++){
                    JSONObject jo = itemLimitList.getJSONObject(i);
                    ConvCtrlItemLimitDto itemLimit = (ConvCtrlItemLimitDto)JSONObject.toBean(jo, ConvCtrlItemLimitDto.class);
                    AccessoryCtrlDao.saveAccessoryControlItemLimit(accessoryCtrlItem.getAccessoryControlItemID(), itemLimit.getCountryCode(), itemLimit.getCountryName(),itemLimit.getDetectionLimit(),itemLimit.getLimitUnit());
                }
            }
        }
        if("".equals(retStr)){
            retStr = "true";
        }
        JSONObject result = DefaultResultUtil.getModificationResult(retStr);
        return result;
    }
    
    
}
