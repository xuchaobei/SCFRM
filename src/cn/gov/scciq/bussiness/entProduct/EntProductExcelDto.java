package cn.gov.scciq.bussiness.entProduct;

public class EntProductExcelDto {
	private String entCode;
	private String entName;
	private String countryCode;
	private String countryName;
	private String entProductName;
	private String entProductCode;
	private String productCode;
	private String riskLevelDesc;
	private String samplingRatio;

	public EntProductExcelDto() {
		// TODO Auto-generated constructor stub
	}

	public EntProductExcelDto(String entCode, String entName,
			String countryCode, String countryName, String entProductName,
			String entProductCode, String productCode, String riskLevelDesc,
			String samplingRatio) {
		super();
		this.entCode = entCode;
		this.entName = entName;
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.entProductName = entProductName;
		this.entProductCode = entProductCode;
		this.productCode = productCode;
		this.riskLevelDesc = riskLevelDesc;
		this.samplingRatio = samplingRatio;
	}

	public String getSamplingRatio() {
		return samplingRatio;
	}

	public void setSamplingRatio(String samplingRatio) {
		this.samplingRatio = samplingRatio;
	}

	public String getEntCode() {
		return entCode;
	}

	public void setEntCode(String entCode) {
		this.entCode = entCode;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getEntProductName() {
		return entProductName;
	}

	public void setEntProductName(String entProductName) {
		this.entProductName = entProductName;
	}

	public String getEntProductCode() {
		return entProductCode;
	}

	public void setEntProductCode(String entProductCode) {
		this.entProductCode = entProductCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getRiskLevelDesc() {
		return riskLevelDesc;
	}

	public void setRiskLevelDesc(String riskLevelDesc) {
		this.riskLevelDesc = riskLevelDesc;
	}

}
