/**
 * OrderService.java
 */
package service;

import java.text.DateFormat;
import java.util.List;

import main.SendConstant;

import org.apache.commons.logging.Log;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import common.BasicService;

import entity.LogUserOrder;
import entity.UserOrder;

/**
 * 
 * @author 刘哈哈 Mar 30, 20115:37:27 PM
 * 
 */
public class OrderService extends BasicService {
	private static final Log LOG = org.apache.commons.logging.LogFactory.getLog(OrderService.class);

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final String MEMBER_SERVICE_ID = "-dx";

	/**
	 * 记录订购的日志记录
	 * 
	 * @param mobile
	 * @param packageid
	 * @param productid
	 * @param streamno
	 * @param remarks
	 * @param optype
	 * @param useridtype
	 * @return
	 */
	public int logUserOrder(String mobile, String packageid, String productid, String streamno, String remarks,
			int optype, int useridtype, String result) {
		try {

			LogUserOrder order = new LogUserOrder();
			order.setMobile(mobile);
			order.setOptime(new java.sql.Timestamp(System.currentTimeMillis()));
			order.setOptype(optype);
			order.setPackageid(packageid);
			order.setProductid(productid);
			order.setRemarks(remarks);
			order.setStreamno(streamno);
			order.setUseridtype(useridtype);
			order.setResult(result);
			// 插入到表里面
			basicDao.save(order);
			return order.getId();
		} catch (Exception e) {
			LOG.error("订购日志记录失败", e);
			return -1;
		}
	}

	/**
	 * 
	 * @param areacode
	 * @return
	 */
	public String getMemberTable(String areacode) {

		String regix = "";
		if (areacode == null || areacode.equals("") || !OrderConstant.AREA_DATABASE.containsKey(areacode)) {
			regix = OrderConstant.DEFAULT_TABLE_DATABASE;
		} else {
			// regix = AREA_DATABASE.get(areacode);
			regix = OrderConstant.DEFAULT_TABLE_DATABASE;
		}
		return regix + "." + OrderConstant.DEFAULT_MEMBER_TABLE;
	}

	/**
	 * 得到1
	 * 
	 * @param chepaileixing
	 * @return
	 */
	public int getMemberChepaiType(String chepaileixing) {
		if (chepaileixing == null || chepaileixing.equals(""))
			return -1;
		if (OrderConstant.MEMBER_CHEPAI.values().contains(chepaileixing))
			return Integer.parseInt(chepaileixing);
		if (!OrderConstant.MEMBER_CHEPAI.containsKey(chepaileixing))
			return -1;
		return Integer.parseInt(OrderConstant.MEMBER_CHEPAI.get(chepaileixing));
	}

	// 得到蓝
	public String getMemberChepaiTypeName(String chepaileixing) {
		if (chepaileixing == null || chepaileixing.equals(""))
			return "未知";
		if (OrderConstant.MEMBER_CHEPAI.containsKey(chepaileixing))
			return chepaileixing;
		if (OrderConstant.MEMBER_CHEPAI_NAME.containsKey(chepaileixing))
			return OrderConstant.MEMBER_CHEPAI_NAME.get(chepaileixing);
		return "未知";
	}

	/**
	 * 
	 * @param mobile
	 * @return
	 */
	public boolean isExistMemberMobile(String area, String mobile) {
		String table = getMemberTable(area);
		String sql = "select count(*) from " + table + " where tel_number='" + mobile + "' and service='"
				+ MEMBER_SERVICE_ID + "'";
		int h = jdbcTemplate.queryForInt(sql);
		return h == 0 ? false : true;
	}

	/**
	 * 插入具体的业务订购表，如果能够确定车牌号等等的话
	 * 
	 * @param order
	 */
	public void saveMember(UserOrder order) {
		String table = this.getMemberTable(order.getAreacode());

		String mt_flag = "0";
		String x = "0";
		String mt_flag_1 = "0";
		int chepai_type = getMemberChepaiType(order.getChepaileixing());

		int active = order.getStatus() == 1 ? 0 : 1;

		String sql = "";
		if (order.getChepai() != null)
			sql = "insert into " + table
					+ "(banner,tel_number,service,chepai,chepai_type,reg_time,active,mt_flag,x,mt_flag_1)values"
					+ "(null,'" + order.getMobile() + "','" + MEMBER_SERVICE_ID + "','" + order.getChepai() + "','"
					+ chepai_type + "','" + df.format(order.getOptime()) + "'," + active + "," + mt_flag + "," + x
					+ "," + mt_flag_1 + ")";
		else

			sql = "insert into " + table
					+ "(banner,tel_number,service,chepai_type,reg_time,active,mt_flag,x,mt_flag_1)values" + "(null,'"
					+ order.getMobile() + "','" + MEMBER_SERVICE_ID + "','" + chepai_type + "','"
					+ df.format(order.getOptime()) + "'," + active + "," + mt_flag + "," + x + "," + mt_flag_1 + ")";
		LOG.debug("新增sql:" + sql);

		basicDao.executeSql(sql);

	}

