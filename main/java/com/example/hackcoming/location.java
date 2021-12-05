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

public class location extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private TextView userLoc;
    private TextView resacti;
    private Button loc1, loc2, loc3, loc4, loc5;
    private String s1, s2, s3, s4, s5;
    private String location;
    private String resac;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        userLoc = findViewById(R.id.locationText);
        loc1 = findViewById(R.id.loc1);
        loc2 = findViewById(R.id.loc2);
        loc3 = findViewById(R.id.loc3);
        loc4 = findViewById(R.id.loc4);
        loc5 = findViewById(R.id.loc5);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("UserAccount").child("almDuiPqALfkQk0MdCg6ecek6nk1");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    if(ds.getKey().equals("location")) location = ds.getValue(String.class);
                    if(ds.getKey().equals("resActivity")) resac = ds.getValue(String.class);
                }
                userLoc.setText(location);

                database.getReference().child("Activity").child(resac).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds: snapshot.getChildren()){
                            //fishing
                            if(ds.getKey().equals("Advanced Bassin' Plus")) s1 = ds.getKey();
                            if(ds.getKey().equals("Centre de peche CR")) s2 = ds.getKey();
                            if(ds.getKey().equals("Lachine Bait & Tackle")) s3 = ds.getKey();
                            if(ds.getKey().equals("Old Montreal Fishing")) s4 = ds.getKey();
                            if(ds.getKey().equals("REEL UM IN fishing")) s5 = ds.getKey();
                            //skating
                            if(ds.getKey().equals("Arena Howie Morenz")) s1 = ds.getKey();
                            if(ds.getKey().equals("Atrium Le 1000")) s2 = ds.getKey();
                            if(ds.getKey().equals("Beaver Lake")) s3 = ds.getKey();
                            if(ds.getKey().equals("Legion Memorial Rink")) s4 = ds.getKey();
                            if(ds.getKey().equals("Patin Patin-Vieux-Port")) s5 = ds.getKey();
                            //skiing
                            if(ds.getKey().equals("Mont Blanc")) s1 = ds.getKey();
                            if(ds.getKey().equals("Mont Riguad")) s2 = ds.getKey();
                            if(ds.getKey().equals("Mont-Tremblent")) s3 = ds.getKey();
                            if(ds.getKey().equals("Montcalm")) s4 = ds.getKey();
                            if(ds.getKey().equals("Sommet Olympia")) s5 = ds.getKey();
                        }
                        loc1.setText(s1);
                        loc2.setText(s2);
                        loc3.setText(s3);
                        loc4.setText(s4);
                        loc5.setText(s5);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // calling on cancelled method when we receive
                        // any error or we are not able to get the data.
                        Toast.makeText(location.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(location.this, "Fail to get user location.", Toast.LENGTH_SHORT).show();
            }
        });

        loc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                ref = database.getReference().child("UserAccount").child("almDuiPqALfkQk0MdCg6ecek6nk1");
                ref.child("resLocation").setValue(loc1.getText());

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                ref = database.getReference().child("UserAccount").child("almDuiPqALfkQk0MdCg6ecek6nk1");
                ref.child("resLocation").setValue(loc2.getText());

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                ref = database.getReference().child("UserAccount").child("almDuiPqALfkQk0MdCg6ecek6nk1");
                ref.child("resLocation").setValue(loc3.getText());

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                ref = database.getReference().child("UserAccount").child("almDuiPqALfkQk0MdCg6ecek6nk1");
                ref.child("resLocation").setValue(loc4.getText());

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loc5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                ref = database.getReference().child("UserAccount").child("almDuiPqALfkQk0MdCg6ecek6nk1");
                ref.child("resLocation").setValue(loc5.getText());

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
