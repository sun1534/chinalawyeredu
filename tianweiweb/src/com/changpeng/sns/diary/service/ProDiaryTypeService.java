package com.changpeng.sns.diary.service;

import java.util.List;

import com.changpeng.common.BasicService;
import com.changpeng.sns.diary.model.SnsDiarytype;


/**
 * 2009年4月22 贴子类型Service
 * 
 * @author lenovo
 * 
 */
public class ProDiaryTypeService extends BasicService {
	/**
	 * 根据当前用户ID得到该用户的所有主贴类型
	 * 
	 * @param userId
	 * @return
	 */
	public List<SnsDiarytype> getDiaryListByUserId(int userId) {
		String sql = "FROM SnsDiarytype t where t.userid=" + userId +" order by id asc";
		List<SnsDiarytype> diaryTypeList = this.findByQuery(sql);
		return diaryTypeList;
	}

	/**
	 * 返回默认的主贴类型
	 * 
	 * @return
	 */
	public SnsDiarytype getDefaultDiaryType(int userid) {
		String sql = "FROM SnsDiarytype t where t.userid="+userid+" and t.typename='默认分组'";
		List<SnsDiarytype> diaryTypeList = findByQuery(sql);
		if(diaryTypeList!=null&&diaryTypeList.size()!=0){
			SnsDiarytype diarytype = diaryTypeList.get(0);
			return diarytype;
		}
		return null;
	}
	
	/**
	 *  添加默认分组
	 * @param userid
	 * @param userRole
	 */
	public void addDefaultDiaryType(int userid){
		SnsDiarytype snsDiarytype = new SnsDiarytype();
		snsDiarytype.setTypename("默认分组");
		snsDiarytype.setUserid(userid);
		this.save(snsDiarytype);
	}

	public Integer deleteDiaryType(int diaryTypeId, int userid) {
		String sql = "FROM SnsDiarytype t where t.diarytypeid=" + diaryTypeId
				+ " and t.userid=" + userid;
		int falg = this.execute(sql);
		return falg;
	}
}
