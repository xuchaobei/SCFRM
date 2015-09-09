package cn.gov.scciq.bussiness.ctrlPlan;

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
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.RsToDtoUtil;

public class CtrlPlanDao {
    
    private static Log log=LogFactory.getLog(CtrlPlanDao.class);
    
    /**
     * 取得监控计划数据
     * @return
     */
    public static Map<Integer, Object> getCtrlPlanImp(String ctrlPlanName, String entName, String productName, int startIndex, int pageSize, String orderWord, String orderDirection){
        Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
        List<CtrlPlanDto> list = new ArrayList<CtrlPlanDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        int recordsTotal = 0;
        String call = "{call Pro_GetCtrlPlanImp(?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ctrlPlanName);
            proc.setString(2, entName);
            proc.setString(3, productName);
            proc.setString(4, ContextUtil.getOrgCode());
            proc.setString(5, ContextUtil.getDeptCode());
            proc.setString(6, ContextUtil.getOperatorCode());
            proc.setInt(7, startIndex);
            proc.setInt(8, pageSize);
            proc.setString(9, orderWord);
            proc.setString(10, orderDirection);
            proc.registerOutParameter(11, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            CtrlPlanDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, CtrlPlanDto.class);
                list.add(dto);
            }
            recordsTotal = proc.getInt(11);
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
     * 根据监控计划ID得到对应的监控计划详细数据
     * @param ctrlPlanImpID
     * @return
     */
    public static CtrlPlanDto getCtrlPlanImpByID(String ctrlPlanImpID){
        CtrlPlanDto dto = new CtrlPlanDto();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetCtrlPlanImpByID(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ctrlPlanImpID);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, CtrlPlanDto.class);
                break;
            }
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
        
        return dto;
    }
    
    
    /**
     * 保存监控计划数据
     * @return
     */
    public static String saveCtrlPlanImp(String ctrlPlanImpID, String ctrlPlanName, String entCode,
            String entproductCode, String regOrgCode, String regDeptCode, String regOperatorCode){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveCtrlPlanImp(?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ctrlPlanImpID);
            proc.setString(2, ctrlPlanName);
            proc.setString(3, entCode);
            proc.setString(4, entproductCode);
             proc.setString(5, regOrgCode);
            proc.setString(6, regDeptCode);
            proc.setString(7, regOperatorCode);
            proc.registerOutParameter(8, Types.VARCHAR);
            proc.registerOutParameter(9, Types.INTEGER);
            proc.execute();
            retStr = proc.getString(8);
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
     * 删除ID对应的监控计划
     * @return
     */
    public static boolean delCtrlPlanImp(String ctrlPlanImpID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelCtrlPlanImp(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ctrlPlanImpID);
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
     * 根据监控计划ID得到对应的检测项目数据
     * @param ctrlPlanImpID
     * @return
     */
    public static List<CtrlPlanItemDto> getCtrlPlanItem(String ctrlPlanImpID ){
        List<CtrlPlanItemDto> list = new ArrayList<CtrlPlanItemDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetCtrlPlanItem(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ctrlPlanImpID);
            proc.execute();
            rs = proc.getResultSet();
            CtrlPlanItemDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, CtrlPlanItemDto.class);
                list.add(dto);
            }
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
        return list;
    }
    
    /**
     * 保存监控计划项目
     * @return
     */
    public static String saveCtrlPlanItem(String ctrlPlanItemID, String ctrlPlanImpID, String itemCode, String labData, String dataUnit,String impDate,String validDays){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveCtrlPlanItem(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ctrlPlanItemID);
            proc.setString(2, ctrlPlanImpID);
            proc.setString(3, itemCode);
            proc.setString(4, labData);
            proc.setString(5, dataUnit);
            proc.setString(6, impDate);
            proc.setString(7, validDays);
            proc.setString(8, ContextUtil.getOrgCode());
            proc.setString(9, ContextUtil.getDeptCode());
            proc.setString(10, ContextUtil.getOperatorCode());
            proc.registerOutParameter(11, Types.VARCHAR);
            proc.registerOutParameter(12, Types.INTEGER);
            proc.execute();
            retStr = proc.getString(11);
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
     * 删除所选的监控计划项目
     * @return
     */
    public static boolean delCtrlPlanItem(String ctrlPlanItemID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelCtrlPlanItem(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ctrlPlanItemID);
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
