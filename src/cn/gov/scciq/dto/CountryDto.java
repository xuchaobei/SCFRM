package cn.gov.scciq.dto;

/**
 * 国家或地区
 * @author chao.xu
 *
 */
public class CountryDto {

    private String countryCode;
    
    private String countryName;
    
    private int ifSet;



    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getIfSet() {
        return ifSet;
    }

    public void setIfSet(int ifSet) {
        this.ifSet = ifSet;
    }
    
    
}
