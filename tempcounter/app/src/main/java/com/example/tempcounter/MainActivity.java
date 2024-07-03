package com.example.tempcounter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void cToF(View view){
        TextView tempView = (TextView) findViewById(R.id.tempView); //顯示溫度
        Button f = (Button) findViewById(R.id.f); // F 華氏按鈕
        EditText tempInput = (EditText) findViewById(R.id.tempInput); //數字輸入匡

        int view = Integer.parseInt(tempView.getText().toString());
        int view = Integer.parseInt(tempView.getText().toString());
    }

    public void fToC(View view){

    }
}