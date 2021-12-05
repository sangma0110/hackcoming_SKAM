package com.example.hackcoming;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class main extends AppCompatActivity {
    // Write a message to the database
    private FirebaseDatabase database;
    private DatabaseReference ref;

    private String name;
    private String s1, s2, s3;
    private TextView userName;
    private TextView resAct, resLoc, resDate;
    private Button ski, skat, fish;
    private String btn1, btn2, btn3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        userName = findViewById(R.id.userName);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("UserAccount").child("almDuiPqALfkQk0MdCg6ecek6nk1");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    if(ds.getKey().equals("name")) name = ds.getValue(String.class);
                }
                userName.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(main.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });

        ski = findViewById(R.id.skiBtn);
        skat = findViewById(R.id.skatBtn);
        fish = findViewById(R.id.fishBtn);
        ref = database.getReference().child("Activity");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    if(ds.getKey().equals("Skiing, Snowboarding")) btn1 = ds.getKey();
                    if(ds.getKey().equals("Skating")) btn2 = ds.getKey();
                    if(ds.getKey().equals("Fishing")) btn3 = ds.getKey();
                }
                ski.setText(btn1);
                ski.setTextColor(Color.WHITE);
                skat.setText(btn2);
                skat.setTextColor(Color.WHITE);
                fish.setText(btn3);
                fish.setTextColor(Color.WHITE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(main.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        }
        );

        resAct = findViewById(R.id.resAct);
        resLoc = findViewById(R.id.resLoc);
        resDate = findViewById(R.id.resDate);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("UserAccount").child("almDuiPqALfkQk0MdCg6ecek6nk1");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    if(ds.getKey().equals("resActivity")) s1 = ds.getValue(String.class);
                    if(ds.getKey().equals("resLocation")) s2 = ds.getValue(String.class);
                    if(ds.getKey().equals("resDate")) s3 = ds.getValue(String.class);
                }
                resAct.setText(s1);
                resLoc.setText(s2);
                resDate.setText(s3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(main.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });

        ski.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                ref = database.getReference().child("UserAccount").child("almDuiPqALfkQk0MdCg6ecek6nk1");
                ref.child("resActivity").setValue(ski.getText());

                Intent intent = new Intent(getApplicationContext(),location.class);
                startActivity(intent);
                finish();
            }
        });

        skat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                ref = database.getReference().child("UserAccount").child("almDuiPqALfkQk0MdCg6ecek6nk1");
                ref.child("resActivity").setValue(skat.getText());

                Intent intent = new Intent(getApplicationContext(),location.class);
                startActivity(intent);
                finish();
            }
        });

        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                ref = database.getReference().child("UserAccount").child("almDuiPqALfkQk0MdCg6ecek6nk1");
                ref.child("resActivity").setValue(fish.getText());

                Intent intent = new Intent(getApplicationContext(),location.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
