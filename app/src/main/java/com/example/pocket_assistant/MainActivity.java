package com.example.pocket_assistant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pocket_assistant.databinding.ActivityMainBinding;
import com.example.pocket_assistant.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    public ActivityMainBinding binding;
    public static float X;
    public static float Y;
    public static float Z;
    public static String Name;
    private TextView textView2 = null;
    private TextView textView3 = null;
    private TextView textView4 = null;
    private TextView textView5 = null;
    private TextView textView6 = null;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private LocationManager locationManager;
    boolean stop = false;

    double latitude; ;
    double longitude;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);



        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
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
        if(HomeFragment.Onoffstate){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    tomber(stop);
                    stop = true;
                }
            };
            Handler handler = new Handler(Looper.getMainLooper());
            if( X >= 10  ){
                textView6.setText(getString(R.string.x) + (int) X+ "as overflow");
                //ouvre pop up
                handler.postDelayed(runnable,3000);

            }
            if( Y >= 10 ){
                textView6.setText(getString(R.string.y) + (int) Y+ "as overflow");
                //ouvre pop up
                handler.postDelayed(runnable,3000);

            }
            if( Z >= 19 ){
                textView6.setText(getString(R.string.z) + (int) Z + "as overflow");
                //ouvre pop up
                handler.postDelayed(runnable,3000);


            }
        }
        else {
            textView6.setText("Protection is off");
        }
         textView2.setText(getString(R.string.x) + (int) MainActivity.X);
         textView3.setText(getString(R.string.y) + (int) MainActivity.Y);
         textView4.setText(getString(R.string.z) + (int) MainActivity.Z);
         textView5.setText(getString(R.string.name) + MainActivity.Name);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void tomber( boolean stop){
        if(!stop){
            //String num[] = new String[10];
            //TextView[] tab_numeros = new TextView[10];
            //tab_numeros[0]=findViewById(R.id.phone_view0);
            //for(int i =0 ;i<10;i++){
            //    num[i]=tab_numeros[i].getText().toString();
            //}
            for(int b =0 ;b<1;b++){
                //String numer = num[b];
                String numer = "0754391322";
                //String numer = "0629363589";
                //String numer ="0648481721";
                if(numer !="0" || numer != null){
                    SmsManager.getDefault().sendTextMessage(numer,null,"Tkt on test l'app" ,null,null);
                }
            }

        }
    }

}