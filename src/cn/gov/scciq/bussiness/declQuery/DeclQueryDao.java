package cn.gov.scciq.bussiness.declQuery;

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

public class DeclQueryDao {

	private static final Log log = LogFactory.getLog(DeclQueryDao.class);
	
	/**
	 * 根据查询条件查询报检批
	 * @return
	 */
	public static Map<Integer,Object> getDeclQueryResultSimple( String inspOrgCode,
			String inspDeptCode, String inspOperatorCode, String declNo,
			String entName, String countryName, String startDate, String endDate,
			  String qualifyJudge, String releaseStatus, int startIndex, int pageSize, String orderWord,
			String orderDirection){
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		List<DeclQueryDto> list = new ArrayList<DeclQueryDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		int recordsTotal = 0;
		String call = "{call Pro_GetDeclQueryResultSimple(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, inspOrgCode);
			proc.setString(2, inspDeptCode);
			proc.setString(3, inspOperatorCode);
			proc.setString(4, declNo);
			proc.setString(5, entName);
			proc.setString(6, countryName);
			proc.setString(7, startDate);
			proc.setString(8, endDate);
			proc.setString(9, qualifyJudge);
			proc.setString(10, releaseStatus);
			proc.setInt(11, startIndex);
			proc.setInt(12, pageSize);
			proc.setString(13, orderWord);
			proc.setString(14, orderDirection);
			proc.registerOutParameter(15, Types.INTEGER);
			proc.execute();
			rs = proc.getResultSet();
			DeclQueryDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, DeclQueryDto.class);
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
