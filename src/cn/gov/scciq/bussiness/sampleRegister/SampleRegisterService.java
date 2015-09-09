package cn.gov.scciq.bussiness.sampleRegister;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import cn.gov.scciq.bussiness.sampleRegister.lab.SendDeclGoodsDto;
import cn.gov.scciq.bussiness.sampleRegister.lab.SendDeclInfoDto;
import cn.gov.scciq.bussiness.sampleRegister.lab.SendLabApplyInfoDto;
import cn.gov.scciq.bussiness.sampleRegister.lab.SendLabSampleDto;
import cn.gov.scciq.bussiness.sampleRegister.lab.SendLabSampleItemDto;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.DefaultResultUtil;

public class SampleRegisterService {

	private static final Log log = LogFactory.getLog(SampleRegisterService.class);
	
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
	
	public static JSONObject getLabApplyByDeclNo(String data) {
		// TODO Auto-generated method stub
		List<LabApplyPrintDto> list = SampleRegisterDao.getLabApplyByDeclNo(data);
		return DefaultResultUtil.getDefaultResult(list);
	}
	
	public static JSONObject checkIfCanSubmitLab(String data) {
		Map<String,Object> rsMap = SampleRegisterDao.checkIfCanSubmitLab(data);
		return DefaultResultUtil.getComplexResult(rsMap);
		
	}
	

	@SuppressWarnings("unchecked")
	public static JSONObject sampleReg(String data) {
		// TODO Auto-generated method stub
		JSONObject jo = JSONObject.fromObject(data);
		String declNo = jo.getString("declNo");
		String labApplyID = jo.getString("labApplyID");
		String orgCode = ContextUtil.getOrgCode();
		String deptCode = ContextUtil.getDeptCode();
		String operatorCode = ContextUtil.getOperatorCode();
		Map<String, Object> rsMap = SampleRegisterDao.sampleReg(orgCode, deptCode, operatorCode, declNo, labApplyID);
		List<SendLabApplyInfoDto> labApplyList = (ArrayList<SendLabApplyInfoDto>)rsMap.get("labApply");  //实际情况中，应该最多只有一条记录？
		if(labApplyList.size() ==0 ){
			return submitLabSuccess(declNo, labApplyID);
		}
		List<SendLabSampleDto> labSampleList = (ArrayList<SendLabSampleDto>)rsMap.get("labSample");    //
		List<SendLabSampleItemDto> labItemList = (ArrayList<SendLabSampleItemDto>)rsMap.get("labItem");
		List<SendDeclInfoDto> declInfoList = SampleRegisterDao.getDeclInfoFromCIQ(declNo);           //应该最多只有一条记录？
		List<SendDeclGoodsDto> declGoodsList = SampleRegisterDao.getDeclGoodsInfoFromCIQ(declNo, "1"); //应该最多只有一条记录？
		
		creatLabApplyXml(declNo, labApplyID, labApplyList.get(0), declInfoList.get(0), declGoodsList.get(0), labSampleList, labItemList);
		return DefaultResultUtil.getComplexResult(rs);
	}
	
	
	public static JSONObject submitLabSuccess(String declNo, String labApplyID) {
		// TODO Auto-generated method stub
	
		String orgCode = ContextUtil.getOrgCode();
		String deptCode = ContextUtil.getDeptCode();
		String operatorCode = ContextUtil.getOperatorCode();
		String rs = SampleRegisterDao.submitLabSuccess(declNo, labApplyID, orgCode, deptCode, operatorCode);
		return DefaultResultUtil.getComplexResult(rs);
	}
	
	public static JSONObject getDeclInfoFromCIQ(String data) {
		// TODO Auto-generated method stub		
		List<SendDeclInfoDto> list = SampleRegisterDao.getDeclInfoFromCIQ(data);
		return DefaultResultUtil.getDefaultResult(list);
	}
	
	public static JSONObject getDeclGoodsInfoFromCIQ(String data) {
		// TODO Auto-generated method stub	
		List<SendDeclGoodsDto> list = SampleRegisterDao.getDeclGoodsInfoFromCIQ(data, "1");
		return DefaultResultUtil.getDefaultResult(list);
	}
	
	private static String rs = "提交到LMIS完成";
	
