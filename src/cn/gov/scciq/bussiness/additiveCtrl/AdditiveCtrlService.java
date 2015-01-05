package cn.gov.scciq.bussiness.additiveCtrl;

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

public class AdditiveCtrlService {
    
    public static JSONObject getAdditiveControl(String data, int draw, int start, int length){
        int startIndex = 0;
        int pageSize = 0;
        String orderWord = "AdditiveControlID";
        String orderDirection = "DESC";
        Map<Integer, Object> rsMap = AdditiveCtrlDao.getAdditiveControl(data, startIndex, pageSize, orderWord, orderDirection);
        int recordsTotal = (Integer)rsMap.get(1);
        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
        JSONObject result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
        return result;
    }
    
    public static JSONObject getAdditiveControlDetailByID(String AdditiveControlID){
        AdditiveCtrlDetailResDto dto = AdditiveCtrlDao.getAdditiveControlDetailByID(AdditiveControlID);
        return JSONObject.fromObject(dto);
    }
    
    public static JSONObject saveAdditiveControl(String data){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        AdditiveCtrlReqDto AdditiveCtrl = (AdditiveCtrlReqDto)JSONObject.toBean(JSONObject.fromObject(data),AdditiveCtrlReqDto.class);
        String retStr = AdditiveCtrlDao.saveAdditiveControl(AdditiveCtrl.getAdditiveControlID(), AdditiveCtrl.getAdditiveName(), AdditiveCtrl.getProductClassCode(), AdditiveCtrl.getProductSubclassCode(),
                AdditiveCtrl.getMaterialClassCode(), AdditiveCtrl.getMaterialSubclassCode(), AdditiveCtrl.getMaterialCode(), AdditiveCtrl.getCountryCode(), 
                ContextUtil.getOrgCode(), ContextUtil.getDeptCode(), ContextUtil.getOperatorCode());
        if("".equals(retStr)){
            retStr = "true";
        }
        JSONObject result = DefaultResultUtil.getModificationResult(retStr);
        return result;  
    }
    
    public static JSONObject delAdditiveControl(String AdditiveControlID){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        boolean retCode = AdditiveCtrlDao.delAdditiveControl(AdditiveControlID);
        JSONObject jo = DefaultResultUtil.getModificationResult(retCode+"");
        return jo;
    }    
    
    public static JSONObject delAdditiveControlItem(String AdditiveControlItemID){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        boolean retCode = AdditiveCtrlDao.delAdditiveControlItem(AdditiveControlItemID);
        JSONObject jo = DefaultResultUtil.getModificationResult(retCode+"");
        return jo;
    }   
    
    public static JSONObject getAdditiveControlItem(String AdditiveControlID){
        List<AdditiveCtrlItemResDto> list = AdditiveCtrlDao.getAdditiveControlItem(AdditiveControlID);
        JSONArray ja = JSONArray.fromObject(list);
        JSONObject result = DefaultResultUtil.getDefaultResult(ja);
        return result;
    }

    public static JSONObject getAdditiveControlItemDetailByID(String AdditiveControlItemID){
        AdditiveCtrlItemDetailDto dto = AdditiveCtrlDao.getAdditiveControlItemDetailByID(AdditiveControlItemID);
        JSONObject jo = new JSONObject();
        jo.put("ctrlItem", JSONObject.fromObject(dto.getItemDetail()));
        jo.put("itemLimit", JSONArray.fromObject(dto.getItemLimitList()));
        return jo;
    }
    
    
    public static JSONObject saveAdditiveControlItem(String data){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        String retStr = "";
        AdditiveCtrlItemReqDto AdditiveCtrlItem = (AdditiveCtrlItemReqDto)JSONObject.toBean(JSONObject.fromObject(data),AdditiveCtrlItemReqDto.class);
        //新增布控项目
        if(AdditiveCtrlItem.getAdditiveControlItemID().equals("0")){
            String AdditiveCtrlItemID = AdditiveCtrlDao.saveAdditiveControlItem(AdditiveCtrlItem.getAdditiveControlItemID() , AdditiveCtrlItem.getAdditiveControlID(), AdditiveCtrlItem.getItemCode(),
                    AdditiveCtrlItem.getItemName(), AdditiveCtrlItem.getDetectionStd(),AdditiveCtrlItem.getSamplingRatio(),AdditiveCtrlItem.getLimitType(),  AdditiveCtrlItem.getDetectionLimit(), AdditiveCtrlItem.getLimitUnit(), 
                    ContextUtil.getOrgCode(), ContextUtil.getDeptCode());
            Pattern pattern = Pattern.compile("\\d+$");
            Matcher matcher = pattern.matcher(AdditiveCtrlItemID);
            if(matcher.matches()){
                JSONArray itemLimitList = AdditiveCtrlItem.getItemLimitList();
                for(int i = 0; i < itemLimitList.size(); i++){
                    JSONObject jo = itemLimitList.getJSONObject(i);
                    ConvCtrlItemLimitDto itemLimit = (ConvCtrlItemLimitDto)JSONObject.toBean(jo, ConvCtrlItemLimitDto.class);
                    AdditiveCtrlDao.saveAdditiveControlItemLimit(AdditiveCtrlItemID, itemLimit.getCountryCode(), itemLimit.getCountryName(),itemLimit.getDetectionLimit(),itemLimit.getLimitUnit());
                }
            }else{
               retStr = AdditiveCtrlItemID;
            }
        }
        //修改布控项目
        else{
            retStr = AdditiveCtrlDao.saveAdditiveControlItem(AdditiveCtrlItem.getAdditiveControlItemID() , AdditiveCtrlItem.getAdditiveControlID(), AdditiveCtrlItem.getItemCode(),
                    AdditiveCtrlItem.getItemName(), AdditiveCtrlItem.getDetectionStd(),AdditiveCtrlItem.getSamplingRatio(),AdditiveCtrlItem.getLimitType(),  AdditiveCtrlItem.getDetectionLimit(), AdditiveCtrlItem.getLimitUnit(), 
                    ContextUtil.getOrgCode(), ContextUtil.getDeptCode());
            if(retStr.equals("")){
                JSONArray itemLimitList = AdditiveCtrlItem.getItemLimitList();
                for(int i = 0; i < itemLimitList.size(); i++){
                    JSONObject jo = itemLimitList.getJSONObject(i);
                    ConvCtrlItemLimitDto itemLimit = (ConvCtrlItemLimitDto)JSONObject.toBean(jo, ConvCtrlItemLimitDto.class);
                    AdditiveCtrlDao.saveAdditiveControlItemLimit(AdditiveCtrlItem.getAdditiveControlItemID(), itemLimit.getCountryCode(), itemLimit.getCountryName(),itemLimit.getDetectionLimit(),itemLimit.getLimitUnit());
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
