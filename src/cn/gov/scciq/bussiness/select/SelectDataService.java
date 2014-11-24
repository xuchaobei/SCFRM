package cn.gov.scciq.bussiness.select;

import java.util.List;

import net.sf.json.JSONObject;
import cn.gov.scciq.dto.EvlLevelDto;
import cn.gov.scciq.dto.InspDeptDto;
import cn.gov.scciq.dto.InspOrgDto;
import cn.gov.scciq.dto.LimitTypeDto;
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
        List<LimitTypeDto> limitTypeList = SelectDataDao.getLimitType();
        JSONObject rs = DefaultResultUtil.getDefaultResult(limitTypeList);
        return rs;
    }
    
    
}
