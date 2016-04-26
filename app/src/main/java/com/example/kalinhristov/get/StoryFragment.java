package com.example.kalinhristov.get;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.kalinhristov.get.models.Story;

public class StoryFragment extends Fragment {

    private View fRootView;
    private Story story;
    WebView mWebView;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        story = getArguments().getParcelable("fragment_activity");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fRootView = inflater.inflate(R.layout.story_fragment, container, false);

        mWebView = (WebView) fRootView.findViewById(R.id.story);

        String url = story.getUrl();
        if(mWebView != null){
            mWebView.loadUrl(url);
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }

//        setMyText(R.id.by, fragment_activity.getBy());
//        setMyText(R.id.descendants, Integer.toString(fragment_activity.getDescendants()));
//        setMyText(R.id.id, Integer.toString(fragment_activity.getId()));
//        setMyText(R.id.kids, fragment_activity.getKids().toString());
//        setMyText(R.id.score, Integer.toString(fragment_activity.getScore()));
//        setMyText(R.id.time, Long.toString(fragment_activity.getTime()));
//        setMyText(R.id.title, fragment_activity.getTitle());
//        setMyText(R.id.type, fragment_activity.getType());
//        setMyText(R.id.url, fragment_activity.getUrl());

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
        args.putParcelable("fragment_activity", story);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }
}
