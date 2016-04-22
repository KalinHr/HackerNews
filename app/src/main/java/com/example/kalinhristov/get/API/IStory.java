package com.example.kalinhristov.get.API;

import com.example.kalinhristov.get.models.Story;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IStory {
    @GET("{id}.json?print=pretty")
    Call<Story> getStory(@Path("id") String id);
}
