package com.tony.mushrommstreet.fragment;

/**
 * @author TonyWang
 * @time 2014 9.11
 * */

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;

import com.example.mushroomstreet.R;
import com.tony.mushrommstreet.activity.MainActivity;
import com.tony.mushrommstreet.adapter.CatChildAdapter;
import com.tony.mushrommstreet.adapter.CatParentAdapter;
import com.tony.mushrommstreet.bean.Cat;
import com.tony.mushroomstreet.AppConfig;

public class SearchFragment extends BasicFragment {

	private View contextView;
	private CatParentAdapter parentAdapter;
	private CatChildAdapter childAdapter;
	private ListView parentCat;
	private GridView childCat;
	private int screenWidth;
	private static SearchFragment instance = new SearchFragment();

	public static SearchFragment getInstance() {
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = (MainActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contextView = inflater.inflate(R.layout.fragment_category, null, false);
		screenWidth = AppConfig.wm.getDefaultDisplay().getWidth();
		super.onCreateView(inflater, container, savedInstanceState);
//		initActionBar();
		
		return contextView;
	}

	/*public void initActionBar() {
		activity.leftIcon.setVisibility(View.GONE);
		activity.rightIcon.setVisibility(View.VISIBLE);
		activity.title.setVisibility(View.VISIBLE);
		activity.title.setText("分类");
	}*/

	@Override
	public void initView() {
		parentCat = (ListView) contextView.findViewById(R.id.list_cat_parent);
		childCat = (GridView) contextView.findViewById(R.id.list_cat_child);
		parentAdapter = new CatParentAdapter(activity);
		childAdapter = new CatChildAdapter(activity, screenWidth);
	}

	@Override
	public void initListener() {
		parentCat.setOnItemClickListener(listItemClickListener);
	}

	@Override
	public void initData() {
		parentCat.setAdapter(parentAdapter);
		parentAdapter.setSelectItem(0);
		parentAdapter.resetData(getParentData());
		childCat.setAdapter(childAdapter);
		childAdapter.resetData(getChildData(0));
	}

	/** 用于获得一级分类数据，可以根据自己需求改用别的方式，比如服务器读取 */
	public List<String> getParentData() {
		List<String> data = new ArrayList<String>();
		data.add("推荐");
		data.add("上衣");
		data.add("裙子");
		data.add("裤子");
		data.add("鞋子");
		data.add("包包");
		data.add("配饰");
		return data;
	}

	/** 用于获得二级分类的数据 */
	private List<Cat> getChildData(int position) {
		List<Cat> data = new ArrayList<Cat>();
		data.clear();
		Cat cat1 = new Cat();
		cat1.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410596120_3038.jpg");
		cat1.setTitle("风衣");
		Cat cat2 = new Cat();
		cat2.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410596120_5547.jpg");
		cat2.setTitle("毛衣");
		Cat cat3 = new Cat();
		cat3.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410596120_3004.jpg");
		cat3.setTitle("寸衫");
		Cat cat4 = new Cat();
		cat4.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410596119_9776.jpg");
		cat4.setTitle("裤子");
		Cat cat5 = new Cat();
		cat5.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410596104_5488.jpg");
		cat5.setTitle("卫衣");
		Cat cat6 = new Cat();
		cat6.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410596104_8745.jpg");
		cat6.setTitle("家居服");
		Cat cat7 = new Cat();
		cat7.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410596103_3957.jpg");
		cat7.setTitle("连衣裙");
		Cat cat8 = new Cat();
		cat8.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410596103_8317.jpg");
		cat8.setTitle("牛仔外套");
		Cat cat9 = new Cat();
		cat9.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410596103_9998.jpg");
		cat9.setTitle("开衫");
		Cat cat10 = new Cat();
		cat10.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410597589_5515.jpg");
		cat10.setTitle("短裙");
		Cat cat11 = new Cat();
		cat11.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410597589_9693.jpg");
		cat11.setTitle("连衣裙");
		Cat cat12 = new Cat();
		cat12.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410597589_6918.jpg");
		cat12.setTitle("蓬蓬裙");
		Cat cat13 = new Cat();
		cat13.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410597873_8942.jpg");
		cat13.setTitle("背带裤");
		Cat cat14 = new Cat();
		cat14.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410597872_6670.jpg");
		cat14.setTitle("背包");
		Cat cat15 = new Cat();
		cat15.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410597873_1547.jpg");
		cat15.setTitle("运动鞋");
		Cat cat16 = new Cat();
		cat16.setLogoIcon("http://img.my.csdn.net/uploads/201409/13/1410598242_8578.jpg");
		cat16.setTitle("配饰");
		switch (position) {
		case 0:
			data.add(cat15);
			data.add(cat14);
			data.add(cat13);
			data.add(cat12);
			data.add(cat11);
			data.add(cat10);
			data.add(cat9);
			data.add(cat8);
			data.add(cat7);
			data.add(cat6);
			data.add(cat5);
			data.add(cat4);
			data.add(cat3);
			data.add(cat2);
			data.add(cat1);
			break;
		case 1:
			data.add(cat1);
			data.add(cat7);
			data.add(cat6);
			data.add(cat5);
			data.add(cat3);
			data.add(cat2);
			data.add(cat9);
			data.add(cat8);
			break;
		case 2:
			data.add(cat12);
			data.add(cat11);
			data.add(cat10);
		case 3:
			data.add(cat13);
			break;
		case 4:
			data.add(cat15);
			break;
		case 5:
			data.add(cat14);
			break;
		case 6:
			data.add(cat16);
			break;
		default:
			break;
		}

		return data;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		try {
			Field childFragmentManager = Fragment.class
					.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private OnItemClickListener listItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			parentAdapter.setSelectItem(position);
			parentAdapter.notifyDataSetChanged();
			childAdapter.resetData(getChildData(position));

		}
	};

	private OnItemClickListener childListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub

		}
	};
	
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
