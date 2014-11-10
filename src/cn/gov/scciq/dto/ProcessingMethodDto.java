package cn.gov.scciq.dto;

/**
 * 加工方式
 * @author chao.xu
 *
 */
public class ProcessingMethodDto {
    private String methodCode;
    
    private String methodName;
    
    private int ifSet;

    public String getMethodCode() {
        return methodCode;
    }

    public void setMethodCode(String methodCode) {
        this.methodCode = methodCode;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getIfSet() {
        return ifSet;
    }

    public void setIfSet(int ifSet) {
        this.ifSet = ifSet;
    }

   
    
}
