package com.think.rethink.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btPercentage,btDiv,btClr,bt0,bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,btMul,btSub,btPlus,btDot,btEql;
    TextView txtInput,txtOutput;
    boolean fAdd,fSub,fMul,fDiv,fPercent,decimal;
    Float m1,m2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt0 = findViewById(R.id.bt0);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        bt6 = findViewById(R.id.bt6);
        bt7 = findViewById(R.id.bt7);
        bt8 = findViewById(R.id.bt8);
        bt9 = findViewById(R.id.bt9);
        btDot = findViewById(R.id.btDot);

        btPlus = findViewById(R.id.btPlus);
        btSub = findViewById(R.id.btSub);
        btMul = findViewById(R.id.btMul);
        btDiv = findViewById(R.id.btDiv);
        btPercentage = findViewById(R.id.btPercentage);

        btEql = findViewById(R.id.btEql);
        btClr = findViewById(R.id.btClr);

        txtInput = findViewById(R.id.txtInput);
        txtOutput = findViewById(R.id.txtOutput);

        btClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInput.setText(null);
                txtOutput.setText(null);
            }
        });

        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInput.setText((txtInput.getText() + "0"));
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInput.setText((txtInput.getText() + "1"));
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInput.setText((txtInput.getText() + "2"));
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInput.setText((txtInput.getText() + "3"));
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInput.setText((txtInput.getText() + "4"));
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInput.setText((txtInput.getText() + "5"));
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInput.setText((txtInput.getText() + "6"));
            }
        });

        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInput.setText((txtInput.getText() + "7"));
            }
        });

        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInput.setText((txtInput.getText() + "8"));
            }
        });

        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInput.setText((txtInput.getText() + "9"));
            }
        });

        btPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtInput.getText().length() != 0) {
                    m1 = Float.parseFloat((txtInput.getText() + ""));
                    fAdd = true;
                    decimal = false;
                    txtInput.setText(null);
                }
            }
        });

        btSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtInput.getText().length() != 0) {
                    m1 = Float.parseFloat((txtInput.getText() + ""));
                    fSub = true;
                    decimal = false;
                    txtInput.setText(null);
                }
            }
        });

        btMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtInput.getText().length() != 0) {
                    m1 = Float.parseFloat((txtInput.getText() + ""));
                    fMul = true;
                    decimal = false;
                    txtInput.setText(null);
                }
            }
        });

        btDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtInput.getText().length() != 0) {
                    m1 = Float.parseFloat((txtInput.getText() + ""));
                    fDiv = true;
                    decimal = false;
                    txtInput.setText(null);
                }
            }
        });

        btPercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtInput.getText().length() != 0) {
                    m1 = Float.parseFloat((txtInput.getText() + ""));
                    fPercent = true;
                    decimal = false;
                    txtInput.setText(null);
                }
            }
        });

        btDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (decimal) {

                }else{
                    txtInput.setText((txtInput.getText() + "."));
                    decimal = true;

                }
            }
        });

        btEql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m2 = Float.parseFloat(txtInput.getText() + "");
                if (fAdd){
                    txtOutput.setText((m1 + m2 + ""));
                    fAdd = false;
                }
                if (fSub){
                    txtOutput.setText((m1 - m2 + ""));
                    fSub = false;
                }
                if (fMul){
                    txtOutput.setText((m1 * m2 + ""));
                    fMul = false;
                }
                if (fDiv){
                    txtOutput.setText((m1 / m2 + ""));
                    fDiv = false;
                }
                if (fPercent){
                    txtOutput.setText((m1 * (m2/100)+ ""));
                    fPercent = false;
                }

            }
        });
    }
}
