package cn.gov.scciq.dto;

/**
 * 原料来源
 * @author chao.xu
 *
 */
public class MaterialSourceDto {
    
    private String sourceCode;
    
    private String sourceName;
    
    private String ifSet;

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getIfSet() {
        return ifSet;
    }

    public void setIfSet(String ifSet) {
        this.ifSet = ifSet;
    }
    
    
}
