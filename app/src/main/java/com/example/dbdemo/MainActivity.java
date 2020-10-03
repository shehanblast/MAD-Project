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

public class MainActivity extends AppCompatActivity {

    vhgvh
    private int add;
    private TextView count;
    private DatabaseHelper databaseHelper;
    Context context;
    private List<budget> budgets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        databaseHelper = new DatabaseHelper(context);
        add = findViewById(R.id.add);
        listView = findViewById(R.id.todoList);
        count = findViewById(R.id.todocount);
        budgets = new ArrayList<>();

        budgets = databaseHelper.getAllBudgets();aaaa

        BudgetAdapter budgetAdapter = new BudgetAdapter(context,R.layout.single_budget,budgets);
        listView.setAdapter(budgetAdapter);
        int countBudget = databaseHelper.countBudget();
        count.setText("You have "+countBudget+" budgets");

        listView.setOnItemClickListener(new AdapzterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final budget budgett = budgets.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(budgett.getName());
                builder.setMessage(budgett.getCategory());
                //builder.setMessage(budgett.getAmount());


                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        budgett.setFinished(System.currentTimeMillis());
                        databaseHelper.updateBudget(budgett);
                        startActivity(new Intent(context,MainActivity.class));
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHelper.deleteBudget(budgett.getId());
                        startActivity(new Intent(context,MainActivity.class));
                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context,editBudget.class);
                        intent.putExtra("id",String.valueOf(budgett.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });


    }

    public void onResume() {

        super.onResume();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,addBudget.class);
                startActivity(intent);
            }
        });
    }




}
