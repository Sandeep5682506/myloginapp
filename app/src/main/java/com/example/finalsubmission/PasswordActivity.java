package com.example.finalsubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {
     EditText useremail;
     Button reset;
     DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        useremail = (EditText)findViewById(R.id.email_reset);
        reset = (Button)findViewById(R.id.btnReset);
        DB = new  DBHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = useremail.getText().toString();
                Boolean checkuser = DB.checkemail(user);
                if(checkuser ==true){
                    Intent intent = new Intent(getApplicationContext(),ResetActivity.class);
                    intent.putExtra("email",user);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(PasswordActivity.this,"Email does'nt Exit!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}