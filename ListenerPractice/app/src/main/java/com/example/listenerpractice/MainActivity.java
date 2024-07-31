package com.example.listenerpractice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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

        Button b = (Button) findViewById(R.id.big);
        b.setOnClickListener(this);
        Button s = (Button) findViewById(R.id.small);
        s.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView textsize = (TextView) findViewById(R.id.textsize);
        TextView textview = (TextView) findViewById(R.id.textview);

        float currentSizeSp = textsize.getTextSize()/getResources().getDisplayMetrics().scaledDensity;

        if(view.getId() == R.id.big) {
            textview.setTextSize(currentSizeSp + 5); // 變更尺寸
            textsize.setText(String.valueOf(currentSizeSp + 5)); // 顯示尺寸
        }else{
            textview.setTextSize(currentSizeSp - 5); // 變更尺寸
            textsize.setText(String.valueOf(currentSizeSp - 5)); // 顯示尺寸
        }

    }
}