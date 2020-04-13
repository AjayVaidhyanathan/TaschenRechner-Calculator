package com.example.conversions;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Force_Converter extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convlayout);
    }

    public static double forceConvert(double originalNumber, String originalUnit, String newUnit)
    { //Begin convertForce
        //Make two doubles, one that holds the original and one that will be redefined where needed
        double num1 = originalNumber;
        double num2 = 0.0d;

        //Make two strings, capturing the units fed to the method
        String originalU = originalUnit.toLowerCase();
        String newU = newUnit.toLowerCase();

        //The series of switch statements below figures out what unit to convert from/to, and does so.
        switch(originalU)
        {
            case "pound force":
                switch(newU)
                {
                    case "pound force":
                        num2 = num1;
                        break;
                    case "newtons":
                        num2 = num1*4.448222d;
                        break;
                }
                break;
            case "newtons":
                switch (newU) {
                    case "newtons":
                        num2 = num1;
                        break;
                    case "pound force":
                        num2 = num1 / 4.448222d;
                        break;
                }
        }
        return num2;
    } //End convertForce
}
