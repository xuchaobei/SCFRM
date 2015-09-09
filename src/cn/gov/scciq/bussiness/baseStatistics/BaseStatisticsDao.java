package cn.gov.scciq.bussiness.baseStatistics;

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

public class BaseStatisticsDao {

	private static final Log log = LogFactory.getLog(BaseStatisticsDao.class);
	
	/**
	 * 得到基地统计数据
	 * @return
	 */
	public static Map<Integer,Object> getStaticResultForBase(int startYear, int startMonth, int endYear , int endMonth, String baseCode, 
			 int startIndex, int pageSize, String orderWord, String orderDirection){
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		List<BaseStatisticsDto> list = new ArrayList<BaseStatisticsDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		int recordsTotal = 0;
		String call = "{call Pro_GetStaticResultForBase(?,?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setInt(1, startYear);
			proc.setInt(2, startMonth);
			proc.setInt(3, endYear);
			proc.setInt(4, endMonth);
			proc.setString(5, baseCode);
			proc.setInt(6, startIndex);
			proc.setInt(7, pageSize);
			proc.setString(8, orderWord);
			proc.setString(9, orderDirection);
			proc.registerOutParameter(10, Types.INTEGER);
			proc.execute();
			rs = proc.getResultSet();
			BaseStatisticsDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, BaseStatisticsDto.class);
				list.add(dto);
			}
			recordsTotal = proc.getInt(10);
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
