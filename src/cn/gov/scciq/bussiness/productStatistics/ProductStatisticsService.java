package cn.gov.scciq.bussiness.productStatistics;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.DefaultResultUtil;

public class ProductStatisticsService {
	public static JSONObject getStaticResultForProduct(String data, int draw,
			int start, int length) {
		ProductStatisticsReqDto dto = null;
		if (data != null) {
			dto = (ProductStatisticsReqDto) JSONObject.toBean(
					JSONObject.fromObject(data), ProductStatisticsReqDto.class);
		} else {
			dto = new ProductStatisticsReqDto();
		}
		String orderWord = "ProductName";
		String orderDirection = "ASC";
		Map<Integer, Object> rsMap = ProductStatisticsDao.getStaticResultForProduct(
				dto.getStartYear(), dto.getStartMonth(), dto.getEndYear(),
				dto.getEndMonth(), dto.getProductCode(), dto.getProductName(),
				dto.getCountryCode(), dto.getEntCode(),dto.getItemCode(),
				start,length, orderWord, orderDirection);
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject tabResult = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		
		return tabResult;
	}
	
	public static JSONObject checkPermission(){
		 String permission = AuthorityDao.getOperateLimit(ConstantStr.PRODUCT_STATISTICS);
		 JSONObject result = new JSONObject();
		 if(permission.equals("0")){
             result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
         }
		 return result;
	}

}
