package cn.gov.scciq.bussiness.baseMaterial;

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

public class BaseMaterialDao {
    
    private static Log log=LogFactory.getLog(BaseMaterialDao.class);
    
    /**
     * 得到基地放行原料列表
     * @return
     */
    public static Map<Integer, Object> getBaseMaterialList(String baseName, int startIndex, int pageSize, String orderWord, String orderDirection){
        Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
        List<BaseMaterialDto> list = new ArrayList<BaseMaterialDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        int recordsTotal = 0;
        String call = "{call Pro_GetBaseMaterialList(?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, baseName);
            proc.setInt(2, startIndex);
            proc.setInt(3, pageSize);
            proc.setString(4, orderWord);
            proc.setString(5, orderDirection);
            proc.registerOutParameter(6, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            BaseMaterialDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, BaseMaterialDto.class);
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
     * 得到显示放行原料批列表
     * @return
     */
    public static Map<Integer, Object> getMaterialReleaseList(String materialBatchNo, int startIndex, int pageSize, String orderWord, String orderDirection){
        Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
        List<MaterialRealseDto> list = new ArrayList<MaterialRealseDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        int recordsTotal = 0;
        String call = "{call Pro_GetMaterialReleaseList(?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, materialBatchNo);
            proc.setInt(2, startIndex);
            proc.setInt(3, pageSize);
            proc.setString(4, orderWord);
            proc.setString(5, orderDirection);
            proc.registerOutParameter(6, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            MaterialRealseDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, MaterialRealseDto.class);
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
     * 根据放行原料ID，得到该放行原料详细数据
     * @param baseMaterialID
     * @return
     */
    public static BaseMaterialDto getBaseMaterialDetailByID(String baseMaterialID){
        BaseMaterialDto dto = new BaseMaterialDto();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetBaseMaterialDetailByID(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, baseMaterialID);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, BaseMaterialDto.class);
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
     * 根据原料批放行ID得到其详细数据
     * @param baseMaterialID
     * @return
     */
    public static MaterialRealseDto getMaterialReleaseByID(String materialReleaseID){
    	MaterialRealseDto dto = new MaterialRealseDto();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetMaterialReleaseByID(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, materialReleaseID);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, MaterialRealseDto.class);
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
     * 保存基地放行原料
     * @return
     */
    public static String saveBaseMaterial(String baseMaterialID, String baseCode, String materialClassCode,
            String materialSubclassCode, String materialCode){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveBaseMaterial(?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, baseMaterialID);
            proc.setString(2, baseCode);
            proc.setString(3, materialClassCode);
            proc.setString(4, materialSubclassCode);
            proc.setString(5, materialCode);
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
     * 保存原料批
     * @return
     */
    public static String saveMaterialRelease(String materialReleaseID, String materialBatchNo, String materialClassCode,
            String materialSubclassCode, String materialCode,String inspDate,String validDays){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveMaterialRelease(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, materialReleaseID);
            proc.setString(2, materialBatchNo);
            proc.setString(3, materialClassCode);
            proc.setString(4, materialSubclassCode);
            proc.setString(5, materialCode);
            proc.setString(6, inspDate);
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
     * 删除基地放行原料ID
     * @return
     */
    public static boolean delBaseMaterial(String baseMaterialID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelBaseMaterial(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, baseMaterialID);
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
     * 删除原料批
     * @return
     */
    public static boolean delMaterialRelease(String materialReleaseID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelMaterialRelease(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, materialReleaseID);
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
     * 根据基地放行原料ID得到放行项目
     * @param baseMaterialID
     * @return
     */
    public static List<BaseMaterialItemResDto> getBaseMaterialItemByID(String baseMaterialID ){
        List<BaseMaterialItemResDto> list = new ArrayList<BaseMaterialItemResDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetBaseMaterialItemByID(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, baseMaterialID);
            proc.execute();
            rs = proc.getResultSet();
            BaseMaterialItemResDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, BaseMaterialItemResDto.class);
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
     * 根据原料批放行的ID给出放行项目
     * @param materialReleaseID
     * @return
     */
    public static List<MaterialRealseItemDto> getMaterialReleaseItem(String materialReleaseID ){
        List<MaterialRealseItemDto> list = new ArrayList<MaterialRealseItemDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetMaterialReleaseItem(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, materialReleaseID);
            proc.execute();
            rs = proc.getResultSet();
            MaterialRealseItemDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, MaterialRealseItemDto.class);
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
     * 保存放行项目
     * @return
     */
    public static String saveBaseMaterialItem(String baseMaterialItemID, String baseMaterialID, String itemCode,String labData,String dataUnit,String impDate,String validDays){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveBaseMaterialItem(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, baseMaterialItemID);
            proc.setString(2, baseMaterialID);
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
     * 保存原料批放行项目
     * @return
     */
    public static String SaveMaterialReleaseItem(String MaterialReleaseItemID, String MaterialReleaseID, String itemCode,String labData,String dataUnit){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveMaterialReleaseItem(?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, MaterialReleaseItemID);
            proc.setString(2, MaterialReleaseID);
            proc.setString(3, itemCode);
            proc.setString(4, labData);
            proc.setString(5, dataUnit);     
            proc.setString(6, ContextUtil.getOrgCode());
            proc.setString(7, ContextUtil.getDeptCode());
            proc.setString(8, ContextUtil.getOperatorCode());
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
     * 删除放行项目
     * @return
     */
    public static boolean delBaseMaterialItem(String baseMaterialItemID){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelBaseMaterialItem(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, baseMaterialItemID);
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
     * 删除原料批放行项目
     * @return
     */
    public static String delMaterialReleaseItem(String materialReleaseItemID,String materialReleaseID){
    	 String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelMaterialReleaseItem(?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, materialReleaseItemID);
            proc.setString(2, materialReleaseID);     
            proc.setString(3, ContextUtil.getOrgCode());
            proc.setString(4, ContextUtil.getDeptCode());
            proc.setString(5, ContextUtil.getOperatorCode());
            proc.registerOutParameter(6, Types.VARCHAR);
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
}
