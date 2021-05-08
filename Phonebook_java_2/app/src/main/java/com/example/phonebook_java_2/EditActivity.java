package com.example.phonebook_java_2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText edit_name_input, edit_number_input;
    Button edit_btn, cancel_edit_btn, delete_edit_btn;
    String id, name, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edit_name_input = findViewById(R.id.edit_name);
        edit_number_input = findViewById(R.id.edit_number);
        edit_btn = findViewById(R.id.edit_btn);
        cancel_edit_btn = findViewById(R.id.cancel_btn_edit);
        delete_edit_btn = findViewById(R.id.delete_btn);


        //First we call this
        getAndSetIntentData();

        //Action Bar | set after detAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }
        edit_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Then we call this
                DBHelper dbHelper = new DBHelper(EditActivity.this);
                name = edit_name_input.getText().toString().trim();
                number = edit_number_input.getText().toString().trim();
                dbHelper.updateData(id, name, number);
            }
        });

        delete_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DeleteDialog();
            }
        });



        cancel_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    void getAndSetIntentData(){ //Passed from main activity
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("number")){
            //Getting data from intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            number = getIntent().getStringExtra("number");
            //Setting Intent data
            edit_name_input.setText(name);
            edit_number_input.setText(number);
        }
        else {
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        }
    }

    void DeleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper dbHelper = new DBHelper(EditActivity.this);
                dbHelper.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}