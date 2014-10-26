package cn.gov.scciq.bussiness.dbpool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DBPool {
	public static javax.sql.DataSource ds;
	static {
		try {
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			Object obj = (Object) ctx.lookup("jdbc/FRM_SC");
			ds = (javax.sql.DataSource) obj;
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
