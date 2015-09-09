package cn.gov.scciq.businessProgress.checkorReceive;

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

public class ProcessReceiverService {
	String retStr = ConstantStr.SAVE_ERROR_MSG;
	private static Log log=LogFactory.getLog(ProcessReceiverService.class);
			
		public  Map<Integer, Object>     GetDeclInfoForProcess (ProcessOperateLogBean bean,int start,int length) throws Exception{
			Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
			 int recordsTotal = 0;
			 List<DeclInfoForProcessBean> list = new ArrayList<DeclInfoForProcessBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			CallableStatement proc2 = null;
			 String declno=null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetDeclInfoForProcess(?,?,?,?,?,?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.setString(2, ContextUtil.getOrgCode());
				proc.setString(3, ContextUtil.getDeptCode());
				proc.setString(4, ContextUtil.getOperatorCode());
				proc.setString(5, bean.getProcessName());
				proc.setString(6, bean.getFinishFlg());
				proc.setInt(7, start); //记录索引号
				proc.setInt(8, length); //每页最大记录数
				proc.setString(9, "DeclDate");
				proc.setString(10, "DESC");
				proc.registerOutParameter(11, java.sql.Types.INTEGER);
				proc.execute();
			    rs = proc.getResultSet();
			   
			    DeclInfoForProcessBean abean=null;
			   
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, DeclInfoForProcessBean.class);
			    	declno=rs.getString("DeclNo");
			    	String wCall2 = "{call Pro_GetProcessStatusForDecl(?,?,?)}";
					proc2 = conn.prepareCall(wCall2);
					proc2.setString(1, declno);
					proc2.setString(2, bean.getProcessName());
					proc2.registerOutParameter(3, java.sql.Types.VARCHAR);
					proc2.execute();
					retStr=proc2.getString(3);
			    	abean.setProcessStatus(retStr);
			    	list.add(abean);
			    	proc2.close();
			    }
			    recordsTotal=proc.getInt(11);
			    rsMap.put(1, recordsTotal);
	            rsMap.put(2, list);
				log.info("Pro_GetDeclInfoForProcess-rsmap-"+rsMap);
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
		public  Map<Integer, Object>     GetDeclInfoForProcessHg (ProcessOperateLogBean bean,int start,int length) throws Exception{
			Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
			 int recordsTotal = 0;
			 List<DeclInfoForProcessBean> list = new ArrayList<DeclInfoForProcessBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			CallableStatement proc2 = null;
			 String declno=null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetDeclInfoForProcess(?,?,?,?,?,?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.setString(2, ContextUtil.getOrgCode());
				proc.setString(3, ContextUtil.getDeptCode());
				proc.setString(4, ContextUtil.getOperatorCode());
				proc.setString(5, bean.getProcessName());
				proc.setString(6, bean.getFinishFlg());
				proc.setInt(7, start); //记录索引号
				proc.setInt(8, length); //每页最大记录数
				proc.setString(9, "DeclDate");
				proc.setString(10, "DESC");
				proc.registerOutParameter(11, java.sql.Types.INTEGER);
				proc.execute();
			    rs = proc.getResultSet();
			   
			    DeclInfoForProcessBean abean=null;
			   
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, DeclInfoForProcessBean.class);
			    	declno=rs.getString("DeclNo");
			    	String wCall2 = "{call Pro_GetProcessStatusForDecl(?,?)}";
					proc2 = conn.prepareCall(wCall2);
					proc2.setString(1, declno);
					proc2.registerOutParameter(2, java.sql.Types.VARCHAR);
					proc2.execute();
					retStr=proc2.getString(2);
			    	abean.setProcessStatus(retStr);
			    	list.add(abean);
			    	proc2.close();
			    }
			    recordsTotal=proc.getInt(11);
			    rsMap.put(1, recordsTotal);
	            rsMap.put(2, list);
				log.info("Pro_GetDeclInfoForProcess-rsmap-"+rsMap);
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
			public String    GetProcessStatusForDecl(ProcessOperateLogBean bean) throws Exception{
				Connection conn = DBPool.ds.getConnection();
			    CallableStatement proc = null;
			   try {
				String wCall = "{call Pro_GetProcessStatusForDecl(?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.setString(2, bean.getProcessName());
				proc.registerOutParameter(3, java.sql.Types.VARCHAR);
				proc.execute();
				
				retStr=proc.getString(3);
				log.info("Pro_GetProcessStatusForDecl--"+retStr);
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
		public List<DeclProductBean>  GetDeclProduct(ProcessOperateLogBean bean,int start,int length) throws Exception{
			List<DeclProductBean> list = new ArrayList<DeclProductBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetDeclProduct(?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.execute();
			    rs = proc.getResultSet();
			    DeclProductBean abean=null;
			    String id=null;
			    String ifinspqualify=null;
			   // String ifqualify=null;
			    String ifinspqualifynot=null;
			   // String ifqualifynot=null;
			    String str=null;
			    String samplingRatio=null;
			    String lowsamp=null;
			    String upsamp=null;
			    String goodsNo=null;
			    String goodsCName=null;
			    String gnn=null;
			    String labeling=null;
			    while(rs.next()){
			    	id=rs.getString("DeclProductDetailID");
			    	ifinspqualify=rs.getString("ifFieldInspQualified_Yes");
			    	ifinspqualifynot=rs.getString("ifFieldInspQualified_No");
			    	labeling=rs.getString("LabellingQualify");
			    	if(labeling==null || "".equals(labeling)){
			    		labeling="";
			    	}
			    	//ifqualifynot=rs.getString("ifQualified_No");
			    	lowsamp=rs.getString("lowerSamplingRatio");
			    	if("".equals(lowsamp)|| lowsamp==null){lowsamp="";samplingRatio="";}
			    	
			    	upsamp=rs.getString("upperSamplingRatio");
			    	if("".equals(upsamp) || upsamp==null){upsamp="";samplingRatio="";}
			    	else{
			    		samplingRatio=lowsamp+"%-"+upsamp+"%";
			    	}
			    	goodsNo=rs.getString("GoodsNo");
			    	goodsCName=rs.getString("GoodsCName");
			    	System.out.println("sggg"+goodsNo+goodsCName);
			    	gnn=goodsNo+" "+goodsCName;
			    	//abean.setGoodnn(gnn);
			    	str=id+"/"+ifinspqualify+"/"+labeling+"/"+ifinspqualifynot;
			    	abean=RsToDtoUtil.tranRsToDto(rs, DeclProductBean.class);
			    	abean.setTotelstr(str);
			    	abean.setSamplingRatio(samplingRatio);
			    	abean.setGoodnn(gnn);  
			    	list.add(abean);
			    }
			    log.info("Pro_GetDeclProduct-rsmap-"+list);
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
		
		public List<ItemBean>  GetItem(ItemBean bean) throws Exception{
			List<ItemBean> list = new ArrayList<ItemBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetItem(?,?,?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getItemName());
				proc.setString(2, bean.getRiskClassCode());
				proc.setInt(3,0);
				proc.setInt(4, 0); //记录索引号
				proc.setInt(5, 0); //每页最大记录数
				proc.setString(6, "ItemID");
				proc.setString(7, "DESC");
				proc.registerOutParameter(8, java.sql.Types.INTEGER);
				proc.execute();
			    rs = proc.getResultSet();
			    ItemBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, ItemBean.class);
			    	list.add(abean);
			    }
			    log.info("Pro_GetItem-rsmap-"+list);
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
		public List<DeclGoodsByDeclNoBean>  GetDeclGoodsByDeclNo(DeclProductBean bean) throws Exception{
			List<DeclGoodsByDeclNoBean> list = new ArrayList<DeclGoodsByDeclNoBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetDeclGoodsByDeclNo(?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.execute();
			    rs = proc.getResultSet();
			    DeclGoodsByDeclNoBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, DeclGoodsByDeclNoBean.class);
			    	list.add(abean);
			    }
			    log.info("Pro_GetDeclGoodsByDeclNo-rsmap-"+list);
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
		
		public List<BaseListBean>  GetBaseList(BaseListBean bean) throws Exception{
			List<BaseListBean> list = new ArrayList<BaseListBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetBaseList(?,?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getBaseName());
				proc.setString(2, bean.getBaseCode());
				proc.setInt(3, 0); //记录索引号
				proc.setInt(4, 0); //每页最大记录数
				proc.setString(5, "BaseID");
				proc.setString(6, "DESC");
				proc.registerOutParameter(7, java.sql.Types.INTEGER);
				proc.execute();
			    rs = proc.getResultSet();
			    BaseListBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, BaseListBean.class);
			    	list.add(abean);
			    }
			    log.info("Pro_GetBaseList-rsmap-"+list);
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
		
		
		public Map<Integer, Object>     GetDeclProductItem(DeclProductItemInputBean bean,int start,int length ) throws Exception{
			Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
			 int recordsTotal = 0;
			 List<DeclProductItemBean> list = new ArrayList<DeclProductItemBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetDeclProductItem(?,?,?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclProductDetailID());
				proc.setString(2, bean.getShowSamplingItemFlg());
				proc.setString(3, bean.getShowNotLabFlg());
				proc.setInt(4, start); //记录索引号
				proc.setInt(5, length); //每页最大记录数
				proc.setString(6, "ifSampling");
				proc.setString(7, "DESC");
				proc.registerOutParameter(8, java.sql.Types.INTEGER);
				proc.execute();
			    rs = proc.getResultSet();
			    DeclProductItemBean abean=null;
			    String lowsamp=null;
			    String upsamp=null;
			    String samplingRatio=null;
			    while(rs.next()){
			    	
			    	lowsamp=rs.getString("lowerSamplingRatio");
			    	if("".equals(lowsamp)|| lowsamp==null){lowsamp="";samplingRatio="";}
			    	
			    	upsamp=rs.getString("upperSamplingRatio");
			    	if("".equals(upsamp) || upsamp==null){upsamp="";samplingRatio="";}
			    	else{
			    		samplingRatio=lowsamp+"%-"+upsamp+"%";
			    	}
			    	
			    	abean=RsToDtoUtil.tranRsToDto(rs, DeclProductItemBean.class);
			    	abean.setSamplingRatio(samplingRatio);
			    	list.add(abean);
			    }
			    recordsTotal=proc.getInt(8);
			    rsMap.put(1, recordsTotal);
	            rsMap.put(2, list);
				log.info("Pro_GetDeclProductItem-rsmap-"+rsMap);
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
		public String     TransDecl(TransDeclBean bean) throws Exception{
			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_TransDecl(?,?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.setString(2, bean.getInspOrgCode());
				proc.setString(3, bean.getInspDeptCode());
				proc.setString(4, ContextUtil.getOrgCode());
				proc.setString(5, ContextUtil.getDeptCode());   
				proc.setString(6, ContextUtil.getOperatorCode());
				proc.registerOutParameter(7, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(7);
				log.info("Pro_TransDecl-rsmap-"+retStr);
				log.info(bean.getDeclNo()+" "+bean.getInspOrgCode()+" "+bean.getInspDeptCode()+" "+ContextUtil.getOrgCode()+" "+ContextUtil.getDeptCode()+" "+ ContextUtil.getOperatorCode());
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
		
		public String     WithdrawDecl(ProcessOperateLogBean bean) throws Exception{
			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_WithdrawDecl(?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.setString(2, ContextUtil.getOrgCode());
				proc.setString(3, ContextUtil.getDeptCode());
				proc.setString(4, ContextUtil.getOperatorCode());
				proc.registerOutParameter(5, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(5);
				log.info("Pro_WithdrawDecl-rsmap-"+retStr);
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
		public Map<String, Object>    AcceptDecl(ProcessOperateLogBean bean) throws Exception{
			Map<String, Object> rsMap = new HashMap<String, Object>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_AcceptDecl(?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.setString(2, ContextUtil.getOrgCode());
				proc.setString(3, ContextUtil.getDeptCode());
				proc.setString(4, ContextUtil.getOperatorCode());
				proc.registerOutParameter(5, java.sql.Types.VARCHAR);
				proc.registerOutParameter(6, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(5);
				if("".equals(retStr)){
					retStr="true";
				}
				rsMap.put("one",retStr);
				rsMap.put("two",proc.getString(6));
				
				log.info("Pro_AcceptDecl-rsmap-"+retStr);
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
		//在集中审单系统调用存储过程Pro_SaveReturnFlg集中审单系统的数据库是Oracle
		public String     SaveReturnFlg(ReturnFlgBean bean) throws Exception{
			
			Connection conn = DBPool.ds_cems.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_SaveReturnFlg(?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.setString(2, bean.getReturnFlg());
				proc.execute();
				retStr="true";
				log.info("Pro_SaveReturnFlg-rsmap-"+retStr);
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
		public String     CancelCurrentProcess(ProcessOperateLogBean bean) throws Exception{
			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_CancelCurrentProcess(?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.setString(2, bean.getProcessName());
				proc.setString(3, ContextUtil.getOrgCode());
				proc.setString(4, ContextUtil.getDeptCode());
				proc.setString(5, ContextUtil.getOperatorCode());
				proc.registerOutParameter(6, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(6);
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
		public String    DirectRelease(ProcessOperateLogBean bean) throws Exception{
			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_DirectRelease(?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.setString(2, ContextUtil.getOrgCode());
				proc.setString(3, ContextUtil.getDeptCode());
				proc.setString(4, ContextUtil.getOperatorCode());
				proc.registerOutParameter(5, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(5);
				log.info("Pro_DirectRelease-rsmap-"+retStr);
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
		public String     ChangeProductSampling(DeclProductBean bean) throws Exception{
			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_ChangeProductSampling(?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclProductDetailID());
				proc.registerOutParameter(2, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(2);
				log.info("Pro_ChangeProductSampling-rsmap-"+retStr);
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
		public Map<Integer, Object>    GetEntProductByQuery(EntProductByQueryInputBean bean,int start ,int length) throws Exception{

			 Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
			 int recordsTotal = 0;
			 List<EntProductByQueryBean> list = new ArrayList<EntProductByQueryBean>();
			 Connection conn = DBPool.ds.getConnection();
		   	 CallableStatement proc = null;
			 ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetEntProductByQuery(?,?,?,?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getEntCode());
				proc.setString(2, bean.getEntName());
				proc.setString(3, bean.getCountryCode());
				proc.setString(4,bean.getEntProductName());
				proc.setInt(5, start); //记录索引号
				proc.setInt(6, length); //每页最大记录数
				proc.setString(7, "ProductCode");
				proc.setString(8, "DESC");
				proc.registerOutParameter(9, java.sql.Types.INTEGER);
				proc.execute();
			    rs = proc.getResultSet();
			    EntProductByQueryBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, EntProductByQueryBean.class);
			    	list.add(abean);
			    }
			    recordsTotal=proc.getInt(9);
			    rsMap.put(1, recordsTotal);
	            rsMap.put(2, list);
				log.info("Pro_GetEntProductByQuery-rsmap-"+rsMap);
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
		public String    SaveDeclProductInput(DeclProductBean bean) throws Exception{
			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_SaveDeclProductInput(?,?,?,?,?,?,?,?,?)}";    
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclProductDetailID());
				proc.setString(2, bean.getDeclNo());
				proc.setString(3, bean.getProductCode());
				proc.setString(4, bean.getEntProductCode());
				proc.setString(5, bean.getBaseCode());
				proc.setString(6, bean.getGoodsNo());
				proc.setString(7, bean.getGoodsCName());
				proc.setString(8, bean.getIfSampling());
				proc.registerOutParameter(9, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(9);
				log.info("Pro_SaveDeclProductInput-rsmap-"+retStr);
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
		
		public String    SaveCreditRelease(CreditReleaseBean bean) throws Exception{
			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			
			try {
				//System.out.println("name "+bean.getTestLabName()+" code "+bean.getItemCode()+"　id "+bean.getDeclProductDetailID()+" data "+bean.getLabData()+" unit "+bean.getDataUnit());
				String wCall = "{call Pro_SaveCreditRelease(?,?,?,?,?,?,?,?,?)}";    
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getTestLabName());
				proc.setString(2, bean.getItemCode());
				proc.setString(3, bean.getDeclProductDetailID());
				proc.setString(4, bean.getLabData());
				proc.setString(5, bean.getDataUnit());
				proc.setString(6,ContextUtil.getOrgCode());
				proc.setString(7, ContextUtil.getDeptCode());
				proc.setString(8, ContextUtil.getOperatorCode());
				proc.registerOutParameter(9, java.sql.Types.VARCHAR);
				proc.execute();
			     retStr=proc.getString(9);
				log.info("SaveCreditRelease-rsmap-"+retStr);
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
public String    CancelReleaseMode(CreditReleaseBean bean) throws Exception{
			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			
			try {
				//System.out.println("name "+bean.getTestLabName()+" code "+bean.getItemCode()+"　id "+bean.getDeclProductDetailID()+" data "+bean.getLabData()+" unit "+bean.getDataUnit());
				String wCall = "{call Pro_CancelReleaseMode(?,?,?,?,?,?)}";    
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclProductDetailID());
				proc.setString(2, bean.getItemCode());
				proc.setString(3,ContextUtil.getOrgCode());
				proc.setString(4, ContextUtil.getDeptCode());
				proc.setString(5, ContextUtil.getOperatorCode());
				proc.registerOutParameter(6, java.sql.Types.VARCHAR);
				proc.execute();
			     retStr=proc.getString(6);
				log.info("Pro_CancelReleaseMode-rsmap-"+retStr);
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
		public String    DelDeclProduct(DeclProductBean bean) throws Exception{
			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_DelDeclProduct(?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclProductDetailID());
				proc.execute();
				retStr="true";
				log.info("Pro_DelDeclProduct-rsmap-"+retStr);
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
		public String    ChangeItemSampling(DeclProductItemBean bean) throws Exception{
			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_ChangeItemSampling(?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclProductDetailID());
				proc.setString(2, bean.getItemCode());
				proc.registerOutParameter(3, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(3);
				log.info("Pro_ChangeItemSampling-rsmap-"+retStr);
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
		public String    SaveDeclProductItemInput(DeclProductItemBean bean) throws Exception{
			
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_SaveDeclProductItemInput(?,?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclProductDetailID());
				proc.setString(2, bean.getItemCode());
				proc.setString(3, bean.getDetectionStd());
				proc.setString(4, bean.getLimitReq());
				proc.setString(5, bean.getDetectionLimit());
				proc.setString(6, bean.getLimitUnit());
				proc.registerOutParameter(7, java.sql.Types.VARCHAR);
				proc.execute();
				retStr=proc.getString(7);
				log.info("Pro_SaveDeclProductItemInput-rsmap-"+retStr);
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
		
		public List<RiskClassBean>    GetRiskClass() throws Exception{
			  List<RiskClassBean> list = new ArrayList<RiskClassBean>();
			  Connection conn = DBPool.ds.getConnection();
			  CallableStatement proc = null;
			  ResultSet rs = null;
			  try {
				String wCall = "{call Pro_GetRiskClass()}";
				proc = conn.prepareCall(wCall);
				proc.execute();
				rs = proc.getResultSet();
				RiskClassBean abean=null;
				    while(rs.next()){
				    	abean=RsToDtoUtil.tranRsToDto(rs, RiskClassBean.class);
				    	list.add(abean);
				    }
				log.info("Pro_GetRiskClass-rsmap-"+list);
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
		public List<Good_DeclBean>    GetDeclInfoFromCIQ(ProcessOperateLogBean bean) throws Exception{
			List<Good_DeclBean> list = new ArrayList<Good_DeclBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetDeclInfoFromCIQ(?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.execute();
			    rs = proc.getResultSet();
			    Good_DeclBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, Good_DeclBean.class);
			    	list.add(abean);
			    }
			    log.info("Pro_GetDeclProduct-rsmap-"+list);
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
		public List<Decl_GoodsBean>    GetDeclGoodsInfoFromCIQ(DeclGoodsInfoFromCIQInpBean bean) throws Exception{
			List<Decl_GoodsBean> list = new ArrayList<Decl_GoodsBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetDeclGoodsInfoFromCIQ(?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getDeclNo());
				proc.setString(2, bean.getGoodsNo());
				proc.execute();
			    rs = proc.getResultSet();
			    Decl_GoodsBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, Decl_GoodsBean.class);
			    	list.add(abean);
			    }
			    log.info("Pro_GetDeclProduct-rsmap-"+list);
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
		public List<ItemSubBean>    GetItemSub(ItemSubBean bean) throws Exception{
			List<ItemSubBean> list = new ArrayList<ItemSubBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetItemSub(?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getItemCode());
				proc.execute();
			    rs = proc.getResultSet();
			    ItemSubBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, ItemSubBean.class);
			    	list.add(abean);
			    }
			    log.info("Pro_GetItemSub-rsmap-"+list);
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
		
		
	public String GetCEMSSeqNum(ProcessOperateLogBean bean) throws Exception{

		
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetCEMSSeqNum(?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getDeclNo());
			proc.registerOutParameter(2, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(2);
			log.info("Pro_TransDecl-rsmap-"+retStr);
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
public String UpdateControlReturnFlgNew(ReturnFlgBean bean) throws Exception{

		
		Connection conn = DBPool.ds_cems.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_UpdateControlReturnFlg(?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getSeqNum());
			proc.setString(2, bean.getStatus());
			proc.setString(3, bean.getReturnFlg());
			proc.execute();
			retStr="true";
			log.info("Pro_UpdateControlReturnFlg-rsmap-"+retStr);
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
	
	public static List<LabelDto> generateLabellingForPrn(String labApplyNo)
			throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		List<LabelDto> list = new ArrayList<LabelDto>();
		try {
			String wCall = "{call Pro_GenerateLabellingForPrn(?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, labApplyNo);
			proc.execute();
			rs = proc.getResultSet();
			LabelDto dto = new LabelDto();
			HashMap<String,String> map = new HashMap<String, String>();
			while(rs.next()){
				if(rs.getString("OP").equals("A")){
				   if(rs.getString("P_Name").equals("样品标签")){
					   dto.setLabel(rs.getString("P_Value"));
				   }
				   else if(rs.getString("P_Name").equals("")){
					   
				   }
				   else{
					   map.put(rs.getString("P_Name"),rs.getString("P_Value"));
				   }
				}
				if(rs.getString("OP").equals("B")){
					dto.setCode(rs.getString("P_Value"));
				}
				if(rs.getString("OP").equals("C")){
					dto.setLabelData(map);
					list.add(dto);
					dto = new LabelDto();
					map.clear();
				}
			}
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
			}
		}
   
		return list;
	}

}
