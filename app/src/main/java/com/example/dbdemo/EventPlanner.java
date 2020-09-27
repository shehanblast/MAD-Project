package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EventPlanner extends AppCompatActivity {

    Button bttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_planner);
        bttn = findViewById(R.id.btn6);
    }

    public void onResume() {

        super.onResume();
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventPlanner.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
