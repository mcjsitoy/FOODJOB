package com.example.order;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class listadapter extends RecyclerView.Adapter<listadapter.UserViewHolder> {
    private Context mcontext;
    private ArrayList name,price,category;


    public listadapter(Context context,
        ArrayList id,
        ArrayList name,
        ArrayList price,
        ArrayList category){
        this.mcontext = context;

        this.name = name;
        this.price = price;
        this.category = category;

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
       /* holder.idtxt.setText(String.valueOf(id));*/

        holder.nametxt.setText(String.valueOf(name.get(position)));
        holder.pricetxt.setText(String.valueOf(price.get(position)));

        holder.categorytxt.setText(String.valueOf(category.get(position)));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView idtxt;
        public TextView nametxt;
        public TextView pricetxt;
        public TextView categorytxt;



        public UserViewHolder(@NonNull View itemView) {
            super(itemView);


            nametxt = itemView.findViewById(R.id.textView2);
            pricetxt = itemView.findViewById(R.id.textView3);
            categorytxt = itemView.findViewById(R.id.textView4);
        }
    }
}
