package com.tony.mushrommstreet.fragment;

import com.tony.mushrommstreet.activity.MainActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BasicFragment extends Fragment{
	
	public View contextView;
	protected MainActivity activity;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		activity = (MainActivity) getActivity();
		initView();
		initListener();
		initData();
		
		return contextView;
	}

	public void initView(){
		
	}
	
	public void initListener(){
		
	}
	
	public void initData(){
		
	}
	
}
