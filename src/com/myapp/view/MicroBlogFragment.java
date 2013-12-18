package com.myapp.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.myapp.R;
import com.myapp.adapter.MyListAdapter;
import com.myapp.model.AppInfo;
import com.myapp.view.SingleLayoutListView.OnLoadMoreListener;
import com.myapp.view.SingleLayoutListView.OnRefreshListener;

@SuppressLint({ "NewApi", "ValidFragment" })
public class MicroBlogFragment extends Fragment {

	private Context context;
	private View view; 
	
	private static final String TAG = "MicroBlogFragment";
	
	private static final int LOAD_DATA_FINISH = 10;
	private static final int REFRESH_DATA_FINISH = 11;

	private List<AppInfo> mList = new ArrayList<AppInfo>();
	private MyListAdapter mAdapter;
	private SingleLayoutListView mListView;
//	private ImageSwitcher imageSwitcher;
	private int mCount = 10;
	
	private Handler mHandler = new Handler() {

		@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case REFRESH_DATA_FINISH:
				if (mAdapter != null) {
					mAdapter.mList = (ArrayList<AppInfo>) msg.obj;
					mAdapter.notifyDataSetChanged();
				}
				mListView.onRefreshComplete(); // ����ˢ�����
				break;
			case LOAD_DATA_FINISH:
				if (mAdapter != null) {
					mAdapter.mList.addAll((ArrayList<AppInfo>) msg.obj);
					mAdapter.notifyDataSetChanged();
				}
				mListView.onLoadMoreComplete(); // ���ظ������
				break;
			}
		};
	};
	
	public MicroBlogFragment() {
		// TODO Auto-generated constructor stub
	}
	
	public MicroBlogFragment(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		buildAppData();
		initView();
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.list_micro_blog, container, false);
		return view;
	}	
	
	private void initView() {
		mAdapter = new MyListAdapter(context, mList);
		mListView = (SingleLayoutListView) view.findViewById(R.id.muserHomeListView);
		/////////////////////////////////////////////////////////////////////////////
		mListView.setAdapter(mAdapter);

		mListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// TODO ����ˢ��
				Log.e(TAG, "onRefresh");
				loadData(0);
			}
		});

		mListView.setOnLoadListener(new OnLoadMoreListener() {

			@Override
			public void onLoadMore() {
				// TODO ���ظ���
				Log.e(TAG, "onLoad");
				loadData(1);
			}
		});

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// �˴���������position��mAdapter.getItemId()��ȡ��һ��;
				Log.e(TAG, "click position:" + position);
				// Log.e(TAG,
				// "__ mAdapter.getItemId() = "+mAdapter.getItemId(position));
			}
		});		
		mListView.setCanLoadMore(true);
		mListView.setCanRefresh(true);
		mListView.setAutoLoadMore(true);
		mListView.setMoveToFirstItemAfterRefresh(true);
		mListView.setDoRefreshOnUIChanged(true);
	}
	
	/**
	 * ����������~
	 * @param type 
	 * @date 2013-12-13 ����10:14:08
	 * @author JohnWatson
	 * @version 1.0
	 */
	public void loadData(final int type) {
		new Thread() {
			@Override
			public void run() {
				List<AppInfo> _List = null;
				switch (type) {
				case 0:
					mCount = 10;

					_List = new ArrayList<AppInfo>();
					for (int i = 1; i <= mCount; i++) {
						AppInfo ai = new AppInfo();

						ai.setAppIcon(BitmapFactory.decodeResource(
								getResources(), R.drawable.ic_launcher));
						ai.setAppName("Ӧ��Demo_" + i);
						ai.setAppVer("�汾: " + (i % 10 + 1) + "." + (i % 8 + 2)
								+ "." + (i % 6 + 3));
						ai.setAppSize("��С: " + i * 10 + "MB");

						_List.add(ai);
					}
					break;

				case 1:
					_List = new ArrayList<AppInfo>();
					int _Index = mCount + 10;

					for (int i = mCount + 1; i <= _Index; i++) {
						AppInfo ai = new AppInfo();

						ai.setAppIcon(BitmapFactory.decodeResource(
								getResources(), R.drawable.ic_launcher));
						ai.setAppName("Ӧ��Demo_" + i);
						ai.setAppVer("�汾: " + (i % 10 + 1) + "." + (i % 8 + 2)
								+ "." + (i % 6 + 3));
						ai.setAppSize("��С: " + i * 10 + "MB");

						_List.add(ai);
					}
					mCount = _Index;
					break;
				}

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (type == 0) { // ����ˢ��
				// Collections.reverse(mList); //����
					Message _Msg = mHandler.obtainMessage(REFRESH_DATA_FINISH,
							_List);
					mHandler.sendMessage(_Msg);
				} else if (type == 1) {
					Message _Msg = mHandler.obtainMessage(LOAD_DATA_FINISH,
							_List);
					mHandler.sendMessage(_Msg);
				}
			}
		}.start();
	}

	private void buildAppData() {
		for (int i = 1; i <= 10; i++) {
			AppInfo ai = new AppInfo();

			ai.setAppIcon(BitmapFactory.decodeResource(getResources(),
					R.drawable.ic_launcher));
			ai.setAppName("Ӧ��Demo_" + i);
			ai.setAppVer("�汾: " + (i % 10 + 1) + "." + (i % 8 + 2) + "."
					+ (i % 6 + 3));
			ai.setAppSize("��С: " + i * 10 + "MB");

			mList.add(ai);
		}
	}
}
