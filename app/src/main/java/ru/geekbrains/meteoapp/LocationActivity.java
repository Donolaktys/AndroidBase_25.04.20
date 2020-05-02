package ru.geekbrains.meteoapp;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity implements Constants {
    private EditText enterLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        enterLocation = findViewById(R.id.enterLocation);

        String[] cities = getResources().getStringArray(R.array.cities);

        initList(cities);

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

    private void initList(String[] cities) {
        RecyclerView recyclerView = findViewById(R.id.viewCities);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        AdapterForStringList adapter = new AdapterForStringList(cities, R.layout.item_cities);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new AdapterForStringList.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                enterLocation.setText(((TextView) view).getText());
            }
        });
    }

}
