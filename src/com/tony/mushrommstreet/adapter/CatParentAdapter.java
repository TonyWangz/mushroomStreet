package com.tony.mushrommstreet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mushroomstreet.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CatParentAdapter extends BasicAdapter<String>{
	private int selectItem = -1;
	
	public CatParentAdapter(Context context){
		this.context = context;
	}
	
	
	private class ViewHolder{
		 private TextView titleTV;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_cat_title, null);
			holder.titleTV = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(position == selectItem){
			holder.titleTV.setTextColor(context.getResources().getColor(R.color.pink_select));
			holder.titleTV.setBackgroundColor(context.getResources().getColor(R.color.white));
		}else{
			holder.titleTV.setTextColor(context.getResources().getColor(R.color.line_dark));
			holder.titleTV.setBackgroundColor(context.getResources().getColor(R.color.gray_line));
		}
		holder.titleTV.setText(data.get(position));
		
		return convertView;
	}
	
	public void setSelectItem(int selectItem) {
		this.selectItem = selectItem;
	}
}
