package com.example.taskmanager.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.taskmanager.Fragments.TaskFragment;
import com.example.taskmanager.Model.Task;
import com.example.taskmanager.Model.TaskLab;
import com.example.taskmanager.R;

import java.util.List;
import java.util.UUID;

public class TaskPagerActivity extends AppCompatActivity {

    private static final String EXTRA_TASK_ID = "task_id";
    private ViewPager mViewPager;
    private List<Task> tasks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_pager);

        UUID taskId = (UUID) getIntent().getSerializableExtra(EXTRA_TASK_ID);
        FragmentManager fragmentManager = getSupportFragmentManager();

        tasks = TaskLab.get(this).getTasks();
        mViewPager = findViewById(R.id.task_view_pager);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager){
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Task task = tasks.get(position);
                return TaskFragment.newInstance(task.getId());
            }

            @Override
            public int getCount() {
                return tasks.size();
            }
        });

        for(int i = 0; i< tasks.size(); i++){
            if(tasks.get(i).getId().equals(taskId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent(Context context, UUID taskId){

        Intent intent = new Intent(context, TaskPagerActivity.class);
        intent.putExtra(EXTRA_TASK_ID, taskId);
        return intent;
    }
}
