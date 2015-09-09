package cn.gov.scciq.basicSettings.item;

public class ItemSubBean {
	 private String itemSubID;
	 private String itemCode;
     private String itemCodeSub;
     private String itemName;
     
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public ItemSubBean() {
		super();
	}
	public String getItemSubID() {
		return itemSubID;
	}
	public void setItemSubID(String itemSubID) {
		this.itemSubID = itemSubID;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemCodeSub() {
		return itemCodeSub;
	}
	public void setItemCodeSub(String itemCodeSub) {
		this.itemCodeSub = itemCodeSub;
	}
     
}
