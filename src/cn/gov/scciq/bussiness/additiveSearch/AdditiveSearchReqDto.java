package cn.gov.scciq.bussiness.additiveSearch;

public class AdditiveSearchReqDto {
	private String additiveName;
    private String entName;
    private String productName;
	private String countryName;

	
	public String getAdditiveName() {
		return additiveName;
	}
	public void setAdditiveName(String additiveName) {
		this.additiveName = additiveName;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
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
	
}
