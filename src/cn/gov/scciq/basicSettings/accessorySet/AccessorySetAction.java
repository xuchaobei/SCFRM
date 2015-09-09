package cn.gov.scciq.basicSettings.accessorySet;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

import cn.gov.scciq.util.DefaultResultUtil;



public class AccessorySetAction  {
	 private String data;
		private JSONObject result;
		private int draw;
		private int start;
	    private int length;
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
		
		private static Log log=LogFactory.getLog(AccessorySetAction.class);
		AccessorySetService accessorySetService = new AccessorySetService();

		
		public String SaveAccessory() throws Exception {
			     JSONObject object = JSONObject.fromObject(data);
			     log.info(object);
			     AccessorySetBean accessorySetBean=(AccessorySetBean)JSONObject.toBean(object,AccessorySetBean.class);
			     String retStr=accessorySetService.SaveAccessory(accessorySetBean);
			     if("".equals(retStr)){
			            retStr = "true";
			        }
			     result=DefaultResultUtil.getModificationResult(retStr);
			     return "success"; 
		}
	
		public String DelAccessory() throws Exception {
			 JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     AccessorySetBean accessorySetBean=(AccessorySetBean)JSONObject.toBean(object,AccessorySetBean.class);
		     String retStr=accessorySetService.DelAccessory(accessorySetBean);
		     result=DefaultResultUtil.getModificationResult(retStr);
		     return "success"; 
		}
		public String getAccessory() throws Exception {
			 JSONObject object = JSONObject.fromObject(data);
		     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		     AccessorySetBean proBean=(AccessorySetBean)JSONObject.toBean(object,AccessorySetBean.class);
		     Map<Integer, Object> rsMap=  accessorySetService.GetAccessory(proBean, start, length);
		       int recordsTotal = (Integer)rsMap.get(1);
		        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
			 return Action.SUCCESS;
		}
		

}
