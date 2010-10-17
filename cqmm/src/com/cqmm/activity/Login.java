package com.cqmm.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.cqmm.common.LoginAccount;
import com.cqmm.common.SysParams;
import com.cqmm.util.CQPreferences;

public class Login extends Activity {
	/** Called when the activity is first created. */

	private String loginName;
	private String pwd;
	private boolean remberPwd;

	private CQPreferences preferences;

	public Login() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		preferences = new CQPreferences(this);
		// 去掉title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 去掉状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.login);

		View view = findViewById(R.id.layoutLogin);
		Button cancelBtn = (Button) findViewById(R.id.resetBtn);
		Button submiBtn = (Button) findViewById(R.id.submitBtn);
		final EditText loginNameText = (EditText) findViewById(R.id.loginName);
		final EditText pwdNameText = (EditText) findViewById(R.id.pwdName);
		final CheckBox remberPwdBox = (CheckBox) findViewById(R.id.login_pwd_remember);

		view.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				if (imm.isActive()) {
					imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
							InputMethodManager.HIDE_NOT_ALWAYS);
				}
			}
		});

		// 初始化登录名

		// 获取屏幕大小
		// 获取手机屏幕大小
		DisplayMetrics dm = getDisplay();
		SysParams.DISPLAY_WIDTH = dm.widthPixels;
		SysParams.DISPLAY_HEIGHT = dm.heightPixels;
		Log.v(SysParams.LOG_TAG, "" + dm.widthPixels + "X" + dm.heightPixels);

		// 登录的动作
		submiBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loginName = loginNameText.getText().toString();
				pwd = pwdNameText.getText().toString();
				remberPwd = remberPwdBox.isChecked();

				LoginAccount account = new LoginAccount();
				account.setLoginName(loginName);
				account.setPwd(pwd);
				account.setRemberPwd(remberPwd ? "1" : "0");

				Intent i = new Intent(Login.this, LoginProgress.class);
				i.putExtra(SysParams.LOGIN_EXTRA, account);
				// 跳转到登陆进度条的显示页面
				startActivityForResult(i, 1);

				// 这个要在那个线程里面处理
				// dismissDialog(1);

			}
		});

		// 退出确认
		cancelBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				AlertDialog.Builder builder = new Builder(Login.this);
				builder.setMessage(R.string.confirm_exit_str);
				builder.setTitle(R.string.tip_str);
				builder.setPositiveButton(R.string.dialogConfirm,
						new android.content.DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
								android.os.Process
										.killProcess(android.os.Process.myPid());
							}
						});
				builder.setNegativeButton(R.string.dialogExit,
						new android.content.DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				builder.create().show();
			}
		});

		String savedLoginName = preferences.getLatestUserName();
		if (savedLoginName != null && !savedLoginName.equals("")) {
			loginNameText.setText(savedLoginName);
			String savedPwd = preferences.getSavedPwd(savedLoginName);

			if (savedPwd != null && !savedPwd.equals("")) {
				pwdNameText.setText(savedPwd);
			}
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.v(SysParams.LOG_TAG, requestCode + ",,," + resultCode);
		if (resultCode == RESULT_OK) {
			Intent intent = new Intent();
			intent.setClass(Login.this, Index.class);
			startActivity(intent);

			preferences.addUserName(loginName);
			if (remberPwd)
				preferences.addPwd(loginName, pwd);

			// account.save(Preferences.getPreferences(this));
			finish();
		} else {
			Toast.makeText(this, R.string.loginFailueText, Toast.LENGTH_SHORT)
					.show();
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case 1: {
			ProgressDialog dialog = new ProgressDialog(this);

			dialog.setTitle("登录");
			dialog.setMessage("正在登录,请稍等...");
			dialog.setIndeterminate(true);
			dialog.setCancelable(true);
			return dialog;
		}
		case 2: {
			ProgressDialog dialog = new ProgressDialog(this);
			dialog.setMessage("Please wait while loading...");
			dialog.setIndeterminate(true);
			dialog.setCancelable(true);
			return dialog;
		}
		}
		return null;
	}

	private DisplayMetrics getDisplay() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm;
	}
}