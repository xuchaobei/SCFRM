package cn.gov.scciq.bussiness.auth;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.ContextUtil;

/**
 * 人员权限判断
 * @author chao.xu
 *
 */
public class AuthorityDao {
    private static Log log=LogFactory.getLog(AuthorityDao.class);
    
    /**
     * 根据角色获取人员权限
     * @param menuName
     * @return
     */
    public static String getOperateLimit(String menuName){
        String retStr = ConstantStr.PERMISSION_DENIAL_MSG;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_GetOperateLimit(?,?,?)}";
        String roleCode = ContextUtil.getRoleCode();
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, roleCode);
            proc.setString(2, menuName);
            proc.registerOutParameter(3, Types.INTEGER);
            proc.execute();
            int operCode = proc.getInt(3);
            retStr = operCode+"";
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
