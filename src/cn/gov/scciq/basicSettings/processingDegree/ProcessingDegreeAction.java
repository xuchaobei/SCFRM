package cn.gov.scciq.basicSettings.processingDegree;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

import cn.gov.scciq.basicSettings.processingWay.ProcessingWayBean;
import cn.gov.scciq.util.DefaultResultUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProcessingDegreeAction{
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
		
		private static Log log=LogFactory.getLog(ProcessingDegreeAction.class);
        ProcessingDegreeService processingDegreeService = new ProcessingDegreeService();
	
	public String SaveProcessingDegree()
			throws Exception {
	     JSONObject object = JSONObject.fromObject(data);
	     log.info(object);
	     ProcessingDegreeBean processingDegreeBean=(ProcessingDegreeBean)JSONObject.toBean(object,ProcessingDegreeBean.class);
		 String reStr=processingDegreeService.SaveProcessingDegree(processingDegreeBean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
	}


	public String DelProcessingDegree()
			throws Exception {
		 JSONObject object = JSONObject.fromObject(data);
	     log.info(object);
	     ProcessingDegreeBean processingDegreeBean=(ProcessingDegreeBean)JSONObject.toBean(object,ProcessingDegreeBean.class);
	     String reStr=processingDegreeService.DelProcessingDegree(processingDegreeBean);
	     result=DefaultResultUtil.getModificationResult(reStr);
	     return "success"; 
	}
    public String getProcessingDegree() throws Exception{
    	 JSONObject object = JSONObject.fromObject(data);
	     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
	     ProcessingDegreeBean proBean=(ProcessingDegreeBean)JSONObject.toBean(object,ProcessingDegreeBean.class);
	     Map<Integer, Object> rsMap=  processingDegreeService.GetProcessingDegree(start, length);
	     int recordsTotal = (Integer)rsMap.get(1);
	     JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	     result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
		 return Action.SUCCESS;
    }
}
