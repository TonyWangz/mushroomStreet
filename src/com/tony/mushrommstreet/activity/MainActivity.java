package com.tony.mushrommstreet.activity;

/**
 * @author TonyWang
 * @time 2014 8.31
 * */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.mushroomstreet.R;
import com.tony.mushrommstreet.fragment.CollocationFragment;
import com.tony.mushrommstreet.fragment.FavoriteFragment;
import com.tony.mushrommstreet.fragment.MessageFragment;
import com.tony.mushrommstreet.fragment.MineFragment;
import com.tony.mushrommstreet.fragment.SearchFragment;
import com.tony.mushroomstreet.AppConfig;

/**
 * 
 * 本应用所用页面都没有用到网络请求
 * 如果有需要，可以直接使用volley jar包 获得网络 数据，调用adapter里的resetData函数更新视图即可
 * 具体请求过程就没有写出，请自行添加
 * 
 * */

/** 用于显示主页面 */
public class MainActivity extends BaseActivity implements
		OnCheckedChangeListener {

	private RadioGroup radioGroup;
	/**
	 * 1表示FavoriteFragment 2表示SearchFragment 3表示CollocationFragment
	 * 4表示MessageFragment 5表示MineFragment
	 * */
	private int whichFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		AppConfig.initConfig(this);
		initActionBar();
		initView();
		initListener();
	}

	public void initView() {
		leftIcon.setVisibility(View.VISIBLE);
		rightIcon.setVisibility(View.VISIBLE);
		radioGroup = (RadioGroup) findViewById(R.id.bottom_menu);
		radioGroup.check(R.id.favorite);
		redirectTo(FavoriteFragment.getInstance());
		whichFragment = 1;
	}

	public void initListener() {
		radioGroup.setOnCheckedChangeListener(this);
	}
	
	public void setChildActionBar(int what) {
		switch (what) {
		case 0:
			leftIcon.setVisibility(View.GONE);
			title.setVisibility(View.GONE);
			rightIcon.setVisibility(View.VISIBLE);
			break;
		case 1:
			leftIcon.setVisibility(View.GONE);
			rightIcon.setVisibility(View.VISIBLE);
			title.setVisibility(View.VISIBLE);
			title.setText("分类");
			break;
		case 2:
			leftIcon.setVisibility(View.GONE);
			title.setText("搭配");
			title.setVisibility(View.VISIBLE);
			rightIcon.setVisibility(View.VISIBLE);
			break;
		case 3:
			leftIcon.setVisibility(View.GONE);
			title.setText("最近联系人");
			title.setVisibility(View.VISIBLE);
			rightIcon.setVisibility(View.VISIBLE);
			break;
		case 4:
			leftIcon.setImageResource(R.drawable.title_icon_set);
			title.setVisibility(View.GONE);
			rightIcon.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
	
	public void redirectTo(Fragment fragment) {
		FragmentTransaction beginTransaction = getSupportFragmentManager()
				.beginTransaction();
		beginTransaction.replace(R.id.fragment_container, fragment)
				.commitAllowingStateLoss();
	}

	public void switchContent(Fragment from, Fragment to) {
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		if (!to.isAdded()) { // 先判断是否被add过
			transaction.hide(from).add(R.id.fragment_container, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
		} else {
			transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
		}
	}
	
	
	/**单纯使用redirectTo切换fragment会导致fragment切换时候数据丢失
	 * 因为fragment被replace掉了，所以改用switchContent切换fragment
	 * 9.14
	 * switchContent会造成actionBar title异常，暂时换回redirecTo
	 * 9.19
	 * actionBarTitle 问题解决
	 * 9.26
	 * */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.favorite:
			setChildActionBar(0);
			switch (whichFragment) {
			case 2:
				switchContent(SearchFragment.getInstance(),
						FavoriteFragment.getInstance());
				break;
			case 3:
				switchContent(MessageFragment.getInstance(),
						FavoriteFragment.getInstance());
				break;
			case 4:
				switchContent(MessageFragment.getInstance(),
						FavoriteFragment.getInstance());
				break;
			case 5:
				switchContent(MineFragment.getInstance(),
						FavoriteFragment.getInstance());
				break;
			}
			whichFragment = 1;
			break;
		case R.id.category:
			setChildActionBar(1);
			switch (whichFragment) {
			case 1:
				switchContent(FavoriteFragment.getInstance(),
						SearchFragment.getInstance());
				break;
			case 3:
				switchContent(CollocationFragment.getInstance(),
						SearchFragment.getInstance());
				break;
			case 4:
				switchContent(MessageFragment.getInstance(),
						SearchFragment.getInstance());
				break;
			case 5:
				switchContent(MineFragment.getInstance(),
						SearchFragment.getInstance());
				break;
			}
			whichFragment = 2;
			break;
		case R.id.collocation:
			setChildActionBar(2);
			switch (whichFragment) {
			case 1:
				switchContent(FavoriteFragment.getInstance(),
						CollocationFragment.getInstance());
				break;
			case 2:
				switchContent(SearchFragment.getInstance(),
						CollocationFragment.getInstance());
				break;
			case 4:
				switchContent(MessageFragment.getInstance(),
						CollocationFragment.getInstance());
				break;
			case 5:
				switchContent(MineFragment.getInstance(),
						CollocationFragment.getInstance());
				break;
			}
			whichFragment = 3;
			break;
		case R.id.message:
			setChildActionBar(3);
			switch (whichFragment) {
			case 1:
				switchContent(FavoriteFragment.getInstance(),
						MessageFragment.getInstance());
				break;
			case 2:
				switchContent(SearchFragment.getInstance(),
						MessageFragment.getInstance());
				break;
			case 3:
				switchContent(CollocationFragment.getInstance(),
						MessageFragment.getInstance());
				break;
			case 5:
				switchContent(MineFragment.getInstance(),
						MessageFragment.getInstance());
				break;
			}
			whichFragment = 4;
			break;
		case R.id.mine:
			setChildActionBar(4);
			switch (whichFragment) {
			case 1:
				switchContent(FavoriteFragment.getInstance(),
						MineFragment.getInstance());
				break;
			case 2:
				switchContent(SearchFragment.getInstance(),
						MineFragment.getInstance());
				break;
			case 3:
				switchContent(CollocationFragment.getInstance(),
						MineFragment.getInstance());
				break;
			case 4:
				switchContent(MessageFragment.getInstance(),
						MineFragment.getInstance());
				break;
			}
			whichFragment = 5;
			break;
		default:
			break;
		}
		
		
	/*
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.favorite:
			redirectTo(FavoriteFragment.getInstance());
			break;
		case R.id.category:
			redirectTo(SearchFragment.getInstance());
			break;
		case R.id.collocation:
			redirectTo(CollocationFragment.getInstance());
			break;
		case R.id.message:
			redirectTo(MessageFragment.getInstance());
			break;
		case R.id.mine:
			redirectTo(MineFragment.getInstance());
			break;
		default:
			break;
		}*/

	}

}
