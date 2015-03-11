package cn.gov.scciq.bussiness.itemStatistics;

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

public class ItemStatisticsDao {

	private static final Log log = LogFactory.getLog(ItemStatisticsDao.class);
	
	/**
	 * 根据统计条件获得货物批统计结果
	 * @return
	 */
	public static Map<Integer,Object> getStaticResultForItem(int startYear, int startMonth, int endYear , int endMonth, String productCode, String productName,
			 String countryCode, String entCode,String itemCode,
			 int startIndex, int pageSize,String orderWord, String orderDirection){
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		List<ItemStatisticsDto> list = new ArrayList<ItemStatisticsDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		int recordsTotal = 0;
		String call = "{call Pro_GetStaticResultForItem(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setInt(1, startYear);
			proc.setInt(2, startMonth);
			proc.setInt(3, endYear);
			proc.setInt(4, endMonth);
			proc.setString(5, productCode);
			proc.setString(6, productName);
			proc.setString(7, countryCode);
			proc.setString(8, entCode);
			proc.setString(9, itemCode);
			proc.setInt(10, startIndex);
			proc.setInt(11, pageSize);
			proc.setString(12, orderWord);
			proc.setString(13, orderDirection);
			proc.registerOutParameter(14, Types.INTEGER);
			proc.execute();
			rs = proc.getResultSet();
			ItemStatisticsDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, ItemStatisticsDto.class);
				list.add(dto);
			}
			recordsTotal = proc.getInt(14);
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
