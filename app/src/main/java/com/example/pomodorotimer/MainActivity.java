package com.example.pomodorotimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    public SeekBar seekBar;
    boolean timerStarted = false;
    CountDownTimer countdownTimer;

    Button controllerButton;


    TextView timerText;

    public void startTimer(View view) {





        //if user presses start
        if (timerStarted == false){

            timerStarted = true;
            seekBar.setEnabled(false);

            countdownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100,1000) {

                public void onTick (long millisUntilFinished) {
                    int conversionNumber = (int) millisUntilFinished/1000;
                    updateTimer(conversionNumber);
                }
                public void onFinish() {
                    Toast.makeText(MainActivity.this, "Time is done", Toast.LENGTH_SHORT).show();
                    MediaPlayer mediaplayer = MediaPlayer.create(getApplicationContext(), R.raw.alarmsound);
                    mediaplayer.start();

                    timerText.setText("00:00");

                }
            }.start();

            controllerButton.setText("Stop");
            timerStarted = true;
        }


        //If user presses stop
        else if (timerStarted ==true){


            timerText.setText("00:30");
            countdownTimer.cancel();
            timerStarted = false;
            controllerButton.setText("Start");
            seekBar.setEnabled(true);
            seekBar.setProgress(30);


        }
    }


    //Update timer TextView
    public void updateTimer(int progress){

        int minutes = (int) progress/60;
        int seconds = progress - minutes * 60;




        String secondString = Integer.toString(seconds);

        //adjust the seconds when below 9 seconds to 00
        if (seconds <= 9){
            secondString = "0" + secondString;
        }


        //update timer
        timerText.setText(Integer.toString(minutes) + ":" + secondString);

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText = (TextView) findViewById(R.id.timerTextView);
        controllerButton = (Button) findViewById(R.id.startButton);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(500);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Log.i("Progress is ", Integer.toString(progress));
                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
}
