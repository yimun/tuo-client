package com.myapp.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.myapp.R;
import com.myapp.base.BaseAuth;
import com.myapp.base.BaseUi;
import com.myapp.ui.About;
import com.myapp.ui.Client;
import com.myapp.ui.ContactUs;
import com.myapp.ui.Login;


@SuppressLint({ "ValidFragment", "NewApi" })
public class SettingFragment extends Fragment implements OnClickListener {
	private Context context;
	private View view;
	
	private Button user_information;
	private Button about;
	private Button contact;
	private Button logout;
	private ImageButton show_right_button;
	
	private FragmentManager fragmentManager;
	
	public void setFragmentManager(FragmentManager fragmentManager) {
		this.fragmentManager = fragmentManager;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		getWidgetId();
		setClickEvent();
		

	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch(v.getId()){
		case R.id.b_contact:

			intent.setClass(getActivity(), ContactUs.class);
			startActivity(intent);
			break;
		case R.id.b_about:

			intent.setClass(getActivity(), About.class);
			startActivity(intent);
			break;
		case R.id.b_user_information:

			intent.setClass(getActivity(), Client.class);
			startActivity(intent);
			break;
		case R.id.b_logout:
			BaseAuth.setLogin(false);
			intent.setClass(getActivity(), Login.class);
			startActivity(intent);
			getActivity().finish();
			break;
		}
	}
	
	public void getWidgetId(){
		contact = (Button)view.findViewById(R.id.b_contact);
		user_information = (Button)view.findViewById(R.id.b_user_information);
		about = (Button)view.findViewById(R.id.b_about);
		logout = (Button)view.findViewById(R.id.b_logout);
		show_right_button = (ImageButton)((BaseUi)context).findViewById(R.id.show_right_button);
		show_right_button.setVisibility(View.GONE);
	}
	public void setClickEvent() {
		logout.setOnClickListener(this);
		contact.setOnClickListener(this);
		user_information.setOnClickListener(this);
		about.setOnClickListener(this);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.view = inflater.inflate(R.layout.setting_fragment, container, false);
		return view;
	}

	public SettingFragment(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		show_right_button.setVisibility(View.VISIBLE);
	}
}
