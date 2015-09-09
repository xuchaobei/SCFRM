package cn.gov.scciq.basicSettings.productClass;

public class ProductClassBean {
	private String  productClassID;
	private String  classCode;
	private String  className;
	private String  productSubclassID;
	private String  subclassCode;
	private String  subclassName;
	private String  serialRank;
    private String  rowIndex ;
    private String  pageSize;
	 
	 public String getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(String rowIndex) {
		this.rowIndex = rowIndex;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
	public ProductClassBean() {
		super();
	}
	public ProductClassBean(String productClassID, String classCode,
			String className) {
		super();
		this.productClassID = productClassID;
		this.classCode = classCode;
		this.className = className;
	}
	public String getSerialRank() {
		return serialRank;
	}
	public void setSerialRank(String serialRank) {
		this.serialRank = serialRank;
	}
	public String getProductClassID() {
		return productClassID;
	}
	public void setProductClassID(String productClassID) {
		this.productClassID = productClassID;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getProductSubclassID() {
		return productSubclassID;
	}
	public void setProductSubclassID(String productSubclassID) {
		this.productSubclassID = productSubclassID;
	}
	public String getSubclassCode() {
		return subclassCode;
	}
	public void setSubclassCode(String subclassCode) {
		this.subclassCode = subclassCode;
	}
	public String getSubclassName() {
		return subclassName;
	}
	public void setSubclassName(String subclassName) {
		this.subclassName = subclassName;
	}
	

}
