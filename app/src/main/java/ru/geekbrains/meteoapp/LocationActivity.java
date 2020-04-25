package ru.geekbrains.meteoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;

public class LocationActivity extends AppCompatActivity implements Constants {
    private EditText enterLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        enterLocation = findViewById(R.id.enterLocation);

        enterLocation.setOnKeyListener((v, actionId, event) -> {
            if( event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                Intent intent = new Intent();
                intent.putExtra(CITY, enterLocation.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                return true;
            }
            return false;
        });
    }
}
