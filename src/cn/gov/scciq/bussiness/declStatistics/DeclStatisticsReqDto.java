package cn.gov.scciq.bussiness.declStatistics;

public class DeclStatisticsReqDto {
	public int startYear;
	public int startMonth;
	public int endYear;
	public int endMonth;
	public String orgCode;
	public String deptCode;
	public String countryCode;
	public String entCode;
	public boolean group_Org;
	public boolean group_Dept;
	public boolean group_Country;
	public boolean group_Ent;
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	public int getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}
	public int getEndYear() {
		return endYear;
	}
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	public int getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getEntCode() {
		return entCode;
	}
	public void setEntCode(String entCode) {
		this.entCode = entCode;
	}
	public boolean getGroup_Org() {
		return group_Org;
	}
	public void setGroup_Org(boolean group_Org) {
		this.group_Org = group_Org;
	}
	public boolean getGroup_Dept() {
		return group_Dept;
	}
	public void setGroup_Dept(boolean group_Dept) {
		this.group_Dept = group_Dept;
	}
	public boolean getGroup_Country() {
		return group_Country;
	}
	public void setGroup_Country(boolean group_Country) {
		this.group_Country = group_Country;
	}
	public boolean getGroup_Ent() {
		return group_Ent;
	}
	public void setGroup_Ent(boolean group_Ent) {
		this.group_Ent = group_Ent;
	}
	
	
}
