package com.example.slate.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "id";
    private String weather;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);
        Intent intent = getIntent();
        if(intent!=null){
            weather = intent.getStringExtra(EXTRA_MESSAGE);
            TextView textView = findViewById(R.id.weather_desc);
            textView.setText(weather);
        }
        Button button = findViewById(R.id.share);
        button.setOnClickListener(shareWithAFriend);
    }

    private final View.OnClickListener shareWithAFriend = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.share) {
                String messageText = weather;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, messageText);
                String chooserTitle = getString(R.string.chooser);
                Intent chosenIntent = Intent.createChooser(intent, chooserTitle);
                startActivity(chosenIntent);
            }
        }
    };
}
