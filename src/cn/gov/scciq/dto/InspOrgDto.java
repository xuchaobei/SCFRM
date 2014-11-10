package cn.gov.scciq.dto;

/**
 * 检验机构
 * 
 * @author chao.xu
 *
 */
public class InspOrgDto {

    private String orgCode;
    
    private String orgName;
    
    private String domRegion;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getDomRegion() {
        return domRegion;
    }

    public void setDomRegion(String domRegion) {
        this.domRegion = domRegion;
    }
    
    
}
