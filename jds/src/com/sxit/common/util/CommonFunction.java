package com.sxit.common.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Hibernate;
import org.hibernate.type.LongType;

import java.util.*;

import com.sxit.system.model.*;

/**
 * 
 * <p>
 * 功能： 公共的函数库
 * </p>
 * <p>
 * 作者： 张如兵
 * </p>
 * <p>
 * 公司： 深圳信科
 * </p>
 * <p>
 * 日期： 2004.9.29
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class CommonFunction {
	public CommonFunction() {
	}

	/**
	 * 功能：取用户权限列表
	 * 
	 * @param session
	 *            Session
	 * @param user
	 *            TsysUser
	 * @return HashMap
	 */
	public static HashMap getUserRights(Session session, TsysUser user) {

//		String image = user.getImage();
//		if (image == null || image.equals(""))
//			image = "0";
//		int ii = Integer.parseInt(image);
//		session.createSQLQuery("update tsys_user set image='" + (ii + 1) + "' where userid=" + user.getUserid())
//				.executeUpdate();

//		if (user.getIscookie() != null && user.getIscookie().equals("cookie")) {
//			try {
//				if (ii != 0) {
//					if (ii <= 10) {
////						Thread.sleep(ii * 1000);
//					} else if (ii <= 100) {
////						Thread.sleep((ii ) * 1000);
//					} else if (ii <= 1000) {
////						Thread.sleep((ii) * 1000);
//					} else if (ii <= 10000) {
////						Thread.sleep((ii ) * 1000);
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

		Set set = user.getTsysFunctionUsers();
		Object object[] = set.toArray();

		int power = 0;
		String functionid = "";
		HashMap hashmap = new HashMap();

		int i = 0;
		int j = 0;
		while (i < object.length) {
			TsysFunctionUser functionuser = (TsysFunctionUser) object[i];
			functionid = functionuser.getComp_id().getTsysFunction().getFunctionid();
			power = functionuser.getPower();
			if (power > 0) {
				hashmap.put(functionid, power);
			}
			i++;
		}
		Set set2 = user.getTsysUserRoles();
		Object object2[] = set2.toArray();
		i = 0;
		while (i < object2.length) {
			TsysUserRole userrole = (TsysUserRole) object2[i];
			TsysRole role = userrole.getComp_id().getTsysRole();
			Set set3 = role.getTsysFunctionRoles();
			Object object3[] = set3.toArray();
			j = 0;
			while (j < object3.length) {
				TsysFunctionRole functionrole = (TsysFunctionRole) object3[j];
				functionid = functionrole.getComp_id().getTsysFunction().getFunctionid();
				power = functionrole.getPower();
				if (power > 0) {
					if (hashmap.containsKey(functionid)) {
						int temppowr = power | ((Integer) hashmap.get(functionid)).intValue();
						hashmap.remove(functionid);
						hashmap.put(functionid, temppowr);
					} else {
						hashmap.put(functionid, power);
					}
				}
				j++;
			}
			i++;
		}
		return hashmap;
	}

	/**
	 * 功能：取目录树的构建字符串函数
	 * 
	 * @return String
	 */
	public static String getTreeOption(Session session, TsysUser user) {
		try {
			long userid = user.getUserid();
			List list = null;
			TsysFunction function = new TsysFunction();
			// 如果用户是系统管理则拥有全部权限
			if (user.isIsadmin()) {
				Query query = session
						.createQuery("from TsysFunction as function  where function.ismenu=1 order by function.tsysModule.moduleorder,function.functionorder,function.functionid");
				list = query.list();
			} else {
				// 非管理员权限
				Query query1 = session
						.createQuery("from TsysUserRole as userrole where userrole.comp_id.tsysUser.userid=" + userid);
				Query query3 = session
						.createQuery("from TsysFunctionUser as functionuser where functionuser.comp_id.tsysUser.userid="
								+ userid);

				List list1 = query1.list();
				List list3 = query3.list();

				TsysUserRole userrole = new TsysUserRole();
				TsysFunctionUser functionuser = new TsysFunctionUser();

				TsysFunctionRole functionrole = new TsysFunctionRole();

				String roleids = "0";
				String functionids = "'0'";

				int n = 0;
				n = list1.size();
				for (int i = 0; i < n; i++) {
					userrole = (TsysUserRole) list1.get(i);
					roleids = roleids + "," + userrole.getComp_id().getTsysRole().getRoleid();
				}
				n = list3.size();
				for (int i = 0; i < n; i++) {
					functionuser = (TsysFunctionUser) list3.get(i);
					functionids = functionids + ",'" + functionuser.getComp_id().getTsysFunction().getFunctionid()
							+ "'";
				}

				Query query4 = session
						.createQuery("from TsysFunctionRole as functionrole where functionrole.comp_id.tsysRole.roleid in("
								+ roleids + ")");
				List list4 = query4.list();

				n = list4.size();
				for (int i = 0; i < n; i++) {
					functionrole = (TsysFunctionRole) list4.get(i);
					functionids = functionids + ",'" + functionrole.getComp_id().getTsysFunction().getFunctionid()
							+ "'";
				}
				Query query = session
						.createQuery("from TsysFunction as function where function.ismenu=1 and function.iscommon=1 or function.functionid in ("
								+ functionids
								+ ") order by function.tsysModule.moduleorder,function.functionorder,function.functionid");
				list = query.list();
			}
			int n = list.size();
			String moduleid = "";
			String modulename = "";
			String functionname = "";
			String functionurl = "";
			String treeoption = "";
			for (int i = 0; i < n; i++) {
				function = (TsysFunction) list.get(i);
				if (!moduleid.equals(function.getTsysModule().getModuleid()) || moduleid.equals("")) {
					modulename = function.getTsysModule().getModulename();
					if (!treeoption.equals("")) {
						treeoption = treeoption.substring(0, treeoption.length() - 3);
						treeoption = treeoption + "1,";
					}
					treeoption = treeoption + "0,\"" + modulename + "\",\"\",\"\",\"\",\"\",4,\"\",\"\",\"\",-1,";
				}
				moduleid = function.getTsysModule().getModuleid();
				functionname = function.getFunctionname();
				functionurl = function.getUrl();
				treeoption = treeoption + "2,\"" + functionname + "\",\"\",\"\",\"\",3,\"\",\"" + functionurl
						+ "\",\"\",-1,";

			}
			treeoption = treeoption.substring(0, treeoption.length() - 3) + "5";
			return treeoption;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 功能：取leftbar的构建字符串函数
	 * 
	 * @return String
	 */
	public static String getBarOption(Session session, int userid) {
		try {
			Query query1 = session.createQuery("from TsysUserRole as userrole where userrole.comp_id.tsysUser.userid="
					+ userid);
			Query query3 = session
					.createQuery("from TsysFunctionUser as functionuser where functionuser.comp_id.tsysUser.userid="
							+ userid);

			List list1 = query1.list();
			List list3 = query3.list();

			TsysUserRole userrole = new TsysUserRole();
			TsysFunctionUser functionuser = new TsysFunctionUser();

			TsysFunctionRole functionrole = new TsysFunctionRole();
			TsysFunction function = new TsysFunction();
			// TsysModule module = new TsysModule();

			String roleids = "0";
			String functionids = "'0'";

			int n = 0;
			n = list1.size();
			for (int i = 0; i < n; i++) {
				userrole = (TsysUserRole) list1.get(i);
				roleids = roleids + "," + userrole.getComp_id().getTsysRole().getRoleid();
			}
			n = list3.size();
			for (int i = 0; i < n; i++) {
				functionuser = (TsysFunctionUser) list3.get(i);
				functionids = functionids + ",'" + functionuser.getComp_id().getTsysFunction().getFunctionid() + "'";
			}

			Query query4 = session
					.createQuery("from TsysFunctionRole as functionrole where functionrole.comp_id.tsysRole.roleid in("
							+ roleids + ")");
			List list4 = query4.list();

			n = list4.size();
			for (int i = 0; i < n; i++) {
				functionrole = (TsysFunctionRole) list4.get(i);
				functionids = functionids + ",'" + functionrole.getComp_id().getTsysFunction().getFunctionid() + "'";
			}
			Query query = session.createQuery("from TsysFunction as function where function.functionid in ("
					+ functionids + ") order by function.tsysModule.moduleorder,function.functionid");
			List list = query.list();
			n = list.size();
			String moduleid = "";
			String modulename = "";
			String functionname = "";
			String functionurl = "";
			String baroption = "";
			String iconname = "";
			for (int i = 0; i < n; i++) {
				function = (TsysFunction) list.get(i);
				if (!moduleid.equals(function.getTsysModule().getModuleid()) || moduleid.equals("")) {
					moduleid = function.getTsysModule().getModuleid();
					modulename = function.getTsysModule().getModulename();
					baroption = baroption + "bar" + moduleid + " = addbar(\"" + modulename + "\"),\n";
				}
				moduleid = function.getTsysModule().getModuleid();
				functionname = function.getFunctionname();
				functionurl = function.getUrl();
				iconname = function.getIconname();
				baroption = baroption + "addbutton(bar" + moduleid + ", \"" + functionname + "\", \"" + functionurl
						+ "\", \"main\", \"" + iconname + "\", \"\"), \n";
			}
			return baroption;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
