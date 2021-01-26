package com.example.projectequaleyes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TitlesAdapter extends
        RecyclerView.Adapter<TitlesAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View titleView = inflater.inflate(R.layout.title_active, parent, false);

        ViewHolder viewHolder = new ViewHolder(titleView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Titles title = titlesList.get(position);

        TextView textView = holder.titleTextView;
        textView.setText(title.getTitle());
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#DAD2D1D8"));
        }
    }

    @Override
    public int getItemCount() {
        return titlesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }

    private List<Titles> titlesList;

    public TitlesAdapter(List<Titles> titlesList1) {
        titlesList = titlesList1;
    }
}
