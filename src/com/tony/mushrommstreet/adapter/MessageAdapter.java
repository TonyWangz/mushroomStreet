package com.tony.mushrommstreet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mushroomstreet.R;
import com.tony.mushrommstreet.bean.Message;

public class MessageAdapter extends BasicAdapter<Message>{
	
	public MessageAdapter(Context context){
		this.context = context;
	}
	
	private class ViewHolder{
		private TextView content;
		private ImageView avatarIV;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		Message message = data.get(position);
		if(convertView == null){
			if(message.getType() == 0){
				convertView = LayoutInflater.from(context).inflate(R.layout.item_mine_talk, null);
			}else{
				convertView = LayoutInflater.from(context).inflate(R.layout.item_other_talk, null);
			}
			holder = new ViewHolder();
			holder.avatarIV = (ImageView) convertView.findViewById(R.id.avatar);
			holder.content = (TextView) convertView.findViewById(R.id.message_content);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		imageLoader.displayImage(message.getAvatar(), holder.avatarIV);
		holder.content.setText(message.getContent());
		
		return convertView;
	}
	
	
	
}
