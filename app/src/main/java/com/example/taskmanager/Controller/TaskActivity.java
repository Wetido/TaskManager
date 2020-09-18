package com.example.taskmanager.Controller;

import androidx.fragment.app.Fragment;

import com.example.taskmanager.Fragments.SingleFragmentActivity;
import com.example.taskmanager.Fragments.TaskFragment;

public class TaskActivity extends SingleFragmentActivity {
    
    @Override
    protected Fragment createFragment() {
        return new TaskFragment();
    }
}