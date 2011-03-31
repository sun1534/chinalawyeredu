package com.xjdzjc.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.xjdzjc.common.DbHelper;
import com.xjdzjc.model.City_Prompt;
import com.xjdzjc.service.TemplateService;

public class ProssCity_Content extends TimerTask {
	public static Logger log = Logger.getLogger(ProssCity_Content.class);
	public List<City_Prompt> templateList;


	public void proess() {
		DbHelper dbh = DbHelper.getInstance();
		Connection connection = dbh.getConnection();
		TemplateService templateservice = new TemplateService();
		// 取到所有地州模板列表
		templateList = templateservice.getCityPrompt(connection);
		if (templateList != null && templateList.size() > 0) {
			for (City_Prompt city_prompt : templateList) {
				long start = System.currentTimeMillis();
				log.info("---处理" + city_prompt.getCity() + "开始时间为：" + start);
				// 按地州处理并发送短信
				try {
					log.info("更新开始时间为+"+new Date().toLocaleString());
					templateservice.UpdateCityPrompt(connection, city_prompt
							.getCity(), city_prompt.getContent());
					log.info("更新结束时间为+"+new Date().toLocaleString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.info("---处理" + city_prompt.getCity() + "数据问题,"
							+ e.getLocalizedMessage());
				}
				long end = System.currentTimeMillis();
				log.info("---处理" + city_prompt.getCity() + "结束时间为：" + end
						+ ",共花了" + (end - start));
			}
		}
		dbh.closeCon(connection);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		long start = System.currentTimeMillis();
		log.info("执行任务开始时间为：" + start);
		this.proess();
		long end = System.currentTimeMillis();
		// dbh.closeCon();
		log.info("执行任务结束时间为：" + end + ",共花了" + (end - start));
	}

}
