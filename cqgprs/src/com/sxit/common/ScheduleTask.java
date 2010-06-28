package com.sxit.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * 
 * <p>
 * 功能：定时任务，每隔指定时间重新连接数据库
 * </p>
 * <p>
 * 作者： sinhoo
 * </p>
 * <p>
 * 日期： Jun 2, 2008
 * </p>
 * 
 * @修改：
 */
public class ScheduleTask extends TimerTask {
	private static Log LOG = LogFactory.getLog(ScheduleTask.class);
	// 清除存储在缓存中的时间间隔
	private static long INTERVAL = 1 * 60 * 60 * 1000;

	public void run() {
		// WebApplicationContext wac =
		// ContextLoader.getCurrentWebApplicationContext();
		// BasicService service = (BasicService) wac.getBean("basicService");
		try {
			LOG.info("执行定时任务连接数据库");
			// service.find("from com.sxit.models.system.SysLog a where
			// a.logid=-1");
			com.sxit.system.util.CommonDatas.getUsers();
			com.sxit.system.util.CommonDatas.getGroups();

			// 定期清理SHQueryService.QUERY_RESULT中的数据

			Iterator<String> iterator = com.sxit.query.service.SHQueryService.QUERY_RESULT.keySet().iterator();

			List<String> list = new ArrayList<String>();
			long now = System.currentTimeMillis();
			while (iterator.hasNext()) {
				String key = iterator.next();
				String[] keysplit = key.split(",");
				long thetime = Long.parseLong(keysplit[1]);
				// String msisdn=keysplit[1];
				if (now - thetime > INTERVAL) {
					list.add(key);
					LOG.info("清除命令执行中的结果缓存:" + key);
				}
			}
			for (String key : list) {
				com.sxit.query.service.SHQueryService.QUERY_RESULT.remove(key);
			}
			LOG.info("定时任务执行完毕.......");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("执行定时任务错误：" + e.getMessage());
		}
	}
}