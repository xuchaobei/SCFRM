package cn.gov.scciq.businessProgress.unusualdesc;

import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.util.DefaultResultUtil;
import com.opensymphony.xwork2.Action;

public class UnUsualDeclAction {
	UnUsualDeclService service=new UnUsualDeclService();
	private static Log log=LogFactory.getLog(UnUsualDeclAction.class);
	private String data;
	private JSONObject result;
	private int draw;
    private int start;
    private int length;
    
    
	public String  GetAbnormalDecl() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		 UnUsualDeclInputBean bean=(UnUsualDeclInputBean)JSONObject.toBean(object,UnUsualDeclInputBean.class);
	     Map<Integer, Object> rsMap=  service.GetAbnormalDecl(bean, start, length);
	     int recordsTotal = (Integer)rsMap.get(1);
	     JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	     result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
	     log.info(result);
	     return Action.SUCCESS;
		}
	public String  ReturnDecl() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 UnUsualDeclInputBean bean=(UnUsualDeclInputBean)JSONObject.toBean(object,UnUsualDeclInputBean.class);
		 String reStr=service.ReturnDecl(bean);
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String  CheckAbnormalDecl() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 UnUsualDeclInputBean bean=(UnUsualDeclInputBean)JSONObject.toBean(object,UnUsualDeclInputBean.class);
		 String reStr=service.CheckAbnormalDecl(bean);
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String  ReReadDeclInfo() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 UnUsualDeclInputBean bean=(UnUsualDeclInputBean)JSONObject.toBean(object,UnUsualDeclInputBean.class);
		 String reStr=service.ReReadDeclInfo(bean);
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String  GetDeclInfoByDeclNo() throws Exception{

		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data);
		 UnUsualDeclInputBean bean=(UnUsualDeclInputBean)JSONObject.toBean(object,UnUsualDeclInputBean.class);
	     List<DeclInfoBean> list=service.GetDeclInfoByDeclNo(bean);
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("methodSub is "+result);
		 return Action.SUCCESS;
		
	
		
		
	}
	public String  SaveDeclInfoInput() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 UnUsualDeclInputBean bean=(UnUsualDeclInputBean)JSONObject.toBean(object,UnUsualDeclInputBean.class);
		 String reStr=service.SaveDeclInfoInput(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String  RecalculateDeclProductSampling() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 UnUsualDeclInputBean bean=(UnUsualDeclInputBean)JSONObject.toBean(object,UnUsualDeclInputBean.class);
		 String reStr=service.RecalculateDeclProductSampling(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String  CheckDeclProductPerfect() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 UnUsualDeclInputBean bean=(UnUsualDeclInputBean)JSONObject.toBean(object,UnUsualDeclInputBean.class);
		 String reStr=service.CheckDeclProductPerfect(bean);
		 /*if("".equals(reStr)){
	            reStr = "true";
	        }*/
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
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
