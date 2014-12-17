package cn.gov.scciq.bussiness.ciqCtrl;

/**
 * 应急布控规则/条件
 * 
 * @author chao.xu
 * 
 */
public class CIQCtrlConditionDto {

    private String ciqControlConditionID;
    
    private String ciqControlID;

    private String orderNo;

    private String leftLogic;

    private String definedField;
    //计算符
    private String calculationDesc;

    private String keywords;

    private String rightLogic;

    private String keywordsDesc;

    
    
    public String getCiqControlID() {
        return ciqControlID;
    }

    public void setCiqControlID(String ciqControlID) {
        this.ciqControlID = ciqControlID;
    }

    public String getCiqControlConditionID() {
        return ciqControlConditionID;
    }

    public void setCiqControlConditionID(String ciqControlConditionID) {
        this.ciqControlConditionID = ciqControlConditionID;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getLeftLogic() {
        return leftLogic;
    }

    public void setLeftLogic(String leftLogic) {
        this.leftLogic = leftLogic;
    }

    public String getDefinedField() {
        return definedField;
    }

    public void setDefinedField(String definedField) {
        this.definedField = definedField;
    }

    public String getCalculationDesc() {
        return calculationDesc;
    }

    public void setCalculationDesc(String calculationDesc) {
        this.calculationDesc = calculationDesc;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getRightLogic() {
        return rightLogic;
    }

    public void setRightLogic(String rightLogic) {
        this.rightLogic = rightLogic;
    }

    public String getKeywordsDesc() {
        return keywordsDesc;
    }

    public void setKeywordsDesc(String keywordsDesc) {
        this.keywordsDesc = keywordsDesc;
    }
}
