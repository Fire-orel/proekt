package com.example.proekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view){
        // создание объекта Intent для запуска SecondActivity
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        String text_bt=b.getText().toString();

        Intent intent = new Intent(this, NapravleniaActivity.class);
        intent.putExtra("Napravlenie",text_bt );
        startActivity(intent);
    }
}