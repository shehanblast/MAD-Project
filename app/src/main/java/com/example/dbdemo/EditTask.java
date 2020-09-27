package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditTask extends AppCompatActivity {

    private EditText tital,des;
    private Button edit;
    private DbHandler dbHandler;
    private Context context;
    private Long updateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        context = this;
        dbHandler = new DbHandler(context);

        tital = findViewById(R.id.editTaskTextTital);
        des = findViewById(R.id.editTaskTextDescription);
        edit = findViewById(R.id.buttonEdit);

        final String id = getIntent().getStringExtra("id");
        Task task = dbHandler.getSingaleTask(Integer.parseInt(id));

        tital.setText(task.getTitle());
        des.setText(task.getDescription());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleText = tital.getText().toString();
                String desText = des.getText().toString();
                updateData = System.currentTimeMillis();


                Task task = new Task(Integer.parseInt(id),titleText,desText,updateData,0);
                int state = dbHandler.UpdateTask(task);
                startActivity(new Intent(context,DualnMain.class));
            }
        });
    }
}
