package com.rigerwu.wanandroid.data;

import com.rigerwu.wanandroid.data.db.DbHelper;
import com.rigerwu.wanandroid.data.http.HttpHelper;
import com.rigerwu.wanandroid.data.prefs.PreferencesHelper;

/**
 * Created by RigerWu on 2018/5/21.
 */
public interface DataManager extends HttpHelper, DbHelper, PreferencesHelper{

}
