package cn.gov.scciq.systemManage.roleManage;

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
import org.apache.jasper.tagplugins.jstl.core.ForEach;

import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.RsToDtoUtil;

public class RoleManageService {
	
	String retStr = ConstantStr.SAVE_ERROR_MSG;
	private static Log log=LogFactory.getLog(RoleManageService.class);

	public Map<String, Object> GetMenuDefine() throws Exception{
		
		Map<String, Object> rsMap = new HashMap<String, Object>();
		 List<MenuDefineBean> list = new ArrayList<MenuDefineBean>();
		 List<SubMenuDefineBean> list2 = new ArrayList<SubMenuDefineBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetMenuDefine()}";
			proc = conn.prepareCall(wCall);
			proc.execute();
		    rs = proc.getResultSet();
		    MenuDefineBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, MenuDefineBean.class);
		    	list.add(abean);
		    }
		    for (MenuDefineBean menuDefineBean : list) {
		    	String menuName=menuDefineBean.getMenuName();
		    	System.out.println(menuDefineBean.getMenuName());
		    	 wCall = "{call Pro_GetSubMenuDefine(?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, menuName);
				
				proc.execute();
			    rs = proc.getResultSet();
			    SubMenuDefineBean subean=null;
			    while(rs.next()){
			    	subean=RsToDtoUtil.tranRsToDto(rs, SubMenuDefineBean.class);
			    	list2.add(subean);
			    }
		    	
				
			}
		       rsMap.put("one", list);
	           rsMap.put("two", list2);
			log.info("Pro_GetMenuDefine-this-rsmap-"+list+" list2"+list2);
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
	public List<SubMenuDefineBean>  GetSubMenuDefine(RoleManageInputBean bean) throws Exception{
		
		 List<SubMenuDefineBean> list = new ArrayList<SubMenuDefineBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetSubMenuDefine(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getMenuName());
			
			proc.execute();
		    rs = proc.getResultSet();
		    SubMenuDefineBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, SubMenuDefineBean.class);
		    	list.add(abean);
		    }
		  
			log.info("Pro_GetSubMenuDefine-this-rsmap-"+list);
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
	public String SaveRole(RoleManageInputBean bean,String str) throws Exception{
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_SaveRole(?,?,?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getRoleID());
			proc.setString(2, ContextUtil.getOrgCode());
			proc.setString(3, ContextUtil.getDeptCode());
			proc.setString(4, ContextUtil.getOperatorCode());
			proc.setString(5, bean.getRoleCode());
			proc.setString(6, bean.getRoleName());
			proc.setString(7, bean.getDataAccess());
			proc.setString(8, bean.getOperateLevel());
			proc.registerOutParameter(9, java.sql.Types.VARCHAR);
			proc.registerOutParameter(10, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(9);
			proc.close();
			
			String[] arry=str.split("@");
			
			for (String string : arry) {
				
				String[] menu=string.split("/");
				
				String menuName=menu[0];
				if(menu.length ==2){
					
				
				String[] submenu=menu[1].split(",");
				System.out.println("");
				String submenuName=null;
				String checked=null;
				String[] sub=null;
				for (int j = 0; j < submenu.length; j++) {
					sub=submenu[j].split(" ");
					submenuName=sub[0];
					checked=sub[1];
					wCall = "{call Pro_SaveRoleSubmenu(?,?,?,?)}";
					proc = conn.prepareCall(wCall);
					proc.setString(1, bean.getRoleCode());
					proc.setString(2, menuName);
					proc.setString(3, submenuName);
					proc.setString(4, checked);
					proc.execute();
				    proc.close();
					
				}
				}
			}
			
			
			log.info("Pro_SaveRole--"+retStr);
			
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
	public String SaveRoleSubmenu(RoleManageInputBean bean) throws Exception{
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_SaveRoleSubmenu(?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getRoleCode());
			proc.setString(2, bean.getMenuName());
			proc.setString(3, bean.getSubMenuName());
			proc.setString(4, bean.getOperateLimit());
			proc.execute();
			log.info("Pro_SaveRoleSubmenu--"+retStr);
			
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
	public  Map<String, Object> GetRoleMenuLimit(RoleManageInputBean bean) throws Exception{
		 Map<String, Object> rsMap = new HashMap<String, Object>();
		 List<RoleMenuLimitBean> list = new ArrayList<RoleMenuLimitBean>();
		 List<RoleSubmenuLimitBean> list2 = new ArrayList<RoleSubmenuLimitBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetRoleMenuLimit(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getRoleCode());
			proc.execute();
		    rs = proc.getResultSet();
		    RoleMenuLimitBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, RoleMenuLimitBean.class);
		    	list.add(abean);
		    }
		    for (RoleMenuLimitBean roleMenuLimitBean : list) {
		    	
		        wCall = "{call Pro_GetRoleSubmenuLimit(?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getRoleCode());
				proc.setString(2, roleMenuLimitBean.getMenuName());
				proc.execute();
			    rs = proc.getResultSet();
			    RoleSubmenuLimitBean subbean=null;
			    while(rs.next()){
			    	subbean=RsToDtoUtil.tranRsToDto(rs, RoleSubmenuLimitBean.class);
			    	list2.add(subbean);
			    }
			}
		       rsMap.put("one", list);
	           rsMap.put("two", list2);
			log.info("Pro_GetRoleMenuLimit-this-rsmap-"+list);
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
	public  List<RoleSubmenuLimitBean> GetRoleSubmenuLimit(RoleManageInputBean bean) throws Exception{
		
		 List<RoleSubmenuLimitBean> list = new ArrayList<RoleSubmenuLimitBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetRoleSubmenuLimit(?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getSubMenuName());
			proc.setString(2, bean.getOperateLimit());
			proc.execute();
		    rs = proc.getResultSet();
		    RoleSubmenuLimitBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, RoleSubmenuLimitBean.class);
		    	list.add(abean);
		    }
		   
			log.info("Pro_GetRoleSubmenuLimit-this-rsmap-"+list);
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
	public String DelRole(RoleManageInputBean bean) throws Exception{
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_DelRole(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getRoleCode());
			proc.setString(2, ContextUtil.getOrgCode());
			proc.setString(3, ContextUtil.getDeptCode());
			proc.setString(4, ContextUtil.getOperatorCode());
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(5);
			if("".equals(retStr)){
				retStr="true";
			}
			log.info("Pro_DelRole--"+retStr);
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
