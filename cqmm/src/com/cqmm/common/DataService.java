package com.cqmm.common;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.drawable.Drawable;

import com.cqmm.bean.Device;

public class DataService {
	
	
	private static Map<String,Drawable> userpics=new HashMap<String,Drawable>();

	
	public static List<Device> devices;
	
	public static List<Device> getDevice(){
		if(devices==null){
			devices=new ArrayList<Device>();
			Device device=new Device();
			device.setDevicename("web001");
			device.setId(1);
			devices.add(device);
			
			Device device1=new Device();
			device1.setDevicename("web002");
			device1.setId(2);
			devices.add(device1);
		}
		return devices;
	}

	public static Drawable getPic(String url){
		if(userpics.get(url)==null){
			try{
				Drawable drawable=Drawable.createFromStream(new URL(url).openStream(), "src");
				userpics.put(url, drawable);
			}catch(Exception e){
				
			}
		}
		return userpics.get(url);
	}
}
