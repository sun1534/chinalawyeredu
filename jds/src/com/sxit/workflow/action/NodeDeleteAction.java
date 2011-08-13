package com.sxit.workflow.action;


import java.util.Calendar;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;





/**
 *
 * <p>功能： 删除节点</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-16</p>
 * @版本： V1.0
 * @修改：
 */

public class NodeDeleteAction extends AbstractAction {

	private TwflNode node;

	public NodeDeleteAction() {
           rights="wfl1,8";
	}
	public String go() throws HibernateException {
                TwflNode node = (TwflNode) get("node");
                nextpage="nodeList.action?processid="+node.getTwflProcess().getProcessid();
                getSession().delete(node);
                message="删除成功！";
                return SUCCESS;
	}

	public TwflNode getNode() {
         if (node==null)
            node = (TwflNode) get("node");
          return node;
	}
}
