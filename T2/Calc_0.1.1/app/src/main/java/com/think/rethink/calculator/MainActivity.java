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
    Boolean decimal = false;


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
                pt = Result+numOne;
                stxtOutput= format.format(pt).toString();
                break;

            case "+":
                pt = Result + numOne;
                stxtOutput = format.format(pt).toString();
                break;

            case "-":
                pt = Result-numOne;
                stxtOutput = format.format(pt).toString();
                break;

            case "x":
                pt = Result * numOne;
                stxtOutput = format.format(pt).toString();
                break;

            case "/":
                try {
                    pt = Result / numOne;
                    stxtOutput = format.format(pt).toString();

                }catch (Exception e){
                    stxtOutput=e.getMessage();
                }
                break;
        }
        decimal = false;
        update();

    }

    public void onClickOperator(View v){
        Button op = (Button) v;
            if (stxtOutput != "") {
                if (current_operator != "" ) {
                }else {
                    stxtInput += op.getText();
                    num1 = "";
                    numOne = 0.0;
                    Result = pt;
                    stxtOutput = format.format(pt).toString();
                    current_operator = op.getText().toString();
                    decimal = false;
                    update();
                }
            }
    }


    public void onDotClick(View v){
        if (!decimal) {
            if (num1.length() == 0) {
                stxtInput += "0.";
                stxtOutput += "0.";
                num1 += "0.";
                decimal = true;
                update();
            }else{
                stxtInput += ".";
                stxtOutput += ".";
                num1 += ".";
                decimal = true;
                update();
            }
        }

       }


    public void onClickClear(View v){
        cleardata();
    }

    public void cleardata() {
        stxtInput = "";
        stxtOutput = "";
        current_operator = "";
        num1 = "";
        Result = 0.0;
        numOne = 0.0;
        pt = 0.0;
        update();
        decimal = false;
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

    private char getcharfromLast(String s, int i) {
        char c = s.charAt(s.length() - i);
        return c;
    }

    public void removeuntilchar(String str, char chr) {
        char c = getcharfromLast(str, 1);
        if (c != chr) {
            str = removechar(str, 1);
            stxtInput = str;
            update();
            removeuntilchar(str, chr);
        }
    }

    public String removechar(String str, int i) {
        char c = str.charAt(str.length() - i);
        //we need to check if dot is removed or not
        if (c == '.' && !decimal) {
            decimal = false;
        }

        if (c == ' ') {
            return str.substring(0, str.length() - (i - 1));
        }
        return str.substring(0, str.length() - i);
    }

    public void onClickDelete(View view) {

        if (stxtOutput != "") {
            if (getcharfromLast(stxtInput, 1) != ' ') {
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
                                cleardata();
                            } else {
                                if (getcharfromLast(stxtInput, 1) == '.') {
                                    decimal = false;
                                }
                                num1 = removechar(num1, 1);
                                numOne = Double.parseDouble(num1);
                                pt = numOne;
                                stxtInput = num1;
                                stxtOutput = num1;

                                update();
                            }
                            break;

                        case "+":

                            if (getcharfromLast(stxtInput, 1) == '.') {
                                decimal = false;
                            }
                            num1 = removechar(num1, 1);
                            if (num1.length() == 1 && num1 == ".") {
                                numOne = Double.parseDouble(num1);
                            }
                            numOne = Double.parseDouble(num1);
                            pt = Result + numOne;
                            stxtOutput = format.format(pt).toString();
                            stxtInput = removechar(stxtInput, 1);

                            update();
                            break;

                        case "-":

                            if (getcharfromLast(stxtInput, 1) == '.') {
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

                            if (getcharfromLast(stxtInput, 1) == '.') {
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

                                if (getcharfromLast(stxtInput, 1) == '.') {
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





    public void update(){
        txtInput.setText(stxtInput);
        txtOutput.setText(stxtOutput);

    }
}