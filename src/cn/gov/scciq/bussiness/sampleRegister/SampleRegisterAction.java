package cn.gov.scciq.bussiness.sampleRegister;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.util.DefaultResultUtil;

import com.opensymphony.xwork2.Action;

public class SampleRegisterAction {
	private static final Log log = LogFactory.getLog(SampleRegisterAction.class);

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

	public String getDeclInfoForProcess(){
		try {
			result = SampleRegisterService.getDeclInfoForProcess(data, draw, start, length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getDeclProduct(){
		try {
			result = SampleRegisterService.getDeclProduct(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getDeclProductItem(){
		try {
			result = SampleRegisterService.getDeclProductItem(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getLabSampleByProduct(){
		try {
			result = SampleRegisterService.getLabSampleByProduct(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getLabSampleItemByProduct(){
		try {
			result = SampleRegisterService.getLabSampleItemByProduct(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String cancelCurrentProcess(){
		try {
			result = SampleRegisterService.cancelCurrentProcess(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String checkIfProcessOperate(){
		try {
			result = SampleRegisterService.checkIfProcessOperate(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String checkItemIfSampling(){
		try {
			result = SampleRegisterService.checkItemIfSampling(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getLabApply(){
		try {
			result = SampleRegisterService.getLabApply(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
}
