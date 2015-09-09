package cn.gov.scciq.businessProgress.passjudge;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

import cn.gov.scciq.util.DefaultResultUtil;

public class PassJundgeAction {
	    PassJundgeService passJundgeService=new PassJundgeService();
		private static Log log=LogFactory.getLog(PassJundgeAction.class);
		private String data;
		private JSONObject result;
		
	    
	    
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
public String   GetLabApplyByDeclNo() throws Exception{
	 JSONObject object = JSONObject.fromObject(data);
	 log.info("提交的参数："+data);
	 PassJudgeInputBean bean=(PassJudgeInputBean)JSONObject.toBean(object,PassJudgeInputBean.class);
     List<LabApplyByDeclNoBean> list=passJundgeService.GetLabApplyByDeclNo(bean);
	 result=DefaultResultUtil.getDefaultResult(list);
	 log.info("methodSub is "+result);
	 return Action.SUCCESS;
	
}
public String   GetInspOperatorByOrgDept() throws Exception{
	 JSONObject object = JSONObject.fromObject(data);
	 log.info("提交的参数："+data);
	 PassJudgeInputBean bean=(PassJudgeInputBean)JSONObject.toBean(object,PassJudgeInputBean.class);
    List<InspOperatorByOrgDeptBean> list=passJundgeService.GetInspOperatorByOrgDept(bean);
	 result=DefaultResultUtil.getDefaultResult(list);
	 log.info("methodSub is "+result);
	 return Action.SUCCESS;
	
}
public String   GetLabSampleItemByProduct() throws Exception{
	 JSONObject object = JSONObject.fromObject(data);
	 log.info("提交参数："+data);
	 PassJudgeInputBean bean=(PassJudgeInputBean)JSONObject.toBean(object,PassJudgeInputBean.class);
     List<LabSampleItemByProductBean> list=passJundgeService.GetLabSampleItemByProduct(bean);
	 result=DefaultResultUtil.getDefaultResult(list);
	 log.info("methodSub is "+result);
	 return Action.SUCCESS;
	
}
public String   SaveDeclProductQualify() throws Exception{
	  JSONObject object = JSONObject.fromObject(data);
	  log.info(object);
	  PassJudgeInputBean bean=(PassJudgeInputBean)JSONObject.toBean(object,PassJudgeInputBean.class);
	  String reStr=passJundgeService.SaveDeclProductQualify(bean);
	  result=DefaultResultUtil.getModificationResult(reStr);
	  log.info(result);
	  return "success"; 
	
}
public String   SaveLabSampleItemQualify() throws Exception{
	  JSONObject object = JSONObject.fromObject(data);
	  log.info("save "+object);   
	  PassJudgeInputBean bean=(PassJudgeInputBean)JSONObject.toBean(object,PassJudgeInputBean.class);
	  String reStr=passJundgeService.SaveLabSampleItemQualify(bean);
	  result=DefaultResultUtil.getModificationResult(reStr);
	  log.info(result);
	  return "success"; 
	
}
public String   AllSaveLabSampleItemQualify() throws Exception{
	  JSONObject object = JSONObject.fromObject(data);
	 String str=object.getString("str");
	// System.out.println("str"+str);
	 String[] data=str.split("@");
	 //System.out.println("leng "+data.length);
	 String reStr=null;
	 for (int i = 0; i < data.length; i++) {
		// System.out.println("i "+i+" "+data[i]);
		  //PassJudgeInputBean bean=(PassJudgeInputBean)JSONObject.toBean(object,PassJudgeInputBean.class);
		   reStr=passJundgeService.AllSaveLabSampleItemQualify(data[i]);
	}
	// System.out.println("restr "+reStr);
	 
	  result=DefaultResultUtil.getModificationResult(reStr);
	  log.info(result);
	  return "success"; 
	
}
public String   CheckQualifySaveBefore() throws Exception{
	  JSONObject object = JSONObject.fromObject(data);
	  log.info(object);
	  PassJudgeInputBean bean=(PassJudgeInputBean)JSONObject.toBean(object,PassJudgeInputBean.class);
	  String reStr=passJundgeService.CheckQualifySaveBefore(bean);
	  if("".equals(reStr)){
		  reStr="true";
	  }
	  result=DefaultResultUtil.getModificationResult(reStr);
	  log.info(result);
	  return "success"; 
	
}
public String   TransDeclForMultiDept() throws Exception{
	  JSONObject object = JSONObject.fromObject(data);
	  log.info(object);
	  PassJudgeInputBean bean=(PassJudgeInputBean)JSONObject.toBean(object,PassJudgeInputBean.class);
	  String reStr=passJundgeService.TransDeclForMultiDept(bean);
	  if("".equals(reStr)){
		  reStr="true";
	  }
	  result=DefaultResultUtil.getModificationResult(reStr);
	  log.info(result);
	  return "success"; 
	
}
public String   CheckQualifyJudgeBefore() throws Exception{
	  JSONObject object = JSONObject.fromObject(data);
	  log.info(object);
	  PassJudgeInputBean bean=(PassJudgeInputBean)JSONObject.toBean(object,PassJudgeInputBean.class);
	  String reStr=passJundgeService.CheckQualifyJudgeBefore(bean);
	/* String[] s= reStr.split("@");
	  if("".equals(reStr)){
		  reStr="true";
	  }*/
	  result=DefaultResultUtil.getModificationResult(reStr);
	  log.info(result);
	  return "success"; 
	
}
public String   CheckInspDeptForDeclProduct() throws Exception{
	  JSONObject object = JSONObject.fromObject(data);
	  log.info(object);
	  PassJudgeInputBean bean=(PassJudgeInputBean)JSONObject.toBean(object,PassJudgeInputBean.class);
	  String reStr=passJundgeService.CheckInspDeptForDeclProduct(bean);
	  if("".equals(reStr)){
		  reStr="true";
	  }
	  result=DefaultResultUtil.getModificationResult(reStr);
	  log.info(result);
	  return "success"; 
	
}

public String   CheckifUnqualify() throws Exception{
	  JSONObject object = JSONObject.fromObject(data);
	  log.info(object);
	  PassJudgeInputBean bean=(PassJudgeInputBean)JSONObject.toBean(object,PassJudgeInputBean.class);
	  String reStr=passJundgeService.CheckifUnqualify(bean);
	  if("".equals(reStr)){
		  reStr="true";
	  }
	  result=DefaultResultUtil.getModificationResult(reStr);
	  log.info(result);
	  return "success"; 
	
}

public String   QualifyJudge() throws Exception{
	 JSONObject object = JSONObject.fromObject(data);
	 log.info("提交参数："+data);
	 PassJudgeInputBean bean=(PassJudgeInputBean)JSONObject.toBean(object,PassJudgeInputBean.class);
     Map<String, Object> rsMap=  passJundgeService.QualifyJudge(bean);
     result = DefaultResultUtil.getDefaultResult(rsMap);
     log.info("entpr detail byID"+result);
    
	 return Action.SUCCESS;
	
}
public String   CancelCurrentProcess() throws Exception{
	  JSONObject object = JSONObject.fromObject(data);
	  log.info(object);
	  PassJudgeInputBean bean=(PassJudgeInputBean)JSONObject.toBean(object,PassJudgeInputBean.class);
	  String reStr=passJundgeService.CancelCurrentProcess(bean);
	  result=DefaultResultUtil.getModificationResult(reStr);
	  log.info(result);
	 
	
	return Action.SUCCESS;
}
public String   CheckIfPrintCert() throws Exception{
	 JSONObject object = JSONObject.fromObject(data);
	  log.info(object);
	  PassJudgeInputBean bean=(PassJudgeInputBean)JSONObject.toBean(object,PassJudgeInputBean.class);
	  String reStr=passJundgeService.CheckIfPrintCert(bean);
	  result=DefaultResultUtil.getModificationResult(reStr);
	  log.info(result);
	  return Action.SUCCESS;
}
}
