package cn.gov.scciq.businessProgress.checkorReceive;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.util.DefaultResultUtil;

import com.opensymphony.xwork2.Action;

public class ProcessReceiverAction {

	ProcessReceiverService processReceiverService = new ProcessReceiverService();
	private static Log log=LogFactory.getLog(ProcessReceiverAction.class);
	private String data;
	private JSONObject result;
	private int draw;
    private int start;
    private int length;
	public String     GetDeclInfoForProcess () throws Exception{
		
		 /*String bookName = new String(data.getBytes("iso8859-1"),"utf-8");
		 data = java.net.URLDecoder.decode(bookName, "UTF-8");
	 	System.out.println("解码后"+data+" draw "+draw+" start "+start+" length "+length);
		 */
		JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		 ProcessOperateLogBean bean=(ProcessOperateLogBean)JSONObject.toBean(object,ProcessOperateLogBean.class);
	     Map<Integer, Object> rsMap=  processReceiverService.GetDeclInfoForProcess(bean, start, length);
	     int recordsTotal = (Integer)rsMap.get(1);
	     JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	     result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
	     log.info(result);
	     return Action.SUCCESS;
		
			
		}
		public String     GetDeclInfoForProcessHg () throws Exception{
			
			/* String bookName = new String(data.getBytes("iso8859-1"),"utf-8");
			 data = java.net.URLDecoder.decode(bookName, "UTF-8");
		 	System.out.println("解码后"+data+" draw "+draw+" start "+start+" length "+length);
			 */
		 	JSONObject object = JSONObject.fromObject(data);
			 log.info("提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
			 ProcessOperateLogBean bean=(ProcessOperateLogBean)JSONObject.toBean(object,ProcessOperateLogBean.class);
		     Map<Integer, Object> rsMap=  processReceiverService.GetDeclInfoForProcess(bean, start, length);
		     int recordsTotal = (Integer)rsMap.get(1);
		     JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		     result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
		     log.info(result);
		     return Action.SUCCESS;
			
				
			}
		
	public String    GetProcessStatusForDecl() throws Exception{
		  JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 ProcessOperateLogBean bean=(ProcessOperateLogBean)JSONObject.toBean(object,ProcessOperateLogBean.class);
		 String reStr=processReceiverService.GetProcessStatusForDecl(bean);
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success";
		
	}
	public String     GetDeclProduct() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data);
		 ProcessOperateLogBean proBean=(ProcessOperateLogBean)JSONObject.toBean(object,ProcessOperateLogBean.class);
	     List<DeclProductBean> list=processReceiverService.GetDeclProduct(proBean,start,length);
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("methodSub is "+result);
		 return Action.SUCCESS;
		
	}
	public String    GetBaseList() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data);
		 BaseListBean proBean=(BaseListBean)JSONObject.toBean(object,BaseListBean.class);
	     List<BaseListBean> list=processReceiverService.GetBaseList(proBean);
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("methodSub is "+result);
		 return Action.SUCCESS;
		
	}
	public String     GetDeclProductItem() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data);
		 DeclProductItemInputBean proBean=(DeclProductItemInputBean)JSONObject.toBean(object,DeclProductItemInputBean.class);
	     Map<Integer, Object> rsMap=  processReceiverService.GetDeclProductItem(proBean, start, length);
	     int recordsTotal = (Integer)rsMap.get(1);
	     JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	     result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
	     log.info("decl item"+result);
	     return Action.SUCCESS;
	}
	public String     TransDecl() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 TransDeclBean bean=(TransDeclBean)JSONObject.toBean(object,TransDeclBean.class);
		 String reStr=processReceiverService.TransDecl(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	
	public String     WithdrawDecl() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 ProcessOperateLogBean bean=(ProcessOperateLogBean)JSONObject.toBean(object,ProcessOperateLogBean.class);
		 String reStr=processReceiverService.WithdrawDecl(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String  getCEMSSeqNum() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 ProcessOperateLogBean bean=(ProcessOperateLogBean)JSONObject.toBean(object,ProcessOperateLogBean.class);
		 String reStr=processReceiverService.GetCEMSSeqNum(bean);
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String  UpdateControlReturnFlgNew() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 ReturnFlgBean bean=(ReturnFlgBean)JSONObject.toBean(object,ReturnFlgBean.class);
		 String reStr=processReceiverService.UpdateControlReturnFlgNew(bean);
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String    AcceptDecl() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data);
		 ProcessOperateLogBean proBean=(ProcessOperateLogBean)JSONObject.toBean(object,ProcessOperateLogBean.class);
	     Map<String, Object> rsMap=  processReceiverService.AcceptDecl(proBean);
	     result = DefaultResultUtil.getDefaultResult(rsMap);
	     log.info("entpr detail byID"+result);
	     
		 return Action.SUCCESS;
		
	}
	//在集中审单系统调用存储过程Pro_SaveReturnFlg集中审单系统的数据库是Oracle
	public String     SaveReturnFlg() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 ReturnFlgBean bean=(ReturnFlgBean)JSONObject.toBean(object,ReturnFlgBean.class);
		 String reStr=processReceiverService.SaveReturnFlg(bean);
		  result=DefaultResultUtil.getModificationResult(reStr);
		  log.info(result);
		 return "success"; 
		
	}
	public String     CancelCurrentProcess() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 ProcessOperateLogBean bean=(ProcessOperateLogBean)JSONObject.toBean(object,ProcessOperateLogBean.class);
		 String reStr=processReceiverService.CancelCurrentProcess(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String    DirectRelease() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 ProcessOperateLogBean bean=(ProcessOperateLogBean)JSONObject.toBean(object,ProcessOperateLogBean.class);
		 String reStr=processReceiverService.DirectRelease(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String     ChangeProductSampling() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 DeclProductBean bean=(DeclProductBean)JSONObject.toBean(object,DeclProductBean.class);
		 String reStr=processReceiverService.ChangeProductSampling(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String    GetEntProductByQuery() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		 EntProductByQueryInputBean bean=(EntProductByQueryInputBean)JSONObject.toBean(object,EntProductByQueryInputBean.class);
	     Map<Integer, Object> rsMap=  processReceiverService.GetEntProductByQuery(bean, start, length);
	     int recordsTotal = (Integer)rsMap.get(1);
	     JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	     result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
	     log.info(result);
	     return Action.SUCCESS;
		
	}
	public String    SaveDeclProductInput() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 DeclProductBean bean=(DeclProductBean)JSONObject.toBean(object,DeclProductBean.class);
		 String reStr=processReceiverService.SaveDeclProductInput(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String    DelDeclProduct() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 DeclProductBean bean=(DeclProductBean)JSONObject.toBean(object,DeclProductBean.class);
		 String reStr=processReceiverService.DelDeclProduct(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String    ChangeItemSampling() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 DeclProductItemBean bean=(DeclProductItemBean)JSONObject.toBean(object,DeclProductItemBean.class);
		 String reStr=processReceiverService.ChangeItemSampling(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String    SaveDeclProductItemInput() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 DeclProductItemBean bean=(DeclProductItemBean)JSONObject.toBean(object,DeclProductItemBean.class);
		 String reStr=processReceiverService.SaveDeclProductItemInput(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String SaveCreditRelease() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 CreditReleaseBean bean=(CreditReleaseBean)JSONObject.toBean(object,CreditReleaseBean.class);
		 System.out.println(bean);
		 String reStr=processReceiverService.SaveCreditRelease(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	public String CancelReleaseMode() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 CreditReleaseBean bean=(CreditReleaseBean)JSONObject.toBean(object,CreditReleaseBean.class);
		 System.out.println(bean);
		 String reStr=processReceiverService.CancelReleaseMode(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 log.info(result);
		 return "success"; 
		
		
	}
	
	public String    GetRiskClass() throws Exception{
		// JSONObject object = JSONObject.fromObject(data);
		// log.info("提交参数："+data);
		// RiskClassBean bean=(RiskClassBean)JSONObject.toBean(object,RiskClassBean.class);
	     List<RiskClassBean> list=processReceiverService.GetRiskClass();
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("methodSub is "+result);
		 return Action.SUCCESS;
		
	}
	public String    GetDeclInfoFromCIQ() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data);
		 ProcessOperateLogBean proBean=(ProcessOperateLogBean)JSONObject.toBean(object,ProcessOperateLogBean.class);
	     List<Good_DeclBean> list=processReceiverService.GetDeclInfoFromCIQ(proBean);
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("methodSub is "+result);
		 return Action.SUCCESS;
		
	}
	public String    GetDeclGoodsByDeclNo() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data);
		 DeclProductBean bean=(DeclProductBean)JSONObject.toBean(object,DeclProductBean.class);
	     List<DeclGoodsByDeclNoBean> list=processReceiverService.GetDeclGoodsByDeclNo(bean);
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("huowubaojian "+result);
		 return Action.SUCCESS;
		
	}
	public String  GetItem() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data);
		 ItemBean proBean=(ItemBean)JSONObject.toBean(object,ItemBean.class);
	     List<ItemBean> list=processReceiverService.GetItem(proBean);
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("methodSub is "+result);
		 return Action.SUCCESS;
		
	}
	public String    GetDeclGoodsInfoFromCIQ() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data);
		 DeclGoodsInfoFromCIQInpBean proBean=(DeclGoodsInfoFromCIQInpBean)JSONObject.toBean(object,DeclGoodsInfoFromCIQInpBean.class);
	     List<Decl_GoodsBean> list=processReceiverService.GetDeclGoodsInfoFromCIQ(proBean);
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("methodSub is "+result);
		 return Action.SUCCESS;
		
	}
	public String    GetItemSub() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data);
		 ItemSubBean proBean=(ItemSubBean)JSONObject.toBean(object,ItemSubBean.class);
	     List<ItemSubBean> list=processReceiverService.GetItemSub(proBean);
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("methodSub is "+result);
		 return Action.SUCCESS;
		
	}
	
	public String generateLabellingForPrn(){
		List<LabelDto> list = new ArrayList<LabelDto>();
		try {
			list = ProcessReceiverService.generateLabellingForPrn(data);
			result = DefaultResultUtil.getDefaultResult(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
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
