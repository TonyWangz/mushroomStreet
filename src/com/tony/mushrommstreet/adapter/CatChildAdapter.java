package com.tony.mushrommstreet.adapter;

import java.util.List;

import com.example.mushroomstreet.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tony.mushrommstreet.bean.Cat;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class CatChildAdapter extends BasicAdapter<Cat>{
	private DisplayImageOptions options;
	private int width;
	
	public CatChildAdapter(Context context, int width){
		this.context = context;
		this.width = width;
		
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.init_icon_1)
		.showImageForEmptyUri(R.drawable.init_icon_1)
		.showImageOnFail(R.drawable.init_icon_1)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.displayer(new RoundedBitmapDisplayer(10)) 
		.bitmapConfig(Bitmap.Config.RGB_565).build();
	}
	
	
	private class ViewHoler {
		TextView cText;
		ImageView cImage;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHoler holder;
		if (convertView == null) {
			holder = new ViewHoler();
			LayoutInflater mInflater = LayoutInflater.from(context);
			convertView = mInflater.inflate(R.layout.item_cat_child,
					null);
			holder.cText = (TextView) convertView
					.findViewById(R.id.child_cat_name_text);
			holder.cImage = (ImageView) convertView.findViewById(R.id.child_img);
			convertView.setTag(holder);
		} else {
			holder = (ViewHoler) convertView.getTag();
		}
		Log.i("test", "width: " + width);
		LayoutParams params = new LayoutParams(width*1/4, width*7/32);
		holder.cImage.setLayoutParams(params);
		Cat cat = data.get(position);
		Log.i("test", "url: " + cat.getLogoIcon());
		imageLoader.displayImage(cat.getLogoIcon(), holder.cImage, options);
		holder.cText.setText(cat.getTitle());
		
		return convertView;
	}

}
