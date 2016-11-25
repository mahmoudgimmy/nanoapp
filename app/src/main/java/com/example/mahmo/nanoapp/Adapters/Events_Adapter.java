package com.example.mahmo.nanoapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mahmo.nanoapp.ADDActivity;
import com.example.mahmo.nanoapp.MainActivity;
import com.example.mahmo.nanoapp.R;

import static com.example.mahmo.nanoapp.MainActivity.events;

/**
 * Created by mahmo on 20/11/2016.
 */

public class Events_Adapter extends RecyclerView.Adapter<Events_Adapter.Holder> {
    Context context;
    public Events_Adapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context=context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.event,parent,false);
        Holder holder=new Holder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.Name.setText(events.get(position).name);
        holder.Description.setText(events.get(position).description);
        holder.Date.setText(events.get(position).date);
        holder.Place.setText(events.get(position).place);
        holder.Start_Time.setText(events.get(position).start_time);
        holder.End_Time.setText(events.get(position).end_time);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.editable=true;
                MainActivity.editableEventPosition=position;
                Intent move=new Intent(context,ADDActivity.class);
                context.startActivity(move);
            }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView Name ;
        TextView Description;
        TextView Date;
        TextView Place;
        TextView Start_Time;
        TextView End_Time;
        public Holder(View itemView){
            super(itemView);
            Name=(TextView) itemView.findViewById(R.id.event_name);
            Description=(TextView) itemView.findViewById(R.id.event_description);
            Date= (TextView) itemView.findViewById(R.id.event_date);
            Place=(TextView) itemView.findViewById(R.id.event_place);
            Start_Time= (TextView) itemView.findViewById(R.id.event_start_time);
            End_Time=(TextView) itemView.findViewById(R.id.event_end_time);
        }
    }


}