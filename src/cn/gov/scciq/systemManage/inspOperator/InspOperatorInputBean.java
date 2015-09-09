package cn.gov.scciq.systemManage.inspOperator;

import net.sf.json.JSONArray;

public class InspOperatorInputBean {
	private String operatorID;
	private String inspOrgCode;
	private String inspDeptCode;
	private String roleCode;
	private String orgCode;
	private String roleName;
	private String deptCode;
	private String operatorName;
	private String operatorCode;
	private JSONArray itemLimitList;
	
	public String getOperatorID() {
		return operatorID;
	}
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}
	public String getInspOrgCode() {
		return inspOrgCode;
	}
	public void setInspOrgCode(String inspOrgCode) {
		this.inspOrgCode = inspOrgCode;
	}
	public String getInspDeptCode() {
		return inspDeptCode;
	}
	public void setInspDeptCode(String inspDeptCode) {
		this.inspDeptCode = inspDeptCode;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
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
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	
	public JSONArray getItemLimitList() {
		return itemLimitList;
	}
	public void setItemLimitList(JSONArray itemLimitList) {
		this.itemLimitList = itemLimitList;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public InspOperatorInputBean() {
		super();
	}
	
	

}
