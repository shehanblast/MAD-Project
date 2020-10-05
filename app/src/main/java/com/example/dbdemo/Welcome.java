package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    EditText ed;
    Button btn11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ed = findViewById(R.id.eventName);
        btn11 = findViewById(R.id.btn22);
    }

    public void click(View view){
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ed.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Event name cannot be Null", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(Welcome.this,EventPlanner.class);
                intent.putExtra("n1",ed.getText().toString());
                startActivity(intent);
            }
        });
    }


}
