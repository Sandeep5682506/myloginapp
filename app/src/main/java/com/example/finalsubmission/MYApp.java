package com.example.finalsubmission;

import android.app.Application;

import java.util.Timer;
import java.util.TimerTask;

public class MYApp extends Application {
    private LogoutListner listner;
    private Timer timer;

    public void startUserSession() {
        canceltimer();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                listner.onSessionLogout();

            }
        }, 5000);
    }

    private void canceltimer() {

        if(timer!=null) timer.cancel();
    }

    public void registerSessionListner(LogoutListner listner) {
        this.listner = listner;
    }

    public void onUserInteracted() {
        startUserSession();
    }
}
