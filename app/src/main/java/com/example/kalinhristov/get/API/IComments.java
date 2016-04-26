package com.example.kalinhristov.get.API;

import com.example.kalinhristov.get.models.Comment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IComments {
    @GET("{id}.json?print=pretty")
    Call<Comment> getComment(@Path("id") String id);
}
