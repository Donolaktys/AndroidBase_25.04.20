package ru.geekbrains.meteoapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReViewAdapter extends RecyclerView.Adapter<ReViewAdapter.ViewHolder> {

    private String[] dataSource;
    private int itemLayoutID;

    public ReViewAdapter(String[] dataSource, int itemLayoutID){
        this.dataSource = dataSource;
        this.itemLayoutID = itemLayoutID;
    }

    @NonNull
    @Override
    public ReViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(itemLayoutID, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReViewAdapter.ViewHolder viewHolder, int i) {
        viewHolder.getTextView().setText(dataSource[i]);
    }

    @Override
    public int getItemCount() {
        return dataSource.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
