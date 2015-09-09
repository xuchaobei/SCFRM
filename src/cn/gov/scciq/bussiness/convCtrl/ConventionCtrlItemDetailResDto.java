package cn.gov.scciq.bussiness.convCtrl;

/**
 * 布控项目table返回数据
 * 
 * @author chao.xu
 * 
 */
public class ConventionCtrlItemDetailResDto {

    private String convCtrlItemID;
    
    private String convCtrlID;

    private String itemCode;

    private String itemName;

    /** 检测标准 */
    private String detectionStd;

    /** 监控依据 */
    private String monitoringReason;

    /** 项目超标率 */
    private String unqualifyRatio;

    /** 风险危害程度 */
    private String hazardLevel;

    /** 输入国反应程度 */
    private String countryReactLevel;

    private String limitType;
    private String detectionLimit;
    private String limitUnit;


    public String getConvCtrlItemID() {
        return convCtrlItemID;
    }

    public void setConvCtrlItemID(String convCtrlItemID) {
        this.convCtrlItemID = convCtrlItemID;
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

    public String getMonitoringReason() {
        return monitoringReason;
    }

    public void setMonitoringReason(String monitoringReason) {
        this.monitoringReason = monitoringReason;
    }

    public String getUnqualifyRatio() {
        return unqualifyRatio;
    }

    public void setUnqualifyRatio(String unqualifyRatio) {
        this.unqualifyRatio = unqualifyRatio;
    }

    public String getHazardLevel() {
        return hazardLevel;
    }

    public void setHazardLevel(String hazardLevel) {
        this.hazardLevel = hazardLevel;
    }

    public String getCountryReactLevel() {
        return countryReactLevel;
    }

    public void setCountryReactLevel(String countryReactLevel) {
        this.countryReactLevel = countryReactLevel;
    }

    public String getConvCtrlID() {
        return convCtrlID;
    }

    public void setConvCtrlID(String convCtrlID) {
        this.convCtrlID = convCtrlID;
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
