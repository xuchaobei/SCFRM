package cn.gov.scciq.bussiness.declProcess;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.DefaultResultUtil;

public class DeclProcessService {

	private static final Log log = LogFactory.getLog(DeclProcessService.class);

	/**
	 * 流程查询
	 * 
	 * @return
	 */
	public static JSONObject getDeclProcessByQuery(String data, int draw,
			int start, int length) {
		DeclProcessReqDto dto = null;
		if (data != null) {
			dto = (DeclProcessReqDto) JSONObject.toBean(
					JSONObject.fromObject(data), DeclProcessReqDto.class);
		} else {
			dto = new DeclProcessReqDto();
		}
		String declDateFlg = "1"; // 因为前台页面时间控件会有一个默认当前时间，所以传给存储过程的参数是包含起始日期和结束日期的。
		String orderWord = "DeclNo";
		String orderDirection = "DESC";
		Map<Integer, Object> rsMap = DeclProcessDao.getDeclProcessByQuery(
				dto.getDeclNo(), declDateFlg, dto.getDeclDateStart(),
				dto.getDeclDateEnd(), dto.getEntName(), dto.getCountryName(),
				ContextUtil.getOrgCode(), ContextUtil.getDeptCode(),
				ContextUtil.getOperatorCode(), dto.getIfQualify(), start,
				length, orderWord, orderDirection);
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject result = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		return result;
	}

	/**
	 * 根据报检号给出各流程操作数据
	 * 
	 * @return
	 */
	public static JSONObject getDeclProcessByDeclNo(String declNo) {

		List<DeclProcessFlowDto> list = DeclProcessDao
				.getDeclProcessByDeclNo(declNo);
		JSONObject jo = DefaultResultUtil.getDefaultResult(list);
		return jo;
	}

	/**
	 * 根据报检号给出报检单证的流程操作日志记录
	 * 
	 * @return
	 */
	public static JSONObject getDeclProcessOperateLog(String declNo) {
		List<DeclProcessLogDto> list = DeclProcessDao
				.getDeclProcessOperateLog(declNo);
		JSONObject jo = DefaultResultUtil.getDefaultResult(list);
		return jo;
	}

}
