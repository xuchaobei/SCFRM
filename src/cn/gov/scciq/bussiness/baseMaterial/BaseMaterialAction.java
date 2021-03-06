package cn.gov.scciq.bussiness.baseMaterial;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

/**
 * 基地放行
 * 
 * @author chao.xu
 *
 */
public class BaseMaterialAction {
	private static Log log=LogFactory.getLog(BaseMaterialAction.class);
    
	private int draw;
    
    private int start;
    
    private int length;
    
    private String data;
    
    private String baseMaterialID;
    
    private String baseMaterialItemID;
    private String materialReleaseID;
    private String materialReleaseItemID;
    private JSONObject result;
    
    
    public String getBaseMaterialList(){
        try {
            result = BaseMaterialService.getBaseMaterialList(data, draw, start, length);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    public String getMaterialReleaseList(){
        try {
            result = BaseMaterialService.getMaterialReleaseList(data, draw, start, length);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    public String getMaterialReleaseItem(){
         try {
             result = BaseMaterialService.getMaterialReleaseItem(materialReleaseID);
         } catch (Exception e) {
             // TODO Auto-generated catch block
             log.error("", e);
         }
         return Action.SUCCESS;
     }
    public String delMaterialRelease(){
         try {
             result = BaseMaterialService.delMaterialRelease(materialReleaseID);
             System.out.println("delmr "+result);
         } catch (Exception e) {
             // TODO Auto-generated catch block
             log.error("", e);
         }
         return Action.SUCCESS;
     }
    public String saveMaterialRelease(){
         try {
             result = BaseMaterialService.saveMaterialRelease(data);
             System.out.println("re "+result);
         } catch (Exception e) {
             // TODO Auto-generated catch block
             log.error("", e);
         }
         return Action.SUCCESS;
     }
    public String getMaterialReleaseByID(){
         try {
           result = BaseMaterialService.getMaterialReleaseByID(materialReleaseID);
           log.info(" mrid "+result);
         } catch (Exception e) {
             // TODO Auto-generated catch block
             log.error("", e);
         }
         return Action.SUCCESS;
     }
    public String saveMaterialReleaseItem(){
         try {
             result = BaseMaterialService.saveMaterialReleaseItem(data);
         } catch (Exception e) {
             // TODO Auto-generated catch block
             log.error("", e);
         }
         return Action.SUCCESS;
     }
    public String delMaterialReleaseItem(){
         try {
             result = BaseMaterialService.delMaterialReleaseItem(materialReleaseItemID, materialReleaseID);
         } catch (Exception e) {
             // TODO Auto-generated catch block
             log.error("", e);
         }
         return Action.SUCCESS;
     }
    
    public String getBaseMaterialItemByID(){
        try {
            result = BaseMaterialService.getBaseMaterialItemByID(baseMaterialID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String saveBaseMaterial(){
        try {
            result = BaseMaterialService.saveBaseMaterial(data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }

    
    public String delBaseMaterial(){
        try {
            result = BaseMaterialService.delBaseMaterial(baseMaterialID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String delBaseMaterialItem(){
        try {
            result = BaseMaterialService.delBaseMaterialItem(baseMaterialItemID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String getBaseMaterialDetailByID(){
        try {
            result = BaseMaterialService.getBaseMaterialDetailByID(baseMaterialID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("", e);
        }
        return Action.SUCCESS;
    }
    
    public String saveBaseMaterialItem(){
        try {
            result = BaseMaterialService.saveBaseMaterialItem(data);
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

  	public String getBaseMaterialID() {
  		return baseMaterialID;
  	}

  	public void setBaseMaterialID(String baseMaterialID) {
  		this.baseMaterialID = baseMaterialID;
  	}

  	public String getBaseMaterialItemID() {
  		return baseMaterialItemID;
  	}

  	public void setBaseMaterialItemID(String baseMaterialItemID) {
  		this.baseMaterialItemID = baseMaterialItemID;
  	}

  	public JSONObject getResult() {
  		return result;
  	}

  	public void setResult(JSONObject result) {
  		this.result = result;
  	}
	public String getMaterialReleaseID() {
		return materialReleaseID;
	}
	public void setMaterialReleaseID(String materialReleaseID) {
		this.materialReleaseID = materialReleaseID;
	}
	public String getMaterialReleaseItemID() {
		return materialReleaseItemID;
	}
	public void setMaterialReleaseItemID(String materialReleaseItemID) {
		this.materialReleaseItemID = materialReleaseItemID;
	}

}
