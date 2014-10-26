package cn.gov.scciq.bussiness.example;

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
}


    
    
