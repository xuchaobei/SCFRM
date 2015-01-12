package cn.gov.scciq.bussiness.baseMng;

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

public class BaseMngDao {
    
    private static final Log log = LogFactory.getLog(BaseMngDao.class);

    /**
     * 取得基地列表数据
     * @return
     */
    public static Map<Integer, Object> getBaseList(String baseName, String baseCode, int startIndex, int pageSize , String orderWord ,String orderDirection){
        Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
        List<BaseMngResDto> list = new ArrayList<BaseMngResDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        int recordsTotal = 0;
        String call = "{call Pro_GetBaseList(?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, baseName);
            proc.setString(2, baseCode);
            proc.setInt(3, startIndex);
            proc.setInt(4, pageSize);
            proc.setString(5, orderWord);
            proc.setString(6, orderDirection);
            proc.registerOutParameter(7, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            BaseMngResDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, BaseMngResDto.class);
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
            } catch (Exception e) {
                // TODO Auto-generated catch block
                log.error("", e);
            }
        }
        
        return rsMap;
    }
    
    /**
     * 根据基地备案号得到基地详细数据
     * @return
     */
    public static BaseMngDetailDto getBaseDetailByCode(String baseCode){
    	BaseMngDetailDto dto = null;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetBaseDetailByCode(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, baseCode);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, BaseMngDetailDto.class);
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
     * 删除基地（及与基地相关的其他数据，如放行项目等)
     * @param foreignReportingID
     * @return
     */
    public static boolean delBase(String baseCode){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_DelBase(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, baseCode);
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
     * 保存基地
     * @return
     */
    public static String saveBase(String baseID, String baseCode, String baseName, String address, String baseLevel,
            String baseEvl, String area, String plantKind, String orgCode, String entName, String regDate,
            String remarks){
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveBase(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, baseID);
            proc.setString(2, baseCode);
            proc.setString(3, baseName);
            proc.setString(4, address);
            proc.setString(5, baseLevel);
            proc.setString(6, baseEvl);
            proc.setString(7, area);
            proc.setString(8, plantKind);
            proc.setString(9, orgCode);
            proc.setString(10, entName);
            proc.setString(11, regDate);
            proc.setString(12, remarks);
            proc.registerOutParameter(13, Types.VARCHAR);
            proc.registerOutParameter(14, Types.INTEGER);
            proc.execute();
            retStr = proc.getString(13);
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
