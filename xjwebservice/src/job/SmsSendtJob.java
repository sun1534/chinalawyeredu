package job;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.jdbc.core.JdbcTemplate;

import service.SendService;
import sms.SmsUtil;

import common.Globals;

import entity.TmpMtsend;

@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class SmsSendtJob implements Job {
	private static Log LOG = LogFactory.getLog(SmsSendtJob.class);
	private SendService sendService = null;
	private JdbcTemplate jdbcTemplate = null;

	private SmsUtil smsutil;

	public SmsSendtJob() {
		this.sendService = (SendService) Globals.getWebBean("sendService");
		this.jdbcTemplate = (JdbcTemplate) Globals.getWebBean("jdbcTemplate");
		smsutil = new SmsUtil();
	}

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// 到tmp表里取数据,进行短信的下发
		List list = sendService.getSendList(50);
		int len = list == null ? 0 : list.size();
		int minid = 0;
		int maxid = 0;
		
		if (len == 0) {

		} else {
			LOG.info("短信下发个数:" + len);
			long now = System.currentTimeMillis();
			List sqls = new ArrayList();
			for (int i = 0; i < len; i++) { // 更新log_mtsend并删除tmp_mtsend里的值
				TmpMtsend tmp = (TmpMtsend) list.get(i);
				int id = tmp.getId();
				if (minid > id)
					minid = id;
				if (maxid < id)
					maxid = id;
				String result = SmsUtil.sendSms(tmp.getMobile(), tmp.getContent(), tmp.getType(),tmp.getLinkid(),tmp.getProductId());
				if (result.equals("-1") || result.equals("-2")) {
					// 重发
					sqls.add("update tmp_mtsend set result='0',send_count=send_count+1 where id=" + id);
				} else {
					sqls.add("update log_mtsend set result='"+result+"',send_time=now() where id=" + id);
					sqls.add("delete from tmp_mtsend where id=" + id);// 删除
				}
			}

			String[] sql = new String[sqls.size()];
			sqls.toArray(sql);
			if (sql.length != 0) {
				int[] s = jdbcTemplate.batchUpdate(sql);

				LOG.info("短信下发历史数据更新完成:" + (System.currentTimeMillis() - now));
			} else {
				LOG.info("短信下发没有数据需要更新" + (System.currentTimeMillis() - now));
			}
		}

	}

}
