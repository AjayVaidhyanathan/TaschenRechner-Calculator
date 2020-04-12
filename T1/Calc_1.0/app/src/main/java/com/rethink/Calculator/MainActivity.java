package com.rethink.Calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    TextView txtInput, txtOutput;
    String sTxtInp="",sTxtOut="",sValue="",sOpt="";
    Double Value=0.0, Value2=0.0, Result=0.0;
    NumberFormat format;
    Boolean decimal = false;


    private Switch btswitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darkTheme);
        } else
            setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Text box Declaration

        txtInput = findViewById(R.id.txtInput);
        txtOutput = findViewById(R.id.txtOutput);

        format = new DecimalFormat("#.####");

        //Light / dark mode

        btswitch = findViewById(R.id.mySwitch);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            btswitch.setChecked(true);
        }
        btswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    update();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    update();
                }
            }
        });
    }   //void function for light/dark theme

        public void update(){
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();
        }


    public void OnClickNum(View v){
        Button num = (Button) v;
        sTxtInp += num.getText();
        sValue += num.getText().toString();

        Value = Double.parseDouble(sValue);
        switch (sOpt){
            case "":
                Value2 = Result + Value;
                sTxtOut= format.format(Value2);
                break;

            case "+":
                Value2 = Result + Value;
                sTxtOut = format.format(Value2);
                break;

            case "-":
                Value2 = Result-Value;
                sTxtOut = format.format(Value2);
                break;

            case "x":
                Value2 = Result * Value;
                sTxtOut = format.format(Value2);
                break;

            case "/":
                try {
                    Value2 = Result / Value;
                    sTxtOut = format.format(Value2);

                }catch (Exception e){
                    sTxtOut=e.getMessage();
                }
                break;
        }
        decimal = false;
        calc();

    }

    public void OnClickOpt(View v){
        Button opt = (Button) v;
        if (sTxtOut != "") {
            if (sOpt != "" ) {
            }else {
                sTxtInp += opt.getText();
                sValue = "";
                Value = 0.0;
                Result = Value2;
                sTxtOut = format.format(Value2);
                sOpt = opt.getText().toString();
                decimal = false;
                calc();
            }
        }
    }

    public void OnClickDot(View v){
        if (!decimal) {
            if (sValue.length() == 0) {
                sTxtInp += "0.";
                sTxtOut += "0.";
                sValue += "0.";
                decimal = true;
                calc();
            }else{
                sTxtInp += ".";
                sTxtOut += ".";
                sValue += ".";
                decimal = true;
                calc();
            }
        }

    }

    public void OnClickClr(View v){
        Cleardata();
    }

    public void Cleardata(){
        sTxtInp="";
        sTxtOut="";
        sOpt="";
        sValue="";
        Value=0.0;
        Value2=0.0;
        Result=0.0;
        calc();
    }
    public void OnClickEql(View v){
        sTxtInp="";
        sTxtOut= format.format(Value2);
        sOpt="";
        sValue="";
        Value=0.0;
        Value2=0.0;
        Result=0.0;
        calc();

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


    public void calc(){
        txtInput.setText(sTxtInp);
        txtOutput.setText(sTxtOut);

    }
}

