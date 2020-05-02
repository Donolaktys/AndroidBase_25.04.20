package ru.geekbrains.meteoapp;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

public class DayViewAdapter extends RecyclerView.Adapter<DayViewAdapter.DayViewHolder> {
    private int numberItems;

    public DayViewAdapter(int numberOfItems) {
        numberItems = numberOfItems;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layoutIdOfListItem = R.layout.item_view_day;

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(layoutIdOfListItem, viewGroup, false);

        DayViewHolder viewHolder = new DayViewHolder(view);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull DayViewHolder dayViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class DayViewHolder extends RecyclerView.ViewHolder {

        private TextClock dataDate;
        private TextView dayHigh;
        private TextView dayHighMeasure;
        private TextView dayLow;
        private TextView dayLowMeasure;
        private TextView humidity;
        private ImageView imageConditions;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);

            dataDate = itemView.findViewById(R.id.dataDate);
            dayHigh = itemView.findViewById(R.id.dataDayHigh);
            dayHighMeasure = itemView.findViewById(R.id.dataDayHighMeasure);
            dayLow = itemView.findViewById(R.id.dataDayLow);
            dayLowMeasure = itemView.findViewById(R.id.dataDayLowMeasure);
            humidity = itemView.findViewById(R.id.dataHumidity);
            imageConditions = itemView.findViewById(R.id.dataImageConditions);
        }
    }
}
