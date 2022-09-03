package com.hmp.clock;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.Clock;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            if (savedInstanceState != null){
                seconds = savedInstanceState.getInt("seconds");
                System.out.println("Seconds**: "+seconds);
                running = savedInstanceState.getBoolean("running");
                System.out.println("Running: "+running);
                wasRunning = savedInstanceState.getBoolean("wasRunning");
            }
            runTimer();
}

    private void runTimer() {
        final  TextView textView = findViewById(R.id.stopwatch_time_view);
        final Handler handler = new Handler();
        // Call the post() method,
        // passing in a new Runnable.
        // The post() method processes
        // code without a delay,
        // so the code in the Runnable
        // will run almost immediately.
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600) /60;
                int sec = seconds % 60;

                // Format the seconds into hours, minutes,
                // and seconds.
                String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, sec);
                textView.setText(time);
                // If running is true, increment the
                // seconds variable.
                if (running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
    @Override
    protected void onPause(){
        super.onPause();
        wasRunning = running;
        running = false;
    }
    // If the activity is resumed,
    // start the stopwatch
    // again if it was running previously.
    @Override
    protected void onResume()
    {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }
    // Start the stopwatch running
    // when the Start button is clicked.
    // Below method gets called
    // when the Start button is clicked.
    public void onClickStart(View view)
    {
        running = true;
    }

    // Stop the stopwatch running
    // when the Stop button is clicked.
    // Below method gets called
    // when the Stop button is clicked.
    public void onClickStop(View view)
    {
        running = false;
    }

    // Reset the stopwatch when
    // the Reset button is clicked.
    // Below method gets called
    // when the Reset button is clicked.
    public void onClickReset(View view)
    {
        running = false;
        seconds = 0;
    }
}