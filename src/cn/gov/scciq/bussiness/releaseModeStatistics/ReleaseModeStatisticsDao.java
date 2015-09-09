package cn.gov.scciq.bussiness.releaseModeStatistics;

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

public class ReleaseModeStatisticsDao {

	private static final Log log = LogFactory.getLog(ReleaseModeStatisticsDao.class);
	
	/**
	 * 取得放行模式统计结果
	 * @return
	 */
	public static Map<Integer,Object> getStaticResultForReleaseMode(int startYear, int startMonth, int endYear , int endMonth, String orgCode,
			String deptCode, String countryCode, String entCode,
			boolean group_Org, boolean group_Dept, boolean group_Country, boolean group_Ent, String inpOrgCode,
			String inspDeptCode, String operateCode, int startIndex, int pageSize){
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		List<ReleaseModeStatisticsDto> list = new ArrayList<ReleaseModeStatisticsDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		int recordsTotal = 0;
		String call = "{call Pro_GetStaticResultForReleaseMode(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setInt(1, startYear);
			proc.setInt(2, startMonth);
			proc.setInt(3, endYear);
			proc.setInt(4, endMonth);
			proc.setString(5, orgCode);
			proc.setString(6, deptCode);
			proc.setString(7, countryCode);
			proc.setString(8, entCode);
			proc.setBoolean(9, group_Org);
			proc.setBoolean(10, group_Dept);
			proc.setBoolean(11, group_Country);
			proc.setBoolean(12, group_Ent);
			proc.setString(13, inpOrgCode);
			proc.setString(14, inspDeptCode);
			proc.setString(15, operateCode);
			proc.setInt(16, startIndex);
			proc.setInt(17, pageSize);
			proc.registerOutParameter(18, Types.INTEGER);
			proc.execute();
			rs = proc.getResultSet();
			ReleaseModeStatisticsDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, ReleaseModeStatisticsDto.class);
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
