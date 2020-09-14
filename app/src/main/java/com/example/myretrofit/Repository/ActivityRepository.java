package com.example.myretrofit.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myretrofit.Interface.JsonPlaceHolderApi;
import com.example.myretrofit.Model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityRepository {

    private static ActivityRepository instance;
    private List<Post> posts = new ArrayList<>();


    //Singleton pattern for get object
    public static ActivityRepository getInstance() {
        if (instance == null) {
            instance = new ActivityRepository();
        }
        return instance;
    }
    MutableLiveData<List<Post>> data;
    public MutableLiveData<List<Post>> getRepoMutubleData() {
        setData();
       data = new MutableLiveData<>();
       return data;
    }

    public void setData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonApiHolder = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonApiHolder.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Log.d("TAG", "onResponse: ");
                    return;
                }

                posts.addAll(response.body());
                data.setValue(posts);

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable throwable) {
                Log.i("TAG", "onFailure: ");
            }
        });

    }

}

