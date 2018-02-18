package com.example.slate.weatherapp;

import android.content.Context;

public class Indexes {
    public static String getCelsius(Context context, int position) {
        String[] indexes = context.getResources().getStringArray(R.array.city_weather_celsius);
        return indexes[position];
    }

    public static String getFahrenheit(Context context, int position) {
        String[] indexes = context.getResources().getStringArray(R.array.city_weather_fahrenheit);
        return indexes[position];
    }

    public static String getHumidity(Context context, int position) {
        String[] indexes = context.getResources().getStringArray(R.array.city_humidity);
        return indexes[position];
    }

    public static String getPressure(Context context, int position) {
        String[] indexes = context.getResources().getStringArray(R.array.city_pressure);
        return indexes[position];
    }
}
