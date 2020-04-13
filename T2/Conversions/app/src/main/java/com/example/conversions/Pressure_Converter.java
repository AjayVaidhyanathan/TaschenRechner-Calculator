package com.example.conversions;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Pressure_Converter extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convlayout);
    }

    public static double pressureConvert(double originalNum, String originalUnit, String desiredUnit) {
        //Make two variable doubles, one the original double and one the new one
        double num2 = 0.0d;


        //Store the units into new strings. I find this to be safer, as I can't override the originals this way.
        //Also convert them to lower case
        String original = originalUnit.toLowerCase();
        String newU = desiredUnit.toLowerCase();


        if (original.equals("torr")) {
            if (newU.equals("torr")) {
                num2 = originalNum;

            } else if (newU.equals("atm")) {
                num2 = originalNum * 0.0013157893594d;
            } else if (newU.equals("mmhg")) {
                num2 = originalNum * 0.99999984999d;
            } else {
                num2 = originalNum * 0.0013332237d;
            }
        } else if (original.equals("atm")) {
            if (newU.equals("atm")) {
                num2 = originalNum;

            } else if (newU.equals("torr")) {
                num2 = originalNum * 760.00006601d;
            } else if (newU.equals("mmhg")) {
                num2 = originalNum * 759.999952d;
            } else {
                num2 = originalNum * 1.0132501d;
            }
        } else if (original.equals("mmhg")) {
            if (newU.equals("mmhg")) {
                num2 = originalNum;

            } else if (newU.equals("torr")) {
                num2 = originalNum * 1.00000015d;
            } else if (newU.equals("atm")) {
                num2 = originalNum * 0.0013157895568d;
            } else {
                num2 = originalNum * 0.0013332239d;
            }
        } else {
            if (newU.equals("bar")) {
                num2 = originalNum;

            } else if (newU.equals("torr")) {
                num2 = originalNum * 750.06167382d;
            } else if (newU.equals("atm")) {
                num2 = originalNum * 0.98692316931d;
            } else {
                num2 = originalNum * 750.0615613d;
            }
        }
        return num2;
    }
}

