package cn.gov.scciq.bussiness.additiveCtrl;

import net.sf.json.JSONArray;

/**
 * 辅料布控项目
 * 
 * @author chao.xu
 *
 */
public class AdditiveCtrlItemReqDto {
    private String additiveControlItemID;
    private String additiveControlID;
    private String itemCode;
    private String itemName;
    private String detectionStd;
    private String samplingRatio;
    private String limitType;
    private String detectionLimit;
    private String limitUnit;
    private JSONArray itemLimitList;
    public String getAdditiveControlItemID() {
        return additiveControlItemID;
    }
    public void setAdditiveControlItemID(String accessoryControlItemID) {
        this.additiveControlItemID = accessoryControlItemID;
    }
    public String getAdditiveControlID() {
        return additiveControlID;
    }
    public void setAdditiveControlID(String accessoryControlID) {
        this.additiveControlID = accessoryControlID;
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
    public String getDetectionStd() {
        return detectionStd;
    }
    public void setDetectionStd(String detectionStd) {
        this.detectionStd = detectionStd;
    }
    public String getSamplingRatio() {
        return samplingRatio;
    }
    public void setSamplingRatio(String samplingRatio) {
        this.samplingRatio = samplingRatio;
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
