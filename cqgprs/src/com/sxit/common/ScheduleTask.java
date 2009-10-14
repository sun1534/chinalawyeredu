package com.sxit.common;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
/**
 * 
 *
 * <p>功能：定时任务，每隔指定时间重新连接数据库 </p>
 * <p>作者： sinhoo</p>
 * <p>日期： Jun 2, 2008</p>
 * @修改：
 */
public class ScheduleTask extends TimerTask{
	private static Log LOG = LogFactory.getLog(ScheduleTask.class);
	public void run(){
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		BasicService service=(BasicService)wac.getBean("basicService");
		try {
			LOG.info("执行定时任务连接数据库");
//			service.find("from com.sxit.models.system.SysLog a where a.logid=-1");
			com.sxit.system.util.CommonDatas.getUsers();
			com.sxit.system.util.CommonDatas.getGroups();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("执行定时任务错误："+e.getMessage());
		}
	}
}