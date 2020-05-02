package ru.geekbrains.meteoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements Constants {
    private TextView localityChoice;
    private TextView temperature;
    private TextView measure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MakeLog.lifeCycle(this, "MAIN Created");

        localityChoice = findViewById(R.id.localityChoice);
        temperature = findViewById(R.id.temperature);
        measure = findViewById(R.id.measure);
        ImageButton settingsBtn = findViewById(R.id.settingsBtn);
        Button oneDayBtn = findViewById(R.id.oneDayBtn);
        Button threeDaysBtn = findViewById(R.id.threeDaysBtn);
        Button weekBtn = findViewById(R.id.weekBtn);
        TextView infoLink = findViewById(R.id.infoLink);
        String link = getApplicationContext().getString(R.string.link);

        //единица измерения по умолчанию
        measure.setText(getApplicationContext().getString(R.string.MEASUREMENT_FAHRENHEIT));

        initDayView(1);

        //обработка нажатия кнопок на первом экране
        //
        // выбор местоположения, открытие экрана выбора
        localityChoice.setOnClickListener(v -> {
            Intent locationIntent = new Intent(MainActivity.this, LocationActivity.class);
            startActivityForResult(locationIntent, REQUEST_CODE_CITY);
        });

        //дополнительная информация
        infoLink.setOnClickListener(v -> {
            Intent info = new Intent(Intent.ACTION_VIEW, Uri.parse(link + localityChoice.getText()));
            startActivity(info);
        });

        //обработка нажатия кнопок выбора вариантов отображения
        oneDayBtn.setOnClickListener(v -> {
            MakeLog.click(this, "\"1 день\"");
            initDayView(1);
        });

        threeDaysBtn.setOnClickListener(v -> {
            MakeLog.click(this, "\"3 дня\"");
            initDayView(3);
        });

        weekBtn.setOnClickListener(v -> {
            MakeLog.click(this, "\"неделя\"");
            initDayView(7);
        });

        // нажатие на кнопку настроек, переход на экран настройки приложения
        settingsBtn.setOnClickListener(v -> {
            MakeLog.click(this, "\"Настройки\"");
            Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settingsIntent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MakeLog.lifeCycle(getApplicationContext(), "onActivityResult");
        if (data != null) {
            if (requestCode == REQUEST_CODE_CITY) {
                if (resultCode == RESULT_OK){
                    String city = data.getStringExtra(CITY);
                    localityChoice.setText(city);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LOCALITY_LABEL, localityChoice.getText().toString());
        outState.putString(TEMPERATURE_LABEL, temperature.getText().toString());
        outState.putString(MEASURE_LABEL, measure.getText().toString());
        MakeLog.lifeCycle(this, "MAIN Сохранение данных");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        localityChoice.setText(savedInstanceState.getString(LOCALITY_LABEL));
        temperature.setText(savedInstanceState.getString(TEMPERATURE_LABEL));
        measure.setText(savedInstanceState.getString(MEASURE_LABEL));
        MakeLog.lifeCycle(this, "MAIN Восстановление данных");
    }

    private void initDayView(int numberOfDays) {
        RecyclerView recyclerView = findViewById(R.id.mainViewDay);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DayViewAdapter adapter = new DayViewAdapter(numberOfDays);
        recyclerView.setAdapter(adapter);
    }
}
