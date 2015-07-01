package com.imageviewdimen.touchspring.monitor;

import android.app.Application;
import android.os.Handler;

/**
 * Created by KIM on 2015/6/25 0025.
 */
public class MyApplication extends Application {

    private Handler mHandler = new Handler();


    @Override
    public void onCreate() {
        super.onCreate();
      mHandler.postDelayed(mRunnable, 1000);


    }

   protected Runnable mRunnable = new Runnable() {
        int i = 0;

        @Override
        public void run() {
            mHandler.postDelayed(mRunnable, 1000);
        }
    };
}
