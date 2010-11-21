package com.cqmm.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.cqmm.bean.Command;
import com.cqmm.bean.Device;

public class Requests {
	public static String url="http://218.201.8.150:8000/serverprocess";
	
	public static String login(String username,String password){
		HttpRequest request=new HttpRequest();
		Map<String,String> params=new HashMap<String,String>();
		params.put("optype", "login");
		params.put("username", username);
		params.put("password", password);
		return request.post(url, params);
	}

	public static List<Device> getDevice(){
		HttpRequest request=new HttpRequest();
		Map<String,String> params=new HashMap<String,String>();
		params.put("optype", "getdevice");
		params.put("userid", Integer.toString(CurSession.userid));
		String res=request.post(url, params);
		List<Device> deviceList=new ArrayList<Device>();
		try{
			Document doc=DocumentHelper.parseText(res);
			List<Element> list=doc.getRootElement().elements();
			for(Element element:list){
				Device device=new Device();
				device.setId(Integer.parseInt(element.elementText("deviceid")));
				device.setDevicename(element.elementText("devicename"));
				deviceList.add(device);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return deviceList;
	}
	
	public static List<Command> getCommand(int deviceid){
		HttpRequest request=new HttpRequest();
		Map<String,String> params=new HashMap<String,String>();
		params.put("optype", "getcommand");
		params.put("userid", Integer.toString(CurSession.userid));
		params.put("deviceid", Integer.toString(deviceid));
		String res=request.post(url, params);
		List<Command> commandList=new ArrayList<Command>();
		try{
			Document doc=DocumentHelper.parseText(res);
			List<Element> list=doc.getRootElement().elements();
			for(Element element:list){
				Command command=new Command();
				command.setId(Integer.parseInt(element.elementText("commandid")));
				command.setCommandname(element.elementText("commandname"));
				command.setCommandtype(Integer.parseInt(element.elementText("commandtype")));
				command.setDeviceid(deviceid);
				commandList.add(command);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return commandList;
	}
	
	public static String execmd(int deviceid,int commandid){
		HttpRequest request=new HttpRequest();
		Map<String,String> params=new HashMap<String,String>();
		params.put("optype", "execommand");
		params.put("userid", Integer.toString(CurSession.userid));
		params.put("deviceid", Integer.toString(deviceid));
		params.put("commandid", Integer.toString(commandid));
		return request.post(url, params);
	}
	
}
