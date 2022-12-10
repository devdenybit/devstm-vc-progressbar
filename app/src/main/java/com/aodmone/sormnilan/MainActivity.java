package com.aodmone.sormnilan;

import static com.gerop.mpsvue.mainaods.MainAppManage.ADMOB_B;
import static com.gerop.mpsvue.mainaods.MainAppManage.ADMOB_N0;
import static com.gerop.mpsvue.mainaods.MainAppManage.FACEBOOK_N;
import static com.gerop.mpsvue.mainaods.MainAppManage.FACEBOOK_NB;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.gerop.mpsvue.activties.MainHomeActivity;
import com.gerop.mpsvue.mainaods.MainAppManage;

public class MainActivity extends AppCompatActivity {
    Activity activity = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainAppManage.getInstance(this).onlyCustBannerCallAD(this);
        MainAppManage.getInstance(this).onlyCustNativeCallAD(this);
        MainAppManage.getInstance(this).onlyCustInterstitialCallAD(this);
        MainAppManage.getInstance(this).showNativeBanner((ViewGroup) findViewById(com.gerop.mpsvue.R.id.banner_container), ADMOB_B[0], FACEBOOK_NB[0]);
        MainAppManage.getInstance(this).showNative((ViewGroup) findViewById(com.gerop.mpsvue.R.id.native_container), ADMOB_N0, FACEBOOK_N[0]);

    }


    public void necxt(View view) {
        startActivity(new Intent(MainActivity.this, MainHomeActivity.class));
        MainAppManage.getInstance(activity).showInterstitialAd(activity, new MainAppManage.MyCallback() {
            public void callbackCall() {
                startActivity(new Intent(MainActivity.this, MainHomeActivity.class));
            }
        }, "", MainAppManage.app_mainClickCntSwAd);
    }

    @Override
    public void onBackPressed() {

        MainAppManage.getInstance(activity).showInterstitialAd(activity, new MainAppManage.MyCallback() {
            public void callbackCall() {
                MainAppManage.getInstance(activity).Show_Exite_Dialog(activity, getPackageName());
            }
        }, "", MainAppManage.app_innerClickCntSwAd);
    }


}