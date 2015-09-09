package cn.gov.scciq.bussiness.itemStatistics;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.DefaultResultUtil;

public class ItemStatisticsService {
	public static JSONObject getStaticResultForItem(String data, int draw,
			int start, int length) {
		ItemStatisticsReqDto dto = null;
		if (data != null) {
			dto = (ItemStatisticsReqDto) JSONObject.toBean(
					JSONObject.fromObject(data), ItemStatisticsReqDto.class);
		} else {
			dto = new ItemStatisticsReqDto();
		}
		String orderWord = "ProductName";
		String orderDirection = "ASC";
		Map<Integer, Object> rsMap = ItemStatisticsDao.getStaticResultForItem(
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
		 String permission = AuthorityDao.getOperateLimit(ConstantStr.ITEM_STATISTICS);
		 JSONObject result = new JSONObject();
		 if(permission.equals("0")){
             result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
         }
		 return result;
	}

}
