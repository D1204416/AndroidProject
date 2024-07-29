package com.example.activitylifecycle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("觀察方法","OnCreate running");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void callMap(View view){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("gel:0,0?q=tokyo disneyland"));
        startActivity(i);
    }

    public void finishAPP(View view){
        finish();
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("觀察方法","onStart: running");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("觀察方法","onResume: running");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("觀察方法","onPause: running");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("觀察方法","onStop: running");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("觀察方法","onDestroy: running");
    }




}