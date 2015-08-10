package cn.gov.scciq.bussiness.sampleRegister;

/**
 * @author chao.xu
 *
 */
public class DeclInfoReqDto {
	private String declNo;	
	private int finishFlg;
	public String getDeclNo() {
		return declNo;
	}
	public void setDeclNo(String declNo) {
		this.declNo = declNo;
	}
	public int getFinishFlg() {
		return finishFlg;
	}
	public void setFinishFlg(int finishFlg) {
		this.finishFlg = finishFlg;
	}
}
