package cn.gov.scciq.bussiness.sampleRegister;

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
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class SampleRegisterDao {

	private static final Log log = LogFactory.getLog(SampleRegisterDao.class);

	/**
	 * 针对某一特定流程，取得待处理或已处理的报检数据
	 * 
	 * @return
	 */
	public static Map<Integer, Object> getDeclInfoForProcess(String declNo,
			String inspOrgCode, String inspDeptCode, String inspOperatorCode,
			String processName, int finishFlg, int startIndex, int pageSize,
			String orderWord, String orderDirection) {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		List<DeclInfoDto> list = new ArrayList<DeclInfoDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		int recordsTotal = 0;
		String call = "{call Pro_GetDeclInfoForProcess(?,?,?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNo);
			proc.setString(2, inspOrgCode);
			proc.setString(3, inspDeptCode);
			proc.setString(4, inspOperatorCode);
			proc.setString(5, processName);
			proc.setInt(6, finishFlg);
			proc.setInt(7, startIndex);
			proc.setInt(8, pageSize);
			proc.setString(9, orderWord);
			proc.setString(10, orderDirection);
			proc.registerOutParameter(11, Types.INTEGER);
			proc.execute();
			rs = proc.getResultSet();
			DeclInfoDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, DeclInfoDto.class);
				list.add(dto);
			}
			recordsTotal = proc.getInt(11);
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
	 * 根据报检号，得到该单证的流程状态
	 * 
	 * @return
	 */
	public static String getProcessStatusForDecl(String declNo,
			String processName) {
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		String status = "";
		String call = "{call GetProcessStatusForDecl(?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNo);
			proc.setString(2, processName);
			proc.registerOutParameter(3, Types.VARCHAR);
			proc.execute();
			status = proc.getString(3);
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
		return status;
	}

	/**
	 * 根据报检号给出报检产品数据
	 * 
	 * @param declNo
	 * @return
	 */
	public static List<DeclProductDto> getDeclProduct(String declNo) {
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		List<DeclProductDto> list = new ArrayList<DeclProductDto>();
		String call = "{call Pro_GetDeclProduct(?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNo);
			proc.execute();
			rs = proc.getResultSet();
			DeclProductDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, DeclProductDto.class);
				list.add(dto);
			}
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
		return list;
	}

	/**
	 * 根据报检产品ID号给出检测项目
	 * 
	 * @return
	 */
	public static Map<Integer, Object> getDeclProductItem(
			String declProductDetailID, boolean showSamplingItemFlg,
			boolean showNotLabFlg, int startIndex, int pageSize,
			String orderWord, String orderDirection) {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		List<DeclProductItemDto> list = new ArrayList<DeclProductItemDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		int recordsTotal = 0;
		String call = "{call Pro_GetDeclProductItem(?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declProductDetailID);
			proc.setBoolean(2, showSamplingItemFlg);
			proc.setBoolean(3, showNotLabFlg);
			proc.setInt(4, startIndex);
			proc.setInt(5, pageSize);
			proc.setString(6, orderWord);
			proc.setString(7, orderDirection);
			proc.registerOutParameter(8, Types.INTEGER);
			proc.execute();
			rs = proc.getResultSet();
			DeclProductItemDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, DeclProductItemDto.class);
				list.add(dto);
			}
			recordsTotal = proc.getInt(8);
			rsMap.put(1, recordsTotal);
			rsMap.put(2, list);
		} catch (SQLException e) {
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
	 * 根据出口产品ID得到该产品的样品数据
	 * 
	 * @return
	 */
	public static List<LabSampleDto> getLabSampleByProduct(
			String declProductDetailID) {
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		List<LabSampleDto> list = new ArrayList<LabSampleDto>();
		String call = "{call Pro_GetLabSampleByProduct(?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declProductDetailID);
			proc.execute();
			rs = proc.getResultSet();
			LabSampleDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, LabSampleDto.class);
				list.add(dto);
			}
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
		return list;
	}

	/**
	 * 根据出口产品得到该产品的样品检测项目数据
	 * 
	 * @return
	 */
	public static List<LabSampleItemDto> getLabSampleItemByProduct(
			String declProductDetailID) {
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		List<LabSampleItemDto> list = new ArrayList<LabSampleItemDto>();
		String call = "{call Pro_GetLabSampleItemByProduct(?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declProductDetailID);
			proc.execute();
			rs = proc.getResultSet();
			LabSampleItemDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, LabSampleItemDto.class);
				list.add(dto);
			}
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
		return list;
	}

	/**
	 * 撤销某一指定流程的操作
	 * 
	 * @return
	 */
	public static String cancelCurrentProcess(String declNo,
			String processName, String processOrgCode, String processDeptCode,
			String processOperatorCode) {
		String rs = ConstantStr.ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_CancelCurrentProcess(?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNo);
			proc.setString(2, processName);
			proc.setString(3, processOrgCode);
			proc.setString(4, processDeptCode);
			proc.setString(5, processOperatorCode);
			proc.registerOutParameter(6, Types.VARCHAR);
			proc.execute();
			rs = proc.getString(6);
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
		return rs;
	}

	/**
	 * 检查是否允许操作
	 * 
	 * @param processName
	 * @param declNo
	 * @return
	 */
	public static String checkIfProcessOperate(String processName, String declNo) {
		String rs = ConstantStr.ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_CheckIfProcessOperate(?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, processName);
			proc.setString(2, declNo);
			proc.registerOutParameter(3, Types.VARCHAR);
			proc.execute();
			rs = proc.getString(3);
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
		return rs;
	}

	public static String checkItemIfSampling(String declProductDetailID) {
		String rs = ConstantStr.ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_CheckItemIfSampling(?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declProductDetailID);
			proc.registerOutParameter(2, Types.VARCHAR);
			proc.execute();
			rs = proc.getString(2);
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
		return rs;
	}

	public static LabApplyDto getLabApply(String declNo, String orgCode,
			String deptCode, String operatorCode, String declProductDetailID) {

		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		LabApplyDto dto = new LabApplyDto();
		String call = "{call Pro_GetLabApply(?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNo);
			proc.setString(2, orgCode);
			proc.setString(3, deptCode);
			proc.setString(4, operatorCode);
			proc.setString(5, declProductDetailID);
			proc.execute();
			rs = proc.getResultSet();
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, LabApplyDto.class);
				break;
			}
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
		return dto;
	}

	public static LabSampleInfoDto getLabSampleInfoBySampleID(String sampleID,
			String declProductDetailID, String productCode, String orgCode,
			String deptCode) {

		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		LabSampleInfoDto dto = new LabSampleInfoDto();
		String call = "{call Pro_GetLabSampleInfoBySampleIDy(?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, sampleID);
			proc.setString(2, declProductDetailID);
			proc.setString(3, productCode);
			proc.setString(4, orgCode);
			proc.setString(5, deptCode);
			proc.execute();
			rs = proc.getResultSet();
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, LabSampleInfoDto.class);
				break;
			}
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
		return dto;
	}

	public static List<LabItemPlanDto> getLabItemMatched(
			String declProductDetailID) {

		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		List<LabItemPlanDto> list = new ArrayList<LabItemPlanDto>();
		LabItemPlanDto dto = null;
		String call = "{call Pro_GetLabItemMatched(?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declProductDetailID);
			proc.execute();
			rs = proc.getResultSet();
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, LabItemPlanDto.class);
				list.add(dto);
			}
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
		return list;
	}


	
}
