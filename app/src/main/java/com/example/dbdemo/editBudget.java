package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editBudget extends AppCompatActivity {

    private EditText title, desc, amount;
    private Button add;
    private DatabaseHelper databaseHelper;
    private Context context;
    private long updateDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_budget);

        context = this;
        final DatabaseHelper databaseHelper = new  DatabaseHelper(context);

        title = findViewById(R.id.editTextTitle);
        desc = findViewById(R.id.editTextDescription);
        add = findViewById(R.id.buttonAdd);
        amount = findViewById(R.id.editTextAmonut);

        final String id = getIntent().getStringExtra("id");
        final budget budgett =  databaseHelper.getSingleBudget(Integer.parseInt(id));

        title.setText(budgett.getName());
        desc.setText(budgett.getCategory());
        amount.setText(budgett.getAmount());

        System.out.println(id);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText = title.getText().toString();
                String decText = desc.getText().toString();
                String amountText = amount.getText().toString();
                updateDate = System.currentTimeMillis();

                budget budgett = new budget(Integer.parseInt(id),titleText,decText,amountText,updateDate,0);
                int state = databaseHelper.updateBudget(budgett);
                System.out.println(state);
                startActivity(new Intent(context,MainActivity.class));
            }
        });

    }
}
