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

public class BudgetAdapter extends ArrayAdapter<budget> {

    private Context context;
    private int resource;
    List<budget> budgets;

    BudgetAdapter(Context context, int resource, List<budget> budgets){
        super(context,resource,budgets);
        this.budgets = budgets;
        this.context = context;
        this.resource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View row =inflater.inflate(resource,parent,false);

        TextView title =row.findViewById(R.id.title);
        TextView description =row.findViewById(R.id.description);
        ImageView imageView =row.findViewById(R.id.onGoing);

        //guEsts [obj1,obj2,obj3]
        budget budget=budgets.get(position);
        title.setText(budget.getName());
        description.setText(budget.getCategory());
        imageView.setVisibility(row.INVISIBLE);

        if(budget.getFinished() > 0){
            imageView.setVisibility(View.VISIBLE);
        }
        return row;
        //return super.getView(position, convertView, parent);
    }
}
