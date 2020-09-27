package com.example.dbdemo;

import android.widget.ArrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private Context context;
    private int resource;
    List<Task> tasks;

    TaskAdapter(Context context, int resource, List<Task> tasks){
        super(context,resource,tasks);
        this.context = context;
        this.resource = resource;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView title = row.findViewById(R.id.title);
        TextView description = row.findViewById(R.id.description);
        ImageView imageView = row.findViewById(R.id.onGoing);

        Task task = tasks.get(position);
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        imageView.setVisibility(row.INVISIBLE);

        if(task.getFinished() > 0){
            imageView.setVisibility(View.VISIBLE);
        }
        return row;
    }
}
