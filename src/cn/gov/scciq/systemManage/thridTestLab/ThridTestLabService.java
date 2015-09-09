package cn.gov.scciq.systemManage.thridTestLab;

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
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.RsToDtoUtil;

public class ThridTestLabService {
	String retStr = ConstantStr.SAVE_ERROR_MSG;
	private static Log log=LogFactory.getLog(ThridTestLabService.class);
	
	public Map<Integer, Object> GetOtherTestLab(ThridTestLabInputBean bean,int start ,int length) throws Exception{
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<OtherTestLabBean> list = new ArrayList<OtherTestLabBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetOtherTestLab(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getTestLabName());
			proc.setInt(2, start); //记录索引号
			proc.setInt(3, length); //每页最大记录数
			proc.setString(4, "OtherTestLabID");
			proc.setString(5, "DESC");
			proc.registerOutParameter(6, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    OtherTestLabBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, OtherTestLabBean.class);
		    	list.add(abean);
		    }
		    recordsTotal=proc.getInt(6);
		    rsMap.put(1, recordsTotal);
           rsMap.put(2, list);
			log.info("Pro_GetOtherTestLab-this-rsmap-"+rsMap);
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
	public String SaveOtherTestLab(ThridTestLabInputBean bean) throws Exception{
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		
		try {
			String wCall = "{call Pro_SaveOtherTestLab(?,?,?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getOtherTestLabID());
			proc.setString(2, bean.getTestLabCode());
			proc.setString(3, bean.getTestLabName());
			proc.setString(4, bean.getAddress());
			proc.setString(5, bean.getTel());
			proc.setString(6, ContextUtil.getOrgCode());
			proc.setString(7, ContextUtil.getDeptCode());
			proc.setString(8, ContextUtil.getOperatorCode());
			proc.registerOutParameter(9, java.sql.Types.VARCHAR);
			proc.registerOutParameter(10, java.sql.Types.INTEGER);
			proc.execute();
			retStr=proc.getString(9);
			log.info("Pro_SaveOtherTestLab-this-rsmap-"+retStr);
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
	public String SaveOtherTestLabItem(ThridTestLabInputBean bean) throws Exception{
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		
		try {
			String wCall = "{call Pro_SaveOtherTestLabItem(?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getOtherTestLabItemID());
			proc.setString(2, bean.getOtherTestLabID());
			proc.setString(3, bean.getItemCode());
			proc.setString(4, bean.getProductClassCode());
			proc.setString(5, bean.getProductSubclassCode());
			proc.registerOutParameter(6, java.sql.Types.VARCHAR);
			proc.registerOutParameter(7, java.sql.Types.INTEGER);
			proc.execute();
			retStr=proc.getString(6);
			
			log.info("Pro_SaveOtherTestLabItem-this-rsmap-"+retStr);
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
	public String DelOtherTestLab(ThridTestLabInputBean bean) throws Exception{
		
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
	
		try {
			String wCall = "{call Pro_DelOtherTestLab(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getOtherTestLabID());
			proc.execute();
		    retStr="true";
			log.info("Pro_GetEntProductInput-this-rsmap-"+retStr);
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
	public String DelOtherTestLabItem(ThridTestLabInputBean bean) throws Exception{
		
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
	
		try {
			String wCall = "{call Pro_DelOtherTestLabItem(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getOtherTestLabItemID());
			proc.execute();
		    retStr="true";
			log.info("Pro_DelOtherTestLabItem-this-rsmap-"+retStr);
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
	public  Map<Integer, Object> GetOtherTestLabItem(ThridTestLabInputBean bean,int start ,int length) throws Exception{
		 Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<OtherTestLabItemBean> list = new ArrayList<OtherTestLabItemBean>();
		 Connection conn = DBPool.ds.getConnection();
		 CallableStatement proc = null;
		 ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetOtherTestLabItem(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getOtherTestLabID());
			proc.setInt(2, start); //记录索引号
			proc.setInt(3, length); //每页最大记录数
			proc.setString(4, "ItemName");
			proc.setString(5, "DESC");
			proc.registerOutParameter(6, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    OtherTestLabItemBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, OtherTestLabItemBean.class);
		    	list.add(abean);
		    }
		    recordsTotal=proc.getInt(6);
		    rsMap.put(1, recordsTotal);
           rsMap.put(2, list);
			log.info("Pro_GetOtherTestLabItem-this-rsmap-"+rsMap);
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

}
