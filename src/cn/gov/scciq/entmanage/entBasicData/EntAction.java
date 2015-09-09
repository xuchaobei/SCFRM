package cn.gov.scciq.entmanage.entBasicData;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opensymphony.xwork2.Action;

import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.DefaultResultUtil;
import cn.gov.scciq.entmanage.entBasicData.MaterialSubsubclassByQueryBean;
import cn.gov.scciq.entmanage.entBasicData.EntBasicDataBean;
public class EntAction {
	EntBasicDataService entBasicDataService = new EntBasicDataService();
	private static Log log=LogFactory.getLog(EntAction.class);
	private String data;
	private JSONObject result;
	private int draw;
    private int start;
    private int length;
    private String roleCode;
    private String  inspOrgCode;
    

	public String saveEnt () throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object+roleCode);
	     EntBasicDataBean bean=(EntBasicDataBean)JSONObject.toBean(object,EntBasicDataBean.class);
		 String reStr=entBasicDataService.saveEnt(bean, roleCode);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
	}
	public String getEnt () throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info("提交getent参数："+data+"draw is "+draw+"start is"+start+"length is "+length+inspOrgCode+roleCode);
	     EntBasicDataBean proBean=(EntBasicDataBean)JSONObject.toBean(object,EntBasicDataBean.class);
	     Map<Integer, Object> rsMap=  entBasicDataService.getEnt(proBean, inspOrgCode, roleCode, start, length);
	     int recordsTotal = (Integer)rsMap.get(1);
	     JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	     result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
	     log.info("get ent is "+result);
		 return Action.SUCCESS;
			
		}
	public String getInspOrg () throws Exception{
		 List<InspOrgBean> list=entBasicDataService.getInspOrg();
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("insporg is "+result);
		 return Action.SUCCESS;
		
	}
	public String delEnt () throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
	     EntBasicDataBean bean=(EntBasicDataBean)JSONObject.toBean(object,EntBasicDataBean.class);
		 String reStr=entBasicDataService.delEnt(bean, inspOrgCode, roleCode);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
		
		
	}
	public String resetEntPasswords () throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
	     EntBasicDataBean bean=(EntBasicDataBean)JSONObject.toBean(object,EntBasicDataBean.class);
		 String reStr=entBasicDataService.ResetEntPasswords(bean, inspOrgCode, roleCode);
		 if("".equals(reStr)){
	            reStr = "true";
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
		
	}
	public String getOperateLimit () throws Exception{
		System.out.println(data);
		
		 JSONObject object = JSONObject.fromObject(data);
		 log.info(object);
	   
	    String menuName=object.getString("menuName");
	    log.info(menuName);
		 String reStr=entBasicDataService.getOperateLimit(menuName);
		 if("1".equals(reStr)){
	            reStr = "true";
	        }else{
	        	reStr = ConstantStr.PERMISSION_DENIAL_MSG;
	        }
		 result=DefaultResultUtil.getModificationResult(reStr);
		 return "success"; 
		
	}
	public String getEvlLevel() throws Exception{
		/*String bookName = new String(data.getBytes("iso8859-1"),"utf-8");
		data = java.net.URLDecoder.decode(bookName, "UTF-8");
		System.out.println("解码fd后"+data+" draw "+draw+" start "+start+" length "+length);
		 */
		JSONObject object = JSONObject.fromObject(data);
	     log.info("objs is "+object);
	     EvlLevelBean bean=(EvlLevelBean)JSONObject.toBean(object,EvlLevelBean.class);
		 List<EvlLevelBean> list=entBasicDataService.getEvlLevel(bean);
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("evlevel is "+result);
		 return Action.SUCCESS;
		
		
	}
////原料细类查询获取原料大类小类细类
				public String   getmatriclall() throws Exception{
					 JSONObject object = JSONObject.fromObject(data);
					 log.info("提交参数："+data);
					 EntProductMaterialComparedBean proBean=(EntProductMaterialComparedBean)JSONObject.toBean(object,EntProductMaterialComparedBean.class);
					 List<MaterialSubsubclassByQueryBean> list=entBasicDataService.getmatriclall(proBean);
				     result=DefaultResultUtil.getDefaultResult(list);
				     log.info("xilei"+result);
					 return Action.SUCCESS;   
					
				}
	public String getEntDetail() throws Exception{
		 JSONObject object = JSONObject.fromObject(data);
	     log.info("obj is "+object);
	     EntDetailBean bean=(EntDetailBean)JSONObject.toBean(object,EntDetailBean.class);
		 List<EntDetailBean> list=entBasicDataService.getEntDetail(bean);
		 result=DefaultResultUtil.getDefaultResult(list);
		 log.info("entdetail is "+result);
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
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getInspOrgCode() {
		return inspOrgCode;
	}
	public void setInspOrgCode(String inspOrgCode) {
		this.inspOrgCode = inspOrgCode;
	}
}
