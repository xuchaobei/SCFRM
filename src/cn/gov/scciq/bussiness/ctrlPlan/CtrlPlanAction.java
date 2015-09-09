package cn.gov.scciq.bussiness.ctrlPlan;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

/**
 * 监控计划结果登记
 * 
 * @author chao.xu
 *
 */
public class CtrlPlanAction {
	private static Log log=LogFactory.getLog(CtrlPlanAction.class);
    
	private int draw;
    
    private int start;
    
    private int length;
    
    private String data;
    
    private String ctrlPlanImpID;
    
    private String ctrlPlanItemID;
    
    private JSONObject result;
    
    
    public String getCtrlPlanImp(){
        try {
            result = CtrlPlanService.getCtrlPlanImp(data, draw, start, length);
       log.info("imp"+result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }

    public String getCtrlPlanItem(){
        try {
            result = CtrlPlanService.getCtrlPlanItem(ctrlPlanImpID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String saveCtrlPlanImp(){
        try {
        	System.out.println("ctrlpandata"+data);   
            result = CtrlPlanService.saveCtrlPlanImp(data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }

    
    public String delCtrlPlanImp(){
        try {
            result = CtrlPlanService.delCtrlPlanImp(ctrlPlanImpID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String delCtrlPlanItem(){
        try {
            result = CtrlPlanService.delCtrlPlanItem(ctrlPlanItemID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String getCtrlPlanImpByID(){
        try {
            result = CtrlPlanService.getCtrlPlanImpByID(ctrlPlanImpID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String saveCtrlPlanItem(){
        try {
            result = CtrlPlanService.saveCtrlPlanItem(data);
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

  	public String getCtrlPlanImpID() {
		return ctrlPlanImpID;
	}

	public void setCtrlPlanImpID(String ctrlPlanImpID) {
		this.ctrlPlanImpID = ctrlPlanImpID;
	}

	public String getCtrlPlanItemID() {
		return ctrlPlanItemID;
	}

	public void setCtrlPlanItemID(String ctrlPlanItemID) {
		this.ctrlPlanItemID = ctrlPlanItemID;
	}

	public JSONObject getResult() {
  		return result;
  	}

  	public void setResult(JSONObject result) {
  		this.result = result;
  	}

}
