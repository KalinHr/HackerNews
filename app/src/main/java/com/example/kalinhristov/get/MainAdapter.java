package com.example.kalinhristov.get;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kalinhristov.get.models.Story;

import java.util.List;

public class MainAdapter extends ArrayAdapter<Story> {
    private class ViewHolder {
        public TextView title;
        public TextView score;
        public TextView by;
    }

    public MainAdapter(Context context, int resource, List<Story> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder holder;

        if (rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.story_title, null);
            holder = new ViewHolder();
            holder.title = (TextView) rowView.findViewById(R.id.title);
            holder.score = (TextView) rowView.findViewById(R.id.score);
            holder.by = (TextView) rowView.findViewById(R.id.by);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        Story story = getItem(position);
        holder.title.setText(story.getTitle());
        holder.score.setText(String.format("%d score", story.getScore()));
        holder.by.setText(Html.fromHtml("by <u>" + story.getBy() + "</u>"));
        return rowView;
    }
}
