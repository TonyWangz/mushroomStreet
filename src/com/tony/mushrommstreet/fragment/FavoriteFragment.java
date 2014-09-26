package com.tony.mushrommstreet.fragment;

/**
 * @author TonyWang
 * @time 2014 9.9
 * */

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.mushroomstreet.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tony.mushrommstreet.activity.MainActivity;
import com.tony.mushrommstreet.bean.Message;
import com.tony.mushrommstreet.waterfall.DuitangInfo;
import com.tony.mushrommstreet.waterfall.Helper;
import com.tony.mushrommstreet.waterfall.ImageFetcher;
import com.tony.mushrommstreet.waterfall.ScaleImageView;
import com.tony.mushrommstreet.waterfall.XListView;
import com.tony.mushrommstreet.waterfall.XListView.IXListViewListener;
import com.tony.mushrommstreet.widget.TopImage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.AsyncTask.Status;
import android.provider.Telephony.Sms.Conversations;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 此页面的瀑布流是参考github上的PinterestLikeAdapterView
 *
 * */
public class FavoriteFragment extends BasicFragment implements
		IXListViewListener {
	private DisplayImageOptions options;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private ImageFetcher mImageFetcher;
	private XListView mAdapterView = null;
	private StaggeredAdapter mAdapter = null;
	private int currentPage = 0;
	ContentTask task = new ContentTask((MainActivity) getActivity(), 2);
	private TopImage topView;
	private int type = 1;
	private List<View> images = new ArrayList<View>();
	private List<Message> datas = new ArrayList<Message>();

	/** 控制单例 */
	public static FavoriteFragment instance = new FavoriteFragment();

	public static FavoriteFragment getInstance() {
		return instance;
	}

	private class ContentTask extends
			AsyncTask<String, Integer, List<DuitangInfo>> {

		private Context mContext;
		private int mType = 1;

		public ContentTask(Context context, int type) {
			super();
			mContext = context;
			mType = type;
		}

		@Override
		protected List<DuitangInfo> doInBackground(String... params) {
			try {
				return parseNewsJSON(params[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<DuitangInfo> result) {
			if (mType == 1) {

				mAdapter.addItemTop(result);
				mAdapter.notifyDataSetChanged();
				mAdapterView.stopRefresh();

			} else if (mType == 2) {
				mAdapterView.stopLoadMore();
				mAdapter.addItemLast(result);
				mAdapter.notifyDataSetChanged();
			}

		}

		@Override
		protected void onPreExecute() {
		}

		public List<DuitangInfo> parseNewsJSON(String url) throws IOException {
			List<DuitangInfo> duitangs = new ArrayList<DuitangInfo>();
			String json = "";
			if (Helper.checkConnection(mContext)) {
				try {
					json = Helper.getStringFromUrl(url);

				} catch (IOException e) {
					Log.e("IOException is : ", e.toString());
					e.printStackTrace();
					return duitangs;
				}
			}
			Log.d("MainActiivty", "json:" + json);

			try {
				if (null != json) {
					JSONObject newsObject = new JSONObject(json);
					JSONObject jsonObject = newsObject.getJSONObject("data");
					JSONArray blogsJson = jsonObject.getJSONArray("blogs");

					for (int i = 0; i < blogsJson.length(); i++) {
						JSONObject newsInfoLeftObject = blogsJson
								.getJSONObject(i);
						DuitangInfo newsInfo1 = new DuitangInfo();
						newsInfo1
								.setAlbid(newsInfoLeftObject.isNull("albid") ? ""
										: newsInfoLeftObject.getString("albid"));
						newsInfo1
								.setIsrc(newsInfoLeftObject.isNull("isrc") ? ""
										: newsInfoLeftObject.getString("isrc"));
						newsInfo1.setMsg(newsInfoLeftObject.isNull("msg") ? ""
								: newsInfoLeftObject.getString("msg"));
						newsInfo1.setHeight(newsInfoLeftObject.getInt("iht"));
						duitangs.add(newsInfo1);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return duitangs;
		}
	}

	private void AddItemToContainer(int pageindex, int type) {
		if (task.getStatus() != Status.RUNNING) {
			String url = "http://www.duitang.com/album/1733789/masn/p/"
					+ pageindex + "/24/";
			Log.d("MainActivity", "current url:" + url);
			ContentTask task = new ContentTask((MainActivity) getActivity(),
					type);
			task.execute(url);

		}
	}

	public class StaggeredAdapter extends BaseAdapter {
		private Context mContext;
		private LinkedList<DuitangInfo> mInfos;
		private XListView mListView;

		public StaggeredAdapter(Context context, XListView xListView) {
			mContext = context;
			mInfos = new LinkedList<DuitangInfo>();
			mListView = xListView;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;
			DuitangInfo duitangInfo = mInfos.get(position);

			if (convertView == null) {
				LayoutInflater layoutInflator = LayoutInflater.from(parent
						.getContext());
				convertView = layoutInflator.inflate(R.layout.infos_list, null);
				holder = new ViewHolder();
				holder.imageView = (ScaleImageView) convertView
						.findViewById(R.id.news_pic);
				holder.contentView = (TextView) convertView
						.findViewById(R.id.news_title);
				convertView.setTag(holder);
			}

			holder = (ViewHolder) convertView.getTag();
			holder.imageView.setImageWidth(duitangInfo.getWidth());
			holder.imageView.setImageHeight(duitangInfo.getHeight());
			holder.contentView.setText(duitangInfo.getMsg());
			mImageFetcher.loadImage(duitangInfo.getIsrc(), holder.imageView);
			return convertView;
		}

		class ViewHolder {
			ScaleImageView imageView;
			TextView contentView;
			TextView timeView;
		}

		@Override
		public int getCount() {
			return mInfos.size();
		}

		@Override
		public Object getItem(int arg0) {
			return mInfos.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		public void addItemLast(List<DuitangInfo> datas) {
			mInfos.addAll(datas);
		}

		public void addItemTop(List<DuitangInfo> datas) {
			for (DuitangInfo info : datas) {
				mInfos.addFirst(info);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.fav_backgroud)
		.showImageForEmptyUri(R.drawable.fav_backgroud)
		.showImageOnFail(R.drawable.fav_backgroud)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.displayer(new RoundedBitmapDisplayer(10)) 
		.bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		contextView = inflater.inflate(R.layout.act_pull_to_refresh_sample,
				null);
		super.onCreateView(inflater, container, savedInstanceState);
//		initActionBar();
		return contextView;
	}

	// TODO
	public void getData() {
		if (type == 2)
			return;
		View view0 = LayoutInflater.from((MainActivity) getActivity()).inflate(
				R.layout.lunbotu, null);
		ImageView image0 = (ImageView) view0.findViewById(R.id.item_image);
		imageLoader.displayImage("http://img.my.csdn.net/uploads/201409/21/1411310258_7497.jpg", image0);
		View view1 = LayoutInflater.from((MainActivity) getActivity()).inflate(
				R.layout.lunbotu, null);
		ImageView image1 = (ImageView) view1.findViewById(R.id.item_image);
		imageLoader.displayImage("http://img.my.csdn.net/uploads/201409/21/1411310258_2775.jpg", image1);
		View view2 = LayoutInflater.from((MainActivity) getActivity()).inflate(
				R.layout.lunbotu, null);
		ImageView image2 = (ImageView) view2.findViewById(R.id.item_image);
		imageLoader.displayImage("http://img.my.csdn.net/uploads/201409/21/1411310257_1288.jpg", image2);
		View view3 = LayoutInflater.from((MainActivity) getActivity()).inflate(
				R.layout.lunbotu, null);
		ImageView image3 = (ImageView) view3.findViewById(R.id.item_image);
		imageLoader.displayImage("http://img.my.csdn.net/uploads/201409/21/1411310257_6731.jpg", image3);
		images.add(view0);
		images.add(view1);
		images.add(view2);
		images.add(view3);
		Message message1 = new Message();
		message1.setAvatar("http://img.my.csdn.net/uploads/201409/13/1410598242_8578.jpg");
		message1.setTitle("美妆");
		message1.setContent("特卖限时购");
		Message message2 = new Message();
		message2.setAvatar("http://img.my.csdn.net/uploads/201409/13/1410596120_3038.jpg");
		message2.setTitle("团购");
		message2.setContent("风衣4折起");
		Message message3 = new Message();
		message3.setAvatar("http://img.my.csdn.net/uploads/201409/13/1410597589_6918.jpg");
		message3.setTitle("达人");
		message3.setContent("把酷穿身上");
		datas.add(message1);
		datas.add(message2);
		datas.add(message3);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void initView() {
		mAdapterView = (XListView) contextView.findViewById(R.id.list);
		topView = new TopImage(activity);
		mAdapterView.addHeaderView(topView.getView());
		getData();
		topView.setView(images, datas);
		mAdapterView.setPullLoadEnable(true);
		mAdapterView.setXListViewListener(this);
		mAdapter = new StaggeredAdapter(activity, mAdapterView);
		mImageFetcher = new ImageFetcher(activity, 240);
		mImageFetcher.setLoadingImage(R.drawable.empty_photo);
	}
	
	/*public void initActionBar(){
		activity.leftIcon.setVisibility(View.GONE);
		activity.title.setText("搭配");
		activity.title.setVisibility(View.VISIBLE);
		activity.rightIcon.setVisibility(View.VISIBLE);
	}*/
	
	@Override
	public void initListener() {
		super.initListener();
	}

	@Override
	public void initData() {
	}

	@Override
	public void onPause() {
		type = 2;
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		mImageFetcher.setExitTasksEarly(false);
		mAdapterView.setAdapter(mAdapter);
		AddItemToContainer(currentPage, 2);
	}

	public void onDetach() {
		super.onDetach();
		try {
			Field childFragmentManager = Fragment.class
					.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);

		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onRefresh() {
		AddItemToContainer(++currentPage, 1);

	}

	@Override
	public void onLoadMore() {
		AddItemToContainer(++currentPage, 2);

	}

}
