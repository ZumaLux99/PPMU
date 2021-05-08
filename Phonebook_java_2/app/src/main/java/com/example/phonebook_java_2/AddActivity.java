package com.example.phonebook_java_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText add_name, add_number;
    Button add_btn, cancel_btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        add_btn = findViewById(R.id.add_btn);
        cancel_btn_add = findViewById(R.id.cancel_btn_add);
        add_name = findViewById(R.id.add_name);
        add_number = findViewById(R.id.add_number);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(AddActivity.this);
                dbHelper.addContact(add_name.getText().toString().trim(), add_number.getText().toString().trim());
            }
        });

        cancel_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}