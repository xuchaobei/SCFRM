package cn.gov.scciq.basicSettings.processingWay;



import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.basicSettings.productClass.ProductClassBean;
import cn.gov.scciq.bussiness.searchSelect.SearchSelectService;
import cn.gov.scciq.util.DefaultResultUtil;

import com.opensymphony.xwork2.Action;



public class ProcessingWayAction {
	    private String data;
		private JSONObject result;
        private String methodName;
        private JSONObject json;
        private String setscontent;
        private int draw;
        private int start;
    	private int length;
        private String processingWayCode;
        private int showFlg;
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
		public JSONObject getJson() {
			return json;
		}
		public void setJson(JSONObject json) {
			this.json = json;
		}
		public String getMethodName() {
			return methodName;
		}
		public void setMethodName(String methodName) {
			this.methodName = methodName;
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
		public String getSetscontent() {
			return setscontent;
		}
		public void setSetscontent(String setscontent) {
			this.setscontent = setscontent;
		}
		public String getProcessingWayCode() {
			return processingWayCode;
		}
		public void setProcessingWayCode(String processingWayCode) {
			this.processingWayCode = processingWayCode;
		}
		
		public int getShowFlg() {
			return showFlg;
		}
		public void setShowFlg(int showFlg) {
			this.showFlg = showFlg;
		}

		private static Log log=LogFactory.getLog(ProcessingWayAction.class);
		ProcessingWayService processingWayService = new ProcessingWayService();
		
		 //获得所有数据
		 public String getProcessingMethodAll() throws Exception{
			 JSONObject object = JSONObject.fromObject(data);
		     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		     ProcessingWayBean proBean=(ProcessingWayBean)JSONObject.toBean(object,ProcessingWayBean.class);
		     Map<Integer, Object> rsMap=  processingWayService.getProductMethod(proBean, showFlg,start, length);
		       int recordsTotal = (Integer)rsMap.get(1);
		        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
			 return Action.SUCCESS;
		 }
		 
		//获得非集合数据
		 public String getProcessingMethod(){
		        int startIndex = 0;
		        int pageSize = 0;
		        int showFlg = 1;
		        String orderWord = "MethodCode";
		        String orderDirection = "DESC";
		        result = SearchSelectService.getProcessingMethod(methodName, showFlg, startIndex, pageSize, orderWord, orderDirection);
		     //   data= json.toString();
		      //  System.out.println("processingwayJson:"+data);
		        return Action.SUCCESS;
		    }
		public String getProcessingMethodSub() throws Exception{
			 JSONObject object = JSONObject.fromObject(data);
		     log.info("obj is "+object);
		     ProcessingWaySubBean processingWayBean=(ProcessingWaySubBean)JSONObject.toBean(object,ProcessingWaySubBean.class);
			 List<ProcessingWaySubBean> list=processingWayService.getProductMethodSub(processingWayBean);
			 result=DefaultResultUtil.getDefaultResult(list);
			 log.info("methodSub is "+result);
			 return Action.SUCCESS;
		}
		public String SaveProcessingMethod() throws Exception{
			  
		     JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     ProcessingWayBean processingWayBean=(ProcessingWayBean)JSONObject.toBean(object,ProcessingWayBean.class);
			 String reStr=processingWayService.SaveProcessingMethod(processingWayBean);
			 if("".equals(reStr)){
		            reStr = "true";
		        }
			 result=DefaultResultUtil.getModificationResult(reStr);
			 return "success"; 
		}
		
		public String SaveProcessingMethodSub() throws Exception{
			  
		     JSONObject object = JSONObject.fromObject(data);
		     log.info("basedata is"+object);
		     log.info(processingWayCode);
		     String[]setarr= setscontent.split(",");
		     ProcessingWayBean processingWayBean=(ProcessingWayBean)JSONObject.toBean(object,ProcessingWayBean.class);
		     String reStr=processingWayService.SaveProcessingMethodSub(processingWayBean,setarr);
		     if("".equals(reStr)){
		            reStr = "true";
		        }
			 result=DefaultResultUtil.getModificationResult(reStr);
			 return "success"; 
		}
		public String DelProcessingMethod() throws Exception{
			  
		     JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     ProcessingWayBean processingWayBean=(ProcessingWayBean)JSONObject.toBean(object,ProcessingWayBean.class);
		     String reStr=processingWayService.DelProcessingMethod(processingWayBean);
		     result=DefaultResultUtil.getModificationResult(reStr);
			 return "success"; 
		}
		
	
		

}