	/**
	 * 
	 * @param order
	 */
	public void deleteMembers(String areacode, String mobile) {
		String table = this.getMemberTable(areacode);
		String sql = "delete from " + table + " where tel_number='" + mobile + "' and service='" + MEMBER_SERVICE_ID
				+ "'";
		LOG.debug("删除sql:" + sql);
		basicDao.executeSql(sql);
	}

	/**
	 * 更新处理时间
	 * 
	 * @param id
	 * @param result
	 */
	public void updateLogOrderResult(int id, String result) {
		try {
			String hql = "update LogUserOrder set result=?,dotime=? where id=?";
			basicDao.execute(hql, new Object[] { result, new java.sql.Timestamp(System.currentTimeMillis()), id });
		} catch (Exception e) {
			LOG.error("updateLogOrderResult", e);
		}
	}

	/**
	 * 新增订购关系
	 * 
	 * @param userId
	 * @param productId
	 */
	@Transactional
	public int order(String mobile, String packageid, String productid, String streamno, String remarks, int useridtype) {

		UserOrder uo = this.getUserOrder(mobile, productid);
		if (uo != null) {
			LOG.warn("该用户已经订购该产品!因此返回给ISMP订购成功" + mobile + "=>" + productid);
			// return -2; // 已经订购
			return uo.getId();
		}
		try {
			UserOrder order = new UserOrder();
			order.setMobile(mobile);
			order.setOptime(new java.sql.Timestamp(System.currentTimeMillis()));
			order.setStatus(0); // 订购的时候，当然是0
			order.setPackageid(packageid);
			order.setProductid(productid);
			order.setRemarks(remarks);
			order.setStreamno(streamno);
			order.setUseridtype(useridtype);
			// 插入到表里面
			basicDao.save(order);
			this.saveMember(order);
			return order.getId();
		} catch (Exception e) {
			LOG.error("订购失败", e);
			return -1;
		}
	}

	/**
	 * 暂停订购关系
	 * 
	 * @param userId
	 * @param productId
	 */
	public int pause(String userId, String productId) {
		try {

			UserOrder order = this.getUserOrder(userId, productId);
			if (order != null) {
				String hql = "update UserOrder set status=1 where mobile=? and productid=?";
				basicDao.execute(hql, new Object[] { userId, productId });

				String table = this.getMemberTable(order.getAreacode());
				String sql = "update " + table + " set active=0 where tel_number='" + userId + "' and service='"
						+ MEMBER_SERVICE_ID + "'";
				int i = jdbcTemplate.update(sql);

				LOG.debug("更新" + table + "的active:" + sql + ",结果:" + i);
			}

			return 0;
		} catch (Exception e) {
			LOG.error("暂停失败", e);
			return -1;
		}
	}

	/**
	 * 恢复订购关系
	 * 
	 * @param userId
	 * @param productId
	 * @return
	 */
	public int resume(String userId, String productId) {
		try {
			UserOrder order = this.getUserOrder(userId, productId);
			if (order != null) {
				String hql = "update UserOrder set status=0 where mobile=? and productid=?";
				basicDao.execute(hql, new Object[] { userId, productId });

				String table = this.getMemberTable(order.getAreacode());
				String sql = "update " + table + " set active=1 where tel_number='" + userId + "' and service='"
						+ MEMBER_SERVICE_ID + "'";
				int i = jdbcTemplate.update(sql);

				LOG.debug("更新" + table + "的active:" + sql + ",结果:" + i);

			}
			return 0;
		} catch (Exception e) {
			LOG.error("业务恢复失败", e);
			return -1;
		}
	}

	/**
	 * 取消订购关系.删除订购关系表里的数据
	 * 
	 * @param userId
	 * @param productId
	 */
	public int cancel(String userId, String productId) {
		try {

			UserOrder order = this.getUserOrder(userId, productId);
			if (order != null) {
				String hql = "delete from UserOrder where mobile=? and productid=?";
				basicDao.execute(hql, new Object[] { userId, productId });
				this.deleteMembers(order.getAreacode(), userId);
			}
			return 0;
		} catch (Exception e) {
			LOG.error("业务退订失败", e);
			return -1;
		}
	}

	/**
	 * 
	 * @param mobile
	 * @return
	 */
	public UserOrder getDzjcUserOrder(String mobile) {
		DetachedCriteria dc = DetachedCriteria.forClass(UserOrder.class);
		dc.add(Restrictions.eq("mobile", mobile));
		dc.add(Restrictions.eq("productid", OrderConstant.DZJC_PRODUCTID));
		List list = basicDao.findByCriteria(dc);
		int len = list == null ? 0 : list.size();
		if (len > 0)
			return (UserOrder) list.get(0);
		return null;
	}

