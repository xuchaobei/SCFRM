package cn.gov.scciq.basicSettings.accessorySet;

public interface IAccessorySetService {
	public String SaveAccessory(AccessorySetBean bean)throws Exception;
	public String DelAccessory(AccessorySetBean bean)throws Exception;
	public String GetAccessory(AccessorySetBean bean)throws Exception;

}
