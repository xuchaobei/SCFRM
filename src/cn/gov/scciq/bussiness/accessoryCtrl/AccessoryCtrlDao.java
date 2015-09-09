package cn.gov.scciq.bussiness.accessoryCtrl;

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

import cn.gov.scciq.bussiness.convCtrl.ConvCtrlItemLimitDto;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class AccessoryCtrlDao {
    
    private static Log log=LogFactory.getLog(AccessoryCtrlDao.class);
    
    /**
     * 得到辅料布控规则
     * @param accessoryName
     * @param startIndex
     * @param pageSize
     * @param orderWord
     * @param orderDirection
     * @return
     */
    public static Map<Integer, Object> getAccessoryControl(String accessoryName, int startIndex, int pageSize, String orderWord, String orderDirection){
        Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
        List<AccessoryCtrlResDto> list = new ArrayList<AccessoryCtrlResDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        int recordsTotal = 0;
        String call = "{call Pro_GetAccessoryControl(?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, accessoryName);
            proc.setInt(2, startIndex);
            proc.setInt(3, pageSize);
            proc.setString(4, orderWord);
            proc.setString(5, orderDirection);
            proc.registerOutParameter(6, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            AccessoryCtrlResDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, AccessoryCtrlResDto.class);
                list.add(dto);
            }
            recordsTotal = proc.getInt(6);
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
     * 获取辅料详情
     * @param accessoryControlID
     * @return
     */
    public static AccessoryCtrlDetailResDto getAccessoryControlDetailByID(String accessoryControlID){
        AccessoryCtrlDetailResDto dto = new AccessoryCtrlDetailResDto();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetAccessoryControlDetailByID(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, accessoryControlID);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, AccessoryCtrlDetailResDto.class);
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
     * 保存辅料规则
     * @return
     */
    public static String saveAccessoryControl(String accessoryControlID, String accessoryName, String productClassCode, String productSubclassCode, String materialClassCode,
            String materialSubclassCode, String materialCode, String countryCode, String controlOrgCode, String controlDeptCode, String controlOperatorCode){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveAccessoryControl(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, accessoryControlID);
            proc.setString(2, accessoryName);
            proc.setString(3, productClassCode);
            proc.setString(4, productSubclassCode);
            proc.setString(5, materialClassCode);
            proc.setString(6, materialSubclassCode);
            proc.setString(7, materialCode);
            proc.setString(8, countryCode);
            proc.setString(9, controlOrgCode);
            proc.setString(10, controlDeptCode);
            proc.setString(11, controlOperatorCode);
            proc.registerOutParameter(12, Types.VARCHAR);
            proc.registerOutParameter(13, Types.INTEGER);
            proc.execute();
            retStr = proc.getString(12);
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
     * 删除辅料布控
     * @param ciqControlID
     * @return
     */
    public static boolean delAccessoryControl(String accessoryControlID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelAccessoryControl(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, accessoryControlID);
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
     * 得到布控项目
     * @param accessoryControlID
     * @return
     */
    public static List<AccessoryCtrlItemResDto> getAccessoryControlItem(String accessoryControlID ){
        List<AccessoryCtrlItemResDto> list = new ArrayList<AccessoryCtrlItemResDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetAccessoryControlItem(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, accessoryControlID);
            proc.execute();
            rs = proc.getResultSet();
            AccessoryCtrlItemResDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, AccessoryCtrlItemResDto.class);
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
     * 保存辅料布控项目
     * @return
     */
    public static String saveAccessoryControlItem(String accessoryControlItemID, String accessoryControlID, String itemCode, String itemName, String detectionStd, String samplingRatio,
            String limitType, String detectionLimit, String limitUnit, String orgCode, String deptCode){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        System.out.println("hehe "+itemCode+"  "+itemName); 
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveAccessoryControlItem(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, accessoryControlItemID);
            proc.setString(2, accessoryControlID);
            proc.setString(3, itemCode);
            proc.setString(4, itemName);
            proc.setString(5, detectionStd);
            proc.setString(6, samplingRatio);
            proc.setString(7, limitType);
            proc.setString(8, detectionLimit);
            proc.setString(9, limitUnit);
            proc.setString(10, orgCode);
            proc.setString(11, deptCode);
            proc.registerOutParameter(12, Types.VARCHAR);
            proc.registerOutParameter(13, Types.INTEGER);
            proc.execute();
            if(accessoryControlItemID.equals("0")){
                retStr = proc.getString(13);
                if(retStr.equals("0")){
                    retStr = proc.getString(12);
                }
            }else{
                retStr = proc.getString(12);
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
     * 保存限量表
     * @return
     */
    public static String saveAccessoryControlItemLimit(String accessoryControlItemID, String countryCode, String countryName, String detectionLimit,
            String limitUnit){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveAccessoryControlItemLimit(?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, accessoryControlItemID);
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
     * 根据辅料布控项目ID得到布控项目详细数据及其限量表数据
     * @return
     */
    public static AccessoryCtrlItemDetailDto getAccessoryControlItemDetailByID(String accessoryControlItemID){
        AccessoryCtrlItemDetailDto dto = new AccessoryCtrlItemDetailDto();
        AccessoryCtrlItemDetailResDto itemDetail = null;
        List<ConvCtrlItemLimitDto> list = new ArrayList<ConvCtrlItemLimitDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetAccessoryControlItemDetailByID(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, accessoryControlItemID);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                itemDetail = RsToDtoUtil.tranRsToDto(rs, AccessoryCtrlItemDetailResDto.class);
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
     * 删除辅料布控项目
     * @param accessoryControlItemID
     * @return
     */
    public static boolean delAccessoryControlItem(String accessoryControlItemID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelAccessoryControlItem(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, accessoryControlItemID);
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
