package com.cqmm.common;

public interface CmdBase {	
	static final int cmd_back=0;
	static final int cmd_node=1;
	static final int cmd_item=2;
	public int getType();
	public String getTitle();
}
