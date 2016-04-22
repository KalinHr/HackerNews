package com.example.kalinhristov.get.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ITopStories {
    @GET("topstories.json?print=pretty")
    Call<List<Integer>> getTopStories();
}
