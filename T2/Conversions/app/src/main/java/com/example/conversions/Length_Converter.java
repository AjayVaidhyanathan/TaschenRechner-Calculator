package com.example.conversions;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Length_Converter extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.convlayout);
    }

    public static double LConvert(double Input, String OldUnit, String NUnit)
    {
        String original = OldUnit.toLowerCase();
        String newU = NUnit.toLowerCase();
        double num1 = Input;
        double num2 = 0.0d;


        //Now there is a series of if statements to check which units are being converted from/to and
        //to do the proper operation
        switch (original) {
            case "inches": { //Begin converting from inches
                switch (newU) {
                    case "inches":
                        num2 = num1;
                        break;
                    case "feet":
                        //12 inches in a foot, so divide by 12
                        num2 = num1 / 12.0d;
                        break;
                    case "yards":
                        //36 inches in a yard, so divide by 36
                        num2 = num1 / 36.0d;
                        break;
                    case "miles":
                        //63,360 inches in a mile
                        num2 = num1 / 63360.0d;
                        break;
                    case "millimeters":
                        //There are 25.4 millimeters in an inch, so multiply inches by 25.4
                        num2 = num1 * 25.4d;
                        break;
                    case "centimeters":
                        //2.54cm in an inch, multiply inches by 2.54
                        num2 = num1 * 2.54d;
                        break;
                    case "meters":
                        //0.0254 meters to an inch, so multiply inches by 0.0254
                        num2 = num1 * 0.0254d;
                        break;
                    case "kilometers":
                        //0.0000254km in an inch
                        num2 = num1 * 0.0000254d;
                        break;
                }
                break;
            } //End converting from inches

            //Next, converting from feet
            case "feet": { //Begin conversions from feet
                switch (newU) {
                    case "inches":
                        //12 inches in a foot, multiply by 12
                        num2 = num1 * 12.0d;
                        break;
                    case "feet":
                        num2 = num1;
                        break;
                    case "yards":
                        //3 feet in a yard
                        num2 = num1 / 3.0d;
                        break;
                    case "miles":
                        //5,280 feet in a mile
                        num2 = num1 / 5280.0d;
                        break;
                    case "millimeters":
                        //304.8 millimeters in a foot
                        num2 = num1 * 304.8d;
                        break;
                    case "centimeters":
                        //30.48 Centimeters in a foot
                        num2 = num1 * 30.48d;
                        break;
                    case "meters":
                        //0.3048 meters in a foot
                        num2 = num1 * 0.3048d;
                        break;
                    case "kilometers":
                        //0.0003048 kilometers in a meter
                        num2 = num1 * 0.0003048d;
                        break;
                }
                break;
            } //End conversions from feet

            //Next, conversions from yards
            case "yards": { //Begin conversions from yards
                switch (newU) {
                    case "inches":
                        //36 inches in a yard
                        num2 = num1 * 36.0d;
                        break;
                    case "feet":
                        //3 feet to a yard
                        num2 = num1 * 3.0d;
                        break;
                    case "yards":
                        num2 = num1;
                        break;
                    case "miles":
                        //1760 yards in a mile
                        num2 = num1 / 1760.0d;
                        break;
                    case "millimeters":
                        //914.4 millimeters in a yard
                        num2 = num1 * 914.4d;
                        break;
                    case "centimeters":
                        //91.44 centimeters in a yard
                        num2 = num1 * 91.44d;
                        break;
                    case "meters":
                        num2 = num1 * 0.9144d;
                        break;
                    case "kilometers":
                        //1,093.61 yards in a kilometer
                        num2 = num1 / 1093.61d;
                        break;
                }
                break;
            } //End conversions from yards

            //Next, convert from miles
            case "miles": { //Begin conversions from miles
                switch (newU) {
                    case "inches":
                        //6330 inches in a mile
                        num2 = num1 * 6330.0d;
                        break;
                    case "feet":
                        //5280 feet in a mile
                        num2 = num1 * 5280.0d;
                        break;
                    case "yards":
                        //1760 yards in a mile
                        num2 = num1 * 1760.0d;
                        break;
                    case "miles":
                        num2 = num1;
                        break;
                    case "millimeters":
                        //1,609,000 millimeters in a mile
                        num2 = num1 * 1609340.0d;
                        break;
                    case "centimeters":
                        //16,0934 centimeters in a miles
                        num2 = num1 * 160934.0d;
                        break;
                    case "meters":
                        //1609.34 meters in a mile
                        num2 = num1 * 1609.34d;
                        break;
                    case "kilometers":
                        //1.60934 kilometers in a mile
                        num2 = num1 * 1.60934d;
                        break;
                }
                break;
            } //End converting from miles

            //Next, convert from millimeters
            case "millimeters": { //Begin converting from millimeters
                switch (newU) {
                    case "inches":
                        num2 = num1 * 25.4d;
                        break;
                    case "feet":
                        num2 = num1 / 304.8d;
                        break;
                    case "yards":
                        num2 = num1 / 914.4d;
                        break;
                    case "miles":
                        num2 = num1 / 1609000.0d;
                        break;
                    case "millimeters":
                        num2 = num1;
                        break;
                    case "centimeters":
                        num2 = metricConvert(num1, "milli", "centi");
                        break;
                    case "meters":
                        num2 = metricConvert(num1, "milli", "unit");
                        break;
                    case "kilometers":
                        num2 = metricConvert(num1, "milli", "kilo");
                        break;
                }
                break;
            } //End converting from millimeters

            //Next, convert from centimeters
            case "centimeters": { //Begin converting from centimeters
                switch (newU) {
                    case "inches":
                        num2 = num1 / 2.54d;
                        break;
                    case "feet":
                        num2 = num1 / 30.48d;
                        break;
                    case "yards":
                        num2 = num1 / 91.44d;
                        break;
                    case "miles":
                        num2 = num1 / 160934.0d;
                        break;
                    case "millimeters":
                        num2 = metricConvert(num1, "centi", "milli");
                        break;
                    case "centimeters":
                        num2 = num1;
                        break;
                    case "meters":
                        num2 = metricConvert(num1, "centi", "unit");
                        break;
                    case "kilometers":
                        num2 = metricConvert(num1, "centi", "kilo");
                        break;
                }
                break;
            } //End converting from centimeters

            //Next, convert from meters
            case "meters": { //Begin converting from meters
                switch (newU) {
                    case "inches":
                        num2 = num1 * 39.3701d;
                        break;
                    case "feet":
                        num2 = num1 * 3.28084d;
                        break;
                    case "yards":
                        num2 = num1 * 1.09361d;
                        break;
                    case "miles":
                        num2 = num1 / 1609.34d;
                        break;
                    case "millimeters":
                        num2 = metricConvert(num1, "unit", "milli");
                        break;
                    case "centimeters":
                        num2 = metricConvert(num1, "unit", "centi");
                        break;
                    case "meters":
                        num2 = num1;
                        break;
                    case "kilometers":
                        num2 = metricConvert(num1, "unit", "kilo");
                        break;
                }
                break;
            } //End converting from meters

            //Finally, try converting from kilometers
            case "kilometers": { //Begin converting from kilometers
                switch (newU) {
                    case "inches":
                        num2 = num1 * 39370.1d;
                        break;
                    case "feet":
                        num2 = num1 * 3280.84d;
                        break;
                    case "yards":
                        num2 = num1 * 1093.61d;
                        break;
                    case "miles":
                        num2 = num1 / 1.60934d;
                        break;
                    case "millimeters":
                        num2 = metricConvert(num1, "kilo", "milli");
                        break;
                    case "centimeters":
                        num2 = metricConvert(num1, "kilo", "centi");
                        break;
                    case "meters":
                        num2 = metricConvert(num1, "kilo", "unit");
                        break;
                    case "kilometers":
                        num2 = num1;
                        break;
                }
                break;
            } //End converting from kilometers
        }
        return num2;

        //num2 is the number we want; return it
    } // End lengthConvert

    private static double allExponents(double base, double exponent)
    { //Begin allExponents
        double b = base;
        double e = Math.abs(exponent);
        double finalNum;
        if (exponent > 0) {
            finalNum = Math.pow(base, e);
        } else if (exponent < 0) {
            double p = Math.pow(base, e);
            finalNum = 1 / p;
        } else {
            finalNum = 1;
        }

        return finalNum;
    } //End allExponents

    public static double metricConvert(double originalNumber, String originalUnit, String newUnit)
    { //Begin metricConvert
        //This can use a slightly different, and much easier, algorithm than the others.
        //Because metric is so well organized, it doesn't matter what number is input- the conversion factors are the same.
        //So if I take the original number and convert it to UNITS (which is x*10^0) then convert from UNITS to the new unit,
        //I can very easily do these conversions with very little work. I will heavily utilize the math class here, I need to
        //use exponents quite a bit to simplify life.

        //First, I will need four doubles: the original number, the UNIT number, the final number, and a variable with which to catch the
        //powers of 10.
        double num1 = originalNumber;
        double unitNum = 0d;
        double finalNum = 0d;
        double tenP;

        //I like to grab the two strings to prevent accidental editing/deletion. I also send them to lower case.
        String originalUn = originalUnit.toLowerCase();
        String newUn = newUnit.toLowerCase();

        String originalU;
        if (originalUn.contains(" ")) {
            originalU = originalUn.substring(0, originalUn.indexOf(" "));
        } else {
            originalU = originalUn;
        }

        //If the string from the MetricActivity spinners is passed, there will be a space. Remove everything after it.
        String newU;
        if (newUn.contains(" ")) {
            newU = newUn.substring(0, newUn.indexOf(" "));
        } else {
            newU = newUn;
        }
        //String newU = newUn.substring(0, newUn.indexOf(" "));
        //Next, I use the first of two switch statements. This converts the original number to UNITS, or x*10^0.
        switch (originalU) {
            case "yotta":
                //Yotta is 10^24 units
                tenP = allExponents(10d, 24d);
                unitNum = tenP * num1;
                break;
            case "zeta":
                //Zeta is 10^21
                tenP = allExponents(10d, 21d);
                unitNum = tenP * num1;
                break;
            case "exa":
                //Exa is 10^18
                tenP = allExponents(10d, 18d);
                unitNum = tenP * num1;
                break;
            case "peta":
                //Peta is 10^15
                tenP = allExponents(10d, 15d);
                unitNum = tenP * num1;
                break;
            case "tera":
                //Tera is 10^12
                tenP = allExponents(10d, 12d);
                unitNum = tenP * num1;
                break;
            case "giga":
                //Giga is 10^9
                tenP = allExponents(10d, 9d);
                unitNum = tenP * num1;
                break;
            case "mega":
                //Mega is 10^6
                tenP = allExponents(10d, 6d);
                unitNum = tenP * num1;
                break;
            case "kilo":
                //Kilo is 10^3
                tenP = allExponents(10d, 3d);
                unitNum = tenP * num1;
                break;
            case "hecto":
                //Hecto is 10^2
                tenP = allExponents(10d, 2d);
                unitNum = tenP * num1;
                break;
            case "deka":
                //Deka is 10^1
                tenP = allExponents(10d, 1d);
                unitNum = tenP * num1;
                break;
            case "unit":
                //UNIT is the target, 10^0
                tenP = allExponents(10d, 0d);
                unitNum = tenP * num1;
                break;
            case "deci":
                //Deci is 10^-1
                tenP = allExponents(10d, -1d);
                unitNum = num1 * tenP;
                break;
            case "centi":
                //Centi is 10^-2
                tenP = allExponents(10d, -2d);
                unitNum = num1 * tenP;
                break;
            case "milli":
                //Milli is 10^-3
                tenP = allExponents(10d, -3d);
                unitNum = num1 * tenP;
                break;
            case "micro":
                //Micro is 10^-6
                tenP = allExponents(10d, -6d);
                unitNum = num1 * tenP;
                break;
            case "nano":
                //Nano is 10^-9
                tenP = allExponents(10d, -9d);
                unitNum = num1 * tenP;
                break;
            case "pico":
                //Pico is 10^-12
                tenP = allExponents(10d, -12d);
                unitNum = num1 * tenP;
                break;
            case "femto":
                //Femto is 10^-15
                tenP = allExponents(10d, -15d);
                unitNum = num1 * tenP;
                break;
            case "atto":
                //Atto is 10^-18
                tenP = allExponents(10d, -18d);
                unitNum = num1 * tenP;
                break;
            case "zepto":
                //Zepto is 10^-21
                tenP = allExponents(10d, -21d);
                unitNum = num1 * tenP;
                break;
            case "yocto":
                //Yocto is 10^-24
                tenP = allExponents(10d, -24d);
                unitNum = num1 * tenP;
                break;
        }

        //Next is a switch statement for all possible cases of the new unit. It takes
        //the number given by the first switch, unitNum, and converts it to the new unit
        //using math.
        switch (newU) { //Begin converting from base units (10^0) to new units.
            case "yotta":
                //Yotta is 10^24 units
                tenP = allExponents(10d, 24d);
                finalNum = unitNum / tenP;
                break;
            case "zeta":
                //Zeta is 10^21
                tenP = allExponents(10d, 21d);
                finalNum = unitNum / tenP;
                break;
            case "exa":
                //Exa is 10^18
                tenP = allExponents(10d, 18d);
                finalNum = unitNum / tenP;
                break;
            case "peta":
                //Peta is 10^15
                tenP = allExponents(10d, 15d);
                finalNum = unitNum / tenP;
                break;
            case "tera":
                //Tera is 10^12
                tenP = allExponents(10d, 12d);
                finalNum = unitNum / tenP;
                break;
            case "giga":
                //Giga is 10^9
                tenP = allExponents(10d, 9d);
                finalNum = unitNum / tenP;
                break;
            case "mega":
                //Mega is 10^6
                tenP = allExponents(10d, 6d);
                finalNum = unitNum / tenP;
                break;
            case "kilo":
                //Kilo is 10^3
                tenP = allExponents(10d, 3d);
                finalNum = unitNum / tenP;
                break;
            case "hecto":
                //Hecto is 10^2
                tenP = allExponents(10d, 2d);
                finalNum = unitNum / tenP;
                break;
            case "deka":
                //Deka is 10^1
                tenP = allExponents(10d, 1d);
                finalNum = unitNum / tenP;
                break;
            case "unit":
                //UNIT is the target, 10^0
                tenP = allExponents(10d, 0d);
                finalNum = unitNum / tenP;
                break;
            case "deci":
                //Deci is 10^-1
                tenP = allExponents(10d, -1d);
                finalNum = unitNum / tenP;
                break;
            case "centi":
                //Centi is 10^-2
                tenP = allExponents(10d, -2d);
                finalNum = unitNum / tenP;
                break;
            case "milli":
                //Milli is 10^-3
                tenP = allExponents(10d, -3d);
                finalNum = unitNum / tenP;
                break;
            case "micro":
                //Micro is 10^-6
                tenP = allExponents(10d, -6d);
                finalNum = unitNum / tenP;
                break;
            case "nano":
                //Nano is 10^-9
                tenP = allExponents(10d, -9d);
                finalNum = unitNum / tenP;
                break;
            case "pico":
                //Pico is 10^-12
                tenP = allExponents(10d, -12d);
                finalNum = unitNum / tenP;
                break;
            case "femto":
                //Femto is 10^-15
                tenP = allExponents(10d, -15d);
                finalNum = unitNum / tenP;
                break;
            case "atto":
                //Atto is 10^-18
                tenP = allExponents(10d, -18d);
                finalNum = unitNum / tenP;
                break;
            case "zepto":
                //Zepto is 10^-21
                tenP = allExponents(10d, -21d);
                finalNum = unitNum / tenP;
                break;
            case "yocto":
                //Yocto is 10^-24
                tenP = allExponents(10d, -24d);
                finalNum = unitNum / tenP;
                break;
        } //End converting from base units (10^0) to new units.

        //Finally, return the final number
        return finalNum;
    } //End metricConvert

}
