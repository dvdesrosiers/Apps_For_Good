package com.example.appsforgood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BusSchedule_Recycler_Adapter extends RecyclerView.Adapter<BusSchedule_Recycler_Adapter.myViewHolder>{
Context context; //used for inflating layout
ArrayList<BusRoutes> busRoute; //arrayList that holds all variables
    public BusSchedule_Recycler_Adapter (Context context, ArrayList<BusRoutes> busRoute) {
        this.context = context;
        this.busRoute = busRoute;
    }

    @NonNull
    @Override
    public BusSchedule_Recycler_Adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflates layout (Giving a look to our rows)

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new BusSchedule_Recycler_Adapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusSchedule_Recycler_Adapter.myViewHolder holder, int position) {
        //assigning values to the views created in the recycler_view_row layout file as they come back onto the screen
        //based on the position of the recycler view


        holder.tvName.setText(busRoute.get(position).getRouteName());
        holder.imageView.setImageResource(busRoute.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        //returns amount of items in total
        //recycler wants to know the number of items you want displayed

        return busRoute.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        //similar to the onCreate method
        //grabs the views from the recycler_view_row_layout file

        ImageView imageView;
        TextView tvName;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView2);
            tvName = itemView.findViewById(R.id.textView3);



        }
    }


}
