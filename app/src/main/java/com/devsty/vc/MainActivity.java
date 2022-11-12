package com.devsty.vc;

import static com.unisob.vclibs.mads.AppManage.ADMOB_B;
import static com.unisob.vclibs.mads.AppManage.ADMOB_N0;
import static com.unisob.vclibs.mads.AppManage.FACEBOOK_N;
import static com.unisob.vclibs.mads.AppManage.FACEBOOK_NB;
import static com.unisob.vclibs.mads.SplashActivity.Show_Exite_Dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.unisob.vclibs.activties.HomePageActivity;
import com.unisob.vclibs.mads.AppManage;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Activity activity = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppManage.getInstance(this).customeAdCall(this);
        AppManage.getInstance(this).showNativeBanner((ViewGroup) findViewById(R.id.banner_container), ADMOB_B[0], FACEBOOK_NB[0]);
        AppManage.getInstance(this).showNative((ViewGroup) findViewById(com.unisob.vclibs.R.id.native_container), ADMOB_N0, FACEBOOK_N[0]);


    }

    public void necxt(View view) {
        startActivity(new Intent(MainActivity.this, HomePageActivity.class));
        AppManage.getInstance(activity).showInterstitialAd(activity, new AppManage.MyCallback() {
            public void callbackCall() {
                startActivity(new Intent(MainActivity.this, HomePageActivity.class));
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }

    @Override
    public void onBackPressed() {

        AppManage.getInstance(activity).showInterstitialAd(activity, new AppManage.MyCallback() {
            public void callbackCall() {
                Show_Exite_Dialog(activity);
            }
        }, "", AppManage.app_innerClickCntSwAd);
    }


}