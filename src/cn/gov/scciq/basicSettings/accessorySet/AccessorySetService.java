package cn.gov.scciq.basicSettings.accessorySet;

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

import cn.gov.scciq.basicSettings.materialClass.MaterialClassBean;
import cn.gov.scciq.basicSettings.packageType.PackageTypeService;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class AccessorySetService  {
	 private static Log log=LogFactory.getLog(AccessorySetService.class);
	 String json = null;
	 String rowIndex ;
	 String pageSize;
	 String retStr = ConstantStr.SAVE_ERROR_MSG;
	 


	
	public String SaveAccessory(AccessorySetBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_SaveAccessory(?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getAccessoryID());
			proc.setString(2, bean.getAccessoryName());
			proc.registerOutParameter(3, java.sql.Types.VARCHAR);
			proc.registerOutParameter(4, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(3);
			log.info("Pro_SaveMaterialClass--"+retStr);
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

	
	public String DelAccessory(AccessorySetBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_DelAccessory(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getAccessoryID());
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

	
	public Map<Integer, Object> GetAccessory(AccessorySetBean bean,int startindex,int pageSize) throws Exception {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<AccessorySetBean> list = new ArrayList<AccessorySetBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetAccessory(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getAccessoryName());
			proc.setInt(2, startindex);
			proc.setInt(3, pageSize);
			proc.setString(4, "AccessoryID"); //记录索引号
			proc.setString(5, "Desc"); //每页最大记录数
			proc.registerOutParameter(6, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    AccessorySetBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, AccessorySetBean.class);
		    	list.add(abean);
		    }
		    recordsTotal=proc.getInt(6);
		    rsMap.put(1, recordsTotal);
           rsMap.put(2, list);
			log.info("Pro_GetAccessory-rsmap-"+rsMap);
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
