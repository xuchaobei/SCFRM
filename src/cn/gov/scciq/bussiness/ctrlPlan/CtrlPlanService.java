package cn.gov.scciq.bussiness.ctrlPlan;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.DefaultResultUtil;

public class CtrlPlanService {

	public static JSONObject getCtrlPlanImp(String data, int draw,
			int start, int length) {
		// TODO Auto-generated method stub
		int startIndex = 0;
		int pageSize = 0;
		String orderWord = "CtrlPlanImpID";
		String orderDirection = "DESC";
		CtrlPlanDto dto = (CtrlPlanDto)JSONObject.toBean(JSONObject.fromObject(data), CtrlPlanDto.class);
		Map<Integer, Object> rsMap = CtrlPlanDao.getCtrlPlanImp(dto.getCtrlPlanName(),dto.getEntName(),dto.getProductName(),
				startIndex, pageSize, orderWord, orderDirection);
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject result = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		return result;
	}

	public static JSONObject getCtrlPlanItem(String ctrlPlanImpID) {
		// TODO Auto-generated method stub
		List<CtrlPlanItemDto> list = CtrlPlanDao
				.getCtrlPlanItem(ctrlPlanImpID);
		JSONArray ja = JSONArray.fromObject(list);
		JSONObject result = DefaultResultUtil.getDefaultResult(ja);
		return result;
	}

	public static JSONObject saveCtrlPlanImp(String data) {
		// TODO Auto-generated method stub
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.CtrlPlan_Regist);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		String retStr = "";
		CtrlPlanDto ctrlPlanDto = (CtrlPlanDto) JSONObject.toBean(
				JSONObject.fromObject(data), CtrlPlanDto.class);
		retStr = CtrlPlanDao.saveCtrlPlanImp(
				ctrlPlanDto.getCtrlPlanImpID(),
				ctrlPlanDto.getCtrlPlanName(),
				ctrlPlanDto.getEntCode(),
				ctrlPlanDto.getEntProductCode(),
				ContextUtil.getOrgCode(),ContextUtil.getDeptCode(),ContextUtil.getOperatorCode());

		if ("".equals(retStr)) {
			retStr = "true";
		}
		JSONObject result = DefaultResultUtil.getModificationResult(retStr);
		return result;
	}

	public static JSONObject delCtrlPlanImp(String ctrlPlanImpID) {
		// TODO Auto-generated method stub
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.CtrlPlan_Regist);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		boolean retCode = CtrlPlanDao.delCtrlPlanImp(ctrlPlanImpID);
		JSONObject jo = DefaultResultUtil.getModificationResult(retCode + "");
		return jo;
	}

	public static JSONObject getCtrlPlanImpByID(String ctrlPlanImpID) {
		// TODO Auto-generated method stub
		CtrlPlanDto dto = CtrlPlanDao
				.getCtrlPlanImpByID(ctrlPlanImpID);
		return JSONObject.fromObject(dto);
	}

	public static JSONObject saveCtrlPlanItem(String data) {
		// TODO Auto-generated method stub
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.CtrlPlan_Regist);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		String retStr = "";
		CtrlPlanItemDto dto = (CtrlPlanItemDto) JSONObject
				.toBean(JSONObject.fromObject(data),
						CtrlPlanItemDto.class);
		retStr = CtrlPlanDao.saveCtrlPlanItem(dto.getCtrlPlanItemID(),dto.getCtrlPlanImpID(),
				dto.getItemCode(), dto.getLabData(), dto.getDataUnit(),dto.getImpDate(),dto.getValidDays());
		if ("".equals(retStr)) {
			retStr = "true";
		}
		JSONObject result = DefaultResultUtil.getModificationResult(retStr);
		return result;
	}

	public static JSONObject delCtrlPlanItem(String ctrlPlanItemID) {
		// TODO Auto-generated method stub
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.CtrlPlan_Regist);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		boolean retCode = CtrlPlanDao
				.delCtrlPlanItem(ctrlPlanItemID);
		JSONObject jo = DefaultResultUtil.getModificationResult(retCode + "");
		return jo;
	}

}
