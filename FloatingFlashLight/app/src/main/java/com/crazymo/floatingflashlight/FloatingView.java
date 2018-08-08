package com.crazymo.floatingflashlight;

/**
 * Auther: Crazy.Mo
 * DateTime: 2017/7/6 11:34
 * Summary:
 */

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * 悬浮窗view
 */
public class FloatingView extends FrameLayout {
    private Context mContext;
    private View mView;
    private ImageView mImageView;
    private int mTouchStartX, mTouchStartY;//手指按下时坐标
    private WindowManager.LayoutParams mParams;
    private FloatingManager mWindowManager;
    private int tempX,tempY;
    private MyClicklistener myClicklistener;

    public FloatingView(Context context) {
        super(context);
        mContext = context.getApplicationContext();
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        mView = mLayoutInflater.inflate(R.layout.floating_view, null);
        mImageView = (ImageView) mView.findViewById(R.id.imageview);
        mImageView.setImageResource(R.drawable.ic_float);
        ///mImageView.setOnTouchListener(mOnTouchListener);
        mWindowManager = FloatingManager.getInstance(mContext);

        mView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mTouchStartX = (int) event.getRawX();
                        mTouchStartY = (int) event.getRawY();
                        myClicklistener.onMyClick();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mParams.x = tempX + (int) event.getRawX() - mTouchStartX;
                        mParams.y = tempY +(int) event.getRawY() - mTouchStartY;
                        mWindowManager.updateView(mView, mParams);
                        break;
                    case MotionEvent.ACTION_UP:
                        tempX = mParams.x;
                        tempY = mParams.y;
                        break;

                }

                return false;
            }
        });

    }

    public ImageView getmImageView(){
        return this.mImageView;
    }
    public void show() {
        mParams = new WindowManager.LayoutParams();
        mParams.gravity = Gravity.TOP | Gravity.LEFT;
        mParams.x = 0;
        mParams.y = 100;
        //总是出现在应用程序窗口之上
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        //设置图片格式，效果为背景透明
        mParams.format = PixelFormat.RGBA_8888;
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowManager.addView(mView, mParams);
        //逐帧动画
       /* AnimationDrawable animationDrawable=(AnimationDrawable)mImageView.getDrawable();
        animationDrawable.start();
        */
    }

    public void hide() {
        mWindowManager.removeView(mView);
    }

    public void setMyClickListener(MyClicklistener listener){
        this.myClicklistener=listener;
    }
    public interface MyClicklistener{
         void onMyClick();
    }
}