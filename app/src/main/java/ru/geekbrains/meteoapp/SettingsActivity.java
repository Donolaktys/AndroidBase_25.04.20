package ru.geekbrains.meteoapp;

import android.os.Bundle;
import android.widget.CompoundButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.snackbar.Snackbar;

public class SettingsActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SwitchCompat switchDarkTheme = findViewById(R.id.nightModeSwitch);
        switchDarkTheme.setChecked(isDarkTheme());
        switchDarkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setDarkTheme(isChecked);
                recreate();
            }
        });

        AppCompatCheckBox cBoxWindSpeed = findViewById(R.id.cBoxWindSpeed);
        cBoxWindSpeed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Snackbar.make(buttonView, getApplicationContext().getString(R.string.windSpeedSnackON), Snackbar.LENGTH_SHORT)
                            .show();
                } else {
                    Snackbar.make(buttonView, getApplicationContext().getString(R.string.windSpeedSnackOFF), Snackbar.LENGTH_SHORT)
                            .show();
                }

            }
        });

        AppCompatCheckBox cBoxPressure = findViewById(R.id.cBoxPressure);
        cBoxPressure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Snackbar.make(buttonView, getApplicationContext().getString(R.string.pressureSnackON), Snackbar.LENGTH_SHORT)
                            .show();
                } else {
                    Snackbar.make(buttonView, getApplicationContext().getString(R.string.pressureSnackOFF), Snackbar.LENGTH_SHORT)
                            .show();
                }

            }
        });

        AppCompatCheckBox cBoxFahrenheit = findViewById(R.id.cBoxFahrenheit);
        cBoxFahrenheit.setChecked(isFahrenheit());
        cBoxFahrenheit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setFahrenheit(isChecked);
            }
        });
    }
}
