package cn.gov.scciq.bussiness.login;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONObject;

/**
 * 登录
 * 
 * @author chao.xu
 *
 */
public class LoginAction {

    private String orgCode;
    private String deptCode;
    private String operatorCode;
    private String password;
    private JSONObject result;
    
    /**
     * 登录
     * @return
     */
    public String login(){
        result = LoginService.login(orgCode, deptCode, operatorCode, password);
        return Action.SUCCESS;
    }
    
    /**
     * 注销
     * @return
     */
    public String logout(){
        ActionContext.getContext().getSession().clear();
        return Action.SUCCESS;
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

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }
    
    

}
