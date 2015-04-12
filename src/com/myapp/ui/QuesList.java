package com.myapp.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.myapp.R;
import com.myapp.adapter.AdapterQuesList;
import com.myapp.base.BaseMessage;
import com.myapp.base.BaseModel;
import com.myapp.base.BaseUi;
import com.myapp.base.C;
import com.myapp.model.Eio;
import com.myapp.model.EioResult;
import com.myapp.model.SimpleSelectQuestion;

public class QuesList extends BaseUi implements OnClickListener{
	
	public static final int SIMPLE_SELECT_QUES = 1;
	public static final int INPUT_QUES = 2;
	public static final int MULIT_SELECT_QUES = 3;

	private ArrayList<? extends BaseModel> quesList = new ArrayList<BaseModel>();
	private int quesType; // 问卷的问题类型
	private Eio eio;     // 主问卷
	private ListView lv;
	private ImageButton cancel;
	private Button submit;
	private AlertDialog D_cancel;
	private AlertDialog D_submit;
	private AdapterQuesList adapter;
	private TextView tvTitle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eio_queslist);
		eio = (Eio) getIntent().getExtras().getSerializable("Eio");
		quesType = Integer.parseInt(eio.getTypeid());
		initWidget();
		initListView();
	}


	private void initWidget() {
		lv = (ListView)findViewById(R.id.listView1);
		cancel = (ImageButton)findViewById(R.id.b_cancel);
		submit = (Button)findViewById(R.id.b_submit);
		tvTitle = (TextView)findViewById(R.id.textTitle);
		tvTitle.setText(eio.getTitle());
		
		cancel.setOnClickListener(this);
		submit.setOnClickListener(this);
		
		D_cancel = new AlertDialog.Builder(this)
		.setTitle("退出")
		.setMessage("确定退出吗？")
		.setPositiveButton("是",new DialogInterface.OnClickListener() { 
	           public void onClick(DialogInterface dialog, int which) { 
	        	   doFinish(); 
	           } 
	       }) 
		.setNegativeButton("否", null) 
		.create();
		
		D_submit = new AlertDialog.Builder(this)
		.setTitle("提交")
		.setMessage("确定提交吗？")
		.setPositiveButton("是",new DialogInterface.OnClickListener() { 
	           public void onClick(DialogInterface dialog, int which) { 
	        	   doTaskGetResult();
	           } 
	       }) 
		.setNegativeButton("否", null)
		.create();
	}

	

	private void initListView() {
		// TODO Auto-generated method stub
		adapter = new AdapterQuesList(this,quesList,quesType);
		lv.setAdapter(adapter);
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		doTaskGetQuesList();
	}

	void doTaskGetQuesList() {
//		showLoadBar();
		HashMap<String, String> urlParams = new HashMap<String, String>();
		urlParams.put("eioId", eio.getId());
		try {
			this.doTaskAsync(C.task.quesList, C.api.quesList, urlParams);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void doTaskGetResult()  { // 提交并返回结果
		showLoadBar();
		JSONObject object = new JSONObject();
		for(int i=0;i < quesList.size();i++){
			try {
				SimpleSelectQuestion item = (SimpleSelectQuestion)quesList.get(i);
				object.put(item.getId(), item.getMyAnswer()+"");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		HashMap<String, String> urlParams = new HashMap<String, String>();
		urlParams.put("eioId", eio.getId());
		urlParams.put("result", object.toString()); // TODO
		try {
			this.doTaskAsync(C.task.eioDispose, C.api.eioDispose, urlParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onTaskComplete(int taskId, BaseMessage message) {
		// TODO Auto-generated method stub
		hideLoadBar();
		super.onTaskComplete(taskId, message);
		switch (taskId) {
		case C.task.quesList:
			switch (quesType) {
			case SIMPLE_SELECT_QUES: // 单选
				try {
					quesList.clear();
					((ArrayList<SimpleSelectQuestion>)quesList).addAll(
							(ArrayList<SimpleSelectQuestion>) message
							.getResultList("SimpleSelectQuestion"));
					Log.i("QuesList","list num=" + quesList.size());
					adapter.notifyDataSetChanged();
				} catch (Exception e) {
					e.printStackTrace();
					toast(e.getMessage());
				}
				break;
			case INPUT_QUES:    // 填空题
				// TODO
				break;
			case MULIT_SELECT_QUES: // 多选
				// TODO
				break;
			}
			break;

		case C.task.eioDispose:
			try {
				EioResult result = (EioResult) message.getResult("EioResult");
				Bundle bd = getIntent().getExtras();
				bd.putSerializable("result", result);
				forward(Result.class, bd);

			} catch (Exception e) {
				e.printStackTrace();
				toast(e.getMessage());
			}
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.b_cancel:
			D_cancel.show();
			break;
		case R.id.b_submit:
			D_submit.show();
			break;
		}
		
	}

}
