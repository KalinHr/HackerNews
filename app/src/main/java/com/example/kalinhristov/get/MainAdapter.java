package com.example.kalinhristov.get;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kalinhristov.get.listeners.OnCommentClick;
import com.example.kalinhristov.get.listeners.OnViewClick;
import com.example.kalinhristov.get.models.Story;

import java.util.List;

public class MainAdapter extends ArrayAdapter<Story> {
    private OnCommentClick onCommentClickListener = null;
    private OnViewClick onViewClickListener = null;

    private class ViewHolder {
        public TextView title;
        public TextView score;
        public TextView by;
        public TextView view;
        public TextView comments;
    }

    public MainAdapter(Context context, int resource, List<Story> objects,
                       OnCommentClick commentListener,
                       OnViewClick viewListener) {
        super(context, resource, objects);

        onCommentClickListener = commentListener;
        onViewClickListener = viewListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder holder;

        if (rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.story, null);
            holder = new ViewHolder();
            holder.title = (TextView) rowView.findViewById(R.id.title);
            holder.score = (TextView) rowView.findViewById(R.id.score);
            holder.by = (TextView) rowView.findViewById(R.id.by);
            holder.view = (TextView) rowView.findViewById(R.id.view);
            holder.comments = (TextView) rowView.findViewById(R.id.comments);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        final Story story = getItem(position);
        holder.title.setText(story.getTitle());
        holder.score.setText(String.format("%d score", story.getScore()));
        holder.by.setText(Html.fromHtml("by <u>" + story.getBy() + "</u>"));

        holder.comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCommentClickListener != null) {
                    onCommentClickListener.onCommentClick(story);
                }
            }
        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onViewClickListener != null) {
                    onViewClickListener.onViewClick(story);
                }
            }
        });

        return rowView;
    }
}
