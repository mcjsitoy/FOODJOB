package com.example.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class menu extends AppCompatActivity {

    private Button Burger, Beverage, Combomeal;
     private DatabaseHelper mDBHelper;
     private SQLiteDatabase mDb;
    ArrayList<String> id,name, price, category;
        RecyclerView recyclerView;
        listadapter listadapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_menu);
        super.onCreate(savedInstanceState);
        Burger = (Button)findViewById(R.id.burger);
        Beverage = (Button)findViewById(R.id.button2);
        Combomeal = (Button)findViewById(R.id.button3);
        recyclerView = (RecyclerView)findViewById(R.id.foodlist);











       

        Burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDBHelper = new DatabaseHelper(getApplicationContext());
                id = new ArrayList<>();
                name = new ArrayList<>();
                price = new ArrayList<>();
                category = new ArrayList<>();
                showburger();

                listadapt = new listadapter(menu.this, id, name, price, category);
                recyclerView.setAdapter(listadapt);
                recyclerView.setLayoutManager(new LinearLayoutManager(menu.this));

                Toast.makeText(menu.this, "Scroll down for more items",Toast.LENGTH_SHORT).show();




            }
        });

        Beverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDBHelper = new DatabaseHelper(getApplicationContext());
                id = new ArrayList<>();
                name = new ArrayList<>();
                price = new ArrayList<>();
                category = new ArrayList<>();
                showbeverage();

                listadapt = new listadapter(menu.this, id, name, price, category);
                recyclerView.setAdapter(listadapt);
                recyclerView.setLayoutManager(new LinearLayoutManager(menu.this));
                Toast.makeText(menu.this, "scroll down for more items",Toast.LENGTH_SHORT).show();

            }
        });

        Combomeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDBHelper = new DatabaseHelper(getApplicationContext());
                id = new ArrayList<>();
                name = new ArrayList<>();
                price = new ArrayList<>();
                category = new ArrayList<>();
                showcombo();

                listadapt = new listadapter(menu.this, id, name, price, category);
                recyclerView.setAdapter(listadapt);
                recyclerView.setLayoutManager(new LinearLayoutManager(menu.this));
                Toast.makeText(menu.this, "scroll down for more items",Toast.LENGTH_SHORT).show();
            }
        });







        /*try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }*/
    }

    public void showburger(){
       Cursor cursor = mDBHelper.fetchburger();
       if(cursor.getCount() == 0){
           Toast.makeText(this,"NO DATA", Toast.LENGTH_SHORT).show();
       }else{
           while (cursor.moveToNext()){
               id.add(cursor.getString(0));
               name.add(cursor.getString(1));
               price.add(cursor.getString(2));
               category.add(cursor.getString(3));
           }
       }

    }

    public void showbeverage(){
        Cursor cursor = mDBHelper.fetchbeverage();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"NO DATA", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                price.add(cursor.getString(2));
                category.add(cursor.getString(3));
            }
        }

    }
    public void showcombo(){
        Cursor cursor = mDBHelper.fetchcombo();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"NO DATA", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                price.add(cursor.getString(2));
                category.add(cursor.getString(3));
            }
        }

    }
}