package cn.gov.scciq.systemManage.thridTestLab;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

import cn.gov.scciq.entmanage.entProductCheck.EntProductCheckAction;

import cn.gov.scciq.util.DefaultResultUtil;
public class ThridTestLabAction {
	
	private static Log log=LogFactory.getLog(EntProductCheckAction.class);
	ThridTestLabService thridTestLabService=new ThridTestLabService();
	private String data;
	private JSONObject result;
	private int draw;
    private int start;
    private int length;
	public String GetOtherTestLab() throws Exception{
		
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		 ThridTestLabInputBean proBean=(ThridTestLabInputBean)JSONObject.toBean(object,ThridTestLabInputBean.class);
	     Map<Integer, Object> rsMap=  thridTestLabService.GetOtherTestLab(proBean, start, length);
	     int recordsTotal = (Integer)rsMap.get(1);
	     JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	     result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
		 return Action.SUCCESS;
		
		
	}
	public String GetOtherTestLabItem() throws Exception{
		
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		 ThridTestLabInputBean proBean=(ThridTestLabInputBean)JSONObject.toBean(object,ThridTestLabInputBean.class);
	     Map<Integer, Object> rsMap=  thridTestLabService.GetOtherTestLabItem(proBean, start, length);
	     int recordsTotal = (Integer)rsMap.get(1);
	     JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	     result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
		 return Action.SUCCESS;
		
		
	}
	public String SaveOtherTestLab() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 ThridTestLabInputBean bean=(ThridTestLabInputBean)JSONObject.toBean(object,ThridTestLabInputBean.class);
		 String reStr=thridTestLabService.SaveOtherTestLab(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
		
		
	}
	public String SaveOtherTestLabItem() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 ThridTestLabInputBean bean=(ThridTestLabInputBean)JSONObject.toBean(object,ThridTestLabInputBean.class);
		 String reStr=thridTestLabService.SaveOtherTestLabItem(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
		
		
	}
	public String DelOtherTestLab() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 ThridTestLabInputBean bean=(ThridTestLabInputBean)JSONObject.toBean(object,ThridTestLabInputBean.class);
		 String reStr=thridTestLabService.DelOtherTestLab(bean);
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
		
		
	}
	public String DelOtherTestLabItem() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 ThridTestLabInputBean bean=(ThridTestLabInputBean)JSONObject.toBean(object,ThridTestLabInputBean.class);
		 String reStr=thridTestLabService.DelOtherTestLabItem(bean);
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
		
		
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
	

}
