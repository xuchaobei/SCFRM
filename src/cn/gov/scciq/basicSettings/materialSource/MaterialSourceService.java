package cn.gov.scciq.basicSettings.materialSource;

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
import cn.gov.scciq.basicSettings.productClass.ProductClassServiceImpl;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;



public class MaterialSourceService  {
	 private static Log log=LogFactory.getLog(ProductClassServiceImpl.class);
	 String json = null;
	 String rowIndex ;
	 String pageSize;
	 String retStr = ConstantStr.SAVE_ERROR_MSG;
	// List<MaterialSourceBean> list = new ArrayList<MaterialSourceBean>();

	
	public String SaveMaterialSource(MaterialSourceBean bean)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			
			
			
			
			
			String wCall = "{call Pro_SaveMaterialSource(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getMaterialSourceID());
			proc.setString(2, bean.getSourceCode());
			proc.setString(3, bean.getSourceName());
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

	
	public String SaveMaterialSourceSub(MaterialSourceSetBean bean,String[] arr)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		
		try {
			String wCall = null;
			wCall = "{call Pro_SaveMaterialSource(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getMaterialSourceID());
			proc.setString(2, bean.getSourceCode());
			proc.setString(3, bean.getSourceName());
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
				
				wCall = "{call Pro_SaveMaterialSourceSub(?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, "0");
				proc.setString(2, bean.getSourceCode());
				proc.setString(3, arr[i]);
				proc.registerOutParameter(4, java.sql.Types.VARCHAR);
				proc.registerOutParameter(5, java.sql.Types.VARCHAR);
				proc.execute();
				proc.close();
				i++;
			}
			
			
			//retStr=proc.getString(4);
			log.info("Pro_SaveMaterialSourceSub--"+retStr);
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

	public String DelMaterialSource(MaterialSourceBean bean)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			
			String returnStr =null;
			
			
			String wCall = "{call Pro_DelMaterialSource(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getSourceCode());
			
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

	
	public Map<Integer, Object> GetMaterialSource(MaterialSourceBean bean,int showflg,int startindex,int pageSize)throws Exception {
		// TODO Auto-generated method stub
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<MaterialSourceBean> list = new ArrayList<MaterialSourceBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetMaterialSource(?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getSourceName());
			proc.setInt(2, showflg);
			proc.setInt(3, startindex);
			proc.setInt(4, pageSize);
			proc.setString(5, "MaterialSourceID"); //记录索引号
			proc.setString(6, "Desc"); //每页最大记录数
			proc.registerOutParameter(7, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    MaterialSourceBean materialClassBean=null;
		    while(rs.next()){
		    	materialClassBean=RsToDtoUtil.tranRsToDto(rs, MaterialSourceBean.class);
		    	list.add(materialClassBean);
		    }
		    recordsTotal=proc.getInt(7);
		    rsMap.put(1, recordsTotal);
            rsMap.put(2, list);
			log.info("Pro_GetMaterialSource-rsmap-"+rsMap);
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

	
	public  List<MaterialSourceSubBean> GetMaterialSourceSub(MaterialSourceSubBean bean)
			throws Exception {
		
		 List<MaterialSourceSubBean> list = new ArrayList<MaterialSourceSubBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetMaterialSourceSub(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getSourceCode());
			proc.execute();
		    rs = proc.getResultSet();
		    MaterialSourceSubBean materialSourceSubBean=null;
		    while(rs.next()){
		    	materialSourceSubBean=RsToDtoUtil.tranRsToDto(rs, MaterialSourceSubBean.class);
		    	list.add(materialSourceSubBean);
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
