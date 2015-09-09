package cn.gov.scciq.basicSettings.storageCondition;

public interface IStorageConditionService {
	public String     DelStorageCondition (StorageConditionBean bean) throws Exception;
	public String     SaveStorageCondition (StorageConditionBean bean) throws Exception;
	public String     GetStorageCondition (StorageConditionBean bean) throws Exception;

}
