package com.example.hackcoming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    private LinearLayout layout;
    private String changedtimeSlot;
    private String date;
    private DatabaseReference mDatabase;
    private String longTimeSlot;
    public String reservedLocation;
    public String reservedActivity;
    public String timeslots;

    public String resAct;
    public String resLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dropdown part
        Spinner spn = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adap = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1
                ,getResources().getStringArray(R.array.Dates));

        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adap);

        spn.setOnItemSelectedListener(this);

        // checkout button stores the clicked date and time to firebase and move to the next page.
        Button button= (Button)findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                saveToDataBase(changedtimeSlot,date);
                Intent intent = new Intent(getApplicationContext(),ReservationPage.class);
                startActivity(intent);
                finish();
            }
        });
        Log.d(longTimeSlot, "longtime");
    }

//    public void dropHelp(String[] time) {
//        onItemSelected();
//        createBtn(time);
//    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        date = text;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("UserAccount").child("almDuiPqALfkQk0MdCg6ecek6nk1");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    if(ds.getKey().equals("resActivity")){
                        reservedActivity = ds.getValue(String.class);
                    }
                    if(ds.getKey().equals("resLocation")){
                        reservedLocation = ds.getValue(String.class);
                    }
                }
                FirebaseDatabase.getInstance().getReference().child("Activity")
                        .child(reservedActivity).child(reservedLocation).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds: snapshot.getChildren()){
                            if(ds.getKey().equals(text)) timeslots = ds.getValue(String.class);
                        }
                        createBtn(timeslots);
                        updateData(text, timeslots);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // calling on cancelled method when we receive
                        // any error or we are not able to get the data.
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void updateData(String key, String value){
        String output = "";
        String[] str = value.split(",");
        ArrayList<String> result = new ArrayList<>(Arrays.asList(str));
        for (String s : result)
        {
            output += s + ",";
        }
        String finalOutput = output;
        FirebaseDatabase.getInstance().getReference().child("UserAccount")
                .child("almDuiPqALfkQk0MdCg6ecek6nk1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    if(ds.getKey().equals("resActivity")) resAct = ds.getValue(String.class);
                    if(ds.getKey().equals("resLocation")) resLoc = ds.getValue(String.class);
                }
                mDatabase = FirebaseDatabase.getInstance().getReference("Activity").child(resAct).child(resLoc);;
                mDatabase.child(key).setValue(finalOutput);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
            }
        });

    }

    public void createBtn (String input){

        try {
            layout.removeAllViews();

            String[] str = input.split(",");

            String st = "2022-01-01";
            //        String st2 = "2022-01-02";
            //        String st3 = "2022-01-03";
            //        String st4 = "2022-01-04";
            //        String st5 = "2022-01-04";

            // this helper method will store the corresponding value of the date from database to longtimeSlot.
            //fromDataBase(date);

            //String [] starr = breakTimeSlots(longTimeSlot);

            ArrayList<String> arrTime = new ArrayList<>(Arrays.asList(str));
            //ArrayList<String> arrTime = new ArrayList<>();
            ArrayList<Button> buttons = new ArrayList<Button>();


            //      create btns
            for (int i = 0; i < arrTime.size(); i++) {
                Button btn = new Button(this);
                btn.setId(i);
                buttons.add(btn);
            }

            // button part
            layout = findViewById(R.id.linearlayoutid);
            //grab the timeslot from firebase and store them in a array. Change the number to arr.length()

            for (int i = 0; i < arrTime.size(); i++) {
                buttons.get(i).setLayoutParams(new LinearLayout.LayoutParams(1080, 150));
                buttons.get(i).setText(arrTime.get(i));
                layout.addView(buttons.get(i));
            }

                    for (Button b : buttons) {
                        b.setOnClickListener(this);
                    }

        }
        catch(Exception e) {


            String[] str = input.split(",");

            String st = "2022-01-01";
            //        String st2 = "2022-01-02";
            //        String st3 = "2022-01-03";
            //        String st4 = "2022-01-04";
            //        String st5 = "2022-01-04";

            // this helper method will store the corresponding value of the date from database to longtimeSlot.
            //fromDataBase(date);

            //String [] starr = breakTimeSlots(longTimeSlot);

            ArrayList<String> arrTime = new ArrayList<>(Arrays.asList(str));
            //ArrayList<String> arrTime = new ArrayList<>();
            ArrayList<Button> buttons = new ArrayList<Button>();


            //      create btns
            for (int i = 0; i < arrTime.size(); i++) {
                Button btn = new Button(this);
                btn.setId(i);
                buttons.add(btn);
            }

            // button part
            layout = findViewById(R.id.linearlayoutid);
            //grab the timeslot from firebase and store them in a array. Change the number to arr.length()

            for (int i = 0; i < arrTime.size(); i++) {
                buttons.get(i).setLayoutParams(new LinearLayout.LayoutParams(1080, 150));
                buttons.get(i).setText(arrTime.get(i));
                layout.addView(buttons.get(i));
            }

                for (Button b : buttons) {
                    b.setOnClickListener(this);
                }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case 0:
                v.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
                String btnText = ((Button)v).getText().toString();
                changedtimeSlot = btnText;
                break;
            case 1:
                v.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
                String btnText1 = ((Button)v).getText().toString();
                changedtimeSlot = btnText1;
                break;
            case 2:
                v.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
                String btnText2 = ((Button)v).getText().toString();
                changedtimeSlot = btnText2;
                break;
            case 3:
                v.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
                String btnText3 = ((Button)v).getText().toString();
                changedtimeSlot = btnText3;
                break;
            case 4:
                v.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
                String btnText4 = ((Button)v).getText().toString();
                changedtimeSlot = btnText4;
                break;
            case 5:
                v.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
                String btnText5 = ((Button)v).getText().toString();
                changedtimeSlot = btnText5;
                break;
            case 6:
                v.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
                String btnText6 = ((Button)v).getText().toString();
                changedtimeSlot = btnText6;
                break;
        }
    }

    public void saveToDataBase(String date, String timeslot) {
        mDatabase = FirebaseDatabase.getInstance().getReference("UserAccount").child("almDuiPqALfkQk0MdCg6ecek6nk1");;
        mDatabase.child("resDate").setValue(date);
        mDatabase.child("resTime").setValue(timeslot);

    }
}