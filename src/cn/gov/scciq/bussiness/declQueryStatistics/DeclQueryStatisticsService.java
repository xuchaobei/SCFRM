package cn.gov.scciq.bussiness.declQueryStatistics;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.DefaultResultUtil;

public class DeclQueryStatisticsService {

	public static JSONObject delDeclQueryAllCondition() {
		// TODO Auto-generated method stub
		boolean retCode = DeclQueryStatisticsDao.delDeclQueryAllCondition(
				ContextUtil.getOrgCode(), ContextUtil.getDeptCode(),
				ContextUtil.getOperatorCode());
		JSONObject jo = DefaultResultUtil.getModificationResult(retCode + "");
		return jo;
	}

	public static JSONObject saveDeclQueryCondition(String data) {
		String retStr = ConstantStr.SAVE_ERROR_MSG;
		DeclQueryConditionDto dto = (DeclQueryConditionDto) JSONObject.toBean(
				JSONObject.fromObject(data), DeclQueryConditionDto.class);
		if (dto != null) {
			retStr = DeclQueryStatisticsDao.saveDeclQueryCondition(
					Integer.parseInt(dto.getDeclQueryConditionID()),
					ContextUtil.getOrgCode(), ContextUtil.getDeptCode(),
					ContextUtil.getOperatorCode(),
					Integer.parseInt(dto.getSortNo()), dto.getLeftLogic(),
					dto.getDefinedField(), dto.getOperateName(),
					dto.getOperateValue(), dto.getRightLogic());
		}

		JSONObject jo = DefaultResultUtil.getModificationResult(retStr);
		return jo;
	}

	public static JSONObject getDeclQueryCondition() {
		List<DeclQueryConditionDto> list = DeclQueryStatisticsDao
				.getDeclQueryCondition(ContextUtil.getOrgCode(),
						ContextUtil.getDeptCode(),
						ContextUtil.getOperatorCode());
		JSONObject result = DefaultResultUtil.getDefaultResult(list);
		return result;
	}

	public static JSONObject delDeclQuerySingleCondition(String data) {
		boolean retCode = DeclQueryStatisticsDao
				.delDeclQuerySingleCondition(Integer.parseInt(data));
		JSONObject jo = DefaultResultUtil.getModificationResult(retCode + "");
		return jo;
	}

	public static JSONObject generateDeclQuerySQLStr() {
		String sqlStr = DeclQueryStatisticsDao.generateDeclQuerySQLStr(
				ContextUtil.getOrgCode(), ContextUtil.getDeptCode(),
				ContextUtil.getOperatorCode());
		JSONObject jo = new JSONObject();
		jo.put("data", sqlStr);
		return jo;
	}

	public static JSONObject getDeclQueryResult(int draw, int start, int length) {
		String orderWord = "DeclNo";
		String orderDirection = "ASC";
		Map<Integer, Object> rsMap = DeclQueryStatisticsDao.getDeclQueryResult(
				ContextUtil.getOrgCode(), ContextUtil.getDeptCode(),
				ContextUtil.getOperatorCode(), start, length, orderWord,
				orderDirection);
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject result = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		return result;
	}
}
