package cn.gov.scciq.basicSettings.intendedUse;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

import cn.gov.scciq.basicSettings.materialSource.MaterialSourceBean;
import cn.gov.scciq.basicSettings.materialSource.MaterialSourceSubBean;
import cn.gov.scciq.util.DefaultResultUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class IntendedUseAction {
    	
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
		
		private static Log log=LogFactory.getLog(IntendedUseAction.class);
		IntendedUseService intendedUseService = new IntendedUseService();
	
	public String SaveIntendedUse() throws Exception{
		  
	     JSONObject object = JSONObject.fromObject(data);
	     log.info(object);
	     IntendedUseBean intendBean=(IntendedUseBean)JSONObject.toBean(object,IntendedUseBean.class);
	     String retStr=intendedUseService.SaveIntendedUse(intendBean);
	     if("".equals(retStr)){
	            retStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(retStr);
		 return "success"; 
	}
	public String SaveIntendedUseSub() throws Exception{
		  
	     JSONObject object = JSONObject.fromObject(data);
	     log.info(object);
	     log.info("basedata is"+object);
		    
	     String[]setarr= setscontent.split(",");
	     IntendedUseSetBean intendBean=(IntendedUseSetBean)JSONObject.toBean(object,IntendedUseSetBean.class);
	     String retStr=intendedUseService.SaveIntendedUseSub(intendBean,setarr);
	     if("".equals(retStr)){
	            retStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(retStr);
		 return "success"; 
	}
	public String DelIntendedUse() throws Exception{
		  
	     JSONObject object = JSONObject.fromObject(data);
	     log.info(object);
	     IntendedUseBean intendBean=(IntendedUseBean)JSONObject.toBean(object,IntendedUseBean.class);
	     String retStr=intendedUseService.DelIntendedUse(intendBean);
	     result=DefaultResultUtil.getModificationResult(retStr);
		 return "success"; 
	}
   public String getIntendedUse() throws Exception{
	   JSONObject object = JSONObject.fromObject(data);
	     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
	     IntendedUseBean proBean=(IntendedUseBean)JSONObject.toBean(object,IntendedUseBean.class);
	     Map<Integer, Object> rsMap=  intendedUseService.GetIntendedUse(proBean, 0, start, length);
	       int recordsTotal = (Integer)rsMap.get(1);
	        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
		 return Action.SUCCESS;
	   
   }
   public String getIntendedUseSet() throws Exception{
	   JSONObject object = JSONObject.fromObject(data);
	     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
	     IntendedUseBean proBean=(IntendedUseBean)JSONObject.toBean(object,IntendedUseBean.class);
	     Map<Integer, Object> rsMap=  intendedUseService.GetIntendedUse(proBean, 1, 0, 0);
	       int recordsTotal = (Integer)rsMap.get(1);
	        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
		 return Action.SUCCESS;
	   
   }
   public String getIntendedUseSub() throws Exception{
	     JSONObject object = JSONObject.fromObject(data);
	     IntendUseSubBean proBean=(IntendUseSubBean)JSONObject.toBean(object,IntendUseSubBean.class);
		 List<IntendUseSubBean> list = intendedUseService.GetIntendedUseSub(proBean);
		 result =DefaultResultUtil.getDefaultResult(list);
		 System.out.println("hihi"+result);
		 return Action.SUCCESS;
   }
}
