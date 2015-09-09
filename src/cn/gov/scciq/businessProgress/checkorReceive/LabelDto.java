package cn.gov.scciq.businessProgress.checkorReceive;

import java.util.HashMap;

public class LabelDto {
	private String label;
	private String code;
	private HashMap<String, String> labelData;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public HashMap<String, String> getLabelData() {
		return labelData;
	}

	public void setLabelData(HashMap<String, String> labelData) {
		this.labelData = labelData;
	}



}
