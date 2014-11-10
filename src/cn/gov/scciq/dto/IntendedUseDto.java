package cn.gov.scciq.dto;

/**
 * 预期用途
 * @author chao.xu
 *
 */
public class IntendedUseDto {

    private String useCode;
    
    private String useName;
    
    private int ifSet;

    public String getUseCode() {
        return useCode;
    }

    public void setUseCode(String useCode) {
        this.useCode = useCode;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public int getIfSet() {
        return ifSet;
    }

    public void setIfSet(int ifSet) {
        this.ifSet = ifSet;
    }
    
    
}
