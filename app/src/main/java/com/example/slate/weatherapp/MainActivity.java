package com.example.slate.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_POSITION = "Position";
    private static final String WEATHERAPP = "WEATHERAPP";
    private SharedPreferences mSettings;
    private SharedPreferences.Editor prefEditor;
    private int saved_position;
    private int code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Button button = findViewById(R.id.button_show_weather);
        button.setOnClickListener(onClickListener);

        spinner = findViewById(R.id.spinner);
        if (mSettings.contains(APP_PREFERENCES_POSITION)) {
            saved_position = mSettings.getInt(APP_PREFERENCES_POSITION, 0);
        }
        spinner.setSelection(saved_position);

        if (savedInstanceState != null) {
            saved_position = savedInstanceState.getInt(APP_PREFERENCES_POSITION);
        }
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.button_show_weather) {
                String result = Weather.getEffect(MainActivity.this, spinner.getSelectedItemPosition());
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                intent.putExtra(WeatherActivity.EXTRA_MESSAGE, result);
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(WEATHERAPP, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putInt(APP_PREFERENCES_POSITION, saved_position);
    }

    @Override
    protected void onStop() {
        Log.d(WEATHERAPP, "onStop()");
        super.onStop();
        int save_position = spinner.getSelectedItemPosition();
        prefEditor = mSettings.edit();
        prefEditor.putInt(APP_PREFERENCES_POSITION, save_position);
        prefEditor.apply();
    }

    @Override
    protected void onStart() {
        Log.d(WEATHERAPP, "onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(WEATHERAPP, "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(WEATHERAPP, "onPause()");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d(WEATHERAPP, "onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d(WEATHERAPP, "onRestart()");
        super.onRestart();
    }
}
