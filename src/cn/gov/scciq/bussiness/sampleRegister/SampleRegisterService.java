package cn.gov.scciq.bussiness.sampleRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.util.ConstantStr;
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
		
		Map<Integer, Object> rsMap = SampleRegisterDao.getDeclProductItem(declProductDetailID,  showSamplingItemFlg, showNotLabFlg, 0, 0, orderWord, orderDirection);
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

	public static JSONObject delLabItemMatchedForNewSample(String data) {
		String rs = SampleRegisterDao.delLabItemMatchedForNewSample(data);
		return DefaultResultUtil.getModificationResult(rs);
	}

	public static JSONObject saveLabItemMatchedManual(String data) {
		// TODO Auto-generated method stub
		JSONObject jo = JSONObject.fromObject(data);
		String itemCode = jo.getString("itemCode");
		String declProductDetailID = jo.getString("declProductDetailID");
		String declProductItemID = jo.getString("declProductItemID");
		String rs = SampleRegisterDao.saveLabItemMatchedManual(itemCode,declProductDetailID,declProductItemID,ContextUtil.getOrgCode());
		if(rs.equals("")){
			rs ="true";
		}
		return DefaultResultUtil.getModificationResult(rs);
	}
	

	public static JSONObject saveLabApplyInfo(String data) {
		// TODO Auto-generated method stub
		JSONObject jo = JSONObject.fromObject(data);
		String declNo = jo.getString("declNo");
		String applyKind = jo.getString("applyKind");
		String sampleKind = jo.getString("sampleKind");
		String applyDept = jo.getString("applyDept");
		String appliant = jo.getString("appliant");
		String sampleState = jo.getString("sampleState");
		String sampleDisposal = jo.getString("sampleDisposal");
		String samplePreservation = jo.getString("samplePreservation");
		String remarks = jo.getString("remarks");
		String rs = SampleRegisterDao.saveLabApplyInfo(declNo, applyKind, sampleKind, applyDept, appliant, sampleState, sampleDisposal, samplePreservation, remarks);
		if(ConstantStr.SAVE_ERROR_MSG.equals(rs)){
			rs ="false";
		}
		return DefaultResultUtil.getModificationResult(rs);
	}

	public static JSONObject saveLabDefaultData(String data) {
		// TODO Auto-generated method stub
		JSONObject jo = JSONObject.fromObject(data);
		String declProductDetailID = jo.getString("declProductDetailID");
		String applyKind = jo.getString("applyKind");
		String sampleKind = jo.getString("sampleKind");
		String applyDept = jo.getString("applyDept");
		String appliant = jo.getString("appliant");
		String sampleState = jo.getString("sampleState");
		String sampleDisposal = jo.getString("sampleDisposal");
		String samplePreservation = jo.getString("samplePreservation");
		String rs = SampleRegisterDao.saveLabDefaultData(ContextUtil.getOrgCode(), ContextUtil.getDeptCode(), ContextUtil.getOperatorCode(), declProductDetailID, applyKind, sampleKind, applyDept, appliant, sampleState, sampleDisposal, samplePreservation);
		if(ConstantStr.SAVE_ERROR_MSG.equals(rs)){
			rs ="false";
		}
		return DefaultResultUtil.getModificationResult(rs);
	}
	
	public static JSONObject saveLabSample(String data){
		JSONObject jo = JSONObject.fromObject(data);
		String sampleID = jo.getString("sampleID");
		String labApplyID = jo.getString("labApplyID");
		String declProductDetailID = jo.getString("declProductDetailID");
		String sampleName = jo.getString("sampleName");
		String sampleCount = jo.getString("sampleCount");
		String countUnit = jo.getString("countUnit");
		String sampleRemarks = jo.getString("sampleRemarks");
		String copyCount = jo.getString("copyCount");
		String rs = SampleRegisterDao.saveLabSample(sampleID, labApplyID, declProductDetailID, 
				sampleName, sampleCount, countUnit, sampleRemarks, copyCount);
		if(ConstantStr.SAVE_ERROR_MSG.equals(rs)){
			rs ="false";
		}
		return DefaultResultUtil.getModificationResult(rs);
	
	}
	
	public static JSONObject saveLabSampleItem(String data){

		JSONObject jo = JSONObject.fromObject(data);
		String declProductDetailID = jo.getString("declProductDetailID");
		String sampleItemID = jo.getString("sampleItemID");
		String sampleID = jo.getString("sampleID");
		String itemCode = jo.getString("itemCode");
		String lrpItemNo = jo.getString("lrpItemNo");
		String lrpItemName = jo.getString("lrpItemName");
		String lrpTestStd = jo.getString("lrpTestStd");
		String labFlg = jo.getString("labFlg");
		String rs = SampleRegisterDao.saveLabSampleItem(declProductDetailID, sampleItemID, sampleID, 
				itemCode, lrpItemNo, lrpItemName, lrpTestStd, labFlg);
		if(ConstantStr.SAVE_ERROR_MSG.equals(rs)){
			rs ="false";
		}
		return DefaultResultUtil.getModificationResult(rs);
	
	
	}

	public static JSONObject getLabItemByQuery(String data) {
		JSONObject jo = JSONObject.fromObject(data);
		String itemName = jo.getString("itemName");
		String labDeptName = jo.getString("labDeptName");
		int rowIndex = 0;
		int pageSize = 0;
		String orderWord = "ItemName";
		String orderDirec = "ASC";
		List<LabItemDto> list = SampleRegisterDao.getLabItemByQuery(itemName, labDeptName,
				rowIndex,pageSize, orderWord, orderDirec);
		return DefaultResultUtil.getDefaultResult(list);
	}

	public static JSONObject saveLabItemMatched(String data) {
		// TODO Auto-generated method stub
		JSONObject jo = JSONObject.fromObject(data);
		String declProductDetailID = jo.getString("declProductDetailID");
		String itemCode = jo.getString("itemCode");
		String lrpItemID = jo.getString("lrpItemID");
		String lrpItemName = jo.getString("lrpItemName");
		String lrpItemTestStd = jo.getString("lrpItemTestStd");
		String labDeptName = jo.getString("labDeptName");
		String declProductItemID = jo.getString("declProductItemID");
		String rs = SampleRegisterDao.saveLabItemMatched(declProductDetailID, itemCode, lrpItemID, lrpItemName, lrpItemTestStd, labDeptName, declProductItemID);
		
		return DefaultResultUtil.getModificationResult(rs);
	}
	
	public static JSONObject getItemSubByDeclItem(String data){
		JSONObject jo = JSONObject.fromObject(data);
		String itemCode = jo.getString("itemCode");
		String productCode = jo.getString("productCode");
		List<ItemSubDto> list = SampleRegisterDao.getItemSubByDeclItem(itemCode, productCode, ContextUtil.getOrgCode());
		return DefaultResultUtil.getDefaultResult(list);
	}
	
	public static JSONObject getItemSubMatchedForDeclItem(String data){
		JSONObject jo = JSONObject.fromObject(data);
		String itemCode = jo.getString("itemCode");
		String declProductItemID = jo.getString("declProductItemID");
		List<ItemSubMatchedDto> list = SampleRegisterDao.getItemSubMatchedForDeclItem(itemCode, declProductItemID);
		return DefaultResultUtil.getDefaultResult(list);	
	}
	
	public static JSONObject saveLabItemMatchedForSubitem(String data){
		JSONObject jo = JSONObject.fromObject(data);
		String itemCode = jo.getString("itemCode");
		String productCode = jo.getString("productCode");
		String lrpItemNo = jo.getString("lrpItemNo");
		String declProductDetailID = jo.getString("declProductDetailID");
		String declProductItemID = jo.getString("declProductItemID");
		String rs = SampleRegisterDao.saveLabItemMatchedForSubitem(itemCode, productCode, ContextUtil.getOrgCode(), lrpItemNo, declProductDetailID, declProductItemID);
		if(rs.equals("")){
			rs = "true";
		}
		return DefaultResultUtil.getModificationResult(rs);
	}
	
	public static JSONObject delItemSubMatchedForDeclItem(String data){
		String rs = SampleRegisterDao.delItemSubMatchedForDeclItem(data);
		return DefaultResultUtil.getModificationResult(rs);
	}
	
	public static JSONObject sampleRegAuto(String data){
		String rs = SampleRegisterDao.sampleRegAuto(data, ContextUtil.getOrgCode(), ContextUtil.getDeptCode(),ContextUtil.getOperatorCode());
		return DefaultResultUtil.getModificationResult(rs);
	}
	
	public static JSONObject divideLabSample(String data){
		JSONObject jo = JSONObject.fromObject(data);
		String labSampleID = jo.getString("labSampleID");
		String dividedSampleNum = jo.getString("dividedSampleNum");
		String rs = SampleRegisterDao.divideLabSample(labSampleID, dividedSampleNum);
		return DefaultResultUtil.getModificationResult(rs);
	}
	
	public static JSONObject delLabSample(String data){
		String rs = SampleRegisterDao.delLabSample(data);
		return DefaultResultUtil.getModificationResult(rs);
	}
	
	public static JSONObject delLabSampleItem(String data){
		String rs = SampleRegisterDao.delLabSampleItem(data);
		return DefaultResultUtil.getModificationResult(rs);
	}
}
