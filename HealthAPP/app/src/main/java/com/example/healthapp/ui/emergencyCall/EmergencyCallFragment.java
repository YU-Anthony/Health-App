package com.example.healthapp.ui.emergencyCall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthapp.R;

public class EmergencyCallFragment extends Fragment {

    private EmergencyCallViewModel emergencyCallViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        emergencyCallViewModel =
                new ViewModelProvider(this).get(EmergencyCallViewModel.class);
        View root = inflater.inflate(R.layout.fragment_emergency_call, container, false);
        final TextView textView = root.findViewById(R.id.text_emergencyCall);
        emergencyCallViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //引用 toolbar
        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.emergencyCall_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        return root;
    }
}