package com.example.finalsubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,lastname,email,address,mobile, password, repassword;
    Button signup, signin;
    DBHelper DB;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        lastname = (EditText) findViewById(R.id.userLast);
        email = (EditText) findViewById(R.id.eMail);
        address = (EditText) findViewById(R.id.etAddress);
        mobile = (EditText) findViewById(R.id.etMobile);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                if(user.matches("")){
                    Toast.makeText(getApplicationContext(),"Enter First Name",Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(!user.matches("[a-zA-Z]+")){
                    Toast.makeText(getApplicationContext(),"Enter Valid First Name",Toast.LENGTH_SHORT).show();
                    return ;
                }
                String last = lastname.getText().toString().trim();
                if(last.matches("")){
                    Toast.makeText(getApplicationContext(),"Enter Last Name",Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(!last.matches("[a-zA-Z]+")){
                    Toast.makeText(getApplicationContext(),"Enter Valid Last Name",Toast.LENGTH_SHORT).show();
                    return ;
                }
                String mail = email.getText().toString().trim();
                if(mail.matches("")){
                    Toast.makeText(getApplicationContext(),"Enter Email",Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                    Toast.makeText(getApplicationContext(),"Enter Valid Email",Toast.LENGTH_SHORT).show();
                    return ;
                }
                String add = address.getText().toString().trim();
                String mob = mobile.getText().toString();
                if(mob.matches("")){
                    Toast.makeText(getApplicationContext(),"Enter Mobile",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!Patterns.PHONE.matcher(mob).matches() || mob.length()!=10){
                    Toast.makeText(getApplicationContext(),"Enter Valid Number",Toast.LENGTH_SHORT).show();
                    return ;
                }
                String pass = password.getText().toString().trim();
                String passwordVal = "^" +
                        //"(?=.*[0-9])" +         //at least 1 digit
                        //"(?=.*[a-z])" +         //at least 1 lower case letter
                        //"(?=.*[A-Z])" +         //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +      //any letter
                        "(?=.*[@#$%^&+=])" +    //at least 1 special character
                        "(?=\\S+$)" +           //no white spaces
                        ".{4,}" +               //at least 4 characters
                        "$";
                if(!pass.matches(passwordVal)){
                    Toast.makeText(getApplicationContext(),"Password weak",Toast.LENGTH_SHORT).show();
                    return ;
                }

                String repass = repassword.getText().toString().trim();

                if(user.equals("")||last.equals("")||mail.equals("")||add.equals("")||mob.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkemail = DB.checkemail(user);
                        if(checkemail==false){
                            Boolean insert = DB.insertData(user,last,mail,add,mob,pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                username.getText().clear();
                                lastname.getText().clear();
                                email.getText().clear();
                                address.getText().clear();
                                mobile.getText().clear();
                                password.getText().clear();
                                repassword.getText().clear();
                            }else{
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Email already exists!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}