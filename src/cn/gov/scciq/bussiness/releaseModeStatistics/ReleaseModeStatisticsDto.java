package cn.gov.scciq.bussiness.releaseModeStatistics;

public class ReleaseModeStatisticsDto {
	public String releaseMode;
	public String orgName;
	public String deptName;
	public String entName;
	public String countryName;
	public String totalReleaseTimes;
	public String totalValues_USD;
	public String totalWeight;
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	public String getTotalValues_USD() {
		return totalValues_USD;
	}
	public void setTotalValues_USD(String totalValues_USD) {
		this.totalValues_USD = totalValues_USD;
	}
	public String getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}
	public String getReleaseMode() {
		return releaseMode;
	}
	public void setReleaseMode(String releaseMode) {
		this.releaseMode = releaseMode;
	}
	public String getTotalReleaseTimes() {
		return totalReleaseTimes;
	}
	public void setTotalReleaseTimes(String totalReleaseTimes) {
		this.totalReleaseTimes = totalReleaseTimes;
	}

	
}
