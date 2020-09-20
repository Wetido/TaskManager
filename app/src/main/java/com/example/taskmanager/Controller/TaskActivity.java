package com.example.taskmanager.Controller;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.taskmanager.Fragments.SingleFragmentActivity;
import com.example.taskmanager.Fragments.TaskFragment;

import java.util.UUID;

public class TaskActivity extends SingleFragmentActivity {
    private static final String EXTRA_TASK_ID = "task_id";

    @Override
    protected Fragment createFragment() {

        UUID taskId = (UUID) getIntent().getSerializableExtra(EXTRA_TASK_ID);
        return TaskFragment.newInstance(taskId);
    }

    public static Intent newIntent(Context packageContext, UUID taskID){

        Intent intent = new Intent(packageContext, TaskActivity.class);
        intent.putExtra(EXTRA_TASK_ID, taskID);
        return intent;
    }
}