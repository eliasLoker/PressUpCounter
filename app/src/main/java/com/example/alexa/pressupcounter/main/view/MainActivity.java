package com.example.alexa.pressupcounter.main.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.alexa.pressupcounter.Logger;
import com.example.alexa.pressupcounter.R;
import com.example.alexa.pressupcounter.databinding.ActivityMainBinding;
import com.example.alexa.pressupcounter.firstlaunch.view.FirstLaunchFragment;
import com.example.alexa.pressupcounter.main.viewmodel.MainViewModel;
import com.example.alexa.pressupcounter.main.viewmodel.MainViewModelImpl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainViewModel = new MainViewModelImpl();
        binding.setViewModel(mMainViewModel);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, FirstLaunchFragment.newInstance())
                    .commit();
        }
        Logger.d("MainActivity", "onCreate");
        // JSON example
        /*
        try {
            JSONObject object = new JSONObject();
            object.put("test", true);
            JSONObject data = new JSONObject();
            data.put("age", 25);
            JSONArray array = new JSONArray();
            array.put("blond");
            array.put("brown");
            data.put("hair", array);
            object.put("data", data);
            Log.d("MAIN", "json: " + object.toString());

            boolean test = object.getBoolean("test");
            JSONObject jData = object.getJSONObject("data");
            int age = jData.getInt("age");
            Log.d("MAIN", "test=" + test + " age=" + age);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */

    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
}
