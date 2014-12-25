package cn.gov.scciq.bussiness.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    
    private static String[] arr = {"0.20","","","0.40"};
    private static List<String> list = Arrays.asList(arr);
    
    public static void sortDatas() {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // TODO Auto-generated method stub
                if("".equals(o1)){
                    return 1;
                }
                if("".equals(o2)){
                    return -1;
                }
                int result = 0;
                try {
                    result = Double.valueOf((String) o2.replace(",", "")).compareTo(Double.valueOf((String) o1.replace(",", "")));
                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return result;
            }
        });
        
        for(String s : list){
            System.out.println(s+" ");
        }
    }

    
    
    public static void main(String[] args) {
        sortDatas();
    }
    
    
    
}


    
    
