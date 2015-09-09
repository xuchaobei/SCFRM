package cn.gov.scciq.util;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * session超时拦截器
 * 
 * @author chao.xu
 *
 */
public class SessionInterceptor extends AbstractInterceptor {

    /**  */
    
    private static final long serialVersionUID = 1L;

    @Override
    public String intercept(ActionInvocation arg0) throws Exception {
        // TODO Auto-generated method stub
        String operatorCode = (String)ActionContext.getContext().getSession().get(ConstantStr.OPERATOR_CODE);
        if(operatorCode == null){
            return "login";
        }else{
            return arg0.invoke();
        }
    }

}
