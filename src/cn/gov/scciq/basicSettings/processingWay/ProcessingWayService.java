package cn.gov.scciq.basicSettings.processingWay;

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


import cn.gov.scciq.basicSettings.productClass.ProductClassBean;
import cn.gov.scciq.basicSettings.productClass.ProductClassServiceImpl;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.dto.ProcessingMethodDto;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class ProcessingWayService  {
	 private static Log log=LogFactory.getLog(ProductClassServiceImpl.class);
	 String json = null;
	 String rowIndex ;
	 String pageSize;
	 private String[] setscontent;
	 
	
	 
	 public Map<Integer, Object> getProductMethod(ProcessingWayBean bean,int showflg,int startindex,int pageSize) throws Exception {
			// TODO Auto-generated method stub
			 Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
			 int recordsTotal = 0;
			 List<ProcessingWayBean> list = new ArrayList<ProcessingWayBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				System.out.println("hehe "+bean.getMethodName());
				String wCall = "{call Pro_GetProcessingMethod(?,?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getMethodName());
				proc.setInt(2, showflg);
				proc.setInt(3, startindex); //记录索引号
				proc.setInt(4, pageSize); //每页最大记录数
				proc.setString(5, "ProcessingMethodID");
				proc.setString(6, "DESC");
				proc.registerOutParameter(7, java.sql.Types.INTEGER);
				proc.execute();
			    rs = proc.getResultSet();
			    ProcessingWayBean processingWayBean=null;
			    while(rs.next()){
			    	processingWayBean=RsToDtoUtil.tranRsToDto(rs, ProcessingWayBean.class);
			    	String ifset=processingWayBean.getIfSet();
			    	if(ifset.equals("1")){
			    		processingWayBean.setIfSet("是");
			    	}else{
			    		processingWayBean.setIfSet("否");
			    	}
			    	list.add(processingWayBean);
			    }
			    recordsTotal=proc.getInt(7);
			    rsMap.put(1, recordsTotal);
	            rsMap.put(2, list);
				log.info("Pro_GetProcessingMethod-rsmap-"+rsMap);
			}catch (SQLException e) {
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
	        
	        return rsMap;
		}
	public List<ProcessingWaySubBean> getProductMethodSub(ProcessingWaySubBean bean)throws Exception{
		 List<ProcessingWaySubBean> list  = new ArrayList<ProcessingWaySubBean>();
	        Connection conn = null;
	        CallableStatement proc = null;
	        ResultSet rs = null;
	       
	        try {
	        	String call = "{call Pro_GetProcessingMethodSub(?)}";
	            conn = DBPool.ds.getConnection();
	            proc = conn.prepareCall(call);
	            proc.setString(1, bean.getMethodCode());
	            proc.execute();
	            rs = proc.getResultSet();
	            ProcessingWaySubBean processingWaySubBean = null;
	            while(rs.next()){
	            	processingWaySubBean = RsToDtoUtil.tranRsToDto(rs, ProcessingWaySubBean.class);
	                list.add(processingWaySubBean);
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
		
		
	
	public String SaveProcessingMethod(ProcessingWayBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		String retStr = ConstantStr.SAVE_ERROR_MSG;
		try {
			
		    String wCall = "{call Pro_SaveProcessingMethod(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getProcessingMethodID());
			proc.setString(2, bean.getMethodCode());
			proc.setString(3, bean.getMethodName());
			proc.setString(4, bean.getIfSet());
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.registerOutParameter(6, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(5);
			log.info("Pro_SaveProcessingMethod--"+retStr);
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

	
	public String SaveProcessingMethodSub(ProcessingWayBean bean,String[] arr )
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		String retStr = ConstantStr.SAVE_ERROR_MSG;
		try {
			
			String wCall = null;
			wCall = "{call Pro_SaveProcessingMethod(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getProcessingMethodID());
			proc.setString(2, bean.getMethodCode());
			proc.setString(3, bean.getMethodName());
			proc.setString(4, bean.getIfSet());
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.registerOutParameter(6, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(5);
			proc.close();
			int i=0;
			System.out.println("sets is");
			System.out.println("setscuntent is ;"+arr.toString()+";"+arr.length);
			while(i<arr.length)
			{
			 wCall = "{call Pro_SaveProcessingMethodSub(?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, "0");
			proc.setString(2, bean.getMethodCode());
			proc.setString(3, arr[i]);
			proc.registerOutParameter(4, java.sql.Types.VARCHAR);
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.execute();
			proc.close();
			i++;
		//	rs = proc.getResultSet();
			//returnStr=proc.getString(4);
			//returnCode=proc.getString(5);
			}
			log.info("Pro_SaveProcessingMethodSub--"+retStr);
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

	
	public String DelProcessingMethod(ProcessingWayBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		String retStr = ConstantStr.SAVE_ERROR_MSG;
		try {
			
			String returnStr =null;
			String wCall = "{call Pro_DelProcessingMethod(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getMethodCode());
			proc.execute();
		    retStr="true";
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
        return retStr;
	}

	public String[] getSetscontent() {
		return setscontent;
	}

	public void setSetscontent(String[] setscontent) {
		this.setscontent = setscontent;
	}

}
