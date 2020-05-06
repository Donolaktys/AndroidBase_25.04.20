package ru.geekbrains.meteoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;


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
        DividerItemDecoration itemDecoration = new DividerItemDecoration(rootView.getContext(),  LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(Objects.requireNonNull(rootView.getContext().getDrawable(R.drawable.day_view_decorator)));
        recyclerView.addItemDecoration(itemDecoration);
        return rootView;
    }
}
