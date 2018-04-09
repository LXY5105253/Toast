package com.hbjy.toast;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/04/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class ToastFactory {
	private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
	private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

	private int mCheckNotification = -1;
	private volatile static ToastFactory sToastFactory;

	private IToast mIToast;

	private ToastFactory(Context context) {
		if (isNotificationEnabled(context)) {
			mIToast = new SystemToast(context);
		} else {
			mIToast = new CustomToast(context);
		}
	}

	public static IToast getInstance(Context context) {
		if (sToastFactory == null) {
			synchronized (ToastFactory.class) {
				if (sToastFactory == null) {
					sToastFactory = new ToastFactory(context);
				}
			}
		}
		return sToastFactory.mIToast;
	}

	private static boolean isNotificationEnabled(Context context) {
		AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
		ApplicationInfo appInfo = context.getApplicationInfo();
		String pkg = context.getApplicationContext().getPackageName();
		int uid = appInfo.uid;
		 /* Context.APP_OPS_MANAGER */
		Class appOpsClass;
		try {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				appOpsClass = Class.forName(AppOpsManager.class.getName());
				Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE,
						Integer.TYPE, String.class);

				Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
				int value = (int) opPostNotificationValue.get(Integer.class);
				return ((int) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager
						.MODE_ALLOWED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
