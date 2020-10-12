package com.example.qrattendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class MakeSelection extends AppCompatActivity {


    MaterialButton mark,view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_selection);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Make Selection");
        setSupportActionBar(myToolbar);

        mark=findViewById(R.id.MarkButton);
        view=findViewById(R.id.ShowButton);

    }

    public void ShowMarkScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void ShowAttendanceScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), ShowAttendanceActivity.class);
        startActivity(intent);
    }

    public void ShowMonthlyAttendanceScreen(View view) {

        Intent intent = new Intent(getApplicationContext(), ShowMonthlyAttendanceActivity.class);
        startActivity(intent);
    }
}
