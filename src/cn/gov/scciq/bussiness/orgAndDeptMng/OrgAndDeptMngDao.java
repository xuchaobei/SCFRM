package cn.gov.scciq.bussiness.orgAndDeptMng;

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

import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class OrgAndDeptMngDao {
    
    private static Log log=LogFactory.getLog(OrgAndDeptMngDao.class);
    
    /**
     * 取得所定义的检验机构
     * @return
     */
    public static Map<Integer, Object> getInspOrg( int startIndex, int pageSize){
        Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
        List<OrgDto> list = new ArrayList<OrgDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        int recordsTotal = 0;
        String call = "{call Pro_GetInspOrgPaging(?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setInt(1, startIndex);
            proc.setInt(2, pageSize);
            proc.registerOutParameter(3, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            OrgDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, OrgDto.class);
                list.add(dto);
            }
            recordsTotal = proc.getInt(3);
            rsMap.put(1, recordsTotal);
            rsMap.put(2, list);
        } catch (SQLException e) {
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
    
   
    /**
     * 保存检验机构
     * @return
     */
    public static String saveInspOrg(String inspOrgID, String orgCode, String orgName){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveInspOrg(?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, inspOrgID);
            proc.setString(2, orgCode);
            proc.setString(3, orgName);
            proc.registerOutParameter(4, Types.VARCHAR);
            proc.registerOutParameter(5, Types.INTEGER);
            proc.execute();
            retStr = proc.getString(4);
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
    
    /**
     * 删除检验机构
     * @return
     */
    public static boolean delInspOrg(String inspOrgID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelInspOrg(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, inspOrgID);
            proc.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            retCode = false;
            log.error("", e);
        } catch (Exception e) {
            retCode = false;
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
        
        return retCode;
    }
    
    /**
     * 根据机构代码得到所定义的检验部门
     * @return
     */
    public static Map<Integer, Object> getInspDeptPaging(String orgCode , int startIndex, int pageSize){
    	Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
        List<DeptDto> list = new ArrayList<DeptDto>();
        int recordsTotal = 0;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetInspDeptPaging(?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, orgCode);
            proc.setInt(2, startIndex);
            proc.setInt(3, pageSize);
            proc.registerOutParameter(4, java.sql.Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            DeptDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, DeptDto.class);
                list.add(dto);
            }
            recordsTotal = proc.getInt(4);
            rsMap.put(1, recordsTotal);
            rsMap.put(2, list);
        } catch (SQLException e) {
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
    
    /**
     * 保存检验部门
     * @return
     */
    public static String saveInspDept(String inspDeptID, String orgCode, String deptCode, String deptName){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveInspDept(?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, inspDeptID);
            proc.setString(2, orgCode);
            proc.setString(3, deptCode);
            proc.setString(4, deptName);
            proc.registerOutParameter(5, Types.VARCHAR);
            proc.registerOutParameter(6, Types.INTEGER);
            proc.execute();
            retStr = proc.getString(5);
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
    
    
    /**
     * 删除检验部门
     * @return
     */
    public static boolean delInspDept(String inspDeptID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelInspDept(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, inspDeptID);
            proc.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            retCode = false;
            log.error("", e);
        } catch (Exception e) {
            retCode = false;
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
        
        return retCode;
    }
}
