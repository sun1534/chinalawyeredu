/*
 * 天威广告业务管理站点 所有action的抽象基类
 * 
 */

package com.changpeng.common.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.Globals;
import com.changpeng.common.util.LocationTree;
import com.changpeng.common.util.UserUtil;
import com.changpeng.core.service.FriendService;
import com.changpeng.core.service.UserService;
import com.changpeng.core.user.model.Userinfo;
import com.changpeng.global.model.GlobalArea;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author mfq
 */

public abstract class AbstractAction extends ActionSupport {
	private static Logger LOG = Logger.getLogger(AbstractAction.class);

	/**
	 * action处理完毕,跳转到信息页面后,继续返回的页面
	 */
	protected String nextPage = "javascript:history.go(-1)";
	/**
	 * 错误或者信息显示页面上,显示的消息
	 */
	protected String message = "";
	/**
	 * 返回给页面的结果数据
	 */
	protected String result = "";

	/**
	 * 用户访问ip地址
	 */
	public String userip = "";

	/**
	 * 当前用户的userid
	 */
	public int currentUserid;

	/**
	 * 当前用户的角色 
	 * 	1 家庭 
	 *  2 商家
	 */
	public int currentRole;

	public int provinceid;
	public int cityid;
	public int districtid;
	public int usertype;
	public String currentUsername;
	private boolean isfriend; // 是否为朋友关系
	/**
	 * <pre>
	 * 模块的id,在每个子类那里设置，拦截器需要用到
	 * 各模块的ID定义如下：
	 * 
	 * 基础SNS业务
	 * 10 登录
	 * </pre>
	 */
	public int moduleid;
	/**
	 * 遮罩层 点击确定之后如果需要跳转到另一个页面，设置这个url
	 */
	protected String redirectURL = "";

	/**
	 * 权限值,判断是否需要登录，如果rightCode为PUBLIC则不需要登录就能显示
	 */
	public String rightCode = "COMMON";

	/**
	 * 被访问者的userid,默认是当前登录用户的userid
	 */
	protected int viewUserid;
	/**
	 * 被访问者的角色,默认是当前登录用户的角色
	 */
	protected int viewUserrole;

	/**
	 * 
	 */
	protected int englishid = 0;

	/**
	 * 
	 */
	protected int indexid = 0;

	protected BasicService service;

	/**
	 * <pre>
	 * 如果有userid的地方需要用到 用户的名字,用户的头像 等最常用的信息 页面上可以加入这段代码 
	 * &lt;#assign dest=userutil.getUserinfo(userid) /&gt; 页面上就可以通过 
	 * ${dest.userName}得到用户名
	 * ${dest.userRole}得到用户角色 
	 * ${dest.pic}得到头像链接
	 * 
	 * </pre>
	 */
	protected UserUtil userutil;

	/**
	 * 隐私设置 1:基本资料 2:详细资料 3:博客相册 4:我产生的动态 5:好友申请 6:礼物、招呼、消息
	 */
	protected String privateflag;

	private boolean p_basicinfo;
	private boolean p_fullinfo;
	private boolean p_blogphoto;
	private boolean p_movement;
	private boolean p_friendapply;
	private boolean p_cansend;

	public AbstractAction() {
		service = (BasicService) this.getBean("basicService");
		userutil = UserUtil.getInstance();
	}

	@Override
	public String execute() throws Exception {
		if (userip.equals(""))
			userip = getIpAddr(ServletActionContext.getRequest());

		if (get("USERSESSION") != null) {
			// userid,provinceid,cityid,districtid,userrole,username,usertype
			if (this.currentUserid == 0) {
				String str[] = get("USERSESSION").toString().split(",");
				this.currentUserid = Integer.parseInt(str[0]);
				this.provinceid = Integer.parseInt(str[1]);
				this.cityid = Integer.parseInt(str[2]);
				this.districtid = Integer.parseInt(str[3]);
				this.currentRole = Integer.parseInt(str[4]);
				this.currentUsername = str[5];
				this.usertype = Integer.parseInt(str[6]);
			}

			if (viewUserid == 0 || viewUserid == currentUserid) {
				viewUserid = this.currentUserid;
				viewUserrole = this.currentRole;
				// viewUserrole = this.currentRole;
//			}
//
//			if (viewUserid == currentUserid) {
				// 访问自己的主页时角色就是自己的角色。
				isfriend = true;
				p_friendapply = true;
				p_basicinfo = true;
				p_fullinfo = true;
				p_blogphoto = true;
				p_movement = true;
				p_cansend = true;
			} else {
				FriendService friendService = (FriendService) this.getBean("friendService");
				isfriend = friendService.isFriendWithThem(this.currentUserid, this.viewUserid);

				Userinfo info = userutil.getUserinfo(this.viewUserid);
				UserService userService = (UserService) this.getBean("userService");
				
			}

		} else if (!rightCode.equals("PUBLIC")) {
			return LOGIN;
		}
		try {
			String result = go();
			return result;
		} catch (Exception e) {
			LOG.error("系统错误:::" + e.getMessage());
			e.printStackTrace();
			message = "系统错误";
			return ERROR;
		} finally {
		}
	}

