package com.myapp.ui;

import android.os.Bundle;

import com.myapp.base.BaseMessage;
import com.myapp.base.BaseUi;
import com.myapp.model.EioResult;

public class Result extends BaseUi {

	private EioResult eioResult;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		eioResult = (EioResult) getIntent().getExtras().getSerializable("result");
	}

	

	@Override
	public void onTaskComplete(int taskId, BaseMessage message) {
		// TODO Auto-generated method stub
		super.onTaskComplete(taskId, message);
	}
	
	

}
