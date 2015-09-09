package cn.gov.scciq.bussiness.sampleRegister;

public class LabItemPlanDto {

	private String labItemMatchID;
	private String itemCode;
	private String itemName;
	private String lrpItemID;
	private String lrpItemName;
	private String lrpItemTestStd;
	private String labDeptName;
	
	public String getLabItemMatchID() {
		return labItemMatchID;
	}
	public void setLabItemMatchID(String labItemMatchID) {
		this.labItemMatchID = labItemMatchID;
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


	public String getLrpItemTestStd() {
		return lrpItemTestStd;
	}
	public void setLrpItemTestStd(String lrpItemTestStd) {
		this.lrpItemTestStd = lrpItemTestStd;
	}
	public String getLabDeptName() {
		return labDeptName;
	}
	public void setLabDeptName(String labDeptName) {
		this.labDeptName = labDeptName;
	}
}
