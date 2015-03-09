package cn.gov.scciq.bussiness.goodsStatistics;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.DefaultResultUtil;

public class GoodsStatisticsService {
	public static JSONObject getStaticResultForGoods(String data, int draw,
			int start, int length) {
		Map<Integer, Object> rsMap = getMapStaticResultForGoods(data, draw, start, length);
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject tabResult = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		
		return tabResult;
	}
	
	public static JSONObject getSummaryStaticResultForGoods(String data, int draw,
			int start, int length) {
		Map<Integer, Object> rsMap = getMapStaticResultForGoods(data, draw, start, length);
		JSONObject singleResult = JSONObject.fromObject(rsMap.get(3));
		return singleResult;
	}
	
	public static Map<Integer, Object> getMapStaticResultForGoods(String data, int draw,
			int start, int length) {
		GoodsStatisticsReqDto dto = null;
		if (data != null) {
			dto = (GoodsStatisticsReqDto) JSONObject.toBean(
					JSONObject.fromObject(data), GoodsStatisticsReqDto.class);
		} else {
			dto = new GoodsStatisticsReqDto();
		}
		Map<Integer, Object> rsMap = GoodsStatisticsDao.getStaticResultForGoods(
				dto.getStartYear(), dto.getStartMonth(), dto.getEndYear(),
				dto.getEndMonth(), dto.getProductCode(), dto.getOrgCode(), dto.getDeptCode(),
				dto.getCountryCode(), dto.getEntCode(),dto.getProductClassCode(),
				dto.getProductSubclassCode(), dto.getGroup_Org(),dto.getGroup_Dept(), dto.getGroup_Country(),
				dto.getGroup_Ent(),dto.getGroup_ProductClass(), dto.getGroup_ProductSubclass(), ContextUtil.getOrgCode(),
				ContextUtil.getDeptCode(), ContextUtil.getRoleCode(), start,
				length);
		return rsMap;
	}
	
	public static JSONObject checkPermission(){
		 String permission = AuthorityDao.getOperateLimit(ConstantStr.GOODS_STATISTICS);
		 JSONObject result = new JSONObject();
		 if(permission.equals("0")){
             result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
         }
		 return result;
	}

}
