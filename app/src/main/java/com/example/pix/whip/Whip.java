package com.example.pix.whip;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by PIX on 06/02/2016.
 */
public class Whip extends SensorWeapon {

    private float lastX, lastY, lastZ;
    private final int WHIP_SPEED = 2800;

    public Whip(Context context) {
        super(context);
        setUpDefaultValues();
    }

    @Override
    protected void proccessCoordinates(float x, float y, float z, long currentTime, long lastUpdateTime) {
        float speed = getSpeed(x, y, z, currentTime, lastUpdateTime);
        if (speed >= WHIP_SPEED) {
            attack();
        }
        setCoordinates(x, y, z);
    }

    @Override
    public void attack() {
        if( !mediaPlayer.isPlaying() ){
            mediaPlayer.start();
        }
    }

    private void setCoordinates(float x, float y, float z) {
        lastX = x;
        lastY = y;
        lastZ = z;
    }

    private float getSpeed(float x, float y, float z, long currentTime, long lastUpdateTime) {
        final int UNIT = 10000;

        long diffTime = currentTime - lastUpdateTime;
        float distance = Math.abs(x + y + z - lastX - lastY - lastZ);

        return distance / diffTime * UNIT;
    }

    private void setUpDefaultValues() {
        setCoordinates(0, 0, 0);
        this.mediaPlayer = MediaPlayer.create(context, R.raw.whip_sound);
    }
}
