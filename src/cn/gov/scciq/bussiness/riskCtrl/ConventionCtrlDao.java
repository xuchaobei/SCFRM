package cn.gov.scciq.bussiness.riskCtrl;

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

public class ConventionCtrlDao {
    
    private static Log log=LogFactory.getLog(ConventionCtrlDao.class);
    
    /**
     * 获取常规布控
     * @param productClassCode
     * @param productSubclassCode
     * @param materialClassCode
     * @param materialSubclassCode
     * @param materialCode
     * @param materialSourceCode
     * @param processingMethodCode
     * @param packageTypeCode
     * @param intendedUseCode
     * @param countryCode
     * @param productCode
     * @param controlOrgCode
     * @param controlDeptCode
     * @param startIndex
     * @param pageSize
     * @param orderWord
     * @param orderDirection
     * @return
     */
    public static Map<Integer, Object> getConvCtrl( String productClassCode,  String productSubclassCode,  String materialClassCode,  String materialSubclassCode, String materialCode, String materialSourceCode,  String processingMethodCode,  String packageTypeCode,  String intendedUseCode, 
            String countryCode,  String productCode, String controlOrgCode, String controlDeptCode, int startIndex, int pageSize, String orderWord, String orderDirection){
        Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
        List<ConventionCtrlRuleResDto> list = new ArrayList<ConventionCtrlRuleResDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        int recordsTotal = 0;
        String call = "{call Pro_GetConvCtrl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, productClassCode);
            proc.setString(2, productSubclassCode);
            proc.setString(3, materialClassCode);
            proc.setString(4, materialSubclassCode);
            proc.setString(5, materialCode);
            proc.setString(6, materialSourceCode);
            proc.setString(7, processingMethodCode);
            proc.setString(8, packageTypeCode);
            proc.setString(9, intendedUseCode);
            proc.setString(10, countryCode);
            proc.setString(11, productCode);
            proc.setString(12, controlOrgCode);
            proc.setString(13, controlDeptCode);
            proc.setInt(14, startIndex);
            proc.setInt(15, pageSize);
            proc.setString(16, orderWord);
            proc.setString(17, orderDirection);
            proc.registerOutParameter(18, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            ConventionCtrlRuleResDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, ConventionCtrlRuleResDto.class);
                list.add(dto);
            }
            recordsTotal = proc.getInt(18);
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
     * 保存常规布控
     * @return
     */
    public static String saveConvCtrl(String convCtrlID,String productClassCode,String productSubclassCode,String productCode,
            String materialClassCode,String materialSubclassCode,String materialCode,String materialSourceCode,
            String processingMethodCode,String packageTypeCode,String intentedUseCode,String countryCode,
            String differenceCode,String controlOrgCode,String controlDeptCode,String controlOperatorCode){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveConvCtrl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, convCtrlID);
            proc.setString(2, productClassCode);
            proc.setString(3, productSubclassCode);
            proc.setString(4, productCode);
            proc.setString(5, materialClassCode);
            proc.setString(6, materialSubclassCode);
            proc.setString(7, materialCode);
            proc.setString(8, materialSourceCode);
            proc.setString(9, processingMethodCode);
            proc.setString(10, packageTypeCode);
            proc.setString(11, intentedUseCode);
            proc.setString(12, countryCode);
            proc.setString(13, differenceCode);
            proc.setString(14, "310000");
            proc.setString(15, "2710");
            proc.setString(16, "2711");
            proc.registerOutParameter(17, Types.VARCHAR);
            proc.registerOutParameter(18, Types.INTEGER);
            proc.execute();
            retStr = proc.getString(17);
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
     * 获取布控规则详情
     * @param convCtrlID
     * @return
     */
    public static ConvCtrlRuleDetailDto getConvCtrlDetailByID(String convCtrlID){
        ConvCtrlRuleDetailDto dto = new ConvCtrlRuleDetailDto();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetConvCtrlDetailByID(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, convCtrlID);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, ConvCtrlRuleDetailDto.class);
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
     * 删除常规布控
     * @param convCtrlId
     * @return
     */
    public static boolean delConvCtrlById(String convCtrlId) {
        // TODO Auto-generated method stub
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelConvCtrl(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, convCtrlId);
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
     * 根据布控项目id，获取布控项目详细数据及其限量表数据
     * @param convCtrlItemId
     * @return
     */
    public static ConvCtrlItemDetailDto getConvCtrlItemDetailByID(String convCtrlItemID){
        ConvCtrlItemDetailDto dto = new ConvCtrlItemDetailDto();
        ConventionCtrlItemDetailResDto itemDetail = null;
        List<ConvCtrlItemLimitDto> list = new ArrayList<ConvCtrlItemLimitDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetConvCtrlItemDetailByID(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, convCtrlItemID);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                itemDetail = RsToDtoUtil.tranRsToDto(rs, ConventionCtrlItemDetailResDto.class);
                break;
            }
            if(proc.getMoreResults()){
                rs = proc.getResultSet();
                ConvCtrlItemLimitDto itemLimit = null;
                while(rs.next()){
                    itemLimit = RsToDtoUtil.tranRsToDto(rs, ConvCtrlItemLimitDto.class);
                    list.add(itemLimit);
                }
            }
            dto.setItemDetail(itemDetail);
            dto.setItemLimitList(list);
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
     * 获取布控项目列表
     * @param convCtrlID
     * @return
     */
    public static List<ConventionCtrlItemResDto> getConvCtrlItem(String convCtrlID){
        List<ConventionCtrlItemResDto> list = new ArrayList<ConventionCtrlItemResDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetConvCtrlItem(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, convCtrlID);
            proc.execute();
            rs = proc.getResultSet();
            ConventionCtrlItemResDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, ConventionCtrlItemResDto.class);
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
     * 保存限量表(存储过程修改过，之前调用的是Pro_SaveCtrlItmeLimit)
     * @return
     */
    public static String saveConvCtrlItmeLimit(String convCtrlItemID, String countryCode, String countryName, String detectionLimit,
            String limitUnit){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveConvCtrlItmeLimit(?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, convCtrlItemID);
            proc.setString(2, countryCode);
            proc.setString(3, countryName);
            proc.setString(4, detectionLimit);
            proc.setString(5, limitUnit);
            proc.registerOutParameter(6, Types.VARCHAR);
            proc.registerOutParameter(7, Types.INTEGER);
            proc.execute();
            retStr = proc.getString(6);
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
     * 保存布控项目
     * 
     */
    public static String saveConvCtrlItem(String convCtrlItemID, String convCtrlID, String itemCode, String itemName,
            String detectionStd, String monitoringReason, String unqualifyRatio, String hazardLevel, String countryReactLevel, 
            String limitType, String detectionLimit, String limitUnit, String orgCode, String deptCode){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveConvCtrlItem(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, convCtrlItemID);
            proc.setString(2, convCtrlID);
            proc.setString(3, itemCode);
            proc.setString(4, itemName);
            proc.setString(5, detectionStd);
            proc.setString(6, monitoringReason);
            proc.setString(7, unqualifyRatio);
            proc.setString(8, hazardLevel);
            proc.setString(9, countryReactLevel);
            proc.setString(10, limitType);
            proc.setString(11, detectionLimit);
            proc.setString(12, limitUnit);
            proc.setString(13, "310000");
            proc.setString(14, "2710");
            proc.registerOutParameter(15, Types.VARCHAR);
            proc.registerOutParameter(16, Types.INTEGER);
            proc.execute();
            if(convCtrlItemID.equals("0")){
                retStr = proc.getString(16);
            }else{
                retStr = proc.getString(15);
            }
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
     * 删除布控项目
     * @param convCtrlItemID
     * @return
     */
    public static boolean delConvCtrlItem(String convCtrlItemID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelConvCtrlItem(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, convCtrlItemID);
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
