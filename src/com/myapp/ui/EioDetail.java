package com.myapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.myapp.R;
import com.myapp.base.BaseUi;
import com.myapp.model.Eio;

public class EioDetail extends BaseUi {

	private Eio eio;
	
	private ImageButton btn_back;
	private Button btn_begin;
	private Button btn_comment;
	private TextView tv_stampcount;
	private TextView tv_time;
	private TextView tv_author;
	private TextView tv_quescount;
	private TextView tv_title;
	private TextView tv_didcount;
	private TextView tv_parisecount;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eio_summary);
		eio = (Eio) getIntent().getExtras().getSerializable("Eio");
		debugMemory(eio.getTitle());
		this.getWidgetId();
		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				doFinish();
			}
		});
		btn_begin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				forward(QuesList.class, getIntent().getExtras());
			}
		});
		btn_comment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				overlay(EioComment.class,getIntent().getExtras());
			}
		});		
		setData();

	}

	private void setData() {
		tv_title.setText(eio.getTitle());
		tv_time.setText(eio.getPublishtime());
		tv_author.setText(eio.getAuthor());
		tv_quescount.setText(eio.getQuestioncount());
		tv_didcount.setText(eio.getDidcount());
		tv_parisecount.setText(eio.getPraisecount());
		tv_stampcount.setText(eio.getStampcount());
	}

	void getWidgetId() {
		btn_back = (ImageButton) findViewById(R.id.img_b_back);
		btn_begin = (Button) findViewById(R.id.img_b_begin);
		btn_comment = (Button) findViewById(R.id.button1);
		tv_title = (TextView) findViewById(R.id.title);
		tv_time = (TextView) findViewById(R.id.time);
		tv_author = (TextView) findViewById(R.id.auther);
		tv_quescount = (TextView) findViewById(R.id.quescount);
		tv_didcount = (TextView) findViewById(R.id.didcount);
		tv_parisecount = (TextView) findViewById(R.id.parisecount);
		tv_stampcount = (TextView) findViewById(R.id.stampcount);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

}
