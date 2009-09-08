package com.changpeng.common;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.changpeng.common.exception.ServiceException;
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
	
		try {
			LOG.info("执行定时任务连接数据库");
			
			ScheduleTask.getSysteNos();
			
//			service.findBySqlQuery("select count(*) from sys_user");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("执行定时任务错误："+e.getMessage());
		}
	}
	
	public static void getSysteNos()throws Exception{
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		BasicService service=(BasicService)wac.getBean("basicService");
		String sql="select systemno,cardno from sys_systemcardno";
		List list=service.findBySqlQuery(sql);
		if(list!=null&&list.size()>0){
			com.changpeng.common.CommonDatas.AllSystemNos.clear();
			synchronized(com.changpeng.common.CommonDatas.AllSystemNos){
			
				for(int i=0;i<list.size();i++){
					Object[] obj=(Object[])list.get(i);
					com.changpeng.common.CommonDatas.AllSystemNos.put(obj[0].toString(), obj[1].toString());
				}
			}
		}
	}
}