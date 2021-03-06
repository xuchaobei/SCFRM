
package cn.gov.scciq.bussiness.searchSelect;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

/**
 * 对应页面上点击搜索按钮获取select集合的action
 * @author chao.xu
 *
 */
public class SearchSelectAction {
    
    private String data;
    private String entCode;
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
       System.out.println("processingway:"+result);
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
//<<<<<<< HEAD
        String orderWord = "IntendedUseID";
//=======
        //String orderWord = "UseCode";
//>>>>>>> branch 'master' of https://github.com/xuchaobei/SCFRM.git
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
    public String getCountryAbnor(){    
        int startIndex = 0;
        int pageSize = 0;
        int showFlg = 1;
        String orderWord = "CountryName";
        String orderDirection = "ASC";
        result = SearchSelectService.getCountry(countryName, showFlg, startIndex, pageSize, orderWord, orderDirection);
        return Action.SUCCESS;
    }
    public String getCountryForDeport(){
        int startIndex = 0;
        int pageSize = 0;
        int showFlg = 3;
        String orderWord = "CountryCode";
        String orderDirection = "ASC";
        result = SearchSelectService.getCountry(countryName, showFlg, startIndex, pageSize, orderWord, orderDirection);
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
    
    
    private String ciqControlID;
    
    private String definedField;
    
    private String keywords;
    
    /**
     * 根据应急布控ID以及所选的字段名称，查询得到对应的字段值
     * @return
     */
    public String getCIQControlKeyValue(){
        result = SearchSelectService.getCIQControlKeyValue(ciqControlID, definedField, keywords);
        return Action.SUCCESS;
    }
    
    /**
     * 查询辅料
     * @return
     */
    public String getAccessory(){
        int startIndex = 0;
        int pageSize = 0;
        String orderWord = "AccessoryName";
        String orderDirection = "ASC";
        result = SearchSelectService.getAccessory(data, startIndex, pageSize, orderWord, orderDirection);
        return Action.SUCCESS;
    }
    
    /**
     * 查询添加剂
     * @return
     */
    public String getAdditive(){
        int startIndex = 0;
        int pageSize = 0;
        String orderWord = "AdditiveName";
        String orderDirection = "ASC";
        result = SearchSelectService.getAdditive(data, startIndex, pageSize, orderWord, orderDirection);
        return Action.SUCCESS;
    }
    
    /**
     * 查询企业
     * @return
     */
    public String getEnt(){
        result = SearchSelectService.getEnt(data);
        return Action.SUCCESS;
    }
    
    /**
     * 查询基地
     * @return
     */
    public String getBase(){
        result = SearchSelectService.getBase(data);
        return Action.SUCCESS;
    }
    
    /**
     * 根据产品名称模糊查询产品数据，得到产品编号和产品名称数据
     * @return
     */
    public String getProductByQuery(){
    	result = SearchSelectService.getProductByQuery(entCode,data);
        return Action.SUCCESS;
    }
    /**
     * 根据所输入的样品类别模糊查询得到LMIS系统的样品类别定义
     * @return
     */
    public String getLabSampleKind(){
    	result = SearchSelectService.getLabSampleKind(data);
        return Action.SUCCESS;
    }
    
    /**
     * 根据送检部门名称模糊查询得到送检部门
     * @return
     */
    public String getLabApplyDept(){
    	result = SearchSelectService.getLabApplyDept(data);
        return Action.SUCCESS;
    }
    
    /**
     * 根据送检人模糊查询得到送检人
     * @return
     */
    public String getLabAppliant(){
    	result = SearchSelectService.getLabAppliant(data);
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCiqControlID() {
        return ciqControlID;
    }

    public void setCiqControlID(String ciqControlID) {
        this.ciqControlID = ciqControlID;
    }

    public String getDefinedField() {
        return definedField;
    }

    public void setDefinedField(String definedField) {
        this.definedField = definedField;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

	public String getEntCode() {
		return entCode;
	}

	public void setEntCode(String entCode) {
		this.entCode = entCode;
	}
    
    
    
    
}

