package com.myapp.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;

import com.myapp.base.BaseMessage;
import com.myapp.base.BaseModel;
import com.myapp.base.BaseUi;
import com.myapp.base.C;
import com.myapp.model.Eio;
import com.myapp.model.EioResult;
import com.myapp.model.SimpleSelectQuestion;

public class QuesList extends BaseUi {

	private ArrayList<? extends BaseModel> quesList = new ArrayList<BaseModel>();
	private int quesType;
	private Eio eio;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		eio = (Eio) getIntent().getExtras().getSerializable("Eio");
		quesType = Integer.parseInt(eio.getTypeid());
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	void doTaskGetQuesList() {
		HashMap<String, String> urlParams = new HashMap<String, String>();
		urlParams.put("eioId", eio.getId());
		try {
			this.doTaskAsync(C.task.quesList, C.api.quesList, urlParams);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void doTaskGetResult() { // 提交返回结果
		HashMap<String, String> urlParams = new HashMap<String, String>();
		urlParams.put("eioId", eio.getId());
		urlParams.put("result", ""); // TODO
		try {
			this.doTaskAsync(C.task.eioDispose, C.api.eioDispose, urlParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTaskComplete(int taskId, BaseMessage message) {
		// TODO Auto-generated method stub
		super.onTaskComplete(taskId, message);
		switch (taskId) {
		case C.task.quesList:
			switch (quesType) {
			case 1: // 单选
				try {
					quesList.clear();
					quesList = (ArrayList<SimpleSelectQuestion>) message
							.getResultList("SimpleSelectQuestion");

				} catch (Exception e) {
					e.printStackTrace();
					toast(e.getMessage());
				}
				break;
			case 2: // 填空题
				// TODO
				break;
			case 3: // 多选
				// TODO
				break;

			}
			break;

		case C.task.eioDispose:
			try {
				EioResult result = (EioResult) message.getResult("EioResult");
				Bundle bd = new Bundle();
				bd.putSerializable("result", result);
				forward(Result.class, bd);

			} catch (Exception e) {
				e.printStackTrace();
				toast(e.getMessage());
			}
			break;
		}
	}

}
