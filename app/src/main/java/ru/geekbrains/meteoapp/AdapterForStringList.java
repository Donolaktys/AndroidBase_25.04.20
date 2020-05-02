package ru.geekbrains.meteoapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AdapterForStringList extends RecyclerView.Adapter<AdapterForStringList.ViewHolder> {
    private String[] dataSource;
    private int itemLayoutID;
    private OnItemClickListener itemClickListener;

    public AdapterForStringList(String[] dataSource, int itemLayoutID){
        this.dataSource = dataSource;
        this.itemLayoutID = itemLayoutID;
    }

    @NonNull
    @Override
    public AdapterForStringList.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(itemLayoutID, viewGroup, false);
        return new AdapterForStringList.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForStringList.ViewHolder viewHolder, int i) {
        viewHolder.getTextView().setText(dataSource[i]);
    }

    @Override
    public int getItemCount() {
        return dataSource.length;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
