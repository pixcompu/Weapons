package com.example.pix.whip;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by PIX on 06/02/2016.
 */
public class Whip extends SensorWeapon {

    private float mLastX, mLastY, mLastZ;
    private final int WHIP_SPEED = 2750;
    private final int SWING_SPEED = 700;
    private boolean isAttackMode;
    private static int times;

    public Whip(Context context) {
        super(context);
        setOldCoordinates(0, 0, 0);
        this.isAttackMode = true;
        times = 1;
    }

    @Override
    protected void proccessCoordinates(float x, float y, float z, long currentTime, long lastUpdateTime) {
        float speed = getSpeed(x, y, z, currentTime, lastUpdateTime);

        if ( speed >= WHIP_SPEED ) {

            attack();

        }else if( speed >= SWING_SPEED){

            swing();

        }

        setOldCoordinates(x, y, z);
    }

    @Override
    public int getAttackSound() {
        return R.raw.whip_sound;
    }

    /**
     * On attack mode, swing sound will be interrupted if its currently playing
     */
    @Override
    public void attack() {
        if( !isAttackMode ){
            changeTrackTo( getAttackSound() );
        }
        if( !mMediaPlayer.isPlaying() ){
            mMediaPlayer.start();
            ((MainActivity)mContext).getCounter().setText("Azote #" + (times++));
        }
        isAttackMode = true;
    }

    /**
     * On No attack mode (swing mode) swing sound will only start if there is no other sounds being played
     */
    private void swing() {
        if( !mMediaPlayer.isPlaying() ){
            if( isAttackMode ){
                changeTrackTo( getSwingSound() );
            }
            mMediaPlayer.start();
            isAttackMode = false;
        }
    }

    private void changeTrackTo(int trackID) {
        mMediaPlayer.stop();
        mMediaPlayer.release();
        mMediaPlayer = null;
        mMediaPlayer = MediaPlayer.create(mContext, trackID);
    }

    private void setOldCoordinates(float x, float y, float z) {
        this.mLastX = x;
        this.mLastY = y;
        this.mLastZ = z;
    }

    private float getSpeed(float x, float y, float z, long currentTime, long lastUpdateTime) {
        final int UNIT = 10000;

        long diffTime = currentTime - lastUpdateTime;
        float distance = Math.abs(x + y + z - mLastX - mLastY - mLastZ);

        return distance / diffTime * UNIT;
    }

    private int getSwingSound() {
        return R.raw.whoosh_sound;
    }

}
