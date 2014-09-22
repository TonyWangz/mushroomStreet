package com.tony.mushrommstreet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

import com.example.mushroomstreet.R;
import com.tony.mushrommstreet.bean.Collocations;
import com.tony.mushrommstreet.common.DensityUtil;

public class CollocationAdapter extends BasicAdapter<Collocations>{
	private LayoutParams layoutParams;
	private int width;
	
	public CollocationAdapter (Context context, int width){
		this.context = context;
		this.width = width;
	}
	
	private class ViewHolder{
		private ImageView bigImage;
		private GridView collocationsGV;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.item_collocation, null);
			holder = new ViewHolder();
			holder.bigImage = (ImageView) convertView.findViewById(R.id.big_image);
			holder.collocationsGV = (GridView) convertView.findViewById(R.id.grid_view);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		int itemWidth = (width + DensityUtil.dipToPx(context, 110)) / 2;
		layoutParams = new LayoutParams(width, itemWidth*4);
		holder.collocationsGV.setLayoutParams(layoutParams);
		Collocations collocations = data.get(position); 
		imageLoader.displayImage(collocations.getBigImage(), holder.bigImage);
		CollocationChildAdapter adapter = new CollocationChildAdapter(context, collocations.getCollocations(), width);
		holder.collocationsGV.setAdapter(adapter);
		
		return convertView;
	}
	
}
