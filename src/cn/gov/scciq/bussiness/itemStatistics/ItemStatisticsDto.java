package cn.gov.scciq.bussiness.itemStatistics;

public class ItemStatisticsDto {
	
	private String itemCode;
    private String itemName;
    private String className;
	private String productCode;
	private String productName;
	private String countryName;
	private String totalGoodsTimes;
	private String totalProductSamplingTimes;
	private String productSamplingRatio;
	private String totalProductUnqualifyTimes;
	private String productUnqualifyRatio;
	
	
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
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
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getTotalGoodsTimes() {
		return totalGoodsTimes;
	}
	public void setTotalGoodsTimes(String totalGoodsTimes) {
		this.totalGoodsTimes = totalGoodsTimes;
	}
	public String getTotalProductSamplingTimes() {
		return totalProductSamplingTimes;
	}
	public void setTotalProductSamplingTimes(String totalProductSamplingTimes) {
		this.totalProductSamplingTimes = totalProductSamplingTimes;
	}
	public String getProductSamplingRatio() {
		return productSamplingRatio;
	}
	public void setProductSamplingRatio(String productSamplingRatio) {
		this.productSamplingRatio = productSamplingRatio;
	}
	public String getTotalProductUnqualifyTimes() {
		return totalProductUnqualifyTimes;
	}
	public void setTotalProductUnqualifyTimes(String totalProductUnqualifyTimes) {
		this.totalProductUnqualifyTimes = totalProductUnqualifyTimes;
	}
	public String getProductUnqualifyRatio() {
		return productUnqualifyRatio;
	}
	public void setProductUnqualifyRatio(String productUnqualifyRatio) {
		this.productUnqualifyRatio = productUnqualifyRatio;
	}
	
	
}
