package com.tony.mushrommstreet.fragment;

/**
 * @author TonyWang 
 * @time 2014 9.14
 * */

import com.example.mushroomstreet.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MineFragment extends BasicFragment {

	private static MineFragment instance = new MineFragment();

	public static MineFragment getInstance() {
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contextView = inflater.inflate(R.layout.fragment_mine, null, false);
		super.onCreateView(inflater, container, savedInstanceState);
		initActionBar();
		
		return contextView;
	}

	public void initActionBar() {
		activity.leftIcon.setImageResource(R.drawable.title_icon_set);
		activity.title.setVisibility(View.GONE);
		activity.rightIcon.setVisibility(View.VISIBLE);
	}

	@Override
	public void initView() {
		super.initView();
	}

	@Override
	public void initListener() {
		super.initListener();
	}

	@Override
	public void initData() {
		super.initData();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		initActionBar();
	}

}
