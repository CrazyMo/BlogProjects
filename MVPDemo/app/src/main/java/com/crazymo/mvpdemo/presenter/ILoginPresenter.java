package com.crazymo.mvpdemo.presenter;

/**
 * Auther: Crazy.Mo
 * DateTime: 2017/8/30 17:06
 * Summary:
 */
public interface ILoginPresenter {
    void login(String user, String pwd);

    void detachView();
}
