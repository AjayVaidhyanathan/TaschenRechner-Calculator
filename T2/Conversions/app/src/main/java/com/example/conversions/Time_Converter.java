package com.example.conversions;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Time_Converter extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convlayout);
    }

    public static double timeConvert(double originalNumber, String originalUnit, String newUnit)
    { //Begin convertTime
        //Make two doubles, one that holds the original and one that will be redefined where needed
        double num1 = originalNumber;
        double num2 = 0.0d;

        //Make two strings, capturing the units fed to the method
        String originalU = originalUnit.toLowerCase();
        String newU = newUnit.toLowerCase();

        //The series of if statements below figures out what unit to convert from/to, and does so.

        //Convert from seconds
        if(originalU.equals("seconds"))
        { //Begin converting from seconds
            //Thanks Pam for the suggestion about switch statements- I realized far too late how easy they are to use and how much better they look.
            switch(newU)
            {
                case "seconds":
                    num2 = num1;
                    break;
                case "minutes":
                    num2 = num1/60.0d;
                    break;
                case "hours":
                    num2 = num1/3600.0d;
                    break;
                case "days":
                    num2 = num1*0.000011574d;
                    break;
                case "weeks":
                    num2 = num1*0.0000016534d;
                    break;
                case "months":
                    num2 = num1*0.00000038027d;
                    break;
                case "years":
                    num2 = num1*0.000000031689d;
                    break;
            }
        } //End converting from seconds

        //Convert from minutes
        else if(originalU.contains("minute"))
        { //Begin converting from minutes
            switch(newU)
            {
                case "minutes":
                    num2 = num1;
                    break;
                case "seconds":
                    num2 = num1*60.0d;
                    break;
                case "hours":
                    num2 = num1/60.0d;
                    break;
                case "days":
                    num2 = num1/1440.0d;
                    break;
                case "weeks":
                    num2 = num1/10080.0d;
                    break;
                case "months":
                    num2 = num1/43829.1d;
                    break;
                case "years":
                    num2 = num1/525949.0d;
            }
        } //End converting from minutes

        //Convert from hours
        else if(originalU.contains("hour"))
        { //Begin converting from hours
            switch(newU)
            {
                case "hours":
                    num2 = num1;
                    break;
                case "seconds":
                    num2 = num1*3600.0d;
                    break;
                case "minutes":
                    num2 = num1*60.0d;
                    break;
                case "days":
                    num2 = num1/24.0d;
                    break;
                case "weeks":
                    num2 = num1/168.0d;
                    break;
                case "months":
                    num2 = num1/730.484d;
                    break;
                case "years":
                    num2 = num1/8765.81d;
                    break;
            }
        } //End converting from hours

        //Convert from days
        else if(originalU.contains("day"))
        { //Begin converting from days
            switch(newU)
            {
                case "days":
                    num2 = num1;
                    break;
                case "seconds":
                    num2 = num1*86400.0d;
                    break;
                case "minutes":
                    num2 = num1*1440.0d;
                    break;
                case "hours":
                    num2 = num1*24.0d;
                    break;
                case "weeks":
                    num2 = num1/7.0d;
                    break;
                case "months":
                    num2 = num1/30.4368d;
                    break;
                case "years":
                    num2 = num1/365.242d;
                    break;
            }
        } //End converting from days

        else if(originalU.contains("week"))
        { //Begin converting from weeks
            switch(newU)
            {
                case "weeks":
                    num2 = num1;
                    break;
                case "seconds":
                    num2 = num1*604800.0d;
                    break;
                case "minutes":
                    num2 = num1*10080.0d;
                    break;
                case "hours":
                    num2 = num1*168.0d;
                    break;
                case "days":
                    num2 = num1*7.0d;
                    break;
                case "months":
                    num2 = num1/4.34812d;
                    break;
                case "years":
                    num2 = num1/52.1775d;
                    break;
            }
        } //End converting from weeks

        //Convert from months
        else if(originalU.contains("month"))
        { //Begin converting from months
            switch(newU)
            {
                case "months":
                    num2 = num1;
                    break;
                case "seconds":
                    num2 = num1*2630000.0d;
                    break;
                case "minutes":
                    num2 = num1*43829.1d;
                    break;
                case "hours":
                    num2 = num1*730.484d;
                    break;
                case "days":
                    num2 = num1*30.4368d;
                    break;
                case "weeks":
                    num2 = num1*4.34812d;
                    break;
                case "years":
                    num2 = num1/12.0d;
                    break;
            }
        } //End converting from months

        //Convert from years
        else
        { //Begin converting from years
            switch(newU)
            {
                case "years":
                    num2 = num1;
                    break;
                case "seconds":
                    num2 = num1*31560000.0d;
                    break;
                case "minutes":
                    num2 = num1*525949.0d;
                    break;
                case "hours":
                    num2 = num1*8765.81d;
                    break;
                case "days":
                    num2 = num1*365.242d;
                    break;
                case "weeks":
                    num2 = num1*52.1775d;
                    break;
                case "months":
                    num2 = num1*12.0d;
                    break;
            }
        } //End converting from years

        return num2;
    } //End convertTime
}
