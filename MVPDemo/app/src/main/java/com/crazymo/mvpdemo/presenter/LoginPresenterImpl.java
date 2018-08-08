package com.crazymo.mvpdemo.presenter;

import com.crazymo.mvpdemo.model.ILoginModel;
import com.crazymo.mvpdemo.model.LoginModelImpl;
import com.crazymo.mvpdemo.view.activity.ILoginView;

/**
 * Auther: Crazy.Mo
 * DateTime: 2017/8/30 18:03
 * Summary:
 */
public class LoginPresenterImpl implements ILoginPresenter ,IOnLoginFinishListener{
    public static final String USER_NOT_EXITS = "user_not_exits";
    public static final String USER_PWD_ERRO = "user_pwd_erro";
    private ILoginView loginView;//P需要与V 交互，所以需要持有V的引用
    private ILoginModel loginModel;
    public LoginPresenterImpl(ILoginView view) {
        this.loginView = view;
        this.loginModel = new LoginModelImpl(this);
    }
    @Override
    public void login(String user, String pwd) {

        if(loginView!=null) {
            loginView.showLoading();
            if ("".equals(user)) {
                loginView.showUsernameErro();
                loginView.hideLoading();
                return;
            }
            if ("".equals(pwd)){
                loginView.showPwdErro();
                loginView.hideLoading();
                return;
            }
            loginModel.login(user,pwd);
        }
    }

    @Override
    public void detachView() {
        //为了避免内存泄漏，在不用的时候及时释放所持有的View 引用
        loginView=null;
    }

    @Override
    public void onLoginSuccess() {
        loginView.toMainActivity();
    }

    @Override
    public void onLoginFailed(String type) {
        loginView.hideLoading();
        if(USER_NOT_EXITS.equals(type)){
            loginView.onUsernameErro();
        }else if(USER_PWD_ERRO.equals(type)){
            loginView.onLoginowPwdErro();
        }
    }
}
