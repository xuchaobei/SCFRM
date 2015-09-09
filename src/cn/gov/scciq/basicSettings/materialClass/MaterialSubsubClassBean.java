package cn.gov.scciq.basicSettings.materialClass;

public class MaterialSubsubClassBean {

	/**
	 * @param args
	 */
	private String materialID;
	private String classCode;
	private String subclassCode;
	private String materialCode;
	private String materialName;
	private String ifSet;
	public String getMaterialID() {
		return materialID;
	}
	public void setMaterialID(String materialID) {
		this.materialID = materialID;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getSubclassCode() {
		return subclassCode;
	}
	public void setSubclassCode(String subclassCode) {
		this.subclassCode = subclassCode;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getIfSet() {
		return ifSet;
	}
	public void setIfSet(String ifSet) {
		this.ifSet = ifSet;
	}
	public MaterialSubsubClassBean() {
		super();
	}

}
