package cn.gov.scciq.bussiness.accessoryCtrl;

/**
 * 辅料布控项目
 * 
 * @author chao.xu
 *
 */
public class AccessoryCtrlItemResDto {
    private String accessoryControlItemID;
    private String itemCode;
    private String itemName;
    private String detectionStd;
    private String samplingRatio;
    private String limitReq;
    public String getAccessoryControlItemID() {
        return accessoryControlItemID;
    }
    public void setAccessoryControlItemID(String accessoryControlItemID) {
        this.accessoryControlItemID = accessoryControlItemID;
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
    public String getLimitReq() {
        return limitReq;
    }
    public void setLimitReq(String limitReq) {
        this.limitReq = limitReq;
    }

    
}
