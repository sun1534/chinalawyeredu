package com.cqmm.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.cqmm.bean.CmdLog;
import com.cqmm.bean.Command;
import com.cqmm.common.CurSession;
import com.cqmm.common.DataService;
import com.cqmm.common.Requests;
import com.cqmm.common.SysParams;

public class CommandStandard extends Activity {

	public CommandStandard() {
		super();
	}

	List<Command> commands;
	private ArrayAdapter<CharSequence> adapter;
	ListView lv;
	EditText tv;
	int[] commandid;
	int curcmdid;
	String curcmdname;
	String cmdresult;
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
		setContentView(R.layout.commandlist);
		lv=(ListView)findViewById(R.id.command_list);
		tv=(EditText)findViewById(R.id.command_result);
		new Thread(){
			@Override
			public void run() {
				
				 commands=DataService.getCommand(CommandList.deviceid,Command.TYPE_COMMON);
				 if(commands.size()>0){
					Message msg = new Message();
		            Bundle b = new Bundle();// 存放数据
		            b.putString("result", "cmdlist");
		            msg.setData(b);
		            
		            CommandStandard.this.handler.sendMessage(msg); // 向Handler发送消息,更新UI
				}
				
			}
		}.start();
	}

	
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle b = msg.getData();
			if(b.getString("result").equals("cmdlist")){
				String[] mStrings=new String[commands.size()];
				Log.i("commands.size();",""+commands.size());
				for(int i=0;i<commands.size();i++){
					mStrings[i]=commands.get(i).getCommandname();
				}
				lv.setAdapter(new ArrayAdapter<String>(CommandStandard.this,
		                android.R.layout.simple_list_item_1, mStrings));
				lv.setVisibility(View.VISIBLE);
				lv.setOnItemClickListener(new ListView.OnItemClickListener() {
				
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						lv.setClickable(false);
						curcmdid=commands.get(position).getId();
						curcmdname=commands.get(position).getCommandname();
						new Thread(){
							@Override
							public void run() {
								 cmdresult=Requests.execmd(CommandList.deviceid, curcmdid);
								 if(commands.size()>0){
									Message msg = new Message();
						            Bundle b = new Bundle();// 存放数据
						            b.putString("result", "cmdresult");
						            msg.setData(b);
						            
						            CommandStandard.this.handler.sendMessage(msg); // 向Handler发送消息,更新UI
								}
								
							}
						}.start();
					}
				
				});
			}else if(b.getString("result").equals("cmdresult")){
				lv.setClickable(true);
				tv.setText(cmdresult);
				CmdLog log=new CmdLog();
				log.setDevicename(CommandList.devicename);
				log.setOptname(curcmdname);
				log.setResult(cmdresult);
				log.setUserid(CurSession.userid);
				DataService.insertLog(CommandStandard.this, log);
			}
		}
	};

}