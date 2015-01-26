package cn.gov.scciq.bussiness.searchSelect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.dto.BaseDto;
import cn.gov.scciq.dto.CountryDto;
import cn.gov.scciq.dto.EntDto;
import cn.gov.scciq.dto.IntendedUseDto;
import cn.gov.scciq.dto.ItemDto;
import cn.gov.scciq.dto.KeywordsDto;
import cn.gov.scciq.dto.MaterialSourceDto;
import cn.gov.scciq.dto.PackageTypeDto;
import cn.gov.scciq.dto.ProcessingMethodDto;
import cn.gov.scciq.dto.ProductDto;
import cn.gov.scciq.util.RsToDtoUtil;

/**
 * 对应页面上点击搜索按钮获取select集合的action
 * @author chao.xu
 *
 */
public class SearchSelectDao {

    private static Log log=LogFactory.getLog(SearchSelectDao.class);
    
    /**
     * 取得加工方式定义
     * @return
     */
    public static List<ProcessingMethodDto> getProcessingMethod(String methodName, int showFlag, int startIndex, int pageSize, String orderWord, String orderDirection){
        List<ProcessingMethodDto> list  = new ArrayList<ProcessingMethodDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetProcessingMethod(?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, methodName);
            proc.setInt(2, showFlag);
            proc.setInt(3, startIndex);
            proc.setInt(4, pageSize);
            proc.setString(5, orderWord);
            proc.setString(6,  orderDirection);
            proc.registerOutParameter(7, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            ProcessingMethodDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, ProcessingMethodDto.class);
                list.add(dto);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
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
     * 取得原料来源定义
     * @return
     */
    public static List<MaterialSourceDto> getMaterialSource(String sourceName, int showFlag, int startIndex, int pageSize, String orderWord, String orderDirection){
        List<MaterialSourceDto> list  = new ArrayList<MaterialSourceDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetMaterialSource(?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, sourceName);
            proc.setInt(2, showFlag);
            proc.setInt(3, startIndex);
            proc.setInt(4, pageSize);
            proc.setString(5, orderWord);
            proc.setString(6,  orderDirection);
            proc.registerOutParameter(7, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            MaterialSourceDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, MaterialSourceDto.class);
                list.add(dto);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
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
     * 取得包装类型定义
     * @return
     */
    public static List<PackageTypeDto> getPackageType(String typeName, int showFlag, int startIndex, int pageSize, String orderWord, String orderDirection){
        List<PackageTypeDto> list  = new ArrayList<PackageTypeDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetPackageType(?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, typeName);
            proc.setInt(2, showFlag);
            proc.setInt(3, startIndex);
            proc.setInt(4, pageSize);
            proc.setString(5, orderWord);
            proc.setString(6,  orderDirection);
            proc.registerOutParameter(7, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            PackageTypeDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, PackageTypeDto.class);
                list.add(dto);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
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
     * 取得预期用途定义
     * @return
     */
    public static List<IntendedUseDto> getIntendedUse(String useName, int showFlag, int startIndex, int pageSize, String orderWord, String orderDirection){
        List<IntendedUseDto> list  = new ArrayList<IntendedUseDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetIntendedUse(?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, useName);
            proc.setInt(2, showFlag);
            proc.setInt(3, startIndex);
            proc.setInt(4, pageSize);
            proc.setString(5, orderWord);
            proc.setString(6,  orderDirection);
            proc.registerOutParameter(7, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            IntendedUseDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, IntendedUseDto.class);
                list.add(dto);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
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
     * 取得国家地区定义
     * @return
     */
    public static List<CountryDto> getCountry(String countryName, int showFlag, int startIndex, int pageSize, String orderWord, String orderDirection){
        List<CountryDto> list  = new ArrayList<CountryDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetCountry(?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, countryName);
            proc.setInt(2, showFlag);
            proc.setInt(3, startIndex);
            proc.setInt(4, pageSize);
            proc.setString(5, orderWord);
            proc.setString(6,  orderDirection);
            proc.registerOutParameter(7, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            CountryDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, CountryDto.class);
                list.add(dto);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
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
     * 检测项目
     * @return
     */
    public static List<ItemDto> getItem(String itemName, String riskClassCode, int showFlg, int startIndex, int pageSize, String orderWord, String orderDirection){
        List<ItemDto> list  = new ArrayList<ItemDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetItem(?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, itemName);
            proc.setString(2, riskClassCode);
            proc.setInt(3, showFlg);
            proc.setInt(4, startIndex);
            proc.setInt(5, pageSize);
            proc.setString(6, orderWord);
            proc.setString(7, orderDirection);
            proc.registerOutParameter(8, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            ItemDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, ItemDto.class);
                list.add(dto);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
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
     * 根据应急布控ID以及所选的字段名称，查询得到对应的字段值
     * @return
     */
    public static List<KeywordsDto> getCIQControlKeyValue(String ciqControlID, String definedField, String keywords){
        List<KeywordsDto> list  = new ArrayList<KeywordsDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetCIQControlKeyValue(?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, ciqControlID);
            proc.setString(2, definedField);
            proc.setString(3, keywords);
            proc.execute();
            rs = proc.getResultSet();
            KeywordsDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, KeywordsDto.class);
                list.add(dto);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
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
     * 查询辅料
     * @param accessoryName
     * @return
     */
    public static List<String> getAccessory(String accessoryName, int startIndex, int pageSize, String orderWord, String orderDirection) {
        // TODO Auto-generated method stub
        List<String> list  = new ArrayList<String>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetAccessory(?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, accessoryName);
            proc.setInt(2, startIndex);
            proc.setInt(3, pageSize);
            proc.setString(4, orderWord);
            proc.setString(5, orderDirection);
            proc.registerOutParameter(6, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                list.add(rs.getString("AccessoryName"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
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
     * 查询添加剂
     * @param additiveName
     * @return
     */
    public static List<String> getAdditive(String additiveName, int startIndex, int pageSize, String orderWord, String orderDirection) {
        // TODO Auto-generated method stub
        List<String> list  = new ArrayList<String>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetAdditive(?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, additiveName);
            proc.setInt(2, startIndex);
            proc.setInt(3, pageSize);
            proc.setString(4, orderWord);
            proc.setString(5, orderDirection);
            proc.registerOutParameter(6, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
                list.add(rs.getString("AdditiveName"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
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
     * 查询企业
     * @return
     */
    public static List<EntDto> getEnt(String entName, String entCode, String mngOrgCode, String inspOrgCode, String roleCode, int startIndex, int pageSize, String orderWord, String orderDirection) {
        // TODO Auto-generated method stub
        List<EntDto> list  = new ArrayList<EntDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetEnt(?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, entName);
            proc.setString(2, entCode);
            proc.setString(3, mngOrgCode);
            proc.setString(4, inspOrgCode);
            proc.setString(5, roleCode);
            proc.setInt(6, startIndex);
            proc.setInt(7, pageSize);
            proc.setString(8, orderWord);
            proc.setString(9, orderDirection);
            proc.registerOutParameter(10, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            EntDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, EntDto.class);
                list.add(dto);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("", e);
            }
        }
        return list;
    }


	public static List<BaseDto> getBase(String baseName, String baseCode,
			int startIndex, int pageSize, String orderWord,
			String orderDirection) {
		List<BaseDto> list  = new ArrayList<BaseDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetBaseList(?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, baseName);
            proc.setString(2, baseCode);
            proc.setInt(3, startIndex);
            proc.setInt(4, pageSize);
            proc.setString(5, orderWord);
            proc.setString(6, orderDirection);
            proc.registerOutParameter(7, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            BaseDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, BaseDto.class);
                list.add(dto);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                log.error("", e);
            }
        }
        return list;
	}


	public static List<ProductDto> getProductByQuery(String productName) {
		// TODO Auto-generated method stub
		List<ProductDto> list  = new ArrayList<ProductDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetProductByQuery(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, productName);
            proc.execute();
            rs = proc.getResultSet();
            ProductDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, ProductDto.class);
                list.add(dto);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
                if(rs != null){
                    rs.close();
                }
                if(proc != null){
                    proc.close();
                }
                if(conn != null){
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
