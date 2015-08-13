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
	
//	LabItemMatchID
//	3+3G项目代码：			ItemCode
//	3+3G项目名称：			ItemName
//	LMIS项目代码：			LRPItemID
//	LMIS项目名称：			LRPItemName
//	LMIS检测标准：			LRPTestStd
//	检测部门：				LabDeptName

	
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
	
	
}
