package cn.gov.scciq.systemManage.inspOperator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;         
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.convCtrl.ConvCtrlItemLimitDto;
import cn.gov.scciq.bussiness.convCtrl.ConventionCtrlDao;
import cn.gov.scciq.util.DefaultResultUtil;
import com.opensymphony.xwork2.Action;

public class InspOperatorAction {
	
	private static Log log=LogFactory.getLog(InspOperatorAction.class);
	InspOperatorService inspOperatorService=new InspOperatorService();
	private String data;
	private JSONObject result;
	private int draw;
    private int start;
    private int length;
	
	public String GetInspOperatorByQuery() throws Exception {
		
		 JSONObject object = JSONObject.fromObject(data);
		 InspOperatorInputBean proBean=(InspOperatorInputBean)JSONObject.toBean(object,InspOperatorInputBean.class);
	     Map<Integer, Object> rsMap=  inspOperatorService.GetInspOperatorByQuery(proBean,start, length);
	     int recordsTotal = (Integer)rsMap.get(1);
	     JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	     result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
		 return Action.SUCCESS;
		
		
	}
	public String SaveInspOperator() throws Exception {
		 JSONObject object = JSONObject.fromObject(data);
		 String orgCode=object.getString("orgCode");
		 String deptCode=object.getString("deptCode");
		 String operatorCode=object.getString("operatorCode");
		 InspOperatorInputBean bean=(InspOperatorInputBean)JSONObject.toBean(object,InspOperatorInputBean.class);
		 String reStr="";
		 JSONArray itemLimitList = bean.getItemLimitList();
		
		 for (int i = 0; i < itemLimitList.size(); i++) {
			 JSONObject jo = itemLimitList.getJSONObject(i);
             InspOperatorInputBean listbean = (InspOperatorInputBean)JSONObject.toBean(jo, InspOperatorInputBean.class);
             reStr=inspOperatorService.CheckSaveInspOperatorRole(listbean);
             if(!"true".equals(reStr)){   
            	 break;
             }
		}
		 System.out.println("check str "+reStr);
		 if("true".equals(reStr)){
			 reStr=inspOperatorService.SaveInspOperator(bean);
			 System.out.println("save insp "+reStr);
			 if("".equals(reStr)){
				 reStr= inspOperatorService.DelInspOperatorRole(bean);
		            if(reStr.equals("true")){
		            	
		                for(int i = 0; i < itemLimitList.size(); i++){
		                    JSONObject jo = itemLimitList.getJSONObject(i);
		                    InspOperatorInputBean listbean = (InspOperatorInputBean)JSONObject.toBean(jo, InspOperatorInputBean.class);
		                  reStr=inspOperatorService.SaveInspOperatorRole(listbean,orgCode,deptCode,operatorCode);
		                }
		            }
			 }
	            
	            
	        }
		 
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
		
		
	}
	public String SetInspOperatorPassword() throws Exception {
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 InspOperatorInputBean bean=(InspOperatorInputBean)JSONObject.toBean(object,InspOperatorInputBean.class);
		 String reStr=inspOperatorService.SetInspOperatorPassword(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
		
		
	}
	public String GetInspOrgPaging() throws Exception {
		 List<InspOrgPagingBean> list=inspOperatorService.GetInspOrgPaging();
		 result=DefaultResultUtil.getDefaultResult(list);
		
		 return Action.SUCCESS;
		
	}
	
	public String GetInspDeptPaging() throws Exception {
		 JSONObject object = JSONObject.fromObject(data);
		
		 InspOperatorInputBean bean=(InspOperatorInputBean)JSONObject.toBean(object,InspOperatorInputBean.class);
		 List<InspDeptPagingBean> list=inspOperatorService.GetInspDeptPaging(bean);
		 result=DefaultResultUtil.getDefaultResult(list);
		
		 return Action.SUCCESS;
		
	}
	public String GetRoleDefine() throws Exception {
		 List<RoleDefineBean> list=inspOperatorService.GetRoleDefine();
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("role is "+result);        
		 return Action.SUCCESS;
		
	}
	public String GetInspOperatorByID() throws Exception {
		 JSONObject object = JSONObject.fromObject(data);
		 InspOperatorInputBean bean=(InspOperatorInputBean)JSONObject.toBean(object,InspOperatorInputBean.class);
		 List<InspOperatorByQueryBean> list=inspOperatorService.GetInspOperatorByID(bean);
		 List<InspOperatorRoleBean>list2= inspOperatorService.GetInspOperatorRole(bean);
		 Map<String, Object> map= new HashMap<String, Object>();
		 map.put("one", list);
		 map.put("two", list2);
		 result=DefaultResultUtil.getDefaultResult(map);
		
		 return Action.SUCCESS;
		
	}
	public String DelInspOperator() throws Exception {
		 JSONObject object = JSONObject.fromObject(data);
		 InspOperatorInputBean bean=(InspOperatorInputBean)JSONObject.toBean(object,InspOperatorInputBean.class);
		 String reStr=inspOperatorService.DelInspOperator(bean);
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
