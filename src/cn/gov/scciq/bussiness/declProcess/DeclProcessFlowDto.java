package cn.gov.scciq.bussiness.declProcess;

public class DeclProcessFlowDto {
	private String declNo;
	private String processName;
	private String processOperateDatetime;
	private String orgName;
	private String deptName;
	private String operatorName;

	public String getDeclNo() {
		return declNo;
	}

	public void setDeclNo(String declNo) {
		this.declNo = declNo;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessOperateDatetime() {
		return processOperateDatetime;
	}

	public void setProcessOperateDatetime(String processOperateDatetime) {
		this.processOperateDatetime = processOperateDatetime;
	}

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

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

}
