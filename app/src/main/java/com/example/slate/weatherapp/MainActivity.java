package com.example.slate.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private RadioGroup radioGroup;
    private CheckBox pressure, humidity;
    private Intent intent;
    private RadioButton celsius, fahrenheit;
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

        Switch aSwitch = findViewById(R.id.settings);
        intent = new Intent(MainActivity.this, WeatherActivity.class);

        radioGroup = findViewById(R.id.radio_group);
        radioGroup.setVisibility(View.GONE);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.Celsius) {
                    Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.show_temperature_in_celsius), Toast.LENGTH_SHORT);
                    toast.show();
                    String celsius = Indexes.getCelsius(MainActivity.this, spinner.getSelectedItemPosition());
                    intent.putExtra(WeatherActivity.EXTRA_MESSAGE, celsius);
                }
                if (checkedId == R.id.Fahrenheit) {
                    Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.show_temperature_in_fahrenheit), Toast.LENGTH_SHORT);
                    toast.show();
                    String fahrenheit = Indexes.getFahrenheit(MainActivity.this, spinner.getSelectedItemPosition());
                    intent.putExtra(WeatherActivity.EXTRA_MESSAGE, fahrenheit);
                }
            }
        });

        celsius = findViewById(R.id.Celsius);
        fahrenheit = findViewById(R.id.Fahrenheit);

        pressure = findViewById(R.id.pressure);
        pressure.setVisibility(View.GONE);
        humidity = findViewById(R.id.humidity);
        humidity.setVisibility(View.GONE);
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.button_show_weather) {

                startActivity(intent);
            }
        }
    };

    public void onSwitchClicked(View view) {
        boolean on = ((Switch) view).isChecked();
        if (on) {
            CharSequence text = getString(R.string.switch_enable_settings);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
            radioGroup.setVisibility(View.VISIBLE);
            pressure.setVisibility(View.VISIBLE);
            humidity.setVisibility(View.VISIBLE);
        } else {
            CharSequence text = getString(R.string.switch_disable_settings);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
            radioGroup.setVisibility(View.GONE);
            pressure.setVisibility(View.GONE);
            humidity.setVisibility(View.GONE);
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.pressure:
                if (checked) {
                    Toast toast = Toast.makeText(this, R.string.show_pressure_on_activity, Toast.LENGTH_SHORT);
                    toast.show();
                    String pressure = Indexes.getPressure(MainActivity.this, spinner.getSelectedItemPosition());
                    intent.putExtra(WeatherActivity.PRESSURE, pressure);
                } else {
                    Toast toast = Toast.makeText(this, R.string.hide_pressure_on_activity, Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.humidity:
                if (checked) {
                    Toast toast = Toast.makeText(this, R.string.show_humidity_on_activity, Toast.LENGTH_SHORT);
                    toast.show();
                    String humidity = Indexes.getHumidity(MainActivity.this, spinner.getSelectedItemPosition());
                    intent.putExtra(WeatherActivity.HUMIDITY, humidity);
                } else {
                    Toast toast = Toast.makeText(this, R.string.hide_humidity_on_activity, Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

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
