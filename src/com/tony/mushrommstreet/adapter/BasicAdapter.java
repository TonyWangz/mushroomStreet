package com.tony.mushrommstreet.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class BasicAdapter<T> extends BaseAdapter {
	
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	protected List<T> data;
	protected Context context;

	@Override
	public int getCount() {
		if (data != null)
			return data.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (data != null)
			return data.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		if (data != null)
			return data.get(position).hashCode();
		return 0;
	}

	public void resetData(List<T> data) {
		this.data = data;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}

}
