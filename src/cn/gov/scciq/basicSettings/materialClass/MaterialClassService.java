package cn.gov.scciq.basicSettings.materialClass;

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


import cn.gov.scciq.basicSettings.processingWay.ProcessingWayBean;
import cn.gov.scciq.basicSettings.productClass.ProductClassServiceImpl;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class MaterialClassService  {
	 private static Log log=LogFactory.getLog(ProductClassServiceImpl.class);
	 String json = null;
	 String rowIndex ;
	 String pageSize;
	// List<MaterialClassBean> list = new ArrayList<MaterialClassBean>();
	 String retStr = ConstantStr.SAVE_ERROR_MSG;
	
	public String SaveMaterialClass(MaterialClassBean bean)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_SaveMaterialClass(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getMaterialClassID());
			proc.setString(2, bean.getClassCode());
			proc.setString(3, bean.getClassName());
			proc.registerOutParameter(4, java.sql.Types.VARCHAR);
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(4);
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

	
	public String SaveMaterialSubclass(MaterialSubclassBean bean)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String returnCode =null;
			String wCall = "{call Pro_SaveMaterialSubclass(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getMaterialSubclassID());
			proc.setString(2, bean.getClassCode	());
			proc.setString(3, bean.getSubclassCode());
			proc.setString(4, bean.getSubclassName());
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.registerOutParameter(6, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(5);
			log.info("Pro_SaveMaterialSubclass--"+retStr);
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

	
	public String SaveMaterialSubsubclass(MaterialSubsubClassBean bean)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_SaveMaterialSubsubclass(?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getMaterialID());
			proc.setString(2, bean.getClassCode	());
			proc.setString(3, bean.getSubclassCode());
			proc.setString(4, bean.getMaterialCode());
			proc.setString(5, bean.getMaterialName());
			proc.setString(6, bean.getIfSet());
			proc.registerOutParameter(7, java.sql.Types.VARCHAR);
			proc.registerOutParameter(8, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(7);
			log.info("Pro_SaveMaterialSubsubclass--"+retStr);
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

	
	public String SaveMaterialSubsubclassSub(MaterialSubsubClassBean bean,String[] ar)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		String wCall=null;
		try {
			 wCall = "{call Pro_SaveMaterialSubsubclass(?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getMaterialID());
			proc.setString(2, bean.getClassCode	());
			proc.setString(3, bean.getSubclassCode());
			proc.setString(4, bean.getMaterialCode());
			proc.setString(5, bean.getMaterialName());
			proc.setString(6, bean.getIfSet());
			proc.registerOutParameter(7, java.sql.Types.VARCHAR);
			proc.registerOutParameter(8, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(7);
			proc.close();
			//
			int i=0;
			while(i<ar.length){
			 wCall = "{call Pro_SaveMaterialSubsubclassSub(?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setInt(1, 0);
			proc.setString(2, bean.getClassCode	());
			proc.setString(3, bean.getSubclassCode());
			proc.setString(4, bean.getMaterialCode());
			proc.setString(5, ar[i]);
			proc.registerOutParameter(6, java.sql.Types.VARCHAR);
			proc.registerOutParameter(7, java.sql.Types.VARCHAR);
			proc.execute();
			proc.close();
			i++;
			}
			
			log.info("Pro_SaveMaterialSubsubclassSub--"+retStr);
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

	
	public String DelMaterialClass(MaterialClassBean bean)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_DelMaterialClass(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getClassCode());
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
	
	public String DelMaterialSubclass(MaterialSubclassBean bean)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_DelMaterialSubclass(?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getClassCode());
			proc.setString(2, bean.getSubclassCode());
			proc.execute();
			retStr="true";
		}catch (SQLException e) {
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
        return retStr;
	}

	 
	public String DelMaterialSubsubclass(MaterialSubsubClassBean bean)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_DelMaterialSubsubclass(?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getClassCode());
			proc.setString(2, bean.getSubclassCode());
			proc.setString(3, bean.getMaterialCode());
			proc.execute();
			retStr="true";
		}catch (SQLException e) {
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
	public Map<Integer, Object> getMaterialClass(MaterialClassBean bean,int startindex,int pageSize) throws Exception{
		 Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<MaterialClassBean> list = new ArrayList<MaterialClassBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetMaterialClass(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setInt(1, startindex);
			proc.setInt(2, pageSize);
			proc.setString(3, "MaterialClassID"); //记录索引号
			proc.setString(4, "Desc"); //每页最大记录数
			proc.registerOutParameter(5, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    MaterialClassBean materialClassBean=null;
		    while(rs.next()){
		    	materialClassBean=RsToDtoUtil.tranRsToDto(rs, MaterialClassBean.class);
		    	list.add(materialClassBean);
		    }
		    recordsTotal=proc.getInt(5);
		    rsMap.put(1, recordsTotal);
            rsMap.put(2, list);
			log.info("Pro_GetMaterialClass-rsmap-"+rsMap);
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
	public Map<Integer, Object> getMaterialSubclass(MaterialSubclassBean bean,int startindex,int pageSize) throws Exception{
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<MaterialSubclassBean> list = new ArrayList<MaterialSubclassBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetMaterialSubclass(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getClassCode());
			proc.setInt(2, startindex);
			proc.setInt(3, pageSize);
			proc.setString(4, "MaterialSubclassID"); //记录索引号
			proc.setString(5, "Desc"); //每页最大记录数
			proc.registerOutParameter(6, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    MaterialSubclassBean materialSubclassBean=null;
		    while(rs.next()){
		    	materialSubclassBean=RsToDtoUtil.tranRsToDto(rs, MaterialSubclassBean.class);
		    	list.add(materialSubclassBean);
		    }
		    recordsTotal=proc.getInt(6);
		    rsMap.put(1, recordsTotal);
           rsMap.put(2, list);
			log.info("Pro_GetMaterialSubclass-rsmap-"+rsMap);
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
	public Map<Integer, Object> getMaterialSubsubClass(MaterialSubsubClassBean bean,int startindex,int pageSize) throws Exception{
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<MaterialSubsubClassBean> list = new ArrayList<MaterialSubsubClassBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetMaterialSubsubclass(?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getClassCode());
			proc.setString(2, bean.getSubclassCode());
			proc.setInt(3, 0);
			proc.setInt(4, startindex); //记录索引号
			proc.setInt(5, pageSize); //每页最大记录数
			proc.setString(6, "MaterialID"); //记录索引号
			proc.setString(7, "DESC");
			proc.registerOutParameter(8, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    MaterialSubsubClassBean materialSubsubClassBean=null;
		    while(rs.next()){
		    	materialSubsubClassBean=RsToDtoUtil.tranRsToDto(rs, MaterialSubsubClassBean.class);
		    	String ifset=materialSubsubClassBean.getIfSet();
		    	if(ifset.equals("1")){
		    		materialSubsubClassBean.setIfSet("是");
		    	}else{
		    		materialSubsubClassBean.setIfSet("否");
		    	}
		    	list.add(materialSubsubClassBean);
		    }
		    recordsTotal=proc.getInt(8);
		    rsMap.put(1, recordsTotal);
          rsMap.put(2, list);
			log.info("Pro_GetMaterialSubsubclass-rsmap-"+rsMap);
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
	public  List<MaterialSubsubClassBean> getMaterialSubsubClasslist(MaterialSubsubClassBean bean) throws Exception{
		
		 int recordsTotal = 0;
		 List<MaterialSubsubClassBean> list = new ArrayList<MaterialSubsubClassBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetMaterialSubsubclass(?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getClassCode());
			proc.setString(2, bean.getSubclassCode());
			proc.setInt(3, 1);
			proc.setInt(4, 0); //记录索引号
			proc.setInt(5, 0); //每页最大记录数
			proc.setString(6, "MaterialID"); //记录索引号
			proc.setString(7, "DESC");
			proc.registerOutParameter(8, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    MaterialSubsubClassBean materialSubsubClassBean=null;
		    while(rs.next()){
		    	materialSubsubClassBean=RsToDtoUtil.tranRsToDto(rs, MaterialSubsubClassBean.class);
		    	String ifset=materialSubsubClassBean.getIfSet();
		    	list.add(materialSubsubClassBean);
		    }
		  
			log.info("Pro_GetMaterialSubsubclass-rsmap-");
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
	public List<MaterialSubsubclassSubBean> getMaterialSubsubclassSub(MaterialSubsubclassSubBean bean) throws Exception{
		 int recordsTotal = 0;
		 List<MaterialSubsubclassSubBean> list = new ArrayList<MaterialSubsubclassSubBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetMaterialSubsubclassSub(?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getClassCode());
			proc.setString(2, bean.getSubclassCode());
			proc.setString(3, bean.getMaterialCode());
			proc.execute();
		    rs = proc.getResultSet();
		    MaterialSubsubclassSubBean materialSubsubclassSubBean=null;
		    while(rs.next()){
		    	materialSubsubclassSubBean=RsToDtoUtil.tranRsToDto(rs, MaterialSubsubclassSubBean.class);
		    	list.add(materialSubsubclassSubBean);
		    }
			log.info("Pro_GetMaterialSubsubclass-rsmap-"+list);
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
	
	public Map<Integer, Object> getMaterialClassForExport() throws Exception{
		 Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<MaterialClassForExportBean> list = new ArrayList<MaterialClassForExportBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String classCode=null;
		String subclassCode=null;
		try {
			String wCall = "{call Pro_GetMaterialClass(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setInt(1, 0);
			proc.setInt(2, 0);
			proc.setString(3, "MaterialClassID"); //记录索引号
			proc.setString(4, "ASC"); //每页最大记录数
			proc.registerOutParameter(5, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    while(rs.next()){
                classCode=rs.getString("ClassCode");
				String wCall2 = "{call Pro_GetMaterialSubclass(?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall2);
				proc.setString(1, classCode);
				proc.setInt(2, 0);
				proc.setInt(3, 0);
				proc.setString(4, "MaterialSubclassID"); //记录索引号
				proc.setString(5, "ASC"); //每页最大记录数
				proc.registerOutParameter(6, java.sql.Types.INTEGER);
				proc.execute();
			    rs2 = proc.getResultSet();
			    while(rs2.next()){
			    	 subclassCode=rs2.getString("SubclassCode");

					String wCall3 = "{call Pro_GetMaterialSubsubclass(?,?,?,?,?,?,?,?)}";
					proc = conn.prepareCall(wCall3);
					proc.setString(1, classCode);
					proc.setString(2, subclassCode);
					proc.setInt(3, 0);
					proc.setInt(4, 0); //记录索引号
					proc.setInt(5, 0); //每页最大记录数
					proc.setString(6, "MaterialID"); //记录索引号
					proc.setString(7, "ASC");
					proc.registerOutParameter(8, java.sql.Types.INTEGER);
					proc.execute();
				    rs3 = proc.getResultSet();
				    MaterialClassForExportBean bean=null;
				    while(rs3.next()){
				    	bean=new MaterialClassForExportBean();
				    	bean.setClassCode(rs.getString("ClassCode"));
				    	bean.setClassName(rs.getString("ClassName"));
				    	bean.setSubclassCode(rs2.getString("SubclassCode"));
				    	bean.setSubclassName(rs2.getString("SubclassName"));
				    	bean.setMaterialCode(rs3.getString("MaterialCode"));
				    	bean.setMaterialName(rs3.getString("MaterialName"));
				    	list.add(bean);
				    }
				    recordsTotal=proc.getInt(8);
			    }
			  
		    }
		   
		    rsMap.put(1, recordsTotal);
           rsMap.put(2, list);
			log.info("Pro_GetMaterialClass-rsmap-"+rsMap);
		}catch (SQLException e) {
           // TODO Auto-generated catch block
           log.error("", e);
       } catch (Exception e) {
           log.error("", e);
       } finally{
           try {
        	   if(rs3 != null){
                   rs3.close();
               }
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
       
       return rsMap;
		
	}
	
	
}
