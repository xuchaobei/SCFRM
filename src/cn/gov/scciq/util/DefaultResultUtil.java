package cn.gov.scciq.util;

import cn.gov.scciq.dto.TableResultDto;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DefaultResultUtil {

    /**
     * 获取传递到前台的默认结果格式(list格式)
     * @param obj
     * @return
     */
    public static JSONObject getDefaultResult(Object obj){
        JSONArray ja = JSONArray.fromObject(obj);
        JSONObject data = new JSONObject();
        data.put("data", ja);
        return data;
    }
    
    /**
     * 获取执行增删改动作后的结果格式
     * @param obj
     * @return
     */
    public static JSONObject getModificationResult(Object obj){
        JSONObject data = new JSONObject();
        data.put("data", obj);
        return data;
    }
    
    /**
     * 获取传递到前台table中的结果格式
     * @param draw
     * @param recordsTotal
     * @param recordsFilter
     * @param data
     * @return
     */
    public static JSONObject getDefaultTableResult(int draw, int recordsTotal, int recordsFilter, Object data){
        JSONArray ja = JSONArray.fromObject(data);
        TableResultDto result = new TableResultDto();
        result.setDraw(draw);
        result.setRecordsTotal(recordsTotal);
        result.setRecordsFiltered(recordsTotal);
        result.setData(ja);
        return JSONObject.fromObject(result);
    }
    
    public static void main(String[] args) {
        getDefaultResult("true");
    }
}
