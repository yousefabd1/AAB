package com.soshians_co.aab;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by yousef on 8/26/2017.
 */
public class YekanFont extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Yekan.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
