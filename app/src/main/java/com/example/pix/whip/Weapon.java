package com.example.pix.whip;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by PIX on 06/02/2016.
 */
public abstract class Weapon {

    protected Context context;
    protected MediaPlayer mediaPlayer;

    public Weapon(Context context) {
        this.context = context;
    }

    public abstract void start();
    public abstract void attack();
    public abstract void finish();
}
