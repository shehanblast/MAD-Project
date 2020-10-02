package com.example.dbdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

public class DulshaniMain extends AppCompatActivity {

    private Button add;
    private ListView guestList;
    private TextView guestcount;
    Context context;

    private DulshaniDbHandler dbHandler;
    private List<GuEst> guEsts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dulshani_main);
        context=this;
        dbHandler =new DulshaniDbHandler(context);

        add=findViewById(R.id.btn22);
        guestList=findViewById(R.id.guestList);
        guestcount=findViewById(R.id.guestcount);
        //context=this;
        guEsts=new ArrayList<>();

        guEsts=dbHandler.getAllGuEsts();

        GuEstAdapter adapter=new GuEstAdapter(context,R.layout.single_guest,guEsts);
        guestList.setAdapter(adapter);
        //get guest counts from the table
        int countGuEst=dbHandler.countGuEst();
        guestcount.setText("You have "+countGuEst+" guests");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast();
                startActivity(new Intent(context,AddGuEst.class));

            }
        });
        guestList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final GuEst guEst=guEsts.get(i);
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle(guEst.getName());
                builder.setMessage(guEst.getEmail());
                //builder.show();

                //create the buttons in alert dialog box
                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        guEst.setFinished(System.currentTimeMillis());
                        dbHandler.updateSingleGuEst(guEst);
                        startActivity(new Intent(context,DulshaniMain.class));
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteGuEst(guEst.getId());
                        showToastDelete();
                        startActivity(new Intent(context,DulshaniMain.class));

                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // startActivity(new Intent(context,EditGuEst.class));
                        Intent intent=new Intent(context,EditGuEst.class);
                        intent.putExtra("id",String.valueOf(guEst.getId()));
                        startActivity(intent);

                    }
                });
                builder.show();


            }
        });

        //create a tool bar
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void  showToastDelete(){
        LayoutInflater inflater=getLayoutInflater();
        View layout=inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.toast_root));

        TextView toastText=layout.findViewById(R.id.toast_text);
        ImageView toastImage=layout.findViewById(R.id.toast_image);

        toastText.setText("removed the guest");
        toastImage.setImageResource(R.drawable.ic_person);


        Toast toast=new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }


    public void showToast(){
        LayoutInflater inflater=getLayoutInflater();
        View layout=inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.toast_root));

        TextView toastText=layout.findViewById(R.id.toast_text);
        ImageView toastImage=layout.findViewById(R.id.toast_image);

        toastText.setText("Adding a new  guest");
        toastImage.setImageResource(R.drawable.ic_person);


        Toast toast=new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
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

        } else if (id == R.id.search) {
            Toast.makeText(getApplicationContext(), "You click search", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.Setting) {
            Toast.makeText(getApplicationContext(), "You click Setting", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
