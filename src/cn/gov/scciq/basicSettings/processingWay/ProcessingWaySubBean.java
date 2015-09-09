package cn.gov.scciq.basicSettings.processingWay;

public class ProcessingWaySubBean {

	/**
	 * @param args
	 */
	private String methodCode;
    private String methodName;
    private String methodCodeSub;
    private String processingMethodSubID;
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
	public String getMethodCodeSub() {
		return methodCodeSub;
	}
	public void setMethodCodeSub(String methodCodeSub) {
		this.methodCodeSub = methodCodeSub;
	}
	public String getProcessingMethodSubID() {
		return processingMethodSubID;
	}
	public void setProcessingMethodSubID(String processingMethodSubID) {
		this.processingMethodSubID = processingMethodSubID;
	}
	public ProcessingWaySubBean() {
		super();
	}
}
