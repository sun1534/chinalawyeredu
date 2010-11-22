package com.cqmm.activity;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cqmm.bean.Device;
import com.cqmm.common.CurSession;
import com.cqmm.common.DataService;
import com.cqmm.common.SysParams;

/**
 * 这是一个listview
 * 
 * @author 华锋
 * 
 */
public class CommandCustomer extends ListActivity {

	public CommandCustomer() {
		super();
	}


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		 取消掉title
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
//		 设置全屏
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		 WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setTitle(SysParams.SYS_NAME + "(" + CurSession.username + ")设备维护");

		
	}

	

}