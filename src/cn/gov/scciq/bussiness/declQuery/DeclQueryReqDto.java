package cn.gov.scciq.bussiness.declQuery;

public class DeclQueryReqDto {
	private String declNo;
	private String startDate;
	private String endDate;
	private String entName;
	private String countryName;
	private String qualifyJudge;
	private String releaseStatus;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getQualifyJudge() {
		return qualifyJudge;
	}
	public void setQualifyJudge(String qualifyJudge) {
		this.qualifyJudge = qualifyJudge;
	}
	public String getReleaseStatus() {
		return releaseStatus;
	}
	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	
	
}
