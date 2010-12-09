package com.cqmm.common;

import org.w3c.dom.Node;


/**
 * Created by IntelliJ IDEA.
 * User: zhyy
 * Date: 2010-9-1
 * Time: 13:57:22
 */
public class CmdManager {
    static protected CmdNode cmdNode = new CmdNode(null);
 
    CmdManager() {
    }

    static public boolean loadXml(Node element) {
        CmdNode newNode = new CmdNode(null);
        if (newNode.loadXml(element)) {
            cmdNode = newNode;
            return true;
        }
        return false;
    }
    


	public static CmdNode getCmdNode() {
        return cmdNode;
    }
}
