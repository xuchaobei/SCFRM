package cn.gov.scciq.bussiness.sampleRegister;

public class DeclProductItemDto {

	private String declProductItemID;
	private String itemCode;
	private String itemName;
	private String limitReq;
	private boolean sendLabFlg;
	private boolean labFlg;
	private String releaseMode;
	private String labDataUnit;
	private String testLabName;
	private boolean ifSet;
	
	private String labItemMatchID;
//	3+3G项目代码：			ItemCode
//	3+3G项目名称：			ItemName
	private String lrpItemID;
	private String lrpItemName;
	private String lrpTestStd;
	private String labDeptName;

	
	public String getDeclProductItemID() {
		return declProductItemID;
	}
	public void setDeclProductItemID(String declProductItemID) {
		this.declProductItemID = declProductItemID;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getLimitReq() {
		return limitReq;
	}
	public void setLimitReq(String limitReq) {
		this.limitReq = limitReq;
	}
	public boolean isSendLabFlg() {
		return sendLabFlg;
	}
	public void setSendLabFlg(boolean sendLabFlg) {
		this.sendLabFlg = sendLabFlg;
	}
	public boolean isLabFlg() {
		return labFlg;
	}
	public void setLabFlg(boolean labFlg) {
		this.labFlg = labFlg;
	}
	public String getReleaseMode() {
		return releaseMode;
	}
	public void setReleaseMode(String releaseMode) {
		this.releaseMode = releaseMode;
	}
	public String getLabDataUnit() {
		return labDataUnit;
	}
	public void setLabDataUnit(String labDataUnit) {
		this.labDataUnit = labDataUnit;
	}
	public String getTstLabName() {
		return testLabName;
	}
	public void setTstLabName(String tstLabName) {
		this.testLabName = tstLabName;
	}
	public boolean isIfSet() {
		return ifSet;
	}
	public void setIfSet(boolean ifSet) {
		this.ifSet = ifSet;
	}
	public String getTestLabName() {
		return testLabName;
	}
	public void setTestLabName(String testLabName) {
		this.testLabName = testLabName;
	}
	public String getLabItemMatchID() {
		return labItemMatchID;
	}
	public void setLabItemMatchID(String labItemMatchID) {
		this.labItemMatchID = labItemMatchID;
	}
	public String getLrpItemID() {
		return lrpItemID;
	}
	public void setLrpItemID(String lrpItemID) {
		this.lrpItemID = lrpItemID;
	}
	public String getLrpItemName() {
		return lrpItemName;
	}
	public void setLrpItemName(String lrpItemName) {
		this.lrpItemName = lrpItemName;
	}
	public String getLrpTestStd() {
		return lrpTestStd;
	}
	public void setLrpTestStd(String lrpTestStd) {
		this.lrpTestStd = lrpTestStd;
	}
	public String getLabDeptName() {
		return labDeptName;
	}
	public void setLabDeptName(String labDeptName) {
		this.labDeptName = labDeptName;
	}
	
	
}
