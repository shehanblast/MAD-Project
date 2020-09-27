package com.example.dbdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DualnMain extends AppCompatActivity {

    private Button add;
    private ListView listView;
    private TextView count;
    Context context;
    private DbHandler dbHandler;
    private List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dualn_main);

        dbHandler = new DbHandler(this);
        add = findViewById(R.id.add);
        listView = findViewById(R.id.tasklist);
        count = findViewById(R.id.taskcount);
        context = this;
        tasks = new ArrayList<>();

        tasks = dbHandler.getAllTask();

        TaskAdapter adapter = new TaskAdapter(context,R.layout.single_task,tasks);
        listView.setAdapter(adapter);

        int  countTask = dbHandler.countTask();
        count.setText("You have "+countTask+" tasks");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,AddTask.class));
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                final Task task = tasks.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(task.getTitle());
                builder.setMessage(task.getDescription());

                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        task.setFinished(System.currentTimeMillis());
                        dbHandler.UpdateTask(task);
                        startActivity(new Intent(context,MainActivity.class));
                    }
                });

                builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //startActivity(new Intent(context,EditTask.class));
                        Intent intent = new Intent(context,EditTask.class);
                        intent.putExtra("id",String.valueOf(task.getId()));
                        startActivity(intent);
                    }
                });

                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteTask(task.getId());
                        startActivity(new Intent(context,MainActivity.class));
                    }
                });

                builder.show();
            }
        });
    }
}
