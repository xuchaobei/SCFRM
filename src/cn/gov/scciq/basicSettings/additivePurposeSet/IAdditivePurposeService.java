package cn.gov.scciq.basicSettings.additivePurposeSet;

public interface IAdditivePurposeService {
	public String SaveAdditivePurpose(AdditivePurposeBean bean) throws Exception;
	public String DelAdditivePurpose(AdditivePurposeBean bean) throws Exception;
	public String GetAdditivePurpose(AdditivePurposeBean bean) throws Exception;

}
