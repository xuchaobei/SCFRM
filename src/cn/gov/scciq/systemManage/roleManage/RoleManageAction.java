package cn.gov.scciq.systemManage.roleManage;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.json.JSONObject;
import cn.gov.scciq.entmanage.entProductCheck.EntProductCheckAction;
import cn.gov.scciq.util.DefaultResultUtil;

import com.opensymphony.xwork2.Action;

public class RoleManageAction {
	
	private static Log log=LogFactory.getLog(EntProductCheckAction.class);
	RoleManageService roleManageService=new RoleManageService();
	private String data;
	private JSONObject result;
	private int draw;
    private int start;
    private int length;
    
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
	public String GetMenuDefine() throws Exception{
		 //JSONObject object = JSONObject.fromObject(data);
		 //log.info("提交参数："+data);
		// RoleManageInputBean proBean=(RoleManageInputBean)JSONObject.toBean(object,RoleManageInputBean.class);
		Map<String, Object> list=roleManageService.GetMenuDefine();
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("methodSub is "+result);
		 return Action.SUCCESS;
		
	}
	public String GetSubMenuDefine() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data);
		 RoleManageInputBean proBean=(RoleManageInputBean)JSONObject.toBean(object,RoleManageInputBean.class);
	     List<SubMenuDefineBean> list=roleManageService.GetSubMenuDefine(proBean);
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("methodSub is "+result);
		 return Action.SUCCESS;
		
	}
	public String SaveRole() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		String str= object.getString("str");
		log.info("str "+str);
		 RoleManageInputBean bean=(RoleManageInputBean)JSONObject.toBean(object,RoleManageInputBean.class);
		 String reStr=roleManageService.SaveRole(bean,str);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
		
		
	}
	public String SaveRoleSubmenu() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 RoleManageInputBean bean=(RoleManageInputBean)JSONObject.toBean(object,RoleManageInputBean.class);
		 String reStr=roleManageService.SaveRoleSubmenu(bean);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
		
		
	}
	public String GetRoleMenuLimit() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
	
		 RoleManageInputBean proBean=(RoleManageInputBean)JSONObject.toBean(object,RoleManageInputBean.class);
		  Map<String, Object> list=roleManageService.GetRoleMenuLimit(proBean);
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("menulitmit is "+result);
		 return Action.SUCCESS;
		
	}
	public String GetRoleSubmenuLimit() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交参数："+data);
		 RoleManageInputBean proBean=(RoleManageInputBean)JSONObject.toBean(object,RoleManageInputBean.class);
	     List<RoleSubmenuLimitBean> list=roleManageService.GetRoleSubmenuLimit(proBean);
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("methodSub is "+result);
		 return Action.SUCCESS;
		
	}
	public String DelRole() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
		 RoleManageInputBean bean=(RoleManageInputBean)JSONObject.toBean(object,RoleManageInputBean.class);
		 String reStr=roleManageService.DelRole(bean);
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
		
		
	}
	

}
