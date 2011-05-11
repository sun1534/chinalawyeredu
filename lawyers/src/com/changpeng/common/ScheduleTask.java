package com.changpeng.common;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.context.Globals;
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
//		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		BasicService service=(BasicService)Globals.getBean("basicService");
		try {
			LOG.info("执行定时任务连接数据库");
			LOG.info("总数:"+service.findBySqlQuery("select count(*) as cnt from sys_user"));
			LOG.info("在线用户数:"+com.changpeng.common.CommonDatas.ONLINE_USERS.size());
			com.changpeng.lessons.util.CommonDatas.getAlllessons();
			com.changpeng.common.CommonDatas.getGroups();
//			com.changpeng.system.util.CommonDatas.getUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("执行定时任务错误：",e);
		}
	}
}