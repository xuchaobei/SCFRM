package cn.gov.scciq.bussiness.ciqCtrl;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

import cn.gov.scciq.bussiness.riskCtrl.ConventionCtrlAction;

/**
 * 应急布控
 * @author chao.xu
 *
 */
public class CIQCtrlAction {
private static Log log=LogFactory.getLog(ConventionCtrlAction.class);
    
    private int draw;
    
    private int start;
    
    private int length;
    
    private int recordsTotal;
    
    private int recordsFiltered;
    
    private String data;
    
    private String ciqControlID;
    
    private JSONObject result;
    
    
    /**
     * 获取应急布控列表
     * @return
     */
    public String getCIQControl(){
        result = CIQCtrlService.getCIQControl(data, draw, start, length);
        return Action.SUCCESS;
    }
    
    /**
     * 获取应急布控条件
     * @return
     */
    public String getCIQControlCondition(){
        result = CIQCtrlService.getCIQControlCondition(ciqControlID);
        return Action.SUCCESS;
    }
    
    /**
     * 获取应急布控项目
     * @return
     */
    public String getCIQControlItem(){
        result = CIQCtrlService.getCIQControlItem(ciqControlID);
        return Action.SUCCESS;
    }

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

    public String getCiqControlID() {
        return ciqControlID;
    }

    public void setCiqControlID(String ciqControlID) {
        this.ciqControlID = ciqControlID;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }
    
    
}
