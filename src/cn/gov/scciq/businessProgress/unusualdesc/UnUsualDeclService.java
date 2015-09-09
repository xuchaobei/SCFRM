package cn.gov.scciq.businessProgress.unusualdesc;

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

public class UnUsualDeclService {
	String retStr = ConstantStr.SAVE_ERROR_MSG;
	private static Log log=LogFactory.getLog(UnUsualDeclService.class);
	
	
	public Map<Integer, Object>  GetAbnormalDecl(UnUsualDeclInputBean bean,int start,int length) throws Exception{
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<AbnormalDeclBean> list = new ArrayList<AbnormalDeclBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetAbnormalDecl(?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getDeclNo());
			proc.setString(2, ContextUtil.getOrgCode());
			proc.setString(3, ContextUtil.getDeptCode());
			proc.setInt(4, start); //记录索引号
			proc.setInt(5, length); //每页最大记录数
			proc.setString(6, "DeclInfoAbnormalID");
			proc.setString(7, "DESC");
			proc.registerOutParameter(8, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		   
		    AbnormalDeclBean abean=null;
		   
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, AbnormalDeclBean.class);
		    	list.add(abean);
		    	
		    }
		    recordsTotal=proc.getInt(8);
		    rsMap.put(1, recordsTotal);
           rsMap.put(2, list);
			log.info("Pro_GetAbnormalDecl-rsmap-"+rsMap);
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
	
		public String  ReturnDecl(UnUsualDeclInputBean bean) throws Exception{
			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_ReturnDecl(?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.setString(2, ContextUtil.getOrgCode());
				proc.setString(3, ContextUtil.getDeptCode());
				proc.setString(4, ContextUtil.getOperatorCode());
				proc.execute();
				retStr="true";
				log.info("Pro_ReturnDecl-rsmap-"+retStr);
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
	       
	       return retStr;
			
			
		}
		
		
		
		
		public String  CheckAbnormalDecl(UnUsualDeclInputBean bean) throws Exception{
			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_CheckAbnormalDecl(?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.setString(2, ContextUtil.getOrgCode());
				proc.setString(3, ContextUtil.getDeptCode());
				proc.setString(4, ContextUtil.getOperatorCode());
				proc.setString(5, ContextUtil.getRoleCode());
				proc.registerOutParameter(6, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(6);
				if("".equals(retStr)){
					retStr="true";
				}
				log.info("CheckAbnormalDecl-rsmap-"+retStr);
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
	       
	       return retStr;
			
			
		}	
		//集中审单系统调用
		public String  ReReadDeclInfo(UnUsualDeclInputBean bean) throws Exception{

			
			Connection conn = DBPool.ds_cems.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_ReReadDeclInfo(?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.registerOutParameter(2, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(2);
				log.info("Pro_ReReadDeclInfo-rsmap-"+retStr);
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
	       
	       return retStr;
			}
		public List<DeclInfoBean>  GetDeclInfoByDeclNo(UnUsualDeclInputBean bean) throws Exception{
			List<DeclInfoBean> list = new ArrayList<DeclInfoBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetDeclInfoByDeclNo(?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.execute();
			    rs = proc.getResultSet();
			    DeclInfoBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, DeclInfoBean.class);
			    	list.add(abean);
			    }
			    log.info("Pro_GetDeclInfoByDeclNo-rsmap-"+list);
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
	      
	      return list;
			
			
		}
		public String  SaveDeclInfoInput(UnUsualDeclInputBean bean) throws Exception{

			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_SaveDeclInfoInput(?,?,?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.setString(2, bean.getDeclDate());
				proc.setString(3, bean.getEntCode());
				proc.setString(4, bean.getCountryCode());
				proc.setString(5, ContextUtil.getOrgCode());
				proc.setString(6, ContextUtil.getDeptCode());
				proc.setString(7, ContextUtil.getOperatorCode());
				proc.registerOutParameter(8, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(8);
				log.info("Pro_SaveDeclInfoInput-rsmap-"+retStr);
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
	       
	       return retStr;
			
			
		
			}
		public String  RecalculateDeclProductSampling(UnUsualDeclInputBean bean) throws Exception{

			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_RecalculateDeclProductSampling(?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.registerOutParameter(2, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(2);
				log.info("Pro_RecalculateDeclProductSampling-rsmap-"+retStr);
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
	       
	       return retStr;
			
			
		
			}
public String  CheckDeclProductPerfect(UnUsualDeclInputBean bean) throws Exception{

			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_CheckDeclProductPerfect(?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.registerOutParameter(2, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(2);
				log.info("CheckDeclProductPerfect-rsmap-"+retStr);
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
	       
	       return retStr;
			
			
		
			}

}
