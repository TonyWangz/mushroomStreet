package com.tony.mushrommstreet.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.mushroomstreet.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.tony.mushrommstreet.adapter.CollocationAdapter;
import com.tony.mushrommstreet.bean.Collocation;
import com.tony.mushrommstreet.bean.Collocations;
import com.tony.mushroomstreet.AppConfig;

import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CollocationFragment extends BasicFragment {
	
	private PullToRefreshListView pullToRefresh;
	private ListView collocationLV;
	private CollocationAdapter adapter;
	private int screenWidth;
	private static CollocationFragment instance = new CollocationFragment();

	public static CollocationFragment getInstance() {
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contextView = inflater.inflate(R.layout.fragment_collocation, null, false);
		screenWidth = AppConfig.wm.getDefaultDisplay().getWidth();
		super.onCreateView(inflater, container, savedInstanceState);
//		initActionBar();
		
		return contextView;
	}
	
	/*public void initActionBar(){
		activity.leftIcon.setVisibility(View.GONE);
		activity.title.setText("搭配");
		activity.title.setVisibility(View.VISIBLE);
		activity.rightIcon.setVisibility(View.VISIBLE);
	}*/
	
	@Override
	public void initView() {
		pullToRefresh =  (PullToRefreshListView) contextView.findViewById(R.id.list_collocation);
		collocationLV = pullToRefresh.getRefreshableView();
		adapter = new CollocationAdapter(activity, screenWidth);
	}

	@Override
	public void initListener() {
		pullToRefresh.setOnRefreshListener(refreshListener);
	}
	
	private OnRefreshListener refreshListener = new OnRefreshListener() {
		@Override
		public void onRefresh() {
			pullToRefresh.setLastUpdatedLabel(DateUtils.formatDateTime(
					activity.getApplicationContext(),
					System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
							| DateUtils.FORMAT_SHOW_DATE
							| DateUtils.FORMAT_ABBREV_ALL));
			resetData();
		}
	};
	
	/**现在模拟了一个耗时操作*/
	public void resetData(){
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				pullToRefresh.onRefreshComplete();
			}
		}, 300);
				
	}
	
	@Override
	public void initData() {
		collocationLV.setAdapter(adapter);
		adapter.resetData(getData());
	}
	
	/**如异步获取数据，就根据数据%8的结果为collocations的个数*/
	public List<Collocations> getData(){
		List<Collocations> data = new ArrayList<Collocations>();
		Collocations collocations1 = new Collocations();
		collocations1.setBigImage("http://img.my.csdn.net/uploads/201409/21/1411310258_7497.jpg");
		List<Collocation> datas = new ArrayList<Collocation>();
		Collocation data1 = new Collocation();
		data1.setAvatar("http://img.my.csdn.net/uploads/201409/13/1410601717_7999.jpg");
		data1.setSmallImages("http://img.my.csdn.net/uploads/201409/13/1410596120_5547.jpg");
		data1.setLoveCount("12");
		data1.setName("Tony");
		datas.add(data1);
		Collocation data2 = new Collocation();
		data2.setAvatar("http://img.my.csdn.net/uploads/201409/13/1410601717_7999.jpg");
		data2.setSmallImages("http://img.my.csdn.net/uploads/201409/13/1410596120_3038.jpg");
		data2.setLoveCount("12");
		data2.setName("Tony");
		datas.add(data2);
		Collocation data3 = new Collocation();
		data3.setAvatar("http://img.my.csdn.net/uploads/201409/13/1410601717_7999.jpg");
		data3.setSmallImages("http://img.my.csdn.net/uploads/201409/13/1410596120_3004.jpg");
		data3.setLoveCount("12");
		data3.setName("Tony");
		datas.add(data3);
		Collocation data4 = new Collocation();
		data4.setAvatar("http://img.my.csdn.net/uploads/201409/13/1410601717_7999.jpg");
		data4.setSmallImages("http://img.my.csdn.net/uploads/201409/13/1410597872_6670.jpg");
		data4.setLoveCount("12");
		data4.setName("Tony");
		datas.add(data4);
		Collocation data5 = new Collocation();
		data5.setAvatar("http://img.my.csdn.net/uploads/201409/13/1410601717_7999.jpg");
		data5.setSmallImages("http://img.my.csdn.net/uploads/201409/13/1410596104_5488.jpg");
		data5.setLoveCount("12");
		data5.setName("Tony");
		datas.add(data5);
		Collocation data6 = new Collocation();
		data6.setAvatar("http://img.my.csdn.net/uploads/201409/13/1410601717_7999.jpg");
		data6.setSmallImages("http://img.my.csdn.net/uploads/201409/13/1410596104_8745.jpg");
		data6.setLoveCount("12");
		data6.setName("Tony");
		datas.add(data6);
		Collocation data7 = new Collocation();
		data7.setAvatar("http://img.my.csdn.net/uploads/201409/13/1410601717_7999.jpg");
		data7.setSmallImages("http://img.my.csdn.net/uploads/201409/13/1410596103_3957.jpg");
		data7.setLoveCount("12");
		data7.setName("Tony");
		datas.add(data7);
		Collocation data8 = new Collocation();
		data8.setAvatar("http://img.my.csdn.net/uploads/201409/13/1410601717_7999.jpg");
		data8.setSmallImages("http://img.my.csdn.net/uploads/201409/13/1410596103_8317.jpg");
		data8.setLoveCount("12");
		data8.setName("Tony");
		datas.add(data8);
		collocations1.setCollocations(datas);
		data.add(collocations1);
		data.add(collocations1);
		data.add(collocations1);
		data.add(collocations1);
		data.add(collocations1);
		data.add(collocations1);
		data.add(collocations1);
		data.add(collocations1);
		return data;
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
//		initActionBar();
	}

}
