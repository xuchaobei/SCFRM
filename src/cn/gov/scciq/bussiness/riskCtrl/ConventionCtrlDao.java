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
import cn.gov.scciq.util.RsToDtoUtil;

public class ConventionCtrlDao {
    
    private static Log log=LogFactory.getLog(ConventionCtrlDao.class);
    
    public static Map<Integer, Object> getConvCtrl( String productClassCode,  String productSubclassCode,  String materialClassCode,  String materialSubclassCode, String materialCode, String materialSourceCode,  String processMethodCode,  String packageTypeCode,  String intendedUseCode, 
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
            proc.setString(7, processMethodCode);
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
}
