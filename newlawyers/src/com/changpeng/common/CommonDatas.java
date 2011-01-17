/**
 * 
 */
package com.changpeng.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.changpeng.common.context.Globals;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.LawyerLoginlog;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUnionparams;

/**
 * @author 华锋
 * 
 */
public class CommonDatas {

	private static Log LOG = LogFactory.getLog(CommonDatas.class);

	/**
	 * 当前在线的用户列表
	 */
	public static Map<Integer, LawyerLoginlog> ONLINE_USERS = new HashMap<Integer, LawyerLoginlog>();

	// 存储areaid和上级areaid的数据
	public static Map<Integer, Integer> AreasIdParent = new LinkedHashMap<Integer, Integer>();
	// 存储areaid和areaname的数据
	public static Map<Integer, String> AreasIdName = new LinkedHashMap<Integer, String>();
	/**
	 * key是url，value是对应的对象
	 */
	public static Map<String, SysUnionparams> SysUnionparams = new HashMap<String, SysUnionparams>();

	public static Map<String, String> SysParameter = new LinkedHashMap<String, String>();

	public static Map<Integer, String> groups = new LinkedHashMap<Integer, String>();;

	public static Map AllLawyers = new HashMap();

	public static Map<Integer, String> ForumType = new LinkedHashMap<Integer, String>();

	public static List<SysGroup> LATEST_OFFICE = new ArrayList<SysGroup>();
	public static List<SysGroup> LATEST_LVXIE = new ArrayList<SysGroup>();
	// 1是律师2是实习律师3是事务所4是律协
	public static Map<Integer, Integer> USER_STATICS = new LinkedHashMap<Integer, Integer>();

	static {

		// 意见和建议、积分申诉、补卡申请、产品使用咨询
		ForumType.put(1, "意见和建议");
		ForumType.put(2, "积分申诉");
		ForumType.put(3, "补卡申请");
		ForumType.put(4, "产品使用咨询");
	}

	private static Object obj = new Object();

	/**
	 * 首页需要的数据
	 */
	public static void indexPgaeData() {
		BasicService service = (BasicService) Globals.getBean("basicService");

		synchronized (obj) {

		}

	}

	public static void getGroups() {
		long now = System.currentTimeMillis();
		// if (now - groupInteractive > 60 * 60 * 60 * 1000) { // 每60分钟
		try {
			BasicService service = (BasicService) Globals.getBean("basicService");
			synchronized (groups) {
				int size = groups.size();
				groups.clear();
				DetachedCriteria dc = DetachedCriteria.forClass(SysGroup.class);
				dc.addOrder(Order.desc("groupid"));
				List list = service.findAllByCriteria(dc);
				int len = list == null ? 0 : list.size();
				int lvxie = 0;
				int office = 0;
				LATEST_OFFICE.clear();
				LATEST_LVXIE.clear();
				for (int i = 0; i < len; i++) {
					SysGroup sysgroup = (SysGroup) list.get(i);

					if (sysgroup.getGrouptype() == 1 && sysgroup.getIsbuy() == 1) {

						if (office++ < 5)
							LATEST_OFFICE.add(sysgroup);
					}
					if (sysgroup.getGrouptype() == 2 && sysgroup.getIsbuy() == 1) {
						if (lvxie++ < 5)
							LATEST_LVXIE.add(sysgroup);
					}
					
					groups.put(sysgroup.getGroupid(), sysgroup.getGroupname());
				}

				
				groups.put(0, "系统发布");
				USER_STATICS.put(3, office);
				USER_STATICS.put(4, lvxie);
				LOG.info("部门更新成功!::前:" + size + "后:" + groups.size());
			}
			
			
			//-1实习律师-2公证员其他律师
			JdbcTemplate jdbcTemplate =(JdbcTemplate)Globals.getBean("jdbcTemplate");
			String sql="select lawyertype,count(lawyertype)  as cnt from lawyers group by lawyertype";
			Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
					int lvshi=0;
					int shixi=0;
					while (rs.next()) {
						int lawyertype=rs.getInt("lawyertype");
						if(lawyertype==-1){
							shixi+=rs.getInt("cnt");
						}else if(lawyertype==-2){
							
						}else{
							lvshi+=rs.getInt("cnt");
						}
					
						
					}
					
					USER_STATICS.put(1, lvshi);
					USER_STATICS.put(2, shixi);
					return null;
				}
			});
			
			
		} catch (ServiceException e) {
			LOG.error("获取所有部门失败" + e);
		}
		groupInteractive = now;
		// }
	}

	private static long groupInteractive = 0;
}
