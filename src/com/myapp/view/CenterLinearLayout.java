package com.myapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class CenterLinearLayout extends LinearLayout {

	private GestureDetector mGestureDetector;  
    View.OnTouchListener mGestureListener;  
  
    private boolean isLock = true;  
  
    private OnScrollListener onScrollListener;// �Զ���ӿ�  
  
    private boolean b; 
	
	public CenterLinearLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mGestureDetector = new GestureDetector(new MySimpleGesture());  
	}

	public CenterLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mGestureDetector = new GestureDetector(new MySimpleGesture());  
	}

	public CenterLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		mGestureDetector = new GestureDetector(new MySimpleGesture());  
	}
	
	public void setOnScrollListener(OnScrollListener onScrollListener) {  
        this.onScrollListener = onScrollListener;  
    }  
	
	@Override  
    public boolean dispatchTouchEvent(MotionEvent ev) {  
//        Log.e("jj", "dispatchTouchEvent...");  
//        // ��ȡ���Ʒ���ֵ  
//        b = mGestureDetector.onTouchEvent(ev);  
//        // �ɿ���Ҫִ��һЩ������(�ر� or ��)  
//        if (ev.getAction() == MotionEvent.ACTION_UP) {  
//            onScrollListener.doLoosen();  
//        }  
        return super.dispatchTouchEvent(ev);  
    }  
  
//    @Override  
//    public boolean onInterceptTouchEvent(MotionEvent ev) {  
//        Log.e("jj", "onInterceptTouchEvent...");  
//        super.onInterceptTouchEvent(ev);  
//        return b;  
//    }  
    /*** 
     * �������Ҽ�˵��һ�� 
     */  
//    @Override  
//    public boolean onTouchEvent(MotionEvent event) {  
//        Log.e("jj", "onTouchEvent...");  
//        isLock = false;  
////        return super.onTouchEvent(event);  
//        return false;
//    }  
    
    /*** 
     * �Զ�������ִ�� 
     *  
     * @author zhangjia 
     *  
     */  
    class MySimpleGesture extends SimpleOnGestureListener {  
  
        @Override  
        public boolean onDown(MotionEvent e) {  
            Log.e("jj", "onDown...");  
            isLock = true;  
            return super.onDown(e);  
        }  
  
        @Override  
        public boolean onScroll(MotionEvent e1, MotionEvent e2,  
                float distanceX, float distanceY) {  
  
            if (!isLock)  
                onScrollListener.doScroll(distanceX);  
  
            // ��ֱ����ˮƽ  
            if (Math.abs(distanceY) > Math.abs(distanceX)) {   
                return false;  
            } else {  
                return true;  
            }  
        }  
    }  
    
    /*** 
     * �Զ���ӿ� ʵ�ֻ���... 
     *  
     * @author zhangjia 
     *  
     */  
    public interface OnScrollListener {  
        void doScroll(float distanceX);// ����...  
  
        void doLoosen();// ��ָ�ɿ���ִ��...  
    }  

}
