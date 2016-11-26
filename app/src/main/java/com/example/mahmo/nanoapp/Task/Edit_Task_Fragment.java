package com.example.mahmo.nanoapp.Task;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.DialogInterface;
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
 * Created by mahmo on 25/11/2016.
 */

public class Edit_Task_Fragment extends Fragment {

    TaskHelper taskHelper;
    ContentValues contentValues;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.edit_task_fragment, container, false);
        taskHelper = new TaskHelper(getContext());
        contentValues = new ContentValues();
        MainActivity.editable=false;
        ((EditText) rootview.findViewById(R.id.edit_task_name))
                .setText(MainActivity.tasks.get(MainActivity.editableTaskPosition).name);

        ((EditText) rootview.findViewById(R.id.edit_task_description))
                .setText(MainActivity.tasks.get(MainActivity.editableTaskPosition).description);

        ((EditText) rootview.findViewById(R.id.edit_task_date))
                .setText(MainActivity.tasks.get(MainActivity.editableTaskPosition).date);

        EditText date=(EditText) rootview.findViewById(R.id.edit_task_date);
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

        Button delete=(Button)rootview.findViewById(R.id.button7);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(
                        getContext());
                alert.setTitle("Alert!!");
                alert.setMessage("Are you sure to delete?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        MainActivity.editable=false;
                        taskHelper.delete(MainActivity.tasks.get(MainActivity.editableTaskPosition).id);
                        MainActivity.tasks.remove(MainActivity.editableTaskPosition);
                        Intent move=new Intent(getContext(),MainActivity.class);
                        move.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(move);
                        Toast toast=Toast.makeText(getContext(),"Deleted",Toast.LENGTH_SHORT);
                        toast.show();

                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();

            }
        });

        Button save=(Button)rootview.findViewById(R.id.button5);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.editable=false;
                EditText TaskName=(EditText) rootview.findViewById(R.id.edit_task_name);
                MainActivity.tasks.get(MainActivity.editableTaskPosition).name=(TaskName.getText()).toString();

                if(MainActivity.tasks.get(MainActivity.editableTaskPosition).name.isEmpty())
                    MainActivity.tasks.get(MainActivity.editableTaskPosition).name="Task";

                EditText TaskDate=(EditText) rootview.findViewById(R.id.edit_task_date);
                MainActivity.tasks.get(MainActivity.editableTaskPosition).date=(TaskDate.getText()).toString();

                EditText Description=(EditText) rootview.findViewById(R.id.edit_task_description);
                MainActivity.tasks.get(MainActivity.editableTaskPosition).description=(Description.getText()).toString();

                contentValues.put(TaskContract.TaskEntry.TASK_NAME, MainActivity.tasks.get(MainActivity.editableTaskPosition).name);
                contentValues.put(TaskContract.TaskEntry.TASK_DESCRIPTION, MainActivity.tasks.get(MainActivity.editableTaskPosition).description);
                contentValues.put(TaskContract.TaskEntry.TASK_DATE, MainActivity.tasks.get(MainActivity.editableTaskPosition).date);
                contentValues.put(TaskContract.TaskEntry.TASK_ID,MainActivity.tasks.get(MainActivity.editableTaskPosition).id);
                taskHelper.update(MainActivity.tasks.get(MainActivity.editableTaskPosition).id,contentValues);


                Toast toast=Toast.makeText(getContext(),"Saved",Toast.LENGTH_SHORT);
                toast.show();
                Intent move=new Intent(getContext(),MainActivity.class);
                move.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(move);
            }
        });



        Button cancel=(Button)rootview.findViewById(R.id.button6);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.editable=false;
                Intent move=new Intent(getContext(),MainActivity.class);
                move.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(move);
            }
        });

        return  rootview;
    }
}
