package cn.gov.scciq.bussiness.productCtrlItem;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

/**
 * 监控表单
 * 
 * @author chao.xu
 *
 */
public class ProductCtrlItemAction {
	private static final Log log = LogFactory.getLog(ProductCtrlItemAction.class);

	private int draw;

	private int start;

	private int length;

	private int productControlItemID;
	
	private String data;

	private JSONObject result;

	public String getProductControlItem() {
		try {
			if(productControlItemID > 0){    //获取详情
				result = ProductCtrlItemService.getProductControlItemDetail(String.valueOf(productControlItemID));
			}else{                                  //获取列表
				result = ProductCtrlItemService.getProductControlItem(data, draw, start, length);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}

	public String getProductByCode() {
		try {
			result = ProductCtrlItemService.getProductByCode(data);
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

	public int getProductControlItemID() {
		return productControlItemID;
	}

	public void setProductControlItemID(int productControlItemID) {
		this.productControlItemID = productControlItemID;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

}
