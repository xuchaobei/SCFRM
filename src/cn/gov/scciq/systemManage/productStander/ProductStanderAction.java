package cn.gov.scciq.systemManage.productStander;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

import cn.gov.scciq.bussiness.foreignReport.ForeignReportAction;
import cn.gov.scciq.bussiness.foreignReport.ForeignReportService;

public class ProductStanderAction {
	 private static final Log log = LogFactory.getLog(ProductStanderAction.class);
	    
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

		public String getProductByQuery(){
		        try {
		            result = ProductStanderService.getProductByQuery(data, draw, start, length);
		        } catch (Exception e) {
		            // TODO Auto-generated catch block
		            log.error("", e);;
		        }
		        return Action.SUCCESS;
		    }
		public String getProductByCode(){
	        try {
	            result = ProductStanderService.getProductByCode(data);
	            System.out.println("the result "+result);
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            log.error("", e);;
	        }
	        return Action.SUCCESS;
	    }
		public String normalizeProductName(){
	        try {
	            result = ProductStanderService.normalizeProductName(data);
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            log.error("", e);;
	        }
	        return Action.SUCCESS;
	    }
		
}
