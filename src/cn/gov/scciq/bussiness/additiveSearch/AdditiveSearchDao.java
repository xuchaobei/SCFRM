package cn.gov.scciq.bussiness.additiveSearch;

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

public class AdditiveSearchDao {

	private static final Log log = LogFactory.getLog(AdditiveSearchDao.class);
	
	/**
	 * 添加剂使用查询
	 * @return
	 */
	public static Map<Integer,Object> getAdditiveUseByEntProduct(String additiveName, String entName,
			 String productName, String countryName,
			 int startIndex, int pageSize,String orderWord, String orderDirection){
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		List<AdditiveSearchDto> list = new ArrayList<AdditiveSearchDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		int recordsTotal = 0;
		String call = "{call Pro_GetAdditiveUseByEntProduct(?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, additiveName);
			proc.setString(2, entName);
			proc.setString(3, productName);
			proc.setString(4, countryName);
			proc.setInt(5, startIndex);
			proc.setInt(6, pageSize);
			proc.setString(7, orderWord);
			proc.setString(8, orderDirection);
			proc.registerOutParameter(9, Types.INTEGER);
			proc.execute();
			rs = proc.getResultSet();
			AdditiveSearchDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, AdditiveSearchDto.class);
				list.add(dto);
			}
			recordsTotal = proc.getInt(9);
			rsMap.put(1, recordsTotal);
			rsMap.put(2, list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (proc != null) {
					proc.close();
				}
				if (conn != null) {
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
}
