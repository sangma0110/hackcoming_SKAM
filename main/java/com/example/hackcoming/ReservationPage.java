package com.example.hackcoming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class ReservationPage extends AppCompatActivity {

/*
   private FirebaseDatabase database;
    private DatabaseReference ref;

    private String name;
    private TextView userName;
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

 */

    private FirebaseDatabase database;
    private DatabaseReference ref;

    private String name, location, date, time;

    private TextView text_location, text_date, text_time;

    EditText cal_location, cal_date, cal_time;
    Button addEvent;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_page);

        text_location = findViewById(R.id.text_location);
        text_date = findViewById(R.id.text_date);
        text_time = findViewById(R.id.text_time);

        addEvent = findViewById(R.id.button);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("UserAccount").child("almDuiPqALfkQk0MdCg6ecek6nk1");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    if (ds.getKey().equals("resLocation")) location = ds.getValue(String.class);
                    else if (ds.getKey().equals("resDate")) date = ds.getValue(String.class);
                    else if (ds.getKey().equals("resTime")) time = ds.getValue(String.class);

                }
                text_location.setText(location);
                text_date.setText(date);
                text_time.setText(time);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ReservationPage.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!text_location.getText().toString().isEmpty() && !text_date.getText().toString().isEmpty() && !text_time
                        .getText().toString().isEmpty()) {

                    Calendar beginTime = Calendar.getInstance();
                    beginTime.set(2022, 0, 1, 9, 00);
                    Calendar endTime = Calendar.getInstance();
                    beginTime.set(2022, 0, 1, 9, 00);
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, "Skating Reservation at 9:00");


                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, text_location.getText().toString());
                    //intent.putExtra(CalendarContract.Events.DESCRIPTION, text_time.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, true);
                    intent.putExtra(Intent.EXTRA_EMAIL, "jaeshin.cho@mail.mcgill.ca, sangma0110@gmail.com");
                    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
                    intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());



                    if(intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }else{
                        Toast.makeText(ReservationPage.this, "There is no app that support this action", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(ReservationPage.this,"Please fill all this fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}