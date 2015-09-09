package cn.gov.scciq.bussiness.accessoryCtrl;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

/**
 * 辅料布控
 * 
 * @author chao.xu
 *
 */
public class AccessoryCtrlAction {
    
	private static Log log=LogFactory.getLog(AccessoryCtrlAction.class);
    
    private int draw;
    
    private int start;
    
    private int length;
    
    private String data;
    
    private String accessoryControlID;
    
    private String accessoryControlItemID;
    
    private JSONObject result;
    
    
    public String getAccessoryControl(){
        try {
            result = AccessoryCtrlService.getAccessoryControl(data, draw, start, length);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }

    public String getAccessoryControlDetailByID(){
        try {
            result = AccessoryCtrlService.getAccessoryControlDetailByID(accessoryControlID);
        log.info("detail "+result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String saveAccessoryControl(){
        try {
            result = AccessoryCtrlService.saveAccessoryControl(data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }

    
    public String delAccessoryControl(){
        try {
            result = AccessoryCtrlService.delAccessoryControl(accessoryControlID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String delAccessoryControlItem(){
        try {
            result = AccessoryCtrlService.delAccessoryControlItem(accessoryControlItemID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String getAccessoryControlItem(){
        try {
            result = AccessoryCtrlService.getAccessoryControlItem(accessoryControlID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String getAccessoryControlItemDetailByID(){
        try {
            result = AccessoryCtrlService.getAccessoryControlItemDetailByID(accessoryControlItemID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String saveAccessoryControlItem(){
        try {
            result = AccessoryCtrlService.saveAccessoryControlItem(data);
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

    public String getAccessoryControlID() {
        return accessoryControlID;
    }

    public void setAccessoryControlID(String accessoryControlID) {
        this.accessoryControlID = accessoryControlID;
    }

    public String getAccessoryControlItemID() {
        return accessoryControlItemID;
    }

    public void setAccessoryControlItemID(String accessoryControlItemID) {
        this.accessoryControlItemID = accessoryControlItemID;
    }
    
    
}
