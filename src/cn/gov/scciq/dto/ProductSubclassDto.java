package cn.gov.scciq.dto;

/**
 * 产品小类
 * 
 * @author chao.xu
 * add pram productSubclassID by jun
 *
 */
public class ProductSubclassDto {
    
    private String subclassCode;
    
    private String subclassName;
    
    private String classCode;
    private String productSubclassID;

    public String getProductSubclassID() {
		return productSubclassID;
	}

	public void setProductSubclassID(String productSubclassID) {
		this.productSubclassID = productSubclassID;
	}

	public String getSubclassCode() {
        return subclassCode;
    }

    public void setSubclassCode(String subclassCode) {
        this.subclassCode = subclassCode;
    }

    public String getSubclassName() {
        return subclassName;
    }

    public void setSubclassName(String subclassName) {
        this.subclassName = subclassName;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
    
    
}
