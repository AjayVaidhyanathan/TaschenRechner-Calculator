package com.rethink.Calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
;

public class MainActivity extends AppCompatActivity {

    TextView txtInput, txtOutput;
    String sTxtInp="",sTxtOut="",sValue="",sOpt="";
    String RorD = "RAD", sin_inv, cos_inv, tan_inv, function;
    Double Value=0.0, Value2=0.0, Result=0.0;
    NumberFormat format;
    Boolean decimal = false , factorial_present = false , root = false , power = false , function_present = false , number_allow = true;


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
        if (function_present) {
            calculateFunction(function);
            return;
        }
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
            if (sOpt != "") {
                char c = getcharfromlast(sTxtInp, 2);// 2 is the char from last because our las char is " "
                if (c == '+' || c == '-' || c == 'x' || c == '/') {
                    sTxtInp = sTxtInp.substring(0, sTxtInp.length() - 3);
                }
            }
            sTxtInp = sTxtInp + "\t" + opt.getText() + " ";
                sValue = "";
                Value = 0.0;
                Result = Value2;
                sTxtOut = format.format(Value2);
                sOpt = opt.getText().toString();
                decimal = false;
                number_allow = true;
                root = false;
                /*invert_allow = true;*/
                power = false;
                factorial_present = false;
                /*constant_present = false;*/
                function_present = false;
                /*value_inverted = false;*/
                calc();
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
        decimal = false;
        number_allow = true;
        root= false;
       /* invert_allow = true;*/
        power = false;
        factorial_present = false;
        function_present = false;
       /* constant_present = false;
        value_inverted = false;*/
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
    static char getcharfromlast(String s, int i)
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

    public void removeuntilchar(String str, char chr) {
        char c = getcharfromlast(str, 1);
        if (c != chr) {
            str = removechar(str, 1);
            sTxtInp = str;
            calc();
            removeuntilchar(str, chr);
        }
    }

    public void onClickDegree(){
        Value = Math.toDegrees(Value);
    }



    public void onClickFunction(View view) {
        Button func = (Button) view;
        function = func.getText().toString();//  sin_inv is not in the text
        if (!function_present && !root && !power && !factorial_present && !decimal) {
            calculateFunction(function);

        }
    }

