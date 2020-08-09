package com.example.finalsubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.finalsubmission.DBHelper.md5;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    String mail;
    Button btnlogin;
    TextView forget;
    DBHelper DB;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.etMail);
        mail = email.getText().toString();
        password = (EditText) findViewById(R.id.password1);
        forget = (TextView) findViewById(R.id.btnforgett);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String spass = md5(pass);



                if(mail.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkemailpass = DB.checkemailpassword(mail, spass);
                    if(checkemailpass==true){
                        Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("email",mail);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PasswordActivity.class);
                startActivity(intent);
            }
        });
    }
} 