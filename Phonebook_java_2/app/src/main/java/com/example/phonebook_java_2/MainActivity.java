package com.example.phonebook_java_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton new_button;

    DBHelper dbHelper;
    ArrayList<String> contact_id, contact_name, contact_number;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rec_view);
        new_button = findViewById(R.id.new_btn);
        new_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new DBHelper(MainActivity.this);
        contact_id = new ArrayList<>();
        contact_name = new ArrayList<>();
        contact_number = new ArrayList<>();

        storeDataInArrays();

        contactAdapter = new ContactAdapter(MainActivity.this, contact_id, contact_name, contact_number);
        Collections.sort(contact_name);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void storeDataInArrays(){
        Cursor cursor = dbHelper.getAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){ //read all data from the cursor
                contact_id.add(cursor.getString(0));
                contact_name.add(cursor.getString(1));
                contact_number.add(cursor.getString(2));
            }
        }
    }
}