package com.example.dbdemo;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GuEstAdapter extends ArrayAdapter<GuEst> {

    private Context context;
    private int resource;
    List<GuEst> guEsts;



    GuEstAdapter(Context context, int resource, List<GuEst> guEsts) {
        super(context,resource,guEsts);
        this.context=context;
        this.resource=resource;
        this.guEsts=guEsts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View row =inflater.inflate(resource,parent,false);

        TextView name=row.findViewById(R.id.name);
        TextView email=row.findViewById(R.id.email);
        ImageView onGoing =row.findViewById(R.id.onGoing);

        //guEsts [obj1,obj2,obj3]
        GuEst guEst=guEsts.get(position);
        name.setText(guEst.getName());
        email.setText(guEst.getEmail());
        onGoing.setVisibility(row.INVISIBLE);

        if(guEst.getFinished() > 0){
            onGoing.setVisibility(View.VISIBLE);
        }
        return row;

        //return super.getView(position, convertView, parent);
    }
}
