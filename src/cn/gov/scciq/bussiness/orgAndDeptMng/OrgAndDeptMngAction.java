package cn.gov.scciq.bussiness.orgAndDeptMng;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

/**
 * 机构部门管理
 * 
 * @author chao.xu
 *
 */
public class OrgAndDeptMngAction {
	private static Log log=LogFactory.getLog(OrgAndDeptMngAction.class);
    
	private int draw;
    
    private int start;
    
    private int length;
    
    private String inspOrgID;
    
    private String inspDeptID;
    
    private String data;
    
    private JSONObject result;
    
    
    public String getInspOrg(){
        try {
            result = OrgAndDeptMngService.getInspOrg(draw, start, length);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }

    public String getInspDeptPaging(){
        try {
            result = OrgAndDeptMngService.getInspDeptPaging(data,draw, start, length);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String saveInspOrg(){
        try {
            result = OrgAndDeptMngService.saveInspOrg(data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }

	public String checkPermission(){
		try {
			result = OrgAndDeptMngService.checkPermission();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
    
    
    public String delInspOrg(){
        try {
            result = OrgAndDeptMngService.delInspOrg(inspOrgID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String delInspDept(){
        try {
            result = OrgAndDeptMngService.delInspDept(inspDeptID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String saveInspDept(){
        try {
            result = OrgAndDeptMngService.saveInspDept(data);
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

	public String getInspOrgID() {
		return inspOrgID;
	}

	public void setInspOrgID(String inspOrgID) {
		this.inspOrgID = inspOrgID;
	}

	public String getInspDeptID() {
		return inspDeptID;
	}

	public void setInspDeptID(String inspDeptID) {
		this.inspDeptID = inspDeptID;
	}

	public JSONObject getResult() {
  		return result;
  	}

  	public void setResult(JSONObject result) {
  		this.result = result;
  	}

}
