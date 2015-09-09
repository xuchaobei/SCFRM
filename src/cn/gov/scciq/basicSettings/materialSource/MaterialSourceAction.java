package cn.gov.scciq.basicSettings.materialSource;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

import cn.gov.scciq.basicSettings.materialClass.MaterialClassBean;
import cn.gov.scciq.util.DefaultResultUtil;



public class MaterialSourceAction  {
	private String data;
	private JSONObject result;
	private int draw;
	private int start;
    private int length;
    private String setscontent;
    
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
	
	public String getSetscontent() {
		return setscontent;
	}
	public void setSetscontent(String setscontent) {
		this.setscontent = setscontent;
	}

	private static Log log=LogFactory.getLog(MaterialSourceAction.class);
	MaterialSourceService materialSourceService = new MaterialSourceService();

	
	public String SaveMaterialSource() throws Exception {
		 JSONObject object = JSONObject.fromObject(data);
	     log.info(object);
	     MaterialSourceBean materialSourceBean=(MaterialSourceBean)JSONObject.toBean(object,MaterialSourceBean.class);
	     String retStr=materialSourceService.SaveMaterialSource(materialSourceBean);
	     if("".equals(retStr)){
	            retStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(retStr);
		 return "success"; 
	}
	
	public String SaveMaterialSourceSub() throws Exception {
		JSONObject object = JSONObject.fromObject(data);
		 log.info("basedata is"+object);
	    
	     String[]setarr= setscontent.split(",");
	    
	     MaterialSourceSetBean bean=(MaterialSourceSetBean)JSONObject.toBean(object,MaterialSourceSetBean.class);
	     String retStr=materialSourceService.SaveMaterialSourceSub(bean,setarr);
	     if("".equals(retStr)){
	            retStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(retStr);
		 return "success"; 
	}
	
	public String DelMaterialSource() throws Exception {
		 JSONObject object = JSONObject.fromObject(data);
	     log.info(object);
	     MaterialSourceBean materialSourceBean=(MaterialSourceBean)JSONObject.toBean(object,MaterialSourceBean.class);
	     String retStr=materialSourceService.DelMaterialSource(materialSourceBean);
	     result=DefaultResultUtil.getModificationResult(retStr);
		 return "success"; 
	}
	public String getMaterialSource() throws Exception{
		JSONObject object = JSONObject.fromObject(data);
	     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
	     MaterialSourceBean proBean=(MaterialSourceBean)JSONObject.toBean(object,MaterialSourceBean.class);
	     Map<Integer, Object> rsMap=  materialSourceService.GetMaterialSource(proBean, 0, start, length);
	       int recordsTotal = (Integer)rsMap.get(1);
	        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
		 return Action.SUCCESS;
		
	}
	public String getMaterialSourceSet() throws Exception{
		JSONObject object = JSONObject.fromObject(data);
	     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
	     MaterialSourceBean proBean=(MaterialSourceBean)JSONObject.toBean(object,MaterialSourceBean.class);
	     Map<Integer, Object> rsMap=  materialSourceService.GetMaterialSource(proBean, 1, 0, 0);
	       int recordsTotal = (Integer)rsMap.get(1);
	        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
		 return Action.SUCCESS;
		
	}
	public String getMaterialSourceSub() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 MaterialSourceSubBean proBean=(MaterialSourceSubBean)JSONObject.toBean(object,MaterialSourceSubBean.class);
		 List<MaterialSourceSubBean> list = materialSourceService.GetMaterialSourceSub(proBean);
		 result =DefaultResultUtil.getDefaultResult(list);
		 return Action.SUCCESS;
	}

}
