package com.myapp.ui;

import java.util.ArrayList;
import java.util.List;

import com.myapp.R;
import com.myapp.adapter.ViewPagerAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Guide extends Activity implements OnPageChangeListener {
	
	private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    
    private ImageView[] dots;
    
    private int currentIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		initViews();
		initDots();
	}

	 private void initViews() {
	        LayoutInflater inflater = LayoutInflater.from(this);

	        views = new ArrayList<View>();
	        // ��ʼ������ͼƬ�б�
	        views.add(inflater.inflate(R.layout.one, null));
	        views.add(inflater.inflate(R.layout.two, null));
	        views.add(inflater.inflate(R.layout.three, null));
	        views.add(inflater.inflate(R.layout.four, null));

	        // ��ʼ��Adapter
	        vpAdapter = new ViewPagerAdapter(views, this);

	        vp = (ViewPager) findViewById(R.id.viewpager);
	        vp.setAdapter(vpAdapter);
	        // �󶨻ص�
	        vp.setOnPageChangeListener(this);
	    }

	    private void initDots() {
	        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

	        dots = new ImageView[views.size()];

	        // ѭ��ȡ��С��ͼƬ
	        for (int i = 0; i < views.size(); i++) {
	            dots[i] = (ImageView) ll.getChildAt(i);
	            dots[i].setEnabled(true);// ����Ϊ��ɫ
	        }

	        currentIndex = 0;
	        dots[currentIndex].setEnabled(false);// ����Ϊ��ɫ����ѡ��״̬
	    }

	    private void setCurrentDot(int position) {
	        if (position < 0 || position > views.size() - 1
	                || currentIndex == position) {
	            return;
	        }

	        dots[position].setEnabled(false);
	        dots[currentIndex].setEnabled(true);

	        currentIndex = position;
	    }

	    // ������״̬�ı�ʱ����
	    @Override
	    public void onPageScrollStateChanged(int arg0) {
	    }

	    // ����ǰҳ�汻����ʱ����
	    @Override
	    public void onPageScrolled(int arg0, float arg1, int arg2) {
	    }

	    // ���µ�ҳ�汻ѡ��ʱ����
	    @Override
	    public void onPageSelected(int arg0) {
	        // ���õײ�С��ѡ��״̬
	        setCurrentDot(arg0);
	    }
	


}
