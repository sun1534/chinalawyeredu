/**
 * 
 */
package com.changpeng.core.service;

import com.changpeng.common.BasicService;
import com.changpeng.core.user.model.CoreUserDetail;

/**
 * 
 * 对个人详细资料的修改
 * 
 * @author 华锋 May 22, 2009 3:34:28 PM
 * 
 */
public class UserDetailService extends BasicService {

	/**
	 * 新增
	 * 
	 * @param userdetail
	 */
	public void saveUserDetail(CoreUserDetail userdetail) {
		basicDAO.save(userdetail);
	}

	/**
	 * 修改
	 * 
	 * @param userdetail
	 */
	public void updateUserDetail(CoreUserDetail userdetail) {
		basicDAO.update(userdetail);
	}

	/**
	 * 返回这个人的基本信息啥滴
	 * 
	 * @param id
	 * @return
	 */
	public CoreUserDetail getUserDetailById(int id) {
		return (CoreUserDetail) basicDAO.get(CoreUserDetail.class, id);
	}

	/**
	 * 判断这个人的详细资料是否存在
	 * 
	 * @param id
	 * @return
	 */
	public boolean isExistUserDetail(int id) {
		String sql = "select count(*) as cnt from core_user_personal where id=" + id;
		int cnt = Integer.parseInt(basicDAO.findBySqlQuery(sql).get(0).toString());
		return cnt > 0 ? true : false;
	}
}