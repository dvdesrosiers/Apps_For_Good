package com.example.appsforgood.firestore;

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

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.ViewHolder> {
    Context context;
    ArrayList<Alerts> arrayList;
    OnItemClickListener onItemClickListener;

    public AlertAdapter(Context context, ArrayList<Alerts> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.alert_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.time.setText(arrayList.get(position).getTime());
        holder.date.setText(arrayList.get(position).getDate());
        holder.date.setText(arrayList.get(position).getIssue());

        holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(arrayList.get(position)));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView time, date, issue;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            time = itemView.findViewById(R.id.list_item_time);
            date = itemView.findViewById(R.id.list_item_date);
            issue = itemView.findViewById(R.id.list_item_issue);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(Alerts Alerts);
    }
}
