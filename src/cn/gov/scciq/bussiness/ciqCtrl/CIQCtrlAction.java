package cn.gov.scciq.bussiness.ciqCtrl;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.bussiness.riskCtrl.ConventionCtrlAction;

import com.opensymphony.xwork2.Action;

/**
 * 应急布控
 * @author chao.xu
 *
 */
public class CIQCtrlAction {
    
    private static Log log=LogFactory.getLog(ConventionCtrlAction.class);
    
    private int draw;
    
    private int start;
    
    private int length;
    
    private int recordsTotal;
    
    private int recordsFiltered;
    
    private String data;
    
    private String ciqControlID;

    private String ciqControlConditionID;
    
    private String ciqControlItemID;
    
    private JSONObject result;
    
    
    /**
     * 获取应急布控列表
     * @return
     */
    public String getCIQControl(){
        result = CIQCtrlService.getCIQControl(data, draw, start, length);
        return Action.SUCCESS;
    }
    
    /**
     * 获取应急布控条件
     * @return
     */
    public String getCIQControlCondition(){
        result = CIQCtrlService.getCIQControlCondition(ciqControlID);
        return Action.SUCCESS;
    }
    
    /**
     * 获取应急布控项目
     * @return
     */
    public String getCIQControlItem(){
        result = CIQCtrlService.getCIQControlItem(ciqControlID);
        return Action.SUCCESS;
    }

    /**
     * 应急布控详情
     * @return
     */
    public String getCIQCtrlDetail(){
        result = CIQCtrlService.getCIQCtrlDetailByID(ciqControlID);
        return Action.SUCCESS;
    }
    
    /**
     * 布控项目详情
     * @return
     */
    public String getCIQCtrlItemDetail(){
        result = CIQCtrlService.getCIQCtrlItemDetailByID(ciqControlItemID);
        return Action.SUCCESS;
    }
    
    /**
     * 保存应急布控
     * @return
     */
    public String saveCIQControl(){
        result = CIQCtrlService.saveCIQControl(data);
        return Action.SUCCESS;   
    }

    /**
     * 设置应急布控条件生效或无效
     * @return
     */
    public String updateCIQControlValid(){
        result = CIQCtrlService.updateCIQControlValid(ciqControlID);
        return Action.SUCCESS;
    }
    

    /**
     * 测试应急布控条件是否设置正确
     * @return
     */
    public String checkCIQControlCondition(){
        result = CIQCtrlService.checkCIQControlCondition(ciqControlID);
        return Action.SUCCESS;
    }
    
    public String saveCIQControlCondition(){
        result = CIQCtrlService.saveCIQControlCondition(data);
        return Action.SUCCESS;   
    }
    
    public String saveCIQControlItem(){
        result = CIQCtrlService.saveCIQControlItem(data);
        return Action.SUCCESS;   
    }
    
    public String deleteCIQControl(){
        result = CIQCtrlService.deleteCIQControl(ciqControlID);
        return Action.SUCCESS;
    }
    
    public String deleteCIQControlCondition(){
        result = CIQCtrlService.deleteCIQControlCondition(ciqControlConditionID);
        return Action.SUCCESS;
    }
    
    public String deleteCIQControlItem(){
        result = CIQCtrlService.deleteCIQControlItem(ciqControlItemID);
        return Action.SUCCESS;
    }
    
    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCiqControlID() {
        return ciqControlID;
    }

    public void setCiqControlID(String ciqControlID) {
        this.ciqControlID = ciqControlID;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public String getCiqControlConditionID() {
        return ciqControlConditionID;
    }

    public void setCiqControlConditionID(String ciqControlConditionID) {
        this.ciqControlConditionID = ciqControlConditionID;
    }

    public String getCiqControlItemID() {
        return ciqControlItemID;
    }

    public void setCiqControlItemID(String ciqControlItemID) {
        this.ciqControlItemID = ciqControlItemID;
    }
    
    
    
}
