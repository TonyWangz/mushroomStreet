package com.tony.mushrommstreet.activity;

/**
 * @author TonyWang
 * @time 2014 8.31
 * */

import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.mushroomstreet.R;


/**项目activity的基类，用来显示actionbar*/
public class BaseActivity extends SherlockFragmentActivity {

	public View leftParent, rightParent;
	public ImageView leftIcon, rightIcon;
	public TextView title;

	public void initActionBar() {
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setCustomView(R.layout.activity_base);
		title = (TextView) findViewById(R.id.title);
		leftIcon = (ImageView) findViewById(R.id.left_icon);
		rightIcon = (ImageView) findViewById(R.id.right_icon);
		leftParent = findViewById(R.id.left_parent);
		rightParent = findViewById(R.id.right_parent);
	}

}
