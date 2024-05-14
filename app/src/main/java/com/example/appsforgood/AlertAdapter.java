package com.example.appsforgood;

// Import statements for necessary Android libraries and components
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsforgood.R;

import java.text.MessageFormat;
import java.util.ArrayList;

// This class adapts the alerts to display them with a RecyclerView
public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.ViewHolder> {
    Context context;
    ArrayList<Alerts> arrayList;
    OnItemClickListener onItemClickListener;

    // Constructor to initialize the adapter with context and list of alerts
    public AlertAdapter(Context context, ArrayList<Alerts> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    // Create new views (invoked by layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.alert_list_item, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position and bind it to the view
        holder.time.setText(arrayList.get(position).getTime());
        holder.date.setText(arrayList.get(position).getDate());
        holder.date.setText(arrayList.get(position).getIssue());

        // Set click listener for each item in the RecyclerView
        holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(arrayList.get(position)));
    }

    // Review the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView time, date, issue;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            time = itemView.findViewById(R.id.list_item_time);
            date = itemView.findViewById(R.id.list_item_date);
            issue = itemView.findViewById(R.id.list_item_issue);
        }
    }

    // Set click listener for RecyclerView items
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    // Interface for item click listener
    public interface OnItemClickListener {
        void onClick(Alerts Alerts);
    }
}
