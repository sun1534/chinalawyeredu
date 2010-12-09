package com.cqmm.common;


import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Created by IntelliJ IDEA.
 * User: zhyy
 * Date: 2010-9-1
 * Time: 13:57:38
 */
public class HostGroup {
    String name = "";
    List<HostItem> hostsList = new LinkedList<HostItem>();

    public HostGroup() {
    }

    void addHostItem(HostItem host) {
        hostsList.add(host);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HostItem> getHosts() {
        return hostsList;
    }

    public boolean loadXml(Node element) {
        if(element == null || !element.getNodeName().equals("group"))
        {
            return false;
        }       
        
        NamedNodeMap attr =  element.getAttributes();        
        if (attr != null)
        {	Node node =attr.getNamedItem("name");
        	if(node !=null)
            this.setName(node.getNodeValue());	
        } 
        NodeList children = element.getChildNodes();               	
        for (int i = 0;  children !=null&&i < children.getLength(); i++) {
        	HostItem host = new HostItem();
        	if (host.loadXml( children.item(i))) {
        		hostsList.add(host);;
            }        
        }
        return true;
    }

}
