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

import java.util.AbstractList;

import static com.example.mahmo.nanoapp.MainActivity.tasks;


/**
 * Created by mahmo on 20/11/2016.
 */

public class Tasks_Adapter extends RecyclerView.Adapter<Tasks_Adapter.Holder> {
    Context context;
    public Tasks_Adapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context=context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.task,parent,false);
        Holder holder=new Holder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.Name.setText(tasks.get(position).name);
        holder.Description.setText(tasks.get(position).description);
        holder.Date.setText(tasks.get(position).date);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.editable=true;
                MainActivity.editableTaskPosition=position;
                Intent move=new Intent(context,ADDActivity.class);
                context.startActivity(move);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView Name ;
        TextView Description;
        TextView Date;
        public Holder(View itemView){
            super(itemView);
            Name=(TextView) itemView.findViewById(R.id.task_name);
            Description=(TextView) itemView.findViewById(R.id.task_description);
            Date= (TextView) itemView.findViewById(R.id.task_date);
        }
    }


}