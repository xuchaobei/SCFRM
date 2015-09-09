package cn.gov.scciq.bussiness.samplingStatistics;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.DefaultResultUtil;

public class SamplingStatisticsService {
	public static JSONObject getStaticResultForSampling(String data, int draw,
			int start, int length) {
		SamplingStatisticsReqDto dto = null;
		if (data != null) {
			dto = (SamplingStatisticsReqDto) JSONObject.toBean(
					JSONObject.fromObject(data), SamplingStatisticsReqDto.class);
		} else {
			dto = new SamplingStatisticsReqDto();
		}
		Map<Integer, Object> rsMap = SamplingStatisticsDao.getStaticResultForSampling(
				dto.getStartYear(), dto.getStartMonth(), dto.getEndYear(),
				dto.getEndMonth(), dto.getProductCode(), dto.getProductName(),
				dto.getCountryCode(), dto.getEntCode(),dto.getItemCode(),
				ContextUtil.getOrgCode(), ContextUtil.getDeptCode(), ContextUtil.getOperatorCode(), start,length);
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
