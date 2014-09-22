package com.tony.mushrommstreet.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ImageSwitchAdapter extends PagerAdapter{
	
	private List<View> data ;
	private Context context;
	
	public ImageSwitchAdapter(Context context){
		this.context =context;
		data = new ArrayList<View>();
	}
	
	public void resetData(List<View> data){
		this.data.clear();
		this.data = data;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		if(data != null)
			return data.size();
		return 0;
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	@Override  
    public void destroyItem(ViewGroup container, int position, Object object)   {     
        container.removeView(data.get(position));//删除页卡  
    }  


    @Override  
    public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡         
         container.addView(data.get(position));//添加页卡  
         return data.get(position);  
    }  

}
