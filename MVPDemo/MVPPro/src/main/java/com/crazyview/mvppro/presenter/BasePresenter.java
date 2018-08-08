package com.crazyview.mvppro.presenter;

import java.lang.ref.WeakReference;

/**
 * Auther: Crazy.Mo
 * DateTime: 2017/9/7 17:10
 * Summary:
 */
public abstract class BasePresenter<T> {
    protected WeakReference<T> modelView;

    public void attachView(T view) {
        this.modelView = new WeakReference<T>(view);
    }

    public void dettachView() {
        this.modelView.clear();
    }

    protected T getView() {
        return modelView.get();
    }
}