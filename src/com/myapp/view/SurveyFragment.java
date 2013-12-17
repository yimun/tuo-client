package com.myapp.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myapp.R;
import com.myapp.adapter.SurveyViewPagerAdapter;
import com.myapp.adapter.ViewPagerAdapter;
import com.myapp.model.AppInfo;
import com.myapp.view.SingleLayoutListView.OnLoadMoreListener;
import com.myapp.view.SingleLayoutListView.OnRefreshListener;

@SuppressLint({ "NewApi", "ValidFragment" })
public class SurveyFragment extends Fragment implements OnPageChangeListener{

	private Context context;
	private View view; 
	
	private static final String TAG = "SingleLayoutActivity";
	
	private static final int LOAD_DATA_FINISH = 10;
	private static final int REFRESH_DATA_FINISH = 11;

	private List<AppInfo> mList = new ArrayList<AppInfo>();
	private MyListAdapter mAdapter;
	private SingleLayoutListView mListView;
//	private ImageSwitcher imageSwitcher;
	private int mCount = 10;
	
	//����ͼƬ������
	//////////////////////////////////////////////////////////
	private ViewPager vp;
    private SurveyViewPagerAdapter sViewAdapter;
    private List<View> views;
    
    private ImageView[] dots;
    
    private int currentIndex;
	//////////////////////////////////////////////////////////
    
	
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
	
	public SurveyFragment() {
		// TODO Auto-generated constructor stub
	}
	
	public SurveyFragment(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		buildAppData();
		initView();
		initViewPagers();
		initDots();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.survey_fragment, container, false);
		return view;
	}	
	
	private void initView() {
		mAdapter = new MyListAdapter(context, mList);
		mListView = (SingleLayoutListView) view.findViewById(R.id.mListView);
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
	 private void initViewPagers() {
	        LayoutInflater inflater = LayoutInflater.from(context);

	        views = new ArrayList<View>();
	        // ��ʼ������ͼƬ�б�
	        views.add(inflater.inflate(R.layout.one, null));
	        views.add(inflater.inflate(R.layout.one, null));
	        views.add(inflater.inflate(R.layout.one, null));
	        views.add(inflater.inflate(R.layout.one, null));

	        // ��ʼ��Adapter
	        sViewAdapter = new SurveyViewPagerAdapter(views);

	        vp = (ViewPager) view.findViewById(R.id.viewpager);
	        vp.setAdapter(sViewAdapter);
	        // �󶨻ص�
	        vp.setOnPageChangeListener(this);
	    }
	
	private void initDots() {
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);

        dots = new ImageView[views.size()];

        // ѭ��ȡ��С��ͼƬ
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(true);// ����Ϊ��ɫ
        }

        currentIndex = 0;
        dots[currentIndex].setEnabled(false);// ����Ϊ��ɫ����ѡ��״̬
    }

    private void setCurrentDot(int position) {
        if (position < 0 || position > views.size() - 1
                || currentIndex == position) {
            return;
        }

        dots[position].setEnabled(false);
        dots[currentIndex].setEnabled(true);

        currentIndex = position;
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
	
	private class MyListAdapter extends BaseAdapter {

		private LayoutInflater mInflater;
		public List<AppInfo> mList;

		public MyListAdapter(Context pContext, List<AppInfo> pList) {
			mInflater = LayoutInflater.from(pContext);
			if (pList != null) {
				mList = pList;
			} else {
				mList = new ArrayList<AppInfo>();
			}
		}

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		@Override
		public long getItemId(int position) {
//			System.out.println("getItemId = " + position);
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (getCount() == 0) {
				return null;
			}
			// System.out.println("position = "+position);
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.list_item, null);

				holder = new ViewHolder();
				holder.mImage = (ImageView) convertView
						.findViewById(R.id.ivIcon);
				holder.mName = (TextView) convertView.findViewById(R.id.tvName);
				holder.mVer = (TextView) convertView.findViewById(R.id.tvVer);
				holder.mSize = (TextView) convertView.findViewById(R.id.tvSize);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			AppInfo ai = mList.get(position);
			holder.mImage.setImageBitmap(ai.getAppIcon());
			holder.mName.setText(ai.getAppName());
			holder.mVer.setText(ai.getAppVer());
			holder.mSize.setText(ai.getAppSize());

			return convertView;
		}
	}

	private static class ViewHolder {
		private ImageView mImage;
		private TextView mName;
		private TextView mVer;
		private TextView mSize;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		setCurrentDot(arg0);
	}
}