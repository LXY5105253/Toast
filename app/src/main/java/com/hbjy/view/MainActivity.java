package com.hbjy.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hbjy.toast.R;
import com.hbjy.toast.ToastFactory;

/**
 * <pre>
 *     @author : 李小勇
 *     date   : 2017/04/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init(){
		ToastFactory.getInstance(getApplicationContext()).makeTextShow("这是toast测试", Toast.LENGTH_SHORT);
	}
}
