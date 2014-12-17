package cn.gov.scciq.bussiness.ciqCtrl;

import net.sf.json.JSONArray;

/**
 * 应急布控 布控项目   
 * 
 * @author chao.xu
 * 
 */
public class CIQCtrlItemReqDto {
    private String ciqControlItemID;
    
    private String ciqControlID;

    private String itemCode;

    private String itemName;

    private String samplingMode;

    private String samplingParamValue;

    private String detectionStd;

    private String limitType;
    private String detectionLimit;
    private String limitUnit;
    
    private JSONArray itemLimitList;

    public String getCiqControlItemID() {
        return ciqControlItemID;
    }

    public void setCiqControlItemID(String ciqControlItemID) {
        this.ciqControlItemID = ciqControlItemID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSamplingMode() {
        return samplingMode;
    }

    public void setSamplingMode(String samplingMode) {
        this.samplingMode = samplingMode;
    }

    public String getSamplingParamValue() {
        return samplingParamValue;
    }

    public void setSamplingParamValue(String samplingParamValue) {
        this.samplingParamValue = samplingParamValue;
    }

    public String getDetectionStd() {
        return detectionStd;
    }

    public void setDetectionStd(String detectionStd) {
        this.detectionStd = detectionStd;
    }


    public String getCiqControlID() {
        return ciqControlID;
    }

    public void setCiqControlID(String ciqControlID) {
        this.ciqControlID = ciqControlID;
    }

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    public String getDetectionLimit() {
        return detectionLimit;
    }

    public void setDetectionLimit(String detectionLimit) {
        this.detectionLimit = detectionLimit;
    }

    public String getLimitUnit() {
        return limitUnit;
    }

    public void setLimitUnit(String limitUnit) {
        this.limitUnit = limitUnit;
    }

    public JSONArray getItemLimitList() {
        return itemLimitList;
    }

    public void setItemLimitList(JSONArray itemLimitList) {
        this.itemLimitList = itemLimitList;
    }

    
}
