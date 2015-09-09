package cn.gov.scciq.basicSettings.packageType;

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
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class PackageTypeService  {
	 private static Log log=LogFactory.getLog(PackageTypeService.class);
	 String json = null;
	 String rowIndex ;
	 String pageSize;
	 String retStr = ConstantStr.SAVE_ERROR_MSG;
	// List<PackageTypeBean> list = new ArrayList<PackageTypeBean>();

	
	public String SavePackageType(PackageTypeBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_SavePackageType(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getPackageTypeID());
			proc.setString(2, bean.getTypeCode());
			proc.setString(3, bean.getTypeName());
			proc.setString(4, bean.getIfSet());
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.registerOutParameter(6, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(5);
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


	public String SavePackageTypeSub(PackageTypeSetBean bean,String[] arr) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			
			String wCall = null;
			wCall = "{call Pro_SavePackageType(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getPackageTypeID());
			proc.setString(2, bean.getTypeCode());
			proc.setString(3, bean.getTypeName());
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
				 wCall = "{call Pro_SavePackageTypeSub(?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, "0");
				proc.setString(2, bean.getTypeCode());
				proc.setString(3, arr[i]);
				proc.registerOutParameter(4, java.sql.Types.VARCHAR);
				proc.registerOutParameter(5, java.sql.Types.VARCHAR);
				proc.execute();
				proc.close();
				i++;
			}
			
			
			log.info("Pro_SavePackageTypeSub--"+retStr);
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

	
	public String DelPackageType(PackageTypeBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_DelPackageType(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getTypeCode());
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

	
	public Map<Integer, Object> GetPackageType(PackageTypeBean bean,int showflg,int startindex,int pageSize) throws Exception {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<PackageTypeBean> list = new ArrayList<PackageTypeBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetPackageType(?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getTypeName());
			proc.setInt(2, showflg);
			proc.setInt(3, startindex);
			proc.setInt(4, pageSize);
			proc.setString(5, "PackageTypeID"); //记录索引号
			proc.setString(6, "Desc"); //每页最大记录数
			proc.registerOutParameter(7, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    PackageTypeBean packageTypeBean=null;
		    while(rs.next()){
		    	packageTypeBean=RsToDtoUtil.tranRsToDto(rs, PackageTypeBean.class);
		    	list.add(packageTypeBean);
		    }
		    recordsTotal=proc.getInt(7);
		    rsMap.put(1, recordsTotal);
           rsMap.put(2, list);
			log.info("Pro_GetPackageType-rsmap-"+rsMap);
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

	
	public List<PackageTypeSubBean> GetPackageTypeSub(PackageTypeSubBean bean) throws Exception {
		 List<PackageTypeSubBean> list = new ArrayList<PackageTypeSubBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetPackageTypeSub(?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getTypeCode());
				proc.execute();
			    rs = proc.getResultSet();
			    PackageTypeSubBean packageTypeSubBean=null;
			    while(rs.next()){
			    	packageTypeSubBean=RsToDtoUtil.tranRsToDto(rs, PackageTypeSubBean.class);
			    	list.add(packageTypeSubBean);
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
