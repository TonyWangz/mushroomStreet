package com.tony.mushrommstreet.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.mushroomstreet.R;
import com.tony.mushrommstreet.bean.Collocation;
import com.tony.mushrommstreet.common.DensityUtil;
import com.tony.mushrommstreet.widget.RoundImageView;

public class CollocationChildAdapter extends BasicAdapter<Collocation>{
	private LayoutParams layoutParams;
	
	public CollocationChildAdapter(Context context, List<Collocation> data, int width){
		this.context = context;
		this.data = data;
		int itemWidth = width / 2;
		layoutParams = new LayoutParams(itemWidth, itemWidth);
	}
	
	private class ViewHolder{
		private ImageView logoIV;
		private TextView nameTV, countTV;
		private RoundImageView avatarIV;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.item_collocation_child, null);
			holder = new ViewHolder();
			holder.logoIV = (ImageView) convertView.findViewById(R.id.conllocation_item_image);
			holder.avatarIV = (RoundImageView) convertView.findViewById(R.id.avatar);
			holder.nameTV = (TextView) convertView.findViewById(R.id.name);
			holder.countTV = (TextView) convertView.findViewById(R.id.love_count);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		Collocation collocation = data.get(position); 
		holder.logoIV.setLayoutParams(layoutParams);
		imageLoader.displayImage(collocation.getSmallImage(), holder.logoIV);
		imageLoader.displayImage(collocation.getAvatar(), holder.avatarIV);
		holder.nameTV.setText(collocation.getName());
		holder.countTV.setText(collocation.getLoveCount());
		
		
		return convertView;
	}
	
}
