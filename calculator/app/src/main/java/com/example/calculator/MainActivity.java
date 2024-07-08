package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void  divide (View view){

        EditText n1View = (EditText) findViewById(R.id.num1);
        EditText n2View = (EditText) findViewById(R.id.num2);
        TextView result_view = (TextView) findViewById(R.id.result);

        double result = Double.valueOf(n1View.getText().toString())/Double.valueOf(n2View.getText().toString());
        result_view.setText(String.valueOf(result));

    }
}