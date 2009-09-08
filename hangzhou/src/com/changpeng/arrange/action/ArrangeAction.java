/**
 * ArrangeAction.java
 */

package com.changpeng.arrange.action;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Arrange;
import com.changpeng.models.system.SysUser;

/**
 * 
 * @author 华锋 2008-5-7 下午05:46:01
 * 
 */
public class ArrangeAction extends AbstractAction {

	private int arrangeid;
	private byte arrangetype;

	private Arrange arrange;

	/**
	 * @return the arrangeid
	 */
	public int getArrangeid() {
		return arrangeid;
	}

	/**
	 * @param arrangeid
	 *            the arrangeid to set
	 */
	public void setArrangeid(int arrangeid) {
		this.arrangeid = arrangeid;
	}

	/**
	 * @return the arrangetype
	 */
	public byte getArrangetype() {
		return arrangetype;
	}

	/**
	 * @param arrangetype
	 *            the arrangetype to set
	 */
	public void setArrangetype(byte arrangetype) {
		this.arrangetype = arrangetype;
	}

	/**
	 * @return the arrange
	 */
	public Arrange getArrange() {
		if (this.arrange == null)
			this.arrange = (Arrange) get("arrange");
		return arrange;
	}

	/**
	 * @param arrange
	 *            the arrange to set
	 */
	public void setArrange(Arrange arrange) {
		this.arrange = arrange;
	}

	public String go() throws Exception {
		BasicService basic = (BasicService) getBean("basicService");
		SysUser user = (SysUser) this.getLoginUser();
		String type = "";
		if (get("arrangeexist") != null && "0".equals(get("arrangeexist"))) {
			arrange.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			arrange.setCreateuser(user.getUserid());

			basic.save(arrange);
			type = "新增";
		}
		else {
			type = "修改";
			basic.update(arrange);
		}
		if (arrange.getArrangetype() == 1)
			this.message = "岗前培训" + type + "成功";
		else
			this.message = "活动安排" + type + "成功";
		this.nextPage = "arrangeList.pl?arrangetype=" + arrangetype;
		return SUCCESS;

	}

	@Override
	public String input() throws Exception {

		BasicService basic = (BasicService) getBean("basicService");
		this.arrange = (Arrange) basic.get(Arrange.class, arrangeid);

		if (this.arrange == null) {
			set("arrangeexist", "0");
			this.arrange = new Arrange();
			this.arrange.setMaxpersons(new Short((short)0));
			this.arrange.setArrangetype(arrangetype);
		}
		else {
			
			set("arrangeexist", "1");
		}
		this.arrangetype=this.arrange.getArrangetype();
		set("arrange", arrange);

		return INPUT;

	}
	/*public String viewit() throws Exception{
	
		BasicService basic = (BasicService) getBean("basicService");
		arrange=(Arrange)basic.get(Arrange.class, arrangeid);
		return "view";
	}
	public String delete() throws Exception{
	
		BasicService basic = (BasicService) getBean("basicService");
		basic.delete(Arrange.class, arrangeid);
		this.message="删除成功";
		return SUCCESS;
	}*/
}
