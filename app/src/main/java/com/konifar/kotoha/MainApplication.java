package com.konifar.kotoha;

import android.app.Application;

import com.konifar.kotoha.network.KotohaService;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class MainApplication extends Application {

    // TODO Use Dagger2
    public static final KotohaService API = new Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
            .create(KotohaService.class);

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
