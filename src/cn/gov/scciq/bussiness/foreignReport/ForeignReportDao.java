package cn.gov.scciq.bussiness.foreignReport;

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

public class ForeignReportDao {
    
    private static final Log log = LogFactory.getLog(ForeignReportDao.class);

    /**
     * 取得国外通报
     * @return
     */
    public static Map<Integer, Object> getForeignReportList(String reportingTitle, String countryName, String entName, int startIndex, int pageSize , String orderWord ,String orderDirection){
        Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
        List<ForeignReportResDto> list = new ArrayList<ForeignReportResDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        int recordsTotal = 0;
        String call = "{call Pro_GetForeignReportList(?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, reportingTitle);
            proc.setString(2, countryName);
            proc.setString(3, entName);
            proc.setInt(4, startIndex);
            proc.setInt(5, pageSize);
            proc.setString(6, orderWord);
            proc.setString(7, orderDirection);
            proc.registerOutParameter(8, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            ForeignReportResDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, ForeignReportResDto.class);
                list.add(dto);
            }
            recordsTotal = proc.getInt(8);
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
     * 获取通报项目
     * @param foreignReportingID
     * @return
     */
    public static List<ForeignReportingItemResDto> getForeignReportingItem(String foreignReportingID){
        List<ForeignReportingItemResDto> list = new ArrayList<ForeignReportingItemResDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetForeignReportingItem(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, foreignReportingID);
            proc.execute();
            rs = proc.getResultSet();
            ForeignReportingItemResDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, ForeignReportingItemResDto.class);
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
     * 删除国外通报
     * @param foreignReportingID
     * @return
     */
    public static boolean delForeignReporting(String foreignReportingID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelForeignReporting(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, foreignReportingID);
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
     * 保存国外通报
     * @return
     */
    public static String saveForeignReporting(String foreignReportingID, String reportingTitle, String reportingDate, String reportingReason, String entCode,
            String productClassCode, String productSubclassCode, String materialClassCode, String materialSubclassCode, String materialCode, String materialSourceCode,
            String processingMethodCode, String packageTypeCode, String intentedUseCode, String countryCode, String regOrgCode, String regDeptCode, String regOperatorCode,String notEffectFlg){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveForeignReporting(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, foreignReportingID);
            proc.setString(2, reportingTitle);
            proc.setString(3, reportingDate);
            proc.setString(4, reportingReason);
            proc.setString(5, entCode);
            proc.setString(6, productClassCode);
            proc.setString(7, productSubclassCode);
            proc.setString(8, materialClassCode);
            proc.setString(9, materialSubclassCode);
            proc.setString(10, materialCode);
            proc.setString(11, materialSourceCode);
            proc.setString(12, processingMethodCode);
            proc.setString(13, packageTypeCode);
            proc.setString(14, intentedUseCode);
            proc.setString(15, countryCode);
            proc.setString(16, regOrgCode);
            proc.setString(17, regDeptCode);
            proc.setString(18, regOperatorCode);
            proc.setString(19, notEffectFlg);
            proc.registerOutParameter(20, Types.VARCHAR);
            proc.registerOutParameter(21, Types.INTEGER);
            proc.execute();
            retStr = proc.getString(20);
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
     * 根据国外通报ID得到国外通报详细数据
     * @param foreignReportingID
     * @return
     */
    public static ForeignReportDetailResDto getForeignReportingDetailByID(String foreignReportingID){
        ForeignReportDetailResDto dto = new ForeignReportDetailResDto();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetForeignReportdingDetailByID(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, foreignReportingID);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, ForeignReportDetailResDto.class);
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
     * 保存通报项目
     * @param reportingItemID
     * @param foreignReportingID
     * @param itemCode
     * @return
     */
    public static String saveForeignReportingItem(String reportingItemID, String foreignReportingID, String itemCode){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveForeignReportingItem(?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, reportingItemID);
            proc.setString(2, foreignReportingID);
            proc.setString(3, itemCode);
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
     * 删除通报项目
     * @param reportingItemID
     * @return
     */
    public static boolean delForeignReportingItem(String reportingItemID){
    	System.out.println("idd "+reportingItemID);
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelForeignReportingItem(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, reportingItemID);
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
