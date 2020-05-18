package ru.geekbrains.meteoapp.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.ArrayMap;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ru.geekbrains.meteoapp.Constants;
import ru.geekbrains.meteoapp.R;
import ru.geekbrains.meteoapp.adapters.AdapterForStringList;
import ru.geekbrains.meteoapp.adapters.SearchHistoryAdapter;
import ru.geekbrains.meteoapp.data.SearchHistory;

public class LocationActivity extends BaseActivity implements Constants {
    private EditText enterLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        enterLocation = findViewById(R.id.enterLocation);

        initList(getSearchHistory());

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

    private void initList(SearchHistory searchHistory) {
        RecyclerView recyclerView = findViewById(R.id.viewCities);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        SearchHistoryAdapter adapter = new SearchHistoryAdapter(searchHistory, getMeasure());
        recyclerView.setAdapter(adapter);
    }

}
