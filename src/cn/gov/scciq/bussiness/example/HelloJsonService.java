package cn.gov.scciq.bussiness.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HelloJsonService{
    
    private static Log log=LogFactory.getLog(HelloJsonService.class);
    
    public JSONObject handleSubmit(int id, String name){
        UserBean bean = new UserBean();
        bean.setId(id);
        bean.setName(name);
        log.debug("获取post提交数据：id-"+id+" name-"+name);
        return JSONObject.fromObject(bean);
    }
    
    public JSONObject handleSubmit2(String data){   
    	
        JSONObject jo = JSONObject.fromObject(data);
        UserBean user = (UserBean)JSONObject.toBean(jo, UserBean.class);
        return JSONObject.fromObject(user);
    }
    
    public JSONObject handleGetJson(){
        UserBean bean = new UserBean();
        bean.setId(123);
        bean.setName("张三");
        String str = "{\"id\":\"123\",\"name\":\"aaa\"}";
        JSONObject jo = JSONObject.fromObject(bean);
        JSONArray ja = JSONArray.fromObject(bean);
        log.info(ja.toString());
        return jo;
    }
    
    public JSONObject handleGetJsonFromFile() throws IOException{
        File file = new File("D://data.json");
        FileReader reader = new FileReader(file);
        BufferedReader bre=new BufferedReader(reader);
        String str = "";
        StringBuilder builder = new StringBuilder();       
        while((str=bre.readLine())!=null)   //●判断最后一行不存在，为空
        {
            builder.append(str);
        }
        bre.close();
        reader.close();
        
        JSONObject jo = JSONObject.fromObject(builder.toString());
        log.info(jo.toString());
        return jo;
    }
    
    public static void main(String[] args) {
        String str = "{\"id\":\"123\",\"name\":\"aaa\",\"limitType\":[{\"type\":\"3\", \"limit\":\"bcd\"},{\"type\":\"4\", \"limit\":\"bgf\"}]}";
        String str2 = "{\"id\":\"123\",\"name\":\"aaa\",\"limitType\":[]}";
        JSONObject jo = JSONObject.fromObject(str2);
        log.info(jo.toString());
        
        UserBean bean = (UserBean)JSONObject.toBean(jo, UserBean.class);
        
        LimitType[] arr =(LimitType[]) JSONArray.toArray(JSONArray.fromObject(jo.get("limitType")), LimitType.class);
        log.info(arr);
        
        LimitType[] arr2 =(LimitType[]) JSONArray.toArray(JSONArray.fromObject(bean.getLimitType()), LimitType.class);
        log.info(arr);

    }
}


    
    
