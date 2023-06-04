package com.example.pocket_assistant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pocket_assistant.databinding.ActivityMainBinding;
import com.example.pocket_assistant.ui.home.HomeFragment;
import com.example.pocket_assistant.ui.contact.ContactFragment;
import com.example.pocket_assistant.ui.home.PopUp;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    public ActivityMainBinding binding;
    public static float X;
    public static float Y;
    public static float Z;
    public static String Name;
    private TextView textView2 = null;
    private TextView textView3 = null;
    private TextView textView4 = null;
    private TextView textView6 = null;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private LocationManager locationManager;
    boolean stop = false;
    String phone ="";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView6 = (TextView) findViewById(R.id.textView6);

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);



        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_parameter, R.id.navigation_contact)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
    public void SensorActivity() {
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void onResume() {
        super.onResume();
        SensorActivity();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }
    protected void onPause() {
        super.onPause();
        //mSensorManager.unregisterListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Name = sensorEvent.sensor.getName();
        X=sensorEvent.values[0];
        Y=sensorEvent.values[1];
        Z=sensorEvent.values[2];
        //Log.d("Debug","Name" + Name);
        //Log.d("Debug",": X: " + sensorEvent.values[0] + "; Y: " + sensorEvent.values[1] + "; Z: " + sensorEvent.values[2] + ";");
        MainActivity activity = this;
        if(HomeFragment.Onoffstate){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if(!stop){
                        tomber();
                    }

                    stop = true;
                }
            };
            Handler handler = new Handler(Looper.getMainLooper());
            if( X >= 10){
                textView6.setText(getString(R.string.x) + (int) X+ "as overflow");

                PopUp popUp = new PopUp(activity);

                popUp.getStopButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUp.mediaPlayer.stop();
                        popUp.dismiss();
                        stop=true;
                    }
                });

                popUp.build();
                popUp.mediaPlayer.start();

                handler.postDelayed(runnable,3000);

            }
            if( Y >= 10 ){
                textView6.setText(getString(R.string.y) + (int) Y+ "as overflow");

                PopUp popUp = new PopUp(activity);

                popUp.getStopButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUp.mediaPlayer.stop();
                        popUp.dismiss();
                        stop=true;
                        //Log.d("Debug",": X: " + sensorEvent.values[0] + "; Y: " + sensorEvent.values[1] + "; Z: " + sensorEvent.values[2] + ";" + stop);
                    }
                });

                popUp.build();
                popUp.mediaPlayer.start();

                handler.postDelayed(runnable,3000);

            }
            if( Z >= 19 ){
                textView6.setText(getString(R.string.z) + (int) Z + "as overflow");

                PopUp popUp = new PopUp(activity);

                popUp.getStopButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUp.mediaPlayer.stop();
                        popUp.dismiss();
                        stop=true;
                        //Log.d("Debug",": X: " + sensorEvent.values[0] + "; Y: " + sensorEvent.values[1] + "; Z: " + sensorEvent.values[2] + ";" + stop);
                    }
                });

                popUp.build();
                popUp.mediaPlayer.start();

                handler.postDelayed(runnable,3000);

            }
        }
        else {
            //textView6.setText("Protection is off");
        }
        textView2.setText(getString(R.string.x) + (int) MainActivity.X);
        textView3.setText(getString(R.string.y) + (int) MainActivity.Y);
        textView4.setText(getString(R.string.z) + (int) MainActivity.Z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void tomber(){
        if(ContactFragment.start){
            Intent intent=getIntent();
            String numer = intent.getStringExtra("contact");
            if (numer!=null && numer.length()!=0){
                SmsManager.getDefault().sendTextMessage(numer,null,"Je suis tomber" ,null,null);
            }
        }
    }
}