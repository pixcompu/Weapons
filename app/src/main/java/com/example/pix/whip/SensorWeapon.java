package com.example.pix.whip;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by PIX on 06/02/2016.
 */
public abstract class SensorWeapon extends Weapon implements SensorEventListener{

    private long lastUpdateTime = 0;
    private final int X_VALUE_INDEX = 0;
    private final int Y_VALUE_INDEX = 1;
    private final int Z_VALUE_INDEX = 2;

    public SensorWeapon(Context context) {
        super(context);
    }

    @Override
    public void start() {
        SensorManager sensorManager = (SensorManager)
                context.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void finish() {
        SensorManager sensorManager = (SensorManager)
                context.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.unregisterListener(this, sensorAccelerometer);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this){

            Sensor activated = event.sensor;
            boolean isAccelerometerActivated = activated.getType() == Sensor.TYPE_ACCELEROMETER;

            if( isAccelerometerActivated ) {
                proccess( event );
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    protected boolean isValidLatency(long currentTime) {
        final int MIN_LATENCY_ACCEPTABLE = 100;

        long latency = currentTime - lastUpdateTime;
        return (latency > MIN_LATENCY_ACCEPTABLE);
    }

    protected abstract void proccessCoordinates(float x, float y, float z, long currentTime, long lastUpdateTime);

    private void proccess(SensorEvent event){
        long currentTime = System.currentTimeMillis();
        if( isValidLatency(currentTime) ){
            float[] coordinates = event.values;
            proccessCoordinates(
                    coordinates[X_VALUE_INDEX],
                    coordinates[Y_VALUE_INDEX],
                    coordinates[Z_VALUE_INDEX],
                    currentTime,
                    lastUpdateTime);

            lastUpdateTime = currentTime;
        }

    }
}
