package cn.gov.scciq.bussiness.additiveCtrl;

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

import cn.gov.scciq.bussiness.ciqCtrl.CIQCtrlDao;
import cn.gov.scciq.bussiness.convCtrl.ConvCtrlItemLimitDto;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class AdditiveCtrlDao {
    
    private static Log log=LogFactory.getLog(CIQCtrlDao.class);
    
    /**
     * 得到添加剂布控规则
     * @param additiveName
     * @param startIndex
     * @param pageSize
     * @param orderWord
     * @param orderDirection
     * @return
     */
    public static Map<Integer, Object> getAdditiveControl(String additiveName, int startIndex, int pageSize, String orderWord, String orderDirection){
        Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
        List<AdditiveCtrlResDto> list = new ArrayList<AdditiveCtrlResDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        int recordsTotal = 0;
        String call = "{call Pro_GetAdditiveControl(?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, additiveName);
            proc.setInt(2, startIndex);
            proc.setInt(3, pageSize);
            proc.setString(4, orderWord);
            proc.setString(5, orderDirection);
            proc.registerOutParameter(6, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            AdditiveCtrlResDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, AdditiveCtrlResDto.class);
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
     * 获取添加剂规则详情
     * @param additiveControlID
     * @return
     */
    public static AdditiveCtrlDetailResDto getAdditiveControlDetailByID(String additiveControlID){
        AdditiveCtrlDetailResDto dto = new AdditiveCtrlDetailResDto();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetAdditiveControlDetailByID(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, additiveControlID);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, AdditiveCtrlDetailResDto.class);
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
     * 保存添加剂规则
     * @return
     */
    public static String saveAdditiveControl(String additiveControlID, String additiveName, String productClassCode, String productSubclassCode, String materialClassCode,
            String materialSubclassCode, String materialCode, String countryCode, String controlOrgCode, String controlDeptCode, String controlOperatorCode){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveAdditiveControl(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, additiveControlID);
            proc.setString(2, additiveName);
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
     * 删除添加剂布控
     * @param ciqControlID
     * @return
     */
    public static boolean delAdditiveControl(String additiveControlID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelAdditiveControl(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, additiveControlID);
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
     * @param additiveControlID
     * @return
     */
    public static List<AdditiveCtrlItemResDto> getAdditiveControlItem(String additiveControlID ){
        List<AdditiveCtrlItemResDto> list = new ArrayList<AdditiveCtrlItemResDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetAdditiveControlItem(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, additiveControlID);
            proc.execute();
            rs = proc.getResultSet();
            AdditiveCtrlItemResDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, AdditiveCtrlItemResDto.class);
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
     * 保存添加剂布控项目
     * @return
     */
    public static String saveAdditiveControlItem(String additiveControlItemID, String additiveControlID, String itemCode, String itemName, String detectionStd, String samplingRatio,
            String limitType, String detectionLimit, String limitUnit, String orgCode, String deptCode){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveAdditiveControlItem(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, additiveControlItemID);
            proc.setString(2, additiveControlID);
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
            if(additiveControlItemID.equals("0")){
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
    public static String saveAdditiveControlItemLimit(String additiveControlItemID, String countryCode, String countryName, String detectionLimit,
            String limitUnit){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveAdditiveControlItemLimit(?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, additiveControlItemID);
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
     * 根据添加剂布控项目ID得到布控项目详细数据及其限量表数据
     * @return
     */
    public static AdditiveCtrlItemDetailDto getAdditiveControlItemDetailByID(String additiveControlItemID){
        AdditiveCtrlItemDetailDto dto = new AdditiveCtrlItemDetailDto();
        AdditiveCtrlItemDetailResDto itemDetail = null;
        List<ConvCtrlItemLimitDto> list = new ArrayList<ConvCtrlItemLimitDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetAdditiveControlItemDetailByID(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, additiveControlItemID);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                itemDetail = RsToDtoUtil.tranRsToDto(rs, AdditiveCtrlItemDetailResDto.class);
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
     * 删除添加剂布控项目
     * @param additiveControlItemID
     * @return
     */
    public static boolean delAdditiveControlItem(String additiveControlItemID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelAdditiveControlItem(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, additiveControlItemID);
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
