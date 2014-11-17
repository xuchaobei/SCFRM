package cn.gov.scciq.bussiness.riskCtrl;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

/**
 * 常规布控
 * @author chao.xu
 *
 */
public class ConventionCtrlAction{
    
    private static Log log=LogFactory.getLog(ConventionCtrlAction.class);
    
    private int draw;
    
    private int start;
    
    private int length;
    
    private int recordsTotal;
    
    private int recordsFiltered;
    
    private String data;
    
    private String convCtrlId;
    
    private JSONObject result;
    
    private ConventionCtrlService convCtrlService = new ConventionCtrlService();
    
    

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getConvCtrlId() {
        return convCtrlId;
    }

    public void setConvCtrlId(String convCtrlId) {
        this.convCtrlId = convCtrlId;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

 
    /**
     * 获取所有常规布控
     * @return
     */
    public String getConvCtrl(){
        result = convCtrlService.getConvCtrl(data, draw, start, length);
        return Action.SUCCESS;
    }
    
    /**
     * 保存常规布控
     * @return
     */
    public String saveConvCtrl(){
        result = convCtrlService.saveConvCtrl(data);
        return Action.SUCCESS;
    }
    
    /**
     * 删除布控
     * @return
     */
    public String delConvCtrl(){
        result = convCtrlService.delConvCtrl(convCtrlId);
        return Action.SUCCESS;
    }
    
    /**
     * 获取布控项目
     * @return
     */
    public String getItemCtrl(){
        result = convCtrlService.getItemCtrl(convCtrlId);
        return Action.SUCCESS;
    }
    
}


    
    
