package com.tony.mushrommstreet.widget;

/**
 * @author TonyWang
 * @time 2014 9.9
 * */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.example.mushroomstreet.R;
import com.tony.mushrommstreet.adapter.ImageSwitchAdapter;
import com.tony.mushrommstreet.adapter.MenuAdapter;
import com.tony.mushrommstreet.bean.Message;
import com.viewpagerindicator.CirclePageIndicator;
/**用于显示轮播图和下面的菜单*/
public class TopImage {
	private ViewPager viewPager;
	private GridView gridView;
	private View view;
	private Context context;
	private CirclePageIndicator indicator;
	private ImageSwitchAdapter adapter;
	private MenuAdapter gridAdapter;
	
	public TopImage(Context context) {
		this.context = context;
	}

	public View getView() {
		view = LayoutInflater.from(context).inflate(R.layout.widget_top_image,
				null);
		viewPager = (ViewPager) view.findViewById(R.id.viewpager);
		gridView = (GridView) view.findViewById(R.id.grid_view);
		viewPager.setLayoutParams(new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, 320));
		adapter = new ImageSwitchAdapter(context);
		indicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
		viewPager.setAdapter(adapter);
		indicator.setViewPager(viewPager);
		gridAdapter = new MenuAdapter(context);
		gridView.setAdapter(gridAdapter);
		
		return view;
	}
	
	public void setView(List<View> images, List<Message> data) {
		adapter.resetData(images);
		gridAdapter.resetData(data);
	}

}
