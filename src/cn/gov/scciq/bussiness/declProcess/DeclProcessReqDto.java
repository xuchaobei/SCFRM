package cn.gov.scciq.bussiness.declProcess;

public class DeclProcessReqDto {
	private String declNo;
	private String declDateStart;
	private String declDateEnd;
	private String entName;
	private String countryName;
	private String ifQualify;
	public String getDeclNo() {
		return declNo;
	}
	public void setDeclNo(String declNo) {
		this.declNo = declNo;
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
	public String getDeclDateStart() {
		return declDateStart;
	}
	public void setDeclDateStart(String declDateStart) {
		this.declDateStart = declDateStart;
	}
	public String getDeclDateEnd() {
		return declDateEnd;
	}
	public void setDeclDateEnd(String declDateEnd) {
		this.declDateEnd = declDateEnd;
	}
	public String getIfQualify() {
		return ifQualify;
	}
	public void setIfQualify(String ifQualify) {
		this.ifQualify = ifQualify;
	}
	
	
}
