package ru.geekbrains.meteoapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArrayMap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import ru.geekbrains.meteoapp.Constants;
import ru.geekbrains.meteoapp.data.Measure;
import ru.geekbrains.meteoapp.data.SearchHistory;


public class BaseActivity extends AppCompatActivity implements Constants {
    private Measure measurement;
    private String requestUri;
    private SearchHistory searchHistory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        initMeasure();
        setMeasure();
        initHistory();
    }

    private void setMeasure() {
        if (isFahrenheit()) {
            measurement.setMeasure(MEASUREMENT_FAHRENHEIT);
        } else {
            measurement.setMeasure(MEASUREMENT_CELSIUS);
        }
    }

    private void setTheme() {
        if (isDarkTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void initMeasure() {
        if (measurement == null) {
            measurement = new Measure();
        }
    }

    private void initHistory() {
        if (searchHistory == null) {
            searchHistory = new SearchHistory();
            ArrayMap<String, String> historyMap = new ArrayMap<>();
            searchHistory.setSearchHistory(historyMap);
            searchHistory.getSearchHistory().put("Moscow", "n/a");
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

    public void putSearchHistory(String city, String temp) {
        searchHistory.getSearchHistory().put(city, temp);
    }

    public SearchHistory getSearchHistory() {
        return searchHistory;
    }
}
