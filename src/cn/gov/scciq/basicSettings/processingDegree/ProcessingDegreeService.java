package cn.gov.scciq.basicSettings.processingDegree;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class ProcessingDegreeService {
	 private static Log log=LogFactory.getLog(ProcessingDegreeService.class);
	 String json = null;
	 String rowIndex ;
	 String pageSize;
	 String retStr = ConstantStr.SAVE_ERROR_MSG;
	// List<ProcessingDegreeBean> list = new ArrayList<ProcessingDegreeBean>();
	
	public String SaveProcessingDegree(ProcessingDegreeBean bean)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_SaveProcessingDegree(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getProcessingDegreeID());
			proc.setString(2, bean.getDegreeCode());
			proc.setString(3, bean.getDegreeName());
			proc.registerOutParameter(4, java.sql.Types.VARCHAR);
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(4);
			log.info("Pro_SavePackageType--"+retStr);
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
	
	public String DelProcessingDegree(ProcessingDegreeBean bean)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			
			String returnStr =null;
			
			
			String wCall = "{call Pro_DelProcessingDegree(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getProcessingDegreeID());
			proc.execute();
			retStr="true";
		}catch (SQLException e) {
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
	
	public Map<Integer, Object> GetProcessingDegree(int startindex,int pageSize)
			throws Exception {
		 Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<ProcessingDegreeBean> list = new ArrayList<ProcessingDegreeBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetProcessingDegree(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setInt(1, startindex); //记录索引号
			proc.setInt(2, pageSize); //每页最大记录数
			proc.setString(3, "ProcessingDegreeID");
			proc.setString(4, "DESC");
			proc.registerOutParameter(5, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    ProcessingDegreeBean processingDegreeBean=null;
		    while(rs.next()){
		    	processingDegreeBean=RsToDtoUtil.tranRsToDto(rs, ProcessingDegreeBean.class);
		    	list.add(processingDegreeBean);
		    }
		    recordsTotal=proc.getInt(5);
		    rsMap.put(1, recordsTotal);
            rsMap.put(2, list);
			log.info("Pro_GetProcessingDegree-rsmap-"+rsMap);
		}catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
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
        
        return rsMap;
	}

}
