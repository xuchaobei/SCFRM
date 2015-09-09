package cn.gov.scciq.systemManage.resetPSWord;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

import cn.gov.scciq.util.DefaultResultUtil;
public class ResetPSWAction {
	
	 private static Log log=LogFactory.getLog(ResetPSWAction.class);
	 JSONObject result=null;
	 ResetPSWService resetPSWService = new ResetPSWService();
	 private String data;
	 
	 
     public JSONObject getResult() {
		return result;
	}


	public void setResult(JSONObject result) {
		this.result = result;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String ChangeOperatorPassword() throws Exception{
	     JSONObject object = JSONObject.fromObject(data);
	     log.info("reset PSW "+object);
	     ResetPSWBean bean=(ResetPSWBean)JSONObject.toBean(object,ResetPSWBean.class);
	     String retStr=resetPSWService.ChangeOperatorPassword(bean);
	     if("".equals(retStr)){
	            retStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(retStr);
		 return Action.SUCCESS;
	
 }
}
