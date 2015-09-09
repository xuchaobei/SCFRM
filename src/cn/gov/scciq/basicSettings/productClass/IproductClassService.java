package cn.gov.scciq.basicSettings.productClass;

public interface IproductClassService {
	public abstract String saveProductClass(ProductClassBean bean) throws Exception;
	public abstract String deleteProductClass(ProductClassBean bean) throws Exception;
	public abstract String getProductClass(ProductClassBean bean) throws Exception;
	public abstract String saveSubProductClass(ProductClassBean bean)throws Exception;;
	public abstract String deleteSubProductClass(ProductClassBean bean)throws Exception;;
	public abstract String getSubProductClass(ProductClassBean bean) throws Exception;
}
