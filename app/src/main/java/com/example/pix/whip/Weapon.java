package com.example.pix.whip;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by PIX on 06/02/2016.
 */
public abstract class Weapon {

    protected Context mContext;
    protected MediaPlayer mMediaPlayer;

    public Weapon(Context mContext) {
        this.mContext = mContext;
        this.mMediaPlayer = MediaPlayer.create(mContext, getAttackSound());
    }

    public abstract void start();
    public abstract void attack();
    public abstract void finish();
    public abstract int getAttackSound();
}
