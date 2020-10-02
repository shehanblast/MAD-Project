package com.example.dbdemo;

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

public class VendorAdapter extends ArrayAdapter<Vendor> {


    private Context context;
    private int resource;
    List<Vendor> vendors;

    VendorAdapter(Context context, int resource, List<Vendor> vendors) {
        super(context, resource, vendors);
        this.context = context;
        this.resource = resource;
        this.vendors = vendors;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource, parent, false);

        TextView name = row.findViewById(R.id.textView25);
        TextView estimated_amount = row.findViewById(R.id.textView23);
        TextView p_amount = row.findViewById(R.id.txtpaid);
        ImageView onGoing = row.findViewById(R.id.imageView4);

        //vendors [obj1,obj2,obj3]
        Vendor vendor = vendors.get(position);
        name.setText(vendor.getName());
        estimated_amount.setText(vendor.getEstimated_amount());
        p_amount.setText(vendor.getP_amount());
        onGoing.setVisibility(row.INVISIBLE);

        if(vendor.getFinished() > 0){
            onGoing.setVisibility(View.VISIBLE);
        }
        return row;

        //return super.getView(position, convertView, parent);
        //}
    }
}

