package cn.gov.scciq.systemManage.inspOperator;

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
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.RsToDtoUtil;
import com.opensymphony.xwork2.Action;

public class InspOperatorService {
	String retStr = ConstantStr.SAVE_ERROR_MSG;
	private static Log log=LogFactory.getLog(InspOperatorService.class);

	
	public Map<Integer, Object> GetInspOperatorByQuery(InspOperatorInputBean bean,int start,int length) throws Exception {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<InspOperatorByQueryBean> list = new ArrayList<InspOperatorByQueryBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetInspOperatorByQuery(?,?,?,?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, ContextUtil.getOrgCode());
			proc.setString(2, ContextUtil.getDeptCode());
			proc.setString(3, ContextUtil.getOperatorCode());
			proc.setString(4,bean.getOrgCode());
			proc.setString(5, bean.getDeptCode());
			proc.setString(6, bean.getOperatorName());
			proc.setInt(7, start); //记录索引号
			proc.setInt(8, length); //每页最大记录数
			proc.setString(9, "OrgCode");
			proc.setString(10, "DESC");
			proc.registerOutParameter(11, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    InspOperatorByQueryBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, InspOperatorByQueryBean.class);
		    	list.add(abean);
		    }
		    recordsTotal=proc.getInt(11);
		    rsMap.put(1, recordsTotal);
           rsMap.put(2, list);
			log.info("Pro_GetInspOperatorByQuery-this-rsmap-"+rsMap+" rolecode"+ContextUtil.getRoleCode());
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
	public String SaveInspOperator(InspOperatorInputBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_SaveInspOperator(?,?,?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getOperatorID());
			proc.setString(2, ContextUtil.getOrgCode());
			proc.setString(3, ContextUtil.getDeptCode());
			proc.setString(4, ContextUtil.getOperatorCode());
			proc.setString(5, bean.getOrgCode());
			proc.setString(6, bean.getDeptCode());
			proc.setString(7, bean.getOperatorCode());
			proc.setString(8, bean.getOperatorName());
			proc.registerOutParameter(9, java.sql.Types.VARCHAR);
			proc.registerOutParameter(10, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(9);
			log.info("Pro_SaveInspOperator--"+retStr);
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
	public String SaveInspOperatorRole(InspOperatorInputBean bean,String orgCode,String deptCode,String operatorCode) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_SaveInspOperatorRole(?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, orgCode);
			proc.setString(2, deptCode);
			proc.setString(3, operatorCode);
			proc.setString(4, bean.getRoleCode());
			proc.execute();
			retStr="true";
			log.info("Pro_SaveInspOperatorRole--"+retStr);
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
	public String DelInspOperatorRole(InspOperatorInputBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_DelInspOperatorRole(?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getOrgCode());
			proc.setString(2, bean.getDeptCode());
			proc.setString(3, bean.getOperatorCode());        
			proc.execute();
			retStr="true";
			log.info("DelInspOperatorRole--"+retStr);
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
	public List<InspOrgPagingBean> GetInspOrgPaging() throws Exception {
		List<InspOrgPagingBean> list = new ArrayList<InspOrgPagingBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetInspOrgPaging(?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, "0");
			proc.setString(2, "0");
			proc.registerOutParameter(3, java.sql.Types.VARCHAR);
			proc.execute();
		    rs = proc.getResultSet();
		    InspOrgPagingBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, InspOrgPagingBean.class);
		    	list.add(abean);
		    }
		    log.info("Pro_GetInspOrgPaging-rsmap-"+list);
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
	public List<InspDeptPagingBean> GetInspDeptPaging(InspOperatorInputBean bean) throws Exception {
		List<InspDeptPagingBean> list = new ArrayList<InspDeptPagingBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetInspDeptPaging(?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getOrgCode());
			proc.setString(2, "0");
			proc.setString(3, "0");
			proc.registerOutParameter(4, java.sql.Types.VARCHAR);
			proc.execute();
		    rs = proc.getResultSet();
		    InspDeptPagingBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, InspDeptPagingBean.class);
		    	list.add(abean);
		    }
		    log.info("Pro_GetInspDeptPaging-rsmap-"+list);
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
			public List<RoleDefineBean> GetRoleDefine() throws Exception {
				List<RoleDefineBean> list = new ArrayList<RoleDefineBean>();
				Connection conn = DBPool.ds.getConnection();
				CallableStatement proc = null;
				ResultSet rs = null;
				try {
					String wCall = "{call Pro_GetRoleDefine()}";
					proc = conn.prepareCall(wCall);
					proc.execute();
				    rs = proc.getResultSet();
				    RoleDefineBean abean=null;
				    while(rs.next()){
				    	abean=RsToDtoUtil.tranRsToDto(rs, RoleDefineBean.class);
				    	list.add(abean);
				    }
				    log.info("Pro_GetRoleDefine-rsmap-"+list);
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
		public List<InspOperatorByQueryBean> GetInspOperatorByID(InspOperatorInputBean bean) throws Exception {
			List<InspOperatorByQueryBean> list = new ArrayList<InspOperatorByQueryBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetInspOperatorByID(?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getOperatorID());
				proc.execute();
			    rs = proc.getResultSet();
			    InspOperatorByQueryBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, InspOperatorByQueryBean.class);
			    	list.add(abean);
			    }
			    log.info("Pro_GetInspOperatorByID-rsmap-"+list);
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
		public List<InspOperatorRoleBean> GetInspOperatorRole(InspOperatorInputBean bean) throws Exception {
			List<InspOperatorRoleBean> list = new ArrayList<InspOperatorRoleBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				System.out.println("name "+bean.getOrgCode()+bean.getDeptCode()+bean.getOperatorCode());
				String wCall = "{call Pro_GetInspOperatorRole(?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getOrgCode());
				proc.setString(2, bean.getDeptCode());
				proc.setString(3, bean.getOperatorCode());
				proc.execute();
			    rs = proc.getResultSet();
			    InspOperatorRoleBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, InspOperatorRoleBean.class);
			    	list.add(abean);
			    }
			    log.info("Pro_GetInspOperatorRole-rsmap-"+list);
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
		public String DelInspOperator(InspOperatorInputBean bean) throws Exception {
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			try {
				String wCall = "{call Pro_DelInspOperator(?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getOperatorID());
				proc.execute();
				retStr="true";
				log.info("Pro_DelInspOperator--"+retStr);
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
		public String CheckSaveInspOperatorRole(InspOperatorInputBean bean) throws Exception {
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			try {
				String wCall = "{call Pro_CheckSaveInspOperatorRole(?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, ContextUtil.getOrgCode());
				proc.setString(2, ContextUtil.getDeptCode());
				proc.setString(3, ContextUtil.getOperatorCode());
				proc.setString(4, bean.getRoleCode());
				proc.registerOutParameter(5, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(5);
				if("".equals(retStr)){
					retStr="true";
				}
				log.info("Pro_CheckSaveInspOperatorRole--"+retStr);
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
		public String SetInspOperatorPassword(InspOperatorInputBean bean) throws Exception {
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			try {
				String wCall = "{call Pro_SetInspOperatorPassword(?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getOperatorID());
				proc.registerOutParameter(2, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(2);
				log.info("Pro_SetInspOperatorPassword--"+retStr);
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
