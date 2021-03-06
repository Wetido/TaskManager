package com.example.taskmanager.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanager.Controller.TaskActivity;
import com.example.taskmanager.Controller.TaskPagerActivity;
import com.example.taskmanager.Model.Task;
import com.example.taskmanager.Model.TaskLab;
import com.example.taskmanager.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TaskListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;
    private int mClickedItemPosition;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        mRecyclerView = view.findViewById(R.id.task_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){

        TaskLab taskLab = TaskLab.get(getActivity());
        List<Task> tasks = taskLab.getTasks();

        if(mAdapter == null){
            mAdapter = new TaskAdapter(tasks);
            mRecyclerView.setAdapter(mAdapter);
        } else {

            mAdapter.notifyDataSetChanged();
        }
    }

    private class TaskHolder extends RecyclerView.ViewHolder{
        private Task mTask;
        private TextView mTitleTextView;
        private TextView mDateTextView;

        public TaskHolder(LayoutInflater inflater, ViewGroup parent, int layoutId) {
            super(inflater.inflate(layoutId,parent, false));

            mTitleTextView = itemView.findViewById(R.id.task_title);
            mDateTextView = itemView.findViewById(R.id.task_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View View) {

                    //mClickedItemPosition = getAdapterPosition();
                    Intent intent = TaskPagerActivity.newIntent(getActivity(), mTask.getId());
                    startActivity(intent);
                }
            });
        }

        public void bind(Task task){
            mTask = task;
            mTitleTextView.setText(mTask.getTitle());
            mDateTextView.setText(formatDate(mTask.getDate()));
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> tasks;
        public TaskAdapter(List<Task>tasks){
            this.tasks = tasks;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TaskHolder(layoutInflater, parent, viewType);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task = tasks.get(position);
            holder.bind(task);
        }

        @Override
        public int getItemCount() {
            return tasks.size();
        }

        @Override
        public int getItemViewType(int position) {
            Task crime = tasks.get(position);
            if (crime.isDifficult()) {
                return R.layout.list_item_difficult_task;
            } else {
                return R.layout.list_item_task;
            }
        }
    }

    String formatDate(Date date){

        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String result = simpleDateFormat.format(date);

        return result;
    }
}
