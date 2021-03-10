package com.example.healthapp.ui.emergencyCall;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EmergencyCallViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EmergencyCallViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}