/**
 * OrderService.java
 */
package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.SendConstant;

import org.apache.commons.logging.Log;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.transaction.annotation.Transactional;

import common.BasicService;

import entity.CityPrompt;
import entity.CityTemplateContent;
import entity.DzjcAll;
import entity.DzjcAllHistory;
import entity.LogMtsend;
import entity.TmpMtsend;
import entity.UserOrder;

/**
 * 
 * @author 刘哈哈 Mar 30, 20115:37:27 PM
 * 
 */
public class SendService extends BasicService {
	private static final Log LOG = org.apache.commons.logging.LogFactory.getLog(SendService.class);

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final String MEMBER_SERVICE_ID = "-dx";

	/**
	 * 只1天内的
	 * 
	 * @param resultcount
	 * @return
	 */
	@Transactional
	public List getSendList(int resultcount) {
		String yestarday = df.format(new java.sql.Timestamp(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
		// String query = "from TmpMtsend a where
		// a.sendTime>=date_sub(now(),interval 1 day) and a.result='0' and
		// a.sendcount<3 order by a.desc";
		String query = "from TmpMtsend a where a.sendTime>='" + yestarday
				+ "' and a.result='0' and a.sendcount<3 order by a.id desc";

		List list = basicDao.findNumList(query, resultcount);
		int len = list == null ? 0 : list.size();
		int minid = 0;
		int maxid = 0;

		// 更新状态为1
		for (int i = 0; i < len; i++) {
			TmpMtsend mtsend = (TmpMtsend) list.get(i);
			int id = mtsend.getId();
			if (minid > id)
				minid = id;
			if (maxid < id)
				maxid = id;
		}
		// 最后要删掉
		if (!(minid == 0 && maxid == 0)) {
			String hql = "update TmpMtsend a set a.result='1' where a.id <=" + maxid + " and a.id>=" + minid;
			basicDao.execute(hql);
		}

		return list;
	}

	/**
	 * 插入临时表
	 * 
	 * @param mobile
	 * @param content
	 * @param type
	 */
	@Transactional
	public void sendSms(String mobile, String content, String result, String type, String linkid, String productId) {

		TmpMtsend tmp = new TmpMtsend();
		tmp.setContent(content);
		tmp.setMobile(mobile);
		tmp.setResult(result);
		tmp.setType(type);
		tmp.setProductId(productId);
		tmp.setSendTime(new java.sql.Timestamp(System.currentTimeMillis()));
		tmp.setLinkid(linkid);
		basicDao.save(tmp);

		int id = tmp.getId();

		LogMtsend mtsend = new LogMtsend();
		mtsend.setContent(content);
		mtsend.setMobile(mobile);
		mtsend.setResult(result);
		mtsend.setType(type);
		mtsend.setLinkid(linkid);
		mtsend.setProductId(productId);
		mtsend.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		mtsend.setId(id);
		basicDao.save(mtsend);
	}

	/**
	 * 得到所有的地市模板信息
	 * 
	 * @return
	 */
	public Map<String, CityTemplateContent> getTemplateList() {
		List list = basicDao.findAll(CityTemplateContent.class);
		Map<String, CityTemplateContent> result = new HashMap<String, CityTemplateContent>();
		int len = list == null ? 0 : list.size();
		for (int i = 0; i < len; i++) {
			CityTemplateContent t = (CityTemplateContent) list.get(i);

			t.setDetailtemplate(main.TemplateUtil.getTemplate(t.getTemplateConDetails()));
			t.setNulltemplate(main.TemplateUtil.getTemplate(t.getTemplateConNull()));
			t.setSimpletemplate(main.TemplateUtil.getTemplate(t.getTemplateConSimple()));

			result.put(t.getCityname(), t);
		}
		return result;
	}

	/**
	 * 得到所有的地市短信提示信息
	 * 
	 * @return
	 */
	public Map<String, CityPrompt> getPromptList() {
		List list = basicDao.findAll(CityPrompt.class);
		Map<String, CityPrompt> result = new HashMap<String, CityPrompt>();
		int len = list == null ? 0 : list.size();
		for (int i = 0; i < len; i++) {
			CityPrompt t = (CityPrompt) list.get(i);
			result.put(t.getCityname(), t);
		}
		return result;
	}

	/**
	 * 设置dzjc_all_history里的ishandled为已处理 如果历史的都不在新的里面了 否则的话，如果旧的还在新的里面，
	 * 
	 */
	public int setHasBeenHandled() {

		String sql = "select count(*) from dzjc_all_history where is_handled=0";
		int count = jdbcTemplate.queryForInt(sql);
		DetachedCriteria dc = DetachedCriteria.forClass(DzjcAllHistory.class);
		dc.add(Restrictions.eq("isHandled", 0));
		int pagesize = SendConstant.PAGESIZE;
		int getcount = ((count - 1) / pagesize) + 1;
		int handlecount = 0;
		for (int i = 0; i < getcount; i++) {
			int startIndex = i * pagesize;
			List list = basicDao.findNumByCriteria(dc, pagesize, startIndex);// 一条条的查数据是否还在dzjc里面，如果不在了，那个数据就设置为is_handled=1了
			int len = (list == null ? 0 : list.size());
			for (int ii = 0; ii < len; ii++) {
				DzjcAllHistory dah = (DzjcAllHistory) list.get(ii);
				int s = getDzjcAllCnt(dah.getHphm(), df.format(dah.getDzjcsj()), dah.getHpzl() );
				if (s == 0) {
					dah.setIsHandled(1);
					dah.setHandleTime(new java.sql.Timestamp(System.currentTimeMillis()));
					basicDao.update(dah);
					handlecount++;
				}
			}
		}

		return handlecount;
	}

	public int getDzjcAllCnt(String hphm, String sj, short zhonglei) {

		String sql = "select count(*) from dzjc where hphm='" + hphm + "' and dzjcsj='" + sj + "' and hpzl="
				+ zhonglei ;
		return jdbcTemplate.queryForInt(sql);
	}
/**
 * 多条违章记录
 * @param uo
 * @return
 */
	public List<DzjcAllHistory> getTodayList(UserOrder uo){
	
		final List list = new ArrayList();
	
		String sql = "select a.* from dzjc a  where a.hphm='"+uo.getChepai()+"' and a.hpzl="+uo.getChepaileixing()+" order by dzjcsj";
		LOG.debug("sql:" + sql);
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {

				while (rs.next()) {
					DzjcAll all = new DzjcAll();
					all.setDiqu(rs.getString("diqu"));
					all.setDzjcdd(rs.getString("dzjcdd"));
					all.setDzjcsj(rs.getTimestamp("dzjcsj"));
					all.setHphm(rs.getString("hphm"));
					all.setHpzl(rs.getShort("hpzl"));
					all.setId(rs.getInt("id"));
					all.setWzxw(rs.getString("wzxw"));
					list.add(all);
				}
				return null;
			}
		});
		int len = (list == null ? 0 : list.size());
		if(len==0)
			return null;
		List<DzjcAllHistory> result = new ArrayList<DzjcAllHistory>();
		long now = System.currentTimeMillis();
		for (int i = 0; i < len; i++) {
			DzjcAll all = (DzjcAll) list.get(i);
			DzjcAllHistory history = updateHistoryHandleTimes(all);
			String key = history.getHphm() + "_" + history.getHpzl();
			result.add(history);
		}
		LOG.debug("处理完毕时间:" + (System.currentTimeMillis() - now));
		return result;
	}
	
