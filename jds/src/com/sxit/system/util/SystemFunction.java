package com.sxit.system.util;

import com.changpeng.common.Globals;
import com.sxit.common.component.HibernateSession;
import com.sxit.system.model.*;

import java.util.*;
import java.sql.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.MatchMode;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class SystemFunction {

	public SystemFunction() {
	}

	/**
	 * @todo 根据申请人以及职务获取处理人
	 * @param session
	 *            Session
	 * @param position
	 *            TsysPosition
	 * @param user
	 *            TsysUser
	 * @return TsysUser
	 */
	public static TsysUser getPositionUser(Session session,
			TsysPosition position, TsysUser user) {
		TsysDepartment department = user.getTsysDepartment();

		Set<TsysDepartmentPosition> deppositionset = position
				.getTsysDepartmentPositions();
		int n = deppositionset.size();
		// 没找到执行的人 则返回null
		if (n == 0) {
			return null;
		}
		// 公司级别的职务
		if (position.getType() == 1) {
			for (TsysDepartmentPosition depp : deppositionset) {
				if (depp.getComp_id().getTsysDepartment().getDepartmentid() == 1)
					return depp.getTsysUser();
			}
			return null;
		}
		// 部门级别的职务
		TsysDepartment temdep = department;
		while (temdep.getDepartmentid() != 1) {
			for (TsysDepartmentPosition depp : deppositionset) {
				if (depp.getComp_id().getTsysDepartment().getDepartmentid() == temdep
						.getDepartmentid())
					return depp.getTsysUser();
			}
			temdep = temdep.getParent();
		}
		return null;
	}

	/**
	 * 取部门的下拉列表
	 * 
	 * @param session
	 * @return
	 */
	private static String tempstr = "";

	private static LinkedHashMap deplist = new LinkedHashMap();;

	public static void selectSet(TsysDepartment dept) {
		String diplayname = tempstr + "-";
		// dept.setDisplayname(diplayname + dept.getDepartmentname());
		//System.out.println(diplayname);
		deplist.put(dept.getDepartmentid(), diplayname
				+ dept.getDepartmentname());
		if (!dept.getChildren().isEmpty())
			for (TsysDepartment child : dept.getChildren()) {
				if (child.getDepartmentid() != 1 && child.getStatusid()==1) {
					tempstr = diplayname;
					selectSet(child);
				}
			}
	}

	/**
	 * 页面使用方法
	 * <s:select name="departmentid" listKey="key" listValue="value" list="departmentlist" />
	 * 
	 * @param session
	 * @return
	 */
	public static LinkedHashMap getDepselect(Session session) {
		if (deplist.size() > 0) {
			return deplist;
		}
		TsysDepartment corp = (TsysDepartment) session.get(
				TsysDepartment.class, Integer.valueOf(1));
		if (corp != null && corp.getStatusid()==1) {
			selectSet(corp);
		} else {
			return null;
		}
		return deplist;
	}

	/**
	 * 取部门的下属部门列表
	 */
	private static List deptlist;
	public static List getDepselect(Session session,int depid) {
		deptlist=new ArrayList();
		if (deptlist.size() > 0) {
			return deptlist;
		}
		TsysDepartment corp = (TsysDepartment) session.get(
				TsysDepartment.class, Integer.valueOf(depid));
		if (corp != null) {
			selectDepSet(corp);
		} else {
			return null;
		}
		return deptlist;
	}
	public static void selectDepSet(TsysDepartment dept) {
		deptlist.add(dept);
		if (!dept.getChildren().isEmpty())
			for (TsysDepartment child : dept.getChildren()) {
				if (child.getDepartmentid() != 1 && child.getStatusid()==1) {
					selectDepSet(child);
				}
			}
	}
	
	/**
	 * 取用户的下拉列表
	 * 
	 * @param session
	 * @return
	 */
	public static List getUserselect(Session session) {
		Criteria criteria2 = session.createCriteria(TsysUser.class);
		criteria2.add(Expression.eq("statusid", 1));
		criteria2.addOrder(Order.asc("username"));
		List userlist = criteria2.list();
		return userlist;
	}

	/**
	 * 取用户的下拉列表
	 * 
	 * @param session
	 * @return
	 */
	public static List getUserselect(Session session, String username) {
		Criteria criteria2 = session.createCriteria(TsysUser.class);
		criteria2.add(Expression.eq("statusid", 1));
		if (username != null) {
			criteria2.add(Expression.like("username", username,
					MatchMode.ANYWHERE));
		}
		criteria2.addOrder(Order.asc("username"));
		List userlist = criteria2.list();
		return userlist;
	}

	/**
	 * 根据职位找到所有的人员,如果有多个人员的话，让别人选给谁，即确定hotman
	 * 
	 * @param session
	 * @param position
	 * @return
	 */
	public static List getPositionUser(Session session, TsysPosition position) {
		// TsysDepartment department = user.getTsysDepartment();

		String query = "from TsysDepartmentPosition a where a.comp_id.tsysPosition.positionid="
				+ position.getPositionid();

		List list = session.createQuery(query).list();
		return list;

		// Set<TsysDepartmentPosition> deppositionset =
		// position.getTsysDepartmentPositions();
		// int n = deppositionset.size();
		// // 没找到执行的人 则返回null
		// if (n == 0) {
		// return null;
		// }
		// // 公司级别的职务
		// // if (position.getType() == 1) {
		// for (TsysDepartmentPosition depp : deppositionset) {
		// if (depp.getComp_id().getTsysDepartment().getDepartmentid() == 1)
		// return depp.getTsysUser();
		// }
		// return null;
		// }
		// 部门级别的职务
		// TsysDepartment temdep = department;
		// while (temdep.getDepartmentid() != 1) {
		// for (TsysDepartmentPosition depp : deppositionset) {
		// if (depp.getComp_id().getTsysDepartment().getDepartmentid() ==
		// temdep.getDepartmentid())
		// return depp.getTsysUser();
		// }
		// temdep = temdep.getParent();
		// }
		// return null;
	}
	
	public static Globals globals=Globals.getInstance();
	public static String getUsernameById(long userid){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String username="";
		try{
			con=globals.getCon();
			pstmt=con.prepareStatement("select username from tsys_user where userid="+userid+"");
			rs=pstmt.executeQuery();
			if(rs.next()){
				username=rs.getString(1);
			}
		}catch(SQLException e){}finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException e){}
		}
		return username;
	}
	
	public static void main(String[] args) {
		SystemFunction systemfunction = new SystemFunction();
	}
}
