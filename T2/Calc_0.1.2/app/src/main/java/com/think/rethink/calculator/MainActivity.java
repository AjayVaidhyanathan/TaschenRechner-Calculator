package com.think.rethink.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.text.Html;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    TextView txtInput, txtOutput;
    String stxtInput="",stxtOutput="",num1="",current_operator="",num2="";
    String RorD = "RAD", sin_inv, cos_inv, tan_inv, function;
    Double numOne=0.0,pt=0.0,Result=0.0, numTwo = 0.0;
    NumberFormat format, longformat;
    Boolean decimal = false, root = false, power = false, value_inverted = false, invert_allow = true;
    Boolean function_present = false, constant_present = false, factorial_present = false, number_allow = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInput=findViewById(R.id.txtInput);
        txtOutput=findViewById(R.id.txtOutput);

        format = new DecimalFormat("#.####");
        longformat = new DecimalFormat("0.#E0");
        sin_inv = String.valueOf(Html.fromHtml("sin<sup><small>-1</small></sup>"));
        cos_inv = String.valueOf(Html.fromHtml("cos<sup><small>-1</small></sup>"));
        tan_inv = String.valueOf(Html.fromHtml("tan<sup><small>-1</small></sup>"));
        final Button btn_RorD = findViewById(R.id.btn_RorD);
        btn_RorD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RorD = btn_RorD.getText().toString();
                RorD = RorD.equals("RAD") ? "DEG" : "RAD";
                btn_RorD.setText(RorD);
            }
        });

    }

    public void onClickNumber(View v){
        Button n = (Button) v;
        stxtInput += n.getText();
        num1 += n.getText();
        numOne = Double.parseDouble(num1);
        if (function_present) {
          calculateFunction(function);
            return;
        }
        //check root is present
        if (root) {
            numOne = Math.sqrt(numOne);
        }
        switch (current_operator){
            case "":
                if(power)
                    pt = Result + Math.pow(numTwo, numOne);
                else
                pt = Result+numOne;
                break;

            case "+":
                if(power)
                    pt = Result + Math.pow(numTwo, numOne);
                else
                pt = Result + numOne;
                break;

            case "-":
                if(power)
                    pt = Result - Math.pow(numTwo, numOne);
                else
                pt = Result-numOne;
                break;

            case "x":
                if(power)
                    pt = Result * Math.pow(numTwo, numOne);
                else
                pt = Result * numOne;
                break;

            case "/":
                try {
                    if(power)
                        pt = Result / Math.pow(numTwo, numOne);
                    else
                    pt = Result / numOne;

                }catch (Exception e){
                    stxtOutput=e.getMessage();
                }
                break;
        }
                stxtOutput= format.format(pt).toString();
        decimal = false;
        update();

    }

    public void onClickOperator(View v){
        Button op = (Button) v;
        if (stxtOutput != "") {
            //we check last char is operator or not
            if (current_operator != "") {
                char c = getcharfromLast(stxtInput, 2);// 2 is the char from last because our las char is " "
                if (c == '+' || c == '-' || c == 'x' || c == '/') {
                    stxtInput = stxtInput.substring(0, stxtInput.length() - 3);
                }
            }
            stxtInput = stxtInput + "\t" + op.getText() + " ";
            num1 = "";
            Result = pt;
            current_operator = op.getText().toString();
            update();
            //when operator click dot is not present in number_one
            num2 = "";
            numTwo = 0.0;
            decimal = false;
            number_allow = true;
            root = false;
            invert_allow = true;
            power = false;
            factorial_present = false;
            constant_present = false;
            function_present = false;
            value_inverted = false;
                }
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
        if (c == '^') {
            power = false;
        }
        if (c == ' ') {
            return str.substring(0, str.length() - (i - 1));
        }
        return str.substring(0, str.length() - i);
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
        num2 = "";
        //prev_ans = "";
        Result = 0.0;
        numOne = 0.0;
        numTwo = 0.0;
        pt = 0.0;
        update();
        decimal = false;
        number_allow = true;
        root= false;
        invert_allow = true;
        power = false;
        factorial_present = false;
        function_present = false;
        constant_present = false;
        value_inverted = false;
    }

    public void onClickEqual(View v){
        stxtInput="";
        stxtOutput= format.format(pt);
        current_operator="";
        num1="";
        num2 = "";
        numOne=0.0;
        numTwo=0.0;
        pt=0.0;
        Result=0.0;
        decimal = true;
        power = false;
        number_allow = false;
        factorial_present = false;
        constant_present = false;
        function_present = false;
        value_inverted = false;
        update();

    }

    public void onModuloClick(View view) {
        if (stxtOutput != "" && getcharfromLast(stxtInput, 1) != ' ') {
            stxtInput += "% ";
            //value of temp will change according to the operator
            switch (current_operator) {
                case "":
                    pt = pt / 100;
                    break;
                case "+":
                    pt = Result + ((Result * numOne) / 100);
                    break;
                case "-":
                    pt = Result - ((Result * numOne) / 100);
                    break;
                case "x":
                    pt = Result * (numOne / 100);
                    break;
                case "/":
                    try {
                        pt = Result / (numOne / 100);
                    } catch (Exception e) {
                        stxtOutput = e.getMessage();
                    }
                    break;
            }
            stxtOutput = format.format(pt).toString();
            if (stxtOutput.length() > 9) {
                stxtOutput = longformat.format(pt).toString();
            }
            Result = pt;
            onClickEqual(view);

        }
    }

    public void onPorMClick(View view) {
        if (invert_allow) {
            if (stxtOutput != "" && getcharfromLast(stxtInput, 1) != ' ') {
                numOne = numOne * (-1);
                num1 = format.format(numOne).toString();
                switch (current_operator) {
                    case "":
                        pt = numOne;
                        stxtInput = num1;
                        break;
                    case "+":
                        pt = Result + numOne;
                        //we need to add - sign in the starting of the string
                        removeuntilchar(stxtInput, ' ');
                        stxtInput += num1;
                        break;
                    case "-":
                        pt = Result - numOne;
                        removeuntilchar(stxtInput, ' ');
                        stxtInput += num1;
                        break;
                    case "*":
                        pt = Result * numOne;
                        //we need to add - sign in the starting of the string
                        removeuntilchar(stxtInput, ' ');
                        stxtInput += num1;
                        break;
                    case "/":
                        try {
                            pt = Result / numOne;
                            //we need to add - sign in the starting of the string
                            removeuntilchar(stxtInput, ' ');
                            stxtInput += num1;
                        } catch (Exception e) {
                            stxtOutput = e.getMessage();
                        }
                        break;
                }
                stxtOutput = format.format(pt).toString();
                value_inverted = value_inverted ? false : true;
                update();
            }
        }
    }


    public void onRootClick(View view) {
        Button r = (Button) view;
        //first we check if root is present or not
        if (stxtOutput == "" && Result == 0 && ! root && !function_present) {
            stxtInput = r.getText().toString();
            root = true;
            invert_allow = false;
            update();
        } else if (getcharfromLast(stxtInput, 1) == ' ' && current_operator != "" && !root) {
            stxtInput += r.getText().toString();
            root = true;
            invert_allow = false;
            update();
        }
    }

    public void onPowerClick(View view) {
        Button p = (Button) view;
        if (stxtInput != "" && !root && !power && !function_present) {
            if (getcharfromLast(stxtInput, 1) != ' ') {
                stxtInput += p.getText().toString();
                //we need second variable for the power
                num2 = num1;
                numTwo = numOne;
                num1 = "";
                power = true;
                update();
            }
        }
    }

    public void onSquareClick(View view) {
        if (stxtInput != "" && stxtOutput != "") {
            if (!root && !function_present && !power && getcharfromLast(stxtInput, 1) != ' ' && getcharfromLast(stxtInput, 1) != ' ') {
                numOne = numOne * numOne;
                num1 = format.format(numOne).toString();
                if (current_operator == "") {
                    if (num1.length() > 9) {
                        num1 = longformat.format(numOne);
                    }
                    stxtInput = num1;
                    pt = numOne;
                } else {
                    switch (current_operator) {
                        case "+":
                            pt = Result + numOne;
                            break;
                        case "-":
                            pt = Result - numOne;
                            break;
                        case "x":
                            pt = Result * numOne;
                            break;
                        case "/":
                            try {
                                pt = Result / numOne;
                            } catch (Exception e) {
                                stxtOutput = e.getMessage();
                            }
                            break;
                    }
                    removeuntilchar(stxtInput, ' ');
                    if (num1.length() > 9) {
                        num1 = longformat.format(numOne);
                    }
                    stxtInput += num1;
                }
                stxtOutput = format.format(pt);
                if (stxtOutput.length() > 9) {
                    stxtOutput = longformat.format(pt);
                }
                update();
            }
        }
    }

    public void onClickFactorial(View view) {
        if (!stxtOutput.equals("") && !factorial_present && !root && !decimal && !power && !function_present) {
            if (getcharfromLast(stxtInput, 1) != ' ') {
                for (int i = 1; i < Integer.parseInt(num1); i++) {
                    numOne *= i;
                }
                if (numOne.equals(0.0)) {
                    numOne = 1.0;
                }
                num1 = format.format(numOne).toString();
                switch (current_operator) {
                    case "":
                        Result = numOne;
                        break;
                    case "+":
                        Result += numOne;
                        break;
                    case "-":
                        Result -= numOne;
                        break;
                    case "x":
                        Result *= numOne;
                        break;
                    case "/":
                        try {
                            Result /= numOne;
                        } catch (Exception e) {
                            stxtInput = e.getMessage();
                        }

                        break;
                }
                stxtOutput = Result.toString();
                pt = Result;
                stxtInput += "! ";
                factorial_present = true;
                number_allow = false;
                update();
            }
        }
    }

    public void onClickInverse(View view) {
        if (!stxtOutput.equals("") && !factorial_present && !root && !decimal && !power && !function_present) {
            if (getcharfromLast(stxtInput, 1) != ' ') {
                numOne = Math.pow(numOne, -1);
                num1 = format.format(numOne).toString();
                switch (current_operator) {
                    case "":
                        pt = numOne;
                        stxtInput = num1;
                        break;
                    case "+":
                        pt = Result + numOne;
                        removeuntilchar(stxtInput, ' ');
                        stxtInput += num1;
                        break;
                    case "-":
                        pt = Result - numOne;
                        removeuntilchar(stxtInput, ' ');
                        stxtInput += num1;
                        break;
                    case "x":
                        pt = Result * numOne;
                        removeuntilchar(stxtInput, ' ');
                        stxtInput += num1;
                        break;
                    case "/":
                        try {
                            pt = Result / numOne;
                            removeuntilchar(stxtInput, ' ');
                            stxtInput += num1;
                        } catch (Exception e) {
                            stxtOutput = e.getMessage();
                        }

                        break;
                }
                stxtOutput = format.format(pt).toString();
                update();
            }
        }
    }

    public void onClickPIorE(View view) {
        Button btn_PIorE = (Button) view;
        number_allow = false;
        if (!root && !decimal && !power && !factorial_present && !constant_present && !function_present) {
            String str_PIorE = btn_PIorE.getText().toString() + " ";
            if (!str_PIorE.equals("e ")) {
                str_PIorE = "\u03A0" + " ";
            }
            if (stxtInput == "") {
                num1 = str_PIorE;
                if (str_PIorE.equals("e ")) {
                    numOne = Math.E;
                } else {
                    numOne = Math.PI;
                }
                pt = numOne;
            } else {
                if (str_PIorE.equals("e ")) {
                    //use ternary operation
                    numOne = getcharfromLast(stxtInput, 1) == ' ' ? Math.E : Double.parseDouble(num1) * Math.E;
                } else {
                    numOne = getcharfromLast(stxtInput, 1) == ' ' ? Math.PI : Double.parseDouble(num1) * Math.PI;
                }
                switch (current_operator) {

                    case "":
                        pt = Result + numOne;
                        break;

                    case "+":
                        pt = Result + numOne;
                        break;

                    case "-":
                        pt = Result - numOne;
                        break;

                    case "x"://we use x instedof * so change it in another function if you not.
                        pt = Result * numOne;
                        break;

                    case "/":
                        try {
                            pt = Result / numOne;
                        } catch (Exception e) {
                            stxtOutput = e.getMessage();
                        }
                        break;
                }
            }
            stxtInput += str_PIorE;
            stxtOutput = format.format(pt).toString();
            update();
            constant_present = true;
        }
    }

    public void onClickDegree(){
        numOne = Math.toDegrees(numOne);
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
        if (current_operator != "" && getcharfromLast(stxtInput, 1) == ' ') {
            switch (function) {
                case "sin_inv":
                    stxtInput += sin_inv + "(";
                    break;
                case "cos_inv":
                    stxtInput += cos_inv + "(";
                    break;
                case "tan_inv":
                    stxtInput += tan_inv + "(";
                    break;
                default:
                    stxtInput += function + "(";
                    break;
            }
            update();
        } else {
            switch (current_operator) {
                case "":
                    if (stxtInput.equals("")) {
                        switch (function) {
                            case "sin_inv":
                                stxtInput += sin_inv + "(";
                                break;
                            case "cos_inv":
                                stxtInput += cos_inv + "(";
                                break;
                            case "tan_inv":
                                stxtInput += tan_inv + "(";
                                break;
                            default:
                                stxtInput += function + "(";
                                break;
                        }
                    } else {
                        switch (function) {
                            case "log":
                                pt = Result + Math.log10(numOne);
                                stxtInput = "log(" + num1;
                                break;

                            case "ln":
                                pt = Result + Math.log(numOne);
                                stxtInput = "ln(" + num1;
                                break;

                            case "sin":
                                if (RorD == ("DEG")) {
                                    onClickDegree();
                                }
                                pt = Result + Math.sin(numOne);
                                stxtInput = "sin(" + num1;
                                break;

                            case "sin_inv":
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                pt = Result + Math.asin(numOne);
                                stxtInput = sin_inv + "(" + num1;
                                break;

                            case "cos":
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                pt = Result + Math.cos(numOne);
                                stxtInput = "cos(" + num1;
                                break;

                            case "cos_inv":
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                pt = Result + Math.acos(numOne);
                                stxtInput = cos_inv + "(" + num1;
                                break;

                            case "tan":
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                pt = Result + Math.tan(numOne);
                                stxtInput = "tan(" + num1;
                                break;

                            case "tan_inv":
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                pt = Result + Math.atan(numOne);
                                stxtInput = tan_inv + "(" + num1;
                                break;
                        }
                    }
                    stxtOutput = format.format(pt).toString();
                    update();
                    break;

                case "+":
                    removeuntilchar(stxtInput, ' ');
                    switch (function) {
                        case "log":
                            pt = Result + Math.log10(numOne);
                            stxtInput += "log(" + num1;
                            break;

                        case "ln":
                            pt = Result + Math.log(numOne);
                            stxtInput += "ln(" + num1;
                            break;

                        case "sin":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result + Math.sin(numOne);
                            stxtInput += "sin(" + num1;
                            break;

                        case "sin_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result + Math.asin(numOne);
                            stxtInput += sin_inv + "(" + num1;
                            break;

                        case "cos":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result + Math.cos(numOne);
                            stxtInput += "cos(" + num1;
                            break;

                        case "cos_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result + Math.acos(numOne);
                            stxtInput += cos_inv + "(" + num1;
                            break;

                        case "tan":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result + Math.tan(numOne);
                            stxtInput += "tan(" + num1;
                            break;

                        case "tan_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result + Math.atan(numOne);
                            stxtInput += tan_inv + "(" + num1;
                            break;
                    }
                    stxtOutput = format.format(pt).toString();
                    update();
                    break;

                case "-":
                    removeuntilchar(stxtInput, ' ');
                    switch (function) {
                        case "log":
                            pt = Result - Math.log10(numOne);
                            stxtInput += "log(" + num1;
                            break;

                        case "ln":
                            pt = Result - Math.log(numOne);
                            stxtInput += "ln(" + num1;
                            break;

                        case "sin":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result - Math.sin(numOne);
                            stxtInput += "sin(" + num1;
                            break;

                        case "sin_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result - Math.asin(numOne);
                            stxtInput += sin_inv + "(" + num1;
                            break;

                        case "cos":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result - Math.cos(numOne);
                            stxtInput += "cos(" + num1;
                            break;

                        case "cos_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result - Math.acos(numOne);
                            stxtInput += cos_inv + "(" + num1;
                            break;

                        case "tan":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result - Math.tan(numOne);
                            stxtInput += "tan(" + num1;
                            break;

                        case "tan_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result - Math.atan(numOne);
                            stxtInput += tan_inv + "(" + num1;
                            break;
                    }
                    stxtOutput = format.format(pt).toString();
                    update();
                    break;

                case "x":
                    removeuntilchar(stxtInput, ' ');
                    switch (function) {
                        case "log":
                            pt = Result * Math.log10(numOne);
                            stxtInput += "log(" + num1;
                            break;

                        case "ln":
                            pt = Result * Math.log(numOne);
                            stxtInput += "ln(" + num1;
                            break;

                        case "sin":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result * Math.sin(numOne);
                            stxtInput += "sin(" + num1;
                            break;

                        case "sin_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result * Math.asin(numOne);
                            stxtInput += sin_inv + "(" + num1;
                            break;

                        case "cos":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result * Math.cos(numOne);
                            stxtInput += "cos(" + num1;
                            break;

                        case "cos_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result * Math.acos(numOne);
                            stxtInput += cos_inv + "(" + num1;
                            break;

                        case "tan":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result * Math.tan(numOne);
                            stxtInput += "tan(" + num1;
                            break;

                        case "tan_inv":
                            if (RorD.equals("DEG")) {
                                onClickDegree();
                            }
                            pt = Result * Math.atan(numOne);
                            stxtInput += tan_inv + "(" + num1;
                            break;
                    }
                    stxtOutput = format.format(pt).toString();
                    update();
                    break;

                case "/":
                    removeuntilchar(stxtInput, ' ');
                    switch (function) {
                        case "log":
                            try {
                                pt = Result / Math.log10(numOne);
                                stxtInput += "log(" + num1;
                            } catch (Exception e) {
                                stxtOutput = e.getMessage();
                            }
                            break;

                        case "ln":
                            try {
                                pt = Result / Math.log(numOne);
                                stxtInput += "ln(" + num1;
                            } catch (Exception e) {
                                stxtOutput = e.getMessage();
                            }
                            break;

                        case "sin":
                            try {
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                pt = Result / Math.sin(numOne);
                                stxtInput += "sin(" + num1;
                            } catch (Exception e) {
                                stxtOutput = e.getMessage();
                            }
                            break;

                        case "sin_inv":
                            try {
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                pt = Result / Math.asin(numOne);
                                stxtInput += sin_inv + "(" + num1;
                            } catch (Exception e) {
                                stxtOutput = e.getMessage();
                            }
                            break;

                        case "cos":
                            try {
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                pt = Result / Math.cos(numOne);
                                stxtInput += "cos(" + num1;
                            } catch (Exception e) {
                                stxtOutput = e.getMessage();
                            }
                            break;

                        case "cos_inv":
                            try {
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                pt = Result / Math.acos(numOne);
                                stxtInput += cos_inv + "(" + num1;
                            } catch (Exception e) {
                                stxtOutput = e.getMessage();
                            }
                            break;

                        case "tan":
                            try {
                                if (RorD.equals("DEG")) {
                                    onClickDegree();
                                }
                                pt = Result / Math.tan(numOne);
                                stxtInput += "tan(" + num1;
                            } catch (Exception e) {
                                stxtOutput = e.getMessage();
                            }
                            break;

                        case "tan_inv":
                            try {
                                if (RorD.equals("DEG")) {
                                onClickDegree();
                                }
                                pt = Result / Math.atan(numOne);
                                stxtInput += tan_inv + "(" + num1;
                            } catch (Exception e) {
                                stxtOutput = e.getMessage();
                            }
                            break;
                    }
                    stxtOutput = pt.toString();
                    update();
                    break;
            }
        }
    }

    public void removePower() {
        if (stxtOutput != "" && stxtInput != "") {
            switch (current_operator) {
                case "":
                    if (getcharfromLast(stxtInput, 1) == '^') {
                        stxtInput = removechar(stxtInput, 1);
                        num1 = num2;
                        numOne = Double.parseDouble(num1);
                        num2 = "";
                        numTwo = 0.0;
                        update();
                    } else if (getcharfromLast(stxtInput, 2) == '^') {
                        num1 = "";
                        numOne = 0.0;
                        pt = numTwo;
                        stxtOutput = format.format(pt).toString();
                        stxtInput = removechar(stxtInput, 1);
                        update();
                    } else {
                        if (getcharfromLast(stxtInput, 1) == '.') {
                            decimal = false;
                        }
                        num1 = removechar(num1, 1);
                        numOne = Double.parseDouble(num1);
                        pt = Math.pow(numTwo, numOne);
                        stxtOutput = format.format(pt).toString();
                        stxtInput = removechar(stxtInput, 1);
                        update();
                    }
                    break;

                case "+":
                    if (getcharfromLast(stxtInput, 1) == '^') {
                        stxtInput = removechar(stxtInput, 1);
                        num1 = num2;
                        numOne = Double.parseDouble(num1);
                        num2 = "";
                        numTwo = 0.0;
                        update();
                    } else if (getcharfromLast(stxtInput, 2) == '^') {
                        num1 = "";
                        numOne = 0.0;
                        pt = Result + numTwo;
                        stxtOutput = format.format(pt).toString();
                        stxtInput = removechar(stxtInput, 1);
                        update();
                    } else {
                        if (getcharfromLast(stxtInput, 1) == '.') {
                            decimal = false;
                        }
                        num1 = removechar(num1, 1);
                        numOne = Double.parseDouble(num1);
                        pt = Result + Math.pow(numTwo, numOne);
                        stxtOutput = format.format(pt).toString();
                        stxtInput = removechar(stxtInput, 1);
                        update();
                    }
                    break;

                case "-":
                    if (getcharfromLast(stxtInput, 1) == '^') {
                        stxtInput = removechar(stxtInput, 1);
                        num1 = num2;
                        numOne = Double.parseDouble(num1);
                        num2 = "";
                        numTwo = 0.0;
                        update();
                    } else if (getcharfromLast(stxtInput, 2) == '^') {
                        num1 = "";
                        numOne = 0.0;
                        pt = Result - numTwo;
                        stxtOutput = format.format(pt).toString();
                        stxtInput = removechar(stxtInput, 1);
                        update();
                    } else {
                        if (getcharfromLast(stxtInput, 1) == '.') {
                            decimal = false;
                        }
                        num1 = removechar(num1, 1);
                        numOne = Double.parseDouble(num1);
                        pt = Result - Math.pow(numTwo, numOne);
                        stxtOutput = format.format(pt).toString();
                        stxtInput = removechar(stxtInput, 1);
                        update();
                    }
                    break;

                case "x":
                    if (getcharfromLast(stxtInput, 1) == '^') {
                        stxtInput = removechar(stxtInput, 1);
                        num1 = num2;
                        numOne = Double.parseDouble(num1);
                        num2 = "";
                        numTwo = 0.0;
                        update();
                    } else if (getcharfromLast(stxtInput, 2) == '^') {
                        num1 = "";
                        numOne = 0.0;
                        pt = Result * numTwo;
                        stxtOutput = format.format(pt).toString();
                        stxtInput= removechar(stxtInput, 1);
                        update();
                    } else {
                        if (getcharfromLast(stxtInput, 1) == '.') {
                            decimal = false;
                        }
                        num1 = removechar(num1, 1);
                        numOne = Double.parseDouble(num1);
                        pt = Result * Math.pow(numTwo, numOne);
                        stxtOutput = format.format(pt).toString();
                        stxtInput = removechar(stxtInput, 1);
                        update();
                    }
                    break;

                case "/":
                    try {
                        if (getcharfromLast(stxtInput, 1) == '^') {
                            stxtInput = removechar(stxtInput, 1);
                            num1 = num2;
                            numOne = Double.parseDouble(num1);
                            num2 = "";
                            numTwo = 0.0;
                            update();
                        } else if (getcharfromLast(stxtInput, 2) == '^') {
                            num1 = "";
                            numOne = 0.0;
                            pt = Result / numTwo;
                            stxtOutput = format.format(pt).toString();
                            stxtInput = removechar(stxtInput, 1);
                            update();
                        } else {
                            if (getcharfromLast(stxtInput, 1) == '.') {
                                decimal = false;
                            }
                            num1 = removechar(num1, 1);
                            numOne = Double.parseDouble(num1);
                            pt = Result / Math.pow(numTwo, numOne);
                            stxtOutput = format.format(pt).toString();
                            stxtInput = removechar(stxtInput, 1);
                            update();
                        }
                    } catch (Exception e) {
                        stxtOutput = e.getMessage();
                    }
                    update();
                    break;
            }
        }
    }

    public void removeRoot() {
        if (getcharfromLast(stxtInput, 1) != ' ') {
            if (String.valueOf(getcharfromLast(stxtInput, 1)).equals("\u221A")) {
                stxtInput = removechar(stxtInput, 1);
                root = false;
                invert_allow = true;
                update();
            }
            if (stxtOutput != "") {
                if (num1.length() < 2 && current_operator != "") {
                    num1 = "";
                    numOne = Result;
                    pt = Result;
                    stxtOutput = format.format(Result).toString();
                    stxtInput = removechar(stxtInput, 1);
                    update();
                } else {
                    switch (current_operator) {
                        case "":
                            if (stxtInput.length() <= 2) {
                                cleardata();
                            } else {
                                if (getcharfromLast(stxtInput, 1) == '.') {
                                    decimal = false;
                                }
                                num1 = removechar(num1, 1);
                                numOne = Double.parseDouble(num1);
                                numOne = Math.sqrt(numOne);
                                pt = numOne;
                                stxtOutput = format.format(pt).toString();
                                stxtInput = "\u221A" + num1;
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
                            numOne = Math.sqrt(numOne);
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
                            numOne = Math.sqrt(numOne);
                            pt = Result - numOne;
                            stxtOutput = format.format(pt).toString();
                            stxtInput = removechar(stxtOutput, 1);
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
                            numOne = Math.sqrt(numOne);
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
                                numOne = Math.sqrt(numOne);
                                pt = Result + numOne;
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

    public void onClickDelete(View view) {
        if (function_present) {
            DeleteFunction();
            return;
        }
        if (root) {
            removeRoot();
            return;
        }
        if (power) {
            removePower();
            return;
        }
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
                            if (value_inverted) {
                                stxtOutput = stxtOutput.substring(1, stxtOutput.length());
                                stxtInput = stxtInput.substring(1, stxtOutput.length());
                                update();
                            }
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
                            if (value_inverted) {
                                numOne = numOne * (-1);
                                num1 = format.format(numOne).toString();
                                pt = Result + numOne;
                                stxtOutput = format.format(pt).toString();
                                removeuntilchar(stxtInput, ' ');
                                stxtInput += num1;
                                update();
                                value_inverted = value_inverted ? false : true;
                            }
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
                            if (value_inverted) {
                                numOne = numOne * (-1);
                                num1 = format.format(numOne).toString();
                                pt = Result - numOne;
                                stxtOutput = format.format(pt).toString();
                                removeuntilchar(stxtInput, ' ');
                                stxtInput += num1;
                                update();
                                value_inverted = value_inverted ? false : true;
                            }
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
                            if (value_inverted) {
                                numOne = numOne * (-1);
                                num1 = format.format(numOne).toString();
                                pt = Result * numOne;
                                stxtOutput = format.format(pt).toString();
                                removeuntilchar(stxtInput, ' ');
                                stxtInput += num1;
                                update();
                                value_inverted = value_inverted ? false : true;
                            }
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
                                if (value_inverted) {
                                    numOne = numOne * (-1);
                                    num1 = format.format(numOne).toString();
                                    pt = Result / numOne;
                                    stxtOutput = format.format(pt).toString();
                                    removeuntilchar(stxtInput, ' ');
                                    stxtInput += num1;
                                    update();
                                    value_inverted = value_inverted ? false : true;
                                }
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

    public void DeleteFunction() {
        if (current_operator == "") {
            if (getcharfromLast(stxtInput, 1) == '(') {
                cleardata();
            } else if (getcharfromLast(stxtInput, 2) == '(') {
                stxtInput = removechar(stxtInput, 1);
            } else {
                stxtInput = removechar(stxtInput, 1);
                num1 = removechar(num1, 1);
                numOne = Double.parseDouble(num1);
                calculateFunction(function);
            }
            update();
        } else {
            if (getcharfromLast(stxtInput, 1) == '(') {
                removeuntilchar(stxtInput, ' ');
                function_present = false;
            } else if (getcharfromLast(stxtInput, 2) == '(') {
                stxtInput = removechar(stxtInput, 1);
                num1 = "";
                pt = Result;
                stxtOutput = format.format(Result).toString();
            } else {
                stxtInput = removechar(stxtInput, 1);
                num1 = removechar(num1, 1);
                numOne = Double.parseDouble(num1);
                calculateFunction(function);
            }
            update();
        }
    }


    public void update(){
        txtInput.setText(stxtInput);
        txtOutput.setText(stxtOutput);

    }
}