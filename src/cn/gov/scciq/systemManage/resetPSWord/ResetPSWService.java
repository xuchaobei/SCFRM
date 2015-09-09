package cn.gov.scciq.systemManage.resetPSWord;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
public class ResetPSWService {
	private static Log log=LogFactory.getLog(ResetPSWService.class);
	 String retStr = ConstantStr.SAVE_ERROR_MSG;
	 
	public String ChangeOperatorPassword(ResetPSWBean bean) throws Exception{
			Connection conn = DBPool.ds.getConnection();
		     CallableStatement proc = null;
		
		try {
			
			String wCall = "{call Pro_ChangeOperatorPassword(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, ContextUtil.getOrgCode());
			proc.setString(2, ContextUtil.getDeptCode());
			proc.setString(3, ContextUtil.getOperatorCode());
			proc.setString(4, bean.getOldPasswords());
			proc.setString(5, bean.getNewPasswords());
			proc.registerOutParameter(6, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(6);
			log.info("Pro_ResetEntPasswords--"+retStr);
		} catch (SQLException e) {
           // TODO Auto-generated catch block
           log.error("", e);
       } catch (Exception e) {
           log.error("", e);
       } finally{
           try {
               if(proc != null){
                   proc.close();
               }
               if(conn != null){
                   conn.close();
               }
           } catch (SQLException e) {
               // TODO Auto-generated catch block
               log.error("", e);
           }
       }
       return retStr;
		
	


	}

}
