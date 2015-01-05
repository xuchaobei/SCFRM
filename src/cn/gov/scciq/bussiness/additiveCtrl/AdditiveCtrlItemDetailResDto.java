package cn.gov.scciq.bussiness.additiveCtrl;

public class AdditiveCtrlItemDetailResDto {

    private String itemCode;

    private String itemName;
    
    private String detectionStd;
    
    private String samplingRatio;

    private String limitType;
    private String detectionLimit;
    private String limitUnit;
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
    
    
}
