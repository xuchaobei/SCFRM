package cn.gov.scciq.bussiness.ciqCtrl;

/**
 * 应急布控记录详情
 * 
 * @author chao.xu
 * 
 */
public class CIQCtrlDetailResDto {

    private String controlName;

    private String controlReason;

    private String remarks;

    private String deadline;

    private String orgName;

    private String deptName;
    
    private String operatorName;
    
    private String controlInputDatetime;

    public String getControlName() {
        return controlName;
    }

    public void setControlName(String controlName) {
        this.controlName = controlName;
    }

    public String getControlReason() {
        return controlReason;
    }

    public void setControlReason(String controlReason) {
        this.controlReason = controlReason;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
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

    public String getControlInputDatetime() {
        return controlInputDatetime;
    }

    public void setControlInputDatetime(String controlInputDatetime) {
        this.controlInputDatetime = controlInputDatetime;
    }



}
