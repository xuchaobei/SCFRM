package cn.gov.scciq.bussiness.goodsStatistics;

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

public class GoodsStatisticsDao {

	private static final Log log = LogFactory.getLog(GoodsStatisticsDao.class);
	
	/**
	 * 根据统计条件获得货物批统计结果
	 * @return
	 */
	public static Map<Integer,Object> getStaticResultForGoods(int startYear, int startMonth, int endYear , int endMonth, String productCode, String orgCode,
			String deptCode, String countryCode, String entCode,String productClassCode, String productSubclassCode,String productName,
			boolean group_Org, boolean group_Dept, boolean group_Country, boolean group_Ent, boolean group_ProductClass, boolean group_ProductSubclass,String inpOrgCode,
			String inspDeptCode, String operateCode, int startIndex, int pageSize){
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		List<GoodsStatisticsDto> list = new ArrayList<GoodsStatisticsDto>();
		GoodsStatisticsSingleDto singleDto = new GoodsStatisticsSingleDto();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		int recordsTotal = 0;
		String call = "{call Pro_GetStaticResultForGoods(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		try {
			conn = DBPool.ds.getConnection();
			proc = conn.prepareCall(call);
			proc.setInt(1, startYear);
			proc.setInt(2, startMonth);
			proc.setInt(3, endYear);
			proc.setInt(4, endMonth);
			proc.setString(5, productCode);
			proc.setString(6, orgCode);
			proc.setString(7, deptCode);
			proc.setString(8, countryCode);
			proc.setString(9, entCode);
			proc.setString(10, productClassCode);
			proc.setString(11, productSubclassCode);
			proc.setString(12, productName);
			proc.setBoolean(13, group_Org);
			proc.setBoolean(14, group_Dept);
			proc.setBoolean(15, group_Country);
			proc.setBoolean(16, group_Ent);
			proc.setBoolean(17, group_ProductClass);
			proc.setBoolean(18, group_ProductSubclass);
			proc.setString(19, inpOrgCode);
			proc.setString(20, inspDeptCode);
			proc.setString(21, operateCode);
			proc.setInt(22, startIndex);
			proc.setInt(23, pageSize);
			proc.registerOutParameter(24, Types.INTEGER);
			proc.execute();
			rs = proc.getResultSet();
			GoodsStatisticsDto dto = null;
			while (rs.next()) {
				dto = RsToDtoUtil.tranRsToDto(rs, GoodsStatisticsDto.class);
				list.add(dto);
			}
			if(proc.getMoreResults()){
				rs = proc.getResultSet();
				while(rs.next()){
					singleDto = RsToDtoUtil.tranRsToDto(rs, GoodsStatisticsSingleDto.class);
					break;
				}
			}
			recordsTotal = proc.getInt(24);
			rsMap.put(1, recordsTotal);
			rsMap.put(2, list);
			rsMap.put(3, singleDto);
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
