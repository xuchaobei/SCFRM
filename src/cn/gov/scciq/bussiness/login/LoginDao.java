package cn.gov.scciq.bussiness.login;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.dto.UserDto;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class LoginDao {
    private static Log log=LogFactory.getLog(LoginDao.class);
    
    public static Map<Integer, Object> checkOperatorPasswords(String orgCode, String deptCode, String operatorCode, String passwords){
        Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
        UserDto userDto = new UserDto();
        String retStr = ConstantStr.SAVE_ERROR_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_CheckOperatorPasswords(?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, orgCode);
            proc.setString(2, deptCode);
            proc.setString(3, operatorCode);
            proc.setString(4, passwords);
            proc.registerOutParameter(5, Types.VARCHAR);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                userDto = RsToDtoUtil.tranRsToDto(rs, UserDto.class);
            }
            retStr = proc.getString(5);
           
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("a", e);
        } catch (Exception e) {
            log.error("b", e);
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
        
        rsMap.put(0, retStr);
        rsMap.put(1, userDto);
        return rsMap;
    }
}
