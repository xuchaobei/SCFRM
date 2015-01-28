package cn.gov.scciq.bussiness.productCtrlItem;

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

import cn.gov.scciq.bussiness.baseMng.BaseMngDetailDto;
import cn.gov.scciq.bussiness.convCtrl.ConvCtrlItemLimitDto;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class ProductCtrlItemDao {
    
    private static final Log log = LogFactory.getLog(ProductCtrlItemDao.class);

    /**
     * 根据条件得到监控表单记录
     * @return
     */
    public static Map<Integer, Object> getProductControlItem(String productControlItemID, String productCode, String productName, String countryName, String itemName, int startIndex, int pageSize , String orderWord ,String orderDirection){
        Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
        List<ProductCtrlItemDto> list = new ArrayList<ProductCtrlItemDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        int recordsTotal = 0;
        String call = "{call Pro_GetProductControlItem(?,?,?,?,?,?,?,?,?,?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, productControlItemID);
            proc.setString(2, productCode);
            proc.setString(3, productName);
            proc.setString(4, countryName);
            proc.setString(5, itemName);
            proc.setInt(6, startIndex);
            proc.setInt(7, pageSize);
            proc.setString(8, orderWord);
            proc.setString(9, orderDirection);
            proc.registerOutParameter(10, Types.INTEGER);
            proc.execute();
            rs = proc.getResultSet();
            ProductCtrlItemDto dto = null;
            while(rs.next()){
                dto = RsToDtoUtil.tranRsToDto(rs, ProductCtrlItemDto.class);
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
            } catch (Exception e) {
                // TODO Auto-generated catch block
                log.error("", e);
            }
        }
        
        return rsMap;
    }
    
    /**
     * 根据产品编号得到产品数据
     * @return
     */
    public static ProductDetailAllDto getProductByCode(String productCode){
    	ProductDetailAllDto dto = new ProductDetailAllDto();
    	ProductDetailDto productDto = null;
    	List<ProductMatDetailDto> list = new ArrayList<ProductMatDetailDto>();
        Connection conn = null;
        CallableStatement proc = null;
        ResultSet rs = null;
        String call = "{call Pro_GetProductByCode(?)}";
        try {
            conn = DBPool.ds.getConnection();
            proc = conn.prepareCall(call);
            proc.setString(1, productCode);
            proc.execute();
            rs = proc.getResultSet();
            while(rs.next()){
            	productDto = RsToDtoUtil.tranRsToDto(rs, ProductDetailDto.class);
                break;
            }
            if(proc.getMoreResults()){
                rs = proc.getResultSet();
                ProductMatDetailDto materialDto = null;
                while(rs.next()){
                	materialDto = RsToDtoUtil.tranRsToDto(rs, ProductMatDetailDto.class);
                    list.add(materialDto);
                }
            }
            dto.setProductDetail(productDto);
            dto.setMaterialDetailList(list);
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
        return dto;
    }
    
    
}
