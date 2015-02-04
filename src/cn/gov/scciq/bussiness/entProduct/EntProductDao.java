package cn.gov.scciq.bussiness.entProduct;

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

public class EntProductDao {

	private static final Log log = LogFactory.getLog(EntProductDao.class);

	/**
	 * 企业产品查询
	 * 
	 * @return
	 */
	public static Map<Integer, Object> getEntProductByQuery(String entCode,
			String entName, String entProductName, String rapidRelease,
			String greenChanel, int startIndex, int pageSize, String orderWord,
			String orderDirection) {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		List<EntProductResDto> list = new ArrayList<EntProductResDto>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		int recordsTotal = 0;
		String call = "{call Pro_GetEntProductByQuery(?,?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, entCode);
			proc.setString(2, entName);
			proc.setString(3, entProductName);
			proc.setString(4, rapidRelease);
			proc.setString(5, greenChanel);
			proc.setInt(6, startIndex);
			proc.setInt(7, pageSize);
			proc.setString(8, orderWord);
			proc.setString(9, orderDirection);
			proc.registerOutParameter(10, Types.INTEGER);
			proc.execute();
			rs = proc.getResultSet();
			EntProductResDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, EntProductResDto.class);
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

	/**
	 * 根据企业代码和企业产品自编号取得详细数据，包括产品特征数据、原料数据、辅料数据、添加剂数据
	 * 
	 * @param entCode
	 * @param entProductCode
	 * @return
	 */
	public static EntProductDetailDto getEntProductDetailByID(String entCode,
			String entProductCode) {
		EntProductDetailDto dto = new EntProductDetailDto();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		String call = "{call Pro_GetEntProductDetailByID(?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, entCode);
			proc.setString(2, entProductCode);
			proc.execute();
			rs = proc.getResultSet();
			EntProductBaseDto productBaseDto = null;
			while (rs.next()) {
				productBaseDto = RsToDtoUtil.tranRsToDto(rs,
						EntProductBaseDto.class);
				break;
			}
			dto.setEntProductBase(productBaseDto);
			if (proc.getMoreResults()) {
				rs = proc.getResultSet();
				List<EntProductMaterialDto> productMaterialList = new ArrayList<EntProductMaterialDto>();
				EntProductMaterialDto productMaterialDto = null;
				while (rs.next()) {
					productMaterialDto = RsToDtoUtil.tranRsToDto(rs,
							EntProductMaterialDto.class);
					productMaterialList.add(productMaterialDto);
				}
				dto.setEntProductMaterialList(productMaterialList);
			}
			if (proc.getMoreResults()) {
				rs = proc.getResultSet();
				List<EntProductAccessoryDto> productAccessoryList = new ArrayList<EntProductAccessoryDto>();
				EntProductAccessoryDto productAccessoryDto = null;
				while (rs.next()) {
					productAccessoryDto = RsToDtoUtil.tranRsToDto(rs,
							EntProductAccessoryDto.class);
					productAccessoryList.add(productAccessoryDto);
				}
				dto.setEntProductAccessoryDtoList(productAccessoryList);
			}
			if (proc.getMoreResults()) {
				rs = proc.getResultSet();
				List<EntProductAdditiveDto> productAdditiveList = new ArrayList<EntProductAdditiveDto>();
				EntProductAdditiveDto productAdditiveDto = null;
				while (rs.next()) {
					productAdditiveDto = RsToDtoUtil.tranRsToDto(rs,
							EntProductAdditiveDto.class);
					productAdditiveList.add(productAdditiveDto);
				}
				dto.setEntProductAdditiveDtoList(productAdditiveList);
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
		return dto;
	}

	public static String setEntProductRapidRelease(String entCode,
			String productCode, String countryCode, String entProductCode) {
		String retStr = ConstantStr.SAVE_ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_SetEntProductRapidRelease(?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, entCode);
			proc.setString(2, productCode);
			proc.setString(3, countryCode);
			proc.setString(4, entProductCode);
			proc.execute();
			retStr = "true";
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

	public static String setEntProductGreenChanel(String entCode,
			String productCode, String countryCode, String entProductCode) {
		String retStr = ConstantStr.SAVE_ERROR_MSG;
		Connection conn = null;
		CallableStatement proc = null;
		String call = "{call Pro_SetEntProductGreenChanel(?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setString(1, entCode);
			proc.setString(2, productCode);
			proc.setString(3, countryCode);
			proc.setString(4, entProductCode);
			proc.execute();
			retStr = "true";
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

}
