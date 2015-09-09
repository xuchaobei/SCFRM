package cn.gov.scciq.bussiness.entProduct;

import java.util.List;

public class EntProductDetailDto {

	private EntProductBaseDto entProductBase;
	
	private List<EntProductMaterialDto> entProductMaterialList;
	
	private List<EntProductAccessoryDto> entProductAccessoryDtoList;
	
	private List<EntProductAdditiveDto> entProductAdditiveDtoList;
	private List<EntProductReleaseModeDto> entProductReleaseModeDto;

	public EntProductBaseDto getEntProductBase() {
		return entProductBase;
	}

	public void setEntProductBase(EntProductBaseDto entProductBase) {
		this.entProductBase = entProductBase;
	}

	public List<EntProductMaterialDto> getEntProductMaterialList() {
		return entProductMaterialList;
	}

	public void setEntProductMaterialList(
			List<EntProductMaterialDto> entProductMaterialList) {
		this.entProductMaterialList = entProductMaterialList;
	}

	public List<EntProductAccessoryDto> getEntProductAccessoryDtoList() {
		return entProductAccessoryDtoList;
	}

	public void setEntProductAccessoryDtoList(
			List<EntProductAccessoryDto> entProductAccessoryDtoList) {
		this.entProductAccessoryDtoList = entProductAccessoryDtoList;
	}

	public List<EntProductAdditiveDto> getEntProductAdditiveDtoList() {
		return entProductAdditiveDtoList;
	}

	public void setEntProductAdditiveDtoList(
			List<EntProductAdditiveDto> entProductAdditiveDtoList) {
		this.entProductAdditiveDtoList = entProductAdditiveDtoList;
	}

	public List<EntProductReleaseModeDto> getEntProductReleaseModeDto() {
		return entProductReleaseModeDto;
	}

	public void setEntProductReleaseModeDto(
			List<EntProductReleaseModeDto> entProductReleaseModeDto) {
		this.entProductReleaseModeDto = entProductReleaseModeDto;
	}
	
	
}
