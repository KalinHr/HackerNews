package com.example.kalinhristov.get;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.kalinhristov.get.listeners.OnCommentClick;
import com.example.kalinhristov.get.listeners.OnViewClick;
import com.example.kalinhristov.get.models.Story;

public class MainActivity extends AppCompatActivity implements OnViewClick, OnCommentClick {

    private boolean isTwoPane = false;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        determinePaneLayout();

        mainFragment = (MainFragment) getFragmentManager().findFragmentById(R.id.mainFragment);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainFragment.setCommentListener(this);
        mainFragment.setViewListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainFragment.setCommentListener(null);
        mainFragment.setViewListener(null);
    }

    private void determinePaneLayout() {
        if (findViewById(R.id.fragment) != null) {
            isTwoPane = true;
        }
    }

    @Override
    public void onViewClick(Story story) {
        if (isTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable("fragment_activity", story);

            StoryFragment fragment = StoryFragment.newInstance(story);
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, StoryActivity.class);
            intent.putExtra("fragment_activity", story);
            startActivity(intent);
        }
    }

    @Override
    public void onCommentClick(Story story) {
        if (isTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable("fragment_activity", story);

            CommentFragment fragment = CommentFragment.newInstance(story);
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, CommentActivity.class);
            intent.putExtra("fragment_activity", story);
            startActivity(intent);
        }
    }
}