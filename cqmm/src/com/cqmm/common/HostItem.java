package com.cqmm.common;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;



/**
 * Created by IntelliJ IDEA.
 * User: zhyy
 * Date: 2010-9-1
 * Time: 13:57:29
 */
public class HostItem {
    int id;
    String title = "";

    public HostItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean loadXml(Node element) {
        if(element == null || !element.getNodeName().equals("host"))
        {
            return false;
        }  
        NamedNodeMap attrs =  element.getAttributes();        
        if (attrs != null)
        {	Node node =attrs.getNamedItem("id");
        	if(node !=null)
        	{
        		try {
                    int id = Integer.parseInt(node.getNodeValue().trim());
                    this.setId(id);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return false;
                }
        	}
        	
        	node =attrs.getNamedItem("title");
        	if(node !=null)
            this.setTitle(node.getNodeValue());
        }
        return true;
    }


}
