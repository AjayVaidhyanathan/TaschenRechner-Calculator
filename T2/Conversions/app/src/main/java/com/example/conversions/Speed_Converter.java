package com.example.conversions;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Speed_Converter extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convlayout);
    }

    public static double speedConvert(double originalNumber, String originalUnit, String newUnit)
    { //Begin convertSpeed
        //Make two doubles, one that holds the original and one that will be redefined where needed
        double num1 = originalNumber;
        double num2 = 0.0d;

        //Make two strings, capturing the units fed to the method
        String originalU = originalUnit.toLowerCase();
        String newU = newUnit.toLowerCase();

        //The series of if statements below figures out what unit to convert from/to, and does so.

        switch(originalU)
        { //Begin conversion table
            case "miles per hour":
                switch(newU)
                { //Begin converting from miles per hour
                    case "miles per hour":
                        num2 = originalNumber;
                        break;
                    case "feet per second":
                        num2 = num1*1.46667d;
                        break;
                    case "kilometers per second":
                        num2 = num1*0.00044704d;
                        break;
                    case "kilometers per hour":
                        num2 = num1*1.60934d;
                        break;
                    case "meters per second":
                        num2 = num1*0.44704d;
                        break;
                } //End converting from miles per hour
                break;
            case "feet per second":
                switch(newU)
                { //Begin converting from feet per second
                    case "miles per hour":
                        num2 = num1*0.681818d;
                        break;
                    case "feet per second":
                        num2 = originalNumber;
                        break;
                    case "kilometers per second":
                        num2 = num1*0.0003048d;
                        break;
                    case "kilometers per hour":
                        num2 = num1*1.09728d;
                        break;
                    case "meters per second":
                        num2 = num1*0.3048d;
                        break;
                } //End converting from feet per second
                break;
            case "kilometers per second":
                switch(newU)
                { //Begin converting from kilometers per second
                    case "miles per hour":
                        num2 = num1*2236.93629d;
                        break;
                    case "feet per second":
                        num2 = num1*3280.8399d;
                        break;
                    case "kilometers per second":
                        num2 = originalNumber;
                        break;
                    case "kilometers per hour":
                        num2 = num1*3600.0d;
                        break;
                    case "meters per second":
                        num2 = num1*0.277778d;
                        break;
                } //End converting from kilometers per second
                break;
            case "kilometers per hour":
                switch(newU)
                { //Begin converting from kilometers per hour
                    case "miles per hour":
                        num2 = num1*2.23694d;
                        break;
                    case "feet per second":
                        num2 = num1*0.911344d;
                        break;
                    case "kilometers per second":
                        num2 = num1*0.000277777778d;
                        break;
                    case "kilometers per hour":
                        num2 = originalNumber;
                        break;
                    case "meters per second":
                        num2 = num1*0.277778d;
                        break;
                } //end converting from kilometers per hour
                break;
            case "meters per second":
                switch(newU)
                { //Begin converting from meters per second
                    case "miles per hour":
                        num2 = num1*2.23694d;
                        break;
                    case "feet per second":
                        num2 = num1*3.28084d;
                        break;
                    case "kilometers per second":
                        num2 = num1*0.001d;
                        break;
                    case "kilometers per hour":
                        num2 = num1*3.6d;
                        break;
                    case "meters per second":
                        num2 = originalNumber;
                        break;
                } //End converting from meters per second
                break;
        } //End conversion table

        //Return the result
        return num2;
    } //End convertSpeed
}
