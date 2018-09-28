package com.example.android.applayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private TextView logout,profile,attendance,activity,achievements;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout=(TextView)findViewById(R.id.logout);
        profile=(TextView)findViewById(R.id.profile);
        attendance=(TextView)findViewById(R.id.attendance);
        activity=(TextView)findViewById(R.id.activity);
        achievements=(TextView)findViewById(R.id.achievements);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.getInstance().signOut();
                Intent intent=new Intent(getApplicationContext(),HomeScreenActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ProfileScreenActivity.class);
                startActivity(intent);
                //finish()

            }
        });
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),GraphActivity.class);
                startActivity(intent);
                //finish()

            }
        });
        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ActivitiesActivity.class);
                startActivity(intent);
                //finish()

            }
        });
        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AchievementsActivity.class);
                startActivity(intent);
                //finish()

            }
        });






    }

}
