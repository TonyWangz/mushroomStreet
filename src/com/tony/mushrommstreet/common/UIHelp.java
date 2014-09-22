package com.tony.mushrommstreet.common;

import com.tony.mushrommstreet.activity.MessageActivity;

import android.content.Context;
import android.content.Intent;

public class UIHelp {
	
	public static void startMessageActivity(Context context){
		Intent intent = new Intent(context,MessageActivity.class);
		context.startActivity(intent);
	}
	
}
