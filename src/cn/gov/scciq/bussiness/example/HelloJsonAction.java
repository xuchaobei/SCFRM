package cn.gov.scciq.bussiness.example;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;

public class HelloJsonAction{
    
    private static Log log=LogFactory.getLog(HelloJsonAction.class);
    
    private int id;
    
    private String name;
    
    private String data;
    
    private JSONObject result;
    
    private HelloJsonService jsonService = new HelloJsonService();
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    
    public String submit(){
        System.out.println("获取表单post提交数据：id-"+id+" name-"+name);
        return Action.SUCCESS;
    }
    
    
    public String normalSubmit(){
        log.debug("获取post提交数据：id-"+id+" name-"+name);
        result = jsonService.handleSubmit(id, name);
        return Action.SUCCESS;
    }
    
    public String normalSubmit2(){
        log.debug("获取post提交数据：data-"+data);
        result = jsonService.handleSubmit2(data);        
        return Action.SUCCESS;
    }
    
    public String getJson() throws IOException{
        result = jsonService.handleGetJsonFromFile();
        return Action.SUCCESS;   
    }
    
    public static void main(String[] args) {
        new HelloJsonAction().submit();
    }
}


    
    
