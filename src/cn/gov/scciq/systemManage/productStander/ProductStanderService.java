package cn.gov.scciq.systemManage.productStander;

import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.gov.scciq.bussiness.auth.AuthorityDao;

import cn.gov.scciq.util.ConstantStr;
import cn.gov.scciq.util.DefaultResultUtil;

public class ProductStanderService {
	
	  public static JSONObject normalizeProductName(String data) throws Exception{
	        String permission = AuthorityDao.getOperateLimit(ConstantStr.PROCUCT_STANDER_MNG);
	        if(!permission.equals("1")){
	            JSONObject result = DefaultResultUtil.getModificationResult(ConstantStr.PERMISSION_DENIAL_MSG);
	            return result;
	        }
	        ProductStanderInputDto bean = (ProductStanderInputDto)JSONObject.toBean(JSONObject.fromObject(data), ProductStanderInputDto.class);
	        String retStr = ProductStanderDao.normalizeProductName(bean);
	        if("".equals(retStr)){
	            retStr = "true";
	        }
	        JSONObject result = DefaultResultUtil.getModificationResult(retStr);
	        return result; 
	    }
	  public static JSONObject getProductByQuery(String data, int draw, int start, int length) throws Exception{
			// TODO Auto-generated method stub
		  ProductStanderInputDto bean = (ProductStanderInputDto)JSONObject.toBean(JSONObject.fromObject(data),ProductStanderInputDto.class);
			Map<Integer,Object> rsMap = ProductStanderDao.getProductByQuery(bean, start, length);
		    int recordsTotal = (Integer)rsMap.get(1);
	        JSONArray ja = JSONArray.fromObject(rsMap.get(2));
	        JSONObject result = DefaultResultUtil.getDefaultTableResult(draw, recordsTotal, recordsTotal, ja);
	        return result;
		}
	  public static JSONObject   getProductByCode(String data) throws Exception{
			 JSONObject object = JSONObject.fromObject(data);
			 ProductStanderInputDto bean=(ProductStanderInputDto)JSONObject.toBean(object,ProductStanderInputDto.class);
		     Map<String, Object> rsMap=  ProductStanderDao.getProductByCode(bean);
		     JSONObject result = DefaultResultUtil.getDefaultResult(rsMap);
		     return result;
			
		}

}
