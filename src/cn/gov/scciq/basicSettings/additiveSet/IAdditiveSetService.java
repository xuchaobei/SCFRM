package cn.gov.scciq.basicSettings.additiveSet;

public interface IAdditiveSetService {
	public String SaveAdditive(AdditiveSetBean bean)throws Exception;
	public String DelAdditive(AdditiveSetBean bean)throws Exception;
	public String GetAdditive(AdditiveSetBean bean)throws Exception;
 
}
