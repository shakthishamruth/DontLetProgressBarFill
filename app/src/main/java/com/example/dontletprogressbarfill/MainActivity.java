package com.example.dontletprogressbarfill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button Left, Right;

    private int fill = 0, increment = 1;

    private ProgressBar progressBar;

    private boolean gameOver = false, running = false;

    private TextView gameOverText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen_layout);
    }

    public void onClickLeft(View view) {

        if (!running) {
            running = true;
            ProgressThread thread = new ProgressThread();
            thread.start();
        }
        Left = findViewById(R.id.leftButton);
        Right = findViewById(R.id.rightButton);
        Right.setEnabled(true);
        Left.setEnabled(false);
        fill = fill - 5;
    }

    public void onClickRight(View view) {
        Left = findViewById(R.id.leftButton);
        Right = findViewById(R.id.rightButton);
        Right.setEnabled(false);
        Left.setEnabled(true);
        fill = fill - 5;
    }

    public void onClickTest(View view) {
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        gameOverText = findViewById(R.id.gameOverText);
    }

    public void onClickPlay(View view) {
        //setContentView(R.layout.maingame);
    }

    public void onClickLeftMain(View view) {

    }

    public void onClickRightMain(View view) {

    }

    class ProgressThread extends Thread {
        @Override
        public void run() {
            while (running) {
                try {
                    if (!gameOver) {
                        if (fill <= 1) {
                            fill = 1;
                        }
                        increment = increment + 1;
                        fill = fill + increment;
                        progressBar.setProgress(fill);
                    }
                    if (fill >= 100 && !gameOver) {
                        gameOverText.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        gameOver = true;
                    }
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        }
    }
}