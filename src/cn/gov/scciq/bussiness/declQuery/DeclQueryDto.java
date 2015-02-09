package cn.gov.scciq.bussiness.declQuery;

public class DeclQueryDto {
	private String declNo;
	private String declDate;
	private String entName;
	private String countryName;
	private String productData;
	private String processOperateDatetime;
	private String ifSampling;
	private String ifQualified_Yes;
	private String releaseMode;
	private String processOperatorCode;
	
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
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getProductData() {
		return productData;
	}
	public void setProductData(String productData) {
		this.productData = productData;
	}
	public String getIfSampling() {
		return ifSampling;
	}
	public void setIfSampling(String ifSampling) {
		this.ifSampling = ifSampling;
	}
	public String getIfQualified_Yes() {
		return ifQualified_Yes;
	}
	public void setIfQualified_Yes(String ifQualified_Yes) {
		this.ifQualified_Yes = ifQualified_Yes;
	}
	public String getProcessOperateDatetime() {
		return processOperateDatetime;
	}
	public void setProcessOperateDatetime(String processOperateDatetime) {
		this.processOperateDatetime = processOperateDatetime;
	}
	public String getReleaseMode() {
		return releaseMode;
	}
	public void setReleaseMode(String releaseMode) {
		this.releaseMode = releaseMode;
	}
	public String getProcessOperatorCode() {
		return processOperatorCode;
	}
	public void setProcessOperatorCode(String processOperatorCode) {
		this.processOperatorCode = processOperatorCode;
	}
	
	
}
