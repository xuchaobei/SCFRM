package cn.gov.scciq.basicSettings.countryArea;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

import cn.gov.scciq.basicSettings.packageType.PackageTypeBean;
import cn.gov.scciq.basicSettings.packageType.PackageTypeSubBean;
import cn.gov.scciq.util.DefaultResultUtil;


public class CountryAreaAction {
	    private String data;
		private JSONObject result;
		private int draw;
		private int start;
	    private int length;
	    private String setscontent;
	    
	    public String getSetscontent() {
			return setscontent;
		}
		public void setSetscontent(String setscontent) {
			this.setscontent = setscontent;
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
		
		private static Log log=LogFactory.getLog(CountryAreaAction.class);
		CountryAreaService countryAreaService = new CountryAreaService();
		
		public String SaveCountry() throws Exception {
			 JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     CountryAreaBean countryAreaBean=(CountryAreaBean)JSONObject.toBean(object,CountryAreaBean.class);
			 String retStr=countryAreaService.SaveCountry(countryAreaBean);
			 if("".equals(retStr)){
		            retStr = "true";
		        }
			 result=DefaultResultUtil.getModificationResult(retStr);
			 return "success"; 
		}
	
		public String SaveCountrySub() throws Exception {
			 JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     String[]setarr= setscontent.split(",");
		     CountryAreaSetBean bean=(CountryAreaSetBean)JSONObject.toBean(object,CountryAreaSetBean.class);
		     String retStr=countryAreaService.SaveCountrySub(bean,setarr);
		     if("".equals(retStr)){
		            retStr = "true";
		        }
			 result=DefaultResultUtil.getModificationResult(retStr);
		     return "success";
		}
	
		public String DelCountry() throws Exception {
			 JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     CountryAreaBean countryAreaBean=(CountryAreaBean)JSONObject.toBean(object,CountryAreaBean.class);
		     String retStr=countryAreaService.DelCountry(countryAreaBean);
		     result=DefaultResultUtil.getModificationResult(retStr);
			 return "success";
		}
		public String getCountry() throws Exception{
			JSONObject object = JSONObject.fromObject(data);
		     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		     CountryAreaBean proBean=(CountryAreaBean)JSONObject.toBean(object,CountryAreaBean.class);
		     Map<Integer, Object> rsMap=  countryAreaService.GetCountry(proBean, 0, start, length);
		       int recordsTotal = (Integer)rsMap.get(1);
		        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
			 return Action.SUCCESS;
		}
		public String getCountrySet() throws Exception{
			JSONObject object = JSONObject.fromObject(data);
		     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		     CountryAreaBean proBean=(CountryAreaBean)JSONObject.toBean(object,CountryAreaBean.class);
		     Map<Integer, Object> rsMap=  countryAreaService.GetCountry(proBean, 1, 0, 0);
		       int recordsTotal = (Integer)rsMap.get(1);
		        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
			 return Action.SUCCESS;
		}
		public String getCountrySub() throws Exception{
			 JSONObject object = JSONObject.fromObject(data);
			 CountryAreaSubBean proBean=(CountryAreaSubBean)JSONObject.toBean(object,CountryAreaSubBean.class);
			 List<CountryAreaSubBean> list = countryAreaService.GetCountrySub(proBean);
			 result =DefaultResultUtil.getDefaultResult(list);
			 return Action.SUCCESS;
		}

}
