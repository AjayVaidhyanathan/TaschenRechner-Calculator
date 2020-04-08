package com.think.rethink.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    TextView txtInput, txtOutput;
    String stxtInput="",stxtOutput="",num1="",current_operator="";
    Double numOne=0.0,pt=0.0,Result=0.0;
    NumberFormat format;
    Boolean decimal = false, opt = false;


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
        numOne = Double.parseDouble(num1);
        switch (current_operator){
            case "":
                pt =  numOne + Result;
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
                        current_operator = op.getText().toString();
                        opt = true;
                        decimal = false;
                        update();
                    } else {
                        stxtInput += op.getText();
                        num1 = "";
                        numOne = 0.0;
                        Result = pt;
                        stxtOutput = format.format(pt);
                        current_operator = op.getText().toString();
                        decimal = false;
                        opt = true;
                        update();
                    }
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

    public void onClickDelete(View view) {
        if (stxtOutput != "") {
            if (stxtInput!= "") {
                if (num1.length() < 2 && current_operator != "") {
                    num1 = "";
                    pt = Result;
                    stxtOutput = format.format(Result).toString();
                    stxtInput = removechar(stxtInput, 1);
                    update();
                } else {
                    switch (current_operator) {
                        case "":

                            if (stxtInput.length() < 2) {
                                Cleardata();
                            } else {
                                if (getcharfromlast(stxtInput, 1) == '.') {
                                    num1 = removechar(num1, 1);
                                    numOne = Double.parseDouble(num1);
                                    pt = numOne;
                                    stxtInput = num1;
                                    stxtOutput = num1;
                                    decimal = false;
                                    update();
                                }else{
                                num1 = removechar(num1, 1);
                                numOne = Double.parseDouble(num1);
                                pt = numOne;
                                stxtInput = num1;
                                stxtOutput = num1;
                                update();
                                }
                            }
                            break;

                        case "+":
                            if (getcharfromlast(stxtInput, 1) == '.') {
                                num1 = removechar(num1, 1);
                                decimal = false;
                                update();
                            }else {
                                num1 = removechar(num1, 1);
                                if (num1.length() == 1 && num1 == ".") {
                                    numOne = Double.parseDouble(num1);
                                }
                                numOne = Double.parseDouble(num1);
                                pt = Result + numOne;
                                stxtOutput = format.format(pt).toString();
                                stxtInput = removechar(stxtInput, 1);
                                update();
                            }
                            break;

                        case "-":
                            if (getcharfromlast(stxtInput, 1) == '.') {
                                decimal = false;
                            }
                            num1 = removechar(num1, 1);
                            if (num1.length() == 1 && num1 == ".") {
                                numOne = Double.parseDouble(num1);
                            }
                            numOne = Double.parseDouble(num1);
                            pt = Result - numOne;
                            stxtOutput = format.format(pt).toString();
                            stxtInput = removechar(stxtInput, 1);

                            update();
                            break;

                        case "x":

                            if (getcharfromlast(stxtInput, 1) == '.') {
                                decimal = false;
                            }
                            num1 = removechar(num1, 1);
                            if (num1.length() == 1 && num1 == ".") {
                                numOne = Double.parseDouble(num1);
                            }
                            numOne = Double.parseDouble(num1);
                            pt = Result * numOne;
                            stxtOutput = format.format(pt).toString();
                            stxtInput = removechar(stxtInput, 1);

                            update();
                            break;

                        case "/":
                            try {
                                if (getcharfromlast(stxtInput, 1) == '.') {
                                    decimal = false;
                                }
                                num1 = removechar(num1, 1);
                                if (num1.length() == 1 && num1 == ".") {
                                    numOne = Double.parseDouble(num1);
                                }
                                numOne = Double.parseDouble(num1);
                                pt = Result / numOne;
                                stxtOutput = format.format(pt).toString();
                                stxtInput = removechar(stxtInput, 1);

                            } catch (Exception e) {
                                stxtOutput = e.getMessage();
                            }
                            update();
                            break;
                    }
                }
            }
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