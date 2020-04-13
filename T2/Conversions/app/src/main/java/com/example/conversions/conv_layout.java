package com.example.conversions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.xml.transform.Result;

public class conv_layout extends AppCompatActivity {

    String sTxtInp = "", sValue = "", sTxtOut = "" , from = "" , to = "" , numStr = "";
    double Value;
    NumberFormat format1;
    TextView txtInput, txtOutput;
    Boolean LClick = false , VClick = false , SClick = false , AClick = false , TimeClick = false , TClick = false;
    Boolean PressClick = false , FClick = false , MClick = false;


    double number = 0.0;



    int pos = 0;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convlayout);
        txtInput = findViewById(R.id.txtInp);
        txtOutput = findViewById(R.id.txtOut);
        format1 = new DecimalFormat("#.####");
         Intent i = getIntent();
        Bundle b = i.getExtras();
         LClick = b.getBoolean("LClick");
         VClick = b.getBoolean("VClick");
         SClick = b.getBoolean("SClick");
         AClick = b.getBoolean("AClick");
        TimeClick = b.getBoolean("TimeClick");
         TClick = b.getBoolean("TClick");
         PressClick = b.getBoolean("PressClick");
         FClick = b.getBoolean("FClick");
         MClick = b.getBoolean("MClick");
        final Spinner spinInp = (Spinner) findViewById(R.id.spinInp);
        final Spinner spinOut = (Spinner) findViewById(R.id.spinOut);
        if(LClick) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Length, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinInp.setAdapter(adapter);
            spinOut.setAdapter(adapter);
        }
        if(AClick)
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Area, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinInp.setAdapter(adapter);
            spinOut.setAdapter(adapter);

        }

        if(VClick)
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.volume_units, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinInp.setAdapter(adapter);
            spinOut.setAdapter(adapter);
        }

        if(SClick)
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.speed_units, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinInp.setAdapter(adapter);
            spinOut.setAdapter(adapter);
        }

        if(TClick)
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.temp_units, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinInp.setAdapter(adapter);
            spinOut.setAdapter(adapter);
        }

        if(TimeClick)
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.time_units, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinInp.setAdapter(adapter);
            spinOut.setAdapter(adapter);
        }

        if(PressClick)
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pressure_units, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinInp.setAdapter(adapter);
            spinOut.setAdapter(adapter);
        }

        if(FClick)
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.force_units, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinInp.setAdapter(adapter);
            spinOut.setAdapter(adapter);
        }

        if(MClick)
        {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mass_units, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinInp.setAdapter(adapter);
            spinOut.setAdapter(adapter);
        }


        spinInp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                from = spinInp.getSelectedItem().toString();
                Toast.makeText(conv_layout.this , from , Toast.LENGTH_LONG).show();
                if(sTxtInp != "")
                {
                    Convert();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spinOut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                to = spinOut.getSelectedItem().toString();
                Toast.makeText(conv_layout.this , to , Toast.LENGTH_LONG).show();
                if(sTxtInp != "")
                {
                    Convert();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }







    public void OnClickNumber(View view) {
        Button a = (Button) view;
        sTxtInp += a.getText();
        Convert();
    }


    public void Convert()
    {
        numStr += sTxtInp;
        number = Double.parseDouble(numStr);
        if(LClick) {
            double answer = Length_Converter.LConvert(number, from, to);
            sTxtOut = format1.format(answer);
        }
        if(VClick)
        {
            double answer = Volume_Converter.volumeConvert(number , from , to);
            sTxtOut = format1.format(answer);
        }
        if(AClick)
        {
            double answer = Area_Converter.areaConvert(number , from , to);
            sTxtOut = format1.format(answer);
        }
        if(TimeClick)
        {
            double answer = Time_Converter.timeConvert(number , from , to);
            sTxtOut = format1.format(answer);
        }
        if(FClick)
        {
            double answer = Force_Converter.forceConvert(number , from , to);
            sTxtOut = format1.format(answer);
        }
        if(MClick)
        {
            double answer = Mass_Converter.massConvert(number , from , to);
            sTxtOut = format1.format(answer);
        }
        if(PressClick)
        {
            double answer = Pressure_Converter.pressureConvert(number , from , to);
            sTxtOut = format1.format(answer);
        }
        if(SClick)
        {
            double answer = Speed_Converter.speedConvert(number , from , to);
            sTxtOut = format1.format(answer);
        }
        if(TClick)
        {
            double answer = Temp_Converter.tempConvert(number , from , to);
            sTxtOut = format1.format(answer);
        }
        calculate();
        numStr = "";

    }

    public void onClickDel(View view) {

            if(sTxtInp.length() < 2)
            {
                txtInput.setText(null);
                txtOutput.setText(null);
                sTxtInp = "";
                sTxtOut = "";
            } else {
                sTxtInp = sTxtInp.substring(0, sTxtInp.length() - 1);
                Convert();
            }
    }




        public void calculate () {
            txtInput.setText(sTxtInp);
            txtOutput.setText(sTxtOut);
        }
    }