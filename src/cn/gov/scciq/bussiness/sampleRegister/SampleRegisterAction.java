package cn.gov.scciq.bussiness.sampleRegister;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	
	public String getLabSampleInfoBySampleID(){
		try {
			result = SampleRegisterService.getLabSampleInfoBySampleID(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getLabItemMatched(){
		try {
			result = SampleRegisterService.getLabItemMatched(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	
	public String delLabItemMatchedForNewSample(){
		try {
			result = SampleRegisterService.delLabItemMatchedForNewSample(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String saveLabItemMatchedManual(){
		try {
			result = SampleRegisterService.saveLabItemMatchedManual(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String saveLabApplyInfo(){
		try {
			result = SampleRegisterService.saveLabApplyInfo(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String saveLabDefaultData(){
		try {
			result = SampleRegisterService.saveLabDefaultData(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	
	public String saveLabSample(){
		try {
			result = SampleRegisterService.saveLabSample(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}

	public String saveLabSampleItem(){
		try {
			result = SampleRegisterService.saveLabSampleItem(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getLabItemByQuery(){
		try {
			result = SampleRegisterService.getLabItemByQuery(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String saveLabItemMatched(){
		try {
			result = SampleRegisterService.saveLabItemMatched(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getItemSubByDeclItem(){
		try {
			result = SampleRegisterService.getItemSubByDeclItem(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getItemSubMatchedForDeclItem(){
		try {
			result = SampleRegisterService.getItemSubMatchedForDeclItem(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String saveLabItemMatchedForSubitem(){
		try {
			result = SampleRegisterService.saveLabItemMatchedForSubitem(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String sampleRegAuto(){
		try {
			result = SampleRegisterService.sampleRegAuto(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String divideLabSample(){
		try {
			result = SampleRegisterService.divideLabSample(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String delLabSample(){
		try {
			result = SampleRegisterService.delLabSample(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String delLabSampleItem(){
		try {
			result = SampleRegisterService.delLabSampleItem(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getLabApplyByDeclNo(){
		try {
			result = SampleRegisterService.getLabApplyByDeclNo(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String delItemSubMatchedForDeclItem(){
		try {
			result = SampleRegisterService.delItemSubMatchedForDeclItem(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String checkIfCanSubmitLab(){
		try {
			result = SampleRegisterService.checkIfCanSubmitLab(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String submitLabSuccess(){
		try {
			JSONObject jo = JSONObject.fromObject(data);
			String declNo = jo.getString("declNo");
			String labApplyID = jo.getString("labApplyID");
			result = SampleRegisterService.submitLabSuccess(declNo, labApplyID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String sampleReg(){
		try {
			result = SampleRegisterService.sampleReg(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getDeclInfoFromCIQ(){
		try {
			result = SampleRegisterService.delItemSubMatchedForDeclItem(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getDeclGoodsInfoFromCIQ(){
		try {
			result = SampleRegisterService.delItemSubMatchedForDeclItem(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
}
