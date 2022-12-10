package com.aodmone.sormnilan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gerop.mpsvue.mainaods.MainSplashActivity;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            MainSplashActivity.All_Data(this, new Intent(this, Class.forName("com.aodmone.sormnilan.MainActivity")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}