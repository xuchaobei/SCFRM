package cn.gov.scciq.basicSettings.item;

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

import cn.gov.scciq.basicSettings.materialClass.MaterialClassBean;
import cn.gov.scciq.basicSettings.materialSource.MaterialSourceSubBean;
import cn.gov.scciq.dbpool.DBPool;
import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.RsToDtoUtil;

public class ItemService {
	 private static Log log=LogFactory.getLog(ItemService.class);
	 String json = null;
	 String rowIndex ;
	 String pageSize;
	 List<ItemBean> list = new ArrayList<ItemBean>();
	 String retStr = ConstantStr.SAVE_ERROR_MSG;
	public String SaveItem(ItemBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			
		
			
			String wCall = "{call Pro_SaveItem(?,?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getItemID());
			proc.setString(2, bean.getItemCode());
			proc.setString(3, bean.getItemName());
			proc.setString(4, bean.getRiskClassCode());
			proc.setString(5, bean.getItemType());
			proc.setString(6, bean.getLabFlg());
			proc.setString(7, bean.getIfSet());
			proc.registerOutParameter(8, java.sql.Types.VARCHAR);
			proc.registerOutParameter(9, java.sql.Types.VARCHAR);
			proc.execute();
		//	rs = proc.getResultSet();
			retStr=proc.getString(8);
		/*	returnCode=proc.getString(9);
			json="{\"returnStr\":"+returnStr+",\"returnCode\":"+returnCode+"}";
			log.info("Pro_SaveItem--"+json);*/
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Pro_SaveItem--"+e);
		}finally{
		    try{
			if(conn!=null)
			   conn.close();
			if(proc!=null)
				proc.close();
			if(rs!=null)
				rs.close();
		      }
			catch(Exception e){
				e.printStackTrace();	
			}
		}
		return retStr;
	}
	
	public String SaveItemSub(ItemBean bean,String[] arr) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		
		try {
			String wCall=null;
			wCall = "{call Pro_SaveItem(?,?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getItemID());
			proc.setString(2, bean.getItemCode());
			proc.setString(3, bean.getItemName());
			proc.setString(4, bean.getRiskClassCode());
			proc.setString(5, bean.getItemType());
			proc.setString(6, bean.getLabFlg());
			proc.setString(7, bean.getIfSet());
			proc.registerOutParameter(8, java.sql.Types.VARCHAR);
			proc.registerOutParameter(9, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(8);
			proc.close();
			int i=0;
			
			while(i<arr.length){
				 wCall = "{call Pro_SaveItemSub(?,?,?,?,?)}";
				proc = conn.prepareCall(wCall);
				proc.setString(1, "0");
				proc.setString(2, bean.getItemCode());
				proc.setString(3, arr[i]);
				proc.registerOutParameter(4, java.sql.Types.VARCHAR);
				proc.registerOutParameter(5, java.sql.Types.VARCHAR);
				proc.execute();
				i++;
			}
			
			log.info("Pro_SaveItemSub--"+retStr);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Pro_SaveItemSub--"+e);
		}finally{
		    try{
			
			if(proc!=null)
				proc.close();
			if(conn!=null)
				  conn.close();
		      }
			catch(Exception e){
				e.printStackTrace();	
			}
		}
		return retStr;
	}
	
	public String DelItem(ItemBean bean) throws Exception {
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			
			
			
			
			String wCall = "{call Pro_DelItem(?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getItemCode());
			proc.registerOutParameter(2, java.sql.Types.VARCHAR);
			proc.execute();
			retStr=proc.getString(2);
			if("".equals(retStr) || retStr==null){
				retStr="true";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Pro_DelItem--"+e);
		}finally{
		    try{
			if(conn!=null)
			   conn.close();
			if(proc!=null)
				proc.close();
			if(rs!=null)
				rs.close();
		      }
			catch(Exception e){
				e.printStackTrace();	
			}
		}
		return retStr;
	}
	
	public Map<Integer, Object> GetItem(ItemBean bean,int showflg,int startindex,int pageSize) throws Exception {
		Map<Integer, Object> rsMap = new HashMap<Integer, Object>();
		 int recordsTotal = 0;
		 List<ItemBean> list = new ArrayList<ItemBean>();
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String wCall = "{call Pro_GetItem(?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setString(1, bean.getItemName());
			proc.setString(2, bean.getRiskClassCode());
			proc.setInt(3, showflg);
			proc.setInt(4, startindex);
			proc.setInt(5, pageSize);
			proc.setString(6, "ItemID"); //记录索引号
			proc.setString(7, "Desc"); //每页最大记录数
			proc.registerOutParameter(8, java.sql.Types.INTEGER);
			proc.execute();
		    rs = proc.getResultSet();
		    ItemBean itemBean=null;
		    while(rs.next()){
		    	itemBean=RsToDtoUtil.tranRsToDto(rs, ItemBean.class);
		    	/*String ifset=itemBean.getIfSet();
		    	if(ifset.equals("1")){
		    		itemBean.setIfSet("是");
		    	}else{
		    		itemBean.setIfSet("否");
		    	}*/
		    	list.add(itemBean);
		    }
		    recordsTotal=proc.getInt(8);
		    rsMap.put(1, recordsTotal);
           rsMap.put(2, list);
			log.info("Pro_GetItem-rsmap-"+rsMap);
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
	
	public List<ItemSubBean> GetItemSub(ItemSubBean bean) throws Exception {
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
			    ItemSubBean itemSubBean=null;
			    while(rs.next()){
			    	itemSubBean=RsToDtoUtil.tranRsToDto(rs, ItemSubBean.class);
			    	list.add(itemSubBean);
			    }
			   
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
	
	public  List<RiskClassBean> GetRiskClass() throws Exception {
		 List<RiskClassBean> list = new ArrayList<RiskClassBean>();
			Connection conn = DBPool.ds.getConnection();
			CallableStatement proc = null;
			ResultSet rs = null;
			try {
				String wCall = "{call Pro_GetRiskClass()}";
				proc = conn.prepareCall(wCall);
				
				proc.execute();
			    rs = proc.getResultSet();
			    RiskClassBean riskClassBean=null;
			    while(rs.next()){
			    	riskClassBean=RsToDtoUtil.tranRsToDto(rs, RiskClassBean.class);
			    	list.add(riskClassBean);
			    }
			   
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
	 

}
