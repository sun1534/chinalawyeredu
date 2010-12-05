package com.cqmm.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cqmm.bean.CmdLog;
import com.cqmm.common.DataService;
import com.cqmm.common.SysParams;

public class LocalHistory extends Activity {

	private static final String EXTRA_ACCOUNT = "account";
	ListView lv;
	List<CmdLog> loglist;
	List<String> devicelist;
	TextView tv;
	Spinner sp_device;
	String curDevice;
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
		tv=(TextView)findViewById(R.id.command_result);
		sp_device=(Spinner)findViewById(R.id.sp_device);
		
		//
		loglist=DataService.getLoglist(LocalHistory.this);
		devicelist=DataService.getLogDevice(LocalHistory.this);
		
		//设置设备选择的监听
		ArrayAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,devicelist);
		sp_device.setAdapter(adapter);
		sp_device.setVisibility(View.VISIBLE);
        
		sp_device.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				updatecmdlist();
				curDevice=devicelist.get(position);
				Log.v("cqmm", devicelist.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
        
	}
	private void updatecmdlist(){
		loglist=DataService.getLoglist_device(LocalHistory.this,devicelist.get(sp_device.getSelectedItemPosition()));
		String[] mStrings=new String[loglist.size()];
		int i=0;
		for(CmdLog log:loglist){
			mStrings[i++]=log.getOptname()+" "+log.getOptime().substring(11);
			
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