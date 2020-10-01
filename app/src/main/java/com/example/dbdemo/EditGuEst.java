package com.example.dbdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

public class EditGuEst extends AppCompatActivity {

    private EditText edittxtname,edittxtemail;
    // private TextView txtEditguest;
    private Button buttonEdit;

    private DulshaniDbHandler dbHandler;
    private Context context;
    private Long updatedDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_gu_est);

        context=this;
        dbHandler=new DulshaniDbHandler(context);

        edittxtname=findViewById(R.id.edittxtname);
        edittxtemail=findViewById(R.id.edittxtemail);
        // txtEditguest=findViewById(R.id.txtEditguest);
        buttonEdit=findViewById(R.id.buttonEdit);

        // getSupportActionBar().setTitle("Edit Guest");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //to get the value
        final String id=getIntent().getStringExtra("id");
        GuEst guEst = dbHandler.getSingleGuEst(Integer.parseInt(id));

        edittxtname.setText(guEst.getName());
        edittxtemail.setText(guEst.getEmail());
        System.out.println(id);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txtname =edittxtname.getText().toString();
                String txtemail =edittxtemail.getText().toString();

                updatedDate=System.currentTimeMillis();

                GuEst guEst = new GuEst(Integer.parseInt(id),txtname,txtemail,updatedDate,0);

                int state=dbHandler.updateSingleGuEst(guEst);
                System.out.println(state);
                showToast();
                startActivity(new Intent(context,DulshaniMain.class));



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

        toastText.setText("Update the guest details");
        toastImage.setImageResource(R.drawable.ic_person);


        Toast toast=new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuedit,menu);
        return true;
    }
    //create  toast messages for the tool bar icons
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
