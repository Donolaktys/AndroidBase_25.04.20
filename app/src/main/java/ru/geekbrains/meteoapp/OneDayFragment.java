package ru.geekbrains.meteoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneDayFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_one_day, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.fragmentViewOneDay);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DayViewAdapter adapter = new DayViewAdapter(1);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

//        private void initDayView(Context context, DayViewSource data) {
//        RecyclerView recyclerView = context.fi
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        DayViewAdapter adapter = new DayViewAdapter(numberOfDays);
//        recyclerView.setAdapter(adapter);
//
//        itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
//        itemDecoration.setDrawable(getDrawable(R.drawable.day_view_decorator));
//        recyclerView.addItemDecoration(itemDecoration);
}
