package com.devsty.vc3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.unisob.vclibs.mads.SplashActivity;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            SplashActivity.All_Data(this, new Intent(this, Class.forName("com.devsty.vc3.MainActivity")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}