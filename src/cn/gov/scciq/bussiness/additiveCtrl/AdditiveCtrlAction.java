package cn.gov.scciq.bussiness.additiveCtrl;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.bussiness.convCtrl.ConventionCtrlAction;

import com.opensymphony.xwork2.Action;

/**
 * 添加剂布控
 * 
 * @author chao.xu
 * 
 */
public class AdditiveCtrlAction {

    private static Log log = LogFactory.getLog(ConventionCtrlAction.class);

    private int draw;

    private int start;

    private int length;

    private String data;

    private String additiveControlID;

    private String additiveControlItemID;

    private JSONObject result;

    public String getAdditiveControl() {
        try {
            result = AdditiveCtrlService.getAdditiveControl(data, draw, start, length);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }

    public String getAdditiveControlDetailByID() {
        try {
            result = AdditiveCtrlService.getAdditiveControlDetailByID(additiveControlID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }

    public String saveAdditiveControl() {
        try {
            result = AdditiveCtrlService.saveAdditiveControl(data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }

    public String delAdditiveControl() {
        try {
            result = AdditiveCtrlService.delAdditiveControl(additiveControlID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }

    public String delAdditiveControlItem() {
        try {
            result = AdditiveCtrlService.delAdditiveControlItem(additiveControlItemID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }

    public String getAdditiveControlItem() {
        try {
            result = AdditiveCtrlService.getAdditiveControlItem(additiveControlID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }

    public String getAdditiveControlItemDetailByID() {
        try {
            result = AdditiveCtrlService.getAdditiveControlItemDetailByID(additiveControlItemID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }

    public String saveAdditiveControlItem() {
        try {
            result = AdditiveCtrlService.saveAdditiveControlItem(data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public String getAdditiveControlID() {
        return additiveControlID;
    }

    public void setAdditiveControlID(String additiveControlID) {
        this.additiveControlID = additiveControlID;
    }

    public String getAdditiveControlItemID() {
        return additiveControlItemID;
    }

    public void setAdditiveControlItemID(String additiveControlItemID) {
        this.additiveControlItemID = additiveControlItemID;
    }

}
