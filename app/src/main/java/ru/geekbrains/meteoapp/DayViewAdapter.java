package ru.geekbrains.meteoapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DayViewAdapter extends RecyclerView.Adapter<DayViewAdapter.DayViewHolder> {
    private int numberItems;

    public DayViewAdapter(int numberOfItems) {
        numberItems = numberOfItems;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layoutIdForListItem = R.layout.item_view_day;

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

        DayViewHolder viewHolder = new DayViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder dayViewHolder, int i) {
        dayViewHolder.build(i);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class DayViewHolder extends RecyclerView.ViewHolder {

        private TextView dataDate;
        private TextView dayHighMeasure;
        private TextView dayLowMeasure;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM");
        Calendar calendar = new GregorianCalendar();

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);

            dataDate = itemView.findViewById(R.id.dataDate);
            dayHighMeasure = itemView.findViewById(R.id.dataDayHighMeasure);
            dayLowMeasure = itemView.findViewById(R.id.dataDayLowMeasure);
        }

        void build(int listIndex) {
            calendar.roll(Calendar.DAY_OF_MONTH, listIndex);
            String dayDate = simpleDateFormat.format(calendar.getTime());
            dataDate.setText(dayDate);
            dayHighMeasure.setText(itemView.getContext().getText(R.string.MEASUREMENT_CELSIUS));
            dayLowMeasure.setText(itemView.getContext().getText(R.string.MEASUREMENT_CELSIUS));
        }
    }
}
