package cn.gov.scciq.businessProgress.passjudge;

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

public class PassJundgeService {
	String retStr = ConstantStr.SAVE_ERROR_MSG;
	private static Log log=LogFactory.getLog(PassJundgeService.class);
	
	
		public List<LabApplyByDeclNoBean>    GetLabApplyByDeclNo(PassJudgeInputBean bean) throws Exception{
					List<LabApplyByDeclNoBean> list = new ArrayList<LabApplyByDeclNoBean>();
					Connection conn = DBPool.ds.getConnection();
					CallableStatement proc = null;
					ResultSet rs = null;
					try {
						String wCall = "{call Pro_GetLabApplyByDeclNo(?)}";
						proc = conn.prepareCall(wCall);
						proc.setString(1, bean.getDeclNo());
						proc.execute();
					    rs = proc.getResultSet();
					    LabApplyByDeclNoBean abean=null;
					    while(rs.next()){
					    	abean=RsToDtoUtil.tranRsToDto(rs, LabApplyByDeclNoBean.class);
					    	list.add(abean);
					    }
					    log.info("Pro_GetLabApplyByDeclNo-rsmap-"+list);
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
		public List<InspOperatorByOrgDeptBean>    GetInspOperatorByOrgDept(PassJudgeInputBean bean) throws Exception{
			List<InspOperatorByOrgDeptBean> list = new ArrayList<InspOperatorByOrgDeptBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetInspOperatorByOrgDept(?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getInspOrgCode());
				proc.setString(2, bean.getInspDeptCode());
				proc.execute();
			    rs = proc.getResultSet();
			    InspOperatorByOrgDeptBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, InspOperatorByOrgDeptBean.class);
			    	list.add(abean);
			    }
			    log.info("Pro_GetInspOperatorByOrgDept-rsmap-"+list);
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
	public List<LabSampleItemByProductBean>    GetLabSampleItemByProduct(PassJudgeInputBean bean) throws Exception{
					List<LabSampleItemByProductBean> list = new ArrayList<LabSampleItemByProductBean>();
					Connection conn = DBPool.ds.getConnection();
					CallableStatement proc = null;
					ResultSet rs = null;
					try {
						String wCall = "{call Pro_GetLabSampleItemByProduct(?)}";
						proc = conn.prepareCall(wCall);
						proc.setString(1, bean.getDeclProductDetailID());
						proc.execute();
					    rs = proc.getResultSet();
					    LabSampleItemByProductBean abean=null;
					    String id=null;
					    String ifqualify=null;
					    String ifqualifynot=null;
					    String ifcancel =null;
					    String str=null;
					    String sendLabFlg=null;
					    String labdata=null;
					    String labdataunit=null;
					    String labInterface=null;
					    while(rs.next()){
					    	id=rs.getString("LabSampleItemID");
					    	ifqualify=rs.getString("ifQualified_Yes");
					    	ifqualifynot=rs.getString("ifQualified_No");
					    	ifcancel=rs.getString("ifCancel");
					    	sendLabFlg=rs.getString("SendLabFlg");
					    	labInterface=rs.getString("labInterface");
					    	labdata=rs.getString("LabData");
					    	labdataunit=rs.getString("LabDataUnit");
					    	if(labdata==null || labdata==""){labdata="";}
					    	if(labdataunit==null || labdataunit==""){labdataunit="";}
					    	
					    	str=id+"@"+ifqualify+"@"+ifcancel+"@"+ifqualifynot+"@"+sendLabFlg+"@"+labdata+"@"+labdataunit+"@"+labInterface;
					    	abean=RsToDtoUtil.tranRsToDto(rs, LabSampleItemByProductBean.class);
					    	abean.setTotelstr(str);
					    	list.add(abean);
					    }     
					    log.info("Pro_GetLabSampleItemByProduct-rsmap-"+list);
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
	public String   SaveDeclProductQualify (PassJudgeInputBean bean) throws Exception{
		
				Connection conn = DBPool.ds.getConnection();
				CallableStatement proc = null;
				ResultSet rs = null;
				try {
					String wCall = "{call Pro_SaveDeclProductQualify(?,?,?)}";
					proc = conn.prepareCall(wCall);
					proc.setString(1, bean.getDeclProductDetailID());
					proc.setString(2, bean.getIfFieldInspQualified());
					proc.setString(3, bean.getLabellingQualify());
					proc.execute();
					retStr="true";
					log.info("Pro_SaveDeclProductQualify-rsmap-"+retStr);
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
	public String    SaveLabSampleItemQualify(PassJudgeInputBean bean) throws Exception{
		
				Connection conn = DBPool.ds.getConnection();
				CallableStatement proc = null;
				ResultSet rs = null;
				try {
					String wCall = "{call Pro_SaveLabSampleItemQualify(?,?,?,?,?,?,?)}";
					proc = conn.prepareCall(wCall);
					proc.setString(1, bean.getLabSampleID());
					proc.setString(2, bean.getItemCode());
					proc.setString(3, bean.getIfQualified());
					proc.setString(4, bean.getIfCancel());
					proc.setString(5, bean.getOperateFlg());
					proc.setString(6, bean.getLabData());
					proc.setString(7, bean.getLabDataUnit());
					proc.execute();
					retStr="true";
					log.info("Pro_SaveLabSampleItemQualify-rsmap-"+retStr+bean.getLabDataUnit());
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
	public String    AllSaveLabSampleItemQualify(String data) throws Exception{
		
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_SaveLabSampleItemQualify(?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			String[] eData=data.split(",");
			proc.setString(1, eData[0]);
			proc.setString(2, eData[1]);
			proc.setString(3, eData[2]);
			proc.setString(4, eData[3]);
			proc.setString(5, eData[4]);
			proc.setString(6, eData[5]);
			proc.setString(7, eData[6]);
			proc.execute();
			retStr="true";
			//log.info("Pro_SaveLabSampleItemQualify-rsmap-"+retStr+bean.getLabDataUnit());
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
	public Map<String, Object>    QualifyJudge(PassJudgeInputBean bean) throws Exception{
				Map<String, Object> rsMap = new HashMap<String, Object>();
				Connection conn = DBPool.ds.getConnection();
				CallableStatement proc = null;
				ResultSet rs = null;
				String str=null;
				try {
					String wCall = "{call Pro_QualifyJudge(?,?,?,?,?,?)}";
					proc = conn.prepareCall(wCall);
					proc.setString(1,ContextUtil.getOrgCode());
					proc.setString(2, ContextUtil.getDeptCode());
					proc.setString(3, ContextUtil.getOperatorCode());
					proc.setString(4, bean.getDeclNo());
					proc.registerOutParameter(5, java.sql.Types.VARCHAR);
					proc.registerOutParameter(6, java.sql.Types.VARCHAR);
					proc.execute();
					retStr=proc.getString(5);
					str=proc.getString(6);
					if("".equals(retStr)){
						retStr="true";
					}
					if("".equals(str) || str==null){
						str="3";
					}
					rsMap.put("one",retStr);
					rsMap.put("two",str);
					
					log.info("Pro_QualifyJudge-rsmap-"+retStr);
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

	public String CancelCurrentProcess(PassJudgeInputBean bean) throws Exception{	
				Connection conn = DBPool.ds.getConnection();
				CallableStatement proc = null;
				ResultSet rs = null;
				try {
					String wCall = "{call Pro_CancelCurrentProcess(?,?,?,?,?,?)}";
					proc = conn.prepareCall(wCall);
					proc.setString(1, bean.getDeclNo());
					proc.setString(2, bean.getProcessName());
					proc.setString(3,ContextUtil.getOrgCode());
					proc.setString(4, ContextUtil.getDeptCode());
					proc.setString(5, ContextUtil.getOperatorCode());
					proc.registerOutParameter(6, java.sql.Types.VARCHAR);
					proc.execute();
					retStr=proc.getString(6);
					
					if("".equals(retStr)){
						retStr="true";
					}
					
					
					
					log.info("Pro_CancelCurrentProcess-rsmap-"+retStr);
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

   public String CheckIfPrintCert(PassJudgeInputBean bean) throws Exception{
	   	Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_CheckPrintCert(?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getDeclNo());
			proc.registerOutParameter(2, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(2);
			
			log.info("Pro_CheckPrintCert-rsmap-"+retStr);
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
   
   public String CheckQualifySaveBefore(PassJudgeInputBean bean) throws Exception{
	   	Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_CheckIfProcessOperate(?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getProcessName());
			proc.setString(2, bean.getDeclNo());
			proc.registerOutParameter(3, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(3);
			
			log.info("CheckIfProcessOperate-rsmap-"+retStr);
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
   
   public String CheckQualifyJudgeBefore(PassJudgeInputBean bean) throws Exception{
	   	Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_CheckQualifyJudgeBefore(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getDeclNo());
			proc.setString(2, ContextUtil.getOrgCode());
			proc.setString(3, ContextUtil.getDeptCode());
			proc.registerOutParameter(4, java.sql.Types.VARCHAR);
			proc.registerOutParameter(5, java.sql.Types.VARCHAR);
			proc.registerOutParameter(6, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(4)+"@"+proc.getString(5)+"@"+proc.getString(6);
			
			log.info("CheckQualifyJudgeBefore-rsmap-"+retStr+bean.getDeclNo()+ContextUtil.getOrgCode()+ContextUtil.getDeptCode());
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
   public String TransDeclForMultiDept(PassJudgeInputBean bean) throws Exception{
	   	Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_TransDeclForMultiDept(?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getDeclNo());
			proc.setString(2, bean.getInspOrgCode());
			proc.setString(3, bean.getInspDeptCode());
			proc.setString(4, bean.getOperatorCode());
			proc.setString(5, ContextUtil.getOrgCode());
			proc.setString(6, ContextUtil.getDeptCode());
			proc.setString(7, ContextUtil.getOperatorCode());
			proc.registerOutParameter(8, java.sql.Types.VARCHAR);
			
			proc.execute();
			retStr=proc.getString(8);
			
			log.info("Pro_TransDeclForMultiDept-rsmap-"+retStr);
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
  
   public String CheckifUnqualify(PassJudgeInputBean bean) throws Exception{
	   	Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_CheckifUnqualify(?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getDeclNo());
			proc.registerOutParameter(2, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(2);
			
			log.info("CheckifUnqualify-rsmap-"+retStr);
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
   public String CheckInspDeptForDeclProduct(PassJudgeInputBean bean) throws Exception{
	   	Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_CheckInspDeptForDeclProduct(?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getDeclProductDetailID());
			proc.setString(2, ContextUtil.getOrgCode());
			proc.setString(3, ContextUtil.getDeptCode());
			proc.registerOutParameter(4, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(4);
			
			log.info("Pro_CheckInspDeptForDeclProduct-rsmap-"+retStr);
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
