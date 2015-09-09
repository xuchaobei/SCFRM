package cn.gov.scciq.bussiness.sampleRegister.lab;

/**
 * 发送到实验室的项目数据
 * 
 * @author chao.xu
 *
 */
public class SendLabSampleItemDto {
	private String lrpItemNo;
	private String lrpItemName;
	private String deptID;
	private String labDeptName;
	
	private String labSampleID;  //需要确认是否有该字段，通过这个字段获取该样品项目属于哪一个样品，
	                             //否则无法生成报文的样品和样品项目的从属关系
	
	public String getLrpItemNo() {
		return lrpItemNo;
	}
	public void setLrpItemNo(String lrpItemNo) {
		this.lrpItemNo = lrpItemNo;
	}
	public String getLrpItemName() {
		return lrpItemName;
	}
	public void setLrpItemName(String lrpItemName) {
		this.lrpItemName = lrpItemName;
	}
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	public String getLabDeptName() {
		return labDeptName;
	}
	public void setLabDeptName(String labDeptName) {
		this.labDeptName = labDeptName;
	}
	public String getLabSampleID() {
		return labSampleID;
	}
	public void setLabSampleID(String labSampleID) {
		this.labSampleID = labSampleID;
	}

	
}
