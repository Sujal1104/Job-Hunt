package com.example.freelancer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        getSupportActionBar().hide();
    }

    public void NextActivity6(View view) {
        Intent intent=new Intent(MainActivity4.this,MainActivity6.class);
        startActivity(intent);
    }
}