package org.openweatherapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.openweatherapp.R;
import org.openweatherapp.models.City;

public class CityListAdapter extends ListAdapter<City, CityListAdapter.CityViewHolder> {

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CityListAdapter(@NonNull DiffUtil.ItemCallback<City> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_item, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City current = getItem(position);
        holder.bind(current.getCity());
    }

    public static class CityDiff extends DiffUtil.ItemCallback<City> {

        @Override
        public boolean areItemsTheSame(@NonNull City oldItem, @NonNull City newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull City oldItem, @NonNull City newItem) {
            return oldItem.getCity().equals(newItem.getCity());
        }
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {

        private final TextView cityItemView;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);

            cityItemView = itemView.findViewById(R.id.city);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }

        public void bind(String text) {
            cityItemView.setText(text);
        }
    }
}
