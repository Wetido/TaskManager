package com.example.taskmanager.Model;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//SINGLETON IMPLEMENTATION WILL BE REPLACED WITH DATABASE LATER
public class TaskLab {

    private static TaskLab sTaskLab;
    private List<Task> tasks;
    public static TaskLab get(Context context){
        if(sTaskLab == null){
            sTaskLab = new TaskLab(context);

        }
        return sTaskLab;
    }

    private TaskLab(Context context){
        tasks = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            Task task = new Task();
            task.setTitle("Sprawa nr " + i);
            task.setSolved(i % 2 == 0);
            tasks.add(task);
        }
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public Task getTask(UUID id){
        for(Task task : tasks){
            if(task.getId().equals(id)){
                return task;
            }
        }
        return null;
    }

}
