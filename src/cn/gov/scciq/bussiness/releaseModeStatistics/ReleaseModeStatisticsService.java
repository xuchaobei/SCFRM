package cn.gov.scciq.bussiness.releaseModeStatistics;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.DefaultResultUtil;

public class ReleaseModeStatisticsService {
	public static JSONObject getStaticResultForReleaseMode(String data, int draw,
			int start, int length) {
		ReleaseModeStatisticsReqDto dto = null;
		if (data != null) {
			dto = (ReleaseModeStatisticsReqDto) JSONObject.toBean(
					JSONObject.fromObject(data), ReleaseModeStatisticsReqDto.class);
		} else {
			dto = new ReleaseModeStatisticsReqDto();
		}
		Map<Integer, Object> rsMap = ReleaseModeStatisticsDao.getStaticResultForReleaseMode(
				dto.getStartYear(), dto.getStartMonth(), dto.getEndYear(),
				dto.getEndMonth(), dto.getOrgCode(), dto.getDeptCode(),
				dto.getCountryCode(), dto.getEntCode(), dto.getGroup_Org(),
				dto.getGroup_Dept(), dto.getGroup_Country(),
				dto.getGroup_Ent(), ContextUtil.getOrgCode(),
				ContextUtil.getDeptCode(), ContextUtil.getRoleCode(), start,
				length);
		
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject tabResult = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		
		return tabResult;
	}
	
	
	public static JSONObject checkPermission(){
		 String permission = AuthorityDao.getOperateLimit(ConstantStr.DECL_STATISTICS);
		 JSONObject result = new JSONObject();
		 if(permission.equals("0")){
             result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
         }
		 return result;
	}

}
