package cn.gov.scciq.bussiness.select;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

/**
 * 获取所有select集合
 * @author chao.xu
 *
 */
public class SelectDataAction {
    
    private JSONObject result;

    
    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    /**
     * 得到检验机构列表
     * @return
     */
    public String getInspOrg(){
        result = SelectDataService.getInspOrg();
        return Action.SUCCESS;
    }
    
    /** 检验机构号 */
    private String orgCode;
    
    /**
     * 得到检验部门列表
     * @return
     */
    public String getInspDept(){
        result = SelectDataService.getInspDept(orgCode);
        return Action.SUCCESS;
    }
    
  
    /** 产品大类号 */
    private String productClassCode;
    /** 产品小类号 */
    private String productSubclassCode;
    
    /**
     * 得到产品大类类别
     * @return
     */
    public String getProductClass(){
        int startIndex = 0;
        int pageSize = 0;
        String orderWord = "ClassCode";
        String orderDirection = "ASC";
        result = SelectDataService.getProductClass(startIndex, pageSize, orderWord, orderDirection);
        return Action.SUCCESS;
    }
  
    /**
     * 得到产品小类列表
     * @return
     */
    public String getProductSubclass(){
        int startIndex = 0;
        int pageSize = 0;
        String orderWord = "SubclassCode";
        String orderDirection = "ASC";
        result = SelectDataService.getProductSubclass(productClassCode, startIndex, pageSize, orderWord, orderDirection);
        return Action.SUCCESS;
    }
    
    
    /** 原料大类号 */
    private String materialClassCode;
    /** 原料小类号 */
    private String materialSubclassCode;
    /** 显示内容标识：0:所有；1：非集合数据；2：集合数据 */
    private int showFlag;
    
    /**
     * 得到原料大类列表
     * @return
     */
    public String getMaterialClass(){
        int startIndex = 0;
        int pageSize = 0;
        String orderWord = "ClassCode";
        String orderDirection = "ASC";
        result = SelectDataService.getMaterialClass(startIndex, pageSize, orderWord, orderDirection);
        return Action.SUCCESS;
    }
    
    /**
     * 得到原料小类列表
     * @return
     */
    public String getMaterialSubclass(){
        int startIndex = 0;
        int pageSize = 0;
        String orderWord = "ClassCode";
        String orderDirection = "ASC";
        result = SelectDataService.getMaterialSubclass(materialClassCode, startIndex, pageSize, orderWord, orderDirection);
        return Action.SUCCESS;
    }
    
    /**
     * 得到原料细类列表
     * @return
     */
    public String getMaterialSubsubclass(){
        int startIndex = 0;
        int pageSize = 0;
        String orderWord = "ClassCode";
        String orderDirection = "ASC";
        result = SelectDataService.getMaterialSubsubclass(materialClassCode, materialClassCode, showFlag, startIndex, pageSize, orderWord, orderDirection);
        return Action.SUCCESS;
    }
    
    
    /**
     * 限量类型
     * @return
     */
    public String getLimitType(){
        result = SelectDataService.getLimitType();
        return Action.SUCCESS;
    }
    

    public String getProductClassCode() {
        return productClassCode;
    }

    public void setProductClassCode(String productClassCode) {
        this.productClassCode = productClassCode;
    }

    public String getProductSubclassCode() {
        return productSubclassCode;
    }

    public void setProductSubclassCode(String productSubclassCode) {
        this.productSubclassCode = productSubclassCode;
    }

    public String getMaterialClassCode() {
        return materialClassCode;
    }

    public void setMaterialClassCode(String materialClassCode) {
        this.materialClassCode = materialClassCode;
    }

    public String getMaterialSubclassCode() {
        return materialSubclassCode;
    }

    public void setMaterialSubclassCode(String materialSubclassCode) {
        this.materialSubclassCode = materialSubclassCode;
    }

    public int getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(int showFlag) {
        this.showFlag = showFlag;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    
    
    
    
    
    
}
