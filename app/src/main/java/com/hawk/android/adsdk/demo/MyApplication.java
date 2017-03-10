package com.hawk.android.adsdk.demo;


import com.hawk.android.adsdk.ads.HkMobileAds;

import android.app.Application;

/**
 * Created by tzh on 2016/11/3.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        //init the ad sdk,must init before ad request
        HkMobileAds.initialize(getApplicationContext(),getString(R.string.app_key),
                getString(R.string.banner_ad_unitid),getString(R.string.intersitial_ad_unitid),getString(R.string.native_ad_unitid));
    }
}
