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
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.cqmm.bean.Device;
import com.cqmm.common.CurSession;
import com.cqmm.common.DataService;
import com.cqmm.common.Requests;
import com.cqmm.common.SysParams;

/**
 * 这是一个listview
 * 
 * @author 华锋
 * 
 */
public class DeviceList extends ListActivity {

	public DeviceList() {
		super();
	}

	List<Device> devices;
	private ArrayAdapter<CharSequence> adapter;
	Device curDevice;
	EditText et_loginname;
	EditText et_password;
	
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

		
		new Thread(){
			@Override
			public void run() {
				
				devices=DataService.getDevice();
				if(devices.size()>0){
					Message msg = new Message();
		            Bundle b = new Bundle();// 存放数据
		            b.putString("result", "true");
		            msg.setData(b);

		            DeviceList.this.handler.sendMessage(msg); // 向Handler发送消息,更新UI
				}
				
			}
		}.start();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		curDevice=devices.get(position);
		
		
		LayoutInflater factory = LayoutInflater.from(DeviceList.this);
        final View textEntryView = factory.inflate(R.layout.device_login_dialog, null);
         AlertDialog dlg = new AlertDialog.Builder(DeviceList.this)
	        .setTitle("登陆设备")
	        .setView(textEntryView)
	        .setPositiveButton("登陆", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	            	
	        		Log.v("cqmm", "device_loginname:"+et_loginname.getText().toString());
	        		Log.v("cqmm", "et_password:"+et_password.getText().toString());
	            	String loginresult=Requests.login_device(curDevice.getId(),et_loginname.getText().toString(), et_password.getText().toString());
//	            	loginresult="OK";
	            	if(loginresult.equals("OK")){
	            		DataService.add_open_device(curDevice);
		        		Bundle bundle=new Bundle();
		        		bundle.putInt("deviceid", curDevice.getId());
		        		bundle.putInt("curTab", 0);
		        		bundle.putString("devicename", curDevice.getDevicename());
		        		
		        		Intent intent = new Intent();
		        		intent.putExtras(bundle);
		        		intent.setClass(DeviceList.this, CommandList.class);
		        		startActivity(intent);
	            	}else{
	            		Toast.makeText(DeviceList.this, "登陆失败", Toast.LENGTH_SHORT).show();
	            	}
	            }
	        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	
	                /* User clicked cancel so do some stuff */
	            }
	        }).create();
         dlg.show();
		
         et_loginname=(EditText)dlg.findViewById(R.id.loginName1);//(EditText)findViewById(R.id.loginName1);
 		 et_password=(EditText)dlg.findViewById(R.id.pwdName1);
		


	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		super.onPrepareDialog(id, dialog);
		
		Log.v(SysParams.LOG_TAG, "onPrepareDialog");
	}

	@Override
	protected Dialog onCreateDialog(int i) {
		AlertDialog.Builder builder = new AlertDialog.Builder(DeviceList.this);

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
		

		AlertDialog dialog = builder.create();

		return dialog;

	}
	
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle b = msg.getData();
			if(b.getString("result").equals("true")){
				String[] mStrings=new String[devices.size()];
				Log.i("devices.size();",""+devices.size());
				for(int i=0;i<devices.size();i++){
					mStrings[i]=devices.get(i).getDevicename();
				}
				setListAdapter(new ArrayAdapter<String>(DeviceList.this,
		                android.R.layout.simple_list_item_1, mStrings));
				
			}
		}
	};

}