package cn.gov.scciq.timer.acceptOrder;

/**
 * 解析后的厂检单数据
 * @author chao.xu
 *
 */
public class CEMSDeclProductDto {

    private String entProductCode;
    private String baseCode;
    private String goodsNo;
    
    
    
    public CEMSDeclProductDto(String entProductCode, String baseCode, String goodsNo) {
        super();
        this.entProductCode = entProductCode;
        this.baseCode = baseCode;
        this.goodsNo = goodsNo;
    }
    public String getEntProductCode() {
        return entProductCode;
    }
    public void setEntProductCode(String entProductCode) {
        this.entProductCode = entProductCode;
    }
    public String getBaseCode() {
        return baseCode;
    }
    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }
    public String getGoodsNo() {
        return goodsNo;
    }
    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }
    
    @Override
    public String toString() {
        return "CEMSDeclProductDto [entProductCode=" + entProductCode + ", baseCode=" + baseCode + ", goodsNo=" + goodsNo + "]";
    }
    
    
}
