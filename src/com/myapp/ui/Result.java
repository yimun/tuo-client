package com.myapp.ui;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.myapp.R;
import com.myapp.base.BaseMessage;
import com.myapp.base.BaseUi;
import com.myapp.base.C;
import com.myapp.model.Eio;
import com.myapp.model.EioResult;

public class Result extends BaseUi {

	private Button btn_addcomment;
	private Button btn_parise;
	private ImageButton btn_back;
	private Button btn_stamp;
	private TextView tv_praise;
	private TextView tv_stamp;
	private TextView tv_score;
	private TextView tv_resultmess;
	private EditText edt_content;

	private EioResult eioResult;
	private Eio eio;
	private int praiseCount = 0;
	private int stampCount = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eio_result);
		Bundle bd = getIntent().getExtras();
		eioResult = (EioResult) bd.getSerializable("result");
		eio = (Eio) bd.getSerializable("Eio");
		// str = new StringBuffer();
		getWidgetId();
		setEvent();
		setData();
	}

	private void setData() {
		tv_praise.setText(eio.getPraisecount());
		tv_stamp.setText(eio.getStampcount());
		
		String[] result = eioResult.getResult().split("#");
		tv_score.setText(result[1]);
		tv_resultmess.setText(result[2]);
		
	}

	private void getWidgetId() {
		btn_addcomment = (Button) findViewById(R.id.button1);
		btn_parise = (Button) findViewById(R.id.button2);
		btn_stamp = (Button) findViewById(R.id.button3);
		tv_praise = (TextView) findViewById(R.id.praise);
		tv_stamp = (TextView) findViewById(R.id.cai);
		tv_score = (TextView) findViewById(R.id.score);
		tv_resultmess = (TextView) findViewById(R.id.resultdata);
		edt_content = (EditText)findViewById(R.id.editText1);
		btn_back = (ImageButton) findViewById(R.id.img_b_back);
	}

	private void setEvent() {
		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				doFinish();
			}
		});

		btn_addcomment.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				String content = edt_content.getText().toString();
				if(content.isEmpty()){
					toast("内容不能为空！");
					return;
				}
				showLoadBar();
				HashMap<String, String> urlParams = new HashMap<String, String>();
				urlParams.put("eioId", eio.getId());
				urlParams.put("content",content );
				try {
					doTaskAsync(C.task.eioComment, C.api.eioComment, urlParams);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}

		});
		btn_parise.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(praiseCount != 0){
					toast("已赞过！");
					return;
				}
				HashMap<String, String> urlParams = new HashMap<String, String>();
				urlParams.put("eioId", eio.getId());
				try {
					doTaskAsync(C.task.eioPraise, C.api.eioPraise, urlParams);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}

		});
		btn_stamp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(stampCount != 0){
					toast("已踩过！");
					return;
				}
				HashMap<String, String> urlParams = new HashMap<String, String>();
				urlParams.put("eioId", eio.getId());
				try {
					doTaskAsync(C.task.eioStamp, C.api.eioStamp, urlParams);
				} catch (Exception e) {
					e.printStackTrace();
					toast("失败");
				}	
			}

		});
	}

	@Override
	public void onTaskComplete(int taskId, BaseMessage message) {
		// TODO Auto-generated method stub
		super.onTaskComplete(taskId, message);
		switch(taskId){
		case C.task.eioComment:
			if(message.isSuccess()){
				toast("添加评论成功");
				edt_content.setText("");
			}else{
				toast("添加评论失败");
			}
			break;
		case C.task.eioPraise:
			if(message.isSuccess()){
				toast("成功赞了该问卷");
				tv_praise.setText(Integer.valueOf(eio.getPraisecount())+1+"");
				praiseCount++;
			}else{
				toast("失败");
			}
			break;
		case C.task.eioStamp:
			if(message.isSuccess()){
				toast("成功踩了该问卷");
				tv_stamp.setText(Integer.valueOf(eio.getStampcount())+1+"");
				stampCount++;
			}else{
				toast("失败");
			}
			break;
		}
		
		
	}
	
	

}
