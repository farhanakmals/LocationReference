package com.example.locationreference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.security.PublicKey;
import java.util.ArrayList;

public class category extends FragmentActivity implements SensorEventListener {

    RelativeLayout card1, card3, card4;
    CardView card2;
    ImageView lang;

    private SensorManager mSensorManager;
    private Sensor mSensorLight;

    public static final String LATITUDE = "LATITUDE_VALUE";
    public static final String LONGITUDE = "LONGITUDE_VALUE";
    public static final String NAME = "NAME_VALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        card1 = findViewById(R.id.car);
        card2 = findViewById(R.id.food);
        card3 = findViewById(R.id.hospital);
        card4 = findViewById(R.id.shop);
        lang = findViewById(R.id.laguage);

        // Sensor Light
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        // language
        lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent languageIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(languageIntent);
            }
        });

        // Card
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(view.getContext(),maps.class);
                String lat = "-7.793347199638624";
                String longi = "110.36029033898855";
                String name = "Rental Mobil 88 Bintang Transport Jogja";
                map.putExtra(LATITUDE, lat);
                map.putExtra(LONGITUDE, longi);
                map.putExtra(NAME, name);
                startActivity(map);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(view.getContext(),maps.class);
                String lat = "-7.785001885248953";
                String longi = "110.3713696389885";
                String name = "The House Of Raminten";
                map.putExtra(NAME, name);
                map.putExtra(LATITUDE, lat);
                map.putExtra(LONGITUDE, longi);
                startActivity(map);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(view.getContext(),maps.class);
                String lat = "-7.768781296128968";
                String longi = "110.37214659665892";
                String name = "Sardjito General Hospital Library";
                map.putExtra(NAME, name);
                map.putExtra(LATITUDE, lat);
                map.putExtra(LONGITUDE, longi);
                startActivity(map);
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(view.getContext(),maps.class);
                String lat = "-7.752976625436258";
                String longi = "110.36090909665887";
                String name = "Jogja City Mall";
                map.putExtra(NAME, name);
                map.putExtra(LATITUDE, lat);
                map.putExtra(LONGITUDE, longi);
                startActivity(map);
            }
        });
    }

    // REGISTER SENSOR
    @Override
    protected void onStart() {
        super.onStart();
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    // UNREGISTER SENSOR
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    // Jika ada perubahan data sensor
    @Override
    public void onSensorChanged(SensorEvent event) {
        float currentValue = event.values[0];
        changeBackgroundColor(currentValue);
    }

    // Change background
    private void changeBackgroundColor(float currentValue) {
        ScrollView layout = findViewById(R.id.bg);
        if (currentValue > 20000 && currentValue < 30000) {
            layout.setBackgroundColor(Color.WHITE);
        } else if (currentValue > 0 && currentValue <20000) {
            layout.setBackgroundColor(Color.DKGRAY);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}