	public static void creatLabApplyXml(String declNo, String labApplyID, SendLabApplyInfoDto labApplyInfoDto, SendDeclInfoDto declInfoDto, 
			SendDeclGoodsDto declGoodsDto, List<SendLabSampleDto> labSampleList, List<SendLabSampleItemDto> labItemList)  {
		Element dataRoot;
		Element root;
		Document doc;
		Element FuncCode,BizID, BizNo,BizSource, BizTypeID, BizTypeName, BizTypeCode, BizOriginalNo, SampleTypeID, ProductName, ProductNameEN, ProductCount, ProductCountUnit, DeliveryDeptID, DeliveryUserID, DeliveryTelephone, DeliveryMobile, ProductCondition, ProductPreservation, ProductDisposition, ReportVersion, ReportDelivery, ReportUse, TestRemark, BizRemark, DueDate, Retest, RetestBizNo, RetestSN, AcceptDeptID, DraftDeptID, CIQNo, SampleTypeName, 
		AcceptUserID, AcceptUserName, AcceptDate, DomesticOrigin, ExportCountry, biz_Fee, biz_CustomerList, BizTypeNo,
		BIZTYPENATURE, CALCULATEFEEWAY, AcceptDeptName, DeliveryDeptName;
	
			
			dataRoot = new Element("DataRoot");
			doc = new Document(dataRoot);
			root = new Element("biz_Main");
			
			FuncCode=new Element("FuncCode");
            
            BIZTYPENATURE = new Element("BIZTYPENATURE");
            
            CALCULATEFEEWAY = new Element("CALCULATEFEEWAY");
            
			BizID = new Element("BizID"); //

			BizNo = new Element("BizNo"); //
			
			BizSource=new Element("BizSource");

			BizTypeID = new Element("BizTypeID");
			BizTypeID.setText(labApplyInfoDto.getApplyKindID());

			BizTypeName = new Element("BizTypeName");
			BizTypeName.setText(labApplyInfoDto.getApplyKind());

			BizTypeCode = new Element("BizTypeCode");
			BizTypeCode.setText(labApplyInfoDto.getApplyKindCode());

			BizTypeNo = new Element("BizTypeNo"); // 数据库表中没有

			BizOriginalNo = new Element("BizOriginalNo");
			BizOriginalNo.setText(labApplyInfoDto.getDeclNo()); // ?原始编号

			SampleTypeID = new Element("SampleTypeID");

			ProductName = new Element("ProductName");
			ProductName.setText(labApplyInfoDto.getSampleName());

			ProductNameEN = new Element("ProductNameEN");

			ProductCount = new Element("ProductCount");
			ProductCount.setText(labApplyInfoDto.getSampleCount());

			ProductCountUnit = new Element("ProductCountUnit");
			ProductCountUnit.setText(labApplyInfoDto.getCountUnit());

			DeliveryDeptID = new Element("DeliveryDeptID");
			DeliveryDeptID.setText(labApplyInfoDto.getApplyDeptID());

			DeliveryUserID = new Element("DeliveryUserID");
			DeliveryUserID.setText(labApplyInfoDto.getAppliantID());

			DeliveryTelephone = new Element("DeliveryTelephone");//

			DeliveryMobile = new Element("DeliveryMobile");//

			ProductCondition = new Element("ProductCondition");
			ProductCondition.setText(labApplyInfoDto.getSampleState());

			ProductPreservation = new Element("ProductPreservation");
			ProductPreservation.setText(labApplyInfoDto.getSamplePreservation());

			ProductDisposition = new Element("ProductDisposition");
			ProductDisposition.setText(labApplyInfoDto.getSampleDisposal());

			ReportVersion = new Element("ReportVersion");//

			ReportDelivery = new Element("ReportDelivery");//

			ReportUse = new Element("ReportUse");//

			TestRemark = new Element("TestRemark");

			BizRemark = new Element("BizRemark");//

			DueDate = new Element("DueDate");

			Retest = new Element("Retest");//
			Retest.setText("0");

			RetestBizNo = new Element("RetestBizNo");//

			RetestSN = new Element("RetestSN");//

			AcceptDeptName = new Element("AcceptDeptName");
			
			DeliveryDeptName = new Element("DeliveryDeptName");
			
			AcceptDeptID = new Element("AcceptDeptID");

			DraftDeptID = new Element("DraftDeptID");

			CIQNo = new Element("CIQNo");
			CIQNo.setText(labApplyInfoDto.getDeclNo());

			SampleTypeName = new Element("SampleTypeName");

			AcceptUserID = new Element("AcceptUserID");// 送检员id和受理员id区别,受理人员都不用管
			
			AcceptUserName = new Element("AcceptUserName");	
			
			AcceptDate = new Element("AcceptDate");			

			DomesticOrigin = new Element("DomesticOrigin");

			ExportCountry = new Element("ExportCountry");
			
			ExportCountry.setText("中国");

			biz_Fee = new Element("biz_Fee");
			
			Element Biz_ID, TotalItemFee, TotalCIQFee, TotalOtherFee, TotalFee, FinalFee, ChargeFee, InvoiceTitle;
			
			Biz_ID = new Element("BizID");
			
			TotalItemFee = new Element("TotalItemFee");
			
			TotalCIQFee = new Element("TotalCIQFee");

			TotalOtherFee = new Element("TotalOtherFee");
			
			TotalFee = new Element("TotalFee");

			FinalFee = new Element("FinalFee");

			ChargeFee = new Element("ChargeFee");
			
			InvoiceTitle = new Element("InvoiceTitle");			
		
			biz_Fee.addContent(Biz_ID);
			biz_Fee.addContent(TotalItemFee);
			biz_Fee.addContent(TotalCIQFee);
			biz_Fee.addContent(TotalOtherFee);
			biz_Fee.addContent(TotalFee);
			biz_Fee.addContent(FinalFee);
			biz_Fee.addContent(ChargeFee);
			biz_Fee.addContent(InvoiceTitle);

			biz_CustomerList = new Element("biz_CustomerList");
			Element biz_Customer, BizCustomerType, BizCustomerTypeName, Invoice_Title, SortOrder, CustomerID, CustomerName, CustomerNameEN, CustomerAddress, ZipCode, ContactName, ContactTelphone, ContactMobile, ContactFax;
			biz_Customer = new Element("biz_Customer");
			BizCustomerType = new Element("BizCustomerType");
			BizCustomerType.setText("2"); // 高斯要求
			BizCustomerTypeName = new Element("BizCustomerTypeName");
			BizCustomerTypeName.setText("生产企业");
			Invoice_Title = new Element("InvoiceTitle");
			SortOrder = new Element("SortOrder");
			CustomerID = new Element("CustomerID");
			CustomerName = new Element("CustomerName");
			CustomerName.setText(labApplyInfoDto.getEntName());
			CustomerNameEN = new Element("CustomerNameEN");
			CustomerAddress = new Element("CustomerAddress");
			ZipCode = new Element("ZipCode");
			ContactName = new Element("ContactName");
			ContactTelphone = new Element("ContactTelphone");
			ContactMobile = new Element("ContactMobile");
			ContactFax = new Element("ContactFax");
			biz_Customer.addContent(BizCustomerType);
			biz_Customer.addContent(BizCustomerTypeName);
			biz_Customer.addContent(Invoice_Title);
			biz_Customer.addContent(SortOrder);
			biz_Customer.addContent(CustomerID);
			biz_Customer.addContent(CustomerName);
			biz_Customer.addContent(CustomerNameEN);
			biz_Customer.addContent(CustomerAddress);
			biz_Customer.addContent(ZipCode);
			biz_Customer.addContent(ContactName);
			biz_Customer.addContent(ContactTelphone);
			biz_Customer.addContent(ContactMobile);
			biz_Customer.addContent(ContactFax);
			biz_CustomerList.addContent(biz_Customer);
			
			Element CIQDetail,biz_FeeCIQList;
			CIQDetail = new Element("CIQDetail");
			biz_FeeCIQList=new Element("biz_FeeCIQList");
			if(declInfoDto!=null && declGoodsDto!=null){
				Element CONSIGNOR_CNAME, CONSIGNEE_CNAME, TRANS_TYPE, TRANS_MEANS_CODE, ENTRY_PORT, DESP_Port, CONTRACT_NO, CARRIER_NOTE_NO, GOODS_PLACE, ARRI_DATE, UNLOAD_DATE, DECL_NO, HS_CODE, GOODS_CNAME, GOODS_ENAME, GOODS_MODEL, MARK_NO, WEIGHT, WEIGHT_UNIT_CODE, QTY, 
				QTY_UNIT_CODE, INSP_ORG_CODE, INSP_DEPT_1, CCY, DESP_DATE, GOODS_VALUES, VALUES_USD, ARRI_PORT_CODE, VALUES_RMB, DECL_REG_NO, DECL_DATE, PACK_NUMBER, PACK_TYPE_CODE, CIQ_CODE, CONSIGNOR_CODE, PROD_REG_NO, CONSIGNEE_CODE, TRADE_COUNTRY_CODE, ORIGIN_PLACE_CODE, ORIGIN_COUNTRY_CODE, 
				TRANS_TYPE_CODE, DESP_PORT_CODE, ENTRY_PORT_CODE,VIA_PORT_CODE, GOODS_NO, ENT_CNAME, Ent_EName, CONTACTOR, TELEPHONE, PROD_REG, ccy_name, WEIGHT_UNIT, QTY_UNIT, ORIGIN_PLACE, ORIGIN_COUNTRY, TRADE_COUNTRY, PACK_TYPE, ARRI_PORT;
				
				CONSIGNOR_CNAME = new Element("CONSIGNOR_CNAME");
				CONSIGNOR_CNAME.setText(declInfoDto.getCONSIGNOR_CNAME());
				CONSIGNEE_CNAME = new Element("CONSIGNEE_CNAME");
				CONSIGNEE_CNAME.setText(declInfoDto.getCONSIGNEE_CNAME());
				TRANS_TYPE = new Element("TRANS_TYPE");
				TRANS_TYPE.setText(declInfoDto.getTRANS_TYPE_CODE());
				TRANS_MEANS_CODE = new Element("TRANS_MEANS_CODE");
				TRANS_MEANS_CODE.setText(declInfoDto.getTRANS_MEANS_CODE());
				ENTRY_PORT = new Element("ENTRY_PORT");
				ENTRY_PORT.setText(declInfoDto.getENTRY_PORT_CODE());
				DESP_Port = new Element("DESP_Port");
				DESP_Port.setText(declInfoDto.getDESP_PORT_CODE());
				CONTRACT_NO = new Element("CONTRACT_NO");
				CONTRACT_NO.setText(declInfoDto.getCONTRACT_NO());
				CARRIER_NOTE_NO = new Element("CARRIER_NOTE_NO");
				CARRIER_NOTE_NO.setText(declInfoDto.getCARRIER_NOTE_NO());
				GOODS_PLACE = new Element("GOODS_PLACE");
				GOODS_PLACE.setText(declInfoDto.getGOODS_PLACE());
				ARRI_DATE = new Element("ARRI_DATE");
				ARRI_DATE.setText(declInfoDto.getARRI_DATE());
				UNLOAD_DATE = new Element("UNLOAD_DATE");
				UNLOAD_DATE.setText(declInfoDto.getUNLOAD_DATE());
				DECL_NO = new Element("DECL_NO");
				DECL_NO.setText(declInfoDto.getDECL_NO());
				HS_CODE = new Element("HS_CODE");
				HS_CODE.setText(declGoodsDto.getGOODS_NO());
				GOODS_CNAME = new Element("GOODS_CNAME");
				GOODS_CNAME.setText(declGoodsDto.getGOODS_CNAME());
				GOODS_ENAME = new Element("GOODS_ENAME");
				GOODS_ENAME.setText(declGoodsDto.getGOODS_ENAME());
				GOODS_MODEL = new Element("GOODS_MODEL");
				GOODS_MODEL.setText(declGoodsDto.getGOODS_MODEL());
				MARK_NO = new Element("MARK_NO");
				MARK_NO.setText(declInfoDto.getMARK_NO());
				WEIGHT = new Element("WEIGHT");
				WEIGHT.setText(declGoodsDto.getWEIGHT());
				WEIGHT_UNIT_CODE = new Element("WEIGHT_UNIT_CODE");
				WEIGHT_UNIT_CODE.setText(declGoodsDto.getWEIGHT_UNIT_CODE());
				QTY = new Element("QTY");
				QTY.setText(declGoodsDto.getQTY());
				QTY_UNIT_CODE = new Element("QTY_UNIT_CODE");
				QTY_UNIT_CODE.setText(declGoodsDto.getQTY());
				INSP_ORG_CODE = new Element("INSP_ORG_CODE");
				INSP_ORG_CODE.setText(declInfoDto.getINSP_ORG_CODE());
				INSP_DEPT_1 = new Element("INSP_DEPT_1");
				INSP_DEPT_1.setText(declInfoDto.getINSP_DEPT_1());
				CCY = new Element("CCY");
				CCY.setText(declGoodsDto.getCCY());
				DESP_DATE = new Element("DESP_DATE");
				DESP_DATE.setText(declInfoDto.getDESP_DATE());
				GOODS_VALUES = new Element("GOODS_VALUES");
				GOODS_VALUES.setText(declGoodsDto.getGOODS_VALUES());
				VALUES_USD = new Element("VALUES_USD");
				VALUES_USD.setText(declGoodsDto.getVALUES_USD());
				ARRI_PORT_CODE = new Element("ARRI_PORT_CODE");
				ARRI_PORT_CODE.setText(declInfoDto.getARRI_PORT_CODE());
				VALUES_RMB = new Element("VALUES_RMB");
				VALUES_RMB.setText(declGoodsDto.getVALUES_RMB());
				DECL_REG_NO = new Element("DECL_REG_NO");
				DECL_REG_NO.setText(declInfoDto.getDECL_REG_NO());
				DECL_DATE = new Element("DECL_DATE");
				DECL_DATE.setText(declInfoDto.getDECL_DATE());
				PACK_NUMBER = new Element("PACK_NUMBER");
				PACK_NUMBER.setText(declGoodsDto.getPACK_NUMBER());
				PACK_TYPE_CODE = new Element("PACK_TYPE_CODE");
				PACK_TYPE_CODE.setText(declGoodsDto.getPACK_TYPE_CODE());
				CIQ_CODE = new Element("CIQ_CODE");
				CIQ_CODE.setText(declGoodsDto.getCIQ_CODE());
				CONSIGNOR_CODE = new Element("CONSIGNOR_CODE");
				CONSIGNOR_CODE.setText(declInfoDto.getCONSIGNOR_CODE());
				PROD_REG_NO = new Element("PROD_REG_NO");
				PROD_REG_NO.setText(declInfoDto.getPROD_REG_NO());
				CONSIGNEE_CODE = new Element("CONSIGNEE_CODE");
				CONSIGNEE_CODE.setText(declInfoDto.getCONSIGNEE_CODE());
				TRADE_COUNTRY_CODE = new Element("TRADE_COUNTRY_CODE");
				TRADE_COUNTRY_CODE.setText(declInfoDto.getTRADE_COUNTRY_CODE());
				ORIGIN_PLACE_CODE = new Element("ORIGIN_PLACE_CODE");
				ORIGIN_PLACE_CODE.setText(declGoodsDto.getORIGIN_PLACE_CODE());
				ORIGIN_COUNTRY_CODE = new Element("ORIGIN_COUNTRY_CODE");
				ORIGIN_COUNTRY_CODE.setText(declGoodsDto.getORIGIN_COUNTRY_CODE());
				TRANS_TYPE_CODE = new Element("TRANS_TYPE_CODE");
				TRANS_TYPE_CODE.setText(declInfoDto.getTRANS_TYPE_CODE());
				DESP_PORT_CODE = new Element("DESP_PORT_CODE");
				DESP_PORT_CODE.setText(declInfoDto.getDESP_PORT_CODE());
				ENTRY_PORT_CODE = new Element("ENTRY_PORT_CODE");
				ENTRY_PORT_CODE.setText(declInfoDto.getENTRY_PORT_CODE());
				VIA_PORT_CODE = new Element("VIA_PORT_CODE");
				VIA_PORT_CODE.setText(declInfoDto.getVIA_PORT_CODE());
				GOODS_NO = new Element("GOODS_NO");
				GOODS_NO.setText("1");
				ENT_CNAME = new Element("ENT_CNAME");
				ENT_CNAME.setText(labApplyInfoDto.getEntName());
				Ent_EName = new Element("Ent_EName");
				CONTACTOR = new Element("CONTACTOR");
				TELEPHONE = new Element("TELEPHONE");
				PROD_REG = new Element("PROD_REG");
				ccy_name = new Element("ccy_name");
				ccy_name.setText(declGoodsDto.getCCY());
				WEIGHT_UNIT = new Element("WEIGHT_UNIT");
				WEIGHT_UNIT.setText(declGoodsDto.getWEIGHT_UNIT_CODE());
				QTY_UNIT = new Element("QTY_UNIT");
				QTY_UNIT.setText(declGoodsDto.getQTY_UNIT_CODE());
				ORIGIN_PLACE = new Element("ORIGIN_PLACE");
				ORIGIN_PLACE.setText(declGoodsDto.getORIGIN_PLACE_CODE());
				ORIGIN_COUNTRY = new Element("ORIGIN_COUNTRY");
				ORIGIN_COUNTRY.setText("中国");
				TRADE_COUNTRY = new Element("TRADE_COUNTRY");
				TRADE_COUNTRY.setText(labApplyInfoDto.getCountryName());
				PACK_TYPE = new Element("PACK_TYPE");
				PACK_TYPE.setText(declGoodsDto.getPACK_TYPE_CODE());
				ARRI_PORT = new Element("ARRI_PORT");
				ARRI_PORT.setText(declInfoDto.getARRI_PORT_CODE());	
				
				CIQDetail.addContent(CONSIGNOR_CNAME);
				CIQDetail.addContent(CONSIGNEE_CNAME);
				CIQDetail.addContent(TRANS_TYPE);
				CIQDetail.addContent(TRANS_MEANS_CODE);
				CIQDetail.addContent(ENTRY_PORT);
				CIQDetail.addContent(DESP_Port);
				CIQDetail.addContent(CONTRACT_NO);
				CIQDetail.addContent(CARRIER_NOTE_NO);
				CIQDetail.addContent(GOODS_PLACE);
				CIQDetail.addContent(ARRI_DATE);
				CIQDetail.addContent(UNLOAD_DATE);
				CIQDetail.addContent(DECL_NO);
				CIQDetail.addContent(HS_CODE);
				CIQDetail.addContent(GOODS_CNAME);
				CIQDetail.addContent(GOODS_ENAME);
				CIQDetail.addContent(GOODS_MODEL);
				CIQDetail.addContent(MARK_NO);
				CIQDetail.addContent(WEIGHT);
				CIQDetail.addContent(WEIGHT_UNIT_CODE);
				CIQDetail.addContent(QTY);
				CIQDetail.addContent(QTY_UNIT_CODE);			
				CIQDetail.addContent(INSP_ORG_CODE);
				CIQDetail.addContent(INSP_DEPT_1);
				CIQDetail.addContent(CCY);
				CIQDetail.addContent(DESP_DATE);
				CIQDetail.addContent(GOODS_VALUES);
				CIQDetail.addContent(VALUES_USD);
				CIQDetail.addContent(ARRI_PORT_CODE);
				CIQDetail.addContent(VALUES_RMB);
				CIQDetail.addContent(DECL_REG_NO);
				CIQDetail.addContent(DECL_DATE);
				CIQDetail.addContent(PACK_NUMBER);
				CIQDetail.addContent(PACK_TYPE_CODE);
				CIQDetail.addContent(CIQ_CODE);
				CIQDetail.addContent(CONSIGNOR_CODE);
				CIQDetail.addContent(PROD_REG_NO);
				CIQDetail.addContent(CONSIGNEE_CODE);
				CIQDetail.addContent(TRADE_COUNTRY_CODE);
				CIQDetail.addContent(ORIGIN_PLACE_CODE);
				CIQDetail.addContent(ORIGIN_COUNTRY_CODE);			
				CIQDetail.addContent(TRANS_TYPE_CODE);
				CIQDetail.addContent(DESP_PORT_CODE);
				CIQDetail.addContent(ENTRY_PORT_CODE);
				CIQDetail.addContent(VIA_PORT_CODE);
				CIQDetail.addContent(GOODS_NO);
				CIQDetail.addContent(ENT_CNAME);
				CIQDetail.addContent(Ent_EName);
				CIQDetail.addContent(CONTACTOR);
				CIQDetail.addContent(TELEPHONE);
				CIQDetail.addContent(PROD_REG);
				CIQDetail.addContent(ccy_name);
				CIQDetail.addContent(WEIGHT_UNIT);
				CIQDetail.addContent(QTY_UNIT);
				CIQDetail.addContent(ORIGIN_PLACE);
				CIQDetail.addContent(ORIGIN_COUNTRY);
				CIQDetail.addContent(TRADE_COUNTRY);
				CIQDetail.addContent(PACK_TYPE);
				CIQDetail.addContent(ARRI_PORT);
			
				Element biz_FeeCIQ,FeeName,FeeMethod,FeeValue,FeeFlag;
				
				biz_FeeCIQ=new Element("biz_FeeCIQ");
				FeeName=new Element("FeeName");
				FeeMethod=new Element("FeeMethod");
				FeeValue=new Element("FeeValue");
				FeeFlag=new Element("FeeFlag");
				
				biz_FeeCIQ.addContent(FeeName);
				biz_FeeCIQ.addContent(FeeMethod);
				biz_FeeCIQ.addContent(FeeValue);
				biz_FeeCIQ.addContent(FeeFlag);
				biz_FeeCIQList.addContent(biz_FeeCIQ);
				
			}
			root.addContent(DeliveryDeptName);
			root.addContent(AcceptDeptName);
			/**业务性质*/
			root.addContent(BIZTYPENATURE);
			/**计费方式*/
			root.addContent(CALCULATEFEEWAY);
			
			root.addContent(FuncCode);
			root.addContent(BizID);
			root.addContent(BizNo);
			root.addContent(BizSource);
			root.addContent(BizTypeID);
			root.addContent(BizTypeName);
			root.addContent(BizTypeCode);
			root.addContent(BizTypeNo); // new
			root.addContent(BizOriginalNo);
			root.addContent(SampleTypeID);// new
			root.addContent(ProductName);
			root.addContent(ProductNameEN);
			root.addContent(ProductCount);
			root.addContent(ProductCountUnit);
			root.addContent(DeliveryDeptID);
			root.addContent(DeliveryUserID);
			root.addContent(DeliveryTelephone);
			root.addContent(DeliveryMobile);
			root.addContent(ProductCondition);
			root.addContent(ProductPreservation);
			root.addContent(ProductDisposition);
			root.addContent(ReportVersion);
			root.addContent(ReportDelivery);
			root.addContent(ReportUse);
			root.addContent(TestRemark);
			root.addContent(BizRemark);
			root.addContent(DueDate);
			root.addContent(Retest);
			root.addContent(RetestBizNo);
			root.addContent(RetestSN);
			root.addContent(AcceptDeptID);
			
			root.addContent(DraftDeptID);
			root.addContent(CIQNo);
			root.addContent(SampleTypeName);
			root.addContent(AcceptUserID);
			root.addContent(AcceptUserName);
			root.addContent(AcceptDate);
			root.addContent(DomesticOrigin);
			root.addContent(ExportCountry);
			root.addContent(biz_Fee);
			root.addContent(biz_CustomerList);			
			root.addContent(CIQDetail);
			root.addContent(biz_FeeCIQList);			
			dataRoot.addContent(root);

			org.jdom.output.Format format = org.jdom.output.Format
					.getPrettyFormat();
			format.setEncoding("utf-8");// 设置编码格式
			XMLOutputter xmlout = new XMLOutputter(format);
			String xmlStr = xmlout.outputString(doc);
            log.info("发送报检信息，报检号： "+ labApplyInfoDto.getDeclNo());
            log.info(xmlStr);
            generateXmlFile(xmlStr,"SendLMISApply");
			sendLabApply(xmlStr, declNo, labApplyID, labApplyInfoDto, labSampleList, labItemList);
	}

