package cn.gov.scciq.bussiness.sampleRegister;

public class DeclInfoResDto {
	private String declNo;
	private String declDate;
	private String countryName;
	private String entName;
	private String productData;
	private String certType;
	private String ifSampling;
	private String processStatus;
	
	public DeclInfoResDto(DeclInfoDto dto) {
		this.setDeclNo(dto.getDeclNo());
		this.setDeclDate(dto.getDeclDate());
		this.setCountryName(dto.getCountryName());
		this.setCertType(dto.getCertType());
		this.setEntName(dto.getEntName());
		this.setProductData(dto.getProductData());
		this.setIfSampling(dto.getIfSampling());
	}
	
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getDeclNo() {
		return declNo;
	}

	public void setDeclNo(String declNo) {
		this.declNo = declNo;
	}

	public String getDeclDate() {
		return declDate;
	}

	public void setDeclDate(String declDate) {
		this.declDate = declDate;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getProductData() {
		return productData;
	}

	public void setProductData(String productData) {
		this.productData = productData;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getIfSampling() {
		return ifSampling;
	}

	public void setIfSampling(String ifSampling) {
		this.ifSampling = ifSampling;
	}
	
	
	
}
