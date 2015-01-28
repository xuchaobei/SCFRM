package cn.gov.scciq.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

/**
 * 常量池
 * 
 * @author chao.xu
 *
 */
public class ConstantStr {

	public static final String SAVE_ERROR_MSG = "程序异常，保存失败！";
	public static final String PERMISSION_DENIAL_MSG = "您没有操作权限!";

	/** 机构代码 */
	public static final String ORG_CODE = "ORG_CODE";
	/** 部门代码 */
	public static final String DEPT_CODE = "DEPT_CODE";
	/** 登录用户代码 */
	public static final String OPERATOR_CODE = "OPERATOR_CODE";
	/** 登录用户名 */
	public static final String OPERATOR_NAME = "OPERATOR_NAME";
	/** 登录用户角色代码 */
	public static final String ROLE_CODE = "ROLE_CODE";

	/** 模块名称，作为查询权限时的参数menuName */
	public static final String RISK_CONTROL = "风险布控";

	public static final String ENT_MANAGEMENT = "企业管理";



}
