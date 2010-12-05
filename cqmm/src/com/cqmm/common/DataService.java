package com.cqmm.common;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.cqmm.bean.CmdLog;
import com.cqmm.bean.Command;
import com.cqmm.bean.Device;

public class DataService {
	
	
	private static Map<String,Drawable> userpics=new HashMap<String,Drawable>();

	
	public static List<Device> devices;
	public static List<Device> open_devices=new ArrayList<Device>();
	
	public static Map<Integer,String> cmd_reslut_map=new HashMap<Integer,String>();
	public static Map<String,List<Command>> commands=new HashMap<String,List<Command>>();
	
	
	public static List<Device> getDevice(){
		if(devices==null||devices.size()==0){
			devices=Requests.getDevice();
			
		}
		
		return devices;
	}

	
	
	//获取历史执行的命令列表-所有
	public static List<CmdLog> getLoglist(Context context){
		List<CmdLog> al=new ArrayList<CmdLog>();
		
		SQLiteHelper sqlh=new SQLiteHelper(context);
		SQLiteDatabase db=sqlh.getReadableDatabase();
		Cursor cursor=db.query("opt_log", null, null, null, null, null, null, "100");
		
		for(int i=0;i<cursor.getCount();i++){
			cursor.moveToPosition(i);
			CmdLog cmdlog=new CmdLog();
			cmdlog.setId(cursor.getInt(0));
			cmdlog.setDevicename(cursor.getString(2));
			cmdlog.setOptname(cursor.getString(3));
			cmdlog.setOptime(cursor.getString(5));
			cmdlog.setResult(cursor.getString(4));
			cmdlog.setUserid(cursor.getInt(1));
			al.add(cmdlog);
		}
		db.close();
		
		return al;
	}
	//获取历史执行的命令列表-指定设备的记录
	public static List<CmdLog> getLoglist_device(Context context,String devicename){
		List<CmdLog> al=new ArrayList<CmdLog>();
		
		SQLiteHelper sqlh=new SQLiteHelper(context);
		SQLiteDatabase db=sqlh.getReadableDatabase();
		Cursor cursor=db.query("opt_log", null, "devicename='"+devicename+"' and optime like '"+DateUtil.getSimpleDate2(new Date())+"%'", null, null, null, null, "100");
		
		for(int i=0;i<cursor.getCount();i++){
			cursor.moveToPosition(i);
			CmdLog cmdlog=new CmdLog();
			cmdlog.setId(cursor.getInt(0));
			cmdlog.setDevicename(cursor.getString(2));
			cmdlog.setOptname(cursor.getString(3));
			cmdlog.setOptime(cursor.getString(5));
			cmdlog.setResult(cursor.getString(4));
			cmdlog.setUserid(cursor.getInt(1));
			al.add(cmdlog);
		}
		db.close();
		
		return al;
	}
	//获取当天执行过的设备列表
	public static List<String> getLogDevice(Context context){
		List<String> al=new ArrayList<String>();
		
		SQLiteHelper sqlh=new SQLiteHelper(context);
		SQLiteDatabase db=sqlh.getReadableDatabase();
		Cursor cursor=db.query(true, "opt_log", new String[]{"devicename"}, null, null, null, null, null, "100");
		for(int i=0;i<cursor.getCount();i++){
			cursor.moveToPosition(i);
			
			al.add(cursor.getString(0));
			Log.v("sql-cursor.getString(0)-", cursor.getString(0));
		}
		db.close();
		
		return al;
	}
	
	
	public static List<Command> getCommand(int deviceid,int type){
		List<Command> devicecommand=commands.get(deviceid+","+type);
		if(devicecommand==null||devicecommand.size()==0){
			List<Command> lc=Requests.getCommand(deviceid);
			devicecommand=new ArrayList<Command>();
			
			for(Command c:lc){
				if(c.getCommandtype()==type){
					devicecommand.add(c);
				}
			}
			commands.put(deviceid+","+type, devicecommand);
		}
		return devicecommand;
	}
	
	
	public static void insertLog(Context context, CmdLog log) {
		SQLiteHelper sqlh=new SQLiteHelper(context);
		SQLiteDatabase db=sqlh.getReadableDatabase();
		ContentValues cv=new ContentValues();
		cv.put("userid", log.getUserid());
		cv.put("devicename", log.getDevicename());
		cv.put("optname", log.getOptname());
		cv.put("result", log.getResult());
		cv.put("optime", log.getOptime());
		long logid=db.insert("opt_log", null, cv);
		db.close();
	}
	
	
	public static void add_open_device(Device device){
		boolean isnew=true;
		for(Device d:open_devices){
			if(d.getId()==device.getId()){
				isnew=false;
			}
		}
		if(isnew){
			open_devices.add(device);
		}
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
