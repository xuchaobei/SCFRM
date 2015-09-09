package cn.gov.scciq.basicSettings.productClass;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gov.scciq.dbpool.*;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;
import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProductClassServiceImpl {
	 private static Log log=LogFactory.getLog(ProductClassServiceImpl.class);
	 private String json = null;
	
	List<ProductClassBean> list = new ArrayList<ProductClassBean>();
	List<ProductClassBeanForExport> listE = new ArrayList<ProductClassBeanForExport>();
	public String saveProductClass(ProductClassBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		String retStr = ConstantStr.SAVE_ERROR_MSG;
		try {
			String wCall = "{call Pro_SaveProductClass(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getProductClassID());
			proc.setString(2, bean.getClassCode());
			proc.setString(3, bean.getClassName());
			proc.registerOutParameter(4, java.sql.Types.VARCHAR);
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(4);
			log.info("Pro_SaveProductClass--"+retStr);
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
	
	public String deleteProductClass(ProductClassBean bean)throws Exception {
		
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		String returnStr =null;
		try {
			String wCall = "{call Pro_DelProductClass(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getProductClassID());
			proc.execute();
			returnStr="true";
		}  catch (SQLException e) {
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
                log.error("", e);
            }
        }
		return returnStr;
	}
	
	public Map<Integer, Object> getProductClass(ProductClassBean bean,int startindex,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		 Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			
			String returnStr =null;
			String returnCount =null;
			String wCall = "{call Pro_GetProductClass(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setInt(1, startindex); //记录索引号
			proc.setInt(2, pageSize); //每页最大记录数
			proc.setString(3, "ProductClassID");
			proc.setString(4, "DESC");
			proc.registerOutParameter(5, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    ProductClassBean productClassbean=null;
		    while(rs.next()){
		    	/*ProductClassBean proBean = new ProductClassBean();
		    	proBean.setProductClassID(rs.getString("ProductClassID"));
		    	proBean.setClassCode(rs.getString("ClassCode"));
		    	proBean.setClassName(rs.getString("ClassName"));
		    	proBean.setSerialRank(rs.getString("SerialRank"));*/
		    	productClassbean=RsToDtoUtil.tranRsToDto(rs, ProductClassBean.class);
		    	list.add(productClassbean);
		    }
		    recordsTotal=proc.getInt(5);
		    rsMap.put(1, recordsTotal);
            rsMap.put(2, list);
			log.info("Pro_GetProductClass-rsmap-"+rsMap);
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
	
	public String saveSubProductClass(ProductClassBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		String retStr = ConstantStr.SAVE_ERROR_MSG;
		try {
			
			String returnStr =null;
			String returnCode =null;
			String wCall = "{call Pro_SaveProductSubclass(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getProductSubclassID());
			proc.setString(2, bean.getClassCode());
			proc.setString(3, bean.getSubclassCode());
			proc.setString(4, bean.getSubclassName());
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.registerOutParameter(6, java.sql.Types.INTEGER);
			proc.execute();
			retStr=proc.getString(5);
			log.info("Pro_SaveProductSubclass--"+retStr);
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
	
	public String deleteSubProductClass(ProductClassBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		String returnStr =null;
		try {
			String wCall = "{call Pro_DelProductSubClass(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getProductSubclassID());
			
			proc.execute();
		//	rs = proc.getResultSet();
			returnStr="true";
		}  catch (SQLException e) {
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
                log.error("", e);
            }
        }
		return returnStr;
	}
	
	public String getSubProductClass(ProductClassBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			
			String returnStr =null;
			String returnCount =null;
		
			String wCall = "{call Pro_GetProductSubclass(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getClassCode()); 
			proc.setString(2, bean.getRowIndex()); //记录索引号
			proc.setString(3, bean.getPageSize());  //每页最大记录数
			proc.setString(4, "ProductSubclassID");
			proc.setString(5, "asc");
			proc.registerOutParameter(6, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    while(rs.next()){
		    	ProductClassBean proBean = new ProductClassBean();
		    	proBean.setProductSubclassID(rs.getString("ProductSubclassID"));
		    	proBean.setClassCode(rs.getString("ClassCode"));
		    	proBean.setSubclassCode(rs.getString("SubclassCode"));
		    	proBean.setSubclassName(rs.getString("SubclassName"));
		    	proBean.setSerialRank(rs.getString("SerialRank"));
		    	list.add(proBean);
		    }
			returnStr=JSONArray.fromObject(list).toString();
			returnCount=proc.getString(6);
			json="{\"returnStr\":"+returnStr+",\"returnCount\":\""+returnCount+"\"}";
			log.info("Pro_GetProductSubclass--"+json);
		} catch (Exception e) {
			
			log.error("Pro_GetProductSubclass--"+e);
		}finally{
		    try{
			if(conn!=null)
			   conn.close();
			if(proc!=null)
				proc.close();
			if(rs!=null)
				rs.close();
		      }
			catch(Exception e){
				e.printStackTrace();	
			}
		}
		return json;
	}
	
	public Map<Integer, Object>  getProductClassForExport() throws Exception {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			String classCode =null;
			String wCall = "{call Pro_GetProductClass(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setInt(1, 0); //记录索引号
			proc.setInt(2, 0); //每页最大记录数
			proc.setString(3, "ProductClassID");
			proc.setString(4, "asc");
			proc.registerOutParameter(5, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    ProductClassBeanForExport proBean=null;
		    while(rs.next()){
		    	classCode=rs.getString("ClassCode");
		    	String wCall2 = "{call Pro_GetProductSubclass(?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall2);
				proc.setString(1, classCode); 
				proc.setInt(2, 0); //记录索引号
				proc.setInt(3, 0);  //每页最大记录数
				proc.setString(4, "ProductSubclassID");
				proc.setString(5, "asc");
				proc.registerOutParameter(6, java.sql.Types.INTEGER);
				proc.execute();
			    rs2 = proc.getResultSet();
			    while(rs2.next()){
			        proBean = new ProductClassBeanForExport();
			    	proBean.setClassCode(rs.getString("ClassCode"));
			    	proBean.setClassName(rs.getString("ClassName"));
			    	proBean.setSubclassCode(rs2.getString("SubclassCode"));
			    	proBean.setSubclassName(rs2.getString("SubclassName"));
			    	listE.add(proBean);
			    }
			    recordsTotal=proc.getInt(6);
			    rsMap.put(1, recordsTotal);
	            rsMap.put(2, listE);
		    }
		    
			log.info("Pro_GetProductClassForExport-rsmap-"+rsMap);
		} catch (Exception e) {
			
			log.error("Pro_GetProductSubclass--"+e);
		}finally{
		    try{
			if(conn!=null)
			   conn.close();
			if(proc!=null)
				proc.close();
			if(rs!=null)
				rs.close();
		      }
			catch(Exception e){
				e.printStackTrace();	
			}
		}
		return rsMap;
	}

}
