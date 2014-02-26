package com.myapp.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.myapp.R;
import com.myapp.base.BaseMessage;
import com.myapp.base.BaseUi;
import com.myapp.manager.MyFragmentManager;
import com.myapp.model.Eio;
import com.myapp.view.MicroBlogFragment;

public class EioComment extends BaseUi {

	private Eio eio;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eio_comment);
		eio = (Eio) getIntent().getExtras().getSerializable("Eio");
		this.findViewById(R.id.b_last_view).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				doFinish();
			}
		});
		
		/// 问卷评论的性质和Microblog的性质是一样的，所以添加一个复用MicroBlogFragment
		MicroBlogFragment eiocomment_frag = new MicroBlogFragment(this,
				R.layout.list_micro_blog_user, MicroBlogFragment.EIO_COMMENT,
				eio.getId());
		MyFragmentManager.eioCommentFragmentChange(getSupportFragmentManager(),
				eiocomment_frag);
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
	}

}
