package com.example.slate.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "id";
    public static final String HUMIDITY = "humidity";
    public static final String PRESSURE = "pressure";
    private static final String WEATHERAPP = "WEATHERAPP";
    private String temperature;
    private String humidity;
    private String pressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);
        Intent intent = getIntent();
        if (intent != null) {
            temperature = intent.getStringExtra(EXTRA_MESSAGE);
            TextView tempView = findViewById(R.id.weather_desc);
            tempView.setText(temperature);

            humidity = intent.getStringExtra(HUMIDITY);
            TextView humidityView = findViewById(R.id.humidity);
            humidityView.setText(humidity);

            pressure = intent.getStringExtra(PRESSURE);
            TextView pressureView = findViewById(R.id.pressure);
            pressureView.setText(pressure);
        }

        Button button = findViewById(R.id.share);
        button.setOnClickListener(shareWithAFriend);
    }

    private final View.OnClickListener shareWithAFriend = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.share) {
                String messageText = temperature;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, messageText);
                String chooserTitle = getString(R.string.chooser);
                Intent chosenIntent = Intent.createChooser(intent, chooserTitle);
                startActivity(chosenIntent);
            }
        }
    };

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
