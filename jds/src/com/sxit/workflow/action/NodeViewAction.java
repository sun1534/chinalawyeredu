package com.sxit.workflow.action;

import org.hibernate.HibernateException;
import com.sxit.common.action.AbstractAction;
import com.sxit.workflow.model.*;
import org.hibernate.Query;
import java.util.List;


/**
 *
 * <p>功能： 查看节点</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深圳信科</p>
 * <p>日期： 2007-10-16</p>
 * @版本： V1.0
 * @修改：
 */

public class NodeViewAction extends AbstractAction {
	private TwflNode node;
        private int nodeid;
	public NodeViewAction() {
          rights="wfl1,1";
	   node = new TwflNode();
	}

	public String go() throws HibernateException {
           nextpage="nodeList.action?pagenumber="+pagenumber;
           node=(TwflNode)getSession().get(TwflNode.class,Integer.valueOf(nodeid));
           if(node==null){
             message="未找到此节点";
             return "message";
           }
           set("node", node);
           return SUCCESS;
	}
	public TwflNode getNode() {
		return node;
	}
        public void setNodeid(int nodeid) {

          this.nodeid = nodeid;
        }
        public int getNodeid() {
          return this.nodeid;
        }

}
