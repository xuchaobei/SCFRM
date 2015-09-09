package cn.gov.scciq.basicSettings.processingWay;

public class ProcessingWayBean {
	 private String methodCode;
     private String methodName;
      private String processingMethodID;
      private String ifSet;
      private String processingMethodSubID;
      private String methodCodeSub;
      
	public ProcessingWayBean() {
		super();
	}
	public String getMethodCode() {
		return methodCode;
	}
	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getProcessingMethodID() {
		return processingMethodID;
	}
	public void setProcessingMethodID(String processingMethodID) {
		this.processingMethodID = processingMethodID;
	}
	
	public String getIfSet() {
		return ifSet;
	}
	public void setIfSet(String ifSet) {
		this.ifSet = ifSet;
	}
	public String getProcessingMethodSubID() {
		return processingMethodSubID;
	}
	public void setProcessingMethodSubID(String processingMethodSubID) {
		this.processingMethodSubID = processingMethodSubID;
	}
	public String getMethodCodeSub() {
		return methodCodeSub;
	}
	public void setMethodCodeSub(String methodCodeSub) {
		this.methodCodeSub = methodCodeSub;
	}
      
}
