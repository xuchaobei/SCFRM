package cn.gov.scciq.bussiness.declQueryStatistics;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class DeclQueryStatisticsDao {

	private static final Log log = LogFactory
			.getLog(DeclQueryStatisticsDao.class);

	/**
	 * 进口批查询中的删除操作员对应所有的查询条件
	 * 
	 * @param orgCode
	 * @param deptCode
	 * @param operatorCode
	 * @return
	 */
	public static boolean delDeclQueryAllCondition(String orgCode,
			String deptCode, String operatorCode) {
		// TODO Auto-generated method stub
		boolean retCode = true;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_DelDeclQueryAllCondition(?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, orgCode);
			proc.setString(2, deptCode);
			proc.setString(3, operatorCode);
			proc.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			retCode = false;
			log.error("", e);
		} catch (Exception e) {
			retCode = false;
			log.error("", e);
		} finally {
			try {
				if (proc != null) {
					proc.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("", e);
			}
		}
		return retCode;
	}

	/**
	 * 报检批查询中的保存查询条件
	 * 
	 * @param declQueryConditionID
	 * @param orgCode
	 * @param deptCode
	 * @param operatorCode
	 * @param sortNo
	 * @param leftLogic
	 * @param definedField
	 * @param operateSign
	 * @param operateValue
	 * @param rightLogic
	 * @return
	 */
	public static String saveDeclQueryCondition(int declQueryConditionID,
			String orgCode, String deptCode, String operatorCode, int sortNo,
			String leftLogic, String definedField, String operateSign,
			String operateValue, String rightLogic) {
		String retStr = ConstantStr.SAVE_ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_SaveDeclQueryCondition(?,?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setInt(1, declQueryConditionID);
			proc.setString(2, orgCode);
			proc.setString(3, deptCode);
			proc.setString(4, operatorCode);
			proc.setInt(5, sortNo);
			proc.setString(6, leftLogic);
			proc.setString(7, definedField);
			proc.setString(8, operateSign);
			proc.setString(9, operateValue);
			proc.setString(10, rightLogic);
			proc.execute();
			retStr = "1";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				if (proc != null) {
					proc.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("", e);
			}
		}
		return retStr;
	}

	/**
	 * 在报检查询中，显示所有的查询条件
	 * 
	 * @return
	 */
	public static List<DeclQueryConditionDto> getDeclQueryCondition(
			String orgCode, String deptCode, String operatorCode) {
		List<DeclQueryConditionDto> list = new ArrayList<DeclQueryConditionDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		String call = "{call Pro_GetDeclQueryCondition(?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, orgCode);
			proc.setString(2, deptCode);
			proc.setString(3, operatorCode);
			proc.execute();
			rs = proc.getResultSet();
			DeclQueryConditionDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, DeclQueryConditionDto.class);
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
	 * 进口批查询中删除单个查询条件（即界面中删除）
	 * 
	 * @param declQueryConditionID
	 * @return
	 */
	public static boolean delDeclQuerySingleCondition(int declQueryConditionID) {
		boolean retCode = true;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_DelDeclQuerySingleCondition(?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setInt(1, declQueryConditionID);
			proc.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			retCode = false;
			log.error("", e);
		} catch (Exception e) {
			retCode = false;
			log.error("", e);
		} finally {
			try {
				if (proc != null) {
					proc.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("", e);
			}
		}
		return retCode;
	}

	/**
	 * 生成报检批查询的SQL语句
	 * 
	 * @return
	 */
	public static String generateDeclQuerySQLStr(String orgCode,
			String deptCode, String operatorCode) {
		String sqlStr = "";
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_GenerateDeclQuerySQLStr(?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, orgCode);
			proc.setString(2, deptCode);
			proc.setString(3, operatorCode);
			proc.registerOutParameter(4, java.sql.Types.NVARCHAR);
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.registerOutParameter(6, java.sql.Types.VARCHAR);
			proc.registerOutParameter(7, java.sql.Types.VARCHAR);
			proc.execute();
			sqlStr = proc.getNString(4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				if (proc != null) {
					proc.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("", e);
			}
		}
		return sqlStr;
	}

	/**
	 * 取得查询结果
	 * 
	 * @param orgCode
	 * @param deptCode
	 * @param operatorCode
	 * @param startIndex
	 * @param pageSize
	 * @param orderWord
	 * @param orderDirection
	 * @return
	 */
	public static Map<Integer, Object> getDeclQueryResult(String orgCode,
			String deptCode, String operatorCode, int startIndex, int pageSize,
			String orderWord, String orderDirection) {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		List<DeclQueryResultDto> list = new ArrayList<DeclQueryResultDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		int recordsTotal = 0;
		String call = "{call Pro_GetDeclQueryResult(?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, orgCode);
			proc.setString(2, deptCode);
			proc.setString(3, operatorCode);
			proc.setInt(4, startIndex);
			proc.setInt(5, pageSize);
			proc.setString(6, orderWord);
			proc.setString(7, orderDirection);
			proc.registerOutParameter(8, Types.INTEGER);
			proc.registerOutParameter(9, Types.VARCHAR);
			proc.execute();
			rs = proc.getResultSet();
			DeclQueryResultDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, DeclQueryResultDto.class);
				list.add(dto);
			}
			recordsTotal = proc.getInt(8);
			String errorMsg = proc.getString(9);
			rsMap.put(1, recordsTotal);
			if (!errorMsg.equals("")) {
				JSONObject jo = new JSONObject();
				jo.put("error", errorMsg);
				rsMap.put(2, jo);
			} else {
				rsMap.put(2, list);
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
			}
		}

		return rsMap;
	}
}
