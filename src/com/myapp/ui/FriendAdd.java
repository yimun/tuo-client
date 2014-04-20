package com.myapp.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.myapp.R;
import com.myapp.adapter.ListAdapterRight;
import com.myapp.base.BaseMessage;
import com.myapp.base.BaseUi;
import com.myapp.base.C;
import com.myapp.model.Fans;
import com.myapp.model.ListRightInfo;

public class FriendAdd extends BaseUi implements OnClickListener {

	private RelativeLayout bt_search;
	private EditText edt_content;
	private ArrayList<Fans> datalist = new ArrayList<Fans>();
	private ArrayList<ListRightInfo> showlist = new ArrayList<ListRightInfo>();
	private ListView lv;
	private ListAdapterRight adapter;
	private ImageButton btn_back;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friendadd);
		initView();
	}

	private void initView() {
		bt_search = (RelativeLayout) findViewById(R.id.search);
		bt_search.setOnClickListener(this);
		edt_content = (EditText) findViewById(R.id.editContent);
		lv = (ListView) findViewById(R.id.lv);
		btn_back = (ImageButton) findViewById(R.id.ib_modify_to_friend);
		btn_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doFinish();
			}
			
		});
		adapter = new ListAdapterRight(this, showlist);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Fans item = datalist.get(arg2);
				new AlertDialog.Builder(getContext())
						.setCancelable(false)
						.setIcon(R.drawable.ic_launcher)
						.setTitle("拖后腿")
						.setMessage("是否添加好友“"+item.getName()+"”?")
						.setPositiveButton("确定",new DialogConfirmListener(arg2))
						.setNeutralButton("取消",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.cancel();
									}
								}).show();
			}

		});

	}

	class DialogConfirmListener implements DialogInterface.OnClickListener{
		
		int position;
		public DialogConfirmListener(int i){
			position = i;
		}
		@Override
		public void onClick(DialogInterface dialog,
				int which) {
			dialog.cancel();
			HashMap<String, String> urlParams = new HashMap<String, String>();
			urlParams.put("userid", datalist.get(position).getId());
			try {
				doTaskAsync(C.task.fansAdd, C.api.fansAdd, urlParams);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onTaskComplete(int taskId, BaseMessage message) {
		// TODO Auto-generated method stub
		super.onTaskComplete(taskId, message);
		switch (taskId) {
		case C.task.fansSearch:
			try {
				datalist.clear();
				datalist.addAll((ArrayList<Fans>) message.getResultList("Fans"));
				buildAppData(datalist);
				adapter.notifyDataSetChanged();
			} catch (Exception e) {
				e.printStackTrace();
				toast(C.err.network);
			}
			break;
		case C.task.fansAdd:
			if(message.isSuccess())
				toast("添加好友成功");
			else{
				toast(message.getMessage());
			}
			break;
		}
	}

	public void buildAppData(ArrayList<Fans> list) {
		showlist.clear();
		for (int i = 0; i < list.size(); i++) {
			ListRightInfo ai = new ListRightInfo();
			ai.setListRightIcon(BitmapFactory.decodeResource(getResources(),
					R.drawable.eio_icon));
			ai.setListRightName(list.get(i).getName()); // 姓名
			ai.setListRightContent(list.get(i).getSign());// 个人说明
			showlist.add(ai);
		}
	}

	@Override
	public void onClick(View arg0) {
		String content = edt_content.getText().toString();
		if (content.equalsIgnoreCase("")) {
			this.toast("输入不能为空");
			return;
		}
		HashMap<String, String> urlParams = new HashMap<String, String>();
		urlParams.put("name", content);
		try {
			this.doTaskAsync(C.task.fansSearch, C.api.fansSearch, urlParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
