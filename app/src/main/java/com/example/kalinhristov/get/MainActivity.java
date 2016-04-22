package com.example.kalinhristov.get;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.kalinhristov.get.listeners.MyOnClickListener;
import com.example.kalinhristov.get.models.Story;

public class MainActivity extends AppCompatActivity implements MyOnClickListener {

    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        determinePaneLayout();

        MainFragment mainFragment =
                (MainFragment) getFragmentManager().findFragmentById(R.id.mainFragment);
        mainFragment.setListener(this);
    }

    private void determinePaneLayout() {
        if (findViewById(R.id.storyFragment) != null) {
            isTwoPane = true;
        }
    }

    @Override
    public void onClickListener(Story story) {
        if (isTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable("story", story);

            StoryFragment fragment = StoryFragment.newInstance(story);
            getFragmentManager().beginTransaction()
                    .replace(R.id.storyFragment, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, StoryActivity.class);
            intent.putExtra("story", story);
            startActivity(intent);
        }
    }
}