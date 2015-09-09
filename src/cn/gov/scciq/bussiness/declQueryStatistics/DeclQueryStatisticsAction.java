package cn.gov.scciq.bussiness.declQueryStatistics;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

public class DeclQueryStatisticsAction {
	private static final Log log = LogFactory.getLog(DeclQueryStatisticsAction.class);

	private int draw;

	private int start;

	private int length;

	private String data;

	private JSONObject result;
	
	public String delDeclQueryAllCondition(){
		try {
			result = DeclQueryStatisticsService.delDeclQueryAllCondition();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String saveDeclQueryCondition(){
		try {
			result = DeclQueryStatisticsService.saveDeclQueryCondition(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getDeclQueryCondition(){
	
		try {
			result = DeclQueryStatisticsService.getDeclQueryCondition();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}

	public String delDeclQuerySingleCondition(){

		try {
			result = DeclQueryStatisticsService.delDeclQuerySingleCondition(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String generateDeclQuerySQLStr(){

		try {
			result = DeclQueryStatisticsService.generateDeclQuerySQLStr();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String getDeclQueryResult(){

		try {
			result = DeclQueryStatisticsService.getDeclQueryResult(draw, start, length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}
	
	public String checkPermission(){
		try {
			result = DeclQueryStatisticsService.checkPermission();
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
