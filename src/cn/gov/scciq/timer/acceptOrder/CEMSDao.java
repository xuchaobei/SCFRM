package cn.gov.scciq.timer.acceptOrder;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.RsToDtoUtil;

/**
 *  集中审单数据库Dao
 * 
 * @author chao.xu
 *
 */
public class CEMSDao {
    private static Log log=LogFactory.getLog(CEMSDao.class);
    
    /**
     * 读取待处理的报检号
     * @return
     */
    public static List<CEMSDto> getCEMS(){
        List<CEMSDto> list =new ArrayList<CEMSDto>();
        CEMSDto dto = null;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetCEMS()}";
        try {
            conn = DBPool.ds_cems.getConnection();
            proc = conn.prepareCall(call);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, CEMSDto.class);
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
     * 根据报检号取得集中审单系统的报检数据
     * @return
     */
    public static CEMSDeclDataDto getCEMSDeclData(String SEQ_NUM){
        CEMSDeclDataDto dto = null;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetCEMSDeclData(?)}";
        try {
            conn = DBPool.ds_cems.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, SEQ_NUM);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, CEMSDeclDataDto.class);
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
    
    /** 风险管理系统回执发送 */
    public static void saveReturnFlg(String declNo, String returnFlg){
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_SaveReturnFlg(?,?)}";
        try {
            conn = DBPool.ds_cems.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, declNo);
            proc.setString(2, returnFlg);
            proc.execute();
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
    }
    
    /**
     * 
     */
    public static void updateControlReturnFlg(String seqNum, String status, String returnFlg){
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_UpdateControlReturnFlg(?,?,?)}";
        try {
            conn = DBPool.ds_cems.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, seqNum);
            proc.setString(2, status);
            proc.setString(3, returnFlg);
            proc.execute();
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
    }
    
    /**
     * 读取货物数据
     * @param seqNum
     * @return
     */
    public static List<CEMSGoodsDataDto> getCEMSGoodsData(String seqNum){
        List<CEMSGoodsDataDto> list =new ArrayList<CEMSGoodsDataDto>();
        CEMSGoodsDataDto dto = null;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetCEMSGoodsData(?)}";
        try {
            conn = DBPool.ds_cems.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, seqNum);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, CEMSGoodsDataDto.class);
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
     * 取得厂检单数据
     */
    public static List<CEMSDeclExchangeDto> getCEMSDeclExchange(String seqNum){
        List<CEMSDeclExchangeDto> list =new ArrayList<CEMSDeclExchangeDto>();
        CEMSDeclExchangeDto dto = null;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetCEMSDeclExchange(?)}";
        try {
            conn = DBPool.ds_cems.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, seqNum);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, CEMSDeclExchangeDto.class);
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
}
