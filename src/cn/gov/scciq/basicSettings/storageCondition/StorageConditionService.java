package cn.gov.scciq.basicSettings.storageCondition;

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

import cn.gov.scciq.basicSettings.processingDegree.ProcessingDegreeBean;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class StorageConditionService {
	 private static Log log=LogFactory.getLog(StorageConditionService.class);
	 String json = null;
	 String rowIndex ;
	 String pageSize;
	 String retStr = ConstantStr.SAVE_ERROR_MSG;
	 //List<StorageConditionBean> list = new ArrayList<StorageConditionBean>();
	
	
	public String SaveStorageCondition(StorageConditionBean bean)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_SaveStorageCondition(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getStorageConditionID());
			proc.setString(2, bean.getConditionCode());
			proc.setString(3, bean.getConditionName());
			proc.registerOutParameter(4, java.sql.Types.VARCHAR);
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(4);
			log.info("Pro_SaveStorageCondition--"+retStr);
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
	
	public String DelStorageCondition(StorageConditionBean bean)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_DelStorageCondition(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getStorageConditionID());
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
	
	public Map<Integer, Object> GetStorageCondition(int startindex,int pageSize)
			throws Exception {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<StorageConditionBean> list = new ArrayList<StorageConditionBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetStorageCondition(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setInt(1, startindex); //记录索引号
			proc.setInt(2, pageSize); //每页最大记录数
			proc.setString(3, "StorageConditionID");
			proc.setString(4, "DESC");
			proc.registerOutParameter(5, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    StorageConditionBean storageConditionBean=null;
		    while(rs.next()){
		    	storageConditionBean=RsToDtoUtil.tranRsToDto(rs, StorageConditionBean.class);
		    	list.add(storageConditionBean);
		    }
		    recordsTotal=proc.getInt(5);
		    rsMap.put(1, recordsTotal);
           rsMap.put(2, list);
			log.info("Pro_GetStorageCondition-rsmap-"+rsMap);
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
