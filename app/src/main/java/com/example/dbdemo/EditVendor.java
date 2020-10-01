package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditVendor extends AppCompatActivity {

    public Toolbar toolbar;
    private EditText name, category, note, estimated_amount, number, email, address, p_amount, p_date;
    public FloatingActionButton floatingActionButton;
    private  DeshaniDbHandler dbHandler;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vendor);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Vendor");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        context = this;
        dbHandler = new DeshaniDbHandler(context);


        name = findViewById(R.id.editTextTextPersonName3);
        category = findViewById(R.id.editTextTextPersonName2);
        note = findViewById(R.id.editTextTextPersonName);
        estimated_amount = findViewById(R.id.editTextTextPersonName6);
        number = findViewById(R.id.editTextTextPersonName4);
        email = findViewById(R.id.editTextTextEmailAddress);
        address = findViewById(R.id.editTextTextPersonName7);
        p_amount = findViewById(R.id.editTextTextPersonName8);
        p_date = findViewById(R.id.editTextTextPersonName9);
        floatingActionButton = findViewById(R.id.button2);


        final String id = getIntent().getStringExtra("id");
        Vendor vendor = dbHandler.getSingleVendor(Integer.parseInt(id));

        name.setText(vendor.getName());
        category.setText(vendor.getCategory());
        note.setText(vendor.getNote());
        estimated_amount.setText(vendor.getEstimated_amount());
        number.setText(vendor.getNumber());
        email.setText(vendor.getEmail());
        address.setText(vendor.getAddress());
        p_amount.setText(vendor.getP_amount());
        p_date.setText(vendor.getP_date());

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();
                String categorytxt = category.getText().toString();
                String notetxt = note.getText().toString();
                String estimated_amounttxt = estimated_amount.getText().toString();
                String numbertxt = number.getText().toString();
                String emailtxt = email.getText().toString();
                String addresstxt = address.getText().toString();
                String p_amounttxt = p_amount.getText().toString();
                String p_datetxt = p_date.getText().toString();

                Vendor vendor = new Vendor(Integer.parseInt(id), nametxt, categorytxt, notetxt, estimated_amounttxt, numbertxt, emailtxt, addresstxt, p_amounttxt, p_datetxt);
                int state = dbHandler.updateSingleVendor(vendor);
                System.out.println(state);
                startActivity(new Intent(context, DeshaniMain.class));

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.check1){
            Intent intent = new Intent(EditVendor.this, BackToMain.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
