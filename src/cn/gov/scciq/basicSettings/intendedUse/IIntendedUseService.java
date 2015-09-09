package cn.gov.scciq.basicSettings.intendedUse;



public interface IIntendedUseService {
	public String SaveIntendedUse(IntendedUseBean bean) throws Exception ;
	public String SaveIntendedUseSub(IntendedUseBean bean) throws Exception ;
	public String DelIntendedUse(IntendedUseBean bean) throws Exception ;
	public String GetIntendedUse(IntendedUseBean bean) throws Exception ;
	public String GetIntendedUseSub(IntendUseSubBean bean) throws Exception ;
}
