package ru.geekbrains.meteoapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import ru.geekbrains.meteoapp.Constants;
import ru.geekbrains.meteoapp.data.Measure;

public class BaseActivity extends AppCompatActivity implements Constants {
    private Measure measurement;
    private String requestUri;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isDarkTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        measurement = new Measure();
        if (isFahrenheit()) {
            measurement.setMeasure(MEASUREMENT_FAHRENHEIT);
        } else {
            measurement.setMeasure(MEASUREMENT_CELSIUS);
        }

    }

    protected boolean isDarkTheme() {
        SharedPreferences sharedPreferences = getSharedPreferences(NAME_PREFERENCE_THEME, MODE_PRIVATE);

        return sharedPreferences.getBoolean(IS_DARK_THEME, true);
    }

    protected void setDarkTheme(boolean isDarkTheme) {
        SharedPreferences sharedPreferences = getSharedPreferences(NAME_PREFERENCE_THEME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_DARK_THEME, isDarkTheme);
        editor.apply();
    }

    protected boolean isFahrenheit() {
        SharedPreferences sharedPreferences = getSharedPreferences(NAME_PREFERENCE_MEASURE, MODE_PRIVATE);

        return sharedPreferences.getBoolean(MEASURE_MODE, false);
    }

    protected void setFahrenheit(boolean isFahrenheit) {
        SharedPreferences sharedPreferences = getSharedPreferences(NAME_PREFERENCE_MEASURE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MEASURE_MODE, isFahrenheit);
        editor.apply();
    }

    public String getMeasure() {
        return measurement.getMeasure();
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }
}
