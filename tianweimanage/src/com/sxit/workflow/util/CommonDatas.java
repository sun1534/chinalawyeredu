/**
 * 
 */
package com.sxit.workflow.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 华锋
 * Jul 10, 2009 12:46:12 AM
 *
 */
public class CommonDatas {

	public static Map<Short,String> NODETYPES=new LinkedHashMap<Short,String>();
	public static Map<Short,String> NODEDOTYPES=new LinkedHashMap<Short,String>();
	
	static{
		
		NODETYPES.put((short)1, "开始节点");
		NODETYPES.put((short)2, "普通节点");
		NODETYPES.put((short)3, "分支节点");
		NODETYPES.put((short)4, "汇聚节点");
		NODETYPES.put((short)5, "结束节点");
		
		NODEDOTYPES.put((short)1, "单人");
		NODEDOTYPES.put((short)2, "循环");
		NODEDOTYPES.put((short)3, "多人串行");
		NODEDOTYPES.put((short)4, "多人并行");
	}
	
}
