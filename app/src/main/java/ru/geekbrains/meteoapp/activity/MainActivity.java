package ru.geekbrains.meteoapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import ru.geekbrains.meteoapp.BuildConfig;
import ru.geekbrains.meteoapp.Constants;
import ru.geekbrains.meteoapp.MakeLog;
import ru.geekbrains.meteoapp.R;
import ru.geekbrains.meteoapp.RequestBuilder;
import ru.geekbrains.meteoapp.data.WeatherRequest;
import ru.geekbrains.meteoapp.fragments.OneDayFragment;
import ru.geekbrains.meteoapp.fragments.ThreeDaysFragment;
import ru.geekbrains.meteoapp.fragments.WeekFragment;


public class MainActivity extends BaseActivity implements Constants {
    private TextView localityChoice;
    private TextView temperature;
    private TextView measure;
    private TextView infoLink;
    private TextView dayHighMeasure;
    private TextView dayLowMeasure;

    private OneDayFragment oneDayFragment;
    private ThreeDaysFragment threeDaysFragment;
    private WeekFragment weekFragment;
    private FragmentManager daysFragmentManager;

    private ImageButton settingsBtn;
    private Button oneDayBtn;
    private Button threeDaysBtn;
    private Button weekBtn;

    private String link;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = daysFragmentManager
                    .beginTransaction();
            fragmentTransaction.add(R.id.mainFragmentView, oneDayFragment);
            fragmentTransaction.commit();
        }

        localityChoice.setOnClickListener(v -> {
            Intent locationIntent = new Intent(MainActivity.this, LocationActivity.class);
            startActivityForResult(locationIntent, REQUEST_CODE_CITY);
        });

        infoLink.setOnClickListener(v -> {
            Intent info = new Intent(Intent.ACTION_VIEW, Uri.parse(link + localityChoice.getText()));
            startActivity(info);
        });

        oneDayBtn.setOnClickListener(v -> {
            replaceFragment(oneDayFragment);
        });

        threeDaysBtn.setOnClickListener(v -> {
            replaceFragment(threeDaysFragment);
        });

        weekBtn.setOnClickListener(v -> {
            replaceFragment(weekFragment);
        });

        settingsBtn.setOnClickListener(v -> {
            MakeLog.click(this, "\"Настройки\"");
            Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivityForResult(settingsIntent, SETTING_CODE);
        });
    }

    private void init() {
        localityChoice = findViewById(R.id.localityChoice);
        temperature = findViewById(R.id.temperature);
        measure = findViewById(R.id.measure);
        settingsBtn = findViewById(R.id.settingsBtn);
        oneDayBtn = findViewById(R.id.oneDayBtn);
        threeDaysBtn = findViewById(R.id.threeDaysBtn);
        weekBtn = findViewById(R.id.weekBtn);
        infoLink = findViewById(R.id.infoLink);
        dayHighMeasure = findViewById(R.id.dayHighMeasure);
        dayLowMeasure = findViewById(R.id.dayLowMeasure);
        link = getApplicationContext().getString(R.string.link);
        daysFragmentManager = getSupportFragmentManager();
        oneDayFragment = new OneDayFragment();
        threeDaysFragment = new ThreeDaysFragment();
        weekFragment = new WeekFragment();
        measure.setText(getMeasure());
        dayHighMeasure.setText(getMeasure());
        dayLowMeasure.setText(getMeasure());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFragmentView, fragment);
        fragmentTransaction.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CITY) {
            if (resultCode == RESULT_OK) {
                String city = data.getStringExtra(CITY);
                localityChoice.setText(city);
                uriBuild(city);
                initBuilder(getRequestUri());
            }
        }
        if (requestCode == SETTING_CODE) {

            if (isFahrenheit()) {
                measure.setText(MEASUREMENT_FAHRENHEIT);

            } else {
                measure.setText(MEASUREMENT_CELSIUS);
            }
            if (getRequestUri() != null) {
                uriBuild(localityChoice.getText().toString());
                initBuilder(getRequestUri());
            }
            recreate();
        }
    }

    private void uriBuild(String city) {
        String requestUri = WEATHER_URL + city;
        if (isFahrenheit()) {
            requestUri += "&units=imperial";
        } else {
            requestUri += "&units=metric";
        }
        requestUri += "&APPID=" + BuildConfig.WEATHER_API_KEY;
        setRequestUri(requestUri);
    }

    private void initBuilder(String uri) {
        final RequestBuilder requestBuilder = new RequestBuilder(new WeatherRequest(), uri);
                displayWeather(requestBuilder.getWeatherRequest());
    }

    private void displayWeather(WeatherRequest weatherRequest) {
        try {
            temperature.setText(String.format("%d", (int) weatherRequest.getMain().getTemp()));
        } catch (NullPointerException e) {
            localityChoice.setText("");
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Не верный запрос!!!", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LOCALITY_LABEL, localityChoice.getText().toString());
        outState.putString(TEMPERATURE_LABEL, temperature.getText().toString());
        outState.putString(MEASURE_LABEL, measure.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        localityChoice.setText(savedInstanceState.getString(LOCALITY_LABEL));
        temperature.setText(savedInstanceState.getString(TEMPERATURE_LABEL));
        measure.setText(savedInstanceState.getString(MEASURE_LABEL));
    }
}
