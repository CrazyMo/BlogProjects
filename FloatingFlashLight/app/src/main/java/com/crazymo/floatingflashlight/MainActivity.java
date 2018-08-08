package com.crazymo.floatingflashlight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 悬浮窗的view里面有TextureView的情况下，需要开启硬件加速，否则onSurfaceTextureAvailable不会被调用。
 * 如果通过Activity显示悬浮窗，可以在Manifest里面设置硬件加速，如果是Service，需要对悬浮窗设置LayoutParams.FLAG_HARDWARE_ACCELERATED。
 * 在MIUI上需要在"安全中心-授权管理-应用权限管理"打开“显示悬浮窗”开关，并重启应用。
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.btn_show)
    Button btnShow;
    @Bind(R.id.btn_hide)
    Button btnHide;
    @Bind(R.id.btn_operate)
    ImageView btnOperate;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;
    private boolean isOn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        if(FlashLightUtils.hasFlash(this)) {
            btnHide.setOnClickListener(this);
            btnShow.setOnClickListener(this);
            btnOperate.setOnClickListener(this);
        }else{
            Toast.makeText(MainActivity.this,"对不起，您的设备不支持闪光灯，无法使用",Toast.LENGTH_LONG).show();
        }
        setBackground();
    }

    private void setBackground(){
        if(isOn){
            activityMain.setBackgroundResource(R.drawable.light_on_bcg);
            btnOperate.setBackgroundResource(R.drawable.ic_btn_on);
        }else {
            activityMain.setBackgroundResource(R.drawable.light_off_bcg);
            btnOperate.setBackgroundResource(R.drawable.ic_btn_off);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, FloatingService.class);
        switch (v.getId()) {
            case R.id.btn_show:
                intent.putExtra(FloatingService.ACTION, FloatingService.SHOW);
                startService(intent);
                break;
            case R.id.btn_hide:
                intent.putExtra(FloatingService.ACTION, FloatingService.HIDE);
                startService(intent);
                break;
            case R.id.btn_operate:
                if (!isOn){
                    if(!FlashLightUtils.isOpen()){
                        try {
                            FlashLightUtils.openFlash(getApplication());
                            isOn=true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    try {
                        FlashLightUtils.closeFlash();
                        isOn=false;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                setBackground();
                break;
        }

    }
}