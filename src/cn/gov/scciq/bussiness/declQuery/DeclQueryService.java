package cn.gov.scciq.bussiness.declQuery;


import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.DefaultResultUtil;

public class DeclQueryService {

	public static JSONObject getDeclQueryResultSimple(String data, int draw,
			int start, int length){
		String permission = AuthorityDao.getOperateLimit(ConstantStr.Decl_Query);
        if(!permission.equals("1")){
            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
            return result;
        }
		DeclQueryReqDto dto = null;
		if (data != null) {
			dto = (DeclQueryReqDto) JSONObject.toBean(
					JSONObject.fromObject(data), DeclQueryReqDto.class);
		} else {
			dto = new DeclQueryReqDto();
		}
		String orderWord = "DeclDate";
		String orderDirection = "DESC";
		System.out.println("date "+dto.getStartDate()+dto.getEndDate());
		Map<Integer, Object> rsMap = DeclQueryDao.getDeclQueryResultSimple(ContextUtil.getOrgCode(), ContextUtil.getDeptCode(), ContextUtil.getOperatorCode(), dto.getDeclNo(),
				dto.getEntName(), dto.getCountryName(),dto.getStartDate(), dto.getEndDate(),dto.getQualifyJudge(), dto.getReleaseStatus(), start, length, orderWord, orderDirection);
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject result = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		return result;
	}
	public static JSONObject getReleaseMode(){
	List<String> list=	DeclQueryDao.getReleaseMode();
	JSONObject result=DefaultResultUtil.getDefaultResult(list);
	
	return result;
		
	}

}
