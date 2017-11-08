package com.jackchan.takeoutservice;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.jackchan.takeoutservice.imageServer.ImageMaker;

public class App extends Application {

    private static Context context;
    private static Handler handler;
    private static Gson gson = new Gson();

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        handler = new Handler();
        //
        ImageMaker.generateImageWithApkFile(this);
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static Gson getGson() {
        return gson;
    }
}
