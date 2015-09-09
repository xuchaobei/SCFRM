package cn.gov.scciq.bussiness.productCtrlItem;

public class ProductCtrlItemReqDto {
	private String productControlItemID;
	private String productCode;
	private String productName;
	private String countryName;
	private String itemName;
	
	public String getProductControlItemID() {
		return productControlItemID;
	}
	public void setProductControlItemID(String productControlItemID) {
		this.productControlItemID = productControlItemID;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	
}
