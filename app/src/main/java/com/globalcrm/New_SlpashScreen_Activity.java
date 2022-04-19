package com.globalcrm;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Created by ADMIJN on 24-Apr-18.
 */

public class New_SlpashScreen_Activity extends AppCompatActivity {

    ImageView imageView;
    private static int SPLASH_TIME_OUT = 3000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    /* @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slpash);

    /* mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    //Toast.makeText(getApplicationContext(), "New Appointment : " + message, Toast.LENGTH_LONG).show();

                }
            }
        };*/

        imageView = (ImageView) this.findViewById(R.id.imageView);

        new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(New_SlpashScreen_Activity.this, New_OTP_Activity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
    }

    @Override
    protected void onPause() {
       // LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
}








