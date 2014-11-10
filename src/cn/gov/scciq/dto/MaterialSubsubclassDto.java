package cn.gov.scciq.dto;

/**
 * 原料细类
 * 
 * @author chao.xu
 *
 */
public class MaterialSubsubclassDto {
    
    private String materialCode;
    
    private String materialName;
    
    private String classCode;

    private String subclassCode;
    
    private int ifSet;
    
    public String getSubclassCode() {
        return subclassCode;
    }

    public void setSubclassCode(String subclassCode) {
        this.subclassCode = subclassCode;
    }


    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
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

    public int getIfSet() {
        return ifSet;
    }

    public void setIfSet(int ifSet) {
        this.ifSet = ifSet;
    }
    
    
}
