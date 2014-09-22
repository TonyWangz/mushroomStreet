package com.tony.mushrommstreet.adapter;

import com.example.mushroomstreet.R;
import com.tony.mushrommstreet.bean.Message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdapter extends BasicAdapter<Message> {

	public MenuAdapter(Context context) {
		this.context = context;
	}

	private class ViewHolder {
		private ImageView logoIV;
		private TextView titleTV, contentTV;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		Message message = data.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_top_image, null);
			holder = new ViewHolder();
			holder.logoIV = (ImageView) convertView.findViewById(R.id.top_logo);
			holder.contentTV = (TextView) convertView
					.findViewById(R.id.top_content);
			holder.titleTV = (TextView) convertView.findViewById(R.id.top_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		imageLoader.displayImage(message.getAvatar(), holder.logoIV);
		holder.contentTV.setText(message.getContent());
		holder.titleTV.setText(message.getTitle());

		return convertView;
	}

}
