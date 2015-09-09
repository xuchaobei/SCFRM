package cn.gov.scciq.basicSettings.materialClass;



public interface IMaterialClassService {
	public String SaveMaterialClass(MaterialClassBean bean) throws Exception ;
	public String SaveMaterialSubclass(MaterialClassBean bean) throws Exception ;
	public String SaveMaterialSubsubclass(MaterialClassBean bean) throws Exception; 
    public String SaveMaterialSubsubclassSub(MaterialClassBean bean) throws Exception;
    
	public String DelMaterialClass(MaterialClassBean bean) throws Exception ;
	public String DelMaterialSubclass(MaterialClassBean bean) throws Exception ;
	public String DelMaterialSubsubclass(MaterialClassBean bean) throws Exception ;
	
	
	//public String Pro_GetMaterialClass(MaterialSourceBean bean) throws Exception ;
	//public String Pro_GetMaterialSubclass(MaterialSourceBean bean) throws Exception ;
	//public String Pro_GetMaterialSubsubclass(MaterialSourceBean bean) throws Exception ;
	//public String Pro_GetMaterialSubsubclassSub(MaterialSourceBean bean) throws Exception ;


}
