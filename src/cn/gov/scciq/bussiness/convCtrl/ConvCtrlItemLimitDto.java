package cn.gov.scciq.bussiness.convCtrl;

/**
 * 限量表
 * @author chao.xu
 *
 */
public class ConvCtrlItemLimitDto {
    private String countryCode;
    private String countryName;    
    private String detectionLimit;    
    private String limitUnit;
    
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
