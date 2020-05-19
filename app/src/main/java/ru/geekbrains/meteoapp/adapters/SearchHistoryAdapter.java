package ru.geekbrains.meteoapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.geekbrains.meteoapp.R;
import ru.geekbrains.meteoapp.data.SearchHistory;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder> {
    private String measure;
    private SearchHistory searchHistory;

    public SearchHistoryAdapter(SearchHistory searchHistory, String measure) {
        this.searchHistory = searchHistory;
        this.measure = measure;
    }

    @NonNull
    @Override
    public SearchHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cities, viewGroup, false);
        return new SearchHistoryAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHistoryAdapter.ViewHolder holder, int position) {
        holder.build(position);
    }

    @Override
    public int getItemCount() {
        return searchHistory.getSearchHistory() == null ? 0 : searchHistory.getSearchHistory().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemCity;
        private TextView itemCityTemp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            init();
        }

        void init() {
            itemCity = itemView.findViewById(R.id.itemCity);
            itemCityTemp = itemView.findViewById(R.id.itemCityTemp);
        }

        void build(int position) {
            itemCity.setText(searchHistory.getSearchHistory().keyAt(position));
            itemCityTemp.setText(searchHistory.getSearchHistory().valueAt(position) + measure);
        }
    }
}
