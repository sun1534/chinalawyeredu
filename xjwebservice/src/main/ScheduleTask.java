package main;

import java.sql.Connection;
import java.util.Calendar;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * <pre>
 * 
 * </pre>
 * 
 * @修改：
 */
public class ScheduleTask extends TimerTask {
	private static Log LOG = LogFactory.getLog(ScheduleTask.class);

	
	public ScheduleTask() {
	}


	//8点整的时候执行
	private static int EXECUTE_HOUR=8;
	private static int EXECUTE_MINUTE=0;
	
	private static int LAST_HOUR=0;
	private static int LAST_MINUTE=0;

	public void run() {

		Connection con = null;
		try {
			LOG.debug("开始执行定时任务======================>");
			
			Calendar c=Calendar.getInstance();
			int hour=c.get(Calendar.HOUR_OF_DAY);
			int minute=c.get(Calendar.MINUTE);
			if(hour==EXECUTE_HOUR&&(LAST_MINUTE>=EXECUTE_MINUTE&&EXECUTE_MINUTE<minute)){
				LOG.info("现在时间:"+hour+":"+minute+",开始短信的下发");
				DxSendMain main=new DxSendMain();
				main.process();
			}else{
				LOG.debug("未到短信下发时间,忽略");
			}
			LAST_HOUR=hour;
			LAST_MINUTE=minute;

		} catch (Exception e) {
			LOG.error("执行定时任务错误:",e);
		}
	}

	public static void main(String[] args) {

	}
}