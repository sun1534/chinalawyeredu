package com.sxit.member.action;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.type.Type;

import com.sxit.member.model.*;
import com.sxit.common.action.AbstractAction;
import java.util.*;

/**
 * 
 * <p>
 * 功能： 查询简历录入
 * </p>
 * <p>
 * 作者： 雷华
 * </p>
 * <p>
 * 公司： 深圳信科
 * </p>
 * <p>
 * 日期： 2008-05-13
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public class ResumeSearchAction extends AbstractAction {
	private List resumelist;

	private TmemResume resumeExample = new TmemResume();

	private String flag = ""; // flag为in的时候表述输入查询条件 flag为out的时候表述输出查询结果

	// private String departmentname;
	public ResumeSearchAction() {
		rights = "mem2,1";
	}

	// 忽略空字符串
	private static final Example.PropertySelector NON_EMPTY_STRING = new Example.PropertySelector() {
		public boolean include(Object value, String name, Type type) {
			return value != null && !"".equals(value);
		}
	};

	protected String go() throws HibernateException {
		Criteria criteria = getSession().createCriteria(TmemResume.class).add(
				Example.create(resumeExample) // 事例查询
						.enableLike(MatchMode.ANYWHERE) // 模糊查询
						.excludeProperty("statusid").ignoreCase() // 忽略大小写
						.setPropertySelector(NON_EMPTY_STRING) // 忽略空字符串
				);
		resumelist = criteria.list(); // 将结果集一次输出
		return SUCCESS;
	}

	public String input() throws Exception {
		return "input";
	}

	public List getResumelist() {
		return resumelist;
	}

	public TmemResume getResumeExample() {
		return resumeExample;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return this.flag;
	}

}
