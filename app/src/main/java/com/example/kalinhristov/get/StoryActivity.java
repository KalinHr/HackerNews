package com.example.kalinhristov.get;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.kalinhristov.get.models.Story;

public class StoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);

        Intent intent = getIntent();
        Story story = intent.getParcelableExtra("fragment_activity");

            StoryFragment storyFragment = StoryFragment.newInstance(story);
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment, storyFragment)
                    .commit();
    }
}
