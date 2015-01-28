package cn.gov.scciq.bussiness.productCtrlItem;

import java.util.List;

public class ProductDetailAllDto {

	private ProductDetailDto productDetail;
	
	private List<ProductMatDetailDto> materialDetailList;

	public ProductDetailDto getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetailDto productDetail) {
		this.productDetail = productDetail;
	}

	public List<ProductMatDetailDto> getMaterialDetailList() {
		return materialDetailList;
	}

	public void setMaterialDetailList(List<ProductMatDetailDto> materialDetailList) {
		this.materialDetailList = materialDetailList;
	}


	
}
