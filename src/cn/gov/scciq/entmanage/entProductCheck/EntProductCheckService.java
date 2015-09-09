package cn.gov.scciq.entmanage.entProductCheck;

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

public class EntProductCheckService {
	String retStr = ConstantStr.SAVE_ERROR_MSG;
	private static Log log=LogFactory.getLog(EntProductCheckService.class);

	
	
	
//取得企业端输入的企业产品数据
	public Map<Integer, Object>  getEntProductInput(EntProductInputBean bean,String inspOrgCode,int start,int length) throws Exception{
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<EntProductInputBean> list = new ArrayList<EntProductInputBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetEntProductInput(?,?,?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getEntCode());
			proc.setString(2, bean.getEntName());
			proc.setString(3, bean.getEntProductName());
			proc.setString(4,bean.getStatus());
			proc.setString(5, ContextUtil.getOrgCode());
			proc.setInt(6, start); //记录索引号
			proc.setInt(7, length); //每页最大记录数
			proc.setString(8, "EntProductInputID");
			proc.setString(9, "DESC");
			proc.registerOutParameter(10, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    EntProductInputBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, EntProductInputBean.class);
		    	list.add(abean);
		    }
		    recordsTotal=proc.getInt(10);
		    rsMap.put(1, recordsTotal);
            rsMap.put(2, list);
			log.info("Pro_GetEntProductInput-this-rsmap-"+rsMap);
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
	//取得企业端输入的企业产品数据
		public Map<Integer, Object>  getEntProductInputConPlan(EntProductInputBean bean) throws Exception{
			Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
			 int recordsTotal = 0;
			 List<EntProductInputBean> list = new ArrayList<EntProductInputBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetEntProductInput(?,?,?,?,?,?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getEntCode());
				proc.setString(2, bean.getEntName());
				proc.setString(3, bean.getEntProductName());
				proc.setString(4,bean.getStatus());
				proc.setString(5, "");
				proc.setInt(6, 0); //记录索引号
				proc.setInt(7, 0); //每页最大记录数
				proc.setString(8, "EntProductInputID");
				proc.setString(9, "DESC");
				proc.registerOutParameter(10, java.sql.Types.INTEGER);
				proc.execute();
			    rs = proc.getResultSet();
			    EntProductInputBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, EntProductInputBean.class);
			    	list.add(abean);
			    }
			    recordsTotal=proc.getInt(10);
			    rsMap.put(1, recordsTotal);
	            rsMap.put(2, list);
				log.info("Pro_GetEntProductInput-this-rsmap-"+rsMap);
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
//将系统所定义的状态数据放到状态下拉式编辑框中。
	public List<EntProductStatusBean>  getEntProductStatus() throws Exception{
		List<EntProductStatusBean> list = new ArrayList<EntProductStatusBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetEntProductStatus()}";
			proc = conn.prepareCall(wCall);
			proc.execute();
		    rs = proc.getResultSet();
		    EntProductStatusBean abean=null;
		    while(rs.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs, EntProductStatusBean.class);
		    	list.add(abean);
		    }
		    log.info("Pro_GetEntProductStatus-rsmap-"+list);
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
//根据企业产品的企业代码和企业产品自编号取得详细数据，包括产品特征数据、原料数据、辅料数据、添加剂数据。
	public Map<String, Object>   getEntProductDetailByID(EntProductBean bean) throws Exception{
		
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String wCall = "{call Pro_GetEntProductDetailByID(?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getEntCode());
			proc.setString(2, bean.getEntProductCode());
			proc.execute();
			ResultSet rs1 = null;
		    rs1 = proc.getResultSet();//first set
		    List<EntProductInputDetailBean> list = new ArrayList<EntProductInputDetailBean>();
		    EntProductInputDetailBean abean=null;
		    while(rs1.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs1, EntProductInputDetailBean.class);
		    	list.add(abean);
		    }
		    if(proc.getMoreResults()){// second set
		    	ResultSet rs2=	proc.getResultSet();
		    	 List<EntProductMaterialComparedBean> list2 = new ArrayList<EntProductMaterialComparedBean>();
		    	 EntProductMaterialComparedBean pBean=null;
		    	 while(rs2.next()){
		    		pBean=RsToDtoUtil.tranRsToDto(rs2, EntProductMaterialComparedBean.class);
			    	list2.add(pBean);
		    	    }
		    	 if(proc.getMoreResults()){ // thrid set 
				    	ResultSet rs3=	proc.getResultSet();
				    	 List<EntProductAccessoryBean> list3 = new ArrayList<EntProductAccessoryBean>();
				    	 EntProductAccessoryBean epBean=null;
				    	 while(rs3.next()){
				    		epBean=RsToDtoUtil.tranRsToDto(rs3, EntProductAccessoryBean.class);
					    	list3.add(epBean);
				    	    }
				    	 if(proc.getMoreResults()){ // four set
						    	ResultSet rs4=	proc.getResultSet();
						    	 List<EntProductAdditiveBean> list4 = new ArrayList<EntProductAdditiveBean>();
						    	 EntProductAdditiveBean epmBean=null;
						    	 while(rs4.next()){
						    		epmBean=RsToDtoUtil.tranRsToDto(rs4, EntProductAdditiveBean.class);
							    	list4.add(epmBean);
						    	    }
						    	 try {
						    		 if(rs4 != null){  
				                          rs4.close();  
				                      }  
				                      if(rs3!=null){  
				                          rs3.close();  
				                      }
				                      if(rs2 != null){  
				                          rs2.close();  
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
								    map.put("two", list2);
								    map.put("three", list3);
								    map.put("four", list4);	 
						      }
				      }
		    	    
		      }
		  log.info("Pro_GetEntProductDetailByID "+map);
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
//驳回企业产品
	public String   rejectEntProduct(EntProductInputBean bean) throws Exception{
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_RejectEntProduct(?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getEntCode());
			proc.setString(2, bean.getEntProductCode());
			proc.setString(3, bean.getRefuseReason());
			proc.setString(4, ContextUtil.getOrgCode());
			proc.setString(5, ContextUtil.getDeptCode());
			proc.setString(6, ContextUtil.getOperatorCode());
			proc.execute();
			retStr="true";
			log.info("Pro_RejectEntProduct--"+retStr);
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
//根据产品特征数据，得到前15位编码相同的产品列表
	public   Map<String, Object>   getProductListByProductNo15(EntProductInputBean bean) throws Exception{
		  List<ProductBean> list = new ArrayList<ProductBean>();
		  Map<String, Object> map = new HashMap<String, Object>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetProductListByProductNo15(?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, bean.getEntCode());
				proc.setString(2, bean.getEntProductCode());
				proc.registerOutParameter(3, java.sql.Types.VARCHAR);
				proc.registerOutParameter(4, java.sql.Types.VARCHAR);
				proc.registerOutParameter(5, java.sql.Types.INTEGER);
				proc.execute();
			    rs = proc.getResultSet();
			    ProductBean abean=null;
			    while(rs.next()){
			    	abean=RsToDtoUtil.tranRsToDto(rs, ProductBean.class);
			    	list.add(abean);
			    }
			    map.put("one", proc.getString(3));
			    map.put("two", proc.getString(4));
			    map.put("three", proc.getString(5));
			    map.put("four", list);	
			    System.out.println("map "+map);
			    log.info("Pro_GetProductListByProductNo15--"+list);
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
	      
	      return map;
		
		
	}
//保存产品数据
	public String   saveProduct(EntProductInputBean bean) throws Exception{
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_SaveProduct(?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getEntCode());
			proc.setString(2, bean.getEntProductCode());
			proc.setString(3, bean.getProductCode());
			proc.setString(4, bean.getProductName());
			proc.setString(5, ContextUtil.getOrgCode());
			proc.setString(6, ContextUtil.getDeptCode());
			proc.setString(7, ContextUtil.getOperatorCode());
			proc.registerOutParameter(8, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(8);
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
//检查产品编号对应的原料是否与被审核的产品的原料是否相一致
	public String   checkMaterialForProduct(EntProductInputBean bean) throws Exception{
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_CheckMaterialForProduct(?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getProductCode());
			proc.setString(2, bean.getEntCode());
			proc.setString(3, bean.getEntProductCode());
			proc.registerOutParameter(4, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(4);
			log.info("Pro_CheckMaterialForProduct--"+retStr);
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
//更新企业产品数据
	public String   updateEntProduct(EntProductInputBean bean) throws Exception{
		
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		try {
			String wCall = "{call Pro_UpdateEntProduct(?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1,bean.getEntCode());
			proc.setString(2,bean.getEntProductCode());
			proc.setString(3,bean.getProductCode());
			proc.setString(4, ContextUtil.getOrgCode());
			proc.setString(5, ContextUtil.getDeptCode());
			proc.setString(6, ContextUtil.getOperatorCode());
			proc.registerOutParameter(7, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(7);
			log.info("Pro_UpdateEntProduct--"+retStr);
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
//比对被审产品和已有产品的产品数据
	public Map<String, Object>   getProductInfoCompared(EntProductInputBean bean) throws Exception{
		
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String wCall = "{call Pro_GetProductInfoCompared(?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getEntCode());
			proc.setString(2, bean.getEntProductCode());
			proc.setString(3, bean.getProductCode());
			proc.execute();
			ResultSet rs1 = null;
		    rs1 = proc.getResultSet();//first set
		    List<ProductComparedBean> list = new ArrayList<ProductComparedBean>();
		    ProductComparedBean abean=null;
		    while(rs1.next()){
		    	abean=RsToDtoUtil.tranRsToDto(rs1, ProductComparedBean.class);
		    	list.add(abean);
		    }
		    if(proc.getMoreResults()){// second set
		    	ResultSet rs2=	proc.getResultSet();
		    	 List<ProductMaterialComparedBean> list2 = new ArrayList<ProductMaterialComparedBean>();
		    	 ProductMaterialComparedBean pBean=null;
		    	 while(rs2.next()){
		    		pBean=RsToDtoUtil.tranRsToDto(rs2, ProductMaterialComparedBean.class);
			    	list2.add(pBean);
		    	    }
		    	 if(proc.getMoreResults()){ // thrid set 
				    	ResultSet rs3=	proc.getResultSet();
				    	 List<EntProductInputComparedBean> list3 = new ArrayList<EntProductInputComparedBean>();
				    	 EntProductInputComparedBean epBean=null;
				    	 while(rs3.next()){
				    		epBean=RsToDtoUtil.tranRsToDto(rs3, EntProductInputComparedBean.class);
					    	list3.add(epBean);
				    	    }
				    	 if(proc.getMoreResults()){ // four set
						    	ResultSet rs4=	proc.getResultSet();
						    	 List<EntProductMaterialComparedBean> list4 = new ArrayList<EntProductMaterialComparedBean>();
						    	 EntProductMaterialComparedBean epmBean=null;
						    	 while(rs4.next()){
						    		epmBean=RsToDtoUtil.tranRsToDto(rs4, EntProductMaterialComparedBean.class);
							    	list4.add(epmBean);
						    	    }
						    	 try {
						    		 if(rs4 != null){  
				                          rs4.close();  
				                      }  
				                      if(rs3!=null){  
				                          rs3.close();  
				                      }
				                      if(rs2 != null){  
				                          rs2.close();  
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
								    map.put("two", list2);
								    map.put("three", list3);
								    map.put("four", list4);	 
						      }
				      }
		    	    
		      }
		    	log.info("Pro_GetProductInfoCompared "+map);
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

}
