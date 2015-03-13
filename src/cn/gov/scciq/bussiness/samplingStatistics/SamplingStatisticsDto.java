package cn.gov.scciq.bussiness.samplingStatistics;

public class SamplingStatisticsDto {
	private String countryName;
	private String entName;
	private String productCode;
	private String productName;
	private String itemCode;
    private String itemName;
    private String className;
    private String itemSamplingTheoryRatio_Lower;
    private String itemSamplingTheoryRatio_Upper;
    private String totalGoodsTimes;
    private String totalSamplingTimes;
    private String itemSamplingActualRatio;
    private String itemSamplingRatioErr;
    private String totalLabDataTimes;
    private String labDataRatio;
    private String labDataTimesErr;
    
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
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public String getItemSamplingTheoryRatio_Lower() {
		return itemSamplingTheoryRatio_Lower;
	}
	public void setItemSamplingTheoryRatio_Lower(
			String itemSamplingTheoryRatio_Lower) {
		this.itemSamplingTheoryRatio_Lower = itemSamplingTheoryRatio_Lower;
	}
	public String getItemSamplingTheoryRatio_Upper() {
		return itemSamplingTheoryRatio_Upper;
	}
	public void setItemSamplingTheoryRatio_Upper(
			String itemSamplingTheoryRatio_Upper) {
		this.itemSamplingTheoryRatio_Upper = itemSamplingTheoryRatio_Upper;
	}
	public String getTotalSamplingTimes() {
		return totalSamplingTimes;
	}
	public void setTotalSamplingTimes(String totalSamplingTimes) {
		this.totalSamplingTimes = totalSamplingTimes;
	}
	public String getItemSamplingActualRatio() {
		return itemSamplingActualRatio;
	}
	public void setItemSamplingActualRatio(String itemSamplingActualRatio) {
		this.itemSamplingActualRatio = itemSamplingActualRatio;
	}
	public String getItemSamplingRatioErr() {
		return itemSamplingRatioErr;
	}
	public void setItemSamplingRatioErr(String itemSamplingRatioErr) {
		this.itemSamplingRatioErr = itemSamplingRatioErr;
	}
	public String getTotalLabDataTimes() {
		return totalLabDataTimes;
	}
	public void setTotalLabDataTimes(String totalLabDataTimes) {
		this.totalLabDataTimes = totalLabDataTimes;
	}
	public String getLabDataRatio() {
		return labDataRatio;
	}
	public void setLabDataRatio(String labDataRatio) {
		this.labDataRatio = labDataRatio;
	}
	public String getLabDataTimesErr() {
		return labDataTimesErr;
	}
	public void setLabDataTimesErr(String labDataTimesErr) {
		this.labDataTimesErr = labDataTimesErr;
	}
	
	
	
}
