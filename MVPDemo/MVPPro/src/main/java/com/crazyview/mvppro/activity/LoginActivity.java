package com.crazyview.mvppro.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.crazyview.mvppro.R;
import com.crazyview.mvppro.presenter.LoginPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginActivity,LoginPresenterImpl> implements ILoginView {

    @Bind(R.id.edt_user)
    EditText edtUser;
    @Bind(R.id.edt_pwd)
    EditText edtPwd;
    @Bind(R.id.progress_login)
    ProgressBar progressLogin;
    @Bind(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public void showLoading() {
        progressLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void toMainActivity() {
        MainActivity.showMainActivity(this);
        finish();
    }

    @Override
    public void showUsernameErro() {
        edtUser.setError("Username can't be empty or Username is too short");
    }

    @Override
    public void showPwdErro() {
        edtPwd.setError("Password can't be empty !");
    }

    @Override
    public void onUsernameErro() {
        edtUser.setError("Username is not exits!");
    }

    @Override
    public void onLoginowPwdErro() {
        edtPwd.setError("Username or password is erro!");
    }

    @OnClick({R.id.btn_login})
    void onClick(View view){
        presenter.login(edtUser.getText().toString(),edtPwd.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public LoginPresenterImpl initPresenter() {
        return new LoginPresenterImpl();
    }
}
