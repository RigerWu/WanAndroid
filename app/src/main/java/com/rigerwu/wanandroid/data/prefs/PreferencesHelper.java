package com.rigerwu.wanandroid.data.prefs;

/**
 * Created by RigerWu on 2018/5/22.
 */
public interface PreferencesHelper {

    void setLoginAccount(String account);

    String getLoginAccount();

    void setLoginPwd(String pwd);

    String getLoginPwd();

    void setLoginStatus(boolean isLogin);

    boolean getLoginStatus();

}
