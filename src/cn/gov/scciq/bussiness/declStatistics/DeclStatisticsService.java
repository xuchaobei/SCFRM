package cn.gov.scciq.bussiness.declStatistics;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.DefaultResultUtil;

public class DeclStatisticsService {
	public static JSONObject getStaticResultForDecl(String data, int draw,
			int start, int length) {
		Map<Integer, Object> rsMap = getMapStaticResultForDec(data, draw, start, length);
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject tabResult = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		
		return tabResult;
	}
	
	public static JSONObject getSummaryStaticResultForDecl(String data, int draw,
			int start, int length) {
		Map<Integer, Object> rsMap = getMapStaticResultForDec(data, draw, start, length);
		JSONObject singleResult = JSONObject.fromObject(rsMap.get(3));
		return singleResult;
	}
	
	public static Map<Integer, Object> getMapStaticResultForDec(String data, int draw,
			int start, int length) {
		DeclStatisticsReqDto dto = null;
		if (data != null) {
			dto = (DeclStatisticsReqDto) JSONObject.toBean(
					JSONObject.fromObject(data), DeclStatisticsReqDto.class);
		} else {
			dto = new DeclStatisticsReqDto();
		}
		Map<Integer, Object> rsMap = DeclStatisticsDao.getStaticResultForDecl(
				dto.getStartYear(), dto.getStartMonth(), dto.getEndYear(),
				dto.getEndMonth(), dto.getOrgCode(), dto.getDeptCode(),
				dto.getCountryCode(), dto.getEntCode(), dto.getGroup_Org(),
				dto.getGroup_Dept(), dto.getGroup_Country(),
				dto.getGroup_Ent(), ContextUtil.getOrgCode(),
				ContextUtil.getDeptCode(), ContextUtil.getOperatorCode(), start,
				length);
		return rsMap;
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
