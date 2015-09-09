package cn.gov.scciq.bussiness.declProcess;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

public class DeclProcessAction {
	private static final Log log = LogFactory.getLog(DeclProcessAction.class);

	private int draw;

	private int start;

	private int length;

	private String data;

	private JSONObject result;
	
	public String getDeclProcessByQuery(){
		try {
			result = DeclProcessService.getDeclProcessByQuery(data, draw, start, length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getDeclProcessByDeclNo(){
		try {
			result = DeclProcessService.getDeclProcessByDeclNo(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getDeclProcessOperateLog(){
		try {
			result = DeclProcessService.getDeclProcessOperateLog(data);
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

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	
}
