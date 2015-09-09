package cn.gov.scciq.basicSettings.countryArea;

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

import cn.gov.scciq.basicSettings.packageType.PackageTypeSubBean;
import cn.gov.scciq.basicSettings.processingDegree.ProcessingDegreeBean;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;


public class CountryAreaService  {
	 private static Log log=LogFactory.getLog(CountryAreaService.class);
	 String json = null;
	 String rowIndex ;
	 String pageSize;
	 String retStr = ConstantStr.SAVE_ERROR_MSG;
	
	public String SaveCountry(CountryAreaBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_SaveCountry(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getCountryID());
			proc.setString(2, bean.getCountryCode());
			proc.setString(3, bean.getCountryName());
			proc.setString(4, bean.getIfSet());
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.registerOutParameter(6, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(5);
			log.info("Pro_SaveCountry--"+retStr);
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
	
	public String SaveCountrySub(CountryAreaSetBean bean,String[] arr) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		
		try {
			String wCall=null;
			wCall = "{call Pro_SaveCountry(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getCountryID());
			proc.setString(2, bean.getCountryCode());
			proc.setString(3, bean.getCountryName());
			proc.setString(4, bean.getIfSet());
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.registerOutParameter(6, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(5);
			proc.close();
			log.info("abcde");
			int i=0;
			
			while(i<arr.length){
				 wCall = "{call Pro_SaveCountrySub(?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, "0");
				proc.setString(2, bean.getCountryCode()); 	
				proc.setString(3, arr[i]);
				proc.registerOutParameter(4, java.sql.Types.VARCHAR);
				proc.registerOutParameter(5, java.sql.Types.VARCHAR);
				proc.execute();
				proc.close();
				i++;
			}
			
			
			log.info("Pro_SaveCountry--"+retStr);
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
	
	public String DelCountry(CountryAreaBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_DelCountry(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getCountryCode());
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
	
	
	public Map<Integer, Object> GetCountry(CountryAreaBean bean,int showflg,int startindex,int pageSize) throws Exception {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<CountryAreaBean> list = new ArrayList<CountryAreaBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetCountry(?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getCountryName());
			proc.setInt(2, showflg); //记录索引号
			proc.setInt(3, startindex);
			proc.setInt(4, pageSize); //每页最大记录数
			proc.setString(5, "countryName");
			proc.setString(6, "ASC");
			proc.registerOutParameter(7, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    CountryAreaBean countryAreaBean=null;
		    while(rs.next()){
		    	countryAreaBean=RsToDtoUtil.tranRsToDto(rs, CountryAreaBean.class);
		    	/*String ifset=countryAreaBean.getIfSet();
		    	if(ifset.equals("1")){
		    		countryAreaBean.setIfSet("是");
		    	}else{
		    		countryAreaBean.setIfSet("否");
		    	}*/
		    	list.add(countryAreaBean);
		    }
		    recordsTotal=proc.getInt(7);
		    rsMap.put(1, recordsTotal);
           rsMap.put(2, list);
			log.info("Pro_GetCountry-rsmap-"+rsMap);
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
	
	public List<CountryAreaSubBean> GetCountrySub(CountryAreaSubBean bean) throws Exception {
		 List<CountryAreaSubBean> list = new ArrayList<CountryAreaSubBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetCountrySub(?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getCountryCode());
				proc.execute();
			    rs = proc.getResultSet();
			    CountryAreaSubBean countryAreaSubBean=null;
			    while(rs.next()){
			    	countryAreaSubBean=RsToDtoUtil.tranRsToDto(rs, CountryAreaSubBean.class);
			    	list.add(countryAreaSubBean);
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
