package cn.gov.scciq.basicSettings.item;

public class RiskClassBean {
	 private String riskClassID;
     private String classCode;
     private String className;
     public String getRiskClassID() {
 		return riskClassID;
 	}
 	public void setRiskClassID(String riskClassID) {
 		this.riskClassID = riskClassID;
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
	public RiskClassBean() {
		super();
	}
}
