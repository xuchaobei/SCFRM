package cn.gov.scciq.bussiness.searchSelect;

import java.util.List;

import net.sf.json.JSONObject;
import cn.gov.scciq.dto.CountryDto;
import cn.gov.scciq.dto.EntDto;
import cn.gov.scciq.dto.IntendedUseDto;
import cn.gov.scciq.dto.ItemDto;
import cn.gov.scciq.dto.KeywordsDto;
import cn.gov.scciq.dto.MaterialSourceDto;
import cn.gov.scciq.dto.PackageTypeDto;
import cn.gov.scciq.dto.ProcessingMethodDto;
import cn.gov.scciq.util.ContextUtil;
import cn.gov.scciq.util.DefaultResultUtil;

/**
 * 对应页面上点击搜索按钮获取select集合的action
 * @author chao.xu
 *
 */
public class SearchSelectService {

    /**
     * 取得加工方式定义
     * @return
     */
    public static JSONObject getProcessingMethod(String methodName, int showFlg,  int startIndex, int pageSize, String orderWord, String orderDirection){
        List<ProcessingMethodDto> processingMethodList = SearchSelectDao.getProcessingMethod( methodName, showFlg, 0, 0, orderWord, orderDirection);
        JSONObject rs = DefaultResultUtil.getDefaultResult(processingMethodList);
        return rs;
    }
    
    /**
     * 取得原料来源定义
     * @return
     */
    public static JSONObject getMaterialSource(String sourceName, int showFlg,  int startIndex, int pageSize, String orderWord, String orderDirection){
        List<MaterialSourceDto> materialSourceList = SearchSelectDao.getMaterialSource( sourceName, showFlg, 0, 0, orderWord, orderDirection);
        JSONObject rs = DefaultResultUtil.getDefaultResult(materialSourceList);
        return rs;
    }
    
    /**
     * 取得包装类型定义
     * @return
     */
    public static JSONObject getPackageType(String typeName, int showFlg,  int startIndex, int pageSize, String orderWord, String orderDirection){
        List<PackageTypeDto> packageTypeList = SearchSelectDao.getPackageType( typeName, showFlg, 0, 0, orderWord, orderDirection);
        JSONObject rs = DefaultResultUtil.getDefaultResult(packageTypeList);
        return rs;
    }
    
    /**
     * 取得预期用途定义
     * @return
     */
    public static JSONObject getIntendedUse(String useName, int showFlg,  int startIndex, int pageSize, String orderWord, String orderDirection){
        List<IntendedUseDto> intendedUseList = SearchSelectDao.getIntendedUse( useName, showFlg, 0, 0, orderWord, orderDirection);
        JSONObject rs = DefaultResultUtil.getDefaultResult(intendedUseList);
        return rs;
    }
    
    /**
     * 取得国家地区定义
     * @return
     */
    public static JSONObject getCountry(String methodName, int showFlg,  int startIndex, int pageSize, String orderWord, String orderDirection){
        List<CountryDto> countryList = SearchSelectDao.getCountry( methodName, showFlg, 0, 0, orderWord, orderDirection);
        JSONObject rs = DefaultResultUtil.getDefaultResult(countryList);
        return rs;
    }
    
    
    /**
     * 检测项目
     * @return
     */
    public static JSONObject getItem(String itemName, String riskClassCode, int showFlg, int startIndex, int  pageSize, String orderWord, String orderDirection){
        List<ItemDto> itemList = SearchSelectDao.getItem(itemName, riskClassCode, showFlg, startIndex, pageSize, orderWord, orderDirection);
        JSONObject rs = DefaultResultUtil.getDefaultResult(itemList);
        return rs;
    }
    
    /**
     * 根据应急布控ID以及所选的字段名称，查询得到对应的字段值
     * @return
     */
    public static JSONObject getCIQControlKeyValue(String ciqControlID, String definedField, String keywords){
        List<KeywordsDto> list  = SearchSelectDao.getCIQControlKeyValue(ciqControlID, definedField, keywords);
        JSONObject rs = DefaultResultUtil.getDefaultResult(list);
        return rs;
    }

    /**
     * 查询辅料
     * @return
     */
    public static JSONObject getAccessory(String accessoryName, int startIndex, int pageSize, String orderWord, String orderDirection) {
        // TODO Auto-generated method stub
        List<String> list = SearchSelectDao.getAccessory(accessoryName,  startIndex,  pageSize,  orderWord,  orderDirection);
        JSONObject rs = DefaultResultUtil.getDefaultResult(list);
        return rs;
    }
    
    /**
     * 查询添加剂
     * @return
     */
    public static JSONObject getAdditive(String additiveName, int startIndex, int pageSize, String orderWord, String orderDirection) {
        // TODO Auto-generated method stub
        List<String> list = SearchSelectDao.getAdditive(additiveName, startIndex, pageSize, orderWord, orderDirection);
        JSONObject rs = DefaultResultUtil.getDefaultResult(list);
        return rs;
    }
    
    /**
     * 查询企业
     * @param entName
     * @return
     */
    public static JSONObject getEnt(String entName){
        int startIndex = 0;
        int pageSize = 0;
        String orderWord = "EntCode";
        String orderDirection = "ASC";
        String entCode = "";
        String mngOrgCode = "";
        String roleCode = "";
    
        List<EntDto> list = SearchSelectDao.getEnt(entName, entCode, mngOrgCode, ContextUtil.getOrgCode(), roleCode, startIndex, pageSize, orderWord, orderDirection);
        JSONObject rs = DefaultResultUtil.getDefaultResult(list);
        return rs;
    }
}
