package cn.gov.scciq.bussiness.baseMng;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.DefaultResultUtil;

public class BaseMngService {
	public static JSONObject getBaseList(String data, int draw, int start, int length) {
		int startIndex = 0;
		int pageSize = 0;
		String orderWord = "BaseID";
		String orderDirection = "DESC";
		String baseCode = "";
		String baseName = "";
		if (data != null && !"".equals(data)) {
			JSONObject params = JSONObject.fromObject(data);
			baseCode = params.getString("baseCode");
			baseName = params.getString("baseName");
		}
		Map<Integer, Object> rsMap = BaseMngDao.getBaseList(baseName, baseCode,
				startIndex, pageSize, orderWord, orderDirection);
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject result = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		return result;
	}

	public static JSONObject delBase(String baseCode) {
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.ENT_MANAGEMENT);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		boolean retCode = BaseMngDao.delBase(baseCode);
		JSONObject jo = DefaultResultUtil.getModificationResult(retCode + "");
		return jo;
	}

	public static JSONObject getBaseDetailByCode(String baseCode) {
		BaseMngDetailDto dto = BaseMngDao.getBaseDetailByCode(baseCode);
		return JSONObject.fromObject(dto);
	}

	public static JSONObject saveBase(String data) {
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.ENT_MANAGEMENT);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		BaseMngDetailDto dto = (BaseMngDetailDto) JSONObject.toBean(
				JSONObject.fromObject(data), BaseMngDetailDto.class);
		String retStr = BaseMngDao.saveBase(dto.getBaseID(), dto.getBaseCode(),
				dto.getBaseName(), dto.getAddress(), dto.getBaseLevel(),
				dto.getBaseEvl(), dto.getArea(), dto.getPlantKind(),
				dto.getOrgCode(), dto.getEntName(), dto.getRegDate(),
				dto.getRemarks());
		if ("".equals(retStr)) {
			retStr = "true";
		}
		JSONObject result = DefaultResultUtil.getModificationResult(retStr);
		return result;
	}
}
