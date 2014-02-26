package com.myapp.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.myapp.R;
import com.myapp.adapter.ListAdapterMicroBlog;
import com.myapp.base.BaseFragment;
import com.myapp.base.BaseMessage;
import com.myapp.base.C;
import com.myapp.model.AppInfo;
import com.myapp.model.Microblog;
import com.myapp.view.SingleLayoutListView.OnLoadMoreListener;
import com.myapp.view.SingleLayoutListView.OnRefreshListener;

@SuppressLint({ "NewApi", "ValidFragment" })
public class MicroBlogFragment extends BaseFragment {

	private Context context;
	private View view;
	private int uiId;
	private String userId;

	private static final String TAG = "MicroBlogFragment";

	private static final int LOAD_DATA_FINISH = 10;
	private static final int REFRESH_DATA_FINISH = 11;
	private static final int USER_MICROBLOG = 0;
	private static final int FRIENDS_MICROBLOG = 1;
	public static final int EIO_COMMENT = 2;  

	private List<Microblog> microBlogList = new ArrayList<Microblog>();
	private List<AppInfo> mList = new ArrayList<AppInfo>();
	private ListAdapterMicroBlog mAdapter;
	private SingleLayoutListView mListView;
	private int myXml = R.layout.list_micro_blog;

	private int currentPage = 1;
	private int currentState;

	public MicroBlogFragment(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public MicroBlogFragment(Context context, int myXml) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.myXml = myXml;
	}

	public MicroBlogFragment(Context context, int myXml, int uiId) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.myXml = myXml;
		this.uiId = uiId;
	}

	public MicroBlogFragment(Context context, int myXml, int uiId, String id) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.myXml = myXml;
		this.uiId = uiId;
		this.userId = id;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
		doTaskGetEioList(uiId, currentPage++);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(myXml, container, false);
		return view;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onTaskComplete(int taskId, BaseMessage message) {
		switch (taskId) {
		case C.task.blogList:
		case C.task.userBlogList:
		case C.task.listComment:
			try {
				microBlogList.clear();
				microBlogList.addAll((ArrayList<Microblog>) message
						.getResultList("Microblog")); // ��addAll()����������ֱ�Ӹ�ֵ�ܹ���ʡ���ٵ��ڴ�ռ�
				if (microBlogList.size() == 0 || microBlogList == null) {
					currentPage--;
				}
				buildAppData(microBlogList);
				doTaskFinish();
			} catch (Exception e) {
				e.printStackTrace();
				toast(e.getMessage());
			}
			break;
		}
	}

	@SuppressWarnings("static-access")
	public void doTaskGetEioList(int uiId, int pageId) {

		HashMap<String, String> urlParams = new HashMap<String, String>();
		if (uiId == this.USER_MICROBLOG) {
			urlParams.put("userId", userId);
			urlParams.put("pageId", "" + pageId);
			try {
				this.doTaskAsync(C.task.userBlogList, C.api.userBlogList,
						urlParams);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (uiId == this.FRIENDS_MICROBLOG) {
			urlParams.put("pageId", "" + pageId);
			try {
				this.doTaskAsync(C.task.blogList, C.api.blogList, urlParams);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (uiId == EIO_COMMENT) {
			urlParams.put("eioId", userId);
			urlParams.put("pageId", "" + pageId);
			try {
				this.doTaskAsync(C.task.listComment, C.api.listComment,
						urlParams);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void doTaskFinish() {
		switch (currentState) {
		case REFRESH_DATA_FINISH:
			if (mAdapter != null) {
				mAdapter.mList = mList;
				mAdapter.notifyDataSetChanged();
			}
			mListView.onRefreshComplete(); // ����ˢ�����
			break;
		case LOAD_DATA_FINISH:
			if (mAdapter != null) {
				mAdapter.mList = mList;
				mAdapter.notifyDataSetChanged();
			}
			mListView.onLoadMoreComplete(); // ���ظ������
			break;
		}
	}

	private void buildAppData(List<Microblog> microBlogList) {
		for (int i = 0; i < microBlogList.size(); i++) {
			AppInfo ai = new AppInfo();
			ai.setAppIcon(BitmapFactory.decodeResource(getResources(),
					R.drawable.eio_icon));
			ai.setAppName(microBlogList.get(i).getUsername());
			ai.setAppVer(microBlogList.get(i).getContent());
			ai.setAppSize(microBlogList.get(i).getUptime());
			mList.add(ai);
		}
	}

	private void initView() {
		mAdapter = new ListAdapterMicroBlog(context, mList);
		mListView = (SingleLayoutListView) view
				.findViewById(R.id.muserHomeListView);
		mListView.setAdapter(mAdapter);

		mListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// TODO ����ˢ��
				Log.e(TAG, "onRefresh");
				currentPage = 1;
				doTaskGetEioList(uiId, currentPage++);
				currentState = REFRESH_DATA_FINISH;
			}
		});

		mListView.setOnLoadListener(new OnLoadMoreListener() {

			@Override
			public void onLoadMore() {
				// TODO ���ظ���
				Log.e(TAG, "onLoad");
				doTaskGetEioList(uiId, currentPage++);
				currentState = LOAD_DATA_FINISH;
			}
		});

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// �˴���������position��mAdapter.getItemId()��ȡ��һ��;
				Log.e(TAG, "click position:" + position);
			}
		});
		mListView.setCanLoadMore(true);
		mListView.setCanRefresh(true);
		mListView.setAutoLoadMore(true);
		mListView.setMoveToFirstItemAfterRefresh(false); // true�᷵�ص�һ�У��û����鲻��
		mListView.setDoRefreshOnUIChanged(true);
	}
}
