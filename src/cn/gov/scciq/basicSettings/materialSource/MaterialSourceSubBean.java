package cn.gov.scciq.basicSettings.materialSource;

public class MaterialSourceSubBean {
	private String materialSourceSubID;
	private String sourceCode;
	private String sourceName;
	private String sourceCodeSub;
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
	public MaterialSourceSubBean() {
		super();
	}
	public String getMaterialSourceSubID() {
		return materialSourceSubID;
	}
	public void setMaterialSourceSubID(String materialSourceSubID) {
		this.materialSourceSubID = materialSourceSubID;
	}
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public String getSourceCodeSub() {
		return sourceCodeSub;
	}
	public void setSourceCodeSub(String sourceCodeSub) {
		this.sourceCodeSub = sourceCodeSub;
	}
}
