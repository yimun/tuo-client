package com.myapp.adapter;

import java.util.ArrayList;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

public class FragmentPagerAdapterSurvey extends FragmentStatePagerAdapter  {
    private ArrayList<Fragment> fragmentsList;

    public FragmentPagerAdapterSurvey(FragmentManager fm) {
        super(fm);
    }

    public FragmentPagerAdapterSurvey(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragmentsList = fragments;
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public Fragment getItem(int arg0) {
        return fragmentsList.get(arg0);
    }

    @Override
    public int getItemPosition(Object object) {
//    	return POSITION_NONE;
        return super.getItemPosition(object);
    }

	@Override
	public Object instantiateItem(ViewGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		Log.i("sur","instantiateItem"+arg1);
		return super.instantiateItem(arg0, arg1);
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
		// TODO Auto-generated method stub
		super.restoreState(arg0, arg1);
	}

	@Override
	public Parcelable saveState() {
		// TODO Auto-generated method stub
		return super.saveState();
	}
    
    
}
