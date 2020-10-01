package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DeshaniMain extends AppCompatActivity {

    public Toolbar toolbar;
    //FloatingActionButton button;
    private ListView listView;
    private TextView count;
    Context context;


    private FloatingActionButton floatingActionButton;
    private DeshaniDbHandler dbHandler;
    private List<Vendor> vendors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Vendors");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);


        floatingActionButton = findViewById(R.id.floatingActionButton5);
        // floatingActionButton.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //public void onClick(View view) {
        //Intent intent= new Intent(MainActivity.this,  AddVendor.class);
        // startActivity(intent);
        //  }
        // });
        listView = findViewById(R.id.vendorList);
        count = findViewById(R.id.vendorcount);
        context = this;
        dbHandler = new DeshaniDbHandler(context);
        vendors = new ArrayList<>();

        vendors = dbHandler.getAllVendors();
        VendorAdapter adapter = new VendorAdapter(context, R.layout.single_vendor,vendors);
        listView.setAdapter(adapter);

        //get vendor counts from the table
        int countVendor = dbHandler.countVendor();
        count.setText("You have " + countVendor + " vendors");

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, AddVendor.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final Vendor vendor=vendors.get(i);
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle(vendor.getName());
                builder.setMessage(vendor.getEstimated_amount());


                //create the buttons in alert dialog box
                builder.setPositiveButton("Paid", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.updateSingleVendor(vendor);
                        startActivity(new Intent(context,MainActivity.class));
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteVendor(vendor.getId());
                        startActivity(new Intent(context,MainActivity.class));

                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent=new Intent(context,EditVendor.class);
                        intent.putExtra("id",String.valueOf(vendor.getId()));
                        startActivity(intent);

                    }
                });
                builder.show();


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menud,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg="";
        switch (item.getItemId()){
            case R.id.search:
                msg="Search";
                break;
            case R.id.summary:
                msg="Summary";
                break;
            case R.id.report:
                msg="Report";
                break;

        }
        Toast.makeText(this, msg+ " checked", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
