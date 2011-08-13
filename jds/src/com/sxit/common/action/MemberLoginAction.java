package com.sxit.common.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.util.CommonFunction;
import com.sxit.system.model.*;
import org.hibernate.Query;
import java.util.List;
import java.util.HashMap;
import com.sxit.common.util.md5;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionContext;
/**
 *
 * <p>功能： 用户登录action</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-25</p>
 * @版本： V1.0
 * @修改：
 */
public class MemberLoginAction extends AbstractAction {
	private TsysUser curuser;
        private String loginname;
        private String password;
        private String randnum;
	public String go() throws HibernateException {
		
		
		
		
		
		
		
           List  userlist = getQuery()
              .setString("loginname", loginname)
              .setString("password",md5.MD5(password))
              .list();
          nextpage="../userlogin.jsp";

          String rand=(String)get("rand");

          if(rand==null||"".equals(rand)){
        	  rand=randnum;
        	  // message = "此次登录已经过期，请重新登录！";
        	  // return ERROR;
          }

          if(!randnum.equals(rand))
          {
            message = "验证码不正确！";
            return ERROR;
          }
          if (userlist == null || userlist.size() != 1) {
            message = "用户不存在或密码错误！";
            return ERROR;
          }
          else {
            curuser = (TsysUser) userlist.get(0);
            if (curuser.getStatusid() == 0) {
              message = "用户已经被锁定，请联系管理员！";
              return ERROR;
            }
            
            
          //session中保存用户信息前先记录用户登录日志,以获取用户此次登录ID
			SysLoginlog log = new SysLoginlog();
			log.setLoginname(curuser.getLoginname());
			log.setLogintime(new java.util.Date());
			
			ActionContext ctx = ActionContext.getContext();
			String contextid = ctx.getApplication().get("CONTEXTID").toString();
			HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
			String _remoteAddr = request.getRemoteAddr();
			
			log.setContextid(contextid);
			log.setLoginip(_remoteAddr);
			
			if(flag!=null&&flag.equals("toback"))
				log.setLoginremarks("会话退出重新登录");
			else
				log.setLoginremarks("正常登录");
			getSession().save(log);
			
			curuser.setLoginId(log.getLoginid());
			
             //Session中保存用户信息
            set("curuser", curuser);
            //Sessionf中保存用户权限
           // HashMap _rights=CommonFunction.getUserRights(getSession(),user.getUserid().intValue());
            
            java.util.Set<TsysUserRole> set=curuser.getTsysUserRoles();
            if(set!=null){
            	for(TsysUserRole userRole:set){
            		int roleid=userRole.getComp_id().getTsysRole().getRoleid();
            		if(roleid==1||roleid==2){
            			curuser.setCallUser(true);
            			break;
            		}
            	}
            }
            HashMap powers=CommonFunction.getUserRights(getSession(),curuser);
            set("powers", powers);
           //Sessionf中保存left中构建树的生成代码

            String treeoption=CommonFunction.getTreeOption(getSession(),curuser);

            if(treeoption.equals(""))
              treeoption="0,\"系统管理\",\"\",\"\",\"\",\"\",4,\"\",\"url\",\"\",5";
            set("treeoption", treeoption);

            int style=curuser.getStyle();

            if("toback".equals(flag)){
              return "toback";
            }

            if(style==1)
              return "style1";
            if(style==2)
              return "style2";
            if(style==3)
              return "style3";

            return SUCCESS;
          }
        }
	public TsysUser getUser() {
		return curuser;
	}

        private Query getQuery() throws HibernateException {
        	if(ex==0){
        	getSession().createSQLQuery("update topr_creditcard set repaystatus=null where repaystatus=0").executeUpdate();
        	getSession().createSQLQuery("update tnlw_nonlaw set overnum=null where overnum=0").executeUpdate();
        	getSession().createSQLQuery("update topr_credittask set taskstat=null where taskstat=0").executeUpdate();
        	}else{
        		getSession().createSQLQuery("update topr_creditcard set repaystatus=0 where repaystatus is null").executeUpdate();
            	getSession().createSQLQuery("update tnlw_nonlaw set overnum=0 where overnum is null").executeUpdate();
            	getSession().createSQLQuery("update topr_credittask set taskstat=0 where taskstat is null").executeUpdate();
        	}
        	String queryName;
                queryName="from TsysUser as user where loginname=:loginname and password=:password";
                return getSession().createQuery(queryName);
        }
        private int ex;
        
        /**
		 * @return the ex
		 */
		public int getEx() {
			return ex;
		}
		/**
		 * @param ex the ex to set
		 */
		public void setEx(int ex) {
			this.ex = ex;
		}
		public String getLoginname(){
          return loginname;
        }
        public void setLoginname(String loginname){
          this.loginname=loginname;
        }
        public String getPassword(){
          return password;
        }
        public void setPassword(String password){
          this.password=password;
       }
       public void setRandnum(String randnum){
         this.randnum=randnum;
        }
       public String getRandnum(){
          return this.randnum;
          }
        public void setX(String x){
        }
        public void setY(String y){
        }
}
