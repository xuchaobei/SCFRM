package cn.gov.scciq.entmanage.entProductCheck;

import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.util.DefaultResultUtil;

import com.opensymphony.xwork2.Action;

public class EntProductCheckAction {
	private static Log log=LogFactory.getLog(EntProductCheckAction.class);
	EntProductCheckService entProductCheckService = new EntProductCheckService();
	private String data;
	private JSONObject result;
	private int draw;
    private int start;
    private int length;
    private String  inspOrgCode;
//取得企业端输入的企业产品数据
	public String  getEntProductInput() throws Exception{
		/*String bookName = new String(data.getBytes("iso8859-1"),"utf-8");
		data = java.net.URLDecoder.decode(bookName, "UTF-8");
		System.out.println("解码后"+data+" draw "+draw+" start "+start+" length "+length);
		 */
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		 EntProductInputBean proBean=(EntProductInputBean)JSONObject.toBean(object,EntProductInputBean.class);
	     Map<Integer, Object> rsMap=  entProductCheckService.getEntProductInput(proBean, inspOrgCode, start, length);
	     int recordsTotal = (Integer)rsMap.get(1);
	     JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	     result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
		 return Action.SUCCESS;
		
		
	}
	//取得企业端输入的企业产品数据
		public String  getEntProductInputConPlan() throws Exception{
			/*String bookName = new String(data.getBytes("iso8859-1"),"utf-8");
			data = java.net.URLDecoder.decode(bookName, "UTF-8");
			System.out.println("解码后"+data+" draw "+draw+" start "+start+" length "+length);
			 */
			JSONObject object = JSONObject.fromObject(data);
			 log.info("提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
			 EntProductInputBean proBean=(EntProductInputBean)JSONObject.toBean(object,EntProductInputBean.class);
		     Map<Integer, Object> rsMap=  entProductCheckService.getEntProductInputConPlan(proBean);
		     int recordsTotal = (Integer)rsMap.get(1);
		     JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		     result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
			 return Action.SUCCESS;
			
			
		}
//将系统所定义的状态数据放到状态下拉式编辑框中。
	public String   getEntProductStatus() throws Exception{
		 List<EntProductStatusBean> list=entProductCheckService.getEntProductStatus();
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("methodSub is "+result);
		 return Action.SUCCESS;
		
	}
//根据企业产品的企业代码和企业产品自编号取得详细数据，包括产品特征数据、原料数据、辅料数据、添加剂数据。
	public String   getEntProductDetailByID() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data);
		 EntProductBean proBean=(EntProductBean)JSONObject.toBean(object,EntProductBean.class);
	     Map<String, Object> rsMap=  entProductCheckService.getEntProductDetailByID(proBean);
	     result = DefaultResultUtil.getDefaultResult(rsMap);
	     log.info("entpr detail byID"+result);
		 return Action.SUCCESS;
		
	}
//驳回企业产品
	public String   rejectEntProduct() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 EntProductInputBean bean=(EntProductInputBean)JSONObject.toBean(object,EntProductInputBean.class);
		 String reStr=entProductCheckService.rejectEntProduct(bean);
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success";
		
	}
//根据产品特征数据，得到前15位编码相同的产品列表
	public String   getProductListByProductNo15() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
	     log.info("obj is "+object);
	     EntProductInputBean bean=(EntProductInputBean)JSONObject.toBean(object,EntProductInputBean.class);
	     Map<String, Object> rsMap=entProductCheckService.getProductListByProductNo15(bean);
		 result=DefaultResultUtil.getDefaultResult(rsMap);
		 log.info("methodSub is "+result);
		 return Action.SUCCESS;
		
	}
//保存产品数据
	public String   saveProduct() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 EntProductInputBean bean=(EntProductInputBean)JSONObject.toBean(object,EntProductInputBean.class);
		 String reStr=entProductCheckService.saveProduct(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
		
		
	}
//检查产品编号对应的原料是否与被审核的产品的原料是否相一致
	public String   checkMaterialForProduct() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 EntProductInputBean bean=(EntProductInputBean)JSONObject.toBean(object,EntProductInputBean.class);
		 String reStr=entProductCheckService.checkMaterialForProduct(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success";
		
	}
//更新企业产品数据
	public String   updateEntProduct() throws Exception{
		JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 EntProductInputBean bean=(EntProductInputBean)JSONObject.toBean(object,EntProductInputBean.class);
		 String reStr=entProductCheckService.updateEntProduct(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success";
		
	}
//比对被审产品和已有产品的产品数据
	public String   getProductInfoCompared() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data);
		 EntProductInputBean bean=(EntProductInputBean)JSONObject.toBean(object,EntProductInputBean.class);
	     Map<String, Object> rsMap=  entProductCheckService.getProductInfoCompared(bean);
	     result = DefaultResultUtil.getDefaultResult(rsMap);
	     log.info("entpr info compaed "+result);
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
	public String getInspOrgCode() {
		return inspOrgCode;
	}
	public void setInspOrgCode(String inspOrgCode) {
		this.inspOrgCode = inspOrgCode;
	}


}
