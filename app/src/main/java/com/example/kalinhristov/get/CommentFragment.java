package com.example.kalinhristov.get;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kalinhristov.get.API.IComments;
import com.example.kalinhristov.get.models.Comment;
import com.example.kalinhristov.get.models.Story;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CommentFragment extends Fragment {
    public static final String STORY_URL = "https://hacker-news.firebaseio.com/v0/item/";
    private View fRootView;
    private Story story;
    public int counter = 0;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        story = getArguments().getParcelable("fragment_activity");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final List<Comment> comments = new ArrayList<>();
        final Gson gson = new GsonBuilder().create();
        fRootView = inflater.inflate(R.layout.comment_fragment, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(STORY_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        IComments iComments = retrofit.create(IComments.class);

        for (int i = 0; i < story.getKids().size(); i++) {
            Call<Comment> call = iComments.getComments(story.getKids().get(i).toString());
            call.enqueue(new Callback<Comment>() {
                @Override
                public void onResponse(Call<Comment> call, Response<Comment> response) {
                    comments.add(response.body());
                    counter++;

                    if (counter == story.getKids().size()) {
                        printComments(comments);
                    }
                }

                @Override
                public void onFailure(Call<Comment> call, Throwable t) {
                    counter++;
                }
            });
        }

        return fRootView;
    }

    private void printComments(List<Comment> comments) {
        final ListView listView = (ListView) fRootView.findViewById(R.id.listView);

        if (listView != null) {
            listView.setAdapter(new CommentAdapter
                    (fRootView.getContext(),
                            android.R.layout.simple_list_item_1,
                            comments));
        }
    }

    public static CommentFragment newInstance(Story story) {
        CommentFragment fragmentDemo = new CommentFragment();
        Bundle args = new Bundle();
        args.putParcelable("fragment_activity", story);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }
}
