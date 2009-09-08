/**
 * ArrangesignupAction.java
 */

package com.changpeng.arrange.action;

import com.changpeng.arrange.service.ArrangeService;
import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Arrange;
import com.changpeng.models.Arrangesignup;
import com.changpeng.models.system.SysUser;

/**
 * 
 * 培训的报名 培训包括岗前培训和活动安排<br/> 岗前培训只能事务所管理员报名<br/> 活动安排事务所管理员和律师自己都可以报名<br/>
 * 
 * @author 华锋 2008-5-5 上午01:01:17
 * 
 */
public class ArrangesignupAction extends AbstractAction {

	private int arrangeid;
	private int signupid;
	private byte arrangetype;
	private Arrange arrange;
	public Arrange getArrange(){
		return this.arrange;
	}

	public String go() throws Exception {
		ArrangeService arrangeService = (ArrangeService) getBean("arrangeService");
		SysUser user = (SysUser) this.getLoginUser();

		BasicService basicService = (BasicService) getBean("basicService");

	
	
		
		if (get("signupexist") != null && "0".equals(get("signupexist"))) {
			signup.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			signup.setCreateuser(user.getUsername());
			//如果是活动报名，判断这个人是否已经报名了
			//哎，暂时不想做这个判断了。先这样吧。
			if (arrangetype == 2) {
				signup.setLawerno(user.getLawerno());
				
			}
			this.arrange = (Arrange) basicService.get(Arrange.class, arrangeid);
			signup.setArrange(arrange);
			if (user.getSysGroup() != null) {
				signup.setGroupid(user.getSysGroup().getGroupid());
				signup.setGroupname(user.getSysGroup().getGroupname());
			}
			basicService.save(signup);
			// arrangeService.saveArrangesignup(signup, user.getUserid());
			this.message = "报名信息新增成功";
		}
		else {

			arrangeService.updateArrangesignup(signup);
			this.message = "报名信息修改成功";
		}

		// this.nextPage = "arrangeList.pl?arrangetype=" + arrangetype;
		this.nextPage = "arrangesignupList.pl?arrangeid=" + this.arrangeid;
		return SUCCESS;

	}

	@Override
	public String input() throws Exception {

		BasicService basic = (BasicService) getBean("basicService");
		this.signup = (Arrangesignup) basic.get(Arrangesignup.class, signupid);
		this.arrange = (Arrange) basic.get(Arrange.class, arrangeid);
		if (this.signup == null) {
			set("signupexist", "0");
			this.signup = new Arrangesignup();
		}
		else {
			set("signupexist", "1");
		}
		set("signup", signup);
	
		if (arrange.getArrangetype() == 1) {
			return "gangqianpeixun";
		}
		else if (arrange.getArrangetype() == 2) {
			return "lxhuodong";
		}

		this.arrangetype = arrange.getArrangetype();
		
		return INPUT;

	}

	private Arrangesignup signup;

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
	 * @return the signupid
	 */
	public int getSignupid() {
		return signupid;
	}

	/**
	 * @param signupid
	 *            the signupid to set
	 */
	public void setSignupid(int signupid) {
		this.signupid = signupid;
	}

	/**
	 * @return the signup
	 */
	public Arrangesignup getSignup() {
		if(signup==null)
			signup=(Arrangesignup)get("signup");
		return signup;
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

}
