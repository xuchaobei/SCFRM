
package cn.gov.scciq.dbpool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DBPool {
	public static javax.sql.DataSource ds;
	public static javax.sql.DataSource ds_cems;
	public static javax.sql.DataSource ds_lmis;
	static {
		try {
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			Object obj = (Object) ctx.lookup("jdbc/FRM_SC");
			ds = (javax.sql.DataSource) obj;
			Object obj2 = (Object) ctx.lookup("jdbc/CEMS");
			ds_cems = (javax.sql.DataSource) obj2;
			Object obj3 = (Object) ctx.lookup("jdbc/LMIS");
			ds_lmis = (javax.sql.DataSource) obj3;
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

