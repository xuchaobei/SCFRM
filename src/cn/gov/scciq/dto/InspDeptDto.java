package cn.gov.scciq.dto;

/**
 * 检验部门
 * @author chao.xu
 *
 */
public class InspDeptDto {
    private String orgCode;
    
    private String deptCode;
    
    private String deptName;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    
    
}
