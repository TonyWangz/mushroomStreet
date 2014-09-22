package com.tony.mushrommstreet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mushroomstreet.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tony.mushrommstreet.bean.Message;

public class MessageListAdapter extends BasicAdapter<Message> {
	
	public MessageListAdapter(Context context) {
		this.context = context;
	}
	
	private class ViewHolder{
		 private TextView nameTV, contentTV, timeTV;
		 private ImageView avatarIV;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.item_message, null);
			holder = new ViewHolder();
			holder.avatarIV = (ImageView) convertView.findViewById(R.id.avatar);
			holder.contentTV = (TextView) convertView.findViewById(R.id.content);
			holder.nameTV = (TextView) convertView.findViewById(R.id.name);
			holder.timeTV = (TextView) convertView.findViewById(R.id.time);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		Message message = data.get(position);
		imageLoader.displayImage(message.getAvatar(), holder.avatarIV);
		holder.contentTV.setText(message.getContent());
		holder.nameTV.setText(message.getName());
		holder.timeTV.setText(message.getTime());
		
		return convertView;
	}

}
