package cn.gov.scciq.basicSettings.materialClass;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

import cn.gov.scciq.basicSettings.processingWay.ProcessingWayAction;
import cn.gov.scciq.basicSettings.processingWay.ProcessingWayBean;
import cn.gov.scciq.util.DefaultResultUtil;

public class MaterialClassAction  {
        private String data;
		private JSONObject result;
		private int draw;
	    private int start;
	    private int length;
	    private String setscontent;
	    
	    public String getSetscontent() {
			return setscontent;
		}
		public void setSetscontent(String setscontent) {
			this.setscontent = setscontent;
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
		
		private static Log log=LogFactory.getLog(ProcessingWayAction.class);
		MaterialClassService materialClassService = new MaterialClassService();
		
		public String SaveMaterialClass() throws Exception{
			  
		     JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     MaterialClassBean materialClassBean=(MaterialClassBean)JSONObject.toBean(object,MaterialClassBean.class);
			 String retStr=materialClassService.SaveMaterialClass(materialClassBean);
			 if("".equals(retStr)){
		            retStr = "true";
		        }
			 result=DefaultResultUtil.getModificationResult(retStr);
			 return "success"; 
		}
	
		
		public String SaveMaterialSubclass() throws Exception {
			 JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     MaterialSubclassBean bean=(MaterialSubclassBean)JSONObject.toBean(object,MaterialSubclassBean.class);
		     String retStr=materialClassService.SaveMaterialSubclass(bean);
		     if("".equals(retStr)){
		            retStr = "true";
		        }
			 result=DefaultResultUtil.getModificationResult(retStr);
			 return "success"; 
		}
		
		public String SaveMaterialSubsubclass() throws Exception {
			JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     MaterialSubsubClassBean materialClassBean=(MaterialSubsubClassBean)JSONObject.toBean(object,MaterialSubsubClassBean.class);
		     String retStr=materialClassService.SaveMaterialSubsubclass(materialClassBean);
		     if("".equals(retStr)){
		            retStr = "true";
		        }
			 result=DefaultResultUtil.getModificationResult(retStr);
			 return "success"; 
		}
		
		public String SaveMaterialSubsubclassSub() throws Exception {
			JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     String[] setarr = setscontent.split(",");
		     MaterialSubsubClassBean bean=(MaterialSubsubClassBean)JSONObject.toBean(object,MaterialSubsubClassBean.class);
		     String retStr=materialClassService.SaveMaterialSubsubclassSub(bean,setarr);
			 if("".equals(retStr)){
		            retStr = "true";
		        }
			 result=DefaultResultUtil.getModificationResult(retStr);
			 return "success"; 
		}
		
		public String DelMaterialClass() throws Exception {
			 JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     MaterialClassBean materialClassBean=(MaterialClassBean)JSONObject.toBean(object,MaterialClassBean.class);
		     String retStr=materialClassService.DelMaterialClass(materialClassBean);
		     result=DefaultResultUtil.getModificationResult(retStr);
			 return "success"; 
		}
		
		public String DelMaterialSubclass() throws Exception {
			 JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     MaterialSubclassBean materialClassBean=(MaterialSubclassBean)JSONObject.toBean(object,MaterialSubclassBean.class);
		     String retStr=materialClassService.DelMaterialSubclass(materialClassBean);
		     result=DefaultResultUtil.getModificationResult(retStr);
			 return "success"; 
		}
		
		public String DelMaterialSubsubclass()  throws Exception {     
			 JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     MaterialSubsubClassBean materialClassBean=(MaterialSubsubClassBean)JSONObject.toBean(object,MaterialSubsubClassBean.class);
		     String retStr=materialClassService.DelMaterialSubsubclass(materialClassBean);
		     result=DefaultResultUtil.getModificationResult(retStr);
			 return "success"; 
		}
		public String getMaterialClass() throws Exception{
			 JSONObject object = JSONObject.fromObject(data);
		     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		     MaterialClassBean proBean=(MaterialClassBean)JSONObject.toBean(object,MaterialClassBean.class);
		     Map<Integer, Object> rsMap=  materialClassService.getMaterialClass(proBean, start, length);
		       int recordsTotal = (Integer)rsMap.get(1);
		        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
			 return Action.SUCCESS;
			
			
		}
		public String getMaterialSubClass() throws Exception{
			 JSONObject object = JSONObject.fromObject(data);
		     log.info("提交参数："+data+"draw is "+draw+" start is"+start+" length is "+length);
		     MaterialSubclassBean proBean=(MaterialSubclassBean)JSONObject.toBean(object,MaterialSubclassBean.class);
		     Map<Integer, Object> rsMap=  materialClassService.getMaterialSubclass(proBean, start, length);
		       int recordsTotal = (Integer)rsMap.get(1);
		        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
			 return Action.SUCCESS;
					
		}
		public String getMaterialSubsubClass() throws Exception{
			 JSONObject object = JSONObject.fromObject(data);
		     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		     MaterialSubsubClassBean proBean=(MaterialSubsubClassBean)JSONObject.toBean(object,MaterialSubsubClassBean.class);
		     Map<Integer, Object> rsMap=  materialClassService.getMaterialSubsubClass(proBean, start, length);
		       int recordsTotal = (Integer)rsMap.get(1);
		        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
			 return Action.SUCCESS;
			
		}
		public String getMaterialSubsubClassNP() throws Exception{
			 JSONObject object = JSONObject.fromObject(data);
		     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		     MaterialSubsubClassBean proBean=(MaterialSubsubClassBean)JSONObject.toBean(object,MaterialSubsubClassBean.class);
		     Map<Integer, Object> rsMap=  materialClassService.getMaterialSubsubClass(proBean, start, length);
		     result = DefaultResultUtil.getDefaultResult(rsMap.get(2));
			 return Action.SUCCESS;
			
		}
		public String getMaterialSubsubclassSub() throws Exception{
			 JSONObject object = JSONObject.fromObject(data);
		     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		     MaterialSubsubclassSubBean proBean=(MaterialSubsubclassSubBean)JSONObject.toBean(object,MaterialSubsubclassSubBean.class);
		     List<MaterialSubsubclassSubBean> list=  materialClassService.getMaterialSubsubclassSub(proBean);
		     result = DefaultResultUtil.getDefaultResult(list);
			 return Action.SUCCESS;
			
		}
}
