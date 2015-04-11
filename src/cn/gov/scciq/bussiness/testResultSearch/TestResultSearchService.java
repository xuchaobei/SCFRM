package cn.gov.scciq.bussiness.testResultSearch;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.DefaultResultUtil;

public class TestResultSearchService {
	public static JSONObject getTestResultByQuery(String data, int draw,
			int start, int length) {
		TestResultSearchReqDto dto = null;
		if (data != null) {
			dto = (TestResultSearchReqDto) JSONObject.toBean(
					JSONObject.fromObject(data), TestResultSearchReqDto.class);
		} else {
			dto = new TestResultSearchReqDto();
		}
		String orderWord = "DeclDate";
		String orderDirection = "ASC";
		Map<Integer, Object> rsMap = TestResultSearchDao.getTestResultByQuery(dto.getDeclNo(),
				dto.getEntName(), dto.getCountryName(),dto.getProductName(),dto.getProductCode(),
				dto.getItemName(),dto.getIfQualify(),dto.getDeclDateFlg(),dto.getDeclDateStart(),
				dto.getDeclDateEnd(), start, length, orderWord, orderDirection);
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject tabResult = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		
		return tabResult;
	}
	
	public static JSONObject checkPermission(){
		 String permission = AuthorityDao.getOperateLimit(ConstantStr.TEST_RESULT_SEARCH);
		 JSONObject result = new JSONObject();
		 if(permission.equals("0")){
             result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
         }
		 return result;
	}

}
