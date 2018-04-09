package com.hbjy.toast;

import android.view.View;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/04/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public interface IToast {
	void makeTextShow(String text, long duration);

	IToast setGravity(int gravity, int xOffset, int yOffset);

	IToast setDuration(long durationMillis);

	/**
	 * 不能和{@link #setText(CharSequence)}一起使用，要么{@link #setView(View)} 要么{@link #setText(CharSequence)}
	 * @param view display view
	 */
	IToast setView(View view);

	/**
	 * 设置间距
	 * @param horizontalMargin 水平间距
	 * @param verticalMargin 竖直间距
	 * @return
	 */
	IToast setMargin(float horizontalMargin, float verticalMargin);

	/**
	 * 不能和{@link #setView(View)}一起使用，要么{@link #setView(View)} 要么{@link #setText(CharSequence)}
	 * @param text display text
	 */
	IToast setText(CharSequence text);

	void show();

	void cancel();
}
