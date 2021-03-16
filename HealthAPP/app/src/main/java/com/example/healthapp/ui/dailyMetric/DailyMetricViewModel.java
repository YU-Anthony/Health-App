package com.example.healthapp.ui.dailyMetric;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DailyMetricViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DailyMetricViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}