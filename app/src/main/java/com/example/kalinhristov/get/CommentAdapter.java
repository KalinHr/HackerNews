package com.example.kalinhristov.get;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kalinhristov.get.models.Comment;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {

    View rowView = null;

    private class ViewHolder {
        public TextView by;
        public TextView comment;
    }

    public CommentAdapter(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        rowView = convertView;
        ViewHolder holder;

        if (rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.comment, null);
            holder = new ViewHolder();
            holder.by = (TextView) rowView.findViewById(R.id.by);
            holder.comment = (TextView) rowView.findViewById(R.id.comment);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        final Comment comment = getItem(position);
        holder.by.setText(Html.fromHtml("by <u>" + comment.getBy() + "</u>"));
        holder.comment.setText(comment.getText());

        return rowView;
    }
}
