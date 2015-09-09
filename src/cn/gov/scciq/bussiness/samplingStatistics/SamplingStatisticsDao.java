package cn.gov.scciq.bussiness.samplingStatistics;

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

public class SamplingStatisticsDao {

	private static final Log log = LogFactory.getLog(SamplingStatisticsDao.class);
	
	/**
	 * 得到抽检分析结果
	 * @return
	 */
	public static Map<Integer,Object> getStaticResultForSampling(int startYear, int startMonth, int endYear , int endMonth, String productCode, String productName,
			 String countryCode, String entCode, String itemCode, String orgCode, String deptCode, String operatorCode, 
			 int startIndex, int pageSize){
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		List<SamplingStatisticsDto> list = new ArrayList<SamplingStatisticsDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		int recordsTotal = 0;
		String call = "{call Pro_GetStaticResultForSampling(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
			proc.setString(10, orgCode);
			proc.setString(11, deptCode);
			proc.setString(12, operatorCode);
			proc.setInt(13, startIndex);
			proc.setInt(14, pageSize);
			proc.registerOutParameter(15, Types.INTEGER);
			proc.execute();
			rs = proc.getResultSet();
			SamplingStatisticsDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, SamplingStatisticsDto.class);
				list.add(dto);
			}
			recordsTotal = proc.getInt(15);
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