	/**
	 * 得到今天的违章列表信息，并同时更新到表里面 返回车牌信息，该车牌的违章记录情况 这里只匹配我有车牌号的
	 * 
	 */
	@Transactional
	public Map<String, List<DzjcAllHistory>> getTodayList() {
		// 数据量太大,我只取1年内的吧
		// final List list = basicDao.findAll(DzjcAll.class);
		final List list = new ArrayList();
		// String sql = "select a.* from " + SendConstant.DZJC_DATABASE
		// + ".dzjc_all a inner join user_order b on a.hphm=b.chepai and
		// a.hpzl=b.chepaileixing";
		String sql = "select a.* from dzjc a inner join user_order b on a.hphm=b.chepai and a.hpzl=b.chepaileixing where b.status=0";
		LOG.debug("sql:" + sql);
		Object object = jdbcTemplate.query(sql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {

				while (rs.next()) {
					DzjcAll all = new DzjcAll();
					// list.add(rs.getInt("deviceid"));
					all.setDiqu(rs.getString("diqu"));
					all.setDzjcdd(rs.getString("dzjcdd"));
					all.setDzjcsj(rs.getTimestamp("dzjcsj"));
					all.setHphm(rs.getString("hphm"));
					all.setHpzl(rs.getShort("hpzl"));
					all.setId(rs.getInt("id"));
					all.setWzxw(rs.getString("wzxw"));
					list.add(all);
				}
				return null;
			}
		});
		int len = list == null ? 0 : list.size();
		// List<DzjcAllHistory> result = new ArrayList<DzjcAllHistory>();

