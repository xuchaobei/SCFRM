package cn.gov.scciq.bussiness.baseMaterial;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.DefaultResultUtil;

public class BaseMaterialService {

	public static JSONObject getBaseMaterialList(String data, int draw,
			int start, int length) {
		// TODO Auto-generated method stub
		int startIndex = 0;
		int pageSize = 0;
		String orderWord = "BaseMaterialID";
		String orderDirection = "DESC";
		Map<Integer, Object> rsMap = BaseMaterialDao.getBaseMaterialList(data,
				startIndex, pageSize, orderWord, orderDirection);
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject result = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		return result;
	}
	public static JSONObject getMaterialReleaseList(String data, int draw,
			int start, int length) {
		// TODO Auto-generated method stub
		int startIndex = 0;
		int pageSize = 0;
		String orderWord = "MaterialReleaseID";
		String orderDirection = "DESC";
		Map<Integer, Object> rsMap = BaseMaterialDao.getMaterialReleaseList(data, startIndex, pageSize, orderWord, orderDirection);
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(rsMap.get(2));
		JSONObject result = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		return result;
	}

	public static JSONObject getBaseMaterialItemByID(String baseMaterialID) {
		// TODO Auto-generated method stub
		List<BaseMaterialItemResDto> list = BaseMaterialDao
				.getBaseMaterialItemByID(baseMaterialID);
		JSONArray ja = JSONArray.fromObject(list);
		JSONObject result = DefaultResultUtil.getDefaultResult(ja);
		return result;
	}
	

	public static JSONObject getMaterialReleaseItem(String materialReleaseID) {
		// TODO Auto-generated method stub
		List<MaterialRealseItemDto> list = BaseMaterialDao.getMaterialReleaseItem(materialReleaseID);
				
		JSONArray ja = JSONArray.fromObject(list);
		JSONObject result = DefaultResultUtil.getDefaultResult(ja);
		return result;
	}

	public static JSONObject saveBaseMaterial(String data) {
		// TODO Auto-generated method stub
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.Base_Material);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		String retStr = "";
		BaseMaterialDto baseMaterialDto = (BaseMaterialDto) JSONObject.toBean(
				JSONObject.fromObject(data), BaseMaterialDto.class);
		retStr = BaseMaterialDao.saveBaseMaterial(
				baseMaterialDto.getBaseMaterialID(),
				baseMaterialDto.getBaseCode(),
				baseMaterialDto.getMaterialClassCode(),
				baseMaterialDto.getMaterialSubclassCode(),
				baseMaterialDto.getMaterialCode());

		if ("".equals(retStr)) {
			retStr = "true";
		}
		JSONObject result = DefaultResultUtil.getModificationResult(retStr);
		return result;
	}
	
	public static JSONObject saveMaterialRelease(String data) {
		// TODO Auto-generated method stub
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.Material_Patch);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		String retStr = "";
		MaterialRealseDto dto = (MaterialRealseDto) JSONObject.toBean(
				JSONObject.fromObject(data), MaterialRealseDto.class);
		retStr = BaseMaterialDao.saveMaterialRelease(dto.getMaterialReleaseID(), dto.getMaterialBatchNo(), dto.getMaterialClassCode(), dto.getMaterialSubclassCode(),
				   dto.getMaterialCode(), dto.getInspDate(), dto.getValidDays());
System.out.println("ret "+retStr);
		if ("".equals(retStr)  || retStr==null) {
			retStr = "true";
		}
		JSONObject result = DefaultResultUtil.getModificationResult(retStr);
		System.out.println("saveMr "+result);
		return result;
	}

	public static JSONObject delBaseMaterial(String baseMaterialID) {
		// TODO Auto-generated method stub
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.Base_Material);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		boolean retCode = BaseMaterialDao.delBaseMaterial(baseMaterialID);
		JSONObject jo = DefaultResultUtil.getModificationResult(retCode + "");
		return jo;
	}
	public static JSONObject delMaterialRelease(String materialReleaseID) {
		// TODO Auto-generated method stub
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.Material_Patch);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		boolean retCode = BaseMaterialDao.delMaterialRelease(materialReleaseID);
		JSONObject jo = DefaultResultUtil.getModificationResult(retCode + "");
		return jo;
	}

	public static JSONObject getBaseMaterialDetailByID(String baseMaterialID) {
		// TODO Auto-generated method stub
		BaseMaterialDto dto = BaseMaterialDao
				.getBaseMaterialDetailByID(baseMaterialID);
		return JSONObject.fromObject(dto);
	}
	public static JSONObject getMaterialReleaseByID(String materialReleaseID) {
		// TODO Auto-generated method stub
		MaterialRealseDto dto = BaseMaterialDao.getMaterialReleaseByID(materialReleaseID);
		return JSONObject.fromObject(dto);
	}

	public static JSONObject saveBaseMaterialItem(String data) {
		// TODO Auto-generated method stub
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.Base_Material);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		String retStr = "";
		BaseMaterialItemReqDto dto = (BaseMaterialItemReqDto) JSONObject
				.toBean(JSONObject.fromObject(data),
						BaseMaterialItemReqDto.class);
		retStr = BaseMaterialDao.saveBaseMaterialItem(dto.getBaseMaterialItemID(),
				dto.getBaseMaterialID(), dto.getItemCode(),dto.getLabData(),dto.getDataUnit(),dto.getImpDate(),dto.getValidDays());
		if ("".equals(retStr)) {
			retStr = "true";
		}
		
		JSONObject result = DefaultResultUtil.getModificationResult(retStr);
		return result;
	}
	
	public static JSONObject saveMaterialReleaseItem(String data) {
		// TODO Auto-generated method stub
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.Material_Patch);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		String retStr = "";
		MaterialRealseItemDto dto = (MaterialRealseItemDto) JSONObject
				.toBean(JSONObject.fromObject(data),
						MaterialRealseItemDto.class);
		retStr = BaseMaterialDao.SaveMaterialReleaseItem(dto.getMaterialReleaseItemID(), dto.getMaterialReleaseID(), dto.getItemCode(), dto.getLabData(), dto.getDataUnit());
		if ("".equals(retStr)) {
			retStr = "true";
		}
		
		JSONObject result = DefaultResultUtil.getModificationResult(retStr);
		return result;
	}

	public static JSONObject delBaseMaterialItem(String baseMaterialItemID) {
		// TODO Auto-generated method stub
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.Base_Material);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		boolean retCode = BaseMaterialDao
				.delBaseMaterialItem(baseMaterialItemID);
		JSONObject jo = DefaultResultUtil.getModificationResult(retCode + "");
		return jo;
	}
	public static JSONObject delMaterialReleaseItem(String materialReleaseItemID,String  materialReleaseID) {
		// TODO Auto-generated method stub
		String permission = AuthorityDao
				.getOperateLimit(ConstantStr.Material_Patch);
		if (!permission.equals("1")) {
			JSONObject result = DefaultResultUtil
					.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
			return result;
		}
		String retCode = BaseMaterialDao.delMaterialReleaseItem(materialReleaseItemID, materialReleaseID);
				
			if("".equals(retCode)){
				retCode="true";
			}
		JSONObject jo = DefaultResultUtil.getModificationResult(retCode);
		System.out.println("code "+jo);
		return jo;
	}

}
