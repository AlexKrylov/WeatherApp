package com.example.slate.weatherapp;

import android.content.Context;

/**
 * Created by slate on 2/3/2018.
 */

public class Wether {
    static String getEffect(Context context, int position) {
        String[] strings = context.getResources().getStringArray(R.array.city_weather);
        String effects = strings[position];
        return effects;
    }
}