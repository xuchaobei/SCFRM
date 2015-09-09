package cn.gov.scciq.basicSettings.item;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

import cn.gov.scciq.basicSettings.materialSource.MaterialSourceBean;
import cn.gov.scciq.basicSettings.materialSource.MaterialSourceSubBean;
import cn.gov.scciq.util.DefaultResultUtil;

public class ItemAction  {
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
		
		private static Log log=LogFactory.getLog(ItemAction.class);
        ItemService itemService = new ItemService();
		
		public String SaveItem() throws Exception {
			 JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     ItemBean itemBean=(ItemBean)JSONObject.toBean(object,ItemBean.class);
		     String retStr=itemService.SaveItem(itemBean);
		     if("".equals(retStr)){
		            retStr = "true";
		        }
		     result=DefaultResultUtil.getModificationResult(retStr);
			 return "success"; 
		}
		
		public String SaveItemSub() throws Exception {
			JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     String[]setarr= setscontent.split(",");
		     ItemBean itemBean=(ItemBean)JSONObject.toBean(object,ItemBean.class);
		     String retStr=itemService.SaveItemSub(itemBean,setarr);
		     if("".equals(retStr)){
		            retStr = "true";
		        }
		     result=DefaultResultUtil.getModificationResult(retStr);
			 return "success";
		}
	
		public String DelItem() throws Exception {
			 JSONObject object = JSONObject.fromObject(data);
		     log.info(object);
		     ItemBean itemBean=(ItemBean)JSONObject.toBean(object,ItemBean.class);
		     String retStr=itemService.DelItem(itemBean);
		     result=DefaultResultUtil.getModificationResult(retStr);
		   log.info("del"+result);
			 return "success";
		}
		
		public String GetItem() throws Exception {
			 JSONObject object = JSONObject.fromObject(data);
		     log.info("提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		     ItemBean proBean=(ItemBean)JSONObject.toBean(object,ItemBean.class);
		      Map<Integer, Object> rsMap=  itemService.GetItem(proBean, 0, start, length);
		       int recordsTotal = (Integer)rsMap.get(1);
		        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
		        log.info("getitem"+result);
			 return Action.SUCCESS;
		}
		public String GetItemSet() throws Exception {
			 JSONObject object = JSONObject.fromObject(data);
		     log.info("getconvctrl提交参数："+data+"draw is "+draw+"start is"+start+"length is "+length);
		     ItemBean proBean=(ItemBean)JSONObject.toBean(object,ItemBean.class);
		      Map<Integer, Object> rsMap=  itemService.GetItem(proBean, 1, 0, 0);
		       int recordsTotal = (Integer)rsMap.get(1);
		        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		         result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
			 return Action.SUCCESS;
		}
		
		public String GetItemSub() throws Exception {
			 JSONObject object = JSONObject.fromObject(data);
			 ItemSubBean bean=(ItemSubBean)JSONObject.toBean(object,ItemSubBean.class);
			 List<ItemSubBean> list = itemService.GetItemSub(bean);
			 result =DefaultResultUtil.getDefaultResult(list);
			 return Action.SUCCESS;  
		}
		public String GetRiskClass() throws Exception {
			 List<RiskClassBean> list = itemService.GetRiskClass();
			 result =DefaultResultUtil.getDefaultResult(list);
			 return Action.SUCCESS;
			
		}

}
