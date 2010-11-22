package com.cqmm.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.cqmm.bean.CmdLog;
import com.cqmm.common.DataService;
import com.cqmm.common.SysParams;

/**
 * 这暂时也是一个listview
 * 
 * @author 华锋
 * 
 */
public class LocalHistory extends Activity {

	private static final String EXTRA_ACCOUNT = "account";
	ListView lv;
	List<CmdLog> loglist;
	EditText tv;
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

		setContentView(R.layout.commandlist);
		setTitle(SysParams.SYS_NAME + "(" + SysParams.LOGIN_USER_NAME + "本地记录");
		
		lv = (ListView)findViewById(R.id.command_list);//new ListView(this);
		tv=(EditText)findViewById(R.id.command_result);
		loglist=DataService.getLoglist(LocalHistory.this);
		String[] mStrings=new String[loglist.size()];
		int i=0;
		for(CmdLog log:loglist){
			mStrings[i++]=log.getDevicename()+"->"+log.getOptname()+" "+log.getOptime();
			
		}
		lv.setAdapter(new ArrayAdapter<String>(LocalHistory.this,
                android.R.layout.simple_list_item_1, mStrings));
		
		lv.setVisibility(View.VISIBLE);
		lv.setOnItemClickListener(new ListView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				tv.setText(loglist.get(position).getResult());
			}
		});

	}
}