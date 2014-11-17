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
                convCtrl.getMaterialCode(), convCtrl.getMaterialSourceCode(), convCtrl.getProcessMethodCode() , convCtrl.getPackageTypeCode(), convCtrl.getIntendedUseCode() , convCtrl.getCountryCode(),
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
                convCtrl.getMaterialSourceCode(), convCtrl.getProcessMethodCode(), convCtrl.getPackageTypeCode(), convCtrl.getIntendedUseCode(), convCtrl.getCountryCode(), 
                convCtrl.getDifferenceCode(), convCtrl.getControlOrgCode(), convCtrl.getControlDeptCode(), convCtrl.getControlOperatorCode());
        if("".equals(retStr)){
            retStr = "true";
        }
        JSONObject result = DefaultResultUtil.getModificationResult(retStr);
        return result;
    }
    
    public JSONObject delConvCtrl(String convCtrlId) {
        // TODO Auto-generated method stub
        boolean retCode = ConventionCtrlDao.delConvCtrlById(convCtrlId);
        JSONObject jo = DefaultResultUtil.getModificationResult(retCode);
        return jo;
    }
    
    public JSONObject getItemCtrl(String convCtrlId){
        List<ConventionCtrlItemDto> list = ConventionCtrlDao.getConvCtrlItem(convCtrlId);
        JSONArray ja = JSONArray.fromObject(list);
        JSONObject result = DefaultResultUtil.getDefaultResult(ja);
        return result;
    }


}
