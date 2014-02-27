package com.myapp.ui;

import java.util.HashMap;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.myapp.R;
import com.myapp.base.BaseMessage;
import com.myapp.base.BaseUi;
import com.myapp.base.C;

public class FriendAdd extends BaseUi implements OnClickListener{

	private RelativeLayout bt_search;
	private EditText edt_content;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friendadd);
		initView();
	}

	private void initView() {
		bt_search = (RelativeLayout)findViewById(R.id.search);
		bt_search.setOnClickListener(this);
		edt_content = (EditText)findViewById(R.id.editText1);
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
			if (!message.isSuccess()) {
				toast("搜索无结果");
				return;
			}
			/*try {
				datalist.clear();
				datalist = (ArrayList<Bbs>) message.getResultList("Bbs");
				lv.setAdapter(new MyAdapter());
			} catch (Exception e) {
				e.printStackTrace();
				toastE(C.err.server);
			}
			break;*/
		}
	}

	@Override
	public void onClick(View arg0) {
		String content = edt_content.getText().toString();
		if(content.equalsIgnoreCase("")){
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