    public void calculateFunction(String function) {
        function_present = true;
        if (sOpt != "" && getcharfromlast(sTxtInp, 1) == ' ') {
            switch (function) {
                case "sin_inv":
                    sTxtInp += sin_inv + "(";
                    break;
                case "cos_inv":
                    sTxtInp += cos_inv + "(";
                    break;
                case "tan_inv":
                    sTxtInp += tan_inv + "(";
                    break;
                default:
                    sTxtInp += function + "(";
                    break;
            }
            calc();
        } else {
            switch (sOpt) {
                case "":
                    if (sTxtInp.equals("")) {
                        switch (function) {
                            case "sin_inv":
                                sTxtInp += sin_inv + "(";
                                break;
                            case "cos_inv":
                                sTxtInp += cos_inv + "(";
                                break;
                            case "tan_inv":
                                sTxtInp += tan_inv + "(";
                                break;
                            default:
                                sTxtInp += function + "(";
                                break;
                        }
                    } else {
                        switch (function) {
                            case "log":
                                Value2 = Result + Math.log10(Value);
                                sTxtInp = "log(" + sValue;
                                break;

                            case "ln":
                                Value2 = Result + Math.log(Value);
                                sTxtInp = "ln(" + sValue;
                                break;

                            case "sin":
                                if (RorD == ("DEG")) {
                                    onClickDegree();
                                }
                                Value2 = Result + Math.sin(Value);
                                sTxtInp = "sin(" + sValue;
                                break;

                            case "sin_inv":
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                Value2 = Result + Math.asin(Value);
                                sTxtInp = sin_inv + "(" + sValue;
                                break;

                            case "cos":
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                Value2 = Result + Math.cos(Value);
                                sTxtInp = "cos(" + sValue;
                                break;

                            case "cos_inv":
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                Value2 = Result + Math.acos(Value);
                                sTxtInp = cos_inv + "(" + sValue;
                                break;

                            case "tan":
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                Value2 = Result + Math.tan(Value);
                                sTxtInp = "tan(" + sValue;
                                break;

                            case "tan_inv":
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                Value2 = Result + Math.atan(Value);
                                sTxtInp = tan_inv + "(" + sValue;
                                break;
                        }
                    }
                    sTxtOut = format.format(Value2).toString();
                    calc();
                    break;

                case "+":
                    removeuntilchar(sTxtInp, ' ');
                    switch (function) {
                        case "log":
                            Value2 = Result + Math.log10(Value);
                            sTxtInp += "log(" + sValue;
                            break;

                        case "ln":
                            Value2 = Result + Math.log(Value);
                            sTxtInp += "ln(" + sValue;
                            break;

                        case "sin":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result + Math.sin(Value);
                            sTxtInp += "sin(" + sValue;
                            break;

                        case "sin_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result + Math.asin(Value);
                            sTxtInp += sin_inv + "(" + sValue;
                            break;

                        case "cos":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result + Math.cos(Value);
                            sTxtInp += "cos(" + sValue;
                            break;

                        case "cos_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result + Math.acos(Value);
                            sTxtInp += cos_inv + "(" + sValue;
                            break;

                        case "tan":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result + Math.tan(Value);
                            sTxtInp += "tan(" + sValue;
                            break;

                        case "tan_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result + Math.atan(Value);
                            sTxtInp += tan_inv + "(" + sValue;
                            break;
                    }
                    sTxtOut = format.format(Value2).toString();
                    calc();
                    break;

                case "-":
                    removeuntilchar(sTxtInp, ' ');
                    switch (function) {
                        case "log":
                            Value2 = Result - Math.log10(Value);
                            sTxtInp += "log(" + sValue;
                            break;

                        case "ln":
                            Value2 = Result - Math.log(Value);
                            sTxtInp += "ln(" + sValue;
                            break;

                        case "sin":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result - Math.sin(Value);
                            sTxtInp += "sin(" + sValue;
                            break;

                        case "sin_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result - Math.asin(Value);
                            sTxtInp += sin_inv + "(" + sValue;
                            break;

                        case "cos":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result - Math.cos(Value);
                            sTxtInp += "cos(" + sValue;
                            break;

                        case "cos_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result - Math.acos(Value);
                            sTxtInp += cos_inv + "(" + sValue;
                            break;

                        case "tan":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result - Math.tan(Value);
                            sTxtInp += "tan(" + sValue;
                            break;

                        case "tan_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result - Math.atan(Value);
                            sTxtInp += tan_inv + "(" + sValue;
                            break;
                    }
                    sTxtOut = format.format(Value2).toString();
                    calc();
                    break;

                case "x":
                    removeuntilchar(sTxtInp, ' ');
                    switch (function) {
                        case "log":
                            Value2 = Result * Math.log10(Value);
                            sTxtInp += "log(" + sValue;
                            break;

                        case "ln":
                            Value2 = Result * Math.log(Value);
                            sTxtInp += "ln(" + sValue;
                            break;

                        case "sin":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result * Math.sin(Value);
                            sTxtInp += "sin(" + sValue;
                            break;

                        case "sin_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result * Math.asin(Value);
                            sTxtInp += sin_inv + "(" + sValue;
                            break;

                        case "cos":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result * Math.cos(Value);
                            sTxtInp += "cos(" + sValue;
                            break;

                        case "cos_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result * Math.acos(Value);
                            sTxtInp += cos_inv + "(" + sValue;
                            break;

                        case "tan":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result * Math.tan(Value);
                            sTxtInp += "tan(" + sValue;
                            break;

                        case "tan_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            Value2 = Result * Math.atan(Value);
                            sTxtInp += tan_inv + "(" + sValue;
                            break;
                    }
                    sTxtOut = format.format(Value2).toString();
                    calc();
                    break;

                case "/":
                    removeuntilchar(sTxtInp, ' ');
                    switch (function) {
                        case "log":
                            try {
                                Value2 = Result / Math.log10(Value);
                                sTxtInp += "log(" + sValue;
                            } catch (Exception e) {
                                sTxtOut = e.getMessage();
                            }
                            break;

                        case "ln":
                            try {
                                Value2 = Result / Math.log(Value);
                                sTxtInp += "ln(" + sValue;
                            } catch (Exception e) {
                                sTxtOut = e.getMessage();
                            }
                            break;

                        case "sin":
                            try {
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                Value2 = Result / Math.sin(Value);
                                sTxtInp += "sin(" + sValue;
                            } catch (Exception e) {
                                sTxtOut = e.getMessage();
                            }
                            break;

                        case "sin_inv":
                            try {
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                Value2 = Result / Math.asin(Value);
                                sTxtInp += sin_inv + "(" + sValue;
                            } catch (Exception e) {
                                sTxtOut = e.getMessage();
                            }
                            break;

                        case "cos":
                            try {
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                Value2 = Result / Math.cos(Value);
                                sTxtInp += "cos(" + sValue;
                            } catch (Exception e) {
                                sTxtOut = e.getMessage();
                            }
                            break;

                        case "cos_inv":
                            try {
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                Value2 = Result / Math.acos(Value);
                                sTxtInp += cos_inv + "(" + sValue;
                            } catch (Exception e) {
                                sTxtOut = e.getMessage();
                            }
                            break;

                        case "tan":
                            try {
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                Value2 = Result / Math.tan(Value);
                                sTxtInp += "tan(" + sValue;
                            } catch (Exception e) {
                                sTxtOut = e.getMessage();
                            }
                            break;

                        case "tan_inv":
                            try {
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                Value2 = Result / Math.atan(Value);
                                sTxtInp += tan_inv + "(" + sValue;
                            } catch (Exception e) {
                                sTxtOut = e.getMessage();
                            }
                            break;
                    }
                    sTxtOut = Value2.toString();
                    calc();
                    break;
            }
        }
    }




    public void calc(){
        txtInput.setText(sTxtInp);
        txtOutput.setText(sTxtOut);

    }
}

