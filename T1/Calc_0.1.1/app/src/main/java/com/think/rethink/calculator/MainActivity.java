package com.think.rethink.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtInput, txtOutput;
    String stxtInput="";
    String stxtOutput="";
    String num1="";
    String current_operator="";
    Double numOne=0.0,pt=0.0,Result=0.0;
    NumberFormat format;
    Boolean decimal = false, opt = false;
    Character c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInput=findViewById(R.id.txtInput);
        txtOutput=findViewById(R.id.txtOutput);

        format = new DecimalFormat("#.####");

    }

    public void onClickNumber(View v){
        Button n = (Button) v;
        stxtInput += n.getText();
        num1 += n.getText();
        Log.i(num1,"value of num1");
        numOne = Double.parseDouble(num1); Log.i(format.format(numOne), "Value of numone in button");
        switch (current_operator){
            case "":
                pt = numOne;
                stxtOutput= format.format(pt);
                break;

            case "+":
                pt = Result + numOne;
                stxtOutput = format.format(pt);
                break;

            case "-":
                pt = Result-numOne;
                stxtOutput = format.format(pt);
                break;

            case "x":
                pt = Result * numOne;
                stxtOutput = format.format(pt);
                break;

            case "/":
                try {
                    pt = Result / numOne;
                    stxtOutput = format.format(pt);

                }catch (Exception e){
                    stxtOutput=e.getMessage();
                }
                break;
        }
        opt = false;
        update();
    }

    public void onClickOperator(View v){
        Button op = (Button) v;
            if (stxtOutput != "") {
                if (!opt) {
                    if (current_operator == "") {
                        stxtInput += op.getText();
                        num1 = "";
                        numOne = 0.0;
                        Result = pt;
                        stxtOutput = format.format(pt);
                        current_operator += op.getText().toString();
                        opt = true;
                        decimal = false;
                        update();
                    } else {
                        stxtInput += op.getText();
                        num1 = "";
                        numOne = 0.0;
                        Result = pt;
                        stxtOutput = format.format(pt);
                        current_operator += op.getText().toString();
                        decimal = false;
                        opt = true;
                        update();
                    }
                    Log.i(current_operator,"all charc");
                    update();
                }
            }
    }

    public void onDotClick(View v){
        if(!decimal){
        if (num1.length() == 0) {
            stxtInput += "0.";
            num1 += "0.";
            decimal = true;
            update();
        }else {
            stxtInput += ".";
            num1 += ".";
            decimal = true;
            update();
        }
        }
    }

    public void onClickClear(View v){
       Cleardata();
    }

    private char getcharfromlast(String s, int i)
    {
        char c = s.charAt(s.length() - i);
        return c;
    }

    public String removechar(String str, int i) {
        char c = str.charAt(str.length() - i);
        if (c == '.' && !decimal) {
            decimal = true;
        }
        if (c == ' ') {
            return str.substring(0, str.length() - (i - 1));
        }
        return str.substring(0, str.length() - i);
    }
    public void onClickDelete(View v){
        if(stxtInput != "" && stxtOutput !=""){ Log.i("INSIDE","Working");
            if(stxtInput.length()<2){ Log.i("cleardata","inside stxt.length()<2 loop");
                Cleardata();
            }else{
                if(getcharfromlast(stxtInput,1) == '.'){
                    if(getcharfromlast(stxtInput,2) == '0'){Log.i("Inside 0. function","Removed");
                    Log.i(num1,"Value of num1");
                        stxtInput =removechar(stxtInput,3);
                        pt = Result;
                        numOne = Result;
                        stxtOutput = format.format(pt);
                        Log.i(format.format(pt), "For Length less than 2 value of pt");
                        Result = 0.0;
                        num1 = stxtOutput;
                        update();
                    }else {
                        num1 = removechar(num1, 1);
                        stxtInput = removechar(stxtInput, 1);
                        Log.i("DOT", "Removed");
                        stxtOutput = format.format(pt);
                        decimal = false;
                        update();
                    }
                }else{
                    switch (current_operator){
                        case "":
                            num1 = removechar(num1,1); Log.i("Inside case null)","working");
                            numOne = Double.parseDouble(num1);
                            pt = numOne;
                            stxtInput = num1; Log.i(stxtInput,"Removed char:input");
                            stxtOutput = removechar(stxtOutput,1);Log.i(stxtOutput,"Removed char:Output");
                            update();
                            break;
                        case "+": Log.i("Inside case +","working");
                            if(num1.length()<2) {
                                Log.i(format.format(num1.length()), "Length less than 2");
                                stxtInput = removechar(stxtInput,1);
                                if (getcharfromlast(stxtInput,1) != '+'){
                                    Log.i("last string +","working");
                                    pt = Result;
                                    stxtOutput = format.format(pt);
                                    numOne = Result;
                                    current_operator="";
                                    Result = 0.0;
                                    num1 = stxtOutput;
                                    update();
                                }else {
                                    if (getcharfromlast(stxtInput, 1) == '+') {
                                        stxtInput = removechar(stxtInput, 1);
                                        pt = Result;
                                        stxtOutput = format.format(pt);
                                        numOne = Result;
                                        current_operator = ""; Log.i(format.format(pt), "For Length less than 2 value of pt");
                                        Result = 0.0; Log.i(current_operator, "Contains symbol or not");
                                        num1 = stxtOutput;
                                        Log.i(format.format(Result), "Value of result after removing");
                                        Log.i(format.format(pt), "Value of pt after removing");
                                        Log.i(num1, "Value of string num1");
                                        update();
                                    }
                                    update();
                                }
                            }
                            else{
                                num1 = removechar(num1, 1); Log.i(num1, "Value of num1");
                                numOne = Double.parseDouble(num1); Log.i(format.format(Result), "Value of result");
                                pt = Result + numOne; Log.i(format.format(pt), "value of pt");
                                stxtInput = removechar(stxtInput, 1);
                                stxtOutput = format.format(pt);
                                update();
                                break;
                            }
                        }
                    }
                }
        }else {
            Log.i("Outside", "notworking");
        }
    }

    public void Cleardata(){
        stxtInput="";
        stxtOutput="";
        current_operator="";
        num1="";
        numOne=0.0;
        pt=0.0;
        Result=0.0;
        decimal = false;
        opt = false;
        update();
    }

    public void onClickEqual(View v){
        stxtInput="";
        stxtOutput= format.format(pt);
        current_operator="";
        num1="";
        numOne=0.0;
        pt=0.0;
        Result=0.0;
        update();
    }

    public void update(){
        txtInput.setText(stxtInput);
        txtOutput.setText(stxtOutput);
    }
}