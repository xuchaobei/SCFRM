package cn.gov.scciq.bussiness.select;

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
import cn.gov.scciq.dto.InspDeptDto;
import cn.gov.scciq.dto.InspOrgDto;
import cn.gov.scciq.dto.MaterialClassDto;
import cn.gov.scciq.dto.MaterialSubclassDto;
import cn.gov.scciq.dto.MaterialSubsubclassDto;
import cn.gov.scciq.dto.ProcessingMethodDto;
import cn.gov.scciq.dto.ProductClassDto;
import cn.gov.scciq.dto.ProductSubclassDto;
import cn.gov.scciq.util.RsToDtoUtil;

/**
 * 获取所有select集合
 * @author chao.xu
 *
 */
public class SelectDataDao {
    private static Log log=LogFactory.getLog(SelectDataDao.class);

    /**
     * 得到检验机构列表
     * @return
     */
    public static List<InspOrgDto> getInspOrg(){
        List<InspOrgDto> list  = new ArrayList<InspOrgDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetInspOrg()}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.execute();
            rs = proc.getResultSet();
            InspOrgDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, InspOrgDto.class);
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
     * 得到检验部门列表
     * @return
     */
    public static List<InspDeptDto> getInspDept(String orgCode){
        List<InspDeptDto> list  = new ArrayList<InspDeptDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetInspDept(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, orgCode);
            proc.execute();
            rs = proc.getResultSet();
            InspDeptDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, InspDeptDto.class);
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
     * 得到产品大类类别
     * @return
     */
    public static List<ProductClassDto> getProductClass(int startIndex, int pageSize, String orderWord, String orderDirection){
        List<ProductClassDto> list  = new ArrayList<ProductClassDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetProductClass(?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setInt(1, startIndex);
            proc.setInt(2, pageSize);
            proc.setString(3, orderWord);
            proc.setString(4,  orderDirection);
            proc.registerOutParameter(5, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            ProductClassDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, ProductClassDto.class);
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
     * 得到产品小类列表
     * @return
     */
    public static List<ProductSubclassDto> getProductSubclass(String classCode, int startIndex, int pageSize, String orderWord, String orderDirection){
        List<ProductSubclassDto> list  = new ArrayList<ProductSubclassDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetProductSubclass(?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, classCode);
            proc.setInt(2, startIndex);
            proc.setInt(3, pageSize);
            proc.setString(4, orderWord);
            proc.setString(5,  orderDirection);
            proc.registerOutParameter(6, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            ProductSubclassDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, ProductSubclassDto.class);
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
     * 得到原料大类列表
     * @return
     */
    public static List<MaterialClassDto> getMaterialClass(int startIndex, int pageSize, String orderWord, String orderDirection){
        List<MaterialClassDto> list  = new ArrayList<MaterialClassDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetMaterialClass(?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setInt(1, startIndex);
            proc.setInt(2, pageSize);
            proc.setString(3, orderWord);
            proc.setString(4, orderDirection);
            proc.registerOutParameter(5, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            MaterialClassDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, MaterialClassDto.class);
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
     * 得到原料小类列表
     * @return
     */
    public static List<MaterialSubclassDto> getMaterialSubclass(String classCode, int startIndex, int pageSize, String orderWord, String orderDirection){
        List<MaterialSubclassDto> list  = new ArrayList<MaterialSubclassDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetMaterialSubclass(?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, classCode);
            proc.setInt(2, startIndex);
            proc.setInt(3, pageSize);
            proc.setString(4, orderWord);
            proc.setString(5,  orderDirection);
            proc.registerOutParameter(6, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            MaterialSubclassDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, MaterialSubclassDto.class);
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
     * 得到原料细类列表
     * @return
     */
    public static List<MaterialSubsubclassDto> getMaterialSubsubclass(String classCode, String subclassCode, int showFlag, int startIndex, int pageSize, String orderWord, String orderDirection){
        List<MaterialSubsubclassDto> list  = new ArrayList<MaterialSubsubclassDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetMaterialSubsubclass(?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, classCode);
            proc.setString(2, subclassCode);
            proc.setInt(3, showFlag);
            proc.setInt(4, startIndex);
            proc.setInt(5, pageSize);
            proc.setString(6, orderWord);
            proc.setString(7,  orderDirection);
            proc.registerOutParameter(8, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            MaterialSubsubclassDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, MaterialSubsubclassDto.class);
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