		Map<String, List<DzjcAllHistory>> result = new HashMap<String, List<DzjcAllHistory>>();
		long now = System.currentTimeMillis();
		for (int i = 0; i < len; i++) {
			DzjcAll all = (DzjcAll) list.get(i);
			DzjcAllHistory history = updateHistoryHandleTimes(all);
			String key = history.getHphm() + "_" + history.getHpzl();
			List l = null;
			if (!result.containsKey(key)) {
				l = new ArrayList();
				result.put(key, l);
			} else
				l = result.get(key);
			l.add(history);
		}
		LOG.debug("处理完毕时间:" + (System.currentTimeMillis() - now));
		return result;
	}

	/**
	 * 如果有的话，就更新，否则就新增
	 * 
	 * @param dzjc
	 */
	public DzjcAllHistory updateHistoryHandleTimes(DzjcAll dzjc) {
		// 插入history表
		DzjcAllHistory history = getHistoryByKey(dzjc.getHphm(), dzjc.getHpzl(), dzjc.getDzjcsj());
		// if(result==0){
		if (history == null) {
			history = new DzjcAllHistory();
			history.setDq(dzjc.getDiqu());
			history.setDzjcdd(dzjc.getDzjcdd());
			history.setDzjcsj(dzjc.getDzjcsj());
			history.setHandleDays(0);
			history.setIsHandled(0);
			history.setHphm(dzjc.getHphm());
			history.setHpzl(dzjc.getHpzl());
			history.setWzxx(dzjc.getWzxw());
			history.setFirstTime(new java.sql.Timestamp(System.currentTimeMillis()));
			basicDao.save(history);
		} else {//对于没有处理过的违章,处理次数在此要加1,实际上在这个段来看的话

			history.setHandleTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setHandleDays(history.getHandleDays() + 1);
			basicDao.update(history);
		}
		return history;
	}

	/**
	 * 这里应该加上未处理的情况，不可以加了，因为是车牌号、类型和时间是唯一的
	 * @param chepai
	 * @param leixing
	 * @param date
	 * @return
	 */
	public DzjcAllHistory getHistoryByKey(String chepai, int leixing, Timestamp date) {
		DetachedCriteria dc = DetachedCriteria.forClass(DzjcAllHistory.class);

//		dc.add(Restrictions.eq("isHandled", 0)); //未处理
		dc.add(Restrictions.eq("hpzl", (short) leixing));
		dc.add(Restrictions.eq("hphm", chepai));
		dc.add(Restrictions.eq("dzjcsj", date));
		List list = basicDao.findByCriteria(dc);
		if (list != null && list.size() > 0)
			return (DzjcAllHistory) list.get(0);
		return null;

	}

}