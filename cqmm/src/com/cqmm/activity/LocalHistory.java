package com.cqmm.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.cqmm.common.SysParams;

/**
 * 这暂时也是一个listview
 * 
 * @author 华锋
 * 
 */
public class LocalHistory extends Activity {

	private static final String EXTRA_ACCOUNT = "account";

	public LocalHistory() {
		super();
	}

	private ArrayAdapter<CharSequence> adapter = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 取消掉title
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setTitle(SysParams.SYS_NAME + "(" + SysParams.LOGIN_USER_NAME + "本地记录");
		
		ListView listView = new ListView(this);
		adapter = ArrayAdapter.createFromResource(this,
				R.array.locals,
				android.R.layout.simple_expandable_list_item_1);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(android.widget.AdapterView arg0,
					android.view.View arg1, int arg2, long arg3) {
				Log.v(SysParams.LOG_TAG, "setOnItemClickListener==" + arg0
						+ "," + arg1 + "," + arg2 + "===>"
						+ adapter.getItem(arg2) + ",," + arg3);
			}
		});

		listView.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(android.widget.AdapterView arg0,
					android.view.View arg1, int arg2, long arg3) {
				Log.v(SysParams.LOG_TAG, "setOnItemSelectedListener==" + arg0
						+ "," + arg1 + ",arg2" + ",," + arg3);
			}

			// Method descriptor #8 (Landroid/widget/AdapterView;)V
			// Signature: (Landroid/widget/AdapterView<*>;)V
			public void onNothingSelected(android.widget.AdapterView arg0) {
				Log.v(SysParams.LOG_TAG, "onNothingSelected==" + arg0);
			}
		});

		setContentView(listView);

	}
}