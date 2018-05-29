package com.rigerwu.wanandroid.Data;

import com.rigerwu.wanandroid.Data.db.DbHelper;
import com.rigerwu.wanandroid.Data.http.HttpHelper;
import com.rigerwu.wanandroid.Data.prefs.PreferencesHelper;

/**
 * Created by RigerWu on 2018/5/21.
 */
public interface DataManager extends HttpHelper, DbHelper, PreferencesHelper{

}
