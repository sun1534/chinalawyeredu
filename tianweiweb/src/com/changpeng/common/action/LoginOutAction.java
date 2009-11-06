/**
 * 
 */
package com.changpeng.common.action;



import com.changpeng.core.service.UserService;





/**
 * 
 * @author 肖云亮
 *
 */
public class LoginOutAction extends AbstractAction {


	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		UserService service = (UserService) this.getBean("userService");
		this.remove("USERSESSION");
		return SUCCESS;

	}

}
