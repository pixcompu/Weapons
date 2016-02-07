package com.example.pix.whip;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by PIX on 06/02/2016.
 */
public abstract class Weapon {

    protected Context context;
    protected MediaPlayer mMediaPlayer;

    public Weapon(Context context) {
        this.context = context;
        this.mMediaPlayer = MediaPlayer.create(context, getAttackSound());
    }

    public abstract void start();
    public abstract void attack();
    public abstract void finish();
    public abstract int getAttackSound();
}
