package cn.gov.scciq.bussiness.additiveCtrl;

/**
 * 辅料布控列表返回集合
 * @author chao.xu
 *
 */
public class AdditiveCtrlResDto {
    
    private String additiveControlID;
    private String additiveName;
    private String countryName;
    private String productClassName;
    private String productSubclassName;
    private String materialClassName;
    private String materialSubclassName;
    private String materialName;
    private String controlDeptName;
    private String controlDatetime;
    public String getAdditiveControlID() {
        return additiveControlID;
    }
    public void setAdditiveControlID(String accessoryControlID) {
        this.additiveControlID = accessoryControlID;
    }
    public String getAdditiveName() {
        return additiveName;
    }
    public void setAdditiveName(String accessoryName) {
        this.additiveName = accessoryName;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public String getProductClassName() {
        return productClassName;
    }
    public void setProductClassName(String productClassName) {
        this.productClassName = productClassName;
    }
    public String getProductSubclassName() {
        return productSubclassName;
    }
    public void setProductSubclassName(String productSubclassName) {
        this.productSubclassName = productSubclassName;
    }
    public String getMaterialClassName() {
        return materialClassName;
    }
    public void setMaterialClassName(String materialClassName) {
        this.materialClassName = materialClassName;
    }
    public String getMaterialSubclassName() {
        return materialSubclassName;
    }
    public void setMaterialSubclassName(String materialSubclassName) {
        this.materialSubclassName = materialSubclassName;
    }
    public String getMaterialName() {
        return materialName;
    }
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
    public String getControlDeptName() {
        return controlDeptName;
    }
    public void setControlDeptName(String controlDeptName) {
        this.controlDeptName = controlDeptName;
    }
    public String getControlDatetime() {
        return controlDatetime;
    }
    public void setControlDatetime(String controlDatetime) {
        this.controlDatetime = controlDatetime;
    }
    
    
}
