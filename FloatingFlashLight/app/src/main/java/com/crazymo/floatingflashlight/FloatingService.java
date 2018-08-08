package com.crazymo.floatingflashlight;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Auther: Crazy.Mo
 * DateTime: 2017/7/6 11:37
 * Summary:
 */
public class FloatingService extends Service {
    public static final String ACTION="action";
    public static final String SHOW="show";
    public static final String HIDE="hide";
    private FloatingView mFloatingView;

    @Override
    public void onCreate(){
        super.onCreate();
        mFloatingView=new FloatingView(this);
        mFloatingView.setMyClickListener(new FloatingView.MyClicklistener() {
            @Override
            public void onMyClick() {
                //手电
                if(!FlashLightUtils.isOpen()){
                    try {
                        Log.e("FALSH","打开");
                        FlashLightUtils.openFlash(getApplication());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.e("FALSH","guanbi");
                    try {
                        FlashLightUtils.closeFlash();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action=intent.getStringExtra(ACTION);
            if(SHOW.equals(action)){
                mFloatingView.show();
            }else if(HIDE.equals(action)){
                mFloatingView.hide();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
}