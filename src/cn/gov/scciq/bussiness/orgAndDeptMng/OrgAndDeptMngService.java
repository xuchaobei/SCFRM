package cn.gov.scciq.bussiness.orgAndDeptMng;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.DefaultResultUtil;

public class OrgAndDeptMngService {

	public static JSONObject getInspOrg( int draw,
			int start, int length) {
		// TODO Auto-generated method stub
		Map<Integer, Object> rsMap = OrgAndDeptMngDao.getInspOrg(start, length);
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject result = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		return result;
	}

	public static JSONObject getInspDeptPaging(String orgCode, int draw,
			int start, int length) {
		// TODO Auto-generated method stub
		Map<Integer, Object> rsMap = OrgAndDeptMngDao.getInspDeptPaging(orgCode, start, length);
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject result = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		return result;
		
	}

	public static JSONObject checkPermission(){
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.ORG_DEPT_MNG);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}else{
			JSONObject result = DefaultResultUtil
					.getModificationResult("");
			return result;
		}
	}
	
	public static JSONObject saveInspOrg(String data) {
		// TODO Auto-generated method stub
		String retStr = "";
		OrgDto orgDto = (OrgDto) JSONObject.toBean(
				JSONObject.fromObject(data), OrgDto.class);
		retStr = OrgAndDeptMngDao.saveInspOrg(orgDto.getInspOrgID(), orgDto.getOrgCode(), orgDto.getOrgName());
		if ("".equals(retStr)) {
			retStr = "true";
		}
		JSONObject result = DefaultResultUtil.getModificationResult(retStr);
		return result;
	}

	public static JSONObject delInspOrg(String inspOrgID) {
		// TODO Auto-generated method stub
		boolean retCode = OrgAndDeptMngDao.delInspOrg(inspOrgID);
		JSONObject jo = DefaultResultUtil.getModificationResult(retCode + "");
		return jo;
	}

	public static JSONObject saveInspDept(String data) {
		// TODO Auto-generated method stub
		String retStr = "";
		DeptDto dto = (DeptDto) JSONObject
				.toBean(JSONObject.fromObject(data),
						DeptDto.class);
		retStr = OrgAndDeptMngDao.saveInspDept(dto.getInspDeptID(), dto.getOrgCode(), dto.getDeptCode(),
				dto.getDeptName() );
		if ("".equals(retStr)) {
			retStr = "true";
		}
		JSONObject result = DefaultResultUtil.getModificationResult(retStr);
		return result;
	}

	public static JSONObject delInspDept(String inspDeptID){
		// TODO Auto-generated method stub
		boolean retCode = OrgAndDeptMngDao
				.delInspDept(inspDeptID);
		JSONObject jo = DefaultResultUtil.getModificationResult(retCode + "");
		return jo;
	}

}
