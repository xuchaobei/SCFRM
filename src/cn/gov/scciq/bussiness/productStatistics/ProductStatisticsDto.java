package cn.gov.scciq.bussiness.productStatistics;

public class ProductStatisticsDto {
	private String productCode;
	private String productName;
	private String countryName;
	private String itemName;
	private String totalGoodsTimes;
	private String totalItemSamplingTimes;
	private String itemSamplingRatio;
	private String totalItemUnqualifyTimes;
	private String itemUnqualifyRatio;
	
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
	public String getTotalItemSamplingTimes() {
		return totalItemSamplingTimes;
	}
	public void setTotalItemSamplingTimes(String totalItemSamplingTimes) {
		this.totalItemSamplingTimes = totalItemSamplingTimes;
	}
	public String getItemSamplingRatio() {
		return itemSamplingRatio;
	}
	public void setItemSamplingRatio(String itemSamplingRatio) {
		this.itemSamplingRatio = itemSamplingRatio;
	}
	public String getTotalItemUnqualifyTimes() {
		return totalItemUnqualifyTimes;
	}
	public void setTotalItemUnqualifyTimes(String totalItemUnqualifyTimes) {
		this.totalItemUnqualifyTimes = totalItemUnqualifyTimes;
	}
	public String getItemUnqualifyRatio() {
		return itemUnqualifyRatio;
	}
	public void setItemUnqualifyRatio(String itemUnqualifyRatio) {
		this.itemUnqualifyRatio = itemUnqualifyRatio;
	}
	
}
