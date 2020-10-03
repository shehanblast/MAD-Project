package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addBudget extends AppCompatActivity {

    private EditText title, desc, amount;
    private Button add;
    private DatabaseHelper databaseHelper;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);

        title = findViewById(R.id.editTextTitle);
        desc = findViewById(R.id.editTextDescription);
        amount = findViewById(R.id.editTextAmonut);
        add = findViewById(R.id.buttonAdd);

        context = this;

        databaseHelper =  new DatabaseHelper(context);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(title.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Budget name cannot be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(amount.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Amount name cannot be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                String userTitle = title.getText().toString();
                String userdesc = desc.getText().toString();
                String userAmount = amount.getText().toString();
                long started = System.currentTimeMillis();

                budget budget = new budget(userTitle,userdesc,userAmount,started,0);
                databaseHelper.addBudget(budget);

                startActivity(new Intent(context,MainActivity.class));
            }
        });


    }

}
