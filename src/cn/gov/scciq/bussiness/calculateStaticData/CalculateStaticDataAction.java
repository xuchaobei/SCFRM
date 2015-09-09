package cn.gov.scciq.bussiness.calculateStaticData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.bussiness.auth.AuthorityDao;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.DefaultResultUtil;

import com.opensymphony.xwork2.Action;

/**
 * 统计计算
 * 
 * @author xu
 *
 */
public class CalculateStaticDataAction {

	private static Log log=LogFactory.getLog(CalculateStaticDataAction.class);
    
    private String data;
    
    private JSONObject result;
    
    public String calculateMonthlyStaticData(){
        boolean retCode = true;
        Connection conn = null;
        CallableStatement proc = null;
        String call = "{call Pro_CalculateMonthlyStaticData(?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setInt(1, 1);
            proc.setString(2, data);
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
        result = DefaultResultUtil.getModificationResult(retCode);
    	return Action.SUCCESS;
    }
    
	public String checkPermission(){
		try {
			 String permission = AuthorityDao.getOperateLimit(ConstantStr.CALCULATE_DATA);
			  result = new JSONObject();
			 if(permission.equals("0")){
	            result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
	         }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return Action.SUCCESS;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	
}
