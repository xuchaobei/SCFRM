package cn.gov.scciq.bussiness.foreignReport;

public class ForeignReportingItemReqDto {
    private String reportingItemID;
    private String foreignReportingID;
    private String itemCode;
    public String getReportingItemID() {
        return reportingItemID;
    }
    public void setReportingItemID(String reportingItemID) {
        this.reportingItemID = reportingItemID;
    }
    public String getForeignReportingID() {
        return foreignReportingID;
    }
    public void setForeignReportingID(String foreignReportingID) {
        this.foreignReportingID = foreignReportingID;
    }
    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    
    
}
