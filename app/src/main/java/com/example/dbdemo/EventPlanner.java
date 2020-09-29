package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EventPlanner extends AppCompatActivity {

    Button bttn;
    Button btt1;
    Button btt2;
    Button btt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_planner);
        bttn = findViewById(R.id.btn6);
        btt1 = findViewById(R.id.btn1);
        btt2 = findViewById(R.id.btn5);
        btt3 = findViewById(R.id.btn7);

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

        btt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventPlanner.this,DualnMain.class);
                startActivity(intent);
            }
        });
        btt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventPlanner.this,DulshaniMain.class);
                startActivity(intent);
            }
        });
    }
}
