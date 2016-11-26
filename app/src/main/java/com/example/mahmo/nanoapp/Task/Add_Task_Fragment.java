package com.example.mahmo.nanoapp.Task;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mahmo.nanoapp.Dialogs.DateDialog;
import com.example.mahmo.nanoapp.MainActivity;
import com.example.mahmo.nanoapp.R;
import com.example.mahmo.nanoapp.Database.TaskContract;
import com.example.mahmo.nanoapp.Database.TaskHelper;

/**
 * Created by mahmo on 22/11/2016.
 */

public class Add_Task_Fragment extends Fragment {
    TaskHelper taskHelper;
    ContentValues contentValues;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview=inflater.inflate(R.layout.add_task_fragment,container,false);
        taskHelper = new TaskHelper(getContext());
        contentValues = new ContentValues();
        MainActivity.editable=false;
        EditText date=(EditText) rootview.findViewById(R.id.add_task_date);
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    DateDialog dialog =new DateDialog(view);
                    android.app.FragmentTransaction ft=getFragmentManager().beginTransaction();
                    dialog.show(ft,"DataPicker");

                }
            }
        });

        Button save=(Button)rootview.findViewById(R.id.button2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText TaskName=(EditText) rootview.findViewById(R.id.add_task_name);
                String taskname=(TaskName.getText()).toString();
                if(taskname.isEmpty())
                    taskname="Task";
                EditText TaskDate=(EditText) rootview.findViewById(R.id.add_task_date);
                String taskdate=(TaskDate.getText()).toString();

                EditText Description=(EditText) rootview.findViewById(R.id.add_task_description);
                String description=(Description.getText()).toString();
                MainActivity.tasks.add(new Task(taskname,description,taskdate,MainActivity.TaskId));

                contentValues.put(TaskContract.TaskEntry.TASK_NAME, taskname);
                contentValues.put(TaskContract.TaskEntry.TASK_DESCRIPTION, description);
                contentValues.put(TaskContract.TaskEntry.TASK_DATE, taskdate);
                contentValues.put(TaskContract.TaskEntry.TASK_ID, MainActivity.TaskId);
                taskHelper.insert(contentValues);
                MainActivity.TaskId++;
                Toast toast=Toast.makeText(getContext(),"Saved",Toast.LENGTH_SHORT);
                toast.show();
                Intent move=new Intent(getContext(),MainActivity.class);
                move.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(move);
            }
        });



        Button cancel=(Button)rootview.findViewById(R.id.button1);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move=new Intent(getContext(),MainActivity.class);
                move.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(move);
            }
        });


        return rootview;
    }
}
