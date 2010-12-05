package com.cqmm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.cqmm.common.CurSession;
import com.cqmm.common.LoginAccount;
import com.cqmm.common.Requests;
import com.cqmm.common.SysParams;

public class LoginProgress extends Activity {

	private static final String EXTRA_ACCOUNT = "account";
	LoginAccount mAccount;
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
		mAccount = (LoginAccount) getIntent().getExtras().getSerializable(SysParams.LOGIN_EXTRA);

		new Thread() {
			public void run() {
				 String loginresult=Requests.login(mAccount.getLoginName(), mAccount.getPwd());
//				 String loginresult="OK,1,管理员";
				 if (loginresult.indexOf("OK")!=-1) {
					 String[] rs=loginresult.split(",");
					 CurSession.userid=Integer.parseInt(rs[1]);
					 CurSession.username=rs[2];
					 setResult(RESULT_OK);
				 }
				 
				finish();
			}
		}.start();
	}
}