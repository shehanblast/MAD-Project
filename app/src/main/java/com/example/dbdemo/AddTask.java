package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddTask extends AppCompatActivity {

    private Button add;
    private EditText title,desc;
    private DbHandler dbHandler;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        title = findViewById(R.id.editTextTital);
        desc = findViewById(R.id.editTextDescription);
        add = findViewById(R.id.buttonAdd);
        context = this;
        dbHandler = new DbHandler(context);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userTital = title.getText().toString();
                String userDece = desc.getText().toString();
                long started = System.currentTimeMillis();

                Task task = new Task(userTital,userDece,started,0);
                dbHandler.addTask(task);

                startActivity(new Intent(context,DualnMain.class));
            }
        });
    }
}
