package cn.gov.scciq.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.timer.acceptOrder.CEMSTask;
import cn.gov.scciq.timer.lmis.BaseDataTask;
import cn.gov.scciq.timer.lmis.TestDataTask;

public class FRMTimerServlet extends HttpServlet{

    /**  */
    
    private static final long serialVersionUID = 1L;
    private static Log log = LogFactory.getLog(FRMTimerServlet.class);
    //读取报检单定时器
    private Timer cemsTimer = null;
    //LRP定时器
    private Timer lrpTimer = null;
    
    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        String startCEMSTask = getInitParameter("startCEMSTask");             
        String startLRPTask = getInitParameter("startLRPTask");             
        // 启动定时器   
        if(startCEMSTask.equals("true")){          
            log.info("cemsTimer定时器启动");
            cemsTimer = new Timer(true);               //true表示timer为后台线程(daemon)            
            TimerTask cemsTask=new CEMSTask();
            cemsTimer.schedule(cemsTask, 0, 10*60*1000);
        }
        if(startLRPTask.equals("true")){   
        	//获得当天的日期
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
    		Date date = new Date();
    		//定义读取基础数据开始时间字符串
    		String timeStr = "1:00:00"; 
    		timeStr = sdf.format(date)+timeStr;
    		//获得当天的指定时间的date对象
    		sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		try {  
    			date = sdf.parse(timeStr);
            } catch (ParseException e) {
    		// TODO Auto-generated catch block
            	log.error(e);
            }		
    		//判断今天的执行时间是否已经过去，如果过去则改为明天
    		if(date.getTime()<System.currentTimeMillis()){
    		    date = new Date(date.getTime()+24*60*60*1000);
    		}
        	
            log.info("startLRPTask定时器启动");
            lrpTimer = new Timer(true);               //true表示timer为后台线程(daemon)            
            TimerTask testDataTask=new TestDataTask();
            TimerTask baseDataTask=new BaseDataTask();
            lrpTimer.schedule(testDataTask, date, 24*60*60*1000);
            lrpTimer.schedule(baseDataTask, 0, 30*60*1000);
        }   
    }
    
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        super.destroy();    
        if(cemsTimer != null){   
            cemsTimer.cancel();   
            log.info("cemsTimer定时器销毁");
        }   
    }
}
