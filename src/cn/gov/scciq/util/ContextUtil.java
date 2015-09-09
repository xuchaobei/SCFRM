package cn.gov.scciq.util;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

/**
 * 上下文工具类
 * 
 * @author chao.xu
 *
 */
public class ContextUtil {
    
    /**
     * 获取session
     * @return
     */
    public static Map<String, Object> getSession(){
        ActionContext ctx = ActionContext.getContext();
        Map<String, Object> sessionMap = ctx.getSession();
        return sessionMap;
    }
    
    /**
     * 获取人员角色
     * @return
     */
    public static String getRoleCode(){
        Map<String, Object> sessionMap = getSession();
        String roleCode = (String)sessionMap.get(ConstantStr.ROLE_CODE);
        return roleCode;
    }
    
    /**
     * 获取当前人员的机构号
     * @return
     */
    public static String getOrgCode(){
        Map<String, Object> sessionMap = getSession();
        String roleCode = (String)sessionMap.get(ConstantStr.ORG_CODE);
        return roleCode;
    }
    
    /**
     * 获取当前人员的科室号
     * @return
     */
    public static String getDeptCode(){
        Map<String, Object> sessionMap = getSession();
        String roleCode = (String)sessionMap.get(ConstantStr.DEPT_CODE);
        return roleCode;
    }
    
    /**
     * 获取当前人员的编码
     * @return
     */
    public static String getOperatorCode(){
        Map<String, Object> sessionMap = getSession();
        String roleCode = (String)sessionMap.get(ConstantStr.OPERATOR_CODE);
        return roleCode;
    }
}
