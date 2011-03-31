package com.xjdzjc.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.xjdzjc.common.DbHelper;
import com.xjdzjc.model.City_Tpl_content;
import com.xjdzjc.model.Dzjc;
import com.xjdzjc.service.SendService;
import com.xjdzjc.service.TemplateService;

import freemarker.template.TemplateException;

public class ProessService extends TimerTask {
	public static Logger log = Logger.getLogger(ProessService.class);
	private int status = 0;
	public List<City_Tpl_content> templateList;
	public List<Dzjc> dzjclist;

	public ProessService(int status) {
		this.status = status;
	}

	public ProessService() {

	}

	public void proess() {
		DbHelper dbh = DbHelper.getInstance();
		Connection connection = dbh.getConnection();
		TemplateService templateservice = new TemplateService();
		SendService sendService = new SendService();
		// 取到所有地州模板列表
		templateList = templateservice.getcitytemplateList(connection, status);
		if (templateList != null && templateList.size() > 0) {
			for (City_Tpl_content ctcontent : templateList) {
				long start = System.currentTimeMillis();
				log.info("---处理" + ctcontent.getCity() + "开始时间为："
						+ start);
				// 按地州处理并发送短信
				try {
					log.info("--------处理" + ctcontent.getCity()
							+ "违章开始----------");
					sendService.handle_Sms(ctcontent, connection);

					log.info("--------处理" + ctcontent.getCity()
							+ "违章结束----------");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.info("---处理" + ctcontent.getCity() + "模板问题,"
							+ e.getLocalizedMessage());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.info("---处理" + ctcontent.getCity() + "数据问题,"
							+ e.getLocalizedMessage());
				} catch (TemplateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.info("---处理" + ctcontent.getCity() + "模板问题,"
							+ e.getLocalizedMessage());
				}
				long end = System.currentTimeMillis();
				log.info("---处理" + ctcontent.getCity() + "结束时间为："
						+ end + ",共花了" + (end - start)+" 毫秒");
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
