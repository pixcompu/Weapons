package com.example.pix.whip;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity{

    private Weapon mWhip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mWhip = new Whip(this);
        mWhip.start();
    }

    @Override
    protected void onDestroy() {
        mWhip.finish();
        super.onDestroy();
    }
}
