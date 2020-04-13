package com.example.conversions;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Area_Converter extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convlayout);
    }

    public static double areaConvert(double originalNumber, String originalUnit, String newUnit)
    { //Begin convertArea
        //Make two doubles, one that holds the original and one that will be redefined where needed
        double num1 = originalNumber;
        double num2 = 0.0d;

        //Make two strings, capturing the units fed to the method
        String originalU = originalUnit.toLowerCase();
        String newU = newUnit.toLowerCase();

        switch(originalU)
        {
            //Begin unit conversions
            case "square inches":
                switch(newU)
                { //Begin converting from square inches
                    case "square inches":
                        num2 = num1;
                        break;
                    case "square feet":
                        num2 = num1/144.0d;
                        break;
                    case "square yards":
                        num2 = num1/1296.0d;
                        break;
                    case "square miles":
                        num2 = num1/4014000000.0d;
                        break;
                    case "acres":
                        num2 = num1/6273000.0d;
                        break;
                    case "square kilometers":
                        num2 = num1/1550000000.0d;
                        break;
                    case "square meters":
                        num2 = num1/1550.0d;
                        break;
                } //End converting from square inches
                break;
            case "square feet":
                switch(newU)
                { //Begin converting from square feet
                    case "square inches":
                        num2 = num1*144.0d;
                        break;
                    case "square feet":
                        num2 = num1;
                        break;
                    case "square yards":
                        num2 = num1/9.0d;
                        break;
                    case "square miles":
                        num2 = num1/27880000.0d;
                        break;
                    case "acres":
                        num2 = num1/43560.0d;
                        break;
                    case "square kilometers":
                        num2 = num1/10760000.0d;
                        break;
                    case "square meters":
                        num2 = num1/10.7639d;
                        break;
                } //End converting from square feet
                break;
            case "square yards":
                switch(newU)
                { //Begin converting from square yards
                    case "square inches":
                        num2 = num1*1296.0d;
                        break;
                    case "square feet":
                        num2 = num1*9.0d;
                        break;
                    case "square yards":
                        num2 = num1;
                        break;
                    case "square miles":
                        num2 = num1/3098000.0d;
                        break;
                    case "acres":
                        num2 = num1/4840.0d;
                        break;
                    case "square kilometers":
                        num2 = num1/1196000.0d;
                        break;
                    case "square meters":
                        num2 = num1/1.19599d;
                        break;
                }//End converting from square yards
                break;
            case "square miles":
                switch(newU)
                { //Begin converting from square miles
                    case "square inches":
                        num2 = num1*4014000000.0d;
                        break;
                    case "square feet":
                        num2 = num1*27880000.0d;
                        break;
                    case "square yards":
                        num2 = num1*3098000.0d;
                        break;
                    case "square miles":
                        num2 = num1;
                        break;
                    case "acres":
                        num2 = num1*640.0d;
                        break;
                    case "square kilometers":
                        num2 = num1*2.58999d;
                        break;
                    case "square meters":
                        num2 = num1*2590000.0d;
                        break;
                }//End converting from square miles
                break;
            case "acres":
                switch(newU)
                {//Begin converting from acres
                    case "square inches":
                        num2 = num1*6273000.0d;
                        break;
                    case "square feet":
                        num2 = num1*43560.0d;
                        break;
                    case "square yards":
                        num2 = num1*4840.0d;
                        break;
                    case "square miles":
                        num2 = num1/640.0d;
                        break;
                    case "square acres":
                        num2 = num1;
                        break;
                    case "square kilometers":
                        num2 = num1/247.105d;
                        break;
                    case "square meters":
                        num2 = num1*4046.86d;
                        break;
                } //End converting from acres
                break;
            case "square kilometers":
                switch(newU)
                { //Begin converting from square kilometers
                    case "square inches":
                        num2 = num1*1550000000.0d;
                        break;
                    case "square feet":
                        num2 = num1*10760000.0d;
                        break;
                    case "square yards":
                        num2 = num1*1196000.0d;
                        break;
                    case "square miles":
                        num2 = num1/2.58999d;
                        break;
                    case "acres":
                        num2 = num1*247.105381d;
                        break;
                    case "square kilometers":
                        num2 = num1;
                        break;
                    case "square meters":
                        num2 = num1*1000000.0d;
                        break;
                } //End converting from square kilometers
                break;
            case "square meters":
                switch(newU)
                { //Begin converting from square meters
                    case "square inches":
                        num2 = num1*1550.0d;
                        break;
                    case "square feet":
                        num2 = num1*10.7639d;
                        break;
                    case "square yards":
                        num2 = num1*1.19599d;
                        break;
                    case "square miles":
                        num2 = num1/2590000.0d;
                        break;
                    case "acres":
                        num2 = num1/4046.86d;
                        break;
                    case "square kilometers":
                        num2 = num1/1000000.0d;
                        break;
                    case "square meters":
                        num2 = num1;
                        break;
                } //End converting from square meters
        } //End conversion table

        //Return the resulting number from the conversion table above
        return num2;
    } //End convertArea
}
