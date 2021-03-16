package com.example.healthapp.ui.dailyMetric;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DailyMetricFragment extends Fragment implements View.OnClickListener {

    private DailyMetricViewModel dailyMetricViewModel;
    private String locationTime;
    private Button btn1;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        dailyMetricViewModel =
                new ViewModelProvider(this).get(DailyMetricViewModel.class);
        View root = inflater.inflate(R.layout.fragment_daily_metric, container, false);

        //引用 toolbar
        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.dailyMetric_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        // 这里不适合用 getActivity()
        iv1 = root.findViewById(R.id.sitPosture);
        iv2 = root.findViewById(R.id.sitTime);
        iv3 = root.findViewById(R.id.gait);
        iv4 = root.findViewById(R.id.exercise);

        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);


        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        locationTime = getDateTime();
        Log.d("time",locationTime);
        TextView tv =getActivity().findViewById(R.id.HM);

        tv.append(locationTime);

    }

    private String getDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }


    //方法：按钮的单击事件
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sitPosture:
                startActivity(new Intent("getSittingPostureActivity"));
                break;
            case R.id.sitTime:
                startActivity(new Intent("getSittingTimeActivity"));
                break;
            case R.id.gait:
                startActivity(new Intent("getGaitAnalysisActivity"));
                break;
            case R.id.exercise:
                startActivity(new Intent("getExerciseRecordActivity"));
                break;
            default:
                break;
        }
    }
}