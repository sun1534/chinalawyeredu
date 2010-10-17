package com.cqmm.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.cqmm.common.SysParams;

/**
 * 设备状态查询
 * 
 * @author 华锋
 * 
 */
public class UserStateQueryResult extends Activity {

	public UserStateQueryResult() {
		super();
	}

	private TextView textView;
	private WebView queryResult;
	private Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				removeDialog(1); // 取消掉dialog并显示获取的内容
				String data="阿德发劳动法进来的说法<a href=\"http://www.sina.com.cn\">新浪新浪新浪新浪</a>";
//				queryResult.loadData(data, "text/html", "gb2312");
				queryResult.loadDataWithBaseURL(null, data, "text/html", "gb2312", null);
				break;
			}
			super.handleMessage(msg);
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 取消掉title
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setTitle(SysParams.SYS_NAME + "(" + SysParams.LOGIN_USER_NAME
				+ ")用户状态查询");

		this.setContentView(R.layout.user_state_query_result);

		textView=(TextView)super.findViewById(R.id.queryResultView);
		queryResult=(WebView)super.findViewById(R.id.userStateQueryWebview);
		
		
		
		ArrayList<String> selectedSgsns = getIntent().getStringArrayListExtra(
				"selectedSgsns");
		String mobile = getIntent().getStringExtra("userMobile");
		Log.v(SysParams.LOG_TAG, "选中的sgsns::" + selectedSgsns);

		textView.setText(mobile+"在"+selectedSgsns.toString().replace("[", "").replace("]", "")+"的查询结果");
		
		showDialog(1);

		new Thread() {
			public void run() {
				int i = 0;
				while (i++ < 5) {
					try {
						Thread.sleep(500L);
					} catch (Exception e) {
						setResult(Activity.RESULT_CANCELED);
						Log.v(SysParams.LOG_TAG, "登录有误");
					}
				}
				Message message = new Message();
				message.what = 1;	
				UserStateQueryResult.this.myHandler.sendMessage(message);
			}
		}.start();
	}

	@Override
	public Dialog onCreateDialog(int dialogId) {

		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setTitle("请等待");
		dialog.setMessage("正在查询中");
		return dialog;
	}

}