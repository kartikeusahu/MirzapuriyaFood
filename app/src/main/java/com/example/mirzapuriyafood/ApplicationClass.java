package com.example.mirzapuriyafood;

import android.app.Application;
import android.util.Log;

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // App-wide initialization yahan karo
        Log.d("ApplicationClass", "App started successfully!");
    }

}
