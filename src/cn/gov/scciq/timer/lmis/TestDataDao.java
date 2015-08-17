package cn.gov.scciq.timer.lmis;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.dbpool.DBPool;

public class TestDataDao {
	private static Log log=LogFactory.getLog(TestDataDao.class);
	
	public static List<String> getLabApplyNoForTestData() throws SQLException {
		List<String> labApplyNoList = new ArrayList<String>();
		Connection conn=null;
		CallableStatement proc = null;
		ResultSet rs = null;
		String wCall = null;
		try {
			conn = DBPool.ds_lmis.getConnection();
			wCall = "{call Pro_GetLabApplyNoForTestData()}";
			proc = conn.prepareCall(wCall);
			proc.execute();
			rs = proc.getResultSet();
			while (rs.next()) {
				labApplyNoList.add(rs.getString("LabApplyNo"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("", e);
		} catch (Exception e){
			log.error("", e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (proc != null)
					proc.close();
				if (conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("", e);
			}
		}
        return labApplyNoList;
	}
	
	public static List<String> getLabApplyNoForDelSample() throws SQLException {
		List<String> labApplyNoList = new ArrayList<String>();
		Connection conn=null;
		CallableStatement proc = null;
		ResultSet rs = null;
		String wCall = null;
		try {
			conn = DBPool.ds_lmis.getConnection();
			wCall = "{call Pro_GetLabApplyNoForDelSample()}";
			proc = conn.prepareCall(wCall);
			proc.execute();
			rs = proc.getResultSet();
			while (rs.next()) {
				labApplyNoList.add(rs.getString("ApplyID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("", e);
		} catch (Exception e){
			log.error("", e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (proc != null)
					proc.close();
				if (conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("", e);
			}
		}
        return labApplyNoList;
	}
	
    public static int updateItemLabData(String SampleID, String DpTestItemID, String ResultData, String ResultUnit) {

    	Connection conn=null;
		CallableStatement proc = null;		
		String wCall = null;
    	wCall = "{call Pro_UpdateItemLabData(?,?,?,?,?)}";
    	int returnCode = 0;
		try {
			conn = DBPool.ds_lmis.getConnection();
			proc = conn.prepareCall(wCall);
			proc.setString(1, SampleID);
			proc.setString(2, DpTestItemID);
			proc.setString(3, ResultData);
			proc.setString(4, ResultUnit);
			proc.registerOutParameter(5,
					java.sql.Types.INTEGER);
			proc.execute();
			returnCode = proc.getInt(5);
			proc.close();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("", e);
		}  catch (Exception e){
			log.error("", e);
		} finally {
			try {				
				if (proc != null)
					proc.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
				log.error("", e);
			}
		}  
		return returnCode;
    }
	
	public static void updateReadTestDataFlg(String LabApplyNo) throws SQLException {
		Connection conn=null;
		CallableStatement proc = null;
		String wCall = null;
		wCall = "{call Pro_UpdateReadTestDataFlg(?)}";
		try {
			conn = DBPool.ds_lmis.getConnection();
			proc = conn.prepareCall(wCall);
			proc.setString(1, LabApplyNo);
			proc.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("", e);
		} catch (Exception e){
			log.error("", e);
		} finally {
			try {
				if (proc != null)
					proc.close();
				if (conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("", e);
			}
		}
	}
    
    public static void updateDelSampleFlg(String labApplyID) throws SQLException{
    	
    	Connection conn=null;
		CallableStatement proc = null;		
		String wCall = null;
		wCall = "{call Pro_UpdateDelSampleFlg(?)}";
		try {
			conn = DBPool.ds_lmis.getConnection();
			proc = conn.prepareCall(wCall);
			proc.setString(1,labApplyID);
			proc.execute();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("", e);
		}  catch (Exception e){
			log.error("", e);
		} finally {
			try {				
				if (proc != null)
					proc.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
				log.error("", e);
			}
		}    	
    } 
}
