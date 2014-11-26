package cn.gov.scciq.bussiness.ciqCtrl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
    
    
    public static JSONObject getCIQControlCondition(String ciqControlID){
        List<CIQCtrlConditionResDto> list = CIQCtrlDao.getCIQControlCondition(ciqControlID);
        JSONArray ja = JSONArray.fromObject(list);
        JSONObject result = DefaultResultUtil.getDefaultResult(ja);
        return result;
    }
    
    public static JSONObject getCIQControlItem(String ciqControlID){
        List<CIQCtrlItemResDto> list = CIQCtrlDao.getCIQControlItem(ciqControlID);
        JSONArray ja = JSONArray.fromObject(list);
        JSONObject result = DefaultResultUtil.getDefaultResult(ja);
        return result;
    }
}
