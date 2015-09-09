package cn.gov.scciq.basicSettings.productClass;


import java.util.Map;

import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.bussiness.select.SelectDataService;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.DefaultResultUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class ProductClassAction extends ActionSupport {
	   /**
	 * 
	 */
	private int draw;
	private int start;
	private int length;
	private String data;
	private JSONObject result;
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
	
	private static final long serialVersionUID = 1L;
	private static Log log=LogFactory.getLog(ProductClassAction.class);
	ProductClassServiceImpl iproductClassService = new ProductClassServiceImpl();
    

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
	 public String getProductClass() throws Exception{
		  
		     JSONObject object = JSONObject.fromObject(data);
		     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		     ProductClassBean proBean=(ProductClassBean)JSONObject.toBean(object,ProductClassBean.class);
		     Map<Integer, Object> rsMap=  iproductClassService.getProductClass(proBean, start, length);
		       int recordsTotal = (Integer)rsMap.get(1);
		        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
		   return Action.SUCCESS;
		}
	 
	  public String saveProClass() throws Exception{
		  
		    System.out.println("the start "+start);
	         JSONObject object = JSONObject.fromObject(data);
	   	     log.info("ob:"+object);
	   		 ProductClassBean proBean=(ProductClassBean)JSONObject.toBean(object,ProductClassBean.class);
	   		log.info(proBean);
	   		 String retStr=iproductClassService.saveProductClass(proBean);
	   		 if("".equals(retStr)){
	   	            retStr = "true";
	   	        }
	   	     result = DefaultResultUtil.getModificationResult(retStr);
	        
		   return "success"; 
	}
	  public String deleteProductClass() throws Exception{
		  
		     JSONObject object = JSONObject.fromObject(data);
			 ProductClassBean proBean=(ProductClassBean)JSONObject.toBean(object,ProductClassBean.class);
			 String retStr=iproductClassService.deleteProductClass(proBean);
			 result = DefaultResultUtil.getModificationResult(retStr);
			 return "success"; 
		}
	   public String saveSubProductClass() throws Exception{
		  
		     JSONObject object = JSONObject.fromObject(data);
		     log.info("obj"+object);
			 ProductClassBean proBean=(ProductClassBean)JSONObject.toBean(object,ProductClassBean.class);
			 String retStr=iproductClassService.saveSubProductClass(proBean);
			 log.info("save after");
			 if("".equals(retStr)){
		            retStr = "true";
		        }
		     result = DefaultResultUtil.getModificationResult(retStr);
			 return "success"; 
		}
	  
	    public String deleteSubProductClass() throws Exception{
		  
		     JSONObject object = JSONObject.fromObject(data);
			 ProductClassBean proBean=(ProductClassBean)JSONObject.toBean(object,ProductClassBean.class);
			 String retStr=iproductClassService.deleteSubProductClass(proBean);
			 result=DefaultResultUtil.getModificationResult(retStr);
			 return "success"; 
		}
	 
	    /* public String getSubProductClass() throws Exception{
		  
		     JSONObject object = JSONObject.fromObject(data);
			 ProductClassBean proBean=(ProductClassBean)JSONObject.toBean(object,ProductClassBean.class);
			 result=iproductClassService.getSubProductClass(proBean);
			 return "success"; 
		}*/
	
	
	

}
