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

import cn.gov.scciq.bussiness.sampleRegister.lab.SendDeclGoodsDto;
import cn.gov.scciq.bussiness.sampleRegister.lab.SendDeclInfoDto;
import cn.gov.scciq.bussiness.sampleRegister.lab.SendLabApplyInfoDto;
import cn.gov.scciq.bussiness.sampleRegister.lab.SendLabSampleDto;
import cn.gov.scciq.bussiness.sampleRegister.lab.SendLabSampleItemDto;
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
		String call = "{call Pro_GetProcessStatusForDecl(?,?,?)}";
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
			String declProductDetailID, int showSamplingItemFlg,
			int showNotLabFlg, int startIndex, int pageSize,
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
			proc.setInt(2, showSamplingItemFlg);
			proc.setInt(3, showNotLabFlg);
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
		String call = "{call Pro_GetLabSampleInfoBySampleID(?,?,?,?,?)}";
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

	/**
	 * 删除拟送检项目
	 * @param labItemMatchID
	 * @return
	 */
	public static String delLabItemMatchedForNewSample(String labItemMatchID){
		String rs = ConstantStr.ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_DelLabItemMatchedForNewSample(?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, labItemMatchID);
			proc.execute();
			rs = "1";
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

	public static String saveLabApplyInfo(String declNo,String applyKind, String sampleKind, String applyDept, 
			String appliant, String sampleState, String sampleDisposal, String samplePreservation, String remarks) {
		// TODO Auto-generated method stub
		String rs = ConstantStr.SAVE_ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_SaveLabApplyInfo(?,?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNo);
			proc.setString(2, applyKind);
			proc.setString(3, sampleKind);
			proc.setString(4, applyDept);
			proc.setString(5, appliant);
			proc.setString(6, sampleState);
			proc.setString(7, sampleDisposal);
			proc.setString(8, samplePreservation);
			proc.setString(9, remarks);
			proc.registerOutParameter(10, java.sql.Types.VARCHAR);
			proc.execute();
			rs = proc.getString(10);
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
	
	public static String saveLabItemMatchedManual(String itemCode,
			String declProductDetailID, String declProductItemID, String orgCode) {
		// TODO Auto-generated method stub
		String rs = ConstantStr.SAVE_ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_SaveLabItemMatchedManual(?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, itemCode);
			proc.setString(2, declProductDetailID);
			proc.setString(3, declProductItemID);
			proc.setString(4, orgCode);
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.execute();
			rs = proc.getString(5);
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

	public static String saveLabSample(String sampleID, String labApplyID,
			String declProductDetailID, String sampleName, String sampleCount,
			String countUnit, String sampleRemarks, String copyCount) {
		// TODO Auto-generated method stub
		String rs = ConstantStr.SAVE_ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_SaveLabSample(?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, sampleID);
			proc.setString(2, labApplyID);
			proc.setString(3, declProductDetailID);
			proc.setString(4, sampleName);
			proc.setString(5, sampleCount);
			proc.setString(6, countUnit);
			proc.setString(7, sampleRemarks);
			proc.setString(8, copyCount);
			proc.registerOutParameter(9, java.sql.Types.VARCHAR);
			proc.execute();
			rs = proc.getString(9);
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
	
	
	
	
	public static String saveLabDefaultData(String orgCode, String deptCode,
			String operatorCode, String declProductDetailID, String applyKind,
			String sampleKind, String applyDept, String appliant,
			String sampleState, String sampleDisposal, String samplePreservation) {
		// TODO Auto-generated method stub
		String rs = ConstantStr.SAVE_ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_SaveLabDefaultData(?,?,?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, orgCode);
			proc.setString(2, deptCode);
			proc.setString(3, operatorCode);
			proc.setString(4, declProductDetailID);
			proc.setString(5, applyKind);
			proc.setString(6, sampleKind);
			proc.setString(7, applyDept);
			proc.setString(8, appliant);
			proc.setString(9, sampleState);
			proc.setString(10, sampleDisposal);
			proc.setString(11, samplePreservation);
			proc.execute();
			rs = "1";
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
	
	public static String saveLabSampleItem(String declProductDetailID, String sampleItemID,
			String sampleID, String itemCode, String lrpItemNo,
			String lrpItemName, String lrpTestStd, String labFlg) {
		// TODO Auto-generated method stub
		String rs = ConstantStr.SAVE_ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_SaveLabSampleItem(?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declProductDetailID);
			proc.setString(2, sampleItemID);
			proc.setString(3, sampleID);
			proc.setString(4, itemCode);
			proc.setString(5, lrpItemNo);
			proc.setString(6, lrpItemName);
			proc.setString(7, lrpTestStd);
			proc.setString(8, labFlg);
			proc.registerOutParameter(9, java.sql.Types.VARCHAR);
			proc.execute();
			rs = proc.getString(9);
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

	public static List<LabItemDto> getLabItemByQuery(String itemName,
			String labDeptName, int rowIndex, int pageSize, String orderWord,
			String orderDirec) {
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		List<LabItemDto> list = new ArrayList<LabItemDto>();
		LabItemDto dto = null;
		String call = "{call Pro_GetLabItemByQuery(?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, itemName);
			proc.setString(2, labDeptName);
			proc.setInt(3, rowIndex);
			proc.setInt(4, pageSize);
			proc.setString(5, orderWord);
			proc.setString(6, orderDirec );
			proc.registerOutParameter(7, java.sql.Types.INTEGER);
			proc.execute();
			rs = proc.getResultSet();
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, LabItemDto.class);
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				if(rs != null){
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
		return list;
	}
	
	public static String saveLabItemMatched(String declProductDetailID, String itemCode,String lrpItemID,
			String lrpItemName,String lrpItemTestStd,String labDeptName,String declProductItemID){
		String rs = ConstantStr.SAVE_ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_SaveLabItemMatched(?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declProductDetailID);
			proc.setString(2, itemCode);
			proc.setString(3, lrpItemID);
			proc.setString(4, lrpItemName);
			proc.setString(5, lrpItemTestStd);
			proc.setString(6, labDeptName);
			proc.setString(7, declProductItemID);
			proc.execute();
			rs = "true";
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
	
	public static List<ItemSubDto> getItemSubByDeclItem(String itemCode,String  productCode,String orgCode){
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		List<ItemSubDto> list = new ArrayList<ItemSubDto>();
		ItemSubDto dto = null;
		String call = "{call Pro_GetItemSubByDeclItem(?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, itemCode);
			proc.setString(2, productCode);
			proc.setString(3, orgCode);
			proc.execute();
			rs = proc.getResultSet();
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, ItemSubDto.class);
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				if(rs != null){
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
		return list;
	}
	
	public static List<ItemSubMatchedDto> getItemSubMatchedForDeclItem(String itemCode, String declProductItemID){
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		List<ItemSubMatchedDto> list = new ArrayList<ItemSubMatchedDto>();
		ItemSubMatchedDto dto = null;
		String call = "{call Pro_GetItemSubMatchedForDeclItem(?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, itemCode);
			proc.setString(2, declProductItemID);
			proc.execute();
			rs = proc.getResultSet();
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, ItemSubMatchedDto.class);
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				if(rs != null){
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
		return list;
	}
	
	public static String saveLabItemMatchedForSubitem(String itemCode,String productCode,String orgCode,
			String lrpItemNo,String declProductDetailID,String declProductItemID){
		String rs = ConstantStr.SAVE_ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_SaveLabItemMatchedForSubitem(?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, itemCode);
			proc.setString(2, productCode);
			proc.setString(3, orgCode);
			proc.setString(4, lrpItemNo);
			proc.setString(5, declProductDetailID);
			proc.setString(6, declProductItemID);
			proc.registerOutParameter(7, java.sql.Types.VARCHAR);
			proc.execute();
			rs = proc.getString(7);
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
	
	public static String delItemSubMatchedForDeclItem(String labItemMatchID){
		String rs = ConstantStr.ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_DelItemSubMatchedForDeclItem(?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, labItemMatchID);
			proc.execute();
			rs = "true";
		} catch (SQLException e) {
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
				log.error("", e);
			}
		}
		return rs;
	}
	
	public static String sampleRegAuto(String declNo, String orgCode, String deptCode, String operatorCode){
		String rs = ConstantStr.ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_SampleRegAuto(?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNo);
			proc.setString(2, orgCode);
			proc.setString(3, deptCode);
			proc.setString(4, operatorCode);
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.execute();
			rs = proc.getString(5);
		} catch (SQLException e) {
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
				log.error("", e);
			}
		}
		return rs;
	}
	
	public static String divideLabSample(String labSampleID, String dividedSampleNum){
		String rs = ConstantStr.ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_DivideLabSample(?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, labSampleID);
			proc.setString(2, dividedSampleNum);
			proc.registerOutParameter(3, java.sql.Types.VARCHAR);
			proc.execute();
			rs = proc.getString(3);
		} catch (SQLException e) {
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
				log.error("", e);
			}
		}
		return rs;
	}
	
	public static String delLabSample(String labSampleID){
		String rs = ConstantStr.ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_DelLabSample(?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, labSampleID);
			proc.registerOutParameter(2, java.sql.Types.VARCHAR);
			proc.execute();
			rs = proc.getString(2);
		} catch (SQLException e) {
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
				log.error("", e);
			}
		}
		return rs;
	}
	
	public static String delLabSampleItem(String labSampleItemID){
		String rs = ConstantStr.ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_DelLabSampleItem(?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, labSampleItemID);
			proc.registerOutParameter(2, java.sql.Types.VARCHAR);
			proc.execute();
			rs = proc.getString(2);
		} catch (SQLException e) {
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
				log.error("", e);
			}
		}
		return rs;
	}
	
	public static Map<String, Object> checkIfCanSubmitLab(String declNo){
		Map<String, Object> map = new HashMap<String, Object>();		
		String rs = ConstantStr.ERROR_MSG;
		Integer labApplyID = 0;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_CheckIfCanSubmitLab(?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNo);
			proc.registerOutParameter(2, java.sql.Types.VARCHAR);
			proc.registerOutParameter(3, java.sql.Types.INTEGER);
			proc.execute();
			rs = proc.getString(2);
			labApplyID = proc.getInt(3);
		} catch (SQLException e) {
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
				log.error("", e);
			}
		}
		map.put("returnStr", rs);
		map.put("labApplyID", labApplyID);
		return map;
	}
	
	
	public static Map<String, Object> sampleReg(String processOrgCode,String processDeptCode,
			String processOperatorCode,String declNo,String labApplyID){
		Map<String, Object> rsMap = new HashMap<String, Object>();
		List<SendLabApplyInfoDto> labApplylist = new ArrayList<SendLabApplyInfoDto>();
		List<SendLabSampleDto> labSamplelist = new ArrayList<SendLabSampleDto>();
		List<SendLabSampleItemDto> labItemlist = new ArrayList<SendLabSampleItemDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		String call = "{call Pro_SampleReg(?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, processOrgCode);
			proc.setString(2, processDeptCode);
			proc.setString(3, processOperatorCode);
			proc.setString(4, declNo);
			proc.setString(5, labApplyID);
			proc.execute();
			rs = proc.getResultSet();
			SendLabApplyInfoDto labApplyDto = null;
			while (rs.next()) {
				labApplyDto = RsToDtoUtil.tranRsToDto(rs, SendLabApplyInfoDto.class);
				labApplylist.add(labApplyDto);
			}
			if(proc.getMoreResults()){
				SendLabSampleDto labSampleDto = null;
				rs = proc.getResultSet();
				while (rs.next()){
					labSampleDto = RsToDtoUtil.tranRsToDto(rs, SendLabSampleDto.class);
					labSamplelist.add(labSampleDto);
				}				
			}
			if(proc.getMoreResults()){
				SendLabSampleItemDto labSampleItemDto = null;
				rs = proc.getResultSet();
				while (rs.next()){
					labSampleItemDto = RsToDtoUtil.tranRsToDto(rs, SendLabSampleItemDto.class);
					labItemlist.add(labSampleItemDto);
				}				
			}
			rsMap.put("labApply", labApplylist);
			rsMap.put("labSample", labSamplelist);
			rsMap.put("labItem", labItemlist);
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
	
	public static List<LabApplyPrintDto> getLabApplyByDeclNo(String declNo){
		List<LabApplyPrintDto> list = new ArrayList<LabApplyPrintDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		String call = "{call Pro_GetLabApplyByDeclNo(?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNo);
			proc.execute();
			rs = proc.getResultSet();
			LabApplyPrintDto dto = null;
			while(rs.next()){
				dto = RsToDtoUtil.tranRsToDto(rs, LabApplyPrintDto.class);
				list.add(dto);
			}
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
				log.error("", e);
			}
		}
		return list;
	}
	
	public static String submitLabSuccess(String declNO, String labApplyID, String processOrgCode, String processDeptCode, String processOperatorCode){
		String rs = "样品登记失败";
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_SubmitLabSuccess(?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNO);
			proc.setString(2, labApplyID);
			proc.setString(3, processOrgCode);
			proc.setString(4, processDeptCode);
			proc.setString(5, processOperatorCode);
			proc.execute();
			rs = "不存在提交到LMIS的样品数据，样品登记完成";
		} catch (SQLException e) {
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
				log.error("", e);
			}
		}
		return rs;
	}
	
	public static List<SendDeclInfoDto> getDeclInfoFromCIQ(String declNo){
		List<SendDeclInfoDto> list = new ArrayList<SendDeclInfoDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		String call = "{call Pro_GetDeclInfoFromCIQ(?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNo);
			proc.execute();
			rs = proc.getResultSet();
			SendDeclInfoDto dto = null;
			while(rs.next()){
				dto = RsToDtoUtil.tranRsToDto(rs, SendDeclInfoDto.class);
				list.add(dto);
			}
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
				log.error("", e);
			}
		}
		return list;
	}
	
	public static List<SendDeclGoodsDto> getDeclGoodsInfoFromCIQ(String declNo, String goodsNo){
		List<SendDeclGoodsDto> list = new ArrayList<SendDeclGoodsDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		String call = "{call Pro_GetDeclGoodsInfoFromCIQ(?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, declNo);
			proc.setString(2, goodsNo);
			proc.execute();
			rs = proc.getResultSet();
			SendDeclGoodsDto dto = null;
			while(rs.next()){
				dto = RsToDtoUtil.tranRsToDto(rs, SendDeclGoodsDto.class);
				list.add(dto);
			}
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
				log.error("", e);
			}
		}
		return list;
	}
}
