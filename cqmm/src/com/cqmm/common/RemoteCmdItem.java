package com.cqmm.common;



import java.io.StringWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlSerializer;

import android.os.Bundle;
import android.util.Xml;




/**
 * Created by IntelliJ IDEA.
 * User: zhyy
 * Date: 2010-9-1
 * Time: 10:22:00
 */
public class RemoteCmdItem {
    protected String cmdValue = "";
    protected String param = "";
    protected String hostGroup = "";
    protected String hosts = "";

    
	public boolean saveToBundle(Bundle bundle ){
		if(bundle == null)
			return false;
		
		bundle.putString("cmdValue",cmdValue);
		bundle.putString("param",param);
		bundle.putString("hostGroup",hostGroup);
		bundle.putString("hosts",hosts);

		
		return true;
	}
	
	public boolean loadFromBundle(Bundle bundle ){
		if(bundle == null)
			return false;
		
		cmdValue=bundle.getString("cmdValue");
		param=bundle.getString("param");
		hostGroup=bundle.getString("hostGroup");
		hosts=bundle.getString("hosts");
		
		return true;
	}
    public RemoteCmdItem() {

    }

    public String getCmdValue() {
        return cmdValue;
    }

    public void setCmdValue(String cmdValue) {
        this.cmdValue = cmdValue;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getHostGroup() {
        return hostGroup;
    }

    public void setHostGroup(String hostGroup) {
        this.hostGroup = hostGroup;
    }

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }
    


    public boolean loadXml(Node element) {

    	if(element == null || !element.getNodeName().equals("remotecmd"))
        {
            return false;
        } 
        
    	NodeList children = element.getChildNodes();               	
        for (int i = 0;  children !=null&&i < children.getLength(); i++) {
        	
        	//------------------------------------------
            if (children.item(i).getNodeName().equals("cmd")) {
            	this.setCmdValue(children.item(i).getNodeValue());
            }else if (children.item(i).getNodeName().equals("param")) {
            	this.setParam(children.item(i).getNodeValue());       	
            }else if (children.item(i).getNodeName().equals("hostgroup")) {
            	this.setHostGroup(children.item(i).getNodeValue());
            }else if (children.item(i).getNodeName().equals("hosts")) {
            	this.setHosts(children.item(i).getNodeValue());
            } 
        }

        return true;
    }
    
    public Element toXml(Document doc)
    {
   	  
    	  Element remotecmd = doc.createElement("remotecmd");
    	  
    	  Element cmd = doc.createElement("cmd");
    	  cmd.setNodeValue(this.cmdValue);
    	  
    	  Element param = doc.createElement("param");
    	  param.setNodeValue(this.param);
    	  
    	  Element hostgroup = doc.createElement("hostgroup");
    	  hostgroup.setNodeValue(this.hostGroup);
    	  
    	  Element hosts = doc.createElement("hosts");
    	  hosts.setNodeValue(this.hosts);

    	  remotecmd.appendChild(cmd);
    	  remotecmd.appendChild(param);
    	  remotecmd.appendChild(hostgroup);
    	  remotecmd.appendChild(hosts);
    	  
    	  return remotecmd;

    }
    
    public String toXmlString() {
    		XmlSerializer serializer = Xml.newSerializer();
    	    StringWriter writer = new StringWriter();
    	    
    	    
    	    try {
    	        serializer.setOutput(writer);
    	        serializer.startDocument("UTF-8", true);
    	        serializer.startTag("", "remotecmd");
    	            serializer.startTag("", "cmd");
    	            serializer.text(this.cmdValue);
    	            serializer.endTag("", "cmd");
    	            
    	            serializer.startTag("", "param");
    	            serializer.text(this.param);
    	            serializer.endTag("", "param");
    	            
    	            serializer.startTag("", "hostgroup");
    	            serializer.text(this.hostGroup);
    	            serializer.endTag("", "hostgroup");
    	            
    	            serializer.startTag("", "hosts");
    	            serializer.text(this.hosts);
    	            serializer.endTag("", "hosts");
    	        serializer.endTag("", "remotecmd");
    	        serializer.endDocument();
    	        return writer.toString();
    	    } catch (Exception e) {
    	        throw new RuntimeException(e);
    	    } 
       
    }

 

}
