package com.lixinsheng.foodmenu.app.api;

import android.app.Application;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by lixinsheng on 16/1/11.
 */
@Module
@Singleton
public class ApiServiceModule {


//    http://apis.baidu.com/tngou/cook/

//    name  菜谱名称
//    show  菜谱详情
//    list  菜谱列表
//    classify 菜谱分类

    private static final String DOMAIN = "http://apis.baidu.com/tngou/cook/";
    private static final String APIKEY = "your API_KEY";

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("apikey", APIKEY)
                        .build();
                return chain.proceed(newRequest);
            }
        };
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(interceptor);
        okHttpClient.setConnectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit provideRestAdapter(Application application, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit restAdapter) {
        return restAdapter.create(ApiService.class);
    }

}
