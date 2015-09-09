package cn.gov.scciq.bussiness.ciqCtrl;

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

/**
 * 应急布控
 * String author chao.xu
 *
 */
/**
 * @author chao.xu
 *
 */
public class CIQCtrlDao {
    private static Log log=LogFactory.getLog(CIQCtrlDao.class);
    
    /**
     * 根据查询条件得到应急布控数据集 
     */
    public static Map<Integer, Object> getCIQControl(String controlName,String controlReason,String ifExec,String ifCheck,String controlOrgCode,String controlDeptCode,
            int startRowIndex,int maximumRows,String orderWord,String orderDirection){
        Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
        List<CIQCtrlResDto> list = new ArrayList<CIQCtrlResDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        int recordsTotal = 0;
        String call = "{call Pro_GetCIQControl(?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, controlName);
            proc.setString(2, controlReason);
            proc.setString(3, ifExec);
            proc.setString(4, ifCheck);
            proc.setString(5, controlOrgCode);
            proc.setString(6, controlDeptCode);
            proc.setInt(7, startRowIndex);
            proc.setInt(8, maximumRows);
            proc.setString(9, orderWord);
            proc.setString(10, orderDirection);
            proc.registerOutParameter(11, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            CIQCtrlResDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, CIQCtrlResDto.class);
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
     * 根据应急布控ID得到布控的详细数据
     * @return
     */
    public static CIQCtrlDetailResDto getCIQCtrlDetailByID(String ciqCtrlID){
        CIQCtrlDetailResDto dto = new CIQCtrlDetailResDto();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetCIQCtrlDetailByID(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ciqCtrlID);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, CIQCtrlDetailResDto.class);
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
     * 保存应急布控
     * @return
     */
    public static String saveCIQControl(String ciqControlID, String controlName, String controlReason, String deadline, String remarks,
            String controlOrgCode, String controlDeptCode, String controlOperatorCode){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveCIQControl(?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ciqControlID);
            proc.setString(2, controlName);
            proc.setString(3, controlReason);
            proc.setString(4, deadline);
            proc.setString(5, remarks);
            proc.setString(6, controlOrgCode);
            proc.setString(7, controlDeptCode);
            proc.setString(8, controlOperatorCode);
            proc.registerOutParameter(9, Types.VARCHAR);
            proc.registerOutParameter(10, Types.INTEGER);
            proc.execute();
            retStr = proc.getString(9);
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
     * 删除应急布控
     * @param ciqControlID
     * @return
     */
    public static boolean deleteCIQControl(String ciqControlID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelCIQControl(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ciqControlID);
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
     * 测试应急布控条件是否设置正确
     * @param ciqControlID
     */
    public static String checkCIQControlCondition(String ciqControlID){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_CheckCIQControlCondition(?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ciqControlID);
            proc.registerOutParameter(2, Types.VARCHAR);
            proc.execute();
            retStr = proc.getString(2);
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
     * 设置应急布控条件生效或无效
     * @param ciqControlID
     */
    public static String updateCIQControlValid(String ciqControlID){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_UpdateCIQControlValid(?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ciqControlID);
            proc.registerOutParameter(2, Types.VARCHAR);
            proc.execute();
            retStr = proc.getString(2);
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
     * 根据应急布控ID得到对应的布控条件
     */
    public static List<CIQCtrlConditionDto> getCIQControlCondition(String ciqControlID){
        List<CIQCtrlConditionDto> list = new ArrayList<CIQCtrlConditionDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetCIQControlCondition(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ciqControlID);
            proc.execute();
            rs = proc.getResultSet();
            CIQCtrlConditionDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, CIQCtrlConditionDto.class);
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
     * 保存应急布控规则
     * @return
     */
    public static String saveCIQControlCondition(String ciqControlConditionID, String ciqControlID, String orderNo, String leftLogic, String rightLogic,
            String definedField, String operatorName, String keywords, String KeywordsDesc){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveCIQControlCondition(?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ciqControlConditionID);
            proc.setString(2, ciqControlID);
            proc.setString(3, orderNo);
            proc.setString(4, leftLogic);
            proc.setString(5, rightLogic);
            proc.setString(6, definedField);
            proc.setString(7, operatorName);
            proc.setString(8, keywords);
            proc.setString(9, KeywordsDesc);
            proc.registerOutParameter(10, Types.VARCHAR);
            proc.registerOutParameter(11, Types.INTEGER);
            proc.execute();
            retStr = proc.getString(10);
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
     * 删除应急布控规则
     * @param ciqControlConditionID
     * @return
     */
    public static boolean deleteCIQControlCondition(String ciqControlConditionID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelCIQControlCondition(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ciqControlConditionID);
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
     * 根据应急布控ID得到对应的布控项目
     */
    public static List<CIQCtrlItemResDto> getCIQControlItem(String ciqControlID){
        List<CIQCtrlItemResDto> list = new ArrayList<CIQCtrlItemResDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetCIQControlItem(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ciqControlID);
            proc.execute();
            rs = proc.getResultSet();
            CIQCtrlItemResDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, CIQCtrlItemResDto.class);
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
     * 根据应急布控项目ID得到布控项目详细数据及其限量表
     * @param ciqCtrlItemID
     * @return
     */
    public static CIQCtrlItemDetailDto getCIQCtrlItemDetailByID(String ciqCtrlItemID){
        CIQCtrlItemDetailDto dto = new CIQCtrlItemDetailDto();
        CIQCtrlItemDetailResDto itemDetail = null;
        List<ConvCtrlItemLimitDto> list = new ArrayList<ConvCtrlItemLimitDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetCIQCtrlItemDetailByID(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ciqCtrlItemID);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                itemDetail = RsToDtoUtil.tranRsToDto(rs, CIQCtrlItemDetailResDto.class);
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
     * 保存应急布控项目
     * @return
     */
    public static String saveCIQCtrlItem(String ciqCtrlItemID, String ciqCtrlID, String itemCode, String itemName, String detectionStd, String samplingMode,
            String samplingParamValue, String limitType, String detectionLimit, String limitUnit, String orgCode, String deptCode){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveCIQCtrlItem(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ciqCtrlItemID);
            proc.setString(2, ciqCtrlID);
            proc.setString(3, itemCode);
            proc.setString(4, itemName);
            proc.setString(5, detectionStd);
            proc.setString(6, samplingMode);
            proc.setString(7, samplingParamValue);
            proc.setString(8, limitType);
            proc.setString(9, detectionLimit);
            proc.setString(10, limitUnit);
            proc.setString(11, orgCode);
            proc.setString(12, deptCode);
            proc.registerOutParameter(13, Types.VARCHAR);
            proc.registerOutParameter(14, Types.INTEGER);
            proc.execute();
            if(ciqCtrlItemID.equals("0")){
                retStr = proc.getString(14);
                if(retStr.equals("0")){
                    retStr = proc.getString(13);
                }
            }else{
                retStr = proc.getString(13);
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
    public static String saveCIQCtrlItmeLimit(String ciqCtrlItemID, String countryCode, String countryName, String detectionLimit,
            String limitUnit){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveCIQCtrlItmeLimit(?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ciqCtrlItemID);
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
     * 删除应急布控项目
     * @param ciqControlItemID
     * @return
     */
    public static boolean deleteCIQControlItem(String ciqControlItemID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelCIQControlItem(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ciqControlItemID);
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
