package cn.gov.scciq.systemManage.inspOperator;

public class RoleDefineBean {
	private String roleID;
	private String roleCode;
	private String roleName;
	private String operateLevel;
	private String dataAccess;
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getOperateLevel() {
		return operateLevel;
	}
	public void setOperateLevel(String operateLevel) {
		this.operateLevel = operateLevel;
	}
	public String getDataAccess() {
		return dataAccess;
	}
	public void setDataAccess(String dataAccess) {
		this.dataAccess = dataAccess;
	}
	public RoleDefineBean() {
		super();
	}
	
}
