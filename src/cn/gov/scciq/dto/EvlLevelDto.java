package cn.gov.scciq.dto;

/**
 * 风险危害程度、输入国反应等选择项的 等级类型
 * @author chao.xu
 *
 */
public class EvlLevelDto {

    private String levelType;
    
    private String levelDesc;
    
    private String definedLevel;
    
    private String remarks;
    
    private String weight;

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public String getLevelDesc() {
        return levelDesc;
    }

    public void setLevelDesc(String levelDesc) {
        this.levelDesc = levelDesc;
    }

    public String getDefinedLevel() {
        return definedLevel;
    }

    public void setDefinedLevel(String definedLevel) {
        this.definedLevel = definedLevel;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    
    
    
}
