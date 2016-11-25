package com.example.mahmo.nanoapp.Task;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahmo.nanoapp.R;

/**
 * Created by mahmo on 24/11/2016.
 */

public class Empty_task extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.empty_tasks,container,false);
        return rootview;
    }
}
