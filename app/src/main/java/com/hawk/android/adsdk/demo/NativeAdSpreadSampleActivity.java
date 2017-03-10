package com.hawk.android.adsdk.demo;

import com.google.android.gms.ads.AdRequest;

import com.facebook.ads.AdSettings;
import com.hawk.android.adsdk.ads.HKNativeAd;
import com.hawk.android.adsdk.demo.view.NativeViewBuild;
import com.tcl.mediator.HawkAdRequest;
import com.tcl.mediator.HkNativeAdListener;
import com.tcl.mediator.iadapter.HawkNativeAd;
import com.tcl.mediator.iadapter.HawkNativeAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

/**
 * Created by $ liuluchao@cmcm.com on 2016/7/4.
 */
public class NativeAdSpreadSampleActivity extends Activity implements View.OnClickListener {

    private HKNativeAd mHKNativeAd;
    private FrameLayout nativeAdContainer;//View Container
    private View mAdView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        nativeAdContainer = (FrameLayout) findViewById(R.id.big_ad_container);
        findViewById(R.id.btn_req).setOnClickListener(this);
        findViewById(R.id.btn_show).setOnClickListener(this);
        initNativeAd();

    }


    private void initNativeAd() {
        //setp1 : create mHKNativeAd
        //The first parameter：Context
        //The second parameter: posid
        String testUnitId=getString(R.string.native_ad_unitid);
        AdSettings.addTestDevice("5545e13a65e654f10f8e1fc4b7a35ca2");
        new AdRequest.Builder().addTestDevice("896552943354F7657852355F9EB714CE");
        mHKNativeAd = new HKNativeAd(this,testUnitId);
        //setp2 : set callback listener(INativeAdLoaderListener)
        mHKNativeAd.setNativeAdListener(new HkNativeAdListener() {
            @Override
            public void onNativeAdLoaded() {
                //ad load  success ,you can do other something here;
                showAd();
                Toast.makeText(NativeAdSpreadSampleActivity.this, "ad load  success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNativeAdLoaded(List<HawkNativeAd> var1) {

            }

            @Override
            public void onNativeAdFailed(int errcode) {
                Toast.makeText(NativeAdSpreadSampleActivity.this, "ad load  failed,error code is:"+errcode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdClick(HawkNativeAdapter nativeAdapter) {
                Toast.makeText(NativeAdSpreadSampleActivity.this, "ad click", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onOpen(HawkNativeAdapter hawkNativeAdapter) {

            }
        });
        mHKNativeAd.loadAd(new HawkAdRequest().addTestDevice("11e51832e3c89032d6db8c4de4acf594"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_req:
                //step3 : start load nativeAd
                mHKNativeAd.loadAd(new HawkAdRequest());
                break;
            case R.id.btn_show:
                //showAd();
                break;
            default:
                break;
        }
    }

    /**
     * if load nativeAd success,you can get and show nativeAd;
     */
    private void showAd(){
        if(mHKNativeAd != null){
            Object ad = mHKNativeAd.getAd();
            if (ad == null) {
                Toast.makeText(NativeAdSpreadSampleActivity.this,
                        "no native ad loaded!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (mAdView != null) {
                // remove old ad view
                nativeAdContainer.removeAllViews();
            }
            //the mAdView is custom by publisher
            mAdView = NativeViewBuild.createAdView(getApplicationContext(), ad, mHKNativeAd);
            if (mHKNativeAd != null) {
                mHKNativeAd.unregisterView();
                mHKNativeAd.registerViewForInteraction(mAdView);
            }
            //add the mAdView into the layout of view container.(the container should be prepared by youself)
            nativeAdContainer.addView(mAdView);
        }      
    }
}
