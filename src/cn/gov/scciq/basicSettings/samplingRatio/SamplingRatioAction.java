package cn.gov.scciq.basicSettings.samplingRatio;


import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.json.JSONObject;
import cn.gov.scciq.util.DefaultResultUtil;

import com.opensymphony.xwork2.Action;

public class SamplingRatioAction {
	private static Log log=LogFactory.getLog(SamplingRatioAction.class);
	private String data;
	private JSONObject result;
	SamplingRatioService samplingRatioService=new SamplingRatioService();
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
	public String GetSamplingRatioDefine() throws Exception{
		
		 Map<String, Object> rsMap=  samplingRatioService.GetSamplingRatioDefine();
	     result = DefaultResultUtil.getDefaultResult( rsMap.get("one"));
	     log.info("prosmpl"+result);
		 return Action.SUCCESS;
		
		
	}
	public String GetSamplingRatioDefineItem() throws Exception{
		
		 Map<String, Object> rsMap=  samplingRatioService.GetSamplingRatioDefine();
	     result = DefaultResultUtil.getDefaultResult( rsMap.get("two"));
	     log.info("itemsmpl"+result);
		 return Action.SUCCESS;
		
		
	}
	public String SaveProductSamplingRatioDefine() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 SamplingRatioInputBean bean=(SamplingRatioInputBean)JSONObject.toBean(object,SamplingRatioInputBean.class);
		 String reStr=samplingRatioService.SaveProductSamplingRatioDefine(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
		
		
	}
	public String SaveItemSamplingRatioDefine() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 SamplingRatioInputBean bean=(SamplingRatioInputBean)JSONObject.toBean(object,SamplingRatioInputBean.class);
		 String reStr=samplingRatioService.SaveItemSamplingRatioDefine(bean);
		 if("".equals(reStr)){
	            reStr = "true";   
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
		
		
	}

}
