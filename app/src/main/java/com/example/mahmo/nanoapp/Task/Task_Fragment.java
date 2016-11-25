package com.example.mahmo.nanoapp.Task;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahmo.nanoapp.Adapters.Tasks_Adapter;
import com.example.mahmo.nanoapp.MainActivity;
import com.example.mahmo.nanoapp.R;
import com.example.mahmo.nanoapp.Database.TaskHelper;

/**
 * Created by mahmo on 18/11/2016.
 */

public class Task_Fragment extends Fragment {
    public static Tasks_Adapter TaskAdapter;
    public TaskHelper taskHelper;
    public Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.task_fragment,container,false);
        RecyclerView recyclerView=(RecyclerView) rootview.findViewById(R.id.recycleView_tasks);
        taskHelper=new TaskHelper(getContext());
        cursor=taskHelper.getData();
        if(!cursor.equals(null)) {
            cursor.moveToFirst();
            MainActivity.tasks.clear();
            while (cursor.isAfterLast() == false) {
                MainActivity.tasks.add(new Task(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
                cursor.moveToNext();
            }
        }

        if(MainActivity.tasks.isEmpty())
        {
            android.app.FragmentManager fm= getFragmentManager();
            fm.beginTransaction().replace(R.id.content_fragment,new Empty_task()).commit();
        }
        else {
            TaskAdapter = new Tasks_Adapter(getContext());
            recyclerView.setAdapter(TaskAdapter);
            Task_Fragment.TaskAdapter.notifyDataSetChanged();
            LinearLayoutManager lm = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(lm);
        }
        return rootview;
    }
}
