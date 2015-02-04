package cn.gov.scciq.bussiness.entProduct;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

import cn.gov.scciq.bussiness.baseMng.BaseMngAction;

public class EntProductAction {

	private static final Log log = LogFactory.getLog(BaseMngAction.class);

	private int draw;

	private int start;

	private int length;

	private String data;

	private String entCode;
	private String entProductCode;
	private String productCode;
	private String countryCode;

	private JSONObject result;

	public String getEntProductByQuery(){
		try {
			result = EntProductService.getEntProductByQuery(data, draw, start, length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getEntProductDetailByID(){
		try {
			result = EntProductService.getEntProductDetailByID(entCode,entProductCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String setEntProductRapidRelease(){
		try {
			result = EntProductService.setEntProductRapidRelease(entCode,productCode,countryCode,entProductCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String setEntProductGreenChanel(){
		try {
			result = EntProductService.setEntProductGreenChanel(entCode,productCode,countryCode,entProductCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
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

	public String getEntCode() {
		return entCode;
	}

	public void setEntCode(String entCode) {
		this.entCode = entCode;
	}

	public String getEntProductCode() {
		return entProductCode;
	}

	public void setEntProductCode(String entProductCode) {
		this.entProductCode = entProductCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public static Log getLog() {
		return log;
	}
	
	

}
