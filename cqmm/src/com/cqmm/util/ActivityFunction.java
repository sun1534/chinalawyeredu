package com.cqmm.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ActivityFunction {

	/**
	 * ¼üÅÌ
	 * @param view
	 * @param context
	 */
	public static void inputMethodHandle(final View view,final Context context){
		view.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
				if (imm.isActive()) {
					imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
							InputMethodManager.HIDE_NOT_ALWAYS);
				}
			}
		});
	}
}
