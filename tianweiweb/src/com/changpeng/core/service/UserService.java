package com.changpeng.core.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.core.model.CorePartner;
import com.changpeng.core.user.model.CoreUser;
import com.changpeng.core.user.model.CoreUserDetail;
import com.changpeng.core.user.model.CoreUserPersonal;
import com.changpeng.core.user.model.Userinfo;

/**
 * 
 * @author 刘华锋 2009-5-14 下午05:15:10
 */

public class UserService extends BasicService {
	private static Logger LOG = Logger.getLogger(UserService.class);
	/**
	 * 根据登录名查找家长
	 * 
	 * @param loginname
	 * @return
	 */
	public CoreUser getUserByLoginName(String loginname) {
		DetachedCriteria dc = DetachedCriteria.forClass(CoreUser.class, "u");
		dc.add(Restrictions.eq("u.loginName", loginname));
		List list = basicDAO.findByCriteria(dc);
		if (list != null && list.size() > 0)
			return (CoreUser) list.get(0);
		return null;
	}

	/**
	 * 根据id得到用户信息
	 * 
	 * @param uesrid
	 * @return
	 */
	public CoreUser getUserById(int userid) {
		return (CoreUser) basicDAO.get(CoreUser.class, userid);
	}


	/**
	 * 仅仅根据Userid去找人
	 * 
	 * @param userid
	 * @return
	 */
	public Userinfo getUserinfoById(int userid) {
		if(userid==0){
			LOG.warn("userid居然为0:::::");
			Userinfo info = new Userinfo();
			info.setId(0);
			return info;
		}
		Userinfo info = new Userinfo();
		CoreUser user = this.getUserById(userid);
		if (user == null) {
			LOG.warn("userid居然不存在:::::"+userid);
			info.setId(userid);
			return info;
		}else{
			info.setId(user.getId());
			info.setCityId(user.getCityId());
			info.setDistrictId(user.getDistrictId());
			info.setMobile(user.getMobile());
			info.setPic(user.getPic());
			info.setProvinceId(user.getProvinceId());
			info.setStatus(user.getStatus());
			info.setUserName(user.getUserName());
			info.setUserRole(user.getUserRole());
		}
		return info;
	}
	/**
	 * 根据id得到这个用户的一些统计信息
	 * 
	 * @param userid
	 * @return
	 */
	public CoreUserPersonal getUserPersonalById(int userid) {
		return (CoreUserPersonal) basicDAO.get(CoreUserPersonal.class, userid);
	}

	/**
	 * 根据id得到这个用户的详细信息
	 * 
	 * @param userid
	 * @return
	 */
	public CoreUserDetail getUserDetailById(int userid) {
		return (CoreUserDetail) basicDAO.get(CoreUserDetail.class, userid);

	}

	/**
	 * 修改用户的一些基本信息，比如生日爱好等等
	 * 
	 * @param personal
	 */
	public void updateUserDetail(CoreUserDetail detail) {
		basicDAO.update(detail);
	}

	/**
	 * 新增用户的基本信息。在页面的个人资料修改处，先判断detail是否存在再判断是执行新增还是执行修改
	 * 
	 * @param detail
	 */
	public void saveUserDetail(CoreUserDetail detail) {
		basicDAO.save(detail);
	}

	/**
	 * 
	 * 得到这个人的某个业务的隐私设置
	 * 
	 * @param userid
	 * @param serviceid
	 *            1:基本资料2:详细资料3:博客相册4:我产生的动态5:好友申请6:礼物、招呼、消息
	 * @return
	 */
	public String getUserPrivateSet(int userid, int serviceid) {

		CoreUser user = this.getUserById(userid);
		String privateflag = user.getPrivateFlag();

		return privateflag.substring((serviceid - 1), serviceid);
	}

	
	@Transactional
	public int regUser(CoreUser user,CoreUserDetail userdetail) {
		this.save(user);
		userdetail.setUserid(user.getId());
		this.save(userdetail);
		CoreUserPersonal userpersonal=new CoreUserPersonal(user.getId(), (short)0,0,
				(short)0, (short)0, 0,(short)0, (short)0,(short)0, (short)0, (short)0,0, (short)0,
				0,(short)0,(short)0,(short)0,(short)0, (short)0,"",new Timestamp(System.currentTimeMillis()),"",
				new Timestamp(System.currentTimeMillis()));
		this.save(userpersonal);
		return user.getId();
	}
	
	public List getMyshowlistindex(){
		DetachedCriteria dc = DetachedCriteria.forClass(CoreUser.class, "u");
//		dc.add(Restrictions.eq("u.userType", (short)10));
		dc.add(Restrictions.eq("u.userRole", (short)1));
		return this.findByCriteria(dc, 6);
	}
	
	public List getEntlistindex(){
		DetachedCriteria dc = DetachedCriteria.forClass(CoreUser.class, "u");
//		dc.add(Restrictions.eq("u.userType", (short)10));
		dc.add(Restrictions.eq("u.userRole", (short)2));
		return this.findByCriteria(dc, 6);
	}
	
	
	public PaginationSupport getMyshowlist(int pageSize,int pageNo){
		DetachedCriteria dc = DetachedCriteria.forClass(CoreUser.class, "u");
//		dc.add(Restrictions.eq("u.userType", (short)10));
		dc.add(Restrictions.eq("u.userRole", (short)1));
		return this.findPageOnPageNo(dc, pageSize, pageNo);
	}
	
	public PaginationSupport getEntlist(int pageSize,int pageNo){
		DetachedCriteria dc = DetachedCriteria.forClass(CoreUser.class, "u");
//		dc.add(Restrictions.eq("u.userType", (short)10));
		dc.add(Restrictions.eq("u.userRole", (short)2));
		return this.findPageOnPageNo(dc, pageSize, pageNo);
	}
	
	
	public List getPartnerlistindex(){
		DetachedCriteria dc = DetachedCriteria.forClass(CorePartner.class, "u");
		dc.add(Restrictions.eq("u.status", 0));
		return this.findByCriteria(dc, 14);
	}
	
	public PaginationSupport getPartnerList(int pageSize,int pageNo){
		DetachedCriteria dc = DetachedCriteria.forClass(CorePartner.class, "u");
		dc.add(Restrictions.eq("u.status", 0));
		return this.findPageOnPageNo(dc, pageSize, pageNo);
	}
	private String list2str(List list) {

		StringBuffer sb = new StringBuffer("(");
		int len = list == null ? 0 : list.size();
		for (int i = 0; i < len; i++) {
			sb.append(list.get(i));
			if (i != len - 1)
				sb.append(",");
		}
		sb.append(")");

		return sb.toString();
	}
	
	
}