package cn.gov.scciq.bussiness.searchSelect;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

/**
 * 对应页面上点击搜索按钮获取select集合的action
 * @author chao.xu
 *
 */
public class SearchSelectAction {
    
    private JSONObject result;
    
    /** 加工方式名称 */
    private String methodName;

    /**
     * 取得加工方式定义
     * @return
     */
    public String getProcessingMethod(){
        int startIndex = 0;
        int pageSize = 0;
        int showFlg = 0;
        String orderWord = "MethodCode";
        String orderDirection = "ASC";
        result = SearchSelectService.getProcessingMethod(methodName, showFlg, startIndex, pageSize, orderWord, orderDirection);
        return Action.SUCCESS;
    }
    
    /**原料来源名称 */
    private String sourceName;
    
    /**
     * 取得原料来源定义
     * @return
     */
    public String getMaterialSource(){
        int startIndex = 0;
        int pageSize = 0;
        int showFlg = 0;
        String orderWord = "SourceCode";
        String orderDirection = "ASC";
        result = SearchSelectService.getMaterialSource(sourceName, showFlg, startIndex, pageSize, orderWord, orderDirection);
        return Action.SUCCESS;
    }
    
    /** 包装类型 */
    private String typeName;
    
    /**
     * 取得包装类型定义
     * @return
     */
    public String getPackageType(){
        int startIndex = 0;
        int pageSize = 0;
        int showFlg = 0;
        String orderWord = "TypeCode";
        String orderDirection = "ASC";
        result = SearchSelectService.getPackageType(typeName, showFlg, startIndex, pageSize, orderWord, orderDirection);
        return Action.SUCCESS;
    }
    
    /** 预期用途 */
    private String useName;
    
    /**
     * 取得预期用途定义
     * @return
     */
    public String getIntendedUse(){
        int startIndex = 0;
        int pageSize = 0;
        int showFlg = 0;
        String orderWord = "UseCode";
        String orderDirection = "ASC";
        result = SearchSelectService.getIntendedUse(useName, showFlg, startIndex, pageSize, orderWord, orderDirection);
        return Action.SUCCESS;
    }
    
    /** 国家地区名称 */
    private String countryName;
    
    /**
     * 取得国家地区定义
     * @return
     */
    public String getCountry(){
        int startIndex = 0;
        int pageSize = 0;
        int showFlg = 0;
        String orderWord = "CountryCode";
        String orderDirection = "ASC";
        result = SearchSelectService.getCountry(countryName, showFlg, startIndex, pageSize, orderWord, orderDirection);
        return Action.SUCCESS;
    }

    private String levelType;
    
    /**
     * 等级类型
     * @return
     */
    public String getEvlLevel(){
        result = SearchSelectService.getEvlLevel(levelType);
        return Action.SUCCESS;
    }

    private String itemName;
    
    /**
     * 检测项目
     * @return
     */
    public String getItem(){
        result = SearchSelectService.getItem(itemName, "", 0, 0, 0, "ItemCode", "ASC");
        return Action.SUCCESS;
    }
    
    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
    
    
    
    
}
