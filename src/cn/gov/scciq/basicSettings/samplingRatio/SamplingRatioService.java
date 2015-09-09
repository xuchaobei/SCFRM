package cn.gov.scciq.basicSettings.samplingRatio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.dbpool.DBPool;

import cn.gov.scciq.util.ConstantStr;

import cn.gov.scciq.util.RsToDtoUtil;

import com.opensymphony.xwork2.Action;

public class SamplingRatioService {
	
	
	String retStr = ConstantStr.SAVE_ERROR_MSG;
	private static Log log=LogFactory.getLog(SamplingRatioService.class);

	
				public Map<String, Object> GetSamplingRatioDefine() throws Exception{
					
					Connection conn = DBPool.ds.getConnection();
					CallableStatement proc = null;
					Map<String, Object> map = new HashMap<String, Object>();
					try {
						String wCall = "{call Pro_GetSamplingRatioDefine()}";
						proc = conn.prepareCall(wCall);
						proc.execute();
						ResultSet rs1 = null;
					    rs1 = proc.getResultSet();//first set
					    List<ProductSamplingRatioBean> list = new ArrayList<ProductSamplingRatioBean>();
					    ProductSamplingRatioBean abean=null;
					    while(rs1.next()){
					    	abean=RsToDtoUtil.tranRsToDto(rs1, ProductSamplingRatioBean.class);
					    	list.add(abean);
					    }
					    if(proc.getMoreResults()){ // second set
									    	ResultSet rs4=	proc.getResultSet();
									    	 List<ItemSamplingRatioBean> list4 = new ArrayList<ItemSamplingRatioBean>();
									    	 ItemSamplingRatioBean epmBean=null;
									    	 while(rs4.next()){
									    		epmBean=RsToDtoUtil.tranRsToDto(rs4, ItemSamplingRatioBean.class);
										    	list4.add(epmBean);
									    	    }
									    	 try {
									    		 if(rs4 != null){  
							                          rs4.close();  
							                      }  
							                      if(rs1!=null){  
							                          rs1.close();  
							                      } 
												
											} catch (SQLException e) {
												// TODO: handle exception
												  log.error("", e);
											}catch (Exception e) {
										          log.error("", e);
										      }
									    	    map.put("one", list);
											    map.put("two", list4);	 
									      }
							      
					    	    
					      
					  log.info("Pro_GetSamplingRatioDefine "+map);
					}catch (SQLException e) {
				      // TODO Auto-generated catch block
				      log.error("", e);
				  } catch (Exception e) {
				      log.error("", e);
				  } finally{
				      try {
				    	  
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
				  
				  return map;
}
	public String SaveProductSamplingRatioDefine(SamplingRatioInputBean bean) throws Exception{
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_SaveProductSamplingRatioDefine(?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getSamplingLevel());
			proc.setString(2, bean.getLowerSamplingRatio());
			proc.setString(3, bean.getUpperSamplingRatio());
			proc.registerOutParameter(4, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(4);
			log.info("Pro_SaveProduct--"+retStr);
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
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
        return retStr;
		
	}
	public String SaveItemSamplingRatioDefine(SamplingRatioInputBean bean) throws Exception{
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_SaveItemSamplingRatioDefine(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getSamplingLevel());
			proc.setString(2, bean.getRiskEvlLevel());
			proc.setString(3, bean.getLowerSamplingRatio());
			proc.setString(4, bean.getUpperSamplingRatio());
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(5);
			log.info("SaveItemSamplingRatioDefine--"+retStr);
		} catch (SQLException e) {
            // TODO Auto-generated catch block
            log.error("", e);
        } catch (Exception e) {
            log.error("", e);
        } finally{
            try {
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
        return retStr;
		
	}

}
