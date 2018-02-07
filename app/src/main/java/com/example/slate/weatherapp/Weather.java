package com.example.slate.weatherapp;

import android.content.Context;

public class Weather {
    static String getEffect(Context context, int position) {
        String[] strings = context.getResources().getStringArray(R.array.city_weather);
        String effects = strings[position];
        return effects;
    }
}