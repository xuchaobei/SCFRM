package cn.gov.scciq.bussiness.declProcess;

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

public class DeclProcessDao {

	private static final Log log = LogFactory.getLog(DeclProcessDao.class);

	/**
	 * 流程查询
	 * 
	 * @return
	 */
	public static Map<Integer, Object> getDeclProcessByQuery(String declNo,
			String declDateFlg, String declDateStart, String declDateEnd,
			String entName, String countryName, String inspOrgCode,
			String inspDeptCode, String inspOperatorCode, String ifQualify,
			int startIndex, int pageSize, String orderWord,
			String orderDirection) {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		List<DeclProcessDto> list = new ArrayList<DeclProcessDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		int recordsTotal = 0;
		String call = "{call Pro_GetDeclProcessByQuery(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNo);
			proc.setString(2, declDateFlg);
			proc.setString(3, declDateStart);
			proc.setString(4, declDateEnd);
			proc.setString(5, entName);
			proc.setString(6, countryName);
			proc.setString(7, inspOrgCode);
			proc.setString(8, inspDeptCode);
			proc.setString(9, inspOperatorCode);
			proc.setString(10, ifQualify);
			proc.setInt(11, startIndex);
			proc.setInt(12, pageSize);
			proc.setString(13, orderWord);
			proc.setString(14, orderDirection);
			proc.registerOutParameter(15, Types.INTEGER);
			proc.execute();
			rs = proc.getResultSet();
			DeclProcessDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, DeclProcessDto.class);
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

	/**
	 * 根据报检号给出各流程操作数据
	 * 
	 * @return
	 */
	public static List<DeclProcessFlowDto> getDeclProcessByDeclNo(String declNo) {
		List<DeclProcessFlowDto> list = new ArrayList<DeclProcessFlowDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		String call = "{call Pro_GetDeclProcessByDeclNo(?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNo);
			proc.execute();
			rs = proc.getResultSet();
			DeclProcessFlowDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, DeclProcessFlowDto.class);
				list.add(dto);
			}
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

		return list;
	}

	/**
	 * 根据报检号给出报检单证的流程操作日志记录
	 * 
	 * @return
	 */
	public static List<DeclProcessLogDto> getDeclProcessOperateLog(String declNo) {
		List<DeclProcessLogDto> list = new ArrayList<DeclProcessLogDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		String call = "{call Pro_GetDeclProcessOperateLog(?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNo);
			proc.execute();
			rs = proc.getResultSet();
			DeclProcessLogDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, DeclProcessLogDto.class);
				list.add(dto);
			}
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

		return list;
	}

}
