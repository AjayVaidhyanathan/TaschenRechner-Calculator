package com.example.conversions;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Temp_Converter extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convlayout);
    }


    public static double tempConvert(double originalNum, String originalUnit, String desiredUnit)
    { //Begin tempConvert
        //Make two variable doubles, one the original double and one the new one
        double num2 = 0.0d;
        double num3;

        //Store the units into new strings. I find this to be safer, as I can't override the originals this way.
        //Also convert them to lower case
        String original = originalUnit.toLowerCase();
        String newU = desiredUnit.toLowerCase();

        switch(original)
        { //Begin conversion table
            case "celsius":
            { //Begin converting from Celsius
                switch(newU)
                {
                    case "celsius":
                        num2 = originalNum;
                        break;
                    case "fahrenheit":
                        num2 = (originalNum*(9.0/5.0)) + 32d;
                        break;
                    case "kelvin":
                        num2 = originalNum + 273.15d;
                        break;
                }
                break;
            } //end converting from Celsius
            case "fahrenheit":
            { //Begin converting from Fahrenheit
                switch(newU)
                {
                    case "celsius":
                        num3 = (-originalNum - 32d);
                        num2 = num3 * (5.0/9.0);
                        break;
                    case "fahrenheit":
                        num2 = originalNum;
                        break;
                    case "kelvin":
                        num2 = ((originalNum - 32d)*(5.0/9.0)) + 273.15d;
                        break;
                }
                break;
            } //End converting from Fahrenheit
            case "kelvin":
            { //Begin converting from Kelvin
                switch(newU)
                {
                    case "celsius":
                        num2 = originalNum - 273.15d;
                        break;
                    case "fahrenheit":
                        num2 = ((originalNum - 273.15d) *(9.0/5.0)) + 32d;
                        break;
                    case "kelvin":
                        num2 = originalNum;
                        break;
                }
                break;
            }
        } //End conversion table

        //Return the final number, num2
        return num2;
    } //End tempConvert












}//End converter class

