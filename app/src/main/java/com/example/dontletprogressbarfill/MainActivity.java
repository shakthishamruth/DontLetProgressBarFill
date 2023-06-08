package com.example.dontletprogressbarfill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button Left, Right;

    private int fill = 0;

    private ProgressBar progressBar;

    private boolean gameOver = false, running = false;

    private TextView gameOverText;

    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        gameOverText = findViewById(R.id.gameOverText);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    if (!gameOver) {
                        fill = fill + 10;
                        progressBar.setProgress(fill);
                    }
                    if (fill >= 100 && !gameOver) {
                        gameOver = true;
                        progressBar.setVisibility(View.INVISIBLE);
                        gameOverText.setVisibility(View.VISIBLE);
                        return;
                    }
                    SystemClock.sleep(500);
                }
            }
        });
    }

    public void onClickLeft(View view) {
        if (!running) {
            running = true;
            thread.start();
        }
        Left = findViewById(R.id.leftButton);
        Right = findViewById(R.id.rightButton);
        Right.setEnabled(true);
        Left.setEnabled(false);
        fill = fill - 10;
    }

    public void onClickRight(View view) {
        Left = findViewById(R.id.leftButton);
        Right = findViewById(R.id.rightButton);
        Right.setEnabled(false);
        Left.setEnabled(true);
        fill = fill - 10;
    }
}