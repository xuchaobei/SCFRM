package cn.gov.scciq.bussiness.baseStatistics;

public class BaseStatisticsDto {
	
    private String baseCode;
	private String baseName;
	private String productCode;
	private String productName;
	private String countryName;
	private String totalWeight;
    private String className;
    private String subclassName;
    private String materialName;
    private String totalExportTimes;
    private String totalUnqualifyExportTimes;
    private String unqualifyRatio;
    
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBaseCode() {
		return baseCode;
	}
	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
	}
	public String getBaseName() {
		return baseName;
	}
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}
	public String getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}
	public String getSubclassName() {
		return subclassName;
	}
	public void setSubclassName(String subclassName) {
		this.subclassName = subclassName;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getTotalExportTimes() {
		return totalExportTimes;
	}
	public void setTotalExportTimes(String totalExportTimes) {
		this.totalExportTimes = totalExportTimes;
	}
	public String getTotalUnqualifyExportTimes() {
		return totalUnqualifyExportTimes;
	}
	public void setTotalUnqualifyExportTimes(String totalUnqualifyExportTimes) {
		this.totalUnqualifyExportTimes = totalUnqualifyExportTimes;
	}
	public String getUnqualifyRatio() {
		return unqualifyRatio;
	}
	public void setUnqualifyRatio(String unqualifyRatio) {
		this.unqualifyRatio = unqualifyRatio;
	}

	
	
	
}
