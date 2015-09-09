package cn.gov.scciq.basicSettings.processingDegree;



public interface IProcessingDegreeService {
	public String SaveProcessingDegree(ProcessingDegreeBean bean) throws Exception ;
	public String GetProcessingDegree(ProcessingDegreeBean bean) throws Exception ;
    public String DelProcessingDegree(ProcessingDegreeBean bean) throws Exception ;

}
