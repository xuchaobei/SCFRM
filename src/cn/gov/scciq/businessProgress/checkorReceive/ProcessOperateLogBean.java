package cn.gov.scciq.businessProgress.checkorReceive;
/**
 * 单证流程操作日志表
 * @author ada
 *
 */
public class ProcessOperateLogBean {
	
	private String processOperateLogID;
	private String declNo;
	private String operateDesc;
	private String operateDate;
	private String inspOrgCode;
	private String inspDeptCode;
	private String inspOperatorCode;
	private String processName;
	private String finishFlg;
	public String getProcessOperateLogID() {
		return processOperateLogID;
	}
	public void setProcessOperateLogID(String processOperateLogID) {
		this.processOperateLogID = processOperateLogID;
	}
	public String getDeclNo() {
		return declNo;
	}
	public void setDeclNo(String declNo) {
		this.declNo = declNo;
	}
	public String getOperateDesc() {
		return operateDesc;
	}
	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}
	public String getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
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
	public String getInspOperatorCode() {
		return inspOperatorCode;
	}
	public void setInspOperatorCode(String inspOperatorCode) {
		this.inspOperatorCode = inspOperatorCode;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	
	public String getFinishFlg() {
		return finishFlg;
	}
	public void setFinishFlg(String finishFlg) {
		this.finishFlg = finishFlg;
	}
	public ProcessOperateLogBean() {
		super();
	}
	
}
