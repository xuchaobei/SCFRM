package cn.gov.scciq.bussiness.foreignReport;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.DefaultResultUtil;

public class ForeignReportService {

    public static JSONObject getForeignReportList(String data, int draw, int start, int length){
        ForeignReportReqDto foreignReport = null;
        if(data != null && !"".equals(data)){
            foreignReport = (ForeignReportReqDto)JSONObject.toBean(JSONObject.fromObject(data), ForeignReportReqDto.class);
        }else{
            foreignReport = new ForeignReportReqDto();
        }
        int startIndex = 0;
        int pageSize = 0;
        String orderWord = "ForeignReportingID";
        String orderDirection = "DESC";
        
        Map<Integer, Object> rsMap = ForeignReportDao.getForeignReportList(foreignReport.getReportingTitle(), foreignReport.getCountryName(), foreignReport.getEntName(), startIndex, pageSize, orderWord, orderDirection);
        int recordsTotal = (Integer)rsMap.get(1);
        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
        JSONObject result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
        return result;
    }
    
    public static JSONObject getForeignReportingItem(String foreignReportingID){
        List<ForeignReportingItemResDto> list = ForeignReportDao.getForeignReportingItem(foreignReportingID);
        JSONArray ja = JSONArray.fromObject(list);  
        JSONObject result = DefaultResultUtil.getDefaultResult(ja);
        return result;
    }
    
    public static JSONObject delForeignReporting(String foreignReportingID){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        boolean retCode = ForeignReportDao.delForeignReporting(foreignReportingID);
        JSONObject jo = DefaultResultUtil.getModificationResult(retCode+"");
        return jo;
    }
    
    public static JSONObject getForeignReportingDetailByID(String foreignReportingID){
        ForeignReportDetailResDto dto = ForeignReportDao.getForeignReportingDetailByID(foreignReportingID);
        return JSONObject.fromObject(dto);
    }
    
    public static JSONObject saveForeignReporting(String data){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        ForeignReportDetailReqDto dto = (ForeignReportDetailReqDto)JSONObject.toBean(JSONObject.fromObject(data), ForeignReportDetailReqDto.class);
        String retStr = ForeignReportDao.saveForeignReporting(dto.getForeignReportingID(), dto.getReportingTitle(), dto.getReportingDate(), dto.getReportingReason(), dto.getEntCode(), 
                dto.getProductClassCode(), dto.getProductSubclassCode(), dto.getMaterialClassCode(), dto.getMaterialSubclassCode(), dto.getMaterialCode(), dto.getMaterialSourceCode(), 
                dto.getProcessingMethodCode(), dto.getPackageTypeCode(), dto.getIntentedUseCode(), dto.getCountryCode(), ContextUtil.getOrgCode(), ContextUtil.getDeptCode(), 
                ContextUtil.getOperatorCode());
        if("".equals(retStr)){
            retStr = "true";
        }
        JSONObject result = DefaultResultUtil.getModificationResult(retStr);
        return result;  
    }
    
    public static JSONObject saveForeignReportingItem(String data){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        ForeignReportingItemReqDto dto = (ForeignReportingItemReqDto)JSONObject.toBean(JSONObject.fromObject(data), ForeignReportingItemReqDto.class);
        String retStr = ForeignReportDao.saveForeignReportingItem(dto.getReportingItemID(), dto.getForeignReportingID(), dto.getItemCode());
        if("".equals(retStr)){
            retStr = "true";
        }
        JSONObject result = DefaultResultUtil.getModificationResult(retStr);
        return result; 
    }
    
    public static JSONObject delForeignReportingItem(String reportingItemID){
        String permission = AuthorityDao.getOperateLimit(ConstantStr.RISK_CONTROL);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
        boolean retCode = ForeignReportDao.delForeignReporting(reportingItemID);
        JSONObject jo = DefaultResultUtil.getModificationResult(retCode+"");
        return jo;
    }
}
