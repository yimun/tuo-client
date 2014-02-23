package com.myapp.ui;

import android.os.Bundle;

import com.myapp.base.BaseUi;
import com.myapp.model.Eio;

public class EioDetail extends BaseUi {

	private Eio eio;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		eio = (Eio) getIntent().getExtras().getSerializable("Eio");
		debugMemory(eio.getTitle());
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	

}
