package cn.gov.scciq.timer;
import java.util.Timer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cn.gov.scciq.systemManage.timeCalculate.TimeCalculateTask;


public class DataCopyServlet extends HttpServlet{
	 private static final long serialVersionUID = 1L;
	    private static Log log = LogFactory.getLog(FRMTimerServlet.class);
	    //读取报检单定时器
	    private Timer cemsTimer = null;
	    
	    @Override
	    public void init() throws ServletException {
	    	// TODO Auto-generated method stub
	    	 String startTask = getInitParameter("startCopy"); 
	    	 if(startTask.equals("true")){          
	             log.info("dataTimer定时器启动");
	             cemsTimer = new Timer(true);               //true表示timer为后台线程(daemon)            
	             TimeCalculateTask task=new TimeCalculateTask();
	           /*  Calendar calendar = Calendar.getInstance();  
	             calendar.set(Calendar.DAY_OF_MONTH,15);
	             calendar.set(Calendar.HOUR_OF_DAY, 12);  
	             int min=calendar.get(Calendar.MINUTE);  
	             //calendar.get(Calendar.SECOND);  
	             int year = calendar.get(Calendar.YEAR);
	             int month = calendar.get(Calendar.MONTH);
	             int day = calendar.get(Calendar.DAY_OF_MONTH);
	             int hour = calendar.get(Calendar.HOUR_OF_DAY);
	             calendar.set(year, month, day, hour, 59, 00);*/
//	             Date time = calendar.getTime(); 
//	             System.out.println(" the time is "+time);
	             cemsTimer.schedule(task, 0,23*60*60*1000);
	         }
	    }
}
