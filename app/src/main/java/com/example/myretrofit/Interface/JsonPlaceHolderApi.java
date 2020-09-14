package com.example.myretrofit.Interface;

import com.example.myretrofit.Model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("posts")
        // Post here is the end point of the Url from where we get data.
    Call<List<Post>> getPost();

}
