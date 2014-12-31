package cn.gov.scciq.timer;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.gov.scciq.timer.acceptOrder.CEMSTask;

public class FRMTimerServlet extends HttpServlet{

    /**  */
    
    private static final long serialVersionUID = 1L;
    private static Log log = LogFactory.getLog(FRMTimerServlet.class);
    //读取报检单定时器
    private Timer cemsTimer = null;
    
    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        String startTask = getInitParameter("startTask");             
        // 启动定时器   
        if(startTask.equals("true")){          
            log.info("cemsTimer定时器启动");
            cemsTimer = new Timer(true);               //true表示timer为后台线程(daemon)            
            TimerTask cemsTask=new CEMSTask();
            cemsTimer.schedule(cemsTask, 0, 10*60*1000);
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
