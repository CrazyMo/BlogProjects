package com.crazyview.mvppro.presenter;

import com.crazyview.mvppro.activity.LoginActivity;
import com.crazyview.mvppro.model.ILoginModel;
import com.crazyview.mvppro.model.LoginModelImpl;

/**
 * Auther: Crazy.Mo
 * DateTime: 2017/8/30 18:03
 * Summary:
 */
public class LoginPresenterImpl extends BasePresenter<LoginActivity> implements ILoginPresenter ,IOnLoginFinishListener{
    public static final String USER_NOT_EXITS = "user_not_exits";
    public static final String USER_PWD_ERRO = "user_pwd_erro";
   //P需要与M 交互，所以需要持有M的引用
    private ILoginModel loginModel;
    public LoginPresenterImpl() {
        this.loginModel = new LoginModelImpl(this);
    }
    @Override
    public void login(String user, String pwd) {

        if(getView()!=null) {
            getView().showLoading();
            if ("".equals(user)) {
                getView().showUsernameErro();
                getView().hideLoading();
                return;
            }
            if ("".equals(pwd)){
                getView().showPwdErro();
                getView().hideLoading();
                return;
            }
            loginModel.login(user,pwd);
        }
    }

    @Override
    public void onLoginSuccess() {
        getView().toMainActivity();
    }

    @Override
    public void onLoginFailed(String type) {
        getView().hideLoading();
        if(USER_NOT_EXITS.equals(type)){
            getView().onUsernameErro();
        }else if(USER_PWD_ERRO.equals(type)){
            getView().onLoginowPwdErro();
        }
    }
}
