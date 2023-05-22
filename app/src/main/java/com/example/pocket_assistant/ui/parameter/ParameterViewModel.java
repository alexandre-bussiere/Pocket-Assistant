package com.example.pocket_assistant.ui.parameter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ParameterViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ParameterViewModel() {
        mText = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
}