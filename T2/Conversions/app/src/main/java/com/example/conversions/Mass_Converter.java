package com.example.conversions;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Mass_Converter extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convlayout);
    }

    public static double massConvert(double originalNum, String originalUnit, String desiredUnit)
    { //Begin massConvert
        //Make two variable doubles, one the original double and one the new one
        double num1 = originalNum;
        double num2 = 0.0d;


        //Store the units into new strings. I find this to be safer, as I can't override the originals this way.
        //Also convert them to lower case
        String original = originalUnit.toLowerCase();
        String newU = desiredUnit.toLowerCase();

        switch(original)
        { //Begin conversion table
            case "pounds":
                switch(newU)
                {
                    case "pounds":
                        num2 = originalNum;
                        break;
                    case "kilograms":
                        num2 = originalNum * 0.453592d;
                        break;
                    case "grams":
                        num2 = originalNum * 453.592d;
                        break;
                    case "milligrams":
                        num2 = originalNum * 453592.0d;
                        break;
                }
                break;
            case "kilograms":
                switch(newU)
                {
                    case "pounds":
                        num2 = originalNum * 2.20462d;
                        break;
                    case "kilograms":
                        num2 = originalNum;
                        break;
                    case "grams":
                        num2 = metricConvert(num1, "kilo", "unit");
                        break;
                    case "milligrams":
                        num2 = metricConvert(num1, "kilo", "milli");
                        break;
                }
                break;
            case "grams":
                switch(newU)
                {
                    case "pounds":
                        num2 = originalNum * 2.20462d;
                        break;
                    case "kilograms":
                        num2 = metricConvert(num1, "unit", "kilo");
                        break;
                    case "grams":
                        num2 = originalNum;
                        break;
                    case "milligrams":
                        num2 = metricConvert(num1, "unit", "milli");
                        break;
                }
                break;
            case "milligrams":
                switch(newU)
                {
                    case "pounds":
                        num2 = originalNum * .0000022046d;
                        break;
                    case "kilograms":
                        num2 = metricConvert(num1, "milli", "kilo");
                        break;
                    case "grams":
                        num2 = metricConvert(num1, "milli", "unit");
                        break;
                    case "milligrams":
                        num2 = originalNum;
                        break;
                }
                break;
        } //End conversion table

        //Return the final number
        return num2;
    } //End massConvert

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
