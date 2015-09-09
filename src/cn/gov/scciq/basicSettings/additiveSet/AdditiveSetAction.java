package cn.gov.scciq.basicSettings.additiveSet;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

import cn.gov.scciq.basicSettings.materialSource.MaterialSourceBean;
import cn.gov.scciq.util.DefaultResultUtil;

public class AdditiveSetAction {
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
		
		private static Log log=LogFactory.getLog(AdditiveSetAction.class);
		AdditiveSetService additiveSetService = new AdditiveSetService();
		
		public String SaveAdditive( ) throws Exception {
			 JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     AdditiveSetBean additiveSetBean=(AdditiveSetBean)JSONObject.toBean(object,AdditiveSetBean.class);
		     String retStr=additiveSetService.SaveAdditive(additiveSetBean);
		     if("".equals(retStr)){
		            retStr = "true";
		        }
		     result=DefaultResultUtil.getModificationResult(retStr);
		     return "success"; 
		}
		
		public String DelAdditive( ) throws Exception {
			 JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     AdditiveSetBean additiveSetBean=(AdditiveSetBean)JSONObject.toBean(object,AdditiveSetBean.class);
		     String retStr=additiveSetService.DelAdditive(additiveSetBean);
		     result=DefaultResultUtil.getModificationResult(retStr);
		     return "success"; 
		}
		public String getAdditive() throws Exception{
			JSONObject object = JSONObject.fromObject(data);
		     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		     AdditiveSetBean proBean=(AdditiveSetBean)JSONObject.toBean(object,AdditiveSetBean.class);
		     Map<Integer, Object> rsMap=  additiveSetService.GetAdditive(proBean, start, length);
		       int recordsTotal = (Integer)rsMap.get(1);
		        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
			 return Action.SUCCESS;
		}

}
