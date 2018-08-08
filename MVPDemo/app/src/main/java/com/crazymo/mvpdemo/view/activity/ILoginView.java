package com.crazymo.mvpdemo.view.activity;

/**
 * Auther: Crazy.Mo
 * DateTime: 2017/8/30 17:06
 * Summary:
 */
public interface ILoginView {
    void showLoading();
    void hideLoading();
    void toMainActivity();
    void showUsernameErro();//仅仅在逻辑上去判断
    void showPwdErro();
    void onUsernameErro();//Model回调
    void onLoginowPwdErro();
}
