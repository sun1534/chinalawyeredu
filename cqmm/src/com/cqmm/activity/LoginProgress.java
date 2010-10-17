package com.cqmm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.cqmm.common.LoginAccount;
import com.cqmm.common.SysParams;

public class LoginProgress extends Activity {

	private static final String EXTRA_ACCOUNT = "account";

	public LoginProgress() {
		super();
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 取消掉title
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.login_progress);

		// YXT.url = LoginProgress.this.getResources().getText(R.string.url)
		// .toString();

		ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progress_login);
		mProgressBar.setIndeterminate(true);
		LoginAccount mAccount = (LoginAccount) getIntent()
				.getSerializableExtra(EXTRA_ACCOUNT);

		new Thread() {
			public void run() {
				// android.os.Process
				// .setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
				// AbstractRequest request = new LoginRequest(mAccount);
				// int resultcode = request.request();
				// if (resultcode == 1) {
				// setResult(RESULT_OK);
				// request.getResponse();
				// }
				int i = 0;
				while (i++ < 1) {
					try {
						Thread.sleep(500L);
//						if(i==3){
//							Log.v(SysParams.LOG_TAG,"退出登录");
//							setResult(Activity.RESULT_CANCELED);
//							break;
//						}
					} catch (Exception e) {
						setResult(Activity.RESULT_CANCELED);
						Log.v(SysParams.LOG_TAG,"登录有误");
					}
				}
				setResult(RESULT_OK);
//				setResult(Activity.RESULT_CANCELED);
				finish();
			}
		}.start();
	}
}