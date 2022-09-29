package com.example.calculator;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Flash flashFragment = new Flash();
        ft.add(R.id.fragment_container, flashFragment);
        ft.commit();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction ft2 = fm.beginTransaction();
                Calculator calcFragment = new Calculator();
                ft2.replace(R.id.fragment_container, calcFragment);
                ft2.commit();
            }
        }, 2000);

    }
}