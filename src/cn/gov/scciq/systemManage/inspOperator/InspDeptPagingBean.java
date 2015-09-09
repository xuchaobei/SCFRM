package cn.gov.scciq.systemManage.inspOperator;

public class InspDeptPagingBean {
	private String orgCode;
	private String deptCode;
	private String deptName;
	private String inspDeptID;
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
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getInspDeptID() {
		return inspDeptID;
	}
	public void setInspDeptID(String inspDeptID) {
		this.inspDeptID = inspDeptID;
	}
	public InspDeptPagingBean() {
		super();
	}
	
}
