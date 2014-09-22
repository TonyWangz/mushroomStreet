package com.tony.mushrommstreet.activity;

/**
 * @author TonyWang
 * @time 2014 9.13
 * */

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.example.mushroomstreet.R;
import com.tony.mushrommstreet.adapter.MessageAdapter;
import com.tony.mushrommstreet.bean.Message;

public class MessageActivity extends BaseActivity {

	private ListView messageLV;
	private MessageAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		
		initActionBar();
		initView();
		initListener();
		initData();
	}

	
	public void initView() {
		messageLV = (ListView) findViewById(R.id.message_list);
		adapter = new MessageAdapter(this);
		leftIcon.setVisibility(View.VISIBLE);
		title.setVisibility(View.VISIBLE);
		leftIcon.setImageResource(R.drawable.back_btn);
		title.setText("蘑菇街小编");
	}

	public void initListener() {
		leftParent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

	public void initData() {
		
		messageLV.setAdapter(adapter);
		adapter.resetData(getData());
	}

	public List<Message> getData() {
		List<Message> data = new ArrayList<Message>();
		Message message1 = new Message();
		message1.setAvatar("http://img.my.csdn.net/uploads/201409/13/1410606006_6350.png");
		message1.setType(0);
		message1.setContent("你好，我是TonyWang，我想加入蘑菇街这个大家庭");
		Message message2 = new Message();
		message2.setAvatar("http://img.my.csdn.net/uploads/201409/13/1410601717_7999.jpg");
		message2.setType(1);
		message2.setContent("您好，请参加校招哦");
		data.add(message1);
		data.add(message2);

		return data;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
}
