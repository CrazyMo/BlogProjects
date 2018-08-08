package com.crazyview.mvppro.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crazyview.mvppro.presenter.BasePresenter;

/**
 * Auther: Crazy.Mo
 * DateTime: 2017/9/7 17:10
 * Summary:
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettachView();
    }

    //适配为不同的view 装载不同的presenter
    public abstract T initPresenter();

}
