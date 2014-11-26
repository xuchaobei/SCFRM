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

import cn.gov.scciq.bussiness.riskCtrl.ConventionCtrlRuleResDto;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.RsToDtoUtil;

/**
 * 应急布控
 * String author chao.xu
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
     * 根据应急布控ID得到对应的布控条件
     */
    public static List<CIQCtrlConditionResDto> getCIQControlCondition(String ciqControlID){
        List<CIQCtrlConditionResDto> list = new ArrayList<CIQCtrlConditionResDto>();
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
            CIQCtrlConditionResDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, CIQCtrlConditionResDto.class);
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
}
