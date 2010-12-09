package com.cqmm.common;

import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



/**
 * Created by IntelliJ IDEA.
 * User: zhyy
 * Date: 2010-9-1
 * Time: 13:57:50
 */
public class HostManager {
    static List<HostGroup> hostGroups = new LinkedList<HostGroup>();

    public HostManager() {
    }


    static public boolean loadXml(Node element) {
    	if(element == null || !element.getNodeName().equals("hosts"))
        {
            return false;
        }         

        List<HostGroup> newGroups = new LinkedList<HostGroup>();
        
        NodeList children = element.getChildNodes();               	
        for (int i = 0;  children !=null&&i < children.getLength(); i++) {
			
        	HostGroup group = new HostGroup();
			if (group.loadXml(children.item(i))) {
				newGroups.add(group);
			}
			    
        }
               
        if (newGroups.size() > 0)
            hostGroups = newGroups;
        return true;
    }

    public static List<HostItem>  getHosts(String groupType)
    {
        for(int i=0;i<hostGroups.size();i++ ){
            HostGroup group = (HostGroup)hostGroups.get(i);
            if(group.getName().equals(groupType))
                return group.getHosts();
        }
        return null;
    }

}
