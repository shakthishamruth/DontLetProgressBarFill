package com.example.dontletprogressbarfill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Chronometer simpleChronometer;

    private RelativeLayout parent;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.display_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.infoButton) {
            Toast.makeText(this, "Version 0.2", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.backButton) {
            setContentView(R.layout.mainscreen_layout);
            running = false;
            fill = 1;
            increment = 1;
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void onClickLeft(View view) {
        if (!running) {
            running = true;
            ProgressThread thread = new ProgressThread();
            thread.start();
            simpleChronometer = findViewById(R.id.simpleChronometer);
            simpleChronometer.setBase(SystemClock.elapsedRealtime());
            simpleChronometer.start();
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

    public void onClickPlay(View view) {
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        gameOverText = findViewById(R.id.gameOverText);
        parent = findViewById(R.id.parent);
    }

    public void onClickSettings(View view) {
        setContentView(R.layout.settingslayout);
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
                        Snackbar.make(parent, "Play Again?", Snackbar.LENGTH_INDEFINITE).setAction("Yes!", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setContentView(R.layout.activity_main);
                                progressBar = findViewById(R.id.progressBar);
                                gameOverText = findViewById(R.id.gameOverText);
                                parent = findViewById(R.id.parent);
                                fill = 1;
                                increment = 1;
                                gameOver = false;
                                simpleChronometer = findViewById(R.id.simpleChronometer);
                                simpleChronometer.setBase(SystemClock.elapsedRealtime());
                                simpleChronometer.start();
                            }
                        }).setActionTextColor(getColor(R.color.green)).show();
                        gameOverText.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        simpleChronometer.stop();
                        gameOver = true;
                    }
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
        }
    }
}