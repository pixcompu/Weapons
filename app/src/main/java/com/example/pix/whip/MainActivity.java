package com.example.pix.whip;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{

    private Weapon mWhip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        changeFont(R.id.counter);

        mWhip = new Whip(this);
        mWhip.start();
    }


    public TextView getCounter(){
        return (TextView)findViewById(R.id.counter);
    }

    private void changeFont(int textViewID) {
        TextView counter = (TextView)findViewById(textViewID);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "architect.ttf");
        counter.setTypeface(typeFace);
    }

    @Override
    protected void onStop() {
        mWhip.finish();
        super.onStop();
    }

    @Override
    protected void onResume() {
        mWhip.start();
        super.onResume();
    }

}
