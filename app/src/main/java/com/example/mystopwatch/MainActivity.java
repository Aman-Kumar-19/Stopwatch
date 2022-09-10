package com.example.mystopwatch;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mystopwatch.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    private int seconds=0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    public void onSaveInstanceState(Bundle saveInstanceState){
        saveInstanceState.putInt("seconds",seconds);
        saveInstanceState.putBoolean("running",running);
    }

    public void onClickstart(View view){
        running=true;

    }

    public void onClickstop(View view){
        running=false;

    }

    public void onClickreset(View view){
        running=false;
        seconds=0;
    }
    private void runTimer(){
        final TextView timeview=(TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int sec=seconds%60;

                String time=String.format("%d:%02d:%02d",hours,minutes,sec);
                timeview.setText(time);

                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });

    }



}