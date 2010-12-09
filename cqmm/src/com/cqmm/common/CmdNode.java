package com.cqmm.common;





import java.util.Vector;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by IntelliJ IDEA.
 * User: zhyy
 * Date: 2010-9-1
 * Time: 13:57:08
 */
public class CmdNode  implements  CmdBase {
    String title = "";
    String image = "";
    boolean needPassword = false;
    Vector<CmdNode> cmdNodeList = new Vector<CmdNode>();
    Vector<CmdItem> cmdItemList = new Vector<CmdItem>();
    CmdNode parent;

    public CmdNode(CmdNode parent) {
        this.parent=parent;
    }
	public int getType() {
		
		return cmd_node;
	}

    public CmdNode getParent() {
        return parent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isNeedPassword() {
        return needPassword;
    }

    public void setNeedPassword(boolean needPassword) {
        this.needPassword = needPassword;
    }

    public void addCmdNode(CmdNode node) {
    	cmdNodeList.add(node);
        
    }

    public void addCmdItem(CmdItem cmd) {
        cmdItemList.add(cmd);
    }

    public Vector<CmdNode> getCmdNodeList() {
        return cmdNodeList;
    }

    public Vector<CmdItem> getCmdItemList() {
        return cmdItemList;
    }

    public boolean loadXml(Node  element) {
        if(element == null || !element.getNodeName().equals("cmdnode"))
        {
            return false;
        }       
        
        NamedNodeMap attr =  element.getAttributes();        
        if (attr != null)
        {	Node node =attr.getNamedItem("title");
        	if(node !=null)
            this.setTitle(node.getNodeValue());
        
        	node =attr.getNamedItem("image");
        	if(node !=null)
            this.setImage(node.getNodeValue());
        	
        	node =attr.getNamedItem("password");
        	if(node !=null)
            this.setNeedPassword(node.getNodeValue().trim().equals("true"));        	
        	
        }         
        
        NodeList children = element.getChildNodes();               	
        for (int i = 0;  children !=null&&i < children.getLength(); i++) {
        	
        	//------------------------------------------
            if (children.item(i).getNodeName().equals("cmdnode")) {
                CmdNode cmdNode = new CmdNode(this);
                if (cmdNode.loadXml(children.item(i))) {
                    cmdNodeList.add(cmdNode);
                }
            }else if (children.item(i).getNodeName().equals("cmd")) {
                CmdItem cmdItem = new CmdItem(this);
                if (cmdItem.loadXml(children.item(i))) {
                    cmdItemList.add(cmdItem);
                }
            }            
        }
        return true;
    }


}
