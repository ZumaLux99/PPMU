package com.example.phonebook_java_2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.myViewHolder> {

    private Context context;
    private ArrayList contact_id, contact_name, contact_number;
    int position;

    ContactAdapter(Context context, ArrayList contact_id, ArrayList contact_name, ArrayList contact_number){
        this.context = context;
        this.contact_id = contact_id;
        this.contact_name = contact_name;
        this.contact_number = contact_number;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_row, parent, false);
        return new myViewHolder(view);
    }

    @Override //get text from the arrays
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.contact_id_txt.setText(String.valueOf(contact_id.get(position)));
        holder.contact_name_txt.setText(String.valueOf(contact_name.get(position)));
        holder.contact_number_txt.setText(String.valueOf(contact_number.get(position)));
        holder.list_row_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("id", String.valueOf(contact_id.get(position)));
                intent.putExtra("name", String.valueOf(contact_name.get(position)));
                intent.putExtra("number", String.valueOf(contact_number.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contact_id.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView contact_id_txt, contact_name_txt, contact_number_txt;
        LinearLayout list_row_layout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            contact_id_txt = itemView.findViewById(R.id.contact_id_txt);
            contact_name_txt = itemView.findViewById(R.id.contact_name_txt);
            contact_number_txt = itemView.findViewById(R.id.contact_number_txt);
            list_row_layout = itemView.findViewById(R.id.list_row_layout);
        }
    }
}
