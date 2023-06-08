package com.example.dontletprogressbarfill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Left, Right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLeft(View view) {
        Left = findViewById(R.id.leftButton);
        Right = findViewById(R.id.rightButton);
        Right.setEnabled(true);
        Left.setEnabled(false);
    }

    public void onClickRight(View view) {
        Left = findViewById(R.id.leftButton);
        Right = findViewById(R.id.rightButton);
        Right.setEnabled(false);
        Left.setEnabled(true);
    }
}