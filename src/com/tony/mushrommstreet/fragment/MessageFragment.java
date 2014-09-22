package com.tony.mushrommstreet.fragment;

/**
 * @author TonyWang
 * @time 2014 9.13
 * */

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.mushroomstreet.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tony.mushrommstreet.adapter.MessageListAdapter;
import com.tony.mushrommstreet.bean.Message;
import com.tony.mushrommstreet.common.UIHelp;

public class MessageFragment extends BasicFragment implements
		OnItemClickListener {

	private PullToRefreshListView pullToRefreshListView;
	private ListView messageLV;
	private MessageListAdapter adapter;
	private static MessageFragment instance = new MessageFragment();

	public static MessageFragment getInstance() {
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		contextView = inflater.inflate(R.layout.fragment_message, container,
				false);
		
		super.onCreateView(inflater, container, savedInstanceState);
		initActionBar();
		resetData();
		return contextView;
	}
	
	public void initActionBar(){
		activity.leftIcon.setVisibility(View.GONE);
		activity.title.setText("最近联系人");
		activity.title.setVisibility(View.VISIBLE);
		activity.rightIcon.setVisibility(View.VISIBLE);
	}
	
	@Override
	public void initView() {
		pullToRefreshListView = (PullToRefreshListView) contextView
				.findViewById(R.id.list_message);
		messageLV = pullToRefreshListView.getRefreshableView();
		adapter = new MessageListAdapter(activity);
	}

	@Override
	public void initListener() {
		pullToRefreshListView.setOnRefreshListener(refreshListener);
		messageLV.setOnItemClickListener(this);
	}

	@Override
	public void initData() {
		messageLV.setAdapter(adapter);

	}

	public List<Message> getData() {
		List<Message> messages = new ArrayList<Message>();
		Message message = new Message();
		message.setAvatar("http://img.my.csdn.net/uploads/201409/13/1410601717_7999.jpg");
		message.setContent("菇凉你好，这里是蘑菇街咨询小编，很高兴为你服务！");
		message.setName("TonyWang");
		message.setTime("14-09-05");
		messages.add(message);
		messages.add(message);
		return messages;
	}

	private OnRefreshListener refreshListener = new OnRefreshListener() {
		@Override
		public void onRefresh() {
			pullToRefreshListView.setLastUpdatedLabel(DateUtils.formatDateTime(
					activity.getApplicationContext(),
					System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
							| DateUtils.FORMAT_SHOW_DATE
							| DateUtils.FORMAT_ABBREV_ALL));
			resetData();
		}
	};

	public void resetData() {
		adapter.resetData(getData());
		pullToRefreshListView.onRefreshComplete();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		UIHelp.startMessageActivity(activity);

	}

}
