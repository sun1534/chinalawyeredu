package com.cqmm.common;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by IntelliJ IDEA. User: zhyy Date: 2010-9-1 Time: 13:31:57
 */
public class CmdResult {
	boolean excuteSuccess;
	int hostId;
	String hostName = "";
	String cmdResult = "";

	public CmdResult() {
	}

	@Override
	public String toString() {
		return hostName + (excuteSuccess ? "执行成功" : "执行失败");
	}

	public boolean isExcuteSuccess() {
		return excuteSuccess;
	}

	public void setExcuteSuccess(boolean excuteSuccess) {
		this.excuteSuccess = excuteSuccess;
	}

	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getCmdResult() {
		return cmdResult;
	}

	public void setCmdResult(String cmdResult) {
		this.cmdResult = cmdResult;
	}
	
    public Element toXml(Document doc)
    {
   	  
    	  Element result = doc.createElement("result");
    	  
    	  Element host = doc.createElement("host");
    	  host.setNodeValue(this.hostName);
    	  
    	  Element hostid = doc.createElement("hostid");
    	  hostid.setNodeValue(this.hostId + "");
    	  
    	  Element status = doc.createElement("status");
    	  status.setNodeValue(this.excuteSuccess+"");
    	  
    	  Element cmdresult = doc.createElement("cmdresult");
    	  cmdresult.setNodeValue(this.cmdResult);

    	  result.appendChild(host);
    	  result.appendChild(hostid);
    	  result.appendChild(status);
    	  result.appendChild(cmdresult);
    	  
    	  return result;

    }

	public boolean loadXml(Node element) {
		try {
			if (element == null || !element.getNodeName().equals("result")) {
				return false;
			}

			NodeList children = element.getChildNodes();
			for (int i = 0; children != null && i < children.getLength(); i++) {

				// ------------------------------------------
				if (children.item(i).getNodeName().equals("host")) {
					this.setHostName(children.item(i).getFirstChild().getNodeValue());
				} else if (children.item(i).getNodeName().equals("hostid")) {
					try {
						int id = Integer.parseInt(children.item(i)
								.getFirstChild().getNodeValue());
						this.setHostId(id);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else if (children.item(i).getNodeName().equals("status")) {
					this.setExcuteSuccess(children.item(i).getFirstChild().getNodeValue()
							.equals("true"));
				} else if (children.item(i).getNodeName().equals("cmdresult")) {
					this.setCmdResult(children.item(i).getFirstChild().getNodeValue());
				}
			}
		} catch (DOMException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
