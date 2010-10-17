package com.cqmm.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cqmm.common.SysParams;

/**
 * 这是一个listview
 * 
 * @author 华锋
 * 
 */
public class Maintenance extends ListActivity {

	public Maintenance() {
		super();
	}

	private ArrayAdapter<CharSequence> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 取消掉title
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, list);

		setTitle(SysParams.SYS_NAME + "(" + SysParams.LOGIN_USER_NAME + ")设备维护");

		adapter = ArrayAdapter.createFromResource(this,
				R.array.maintencesArray,
				android.R.layout.simple_expandable_list_item_1);
		setListAdapter(adapter);

		
		
		// listView.setAdapter(adapter);

		// setContentView(listView);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		Log.v(SysParams.LOG_TAG, "onListItemClick===>position=>value:"
				+ position + "=>" + adapter.getItem(position));

		
		if (position == 0) {// 设备状态查询
			Intent intent = new Intent();
			intent.setClass(this, EquipmentState.class);
			startActivity(intent);
		} else if (position == 1) {// 用户状态查询
			Intent intent = new Intent();
			intent.setClass(this, UserStateQuery.class);
			startActivity(intent);
		} else if (position == 2) {// 用户投诉处理
			// 弹出对话框,暂时还未有这个功能
			showDialog(2);
		} else if (position == 3) {// 设备应急 操作
			showDialog(3);
		} else
			showDialog(4);
		
	
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		super.onPrepareDialog(id, dialog);
		
		Log.v(SysParams.LOG_TAG, "onPrepareDialog");
	}

	@Override
	protected Dialog onCreateDialog(int i) {
		AlertDialog.Builder builder = new AlertDialog.Builder(Maintenance.this);

//		builder.setTitle(SysParams.SYS_NAME);
		builder.setCancelable(false);
		if (i == 2)
			builder.setMessage("用户投诉处理功能暂未实现");
		else if (i == 3)
			builder.setMessage("设备应急 操作功能暂未实现");
		else
			builder.setMessage("您点了错误的选项");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.v(SysParams.LOG_TAG, "press the confirm button");
				dialog.dismiss();
			}
		});
//		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				// TODO Auto-generated method stub
//				Log.v(SysParams.LOG_TAG, "press the exit button");
//				dialog.dismiss();
//			}
//		});

		AlertDialog dialog = builder.create();

		return dialog;

	}

}