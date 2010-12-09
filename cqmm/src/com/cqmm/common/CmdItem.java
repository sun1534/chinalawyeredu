package com.cqmm.common;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import android.os.Bundle;


/**
 * Created by IntelliJ IDEA. User: zhyy Date: 2010-9-1 Time: 13:56:55
 */
public class CmdItem implements  CmdBase {

	protected String title = "";
	protected String image = "";
	protected String cmdValue = "";
	protected boolean needEnterPhone = false;
	protected String hostGroup = "";
	CmdNode parent;

	public CmdItem(CmdNode parent) {
		this.parent = parent;
	}
	public int getType() {
		
		return cmd_item;
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

	public String getCmdValue() {
		return cmdValue;
	}

	public void setCmdValue(String cmdValue) {
		this.cmdValue = cmdValue;
	}

	public boolean isNeedEnterPhone() {
		return needEnterPhone;
	}

	public void setNeedEnterPhone(boolean needEnterPhone) {
		this.needEnterPhone = needEnterPhone;
	}

	public String getHostGroup() {
		return hostGroup;
	}

	public void setHostGroup(String hostGroup) {
		this.hostGroup = hostGroup;
	}
	
	public boolean saveToBundle(Bundle bundle ){
		if(bundle == null)
			return false;
		
		bundle.putString("title",title);
		bundle.putString("image",image);
		bundle.putString("cmdValue",cmdValue);
		bundle.putBoolean("needEnterPhone",needEnterPhone);
		bundle.putString("hostGroup",hostGroup);
		
		return true;
	}
	
	public boolean loadFromBundle(Bundle bundle ){
		if(bundle == null)
			return false;
		
		title=bundle.getString("title");
		image=bundle.getString("image");
		cmdValue=bundle.getString("cmdValue");
		needEnterPhone=bundle.getBoolean("needEnterPhone");
		hostGroup=bundle.getString("hostGroup");
		
		return true;
	}
	
	public boolean loadXml(Node element) {

		if (element == null || !element.getNodeName().equals("cmd")) {
			return false;
		}

		NamedNodeMap attrs = element.getAttributes();
		if (attrs != null) {
			Node node = attrs.getNamedItem("title");
			if (node != null)
				this.setTitle(node.getNodeValue());

			node = attrs.getNamedItem("image");
			if (node != null)
				this.setImage(node.getNodeValue());

			node = attrs.getNamedItem("phone");
			if (node != null)
				this.setNeedEnterPhone(node.getNodeValue().trim().equals("true"));
			
			node = attrs.getNamedItem("hosts");
			if (node != null)
				this.setHostGroup(node.getNodeValue());
			
			node = attrs.getNamedItem("cmdv");
			if (node != null)
				this.setCmdValue(node.getNodeValue());
			
		}
		

		return true;
	}



}
