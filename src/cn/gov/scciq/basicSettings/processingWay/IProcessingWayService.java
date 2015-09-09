package cn.gov.scciq.basicSettings.processingWay;



public interface IProcessingWayService {
	public abstract String SaveProcessingMethod(ProcessingWayBean bean) throws Exception;
	public abstract String SaveProcessingMethodSub(ProcessingWayBean bean,String[] arr) throws Exception;
	//public abstract String GetProcessingMethod(ProcessingWayBean bean) throws Exception;
	//public abstract String GetProcessingMethodSub(ProcessingWayBean bean) throws Exception;
	public abstract String DelProcessingMethod(ProcessingWayBean bean) throws Exception;


}
