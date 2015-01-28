package cn.gov.scciq.bussiness.productCtrlItem;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.util.DefaultResultUtil;

public class ProductCtrlItemService {
	
	public static JSONObject getProductControlItem(String data, int draw,
			int start, int length) {
		// TODO Auto-generated method stub
		ProductCtrlItemReqDto dto = (ProductCtrlItemReqDto)JSONObject.toBean(JSONObject.fromObject(data),ProductCtrlItemReqDto.class);
		Map<Integer,Object> rsMap = ProductCtrlItemDao.getProductControlItem("0", dto.getProductCode(),dto.getProductName(), dto.getCountryName(),
				dto.getItemName(), start, length, "ProductControlItemID", "DESC");
	    int recordsTotal = (Integer)rsMap.get(1);
        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
        JSONObject result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
        return result;
	}
	
	public static JSONObject getProductControlItemDetail(String productControlItemID) {
		// TODO Auto-generated method stub
		Map<Integer,Object> rsMap = ProductCtrlItemDao.getProductControlItem(productControlItemID, "","", "",
				"", 0, 0, "ProductControlItemID", "DESC");
	    JSONObject jo = JSONObject.fromObject(((List<ProductCtrlItemDto>)rsMap.get(2)).get(0));
        return jo;
	}

	public static JSONObject getProductByCode(String data) {
		// TODO Auto-generated method stub
		ProductDetailAllDto dto = ProductCtrlItemDao.getProductByCode(data);
		JSONObject jo = new JSONObject();
		jo.put("productDetail", dto.getProductDetail());
		jo.put("materialDetail", dto.getMaterialDetailList());
		return jo;
	}



}
