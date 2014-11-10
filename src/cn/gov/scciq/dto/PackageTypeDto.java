package cn.gov.scciq.dto;

/**
 * 包装类型
 * @author chao.xu
 *
 */
public class PackageTypeDto {

    private String typeCode;
    
    private String typeName;
    
    private int ifSet;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getIfSet() {
        return ifSet;
    }

    public void setIfSet(int ifSet) {
        this.ifSet = ifSet;
    }


}
