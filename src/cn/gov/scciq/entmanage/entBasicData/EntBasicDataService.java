package cn.gov.scciq.entmanage.entBasicData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
  
import cn.gov.scciq.entmanage.entBasicData.EntBasicDataBean;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.RsToDtoUtil;

public class EntBasicDataService {
	String retStr = ConstantStr.SAVE_ERROR_MSG;
	private static Log log=LogFactory.getLog(EntBasicDataService.class);
	public Map<Integer, Object> getEnt(EntBasicDataBean bean,String inspOrgCode,String roleCode, int start,int length) throws Exception{
		 Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<EntBasicDataBean> list = new ArrayList<EntBasicDataBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetEnt(?,?,?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getEntName());
			proc.setString(2, bean.getEntCode());
			proc.setString(3, ContextUtil.getOrgCode());/*bean.getMngOrgCode()*/
			proc.setString(4,ContextUtil.getDeptCode());
			proc.setString(5, ContextUtil.getOperatorCode());
			proc.setInt(6, start); //记录索引号
			proc.setInt(7, length); //每页最大记录数
			proc.setString(8, "EntID");
			proc.setString(9, "DESC");
			proc.registerOutParameter(10, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    EntBasicDataBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, EntBasicDataBean.class);
		    	list.add(abean);
		    }
		    recordsTotal=proc.getInt(10);
		    rsMap.put(1, recordsTotal);
            rsMap.put(2, list);
			log.info("Pro_GetEnt-rsmap-"+rsMap);
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
	public  List<InspOrgBean>  getInspOrg() throws Exception{
		    List<InspOrgBean> list = new ArrayList<InspOrgBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetInspOrg()}";
				proc = conn.prepareCall(wCall);
				proc.execute();
			    rs = proc.getResultSet();
			    InspOrgBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, InspOrgBean.class);
			    	list.add(abean);
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
	public String  delEnt(EntBasicDataBean bean ,String inspOrgCode,String roleCode) throws Exception{
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_DelEnt(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getEntCode());
			proc.setString(2, ContextUtil.getOrgCode());
			proc.setString(3, ContextUtil.getDeptCode());
			proc.setString(4, ContextUtil.getOperatorCode());
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(5);
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
	public String ResetEntPasswords(EntBasicDataBean bean,String inspOrgCode,String roleCode) throws Exception{
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_ResetEntPasswords(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getEntCode());
			proc.setString(2, ContextUtil.getOrgCode());
			proc.setString(3, ContextUtil.getDeptCode());
			proc.setString(4, ContextUtil.getOperatorCode());
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(5);
			log.info("Pro_ResetEntPasswords--"+retStr);
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
	public List<EvlLevelBean> getEvlLevel(EvlLevelBean bean) throws Exception{
		 List<EvlLevelBean> list = new ArrayList<EvlLevelBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetEvlLevel(?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getLevelType());
				proc.execute();
			    rs = proc.getResultSet();
			    EvlLevelBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, EvlLevelBean.class);
			    	list.add(abean);
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
	public String saveEnt(EntBasicDataBean bean,String roleCode) throws Exception{
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_SaveEnt(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getEntID());
			proc.setString(2, bean.getEntCode());
			proc.setString(3, bean.getEntName());
			proc.setString(4, bean.getAddress());
			proc.setString(5, bean.getSpecialMonitorReq());
			proc.setString(6, bean.getRiskCtrl());
			proc.setString(7, bean.getRegScore());
			proc.setString(8, bean.getSourceMngScore());
			proc.setString(9, bean.getCreditLevel());
			proc.setString(10, bean.getRemarks());
			proc.setString(11, bean.getMngOrgCode());
			proc.setString(12, ContextUtil.getOrgCode());
			proc.setString(13, ContextUtil.getDeptCode());
			proc.setString(14, ContextUtil.getOperatorCode());
			proc.registerOutParameter(15, java.sql.Types.VARCHAR);
			proc.registerOutParameter(16, java.sql.Types.INTEGER);
			proc.execute();
			retStr=proc.getString(15);
			log.info("Pro_SaveEnt--"+retStr);
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
	public  List<EntDetailBean> getEntDetail(EntDetailBean bean) throws Exception{
		
		 List<EntDetailBean> list = new ArrayList<EntDetailBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetEntDetail(?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getEntCode());
				proc.execute();
			    rs = proc.getResultSet();
			    EntDetailBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, EntDetailBean.class);
			    	list.add(abean);
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
	public String  getOperateLimit(String menuName) throws Exception{
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		 String roleCode = ContextUtil.getRoleCode();
		 System.out.println("rolecode"+roleCode);
		try {
			
			 	String wCall = "{call Pro_CheckOperateLimit(?,?,?,?,?)}";
			 	proc = conn.prepareCall(wCall);
			 	proc.setString(1, ContextUtil.getOrgCode());
	            proc.setString(2, ContextUtil.getDeptCode());
	            proc.setString(3, ContextUtil.getOperatorCode());
	            proc.setString(4, menuName);
	            proc.registerOutParameter(5, Types.VARCHAR);
	            proc.execute();
	            retStr=proc.getString(5);
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
	
	public List<MaterialSubsubclassByQueryBean> getmatriclall(EntProductMaterialComparedBean bean) throws Exception{
		List<MaterialSubsubclassByQueryBean> list = new ArrayList<MaterialSubsubclassByQueryBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetMaterialSubsubclassByQuery(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getMaterialName());
			proc.setInt(2,1);
			proc.setInt(3, 0);
			proc.setInt(4, 0);
			proc.registerOutParameter(5, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    MaterialSubsubclassByQueryBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, MaterialSubsubclassByQueryBean.class);
		    	list.add(abean);
		    }
		    log.info("Pro_GetMaterialSubsubclassByQuery-rsmap-"+list);
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
