package cn.gov.scciq.bussiness.foreignReport;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

/**
 * 国外通报登记
 * 
 * @author chao.xu
 *
 */
public class ForeignReportAction {

    private static final Log log = LogFactory.getLog(ForeignReportAction.class);
    
    private int draw;

    private int start;

    private int length;

    private String data;

    private String foreignReportingID;

    private String reportingItemID;

    private JSONObject result;
    
    public String getForeignReportList(){
        try {
            result = ForeignReportService.getForeignReportList(data, draw, start, length);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);;
        }
        return Action.SUCCESS;
    }
    
    public String getForeignReportingItem(){
        try {
            result = ForeignReportService.getForeignReportingItem(foreignReportingID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);;
        }
        return Action.SUCCESS;
    }
    
    public String delForeignReporting(){
        try {
            result = ForeignReportService.delForeignReporting(foreignReportingID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);;
        }
        return Action.SUCCESS;
    }
    
    public String getForeignReportingDetailByID(){
        try {
            result = ForeignReportService.getForeignReportingDetailByID(foreignReportingID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);;
        }
        return Action.SUCCESS;
    }
    
    public String saveForeignReporting(){
        try {
            result = ForeignReportService.saveForeignReporting(data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);;
        }
        return Action.SUCCESS;
    }
    
    public String saveForeignReportingItem(){
        try {
            result = ForeignReportService.saveForeignReportingItem(data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);;
        }
        return Action.SUCCESS;
    }
    
    public String delForeignReportingItem(){
        try {
            result = ForeignReportService.delForeignReportingItem(reportingItemID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getForeignReportingID() {
        return foreignReportingID;
    }

    public void setForeignReportingID(String foreignReportingID) {
        this.foreignReportingID = foreignReportingID;
    }

    public String getReportingItemID() {
        return reportingItemID;
    }

    public void setReportingItemID(String reportingItemID) {
        this.reportingItemID = reportingItemID;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }
    
    
}
