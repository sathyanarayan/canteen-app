package com.example.ubundu.canteen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;



public class Splashscreen extends Activity {
   // private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */


            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                Intent k = new Intent(Splashscreen.this, MainActivity.class);
                startActivity(k);

                // close this activity
                finish();
            }
        }, 3000);
    }
    public void onPause(){

        super.onPause();
    }

}