	/**
	 * 判断是否订购
	 * 
	 * @param mobile
	 * @param productId
	 * @return
	 */
	public UserOrder getUserOrder(String mobile, String productId) {
		DetachedCriteria dc = DetachedCriteria.forClass(UserOrder.class);
		dc.add(Restrictions.eq("mobile", mobile));
		if (productId != null && !productId.equals(""))
			dc.add(Restrictions.eq("productid", productId));
		List list = basicDao.findByCriteria(dc);
		int len = list == null ? 0 : list.size();
		if (len > 0)
			return (UserOrder) list.get(0);
		return null;
	}

	/**
	 * 
	 * @param mobile
	 * @param chepai
	 * @param leixing
	 * @param areacode
	 * @return
	 */
	@Transactional
	public int updateDzjcOrderInfo(String mobile, String chepai, String leixing, String areacode) {
		try {
			UserOrder order = this.getUserOrder(mobile, null);
			if (order == null)
				return -2;
			String oldarea = order.getAreacode();// 老的所在地市
			if (chepai != null && !chepai.equals(""))
				order.setChepai(chepai);
			if (leixing != null && !leixing.equals("")) {
				int changed = this.getMemberChepaiType(leixing);
				order.setChepaileixing(changed + ""); // 数据库里存的数据只有1，2，3这样子的
			}
			if (areacode != null && !areacode.equals("")) {
				order.setAreacode(areacode);// 新的地市
				order.setCityname(OrderConstant.AREA_DATABASE.get(areacode));
			}

			basicDao.update(order);
			this.updateMembers(order, oldarea);// 老的里面删掉,新的里面新增。记录这种日志

			return 0;
		} catch (Exception e) {
			LOG.error("更新车牌信息失败", e);
			return -1;
		}
	}

	/**
	 * 为了避免内存的溢出,一次取出来的个数,轮询处理 得到所有的用户列表
	 * 
	 * @return
	 */
	public List getAllOrdreUsers(int startIndex, int pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(UserOrder.class);
		dc.add(Restrictions.eq("status", 0));
		return basicDao.findNumByCriteria(dc, pageSize, startIndex);
	}

	/**
	 * 得到所有的订购人数
	 * 
	 * @return
	 */
	public int getAllOrderUserCount() {
		return jdbcTemplate.queryForInt("select count(*) from user_order where status=0");
	}

	/**
	 * 判断这个号码有几条违章信息
	 * 
	 * @param chepai
	 * @return
	 */
	public int getWZCount(String chepai) {
		if (chepai != null && !chepai.equals("")) {
//			String sql = "select count(*) from " + SendConstant.DZJC_DATABASE + ".dzjc_all where hphm='"
//					+ chepai.toUpperCase() + "'";
			String sql = "select count(*) from dzjc where hphm='"
			+ chepai.toUpperCase() + "'";
			LOG.debug("getWZCount:" + sql);
			return jdbcTemplate.queryForInt(sql);
		}
		return 0;
	}

	/**
	 * 
	 * @param order
	 * @param oldarea
	 * @return
	 */
	public int updateMembers(UserOrder order, String oldarea) {

		// String oldtable = this.getMemberTable(oldarea);
		String newtable = this.getMemberTable(order.getAreacode());
		// try {

		// boolean oldb = this.isExistMemberMobile(oldtable, order.getMobile());

		String newarea = order.getAreacode();// 新的

		// 新的区域和旧的区域一样,或者新的区域
		if (newarea == null || newarea.equals("") || newarea.equals(oldarea)) {

			boolean newb = this.isExistMemberMobile(newtable, order.getMobile());// 新的里面存不存在
			if (!newb) {
				this.saveMember(order);
			} else {

				String upd = "";

				int chepai_type = getMemberChepaiType(order.getChepaileixing());

				if (order.getChepai() != null && !order.getChepai().equals(""))
					upd += " chepai='" + order.getChepai() + "',";
				if (chepai_type != -1)
					upd += " chepai_type=" + chepai_type + ",";

				if (upd.length() != 0) {
					int len = upd.length();
					upd = upd.substring(0, len - 1);
					String sql = "update " + newtable + " set " + upd + " where tel_number='" + order.getMobile()
							+ "' and service='" + MEMBER_SERVICE_ID + "'";
					// 插入到现有业务的订购关系表
					LOG.debug("更新sql:" + sql);
					jdbcTemplate.execute(sql);
				}

				else {
					LOG.debug("不需要更新members表");
				}
			}

		} else {
			LOG.warn("两次的areacode不一样，删除旧的，新增到新的");
			this.deleteMembers(oldarea, order.getMobile());
			this.saveMember(order);
		}

		return 0;
		// } catch (Exception e) {
		// LOG.error("更新车牌信息失败", e);
		// return -1;
		// }
	}

}