package com.example.taskmanager.Fragments;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintsChangedListener;
import androidx.fragment.app.Fragment;

import com.example.taskmanager.Model.Task;
import com.example.taskmanager.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.widget.CompoundButton.*;

public class TaskFragment extends Fragment {

    private Task mTask;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTask = new Task();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_task, container, false);
        mTitleField = v.findViewById(R.id.task_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTask.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDateButton = v.findViewById(R.id.task_date);


        mDateButton.setText( formatDate(mTask.getDate()));
        mDateButton.setEnabled(false);

        mSolvedCheckBox = v.findViewById(R.id.task_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mTask.setSolved(b);
            }
        });

        return v;
    }

    String formatDate(Date date){

        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String result = simpleDateFormat.format(date);

        return result;
    }
}


