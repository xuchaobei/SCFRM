package cn.gov.scciq.bussiness.example;

import net.sf.json.JSONArray;

public class UserBean{
    private int id;
    private String name = "attr";
    
    private JSONArray limitType;
  
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String attr) {
        this.name = attr;
    }
    public JSONArray getLimitType() {
        return limitType;
    }
    public void setLimitType(JSONArray limitType) {
        this.limitType = limitType;
    }

    
    
    
}
