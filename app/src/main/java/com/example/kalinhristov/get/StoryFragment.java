package com.example.kalinhristov.get;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kalinhristov.get.models.Story;

public class StoryFragment extends Fragment {

    private View fRootView;
    private Story story;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        story = getArguments().getParcelable("story");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fRootView = inflater.inflate(R.layout.story_fragment, container, false);
//
//        Intent intent = getActivity().getIntent();
//        Story story = intent.getParcelableExtra("story");

        setMyText(R.id.by, story.getBy());
        setMyText(R.id.descendants, Integer.toString(story.getDescendants()));
        setMyText(R.id.id, Integer.toString(story.getId()));
        setMyText(R.id.kids, story.getKids().toString());
        setMyText(R.id.score, Integer.toString(story.getScore()));
        setMyText(R.id.time, Long.toString(story.getTime()));
        setMyText(R.id.title, story.getTitle());
        setMyText(R.id.type, story.getType());
        setMyText(R.id.url, story.getUrl());

        return fRootView;
    }

    private void setMyText(int id, String extra) {
        TextView textView = (TextView) fRootView.findViewById(id);
        if (textView != null) {
            textView.setText(extra);
        }
    }

    public static StoryFragment newInstance(Story story) {
        StoryFragment fragmentDemo = new StoryFragment();
        Bundle args = new Bundle();
        args.putParcelable("story", story);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }
}
