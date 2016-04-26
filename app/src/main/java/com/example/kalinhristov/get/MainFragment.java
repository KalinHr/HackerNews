package com.example.kalinhristov.get;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kalinhristov.get.API.IStory;
import com.example.kalinhristov.get.API.ITopStories;
import com.example.kalinhristov.get.listeners.OnCommentClick;
import com.example.kalinhristov.get.listeners.OnViewClick;
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

public class MainFragment extends Fragment {
    public static final String STORY_URL = "https://hacker-news.firebaseio.com/v0/item/";
    public static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/";
    public int counter = 0;
    private View fRootView;
    private OnViewClick onViewClickListener;
    private OnCommentClick onCommentClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fRootView = inflater.inflate(R.layout.main_fragment, container, false);

        final List<Integer> storyIds = new ArrayList<>();

        final Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TOP_STORIES_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ITopStories iTopStories = retrofit.create(ITopStories.class);

        Call<List<Integer>> callTopStory = iTopStories.getTopStories();
        callTopStory.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> callTopStory, Response<List<Integer>> response) {
                storyIds.addAll(response.body());

                getListOfStoriesFromId(storyIds, gson);
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
            }
        });

        return fRootView;
    }

    private void getListOfStoriesFromId (final List<Integer> storyIds, Gson gson) {
        final List<Story> stories = new ArrayList<>();

        Retrofit retrofitStory = new Retrofit.Builder()
                .baseUrl(STORY_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        IStory iStory = retrofitStory.create(IStory.class);

        final List<Integer> sampleStories = storyIds.subList(0, 20);

        for (int i = 0; i < sampleStories.size(); i++) {
            Call<Story> call = iStory.getStory(sampleStories.get(i).toString());
            call.enqueue(new Callback<Story>() {
                @Override
                public void onResponse(Call<Story> call, Response<Story> response) {
                    stories.add(response.body());
                    counter++;

                    if (counter == sampleStories.size()) {
                        printClickableListOfStories(stories);
                    }
                }

                @Override
                public void onFailure(Call<Story> call, Throwable t) {
                    counter++;
                }
            });
        }
    }

    private void printClickableListOfStories(List<Story> stories) {
        final ListView listView = (ListView) fRootView.findViewById(R.id.listView);


        if (listView != null) {
            listView.setAdapter(new MainAdapter
                    (fRootView.getContext(),
                            android.R.layout.simple_list_item_1,
                            stories,
                            new OnCommentClick() {
                                @Override
                                public void onCommentClick(Story story) {
                                    onCommentClickListener.onCommentClick(story);
                                }
                            },
                            new OnViewClick() {
                                @Override
                                public void onViewClick(Story story) {
                                    onViewClickListener.onViewClick(story);
                                }
                            }));
        }
    }

    public void setCommentListener(OnCommentClick listener) {
        this.onCommentClickListener = listener;
    }

    public void setViewListener(OnViewClick listener) {
        this.onViewClickListener = listener;
    }
}
