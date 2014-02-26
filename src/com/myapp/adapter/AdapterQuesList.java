package com.myapp.adapter;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.myapp.R;
import com.myapp.base.BaseModel;
import com.myapp.base.BaseUi;
import com.myapp.model.SimpleSelectQuestion;
import com.myapp.ui.QuesList;

public class AdapterQuesList extends BaseAdapter {

	private ArrayList<? extends BaseModel> quesList;
	private int quesType;
	private BaseUi ui;

	public AdapterQuesList(BaseUi ui, ArrayList<? extends BaseModel> quesList,
			int quesType) {
		this.ui = ui;
		this.quesList = quesList;
		this.quesType = quesType;
	}

	@Override
	public int getCount() {
		return quesList.size();
	}

	@Override
	public Object getItem(int arg0) {
		quesList.get(arg0);
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	private class SimpViewHolder {
		TextView itemNum;
		TextView itemName;
		CheckBox[] cb = new CheckBox[5];
		
		
		// 显示的checkbox
		public void setCheckBoxContent(int position){
			SimpleSelectQuestion item = (SimpleSelectQuestion) quesList.get(position);
			int count = item.getQuestionnum();
			String[] answer = item.getAllAnswer();
			String[] prefix = {"A.","B.","C.","D.","E."};
			for(int i=0;i<5;i++){
				if(i < count){
					cb[i].setVisibility(View.VISIBLE);
					cb[i].setText(prefix[i] + answer[i]);
					cb[i].setOnCheckedChangeListener(new CheckedListener(i,position));
				}
				else
					cb[i].setVisibility(View.GONE);
			}
		}
		
		//设定被选项
		public void setCheck(int pos){
			for(int i =0;i<5;i++){
				if(i == pos)
					cb[i].setChecked(true);
				else
					cb[i].setChecked(false);
			}
		}
		
		public class CheckedListener implements OnCheckedChangeListener{

			private int checkedPosi;
			private int itemPosi;

			public CheckedListener(int i,int position) {
				checkedPosi = i;
				itemPosi = position;
			}

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked){
					((SimpleSelectQuestion)quesList.get(itemPosi)).setMyAnswer(checkedPosi);
					notifyDataSetChanged(); // 通知界面刷新了
				}
			}
			
		}
		
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(getCount()==0)
			return null;
		switch(quesType){
		
		case QuesList.SIMPLE_SELECT_QUES:
			SimpViewHolder holder = null;
			if (convertView == null) {
				convertView = ui.getLayout(R.layout.item_question);
				holder = new SimpViewHolder();
				holder.itemNum = (TextView)convertView.findViewById(R.id.ItemNum);
				holder.itemName = (TextView)convertView.findViewById(R.id.ItemName);
				
				holder.cb[0] = (CheckBox)convertView.findViewById(R.id.cb1);
				holder.cb[1] = (CheckBox)convertView.findViewById(R.id.cb2);
				holder.cb[2] = (CheckBox)convertView.findViewById(R.id.cb3);
				holder.cb[3] = (CheckBox)convertView.findViewById(R.id.cb4);
				holder.cb[4] = (CheckBox)convertView.findViewById(R.id.cb5);
				convertView.setTag(holder);
			} else {
				holder = (SimpViewHolder) convertView.getTag();
			}
			SimpleSelectQuestion ques = (SimpleSelectQuestion) quesList.get(position);
			
			holder.itemNum.setText((position+1)+".");
			holder.itemName.setText(ques.getQuestion());
			holder.setCheckBoxContent(position);
			holder.setCheck(ques.getMyAnswer());
			break;
		}
		return convertView;
		
	}
}
