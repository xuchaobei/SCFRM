package cn.gov.scciq.basicSettings.additivePurposeSet;

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

import cn.gov.scciq.basicSettings.accessorySet.AccessorySetBean;
import cn.gov.scciq.basicSettings.additiveSet.AdditiveSetBean;
import cn.gov.scciq.basicSettings.packageType.PackageTypeService;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class AdditivePurposeService {
	 private static Log log=LogFactory.getLog(PackageTypeService.class);
	 String json = null;
	 String rowIndex ;
	 String pageSize;
	 String retStr = ConstantStr.SAVE_ERROR_MSG;

	
	public String SaveAdditivePurpose(AdditivePurposeBean bean)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			
			String returnStr =null;
			String returnCode =null;
		//	ResultSet rs = null;
			
			String wCall = "{call Pro_SaveAdditivePurpose(?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getAdditivePurposeID());
			proc.setString(2, bean.getPurpose());
			proc.registerOutParameter(3, java.sql.Types.VARCHAR);
			proc.registerOutParameter(4, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(3);
			log.info("Pro_SaveAdditive--"+retStr);
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

	
	public String DelAdditivePurpose(AdditivePurposeBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			
			String returnStr =null;
			
			
			String wCall = "{call Pro_DelAdditivePurpose(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getAdditivePurposeID());
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

	
	public Map<Integer, Object> GetAdditivePurpose(AdditivePurposeBean bean,int startindex,int pageSize) throws Exception {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<AdditivePurposeBean> list = new ArrayList<AdditivePurposeBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetAdditivePurpose(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getPurpose());
			proc.setInt(2, startindex);
			proc.setInt(3, pageSize);
			proc.setString(4, "AdditivePurposeID"); //记录索引号
			proc.setString(5, "Desc"); //每页最大记录数
			proc.registerOutParameter(6, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    AdditivePurposeBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, AdditivePurposeBean.class);
		    	list.add(abean);
		    }
		    recordsTotal=proc.getInt(6);
		    rsMap.put(1, recordsTotal);
         rsMap.put(2, list);
			log.info("Pro_GetAdditive-rsmap-"+rsMap);
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
	public Map<Integer, Object> GetAdditivePurposeForExport() throws Exception {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<AdditivePurposeForExportBean> list = new ArrayList<AdditivePurposeForExportBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetAdditivePurpose(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, "");
			proc.setInt(2, 0);
			proc.setInt(3, 0);
			proc.setString(4, "AdditivePurposeID"); //记录索引号
			proc.setString(5, "ASC"); //每页最大记录数
			proc.registerOutParameter(6, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    AdditivePurposeForExportBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, AdditivePurposeForExportBean.class);
		    	list.add(abean);
		    }
		    recordsTotal=proc.getInt(6);
		    rsMap.put(1, recordsTotal);
         rsMap.put(2, list);
			log.info("Pro_GetAdditivepurposeexprt-rsmap-"+rsMap);
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
