package job;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import main.DateUtil;
import main.DxSendMain;
import main.SendConstant;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.jdbc.core.JdbcTemplate;

import common.Globals;

@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class DxwzExecuteJob implements Job {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private static Log LOG = LogFactory.getLog(DxwzExecuteJob.class);

	private static volatile int SCHEDULE_SEQ = 0;

	private static Map<String, Boolean> HAS_BEEN = new HashMap<String, Boolean>();

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		LOG.info("开始执行定时任务======================>" + (SCHEDULE_SEQ++));
		int weekday = DateUtil.getWeekday();
		if (!SendConstant.SEND_WEEKDAY.contains(weekday)) {
			LOG.warn("今天是周" + weekday + ",不再发送的时间"+SendConstant.SEND_WEEKDAY+"之内");
		} else {
			try {

				JdbcTemplate jdbcTemplate = (JdbcTemplate) Globals.getWebBean("jdbcTemplate");

				String sql = "select count(*) from user_order";
				int cc = jdbcTemplate.queryForInt(sql);
				LOG.info("订购用户数:" + cc);

				Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int minute = c.get(Calendar.MINUTE);
				Date today = new Date();
//				if (hour == SendConstant.SEND_HOUR && !HAS_BEEN.containsKey(df.format(today))) {
//				if (hour == SendConstant.SEND_HOUR && !HAS_BEEN.containsKey(df.format(today))) {

					HAS_BEEN.put(df.format(today), true);

					LOG.info("现在时间:" + hour + ":" + minute + ",开始短信的下发");
					DxSendMain main = new DxSendMain();
					main.process();

//				} else {
//					LOG.info("未到短信下发时间,忽略,本次完成>" + hour + ":" + minute);
//				}

			} catch (Exception e) {
				LOG.error("执行定时任务错误:", e);
			}
		}
	}

}
