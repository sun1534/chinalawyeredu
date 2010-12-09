package com.cqmm.common;



import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by IntelliJ IDEA.
 * User: zhyy
 * Date: 2010-9-1
 * Time: 13:32:49
 */
public class RemoteCmdResult {

    List<CmdResult> results = new LinkedList<CmdResult>();

    public RemoteCmdResult() {
    }

    public void addResult(CmdResult result) {
        results.add(result);
    }

    public List<CmdResult> getResults() {
        return results;
    }

    public boolean loadXml(Node element) {
    	 if(element == null || !element.getNodeName().equals("cmdresult"))
         {
             return false;
         } 
    	 
    	 NodeList children = element.getChildNodes();               	
         for (int i = 0;  children !=null&&i < children.getLength(); i++) {
        	 CmdResult result = new CmdResult();
         	if (result.loadXml( children.item(i))) {
         		results.add(result);
             }        
         }

        return true;
    }
    
    public Element toXml(Document doc)
    {
   	  
    	  Element cmdresult = doc.createElement("cmdresult");
    	  
    	  for(int i=0;i<results.size();i++ )
    	  {
    		  cmdresult.appendChild(results.get(i).toXml(doc));
    	  }
    	  
    	  return cmdresult;

    }
}