package com.example.conversions;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Conv extends AppCompatActivity {

    float x1, x2, y1, y2;
    Boolean Length_clicked = false, Area_clicked = false, Volume_clicked = false, Speed_clicked = false, Time_clicked = false;
    Boolean Temperature_clicked = false, Force_clicked = false, Pressure_clicked = false , Mass_clicked = false;
    Intent i;
    String LClick="" , AClick="" , VClick ="" , SClick="" , TimeClick="" , TClick="" , FClick="" , PressClick = "" , MClick="";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conv);


    }

    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (x1 < x2) {
                    Intent i = new Intent(Conv.this, MainActivity.class);
                    startActivity(i);
                } else if (x1 > x2) {
                    Intent i = new Intent(Conv.this, Conv.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }

    public void onclickLength(View v) {
        Length_clicked = true;
        Intent j = new Intent(Conv.this, conv_layout.class);
        j.putExtra("LClick" , Length_clicked);
        startActivity(j);
    }

    public void onclickVolume(View v) {
        Volume_clicked = true;
        Intent j = new Intent(Conv.this, conv_layout.class);
        j.putExtra("VClick" , Volume_clicked);
        startActivity(j);
    }

    public void onclickArea(View v) {
        Area_clicked = true;
        Intent j = new Intent(Conv.this, conv_layout.class);
        j.putExtra("AClick", Area_clicked);
        startActivity(j);
    }

    public void onclickSpeed(View v) {
        Speed_clicked = true;
        Intent j = new Intent(Conv.this, conv_layout.class);
        j.putExtra("SClick" , Speed_clicked);
        startActivity(j);
    }

    public void onclickTime(View v) {
        Time_clicked = true;
        Intent j = new Intent(Conv.this, conv_layout.class);
        j.putExtra("TimeClick" , Time_clicked);
        startActivity(j);
    }

    public void onclickTemperature(View v) {
        Temperature_clicked = true;
        Intent j = new Intent(Conv.this, conv_layout.class);
        j.putExtra("TClick" , Temperature_clicked);
        startActivity(j);
    }

    public void onclickForce(View v) {
        Force_clicked = true;
        Intent j = new Intent(Conv.this, conv_layout.class);
        j.putExtra("FClick", Force_clicked);
        startActivity(j);
    }

    public void onclickPressure(View v) {
        Pressure_clicked = true;
        Intent j = new Intent(Conv.this, conv_layout.class);
        j.putExtra("PressClick" , Pressure_clicked);
        startActivity(j);
    }

    public void onclickMass(View v) {
        Mass_clicked = true;
        Intent j = new Intent(Conv.this, conv_layout.class);
        j.putExtra("MClick", Mass_clicked);
        startActivity(j);
    }


}






