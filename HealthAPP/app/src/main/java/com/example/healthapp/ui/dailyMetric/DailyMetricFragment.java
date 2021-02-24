package com.example.healthapp.ui.dailyMetric;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;

public class DailyMetricFragment extends Fragment {

    private DailyMetricViewModel dailyMetricViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dailyMetricViewModel =
                new ViewModelProvider(this).get(DailyMetricViewModel.class);
        View root = inflater.inflate(R.layout.fragment_daily_metric, container, false);

        //引用 toolbar
        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.dailyMetric_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        return root;
    }
}