	public static void main(String[] args) {
		String xml = "agedwff";
		generateXmlFile(xml, "SendLMISApply");
	}
	
	
	/**
	 * 保存报文到本地文件
	 * @param xml
	 * @param fileName
	 */
	public static void generateXmlFile(String xml, String fileName) {
		String path = "C://sampleRegister";
		File dir = new File(path);
		if(!dir.exists()){
			dir.mkdirs();
		}
		path = path + "/"+fileName +".xml";
		File file = new File(path);
		try {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
				bw.write(xml);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e);
			log.error("保存报文到本地失败");
		} 
	}
	
	public static void sendLabApply(String xmlStr, String declNo, String labApplyID, 
			SendLabApplyInfoDto labApplyInfoDto,  List<SendLabSampleDto> labSampleList, List<SendLabSampleItemDto> labItemList){
		HttpClient httpClient = new DefaultHttpClient();
		try {
			StringEntity stringEntity = new StringEntity(xmlStr, "text/xml","utf-8"); // 第二三个参数合并为一个参数："text/plain;charset=\"UTF-8\"";
			stringEntity.setChunked(true);
			HttpPost httpPost = new HttpPost(
					ConstantStr.LRP_URL
							+ "/BizAccept/AcceptMain?apikey=bda11d91-7ade-4da1-855d-24adfe39d174&uid=1&uname=admin&utype=2");
			httpPost.setEntity(stringEntity);
			HttpResponse response = httpClient.execute(httpPost);
			log.info(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				try(InputStream instream = entity.getContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(instream, "utf-8"));)
				{
					StringBuilder temp = new StringBuilder();
					String line = reader.readLine();
					while (line != null) {
						temp.append(line).append("\r\n");
						line = reader.readLine();
					}
					String result = temp.toString();
					log.info("报验数据发送成功,返回结果是:"+result);
					generateXmlFile(result, "AcceptLMISApply");
					saveLabApplyNo(result, declNo, labApplyID, labApplyInfoDto, labSampleList,  labItemList);
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			rs = "报验数据发送成功失败，样品提交失败";
			log.error(e);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			rs = "报验数据发送成功失败，样品提交失败";
			log.error(e);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			rs = "报验数据发送成功失败，样品提交失败";
			log.error(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			rs = "报验数据发送成功失败，样品提交失败";
			log.error(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			rs = "报验数据发送成功失败，样品提交失败";
			log.error(e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

	}

	// 保存报检号
	public static void saveLabApplyNo(String result, String declNo, String labApplyID, SendLabApplyInfoDto labApplyInfoDto, List<SendLabSampleDto> labSampleList, List<SendLabSampleItemDto> labItemList) {
		try {
			Reader rr = new StringReader(result);
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder domBuilder = builderFactory.newDocumentBuilder();
			org.w3c.dom.Document document = (org.w3c.dom.Document) domBuilder
					.parse(new InputSource(rr));
			org.w3c.dom.Element root = document.getDocumentElement();
			Node bizID = root.getElementsByTagName("BizID").item(0);
			Node bizNo = root.getElementsByTagName("BizNo").item(0);
			Node bizTypeCode = root.getElementsByTagName("BizTypeCode").item(0);
			Node bizStatus = root.getElementsByTagName("BizStatus").item(0); // 仅在发送样品信息时使用
			String applyID = "";
			String applyNo = "";
			if (bizID.getChildNodes().getLength() != 0){
				applyID = bizID.getFirstChild().getNodeValue();
			    log.info("applyID="+applyID);
			}				
			if (bizNo.getChildNodes().getLength() != 0){
				applyNo = bizNo.getFirstChild().getNodeValue();
			    log.info("applyNo="+applyNo);
			}				
			Connection conn=DBPool.ds.getConnection();
			CallableStatement proc = null;
			try {
				proc = conn.prepareCall("{call Pro_UpdateLabApplyNo(?,?,?,?)}");
				proc.setString(1, labApplyID);
				proc.setString(2, applyNo);
				proc.setString(3, applyID);
				proc.registerOutParameter(4, java.sql.Types.VARCHAR);
				proc.execute();
				String tempRs = proc.getString(4);
				if (!"".equals(tempRs)) {
					log.info("保存报检号成功");
					getLabSample(declNo, labApplyID, applyID, applyNo, labSampleList,  labItemList);
				}else{
					rs = tempRs;
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(e);
			} finally {
				try {
					if (proc != null)
						proc.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}
			}
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			rs = "保存报检号失败，样品提交失败";
			log.error(e);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			rs = "保存报检号失败，样品提交失败";
			log.error(e);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			rs = "保存报检号失败，样品提交失败";
			log.error(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			rs = "保存报检号失败，样品提交失败";
			log.error(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			rs = "保存报检号失败，样品提交失败";
			log.error(e);
		}
	}
	
	/**读取样品数据**/
	public static void getLabSample(String declNo,String labApplyID,String applyID, String applyNo, List<SendLabSampleDto> labSampleList, List<SendLabSampleItemDto> labItemList) {
		log.info("正在读取样品信息...");

		ArrayList<Element> elements = new ArrayList<Element>();

		//用于保存每个样品下的项目集合
		List<SendLabSampleItemDto> sampleItemList = new ArrayList<SendLabSampleItemDto>();
		
		
		for (int i = 0; i < labSampleList.size(); i++) {
			String labSampleID = labSampleList.get(i).getLabSampleID();
			sampleItemList.clear();
			
			for (int j = 0; i < labItemList.size(); j++){
				SendLabSampleItemDto labItem = labItemList.get(j);
				if(labItem.getLabSampleID().equals(labSampleID)){
					sampleItemList.add(labItem);						
				}
			}
			
			elements.add(createSampleXml(labSampleList.get(i),
					sampleItemList)); // 生成biz_Sample子节点,样品子节点
			
		
		}
		log.info("样品信息读取完毕,正在生成样品XML文件...");
		createTotalXml(declNo, labApplyID, applyID, applyNo, elements);
	}

	public static void createTotalXml(String declNo,String labApplyID,String applyID,String applyNo, ArrayList<Element> biz_Samples) {
		Element dataRoot;
		Element root;
		Document doc;
		Element BizID,  biz_SampleList;

		dataRoot = new Element("DataRoot");
		root = new Element("biz_Main");
		doc = new Document(dataRoot);
		BizID = new Element("BizID");
		BizID.setText(applyID);
		/*BizTypeCode = new Element("BizTypeCode");
		BizTypeCode.setText(typeCode);
		BizStatus = new Element("BizStatus");
		BizStatus.setText(status);*/
		biz_SampleList = new Element("biz_SampleList");

		for (int i = 0; i < biz_Samples.size(); i++) {
			biz_SampleList.addContent(biz_Samples.get(i));
		}
		root.addContent(BizID);
		/*root.addContent(BizTypeCode);
		root.addContent(BizStatus);*/
		root.addContent(biz_SampleList);
		dataRoot.addContent(root);

		org.jdom.output.Format format = org.jdom.output.Format
				.getPrettyFormat();
		format.setEncoding("utf-8");// 设置编码格式
		XMLOutputter xmlout = new XMLOutputter(format);
		String xmlStr = xmlout.outputString(doc);
		log.info("发送样品检样数据");
		log.info(xmlStr);
		generateXmlFile(xmlStr,"SendLMISSample");
		sendLabSample(declNo, labApplyID,applyNo, xmlStr);
	}

	public static Element createSampleXml(SendLabSampleDto labSample,
			List<SendLabSampleItemDto> sampleItemList) {

		Element biz_Sample, SampleNo, SampleNumber, SampleName, SampleMark, SampleSpec, SampleQuantity, DeliveryNo, SampleDueDate, SampleRemark;
		Element biz_SampleItemList, biz_SampleItem, TestItemID, TestItemCode, ItemID, ItemCode, ItemName, DeptID,DeptName,
		        StandardNo,StandardName,ProductCategoryCode,ProductCategoryName,DetectionLimit,ResultUnit,Limit,TestPeriod,TestFee,TestGroupID,
		        TestGroupName,DefaultUserID,DefaultUserName,CMA,CNAS,ItemProperty,TestItemStatus,OutTest,PackFlag;
		Element biz_TestSampleList, biz_TestSample, Dept_ID, Dept_Name; // 注意最后两个节点名字和生成节点的名字不同
		

		biz_Sample = new Element("biz_Sample");

		SampleNo = new Element("SampleNo");

		SampleNumber = new Element("SampleNumber");

		SampleName = new Element("SampleName");
		SampleName.setText(labSample.getSampleName());

		SampleMark = new Element("SampleMark");
		SampleMark.setText(labSample.getSampleMarked());

		SampleSpec = new Element("SampleSpec");

		SampleQuantity = new Element("SampleQuantity");
		SampleQuantity.setText(labSample.getSampleCount());

		DeliveryNo = new Element("DeliveryNo");
		DeliveryNo.setText(labSample.getLabSampleID());

		SampleDueDate = new Element("SampleDueDate");

		SampleRemark = new Element("SampleRemark");
		SampleRemark.setText(labSample.getSampleRemarks());

		biz_SampleItemList = new Element("biz_SampleItemList");

		for (int j = 0; j < sampleItemList.size(); j++) {
			biz_SampleItem = new Element("biz_SampleItem");
			TestItemID = new Element("TestItemID"); // lrp生成
			TestItemID.setText(sampleItemList.get(j).getLrpItemNo());

			TestItemCode = new Element("TestItemCode");

			ItemID = new Element("ItemID");

			ItemCode = new Element("ItemCode");

			ItemName = new Element("ItemName");
			ItemName.setText(sampleItemList.get(j).getLrpItemName());
			
			StandardNo=new Element("StandardNo");
			
			StandardName=new Element("StandardName");
			
			ProductCategoryCode=new Element("ProductCategoryCode");
			
			ProductCategoryName=new Element("ProductCategoryName");

			DetectionLimit=new Element("DetectionLimit");
			
			ResultUnit=new Element("ResultUnit");
			
			Limit=new Element("Limit");
			
			TestPeriod=new Element("TestPeriod");
			
			TestFee=new Element("TestFee");

			CMA = new Element("CMA");
			CMA.setText("0");
			
			CNAS = new Element("CNAS");
			CNAS.setText("0");
			
			ItemProperty = new Element("ItemProperty");
			ItemProperty.setText("1");			
		    
			TestItemStatus = new Element("TestItemStatus");
			TestItemStatus.setText("1");
			
			OutTest = new Element("OutTest");
			OutTest.setText("0");
			
			PackFlag = new Element("PackFlag");
			PackFlag.setText("0");
			
			DeptID = new Element("DeptID");
			DeptID.setText(sampleItemList.get(j).getDeptID()); 		
			
			TestGroupID=new Element("TestGroupID");
		    
		    TestGroupName=new Element("TestGroupName");
		    
		    DefaultUserID=new Element("DefaultUserID");
		    
		    DefaultUserName=new Element("DefaultUserName");
		    
			DeptName=new Element("DeptName");
			DeptName.setText(sampleItemList.get(j).getLabDeptName());
			
			biz_SampleItem.addContent(TestItemID);
			biz_SampleItem.addContent(TestItemCode);
			biz_SampleItem.addContent(ItemID);
			biz_SampleItem.addContent(ItemCode);
			biz_SampleItem.addContent(ItemName);			
			biz_SampleItem.addContent(StandardNo);
			biz_SampleItem.addContent(StandardName);
			biz_SampleItem.addContent(ProductCategoryCode);
			biz_SampleItem.addContent(ProductCategoryName);
			biz_SampleItem.addContent(DetectionLimit);
			biz_SampleItem.addContent(ResultUnit);
			biz_SampleItem.addContent(Limit);
			biz_SampleItem.addContent(TestPeriod);
			biz_SampleItem.addContent(TestFee);
			biz_SampleItem.addContent(CMA);
			biz_SampleItem.addContent(CNAS);
			biz_SampleItem.addContent(ItemProperty);
			biz_SampleItem.addContent(TestItemStatus);
			biz_SampleItem.addContent(OutTest);
			biz_SampleItem.addContent(PackFlag);
			biz_SampleItem.addContent(DeptID);			
			biz_SampleItem.addContent(TestGroupID);
			biz_SampleItem.addContent(TestGroupName);
			biz_SampleItem.addContent(DefaultUserID);
			biz_SampleItem.addContent(DefaultUserName);
			biz_SampleItem.addContent(DeptName);       //7、3新增检测部门
			biz_SampleItemList.addContent(biz_SampleItem);
		}
		
		biz_TestSampleList = new Element("biz_TestSampleList");
		biz_TestSample = new Element("biz_TestSample");
		Dept_ID = new Element("DeptID");
		Dept_Name = new Element("DeptName");
		biz_TestSample.addContent(Dept_ID);
		biz_TestSample.addContent(Dept_Name);
		biz_TestSampleList.addContent(biz_TestSample);

		biz_Sample.addContent(SampleNo);
		biz_Sample.addContent(SampleNumber);
		biz_Sample.addContent(SampleName);
		biz_Sample.addContent(SampleMark);
		biz_Sample.addContent(SampleSpec);
		biz_Sample.addContent(SampleQuantity);
		biz_Sample.addContent(DeliveryNo);
		biz_Sample.addContent(SampleDueDate);
		biz_Sample.addContent(SampleRemark);
		biz_Sample.addContent(biz_SampleItemList);
		biz_Sample.addContent(biz_TestSampleList);
		return biz_Sample;

	}
	
	/**发送样品数据**/
	public static void sendLabSample(String declNo,String labApplyID,String applyNo, String xmlStr)  {
		log.info("正在发送样品数据...");
		HttpClient httpClient = new DefaultHttpClient();
		try {
			StringEntity stringEntity = new StringEntity(xmlStr, "text/xml",
					"utf-8"); // 第二三个参数合并为一个参数："text/plain;charset=\"UTF-8\"";
			stringEntity.setChunked(true);
			/**Old build of sample attend*/
			//HttpPost httpPost = new HttpPost(GetLrpURL.lrpURL()+ "/BizAccept/AcceptSample?apikey=bda11d91-7ade-4da1-855d-24adfe39d174&uid=1&uname=admin&utype=2");
			/**New build of sample attend*/
			HttpPost httpPost = new HttpPost(ConstantStr.LRP_URL+ "/BizAccept/AcceptSampleFX?apikey=bda11d91-7ade-4da1-855d-24adfe39d174&uid=1&uname=admin&utype=2");
			
			httpPost.setEntity(stringEntity);
			HttpResponse response = httpClient.execute(httpPost);
			log.info(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			log.info("样品数据发送成功");
			if (entity != null) {
				InputStream instream = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(instream, "utf-8"));
				StringBuffer temp = new StringBuffer();
				String line = reader.readLine();
				while (line != null) {
					temp.append(line).append("\r\n");
					line = reader.readLine();
				}
				reader.close();
				instream.close();
				String result = temp.toString();
				generateXmlFile(xmlStr,"AcceptLMISSample");
				log.info(result);
				saveSampleNoAndSamplingNo(declNo, labApplyID, applyNo, result);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.info("发送样品数据不成功！");
			log.error(e);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			log.info("发送样品数据不成功！");
			log.error(e);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			log.info("发送样品数据不成功！");
			log.error(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.info("发送样品数据不成功！");
			log.error(e);
		} catch (Exception e) {
			log.info("发送样品数据不成功！");
			log.error(e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

	}

	/**保存样品号和检样号**/
	public static void saveSampleNoAndSamplingNo(String declNo, String labApplyID, String applyNo, String result)  {
		log.info("正在保存样品数据...");
		try {
			Reader rr = new StringReader(result);
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder domBuilder = builderFactory.newDocumentBuilder();
			org.w3c.dom.Document document = (org.w3c.dom.Document) domBuilder
					.parse(new InputSource(rr));
			org.w3c.dom.Element root = document.getDocumentElement();

			NodeList biz_Sample = root.getElementsByTagName("biz_Sample");
			if (biz_Sample.getLength()!=0) {
				for (int i = 0; i < biz_Sample.getLength(); i++) {
					Node sample = biz_Sample.item(i);
					String sampleID = "";
					String sampleNo = "";
					String sampleBarcode = "";
					for (Node node = sample.getFirstChild(); node != null; node = node
							.getNextSibling()) {
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							if (node.getNodeName().equals("SampleNo")) {
								if (node.getChildNodes().getLength() != 0)
									sampleNo = node.getFirstChild()
											.getNodeValue();
							}
							if (node.getNodeName().equals("DeliveryNo")) {
								if (node.getChildNodes().getLength() != 0)
									sampleID = node.getFirstChild()
											.getNodeValue();
							}
							/*if (node.getNodeName().equals("SampleBarcode")) {
								if (node.getChildNodes().getLength() != 0)
									sampleBarcode = node.getFirstChild()
											.getNodeValue();
							}*/

						}
					}
					Connection conn=DBPool.ds.getConnection();
					CallableStatement proc = null;
					try {
						proc = conn
								.prepareCall("{call Pro_UpdateLabSampleNo(?,?,?,?)}");
						proc.setString(1, sampleID);
						proc.setString(2, sampleNo);
						proc.setString(3, applyNo);
						proc.registerOutParameter(4, java.sql.Types.VARCHAR);
						proc.execute();
						String returnCode = proc.getString(4);
						if (!returnCode.equals("")) {
							log.info("保存样品号失败"+sampleNo);
							rs = returnCode;
							return;
						}else{			
							log.info("保存样品号成功"+sampleNo);
							submitLabSuccess(declNo, labApplyID);
							return;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						rs = "保存样品数据不成功！";
						log.error(e);
					} catch (Exception e) {
						
					} finally {
						try {
							if (proc != null)
								proc.close();
							if (conn != null)
								conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							log.error(e);
						}
					}
				}
				log.info("样品数据保存成功,正在返回...");
			}
			else{
				log.info("LMIS返回的样品信息为空,样品数据保存失败!");
			}
		} catch (DOMException e) {
			log.info("保存样品数据不成功！");
			rs = "保存样品数据不成功！";
			log.error(e);
		} catch (ParserConfigurationException e) {
			log.info("保存样品数据不成功！");
			rs = "保存样品数据不成功！";
			log.error(e);
		} catch (SAXException e) {
			log.info("保存样品数据不成功！");
			rs = "保存样品数据不成功！";
			log.error(e);
		} catch (IOException e) {
			log.info("保存样品数据不成功！");
			rs = "保存样品数据不成功！";
			log.error(e);
		} catch (Exception e) {
			log.info("保存样品数据不成功！");
			rs = "保存样品数据不成功！";
			log.error(e);
		}
	}
	
}
