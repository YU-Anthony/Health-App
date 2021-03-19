package com.example.healthapp.ui.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.View.OnClickListener;


import com.example.healthapp.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExerciseFragment extends Fragment{

    private ExerciseViewModel exerciseViewModel;
//    private Button btn1;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        exerciseViewModel =
                new ViewModelProvider(this).get(ExerciseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_exercise, container, false);


        //引用 toolbar
        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.exercise_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

//        // 这里不适合用 getActivity()
//        btn1 = root.findViewById(R.id.get_sitPosture);
//        btn1.setOnClickListener(this);

        return root;

    }

//    //方法：按钮的单击事件
//    @Override
//    public void onClick(View v) {
//
//        switch (v.getId()) {
//            case R.id.get_sitPosture:
//                startActivity(new Intent("getSittingPostureActivity"));
//                break;
//            default:
//                break;
//        }
//    }
}