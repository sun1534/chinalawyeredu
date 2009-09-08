package com.changpeng.lessons.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessonscore;

public class LessonsVoteAction extends AbstractAction {
	private int lessonid;
	private int common; // 评价一般票数
	private int preferably; // 评价较好票数
	private int good; // 评价很好票数

	private int commonl; // 评价一般票数显示宽度
	private int preferablyl;
	private int goodl;

	private Lessons lesson;

	public int getCommon() {
		return common;
	}

	public int getPreferably() {
		return preferably;
	}

	public int getGood() {
		return good;
	}

	public int getCommonl() {
		return commonl;
	}

	public int getPreferablyl() {
		return preferablyl;
	}

	public int getGoodl() {
		return goodl;
	}

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public LessonsVoteAction() {
		this.rightCode = "online";
	}

	@Override
	protected String go() throws Exception {
		BasicService service = (BasicService) this.getBean("basicService");

		DetachedCriteria dc = DetachedCriteria.forClass(Lessonscore.class);
		dc.add(Restrictions.eq("lessonid", lessonid));

		List lessonscores = service.findAllByCriteria(dc);

		int all = lessonscores == null ? 0 : lessonscores.size();

		if (all > 0) {
			for (Object obj : lessonscores) {
				Lessonscore score = (Lessonscore) obj;
				if (score.getScore() == 1)
					common++;
				else if (score.getScore() == 2)
					preferably++;
				else if (score.getScore() == 3)
					good++;
			}
			commonl = (common * 117) / all;
			preferablyl = (preferably * 117) / all;
			goodl = (good * 117) / all;
		}
		return SUCCESS;
	}

	public Lessons getLesson() {
		return lesson;
	}

}
