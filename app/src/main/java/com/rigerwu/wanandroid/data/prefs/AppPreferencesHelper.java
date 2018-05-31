package com.rigerwu.wanandroid.data.prefs;

import com.blankj.utilcode.util.SPUtils;

import javax.inject.Inject;

/**
 * Created by RigerWu on 2018/5/22.
 */
public class AppPreferencesHelper implements PreferencesHelper{

    private static final String USER_ACCOUNT = "user_account";

    private static final String USER_PWD = "user_pwd";

    private static final String LOGIN_STATUS = "login_status";

    @Inject
    public AppPreferencesHelper() {
    }

    @Override
    public void setLoginAccount(String account) {
        SPUtils.getInstance().put(USER_ACCOUNT, account);
    }

    @Override
    public String getLoginAccount() {
        return SPUtils.getInstance().getString(USER_ACCOUNT);
    }

    @Override
    public void setLoginPwd(String pwd) {
        SPUtils.getInstance().put(USER_PWD, pwd);
    }

    @Override
    public String getLoginPwd() {
        return SPUtils.getInstance().getString(USER_PWD);
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        SPUtils.getInstance().put(LOGIN_STATUS, isLogin);
    }

    @Override
    public boolean getLoginStatus() {
        return SPUtils.getInstance().getBoolean(LOGIN_STATUS);
    }
}
