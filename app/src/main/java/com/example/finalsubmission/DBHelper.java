package com.example.finalsubmission;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT ,lastname TEXT ,email TEXT ,address TEXT,mobile INTEGER primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }




    public Boolean insertData(String username,String lastname,String email,String address,String mobile, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        password = md5(password);
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("lastname", lastname);
        contentValues.put("email", email);
        contentValues.put("address", address);
        contentValues.put("mobile", mobile);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean updatepassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        password = md5(password);
        ContentValues contentValues= new ContentValues();
        contentValues.put("password", password);
        long result = MyDB.update("users", contentValues,"email = ?",new String[]{email});
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkemail(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkemailpassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public String data(String s){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?",new String[]{s});
        cursor.moveToFirst();
        return (cursor.getString(0)+" "+cursor.getString(1));
    }
}
