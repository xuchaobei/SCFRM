package cn.gov.scciq.timer.lmis;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.dbpool.DBPool;

/**
 * 读取实验室系统设置参数
 * 
 * @author chao.xu
 *
 */
public class BaseDataDao {
	private static Log log=LogFactory.getLog(BaseDataDao.class);
	
	public static void SaveLabAppliantDao(String userName,String loginName,String userType,String userID,String sex,String userStatus,String telePhone,String fax,String mobile,String email) throws SQLException{
		Connection conn=null;		
		CallableStatement proc = null;
		String wCall = "{call Pro_SaveLabAppliant(?,?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds_lmis.getConnection();
			proc = conn.prepareCall(wCall);
			proc.setString(1, userName);
			proc.setString(2, loginName);
			proc.setString(3, userType);
			proc.setString(4, userID);
			proc.setString(5, sex);
			proc.setString(6, userStatus);
			proc.setString(7, telePhone);
			proc.setString(8, fax);
			proc.setString(9, mobile);
			proc.setString(10, email);
			proc.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("", e);
		} catch (Exception e){
			log.error("", e);
		} finally{
			try {
				if(proc!=null)
					proc.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("", e);
			}
		}		
	}
	
	public static void SaveLabApplyDeptDao(String deptID,String deptName,String deptSimpleName,String deptCode,String deptPath,String deptNo,String deptCIQCode,String deptProperty,
			String deptDesc,String parentDeptID,String sortOrder) throws SQLException{
		Connection conn=null;
		CallableStatement proc = null;
		String wCall = "{call Pro_SaveLabApplyDept(?,?,?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds_lmis.getConnection();
			proc = conn.prepareCall(wCall);
			proc.setString(1, deptID);
			proc.setString(2, deptName);
			proc.setString(3, deptSimpleName);
			proc.setString(4, deptCode);
			proc.setString(5, deptPath);
			proc.setString(6, deptNo);
			proc.setString(7, deptCIQCode);
			proc.setString(8, deptProperty);
			proc.setString(9, deptDesc);
			proc.setString(10, parentDeptID);
			proc.setString(11, sortOrder);
			proc.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("", e);
		} catch (Exception e){
			log.error("", e);
		} finally{
			try {
				if(proc!=null)
					proc.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("", e);
			}
		}		
	}
	
	public static void SaveLabApplyKindDao(String applyKindID,String applyKindCode,String applyKind,String applyKindDesc,String parentApplyKindID,String applyKindNo,String sordOrder) throws SQLException{
		Connection conn=null;
		CallableStatement proc = null;
		String wCall = "{call Pro_SaveLabApplyKind(?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds_lmis.getConnection();
			proc = conn.prepareCall(wCall);
			proc.setString(1, applyKindID);
			proc.setString(2, applyKindCode);
			proc.setString(3, applyKind);
			proc.setString(4, applyKindDesc);
			proc.setString(5, parentApplyKindID);
			proc.setString(6, applyKindNo);
			proc.setString(7, sordOrder);
			proc.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		} catch (Exception e){
			log.error("", e);
		} finally{
			try {
				if(proc!=null)
					proc.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("", e);
			}
		}		
	}
	
	public static void SaveLabDeptDao(String deptID,String deptName,String deptSimpleName,String deptCode,String deptPath,String deptNo,String deptCIQCode,String deptProperty,
			String deptDesc,String parentDeptID,String sortOrder) throws SQLException{
		Connection conn=null;
		CallableStatement proc = null;
		String wCall = "{call Pro_SaveLabDept(?,?,?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds_lmis.getConnection();
			proc = conn.prepareCall(wCall);
			proc.setString(1, deptID);
			proc.setString(2, deptName);
			proc.setString(3, deptSimpleName);
			proc.setString(4, deptCode);
			proc.setString(5, deptPath);
			proc.setString(6, deptNo);
			proc.setString(7, deptCIQCode);
			proc.setString(8, deptProperty);
			proc.setString(9, deptDesc);
			proc.setString(10, parentDeptID);
			proc.setString(11, sortOrder);
			proc.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("", e);
		} catch(Exception e){
			log.error("", e);
		} finally{
			try {
				if(proc!=null)
					proc.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("", e);
			}
		}		
	}
	
	public static void SaveLabItemDao(String itemCode,String itemName,String testItemID,String testItemCode,String itemID,String standardNo,
			String standardName,String productCategoryCode,String productCategoryName,String detectionLimit,String resultUnit,String limit,String testPeriod,String testFee,String deptID,String testGroupID,String testGroupName,String defaultUserID,String defaultUserName) throws SQLException{
		Connection conn=null;
		CallableStatement proc = null;
		String wCall = null;
		wCall = "{call Pro_SaveLabItem(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";        //ֻ�ܱ���һ����ݣ��ǲ���itemCode��ͬ�Զ��滻������ӣ�
		try {
			conn = DBPool.ds_lmis.getConnection();
			proc = conn.prepareCall(wCall);
			proc.setString(1, itemCode);
			proc.setString(2, itemName);
			proc.setString(3, testItemID);
			proc.setString(4, testItemCode);
			proc.setString(5, itemID);     
			proc.setString(6, standardNo);                
			proc.setString(7, standardName);     
			proc.setString(8,productCategoryCode);   
			proc.setString(9,productCategoryName);
			proc.setString(10,detectionLimit);
			proc.setString(11,resultUnit);
			proc.setString(12,limit);
			proc.setString(13,testPeriod);
			proc.setString(14,testFee);
			proc.setString(15,deptID);
			proc.setString(16, testGroupID);
			proc.setString(17, testGroupName);
			proc.setString(18, defaultUserID);
			proc.setString(19,defaultUserName);
			proc.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		} catch(Exception e){
			log.error("", e);
		} finally{
			try {
				if(proc!=null)
					proc.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("", e);
			}
		}	
	}
	
	public static void SaveLabSampleDisposal(String DisposalName ) throws SQLException{
		Connection conn=null;
		CallableStatement proc = null;
		String wCall = null;
		wCall = "{call Pro_SaveLabSampleDisposal(?)}";
		try {
			conn = DBPool.ds_lmis.getConnection();
			proc = conn.prepareCall(wCall);
			proc.setString(1, DisposalName );
			proc.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("", e);
		} catch (Exception e){
			log.error("", e);
		} finally{
			try {
				if(proc!=null)
					proc.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("", e);
			}
		}	
	}
	
	public static void SaveLabSampleKindDao(String SampleKindCode,String SampleKind,String SampleKindID,String SampleKindNo) throws SQLException{
		Connection conn=null;
		CallableStatement proc = null;
		String wCall = null;
		wCall = "{call Pro_SaveLabSampleKind(?,?,?,?)}";
		try {
			conn = DBPool.ds_lmis.getConnection();
			proc = conn.prepareCall(wCall);
			proc.setString(1, SampleKindCode);
			proc.setString(2, SampleKind);
			proc.setString(3,SampleKindID);
			proc.setString(4,SampleKindNo);
			proc.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("", e);
		} catch (Exception e){
			log.error("", e);
		} finally{
			try {
				if(proc!=null)
					proc.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("", e);
			}
		}	
	}
	
	public static void SaveLabSamplePhysicalStateDao(String PhysicalStateName) throws SQLException{
		Connection conn=null;
		CallableStatement proc = null;
		String wCall = null;
		wCall = "{call Pro_SaveLabSamplePhysicalState(?)}";
		try {
			conn = DBPool.ds_lmis.getConnection();
			proc = conn.prepareCall(wCall);
			proc.setString(1, PhysicalStateName);
			proc.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("", e);
		} catch (Exception e){
			log.error("", e);
		}finally{
			try {
				if(proc!=null)
					proc.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("", e);
			}
		}	
	}
	
	public static void SaveLabSamplePreservationDao(String PreservationName) throws SQLException{
		Connection conn=null;
		CallableStatement proc = null;
		String wCall = null;
		wCall = "{call Pro_SaveLabSamplePreservation(?)}";
		try {
			conn = DBPool.ds_lmis.getConnection();
			proc = conn.prepareCall(wCall);
			proc.setString(1, PreservationName);
			proc.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("", e);
		} catch(Exception e){
			log.error("", e);
		} finally{
			try {
				if(proc!=null)
					proc.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("", e);
			}
		}	
	}
	
	public static void DelLabOutdatedParamDao() throws SQLException{
		Connection conn=null;
		CallableStatement proc = null;
		String wCall = null;
		wCall = "{call Pro_DelLabOutdatedParam()}";
		try {
			conn = DBPool.ds_lmis.getConnection();
			proc = conn.prepareCall(wCall);
			proc.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("", e);
		} catch(Exception e){
			log.error("", e);
		} finally{
			try {
				if(proc!=null)
					proc.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("", e);
			}
		}	
	}
	
}
