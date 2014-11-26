package cn.gov.scciq.bussiness.ciqCtrl;

/**
 * 应急布控请求参数
 * 
 * @author chao.xu
 * 
 */
public class CIQCtrlReqDto {


    private String controlName;

    private String controlReason;

    private String ifExec; // （1：生效；非1：无效）

    private String ifCheck; // （1：通过；非1：不通过）

    private String controlOrgCode;

    private String controlDeptCode;

    private String ciqControlID;
    
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

    public String getControlOrgCode() {
        return controlOrgCode;
    }

    public void setControlOrgCode(String controlOrgCode) {
        this.controlOrgCode = controlOrgCode;
    }

    public String getControlDeptCode() {
        return controlDeptCode;
    }

    public void setControlDeptCode(String controlDeptCode) {
        this.controlDeptCode = controlDeptCode;
    }


}
