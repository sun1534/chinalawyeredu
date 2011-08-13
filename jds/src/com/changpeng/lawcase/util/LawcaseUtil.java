package com.changpeng.lawcase.util;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;

import com.changpeng.common.Globals;
import com.sxit.workflow.model.TwflDirection;
import com.sxit.workflow.model.TwflNode;

public class LawcaseUtil {

	public static Globals globals = Globals.getInstance();

	/**
	 * 选择诉讼记录及人员进行任务分配
	 * 
	 * @param userid
	 *            用户ID
	 * @param check
	 *            催收记录
	 * @throws SQLException
	 */
	public static TwflNode getNextNode(Session session,int currentNodeId,String tonext)  {

		TwflNode node = (TwflNode) session.get(TwflNode.class,
				currentNodeId);
		Set<TwflDirection> directons = node.getToNode();
		Iterator<TwflDirection> tonodes =directons.iterator();
		int nodeid=0;
		if (directons.size() == 1) {
			nodeid = tonodes.next().getToNode().getNodeid();
		} else {
			while (tonodes.hasNext()) {
				TwflDirection d = tonodes.next();
				if (d.getDescription()!=null&&tonext!=null&&d.getDescription().equals(tonext))
					nodeid = d.getToNode().getNodeid();
			}
		}
		return com.changpeng.lawcase.util.CommanDatas.ALLNODES.get(nodeid);
	}
	
	
}
