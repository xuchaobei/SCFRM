package cn.gov.scciq.systemManage.timeCalculate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cn.gov.scciq.dbpool.DBPool;

public class TimeCalculateTask extends TimerTask {
	 private static Log log = LogFactory.getLog(TimeCalculateTask.class);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		Calendar calendar = Calendar.getInstance();
		 int day = calendar.get(Calendar.DAY_OF_MONTH);
		 if(day==5){
			 try {
				CalculateMonthlyStaticData();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error(e);
				e.printStackTrace();
			}
		 }
		
	}
	
	public void CalculateMonthlyStaticData() throws Exception{
		
		Connection conn = DBPool.ds.getConnection();
		CallableStatement proc = null;
		
		Date date= new Date();
		SimpleDateFormat sf= new SimpleDateFormat("yy-MM-dd");
		String dateS=sf.format(date);
		System.out.println(dateS);
		
		try {
			String wCall = "{call Pro_CalculateMonthlyStaticData(?,?)}";
			proc = conn.prepareCall(wCall);
			proc.setInt(1, 0);
			proc.setString(2,dateS);
			proc.execute();
			log.info("Pro_CalculateMonthlyStaticData is ok "+dateS);
		
		}catch (SQLException e) {
          // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (Exception e) {
          e.printStackTrace();
      } finally{
          try {
              
              if(proc != null){
                  proc.close();
              }
              if(conn != null){
                  conn.close();
              }
          } catch (SQLException e) {
              // TODO Auto-generated catch block
             e.printStackTrace();
          }
      }
   }

}
