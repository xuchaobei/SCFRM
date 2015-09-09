package cn.gov.scciq.basicSettings.intendedUse;

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
import cn.gov.scciq.basicSettings.materialSource.MaterialSourceSubBean;
import cn.gov.scciq.basicSettings.packageType.PackageTypeBean;
import cn.gov.scciq.basicSettings.packageType.PackageTypeService;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class IntendedUseService  {
	 private static Log log=LogFactory.getLog(PackageTypeService.class);
	 String json = null;
	 String rowIndex ;
	 String pageSize;
	 String retStr = ConstantStr.SAVE_ERROR_MSG;
	// List<IntendedUseBean> list = new ArrayList<IntendedUseBean>();

	
	public String SaveIntendedUse(IntendedUseBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_SaveIntendedUse(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getIntendedUseID());
			proc.setString(2, bean.getUseCode());
			proc.setString(3, bean.getUseName());
			proc.setString(4, bean.getIfSet());
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.registerOutParameter(6, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(5);
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

	
	public String SaveIntendedUseSub(IntendedUseSetBean bean,String[] arr) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		
		try {
			String wCall=null;
			wCall = "{call Pro_SaveIntendedUse(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getIntendedUseID());
			proc.setString(2, bean.getUseCode());
			proc.setString(3, bean.getUseName());
			proc.setString(4, bean.getIfSet());
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.registerOutParameter(6, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(5);
			proc.close();
			int i=0;
			System.out.println("sets is");
			System.out.println("setscuntent is ;"+arr.toString()+";"+arr.length);
			
			while(i<arr.length){
				wCall = "{call Pro_SaveIntendedUseSub(?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, "0");
				proc.setString(2, bean.getUseCode());
				proc.setString(3, arr[i]);
				proc.registerOutParameter(4, java.sql.Types.VARCHAR);
				proc.registerOutParameter(5, java.sql.Types.VARCHAR);
				proc.execute();
				proc.close();
				i++;
				
			}
			 
			
			
			
			
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

	
	public String DelIntendedUse(IntendedUseBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_DelIntendedUse(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getUseCode());
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

	
	public Map<Integer, Object> GetIntendedUse(IntendedUseBean bean,int showflg,int startindex,int pageSize) throws Exception {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<IntendedUseBean> list = new ArrayList<IntendedUseBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetIntendedUse(?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getUseName());
			proc.setInt(2, showflg);
			proc.setInt(3, startindex);
			proc.setInt(4, pageSize);
			proc.setString(5, "IntendedUseID"); //记录索引号
			proc.setString(6, "Desc"); //每页最大记录数
			proc.registerOutParameter(7, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    IntendedUseBean intendedUseBean=null;
		    while(rs.next()){
		    	intendedUseBean=RsToDtoUtil.tranRsToDto(rs, IntendedUseBean.class);
		    	list.add(intendedUseBean);
		    }
		    recordsTotal=proc.getInt(7);
		    rsMap.put(1, recordsTotal);
           rsMap.put(2, list);
			log.info("Pro_GetIntendedUse-rsmap-"+rsMap);
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

	
	public  List<IntendUseSubBean> GetIntendedUseSub(IntendUseSubBean bean) throws Exception {
		 List<IntendUseSubBean> list = new ArrayList<IntendUseSubBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetIntendedUseSub(?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getUseCode());
				proc.execute();
			    rs = proc.getResultSet();
			    IntendUseSubBean intendUseSubBean=null;
			    while(rs.next()){
			    	intendUseSubBean=RsToDtoUtil.tranRsToDto(rs, IntendUseSubBean.class);
			    	list.add(intendUseSubBean);
			    }
			   
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
	      
	      return list;
	}

}
