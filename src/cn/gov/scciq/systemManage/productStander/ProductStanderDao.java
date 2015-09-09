package cn.gov.scciq.systemManage.productStander;

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
import cn.gov.scciq.entmanage.entProductCheck.EntProductInputDetailBean;
import cn.gov.scciq.entmanage.entProductCheck.EntProductMaterialComparedBean;
import cn.gov.scciq.systemManage.inspOperator.InspOperatorByQueryBean;
import cn.gov.scciq.systemManage.inspOperator.InspOperatorInputBean;
import cn.gov.scciq.systemManage.inspOperator.InspOperatorService;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class ProductStanderDao {
	
	static String retStr = ConstantStr.SAVE_ERROR_MSG;
	private static Log log=LogFactory.getLog(ProductStanderDao.class);
	
	public static  Map<Integer, Object> getProductByQuery(ProductStanderInputDto bean,int start,int length) throws Exception {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0; 
		List<ProductStandByQueryDto> list = new ArrayList<ProductStandByQueryDto>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetProductByQuery(?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getProductCode());
			proc.setString(2, bean.getProductName());
			proc.setInt(3, start);
			proc.setInt(4, length);
			proc.setString(5, "ProductCode");
			proc.setString(6, "ASC");
			proc.registerOutParameter(7, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    ProductStandByQueryDto abean=null;
		     while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, ProductStandByQueryDto.class);
		    	list.add(abean);
		    }
		     recordsTotal=proc.getInt(7);
			    rsMap.put(1, recordsTotal);
	           rsMap.put(2, list);
		    log.info("Pro_GetProductByQuery-rsmap-"+list);
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
	
	public static Map<String, Object> getProductByCode(ProductStanderInputDto bean) throws Exception {
		 
		 Map<String, Object> map = new HashMap<String, Object>();
		 List<ProductStandByCodeOneDto> list = new ArrayList<ProductStandByCodeOneDto>();
		 List<ProductStandByCodeTwoDto> list2 = new ArrayList<ProductStandByCodeTwoDto>();
    	 Connection conn = DBPool.ds.getConnection();
		 CallableStatement proc = null;
		 ResultSet rs = null;
		 ResultSet rs2=null;
		try {
			String wCall = "{call Pro_GetProductByCode(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getProductCode());
			proc.execute();
		    rs = proc.getResultSet();
		    ProductStandByCodeOneDto abean=null;
		    
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, ProductStandByCodeOneDto.class);
		    	list.add(abean);
		    }
		     if(proc.getMoreResults()){
		    	 rs2=	proc.getResultSet();
		    	 ProductStandByCodeTwoDto pBean=null;
		    	 while(rs2.next()){
		    		pBean=RsToDtoUtil.tranRsToDto(rs2, ProductStandByCodeTwoDto.class);
			    	list2.add(pBean);
		    	    }
		    	 
		    	 
		     }
		     
		        map.put("one", list);
			    map.put("two", list2);
			  
		   
		    log.info("Pro_GetProductByQuery-rsmap-"+list);
		}catch (SQLException e) {
         // TODO Auto-generated catch block
         log.error("", e);
     } catch (Exception e) {
         log.error("", e);
     } finally{
         try {
             if(rs2 != null){
                 rs2.close();
             }
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
     
     return map;
		
		
	}
	
	public static String normalizeProductName(ProductStanderInputDto bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_NormalizeProductName(?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getProductCode());
			proc.setString(2, bean.getProductName());
			proc.registerOutParameter(3, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(3);
			log.info("Pro_NormalizeProductName--"+retStr);
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
	
	
	
}
