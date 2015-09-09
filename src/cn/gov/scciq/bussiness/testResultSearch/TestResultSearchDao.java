package cn.gov.scciq.bussiness.testResultSearch;

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

public class TestResultSearchDao {

	private static final Log log = LogFactory.getLog(TestResultSearchDao.class);
	
	/**
	 * 报检单证检测结果查询
	 * @return
	 */
	public static Map<Integer,Object> getTestResultByQuery(String declNo, String entName, String countryName , String productName, String productCode, String itemName,
			 String ifQualify, String declDateFlg,String declDateStart,String declDateEnd,
			 int startIndex, int pageSize,String orderWord, String orderDirection){
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		List<TestResultSearchResDto> list = new ArrayList<TestResultSearchResDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		int recordsTotal = 0;
		String call = "{call Pro_GetTestResultByQuery(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNo);
			proc.setString(2, entName);
			proc.setString(3, countryName);
			proc.setString(4, productName);
			proc.setString(5, productCode);
			proc.setString(6, productName);
			proc.setString(7, ifQualify);
			proc.setString(8, declDateFlg);
			proc.setString(9, declDateStart);
			proc.setString(10, declDateEnd);
			proc.setInt(11, startIndex);
			proc.setInt(12, pageSize);
			proc.setString(13, orderWord);
			proc.setString(14, orderDirection);
			proc.registerOutParameter(15, Types.INTEGER);
			proc.execute();
			rs = proc.getResultSet();
			TestResultSearchResDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, TestResultSearchResDto.class);
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
