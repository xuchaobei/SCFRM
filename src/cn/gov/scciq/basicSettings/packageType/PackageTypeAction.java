package cn.gov.scciq.basicSettings.packageType;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

import cn.gov.scciq.basicSettings.materialSource.MaterialSourceAction;
import cn.gov.scciq.basicSettings.materialSource.MaterialSourceBean;
import cn.gov.scciq.basicSettings.materialSource.MaterialSourceSubBean;
import cn.gov.scciq.util.DefaultResultUtil;

public class PackageTypeAction  {
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
	
	private static Log log=LogFactory.getLog(PackageTypeAction.class);
	PackageTypeService packageTypeService = new PackageTypeService();

	
	public String SavePackageType() throws Exception {
		 JSONObject object = JSONObject.fromObject(data);
	     log.info(object);
	     PackageTypeBean packageTypeBean=(PackageTypeBean)JSONObject.toBean(object,PackageTypeBean.class);
	     String retStr=packageTypeService.SavePackageType(packageTypeBean);
	     if("".equals(retStr)){
	            retStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(retStr);
		 return "success"; 
	  
	}
	
	public String SavePackageTypeSub() throws Exception {
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("basedata is"+object);
		    
	     String[]setarr= setscontent.split(",");
	     log.info(object);
	     PackageTypeSetBean bean=(PackageTypeSetBean)JSONObject.toBean(object,PackageTypeSetBean.class);
	     String retStr=packageTypeService.SavePackageTypeSub(bean,setarr);
	     if("".equals(retStr)){
	            retStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(retStr);
		 return "success"; 
	}

	public String DelPackageType() throws Exception {
		 JSONObject object = JSONObject.fromObject(data);
	     log.info(object);
	     PackageTypeBean packageTypeBean=(PackageTypeBean)JSONObject.toBean(object,PackageTypeBean.class);
	     String retStr=packageTypeService.DelPackageType(packageTypeBean);
	     result=DefaultResultUtil.getModificationResult(retStr);
		 return "success"; 
	}
	public String getPackageType() throws Exception {
		JSONObject object = JSONObject.fromObject(data);
	     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
	     PackageTypeBean proBean=(PackageTypeBean)JSONObject.toBean(object,PackageTypeBean.class);
	     Map<Integer, Object> rsMap=  packageTypeService.GetPackageType(proBean, 0, start, length);
	       int recordsTotal = (Integer)rsMap.get(1);
	        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
		 return Action.SUCCESS;
	}
	public String getPackageTypeSet() throws Exception {
		JSONObject object = JSONObject.fromObject(data);
	     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
	     PackageTypeBean proBean=(PackageTypeBean)JSONObject.toBean(object,PackageTypeBean.class);
	     Map<Integer, Object> rsMap=  packageTypeService.GetPackageType(proBean, 1, 0, 0);
	       int recordsTotal = (Integer)rsMap.get(1);
	        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
		 return Action.SUCCESS;
	}
    public String getPackageTypeSub() throws Exception {
    	 JSONObject object = JSONObject.fromObject(data);
    	 PackageTypeSubBean proBean=(PackageTypeSubBean)JSONObject.toBean(object,PackageTypeSubBean.class);
		 List<PackageTypeSubBean> list = packageTypeService.GetPackageTypeSub(proBean);
		 result =DefaultResultUtil.getDefaultResult(list);
		 return Action.SUCCESS;
	}

}
