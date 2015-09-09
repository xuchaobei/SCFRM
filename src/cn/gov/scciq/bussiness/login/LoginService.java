package cn.gov.scciq.bussiness.login;

import java.util.Map;

import net.sf.json.JSONObject;
import cn.gov.scciq.dto.UserDto;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.DefaultResultUtil;

public class LoginService {

    public static JSONObject login(String orgCode, String deptCode, String operatorCode, String passwords){
        JSONObject rs = null;
        Map<Integer, Object> map = LoginDao.checkOperatorPasswords(orgCode, deptCode, operatorCode, passwords);
        String loginResult = (String)map.get(0);
        if("".equals(loginResult)){
            rs = DefaultResultUtil.getModificationResult("true");
            UserDto dto = (UserDto)map.get(1);
            //往Session设置共享数据
            Map<String, Object> sessionMap = ContextUtil.getSession();
            sessionMap.put(ConstantStr.ORG_CODE, dto.getOrgCode());
            sessionMap.put(ConstantStr.DEPT_CODE, dto.getDeptCode());
            sessionMap.put(ConstantStr.OPERATOR_CODE, dto.getOperatorCode());
            sessionMap.put(ConstantStr.OPERATOR_NAME, dto.getOperatorName());
            //sessionMap.put(ConstantStr.ROLE_CODE, dto.getRoleCode());
        }else{
            rs = DefaultResultUtil.getModificationResult(loginResult);
        }
        return rs;
    }
}
