package com.example.alexa.pressupcounter.splash;

import android.content.Intent;
import android.os.Bundle;

import com.example.alexa.pressupcounter.main.view.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {


    //private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = MainActivity.getIntent(this);
        startActivity(intent);
        /*
        mSharedPreferences = getSharedPreferences(Constants.MY_SETTINGS, Context.MODE_PRIVATE);

        boolean hasVisited = mSharedPreferences.getBoolean(Constants.TAG_FOR_SPLASH_BOOLEAN, false);
        if (!hasVisited) {
            Intent intent = FirstLaunchActivity.getIntent(this);
            startActivity(intent);

            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(Constants.TAG_FOR_SPLASH_BOOLEAN, true);
            editor.commit();
        } else {
            Intent intent = MainActivity.getIntent(this);
            startActivity(intent);

        }*/

        finish();
    }
}
