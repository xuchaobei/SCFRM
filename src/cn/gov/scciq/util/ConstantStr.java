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

	public static String readFile() throws Exception {
		
		String path = "D://wind2.js";
		File file = new File(path);
		FileOutputStream out = new FileOutputStream(file, false);
		
		String fileUrl = "http://mobile-cn.envisioncn.com:12888/web_widget/windpower/windpowerLanguage.js";
		URL uri = new URL(fileUrl);
		URLConnection ucon = uri.openConnection();
		ucon.connect();
		InputStream is = ucon.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder builder = new StringBuilder();
		String inputLine = "";
		while ((inputLine = br.readLine()) != null) {
			inputLine += "\n";
			builder.append(inputLine);
			out.write(inputLine.getBytes("gbk"));
		}
		is.close();
		out.close();
		System.out.println(builder);
		return builder.toString();

	}

	public static void writeFile(String content) throws Exception {
		String path = "D://wind.js";
		File file = new File(path);
		FileOutputStream out = new FileOutputStream(file, false);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out,
				"utf-8"));
		writer.write(content);
		writer.close();
	}
	
	public static void main(String[] args) throws Exception {
		//String content = readFile();
		//writeFile(content);
		getJSON();
	}
	

	public static void getJSON(){
		JSONObject jo = new JSONObject();
		String str = "{name: \"tt\", age:22}";
		jo.put("name","test");
		jo.put("age","123");
		JSONObject jj = JSONObject.fromObject(str);
		jo.put("relation",jj);
		
		String ss = jj.toString();
		System.out.println(jo);
		System.out.println(jo.toString());
		JSONObject jo2 = jo.getJSONObject("relation");
		String jo3 = jo.getString("relation");
		
		
		System.out.println(jo2);
		System.out.println(jo3);
		JSONObject jo4 = JSONObject.fromObject(jo3);
		System.out.println(jo4);
	}


}
