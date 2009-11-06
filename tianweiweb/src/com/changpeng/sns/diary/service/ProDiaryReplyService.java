package com.changpeng.sns.diary.service;

//import jxq.sns.diary.dao.ProDiaryReplyDAO;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.sns.diary.model.SnsDiary;
import com.changpeng.sns.diary.model.SnsDiaryComment;

/**
 * 2009年4月22 回贴Service
 * 
 * @author lenovo
 * 
 */
public class ProDiaryReplyService extends BasicService {
	private ProDiaryService diaryService;
//	private ProDiaryReplyDAO proDiaryReplyDAO;

	/**
	 * 保存回复，更新主贴表的回复数
	 * 
	 * @param replDiary
	 */
	public void saveProDiaryReply(SnsDiaryComment replDiary) {
		int diaryId = replDiary.getUserid();
		SnsDiary diary = diaryService.findProDiary(diaryId);
		int count = diary.getReplyCount();
		diary.setReplyCount(count + 1);
		diaryService.update(diary);
		this.save(replDiary);

	}

	/**
	 * 删除回复，更新主贴表的回复数
	 */
	public void deleteProDiaryReply(int replDiaryId) {
		SnsDiaryComment prodiareply = (SnsDiaryComment) super.get(
				SnsDiaryComment.class, replDiaryId);
		SnsDiary diary = diaryService.findProDiary(prodiareply.getUserid());
		int count = diary.getReplyCount();
		diary.setReplyCount(count - 1);
		diaryService.update(diary);
		this.delete(replDiaryId);
	}

	/**
	 * 根据主贴ID返回回复列表
	 * 
	 * @param diaryId
	 * @param pageSize
	 * @param pageNO
	 * @return
	 */
	public PaginationSupport ProDiaryReplyListByDiaryId(int diaryid,
			int pageSize, int pageNO) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(SnsDiaryComment.class);

		detachedCriteria.add(Restrictions.eq("diaryid", diaryid));
		detachedCriteria.addOrder(Order.asc("createTime"));
		return this.findPageOnPageNo(detachedCriteria, pageSize, pageNO);
		// return
		// proDiaryReplyDAO.findPageByCriteria(detachedCriteria,pageSize,pageNO);

	}

	public ProDiaryService getDiaryService() {
		return diaryService;
	}

	public void setDiaryService(ProDiaryService diaryService) {
		this.diaryService = diaryService;
	}

//	public ProDiaryReplyDAO getProDiaryReplyDAO() {
//		return proDiaryReplyDAO;
//	}
//
//	public void setProDiaryReplyDAO(ProDiaryReplyDAO proDiaryReplyDAO) {
//		this.proDiaryReplyDAO = proDiaryReplyDAO;
//	}
}
