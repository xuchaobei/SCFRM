package cn.gov.scciq.bussiness.sampleRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.DefaultResultUtil;

public class SampleRegisterService {

	public static JSONObject getDeclInfoForProcess(String data, int draw, int start, int length) {
		String orderWord = "DeclDate";
		String orderDirection = "ASC";
		DeclInfoReqDto reqDto = (DeclInfoReqDto)JSONObject.toBean(JSONObject.fromObject(data), DeclInfoReqDto.class);
		Map<Integer, Object> rsMap = SampleRegisterDao.getDeclInfoForProcess(reqDto.getDeclNo(), ContextUtil.getOrgCode(),
				ContextUtil.getDeptCode(), ContextUtil.getOperatorCode(),"样品登记", reqDto.getFinishFlg(), start, length, orderWord, orderDirection);
		@SuppressWarnings("unchecked")
		List<DeclInfoDto> list =(List<DeclInfoDto>) rsMap.get(2);
		List<DeclInfoResDto> resList = new ArrayList<DeclInfoResDto>();
		DeclInfoResDto resDto = null;
		for(DeclInfoDto dto : list){
			resDto = new DeclInfoResDto(dto);
			String status = SampleRegisterDao.getProcessStatusForDecl(dto.getDeclNo(), "样品登记");
			resDto.setProcessStatus(status);
			resList.add(resDto);
		}
		int recordsTotal = (Integer) rsMap.get(1);
		JSONArray ja = JSONArray.fromObject(resList);
		JSONObject result = DefaultResultUtil.getDefaultTableResult(draw,
				recordsTotal, recordsTotal, ja);
		return result;
	}

	public static JSONObject getDeclProduct(String data) {
		// TODO Auto-generated method stub
		List<DeclProductDto> list = SampleRegisterDao.getDeclProduct(data);
		return DefaultResultUtil.getDefaultResult(list);
	}

	public static JSONObject getDeclProductItem(String data) {
		// TODO Auto-generated method stub
		JSONObject jo = JSONObject.fromObject(data);
		String declProductDetailID = jo.getString("declProductDetailID");
		int showSamplingItemFlg = jo.getInt("showSamplingItemFlg");
		int showNotLabFlg = jo.getInt("showNotLabFlg");
		String orderWord = "DeclProductItemID";
		String orderDirection = "ASC";
		
		Map<Integer, Object> rsMap = SampleRegisterDao.getDeclProductItem(declProductDetailID,  true, false, 0, 0, orderWord, orderDirection);
		JSONObject result = DefaultResultUtil.getDefaultResult(rsMap.get(2));
		return result;
	}

	
	public static JSONObject getLabSampleByProduct(String data) {
		// TODO Auto-generated method stub
		List<LabSampleDto> list = SampleRegisterDao.getLabSampleByProduct(data);
		return DefaultResultUtil.getDefaultResult(list);
	}

	public static JSONObject getLabSampleItemByProduct(String data) {
		// TODO Auto-generated method stub
		List<LabSampleItemDto> list = SampleRegisterDao.getLabSampleItemByProduct(data);
		return DefaultResultUtil.getDefaultResult(list);
	}

	public static JSONObject cancelCurrentProcess(String data) {
		String rs = SampleRegisterDao.cancelCurrentProcess(data, "检验员接单", ContextUtil.getOrgCode(), ContextUtil.getDeptCode(),
				ContextUtil.getOperatorCode());
		return DefaultResultUtil.getModificationResult(rs);
	}
	
	public static JSONObject checkIfProcessOperate(String data){
		String rs = SampleRegisterDao.checkIfProcessOperate("样品登记", data);
		return DefaultResultUtil.getModificationResult(rs);
	}
	
	public static JSONObject checkItemIfSampling(String data){
		String rs = SampleRegisterDao.checkItemIfSampling(data);
		return DefaultResultUtil.getModificationResult(rs);
	}
	
	public static JSONObject getLabApply(String data){
		JSONObject jo = JSONObject.fromObject(data);
		String declNo = jo.getString("declNo");
		String declProductDetailID = jo.getString("declProductDetailID");
		LabApplyDto rs = SampleRegisterDao.getLabApply(declNo, ContextUtil.getOrgCode(),
				ContextUtil.getDeptCode(), ContextUtil.getOperatorCode(), declProductDetailID);
		return DefaultResultUtil.getDefaultResult(rs);
	}
	
	public static JSONObject getLabSampleInfoBySampleID(String data){
		JSONObject jo = JSONObject.fromObject(data);
		String sampleID = jo.getString("sampleID");
		String declProductDetailID = jo.getString("declProductDetailID");
		String productCode = jo.getString("productCode");
		
		LabSampleInfoDto rs = SampleRegisterDao.getLabSampleInfoBySampleID(sampleID, declProductDetailID,productCode,
				ContextUtil.getOrgCode(),ContextUtil.getDeptCode());
		return DefaultResultUtil.getDefaultResult(rs);
	}	
	
	public static JSONObject getLabItemMatched(String data) {
		// TODO Auto-generated method stub
		List<LabItemPlanDto> list = SampleRegisterDao.getLabItemMatched(data);
		return DefaultResultUtil.getDefaultResult(list);
	}
	
}
