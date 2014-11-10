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
import cn.gov.scciq.dto.CountryDto;
import cn.gov.scciq.dto.IntendedUseDto;
import cn.gov.scciq.dto.MaterialSourceDto;
import cn.gov.scciq.dto.PackageTypeDto;
import cn.gov.scciq.dto.ProcessingMethodDto;
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
    
}