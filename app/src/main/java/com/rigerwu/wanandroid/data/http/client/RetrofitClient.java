package com.rigerwu.wanandroid.data.http.client;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.blankj.utilcode.util.Utils;
import com.rigerwu.wanandroid.BuildConfig;
import com.rigerwu.wanandroid.data.http.client.cookie.CookiesManager;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RigerWu on 2018/5/22.
 */
public class RetrofitClient {


    private RetrofitClient() {
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Retrofit createRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }

        File cacheFile;
        if (SDCardUtils.isSDCardEnableByEnvironment()) {
            cacheFile = new File(Utils.getApp().getExternalCacheDir(), "WanAndroid");
        } else {
            cacheFile = new File(Utils.getApp().getCacheDir(), "WanAndroid");
        }
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor interceptor = chain -> {
            Request request = chain.request();
            if (!NetworkUtils.isConnected()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (NetworkUtils.isConnected()) {
                int maxAge = 0 * 60;
                // 有网络时 设置缓存超时时间0个小时
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                // 无网络时，设置超时为4周
//                int maxStale = 60 * 60 * 24 * 28;
                int maxStale = 0;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        };

        builder.addInterceptor(interceptor);
        builder.addNetworkInterceptor(interceptor);
        builder.cache(cache);

        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        builder.cookieJar(new CookiesManager());
        return builder.build();
    }

    private static class SingletonHolder {
        private static final RetrofitClient INSTANCE = new RetrofitClient();
    }
}
