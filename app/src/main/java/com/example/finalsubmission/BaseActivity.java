package com.example.finalsubmission;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity implements LogoutListner{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MYApp) getApplication()).registerSessionListner(this);

        ((MYApp) getApplication()).startUserSession();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        ((MYApp) getApplication()).onUserInteracted();
    }

    @Override
    public void onSessionLogout() {
        finish();
        startActivity(new Intent(this,LoginActivity.class));
    }
}