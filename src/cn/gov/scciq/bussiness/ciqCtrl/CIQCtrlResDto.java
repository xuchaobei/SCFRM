package cn.gov.scciq.bussiness.ciqCtrl;

/**
 * 应急布控列表
 * 
 * @author chao.xu
 * 
 */
public class CIQCtrlResDto {
    private String ciqControlID;

    private String controlName;

    private String controlReason;

    private String remarks;

    private String deadline;

    private String ifExec; // （1：生效；非1：无效）

    private String ifCheck; // （1：通过；非1：不通过）

    private String controlInputDatetime;

    private String controlDeptName;
    
    public String getCiqControlID() {
        return ciqControlID;
    }

    public void setCiqControlID(String ciqControlID) {
        this.ciqControlID = ciqControlID;
    }

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

    public String getIfExec() {
        return ifExec;
    }

    public void setIfExec(String ifExec) {
        this.ifExec = ifExec;
    }

    public String getIfCheck() {
        return ifCheck;
    }

    public void setIfCheck(String ifCheck) {
        this.ifCheck = ifCheck;
    }

    public String getControlInputDatetime() {
        return controlInputDatetime;
    }

    public void setControlInputDatetime(String controlInputDatetime) {
        this.controlInputDatetime = controlInputDatetime;
    }

    public String getControlDeptName() {
        return controlDeptName;
    }

    public void setControlDeptName(String controlDeptName) {
        this.controlDeptName = controlDeptName;
    }

}
