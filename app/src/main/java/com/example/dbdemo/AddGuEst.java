package com.example.dbdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddGuEst extends AppCompatActivity {

    private EditText txtname, txtemail;
    private TextView txtAddguest;
    private Button buttonAdd;
    private DulshaniDbHandler dbHandler;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gu_est);

        txtname = findViewById(R.id.txtname);
        txtemail = findViewById(R.id.txtemail);
        //txtAddguest = findViewById(R.id.txtAddguest);
        buttonAdd = findViewById(R.id.buttonAdd);
        context = this;
        dbHandler = new DulshaniDbHandler(context);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        //getActionBar().setTitle("Add Guest");
        // getActionBar().setDisplayHomeAsUpEnabled(true);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtname.getText().toString();
                String useremail = txtemail.getText().toString();
                long started = System.currentTimeMillis();

                GuEst guEst = new GuEst(username, useremail, started, 0);
                dbHandler.addGuEst(guEst);

                showToast();
                startActivity(new Intent(context, DulshaniMain.class));

            }
        });

        //create a tool bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void showToast(){
        LayoutInflater inflater=getLayoutInflater();
        View layout=inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.toast_root));

        TextView toastText=layout.findViewById(R.id.toast_text);
        ImageView toastImage=layout.findViewById(R.id.toast_image);

        toastText.setText("Added a new guest");
        toastImage.setImageResource(R.drawable.ic_person);


        Toast toast=new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd,menu);
        return true;
    }
    //create custom toast messages for the tool bar icons
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.share) {
            Toast.makeText(getApplicationContext(), "You click share", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.about) {
            Toast.makeText(getApplicationContext(), "You click about", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.exit) {
            Toast.makeText(getApplicationContext(), "You click exit", Toast.LENGTH_SHORT).show();

        }
        return true;
    }
}
