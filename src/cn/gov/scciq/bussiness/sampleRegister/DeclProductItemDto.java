package cn.gov.scciq.bussiness.sampleRegister;

public class DeclProductItemDto {

	private String declProductItemID;
	private String itemCode;
	private String itemName;
	private String limitReq;
	private int sendLabFlg;
	private int labFlg;
	private String releaseMode;
	private String labDataUnit;
	private String testLabName;
	private int ifSet;
	
    private String fromWhere;
    private String itemType;
	private String itemifMatched;
	//private String labItemMatchID;
//	3+3G项目代码：			ItemCode
//	3+3G项目名称：			ItemName
	/*private String lrpItemID;
	private String lrpItemName;
	private String lrpTestStd;
	private String labDeptName;*/

	
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

	
	public int getSendLabFlg() {
		return sendLabFlg;
	}
	public void setSendLabFlg(int sendLabFlg) {
		this.sendLabFlg = sendLabFlg;
	}
	public int getLabFlg() {
		return labFlg;
	}
	public void setLabFlg(int labFlg) {
		this.labFlg = labFlg;
	}
	public int getIfSet() {
		return ifSet;
	}
	public void setIfSet(int ifSet) {
		this.ifSet = ifSet;
	}
	public String getTestLabName() {
		return testLabName;
	}
	public void setTestLabName(String testLabName) {
		this.testLabName = testLabName;
	}
	public String getFromWhere() {
		return fromWhere;
	}
	public void setFromWhere(String fromWhere) {
		this.fromWhere = fromWhere;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemifMatched() {
		return itemifMatched;
	}
	public void setItemifMatched(String itemifMatched) {
		this.itemifMatched = itemifMatched;
	}
	
	
	
	
}
