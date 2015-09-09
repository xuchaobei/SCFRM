package cn.gov.scciq.basicSettings.packageType;



public interface IPackageTypeService {
	public String SavePackageType(PackageTypeBean bean) throws Exception ;
	public String SavePackageTypeSub(PackageTypeBean bean) throws Exception ;
	public String DelPackageType(PackageTypeBean bean) throws Exception ;
	public String GetPackageType(PackageTypeBean bean) throws Exception ;
	public String GetPackageTypeSub(PackageTypeSubBean bean) throws Exception ;
}
