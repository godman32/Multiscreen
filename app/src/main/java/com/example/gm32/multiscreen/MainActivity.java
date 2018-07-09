package com.example.gm32.multiscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.support.v4.view.ViewPager;



import java.lang.Object;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openColorActivity(View view){
        Intent i= new Intent(this, ColorsActivity.class);
        startActivity(i);
    }

    public void openNumberActivity(View view){
        Intent i= new Intent(this, NumberActivity.class);
        startActivity(i);
    }

    public void openFamilyActivity(View view){
        Intent i= new Intent(this, FamilyActivity.class);
        startActivity(i);
    }
}
