package com.crazymo.mvpdemo.model;

import android.util.Log;

import com.crazymo.mvpdemo.presenter.IOnLoginFinishListener;
import com.crazymo.mvpdemo.presenter.LoginPresenterImpl;

/**
 * Auther: Crazy.Mo
 * DateTime: 2017/8/31 9:20
 * Summary:
 */
public class LoginModelImpl implements ILoginModel {
    public static final String DEFAULT_USER = "crazymo_";
    public static final String DEFAULT_PWD = "cmo";
    private IOnLoginFinishListener finishListener;
    public LoginModelImpl(IOnLoginFinishListener listener){
        this.finishListener=listener;
    }

    @Override
    public void login(String user,String pwd) {
        int k=0;
        for(int i=0;i<100000;i++){
            k++;
        }
        Log.e("CrazyMo_",Thread.currentThread().getName().toString());
        if(DEFAULT_USER.equals(user)&& DEFAULT_PWD.equals(pwd)){
            finishListener.onLoginSuccess();
        }else if(!(DEFAULT_USER.equals(user))){
            finishListener.onLoginFailed(LoginPresenterImpl.USER_NOT_EXITS);
        }else if(DEFAULT_USER.equals(user)&&!(DEFAULT_PWD.equals(pwd))){
            finishListener.onLoginFailed(LoginPresenterImpl.USER_PWD_ERRO);
        }
    }
}
