package cn.gov.scciq.bussiness.searchSelect;

import java.util.List;

import net.sf.json.JSONObject;
import cn.gov.scciq.dto.CountryDto;
import cn.gov.scciq.dto.IntendedUseDto;
import cn.gov.scciq.dto.ItemDto;
import cn.gov.scciq.dto.MaterialSourceDto;
import cn.gov.scciq.dto.PackageTypeDto;
import cn.gov.scciq.dto.ProcessingMethodDto;
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
    
}
