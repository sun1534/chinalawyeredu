package com.changpeng.common;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.changpeng.operation.model.ToprCredittask;
import com.sxit.common.component.HibernateSession;
public class Globals {
	private static final Log LOG = LogFactory.getLog(Globals.class);
	// private static String configName = "/WEB-INF/applicationContext.xml";
	private static String configName = "classpath:applicationContext.xml";
	private static ApplicationContext context;
	public static HibernateSession session;
	private static org.apache.commons.dbcp.BasicDataSource ds;

	private static Globals instance=null;
	private Globals(){
		context = new ClassPathXmlApplicationContext(configName);
		session=(HibernateSession)context.getBean("hibernateSession");
		ds=(org.apache.commons.dbcp.BasicDataSource)context.getBean("dataSource");
	}
	public static synchronized Globals getInstance(){
		if(instance==null)
			instance=new Globals();
		return instance;
	}

	/**
	 * Convenience method to bind objects in Actions
	 * 
	 * @param name
	 * @return
	 */
	public Object getBean(String name) {
		//if (context == null) {
		//	context = new ClassPathXmlApplicationContext(configName);
		 //}
	//	WebApplicationContext wac =(WebApplicationContext)ActionContext.getContext().getApplication().get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

		return context.getBean(name);
	
	}
	
	public List findAll(String query){
		try{
			//if(session==null)
			//	session=(HibernateSession)getBean("hibernateSession");
			return session.getSession().createQuery(query).list();
		}finally{
			session.disposeSession();
		}
	}
	
	public Object get(Class o,Serializable id){
		try{
			//if(session==null)
			//	session=(HibernateSession)getBean("hibernateSession");
			return session.getSession().get(o, id);
		}finally{
			session.disposeSession();
		}
	}
	
	public Object get(Object o,Serializable id){
		try{
			//if(session==null)
			//	session=(HibernateSession)getBean("hibernateSession");
			return session.getSession().get(o.getClass(), id);
		}finally{
			session.disposeSession();
		}
	}
	
	public void update(Object o){
		try{
			session.getSession().update(o);
		}finally{
			session.disposeSession();
		}
	}
	
	public void save(Object o){
		try{
			session.getSession().save(o);
		}finally{
			session.disposeSession();
		}
	}
	
	public Connection getCon(){
		try{
			//if(ds==null)
			//	ds=(org.apache.commons.dbcp.BasicDataSource)getBean("dataSource");
			return ds.getConnection();
		}catch(SQLException e){
			LOG.error("SQLException:"+e);
			return null;
		}
	}
	
	public void closeCon(Connection con){
		try {
			if(con!=null) con.close();
		} catch (SQLException e) {
			LOG.error("SQLException:"+e);
		}
	}
	
	public static void main(String args[]) throws Exception {
		new Globals();
		System.out.println(session.getSession().createQuery(" from ToprCredittask where toprCreditcard.bankid=1").list());
		Criteria criteria = session.getSession().createCriteria(ToprCredittask.class).createCriteria("toprCreditcard");
		
		//	criteria.add(Expression.eq("bankid", 1l));        		


		System.out.println( criteria.setCacheable(true).list());
		session.disposeSession();
	}
	
	/**
	 * 获取CALL CENTER数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection callCenter() throws Exception{
		
		Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		Connection con=DriverManager.getConnection("jdbc:microsoft:sqlserver://192.168.1.9:14331;DatabaseName=cocall", "sa", "szjdx.99.cn");
		return con;
	}
}
