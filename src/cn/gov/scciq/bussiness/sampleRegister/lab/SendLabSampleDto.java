package cn.gov.scciq.bussiness.sampleRegister.lab;

/**
 * 发送到实验室的样品数据
 * 
 * @author chao.xu
 *
 */
public class SendLabSampleDto {
	private String sampleName;
	private String sampleMarked;
	private String sampleCount;
	private String labSampleID;
	private String sampleRemarks;
	
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getSampleMarked() {
		return sampleMarked;
	}
	public void setSampleMarked(String sampleMarked) {
		this.sampleMarked = sampleMarked;
	}
	public String getSampleCount() {
		return sampleCount;
	}
	public void setSampleCount(String sampleCount) {
		this.sampleCount = sampleCount;
	}
	public String getLabSampleID() {
		return labSampleID;
	}
	public void setLabSampleID(String labSampleID) {
		this.labSampleID = labSampleID;
	}
	public String getSampleRemarks() {
		return sampleRemarks;
	}
	public void setSampleRemarks(String sampleRemarks) {
		this.sampleRemarks = sampleRemarks;
	}

	
}
