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
        stxtInput="";
        stxtOutput="";
        current_operator="";
        num1="";
        numOne=0.0;
        pt=0.0;
        Result=0.0;
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