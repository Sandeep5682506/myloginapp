package com.example.finalsubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetActivity extends AppCompatActivity {
    TextView useremail;
    EditText pass,repass;
    Button confirm;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        useremail = (TextView)findViewById(R.id.user_reset_text);
        pass = (EditText)findViewById(R.id.password_reset);
        repass = (EditText)findViewById(R.id.password_reset_confirm);
        confirm = (Button)findViewById(R.id.btnConfirm);
        DB = new DBHelper(this);

        Intent intent = getIntent();
        useremail.setText(intent.getStringExtra("email"));
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String user = useremail.getText().toString();
               String password =pass.getText().toString();
               String repassword = repass.getText().toString();

               if(password.equals(repassword)) {
                   Boolean checkpasswordupdate = DB.updatepassword(user, password);
                   if (checkpasswordupdate == true) {
                       Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                       startActivity(intent);
                       Toast.makeText(ResetActivity.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                   } else {
                       Toast.makeText(ResetActivity.this, "Password Not updated Successfully", Toast.LENGTH_SHORT).show();
                   }
               }
               else{
                   Toast.makeText(ResetActivity.this, "Password Not Matching", Toast.LENGTH_SHORT).show();
               }

            }
        });

    }
}