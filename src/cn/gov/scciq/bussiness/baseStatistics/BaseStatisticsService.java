package cn.gov.scciq.bussiness.baseStatistics;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.DefaultResultUtil;

public class BaseStatisticsService {
	public static JSONObject getStaticResultForSampling(String data, int draw,
			int start, int length) {
		BaseStatisticsReqDto dto = null;
		if (data != null) {
			dto = (BaseStatisticsReqDto) JSONObject.toBean(
					JSONObject.fromObject(data), BaseStatisticsReqDto.class);
		} else {
			dto = new BaseStatisticsReqDto();
		}
		String orderWord = "BaseName";
		String orderDirection = "ASC";
		Map<Integer, Object> rsMap = BaseStatisticsDao.getStaticResultForBase(
				dto.getStartYear(), dto.getStartMonth(), dto.getEndYear(),
				dto.getEndMonth(), dto.getBaseCode(), start,length, orderWord, orderDirection);
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
