package cn.gov.scciq.businessProgress.checkorReceive;

public class DeclProductItemInputBean {
	private String declProductDetailID;
	private String showSamplingItemFlg;
	private String showNotLabFlg;
	public String getDeclProductDetailID() {
		return declProductDetailID;
	}
	public void setDeclProductDetailID(String declProductDetailID) {
		this.declProductDetailID = declProductDetailID;
	}
	public String getShowSamplingItemFlg() {
		return showSamplingItemFlg;
	}
	public void setShowSamplingItemFlg(String showSamplingItemFlg) {
		this.showSamplingItemFlg = showSamplingItemFlg;
	}
	public String getShowNotLabFlg() {
		return showNotLabFlg;
	}
	public void setShowNotLabFlg(String showNotLabFlg) {
		this.showNotLabFlg = showNotLabFlg;
	}
	public DeclProductItemInputBean() {
		super();
	}
	
}