	@Override
	public String input() throws Exception {
		if (userip.equals(""))
			userip = ServletActionContext.getRequest().getRemoteAddr();
		if (get("USERSESSION") != null) {

			if (this.currentUserid == 0) {
				String str[] = get("USERSESSION").toString().split(",");
				this.currentUserid = Integer.parseInt(str[0]);
				this.provinceid = Integer.parseInt(str[1]);
				this.cityid = Integer.parseInt(str[2]);
				this.districtid = Integer.parseInt(str[3]);
				this.currentRole = Integer.parseInt(str[4]);
				this.currentUsername = str[5];
				this.usertype = Integer.parseInt(str[6]);
			}
		} else if (!rightCode.equals("PUBLIC")) {
			return LOGIN;
		}

		try {
			String result = getin();

			return result;
		} catch (Exception e) {
			LOG.error("INPUT系统错误::" + e.getMessage());
			e.printStackTrace();
			message = "系统错误";
			return ERROR;
		} finally {
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	protected abstract String go() throws Exception;

	protected String getin() {
		return INPUT;
	}

	/**
	 * 将信息存储到会话信息中
	 * 
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	protected void set(Object key, Object value) {
		ActionContext.getContext().getSession().put(key, value);
	}

	/**
	 * 从会话信息中获取信息
	 * 
	 * @param key
	 * @return
	 */
	protected Object get(Object key) {
		return ActionContext.getContext().getSession().get(key);
	}

	protected void remove(Object key) {
		ActionContext.getContext().getSession().remove(key);
	}

	/**
	 * 页面到了提示页面后，点返回后跳转的页面
	 * 
	 * @return
	 */
	public String getNextPage() {
		return this.nextPage;
	}

	public String getMessage() {
		return this.message;

	}

	public String getResult() {
		return this.result;
	}

	/**
	 * 根据在spring配置文件中的bean的id,得到初始化了的bean对象
	 * 
	 * @param name
	 * @return
	 */
	protected Object getBean(String name) {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		return wac.getBean(name);
	}

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setViewUserid(int viewUserid) {
		this.viewUserid = viewUserid;
	}

	public int getViewUserid() {
		return viewUserid;
	}

	public int getViewUserrole() {
		return viewUserrole;
	}

	public void setViewUserrole(int viewUserrole) {
		this.viewUserrole = viewUserrole;
	}

	public int getEnglishid() {
		return englishid;
	}

	public void setEnglishid(int englishid) {
		this.englishid = englishid;
	}

	public int getIndexid() {
		return indexid;
	}

	public void setIndexid(int indexid) {
		this.indexid = indexid;
	}

	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public String getStaticpath() {
		return com.changpeng.common.sysdata.CommonData.Staticpath;
	}

	public String getResourcepath() {
		return com.changpeng.common.sysdata.CommonData.Resourcepath;
	}

	public UserUtil getUserutil() {
		return userutil;
	}

	

	public String getCurrentUsername() {
		return currentUsername;
	}

	public boolean isIsfriend() {
		return isfriend;
	}

	public String getPrivateflag() {
		return privateflag;
	}

	public boolean isP_basicinfo() {
		return p_basicinfo;
	}

	public boolean isP_fullinfo() {
		return p_fullinfo;
	}

	public boolean isP_blogphoto() {
		return p_blogphoto;
	}

	public boolean isP_movement() {
		return p_movement;
	}

	public boolean isP_friendapply() {
		return p_friendapply;
	}

	public boolean isP_cansend() {
		return p_cansend;
	}

}