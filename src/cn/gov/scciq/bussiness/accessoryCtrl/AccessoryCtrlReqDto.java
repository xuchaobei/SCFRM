package cn.gov.scciq.bussiness.accessoryCtrl;

import net.sf.json.JSONArray;

public class AccessoryCtrlReqDto {
    
    private String accessoryControlID;
    private String accessoryName;
    private String productClassCode;
    private String productSubclassCode;
    private String materialClassCode;
    private String materialSubclassCode;
    private String materialCode;
    private String countryCode;
    
    private JSONArray itemLimitList;
    
    public String getAccessoryControlID() {
        return accessoryControlID;
    }
    public void setAccessoryControlID(String accessoryControlID) {
        this.accessoryControlID = accessoryControlID;
    }
    public String getAccessoryName() {
        return accessoryName;
    }
    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
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
    public String getMaterialCode() {
        return materialCode;
    }
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public JSONArray getItemLimitList() {
        return itemLimitList;
    }
    public void setItemLimitList(JSONArray itemLimitList) {
        this.itemLimitList = itemLimitList;
    }
    
    
}
