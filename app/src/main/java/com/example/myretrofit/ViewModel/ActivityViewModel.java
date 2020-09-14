package com.example.myretrofit.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myretrofit.Model.Post;
import com.example.myretrofit.Repository.ActivityRepository;

import java.util.List;

public class ActivityViewModel extends ViewModel {
    private MutableLiveData<List<Post>> mutableLiveData;
    private ActivityRepository activityRepository;

    public void init() {
        if (!(mutableLiveData == null)) {
            Log.i("null live data", "init: ");
            return;
        } else {
            activityRepository = ActivityRepository.getInstance();
            mutableLiveData = activityRepository.getRepoMutubleData();
        }
    }


    public LiveData<List<Post>> getLiveData() {
        return mutableLiveData;
    }

}
