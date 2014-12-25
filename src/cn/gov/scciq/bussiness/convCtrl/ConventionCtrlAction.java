package cn.gov.scciq.bussiness.convCtrl;

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
    
    private String convCtrlID;
    
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

    public String getConvCtrlID() {
        return convCtrlID;
    }

    public void setConvCtrlID(String convCtrlID) {
        this.convCtrlID = convCtrlID;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public String getConvCtrlItemID() {
        return convCtrlItemID;
    }

    public void setConvCtrlItemID(String convCtrlItemID) {
        this.convCtrlItemID = convCtrlItemID;
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
     * 获取常规布控详情
     * @return
     */
    public String getConvCtrlDetail(){
        result = convCtrlService.getConvCtrlDetailByID(convCtrlID);
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
     * 删除常规布控
     * @return
     */
    public String delConvCtrl(){
        result = convCtrlService.delConvCtrl(convCtrlID);
        return Action.SUCCESS;
    }
    
    /**
     * 获取布控项目
     * @return
     */
    public String getConvCtrlItem(){
        result = convCtrlService.getConvCtrlItem(convCtrlID);
        return Action.SUCCESS;
    }
    
    
    /**
     * 保存布控项目
     * @return
     */
    public String saveConvCtrlItem(){
        result = convCtrlService.saveConvCtrlItem(data);
        return Action.SUCCESS;
    }
    
    /** 布控项目id */
    private String convCtrlItemID;
    
    /**
     * 根据布控项目id，获取布控项目详细数据及其限量表数据
     * @return
     */
    public String getConvCtrlItemDetail(){
        result = convCtrlService.getConvCtrlItemDetailByID(convCtrlItemID);
        return Action.SUCCESS;
    }
    
    /**
     * 删除布控项目
     * @return
     */
    public String delConvCtrlItem(){
        result = convCtrlService.delConvCtrlItem(convCtrlItemID);
        return Action.SUCCESS;
    }
}


    
    
