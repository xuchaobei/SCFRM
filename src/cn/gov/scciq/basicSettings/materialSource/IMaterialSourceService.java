package cn.gov.scciq.basicSettings.materialSource;

public interface IMaterialSourceService {
	
	public String SaveMaterialSource(MaterialSourceBean bean) throws Exception ;
	public String SaveMaterialSourceSub(MaterialSourceBean bean) throws Exception; 
	
	public String DelMaterialSource(MaterialSourceBean bean) throws Exception ;
	
	
	public String Pro_GetMaterialSource(MaterialSourceBean bean) throws Exception ;
	public String Pro_GetMaterialSourceSub(MaterialSourceBean bean) throws Exception ;
	
}
