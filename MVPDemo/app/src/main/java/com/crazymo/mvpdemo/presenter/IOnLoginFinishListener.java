package com.crazymo.mvpdemo.presenter;

/**
 * Auther: Crazy.Mo
 * DateTime: 2017/8/30 17:18
 * Summary:
 */
public interface IOnLoginFinishListener {
    void onLoginSuccess();
    void onLoginFailed(String type);
}
