package com.example.finalsubmission;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends BaseActivity { //Appcompactivity changes to baseActivity
    TextView helloUser;
    DBHelper DB;

    @Override
    public void onCreate(Bundle savedInstanceState) { //Makes Public the onCreate function
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DB = new DBHelper(this);
        helloUser = (TextView) findViewById(R.id.helloUser);

       String str = DB.data(getIntent().getStringExtra("email"));
       helloUser.setText("WELCOME"+" "+str+" "+"in HEY FOODIE!");
        }
    }