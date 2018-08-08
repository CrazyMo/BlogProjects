package com.crazymo.mvpdemo.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.crazymo.mvpdemo.R;
import com.crazymo.mvpdemo.presenter.LoginPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @Bind(R.id.edt_user)
    EditText edtUser;
    @Bind(R.id.edt_pwd)
    EditText edtPwd;
    @Bind(R.id.progress_login)
    ProgressBar progressLogin;
    @Bind(R.id.btn_login)
    Button btnLogin;
    private LoginPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter=new LoginPresenterImpl(this);
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
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
