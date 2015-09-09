package cn.gov.scciq.bussiness.select;

import java.util.List;

import net.sf.json.JSONObject;
import cn.gov.scciq.dto.EvlLevelDto;
import cn.gov.scciq.dto.InspDeptDto;
import cn.gov.scciq.dto.InspOrgDto;
import cn.gov.scciq.dto.MaterialClassDto;
import cn.gov.scciq.dto.MaterialSubclassDto;
import cn.gov.scciq.dto.MaterialSubsubclassDto;
import cn.gov.scciq.dto.ProductClassDto;
import cn.gov.scciq.dto.ProductSubclassDto;
import cn.gov.scciq.util.DefaultResultUtil;

/**
 * 获取所有select集合
 * @author chao.xu
 *
 */
public class SelectDataService {
    

    /**
     * 得到检验机构列表
     * @return
     */
    public static JSONObject getInspOrg(){
        List<InspOrgDto> inpOrgList = SelectDataDao.getInspOrg();
        JSONObject rs = DefaultResultUtil.getDefaultResult(inpOrgList);
        return rs;
    }
    
    /**
     * 得到检验部门列表
     * @return
     */
    public static JSONObject getInspDept(String orgCode){
        List<InspDeptDto> inspDeptList = SelectDataDao.getInspDept(orgCode);
        JSONObject rs = DefaultResultUtil.getDefaultResult(inspDeptList);
        return rs;
    }
    
    /**
     * 得到产品大类类别
     * @return
     */
    public static JSONObject getProductClass(int startIndex, int pageSize, String orderWord, String orderDirection){
        List<ProductClassDto> productClassList = SelectDataDao.getProductClass(0, 0, orderWord, orderDirection);
        JSONObject rs = DefaultResultUtil.getDefaultResult(productClassList);
        return rs;
    }
    

    
    /**
     * 得到产品小类列表
     * @return
     */
    public static JSONObject getProductSubclass(String classCode, int startIndex, int pageSize, String orderWord, String orderDirection){
        List<ProductSubclassDto> productSubclassList = SelectDataDao.getProductSubclass(classCode, 0, 0, orderWord, orderDirection);
        JSONObject rs = DefaultResultUtil.getDefaultResult(productSubclassList);
        return rs;
    }
    
    /**
     * 得到原料大类列表
     * @return
     */
    public static JSONObject getMaterialClass(int startIndex, int pageSize, String orderWord, String orderDirection){
        List<MaterialClassDto> materialClassList = SelectDataDao.getMaterialClass( 0, 0, orderWord, orderDirection);
        JSONObject rs = DefaultResultUtil.getDefaultResult(materialClassList);
        return rs;
    }
    
    /**
     * 得到原料小类列表
     * @return
     */
    public static JSONObject getMaterialSubclass(String classCode, int startIndex, int pageSize, String orderWord, String orderDirection){
        List<MaterialSubclassDto> materialSubclassList = SelectDataDao.getMaterialSubclass( classCode, 0, 0, orderWord, orderDirection);
        JSONObject rs = DefaultResultUtil.getDefaultResult(materialSubclassList);
        return rs;
    }
    
    /**
     * 得到原料细类列表
     * @return
     */
    public static JSONObject getMaterialSubsubclass(String classCode, String subclassCode, int showFlag, int startIndex, int pageSize, String orderWord, String orderDirection){
        List<MaterialSubsubclassDto> materialSubsubList = SelectDataDao.getMaterialSubsubclass( classCode, subclassCode, showFlag, 0, 0, orderWord, orderDirection);
        JSONObject rs = DefaultResultUtil.getDefaultResult(materialSubsubList);
        return rs;
    }
    
    /**
     * 等级类型
     * @param levelType
     * @return
     */
    public static JSONObject getEvlLevel(String levelType){
        List<EvlLevelDto> evlLevelList = SelectDataDao.getEvlLevel(levelType);
        JSONObject rs = DefaultResultUtil.getDefaultResult(evlLevelList);
        return rs;
    }
    
    /**
     * 限量类型
     * @return
     */
    public static JSONObject getLimitType(){
        List<String> limitTypeList = SelectDataDao.getLimitType();
        JSONObject rs = DefaultResultUtil.getDefaultResult(limitTypeList);
        return rs;
    }
    
    /**
     * 布控依据
     * @return
     */
    public static JSONObject getControlReason(){
        List<String> controlReasonList = SelectDataDao.getControlReason();
        JSONObject rs = DefaultResultUtil.getDefaultResult(controlReasonList);
        return rs;
    }
    
    /**
     * 取得逻辑关系定义
     * @return
     */
    public static JSONObject getLogicalDefine(String logicFlg){
        List<String> controlReasonList = SelectDataDao.getLogicalDefine(logicFlg);
        JSONObject rs = DefaultResultUtil.getDefaultResult(controlReasonList);
        return rs;
    }
    
    /**
     * 取得应急布控字段定义
     * @return
     */
    public static JSONObject getCIQControlFieldDefine(){
        List<String> list = SelectDataDao.getCIQControlFieldDefine();
        JSONObject rs = DefaultResultUtil.getDefaultResult(list);
        return rs;
    }
    
    /**
     * 取得逻辑计算定义
     * @return
     */
    public static JSONObject getLogicalOperator(){
        List<String> list = SelectDataDao.getLogicalOperator();
        JSONObject rs = DefaultResultUtil.getDefaultResult(list);
        return rs;
    }
    
    /**
     * 取得应急布控抽检模式定义
     * @return
     */
    public static JSONObject getCIQControlSamplingMode(){
        List<String> list = SelectDataDao.getCIQControlSamplingMode();
        JSONObject rs = DefaultResultUtil.getDefaultResult(list);
        return rs;
    }

	/**
	 * 进口批查询中，获取关系符
	 * @return
	 * 
	 */
	public static JSONObject getDeclQueryLogic(int logicSignal) {
		List<String> list = SelectDataDao.getDeclQueryLogic(logicSignal);
	    JSONObject rs = DefaultResultUtil.getDefaultResult(list);
		return rs;
	}

	public static JSONObject getDeclQueryDefinedField() {
		List<String> list = SelectDataDao.getDeclQueryDefinedField();
	    JSONObject rs = DefaultResultUtil.getDefaultResult(list);
		return rs;
	}

	public static JSONObject getDeclQueryOperateSignal() {
		List<String> list = SelectDataDao.getDeclQueryOperateSignal();
	    JSONObject rs = DefaultResultUtil.getDefaultResult(list);
		return rs;
	}
	
	public static JSONObject getLabApplyKind() {
		List<String> list = SelectDataDao.getLabApplyKind();
	    JSONObject rs = DefaultResultUtil.getDefaultResult(list);
		return rs;
	}
	
	public static JSONObject getLabSamplePhysicalState() {
		List<String> list = SelectDataDao.getLabSamplePhysicalState();
	    JSONObject rs = DefaultResultUtil.getDefaultResult(list);
		return rs;
	}
	
	
	public static JSONObject getLabSampleDisposal() {
		List<String> list = SelectDataDao.getLabSampleDisposal();
	    JSONObject rs = DefaultResultUtil.getDefaultResult(list);
		return rs;
	}
	
	public static JSONObject getLabSamplePreservation() {
		List<String> list = SelectDataDao.getLabSamplePreservation();
	    JSONObject rs = DefaultResultUtil.getDefaultResult(list);
		return rs;
	}
    
}
