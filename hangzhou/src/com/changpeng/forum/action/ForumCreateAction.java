/**
 * ForumCreateAction.java
 */

package com.changpeng.forum.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Forum;

/**
 * 新增或者回复帖子
 * 
 * @author 华锋 2008-5-5 下午06:36:36
 * 
 */
public class ForumCreateAction extends AbstractAction {

	public ForumCreateAction() {

		forumadd = new Forum();
	}

	/**
	 * 回复帖子的帖子id
	 */
	private int mainforumid;

	/**
	 * @return the mainforum
	 */
	public int getMainforumid() {
		return mainforumid;
	}

	/**
	 * @param mainforumid
	 *            the mainforumid to set
	 */
	public void setMainforumid(int forumid) {
		this.mainforumid = forumid;
	}

	/**
	 * 对帖子进行新增
	 */
	public String go() throws Exception {
		BasicService basicService = (BasicService) getBean("basicService");
		// 对主题帖的回帖数加1
		if (this.mainforumid != 0) { // 增加回帖
			forumadd.setMainforum(mainforumid);

			Forum main = (Forum) basicService.get(Forum.class, mainforumid);
			main.setReplycount(main.getReplycount() + 1);
			main.setLastupdate(new java.sql.Timestamp(System.currentTimeMillis()));
			basicService.update(main);

			forumadd.setIsmain(false);
		}
		else {
			forumadd.setIsmain(true); // 是回帖还是主题帖
		}
		forumadd.setDelflag(false);
		forumadd.setCreateuser(this.getLoginUser().getUsername());
		forumadd.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		forumadd.setLastupdate(new java.sql.Timestamp(System.currentTimeMillis()));
		basicService.save(forumadd);
		if (this.mainforumid == 0)
			this.mainforumid = forumadd.getForumid();
		debug("新增后的帖子的ID为::::" + this.mainforumid);

		return SUCCESS;
	}

	private Forum forumadd;

	/**
	 * @return the forumadd
	 */
	public Forum getForumadd() {
		return forumadd;
	}

}
