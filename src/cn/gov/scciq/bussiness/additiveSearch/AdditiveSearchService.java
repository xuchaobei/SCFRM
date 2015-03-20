package cn.gov.scciq.bussiness.additiveSearch;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.DefaultResultUtil;

public class AdditiveSearchService {
	public static JSONObject getAdditiveUseByEntProduct(String data, int draw,
			int start, int length) {
		AdditiveSearchReqDto dto = null;
		if (data != null) {
			dto = (AdditiveSearchReqDto) JSONObject.toBean(
					JSONObject.fromObject(data), AdditiveSearchReqDto.class);
		} else {
			dto = new AdditiveSearchReqDto();
		}
		String orderWord = "AdditiveName";
		String orderDirection = "ASC";
		Map<Integer, Object> rsMap = AdditiveSearchDao.getAdditiveUseByEntProduct(dto.getAdditiveName(),dto.getEntName(), dto.getProductName(), dto.getCountryName(),
				start, length, orderWord, orderDirection);
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject tabResult = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		
		return tabResult;
	}
	
	public static JSONObject checkPermission(){
		 String permission = AuthorityDao.getOperateLimit(ConstantStr.ACCESSORY_SEARCH);
		 JSONObject result = new JSONObject();
		 if(permission.equals("0")){
             result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
         }
		 return result;
	}

}
