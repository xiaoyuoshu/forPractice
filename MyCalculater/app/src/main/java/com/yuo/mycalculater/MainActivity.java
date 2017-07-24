package com.yuo.mycalculater;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private StringBuilder Input = new StringBuilder("");
    private StringBuilder Result = new StringBuilder("");
    private double result = 0;

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final TextView inputText = (TextView) findViewById(R.id.InputText);
            final TextView resultText = (TextView) findViewById(R.id.ResultText);
            int flag = 1;
            int tag = (Integer) v.getTag();
            switch (tag){
                case 41:
                    Input.append(1);
                    break;
                case 42:
                    Input.append(2);
                    break;
                case 43:
                    Input.append(3);
                    break;
                case 31:
                    Input.append(4);
                    break;
                case 32:
                    Input.append(5);
                    break;
                case 33:
                    Input.append(6);
                    break;
                case 21:
                    Input.append(7);
                    break;
                case 22:
                    Input.append(8);
                    break;
                case 23:
                    Input.append(9);
                    break;
                case 52:
                    Input.append(0);
                    break;
                case 53:
                    Input.append('.');
                    break;
                case 12:
                    Input.append('(');
                    break;
                case 13:
                    Input.append(')');
                    break;
                case 14:
                    Input.append('/');
                    break;
                case 24:
                    Input.append('*');
                    break;
                case 34:
                    Input.append('-');
                    break;
                case 44:
                    Input.append('+');
                    break;
                case 11:
                    if (Input.length() != 0) Input.deleteCharAt(Input.length() - 1);
                    break;
                case 51:
                    Input = new StringBuilder();
                    break;
                case 54:
                    result = Counter.calculate(Input.toString());
                    Result = new StringBuilder();
                    Result.append(result);
                    resultText.setText(Result);
                    Input = new StringBuilder("");
                    flag = 0;
                    break;
            }
            if(flag == 1) {
                inputText.setText(Input);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity", "onCreate:8.0+3.0=" + Counter.calculate("8.0+3.0") + "#");
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) actionBar.hide();

        final TextView inputText = (TextView) findViewById(R.id.InputText);
        final TextView resultText = (TextView) findViewById(R.id.ResultText);

        Button Button_delete = (Button) findViewById(R.id.Button_delete);
        Button Button_left = (Button) findViewById(R.id.Button_left);
        Button Button_right = (Button) findViewById(R.id.Button_right);
        Button Button_div = (Button) findViewById(R.id.Button_div);
        Button Button_7 = (Button) findViewById(R.id.Button_7);
        Button Button_8 = (Button) findViewById(R.id.Button_8);
        Button Button_9 = (Button) findViewById(R.id.Button_9);
        Button Button_mul = (Button) findViewById(R.id.Button_mul);
        Button Button_4 = (Button) findViewById(R.id.Button_4);
        Button Button_5 = (Button) findViewById(R.id.Button_5);
        Button Button_6 = (Button) findViewById(R.id.Button_6);
        Button Button_sub = (Button) findViewById(R.id.Button_sub);
        Button Button_1 = (Button) findViewById(R.id.Button_1);
        Button Button_2 = (Button) findViewById(R.id.Button_2);
        Button Button_3 = (Button) findViewById(R.id.Button_3);
        Button Button_plus = (Button) findViewById(R.id.Button_plus);
        Button Button_reset = (Button) findViewById(R.id.Button_reset);
        Button Button_0 = (Button) findViewById(R.id.Button_0);
        Button Button_dec = (Button) findViewById(R.id.Button_dec);
        Button Button_result = (Button) findViewById(R.id.Button_result);

        Button_delete.setOnClickListener(onClickListener);
        Button_delete.setTag(11);
        Button_left.setOnClickListener(onClickListener);
        Button_left.setTag(12);
        Button_right.setOnClickListener(onClickListener);
        Button_right.setTag(13);
        Button_div.setOnClickListener(onClickListener);
        Button_div.setTag(14);
        Button_7.setOnClickListener(onClickListener);
        Button_7.setTag(21);
        Button_8.setOnClickListener(onClickListener);
        Button_8.setTag(22);
        Button_9.setOnClickListener(onClickListener);
        Button_9.setTag(23);
        Button_mul.setOnClickListener(onClickListener);
        Button_mul.setTag(24);
        Button_4.setOnClickListener(onClickListener);
        Button_4.setTag(31);
        Button_5.setOnClickListener(onClickListener);
        Button_5.setTag(32);
        Button_6.setOnClickListener(onClickListener);
        Button_6.setTag(33);
        Button_sub.setOnClickListener(onClickListener);
        Button_sub.setTag(34);
        Button_1.setOnClickListener(onClickListener);
        Button_1.setTag(41);
        Button_2.setOnClickListener(onClickListener);
        Button_2.setTag(42);
        Button_3.setOnClickListener(onClickListener);
        Button_3.setTag(43);
        Button_plus.setOnClickListener(onClickListener);
        Button_plus.setTag(44);
        Button_reset.setOnClickListener(onClickListener);
        Button_reset.setTag(51);
        Button_0.setOnClickListener(onClickListener);
        Button_0.setTag(52);
        Button_dec.setOnClickListener(onClickListener);
        Button_dec.setTag(53);
        Button_result.setOnClickListener(onClickListener);
        Button_result.setTag(54);


    }

}

