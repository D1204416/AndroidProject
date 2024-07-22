package com.example.chiabank;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;
    private double amount;

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


        intentActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {


                        // 寫另一個Activity回傳後,得到回傳的資料之後的做法
                        if (result.getData() != null && result.getResultCode() == Activity.RESULT_OK) {
                            amount = result.getData().getDoubleExtra("AMOUNT", 0);
                            String action = result.getData().getStringExtra("ACTION");
                            String coin = result.getData().getStringExtra("COIN");
                            TextView tv_ntdbalance = (TextView) findViewById(R.id.ntdbalance);
                            TextView tv_result = (TextView) findViewById(R.id.result);
                            double ntd_balance = Double.parseDouble(tv_ntdbalance.getText().toString());

                            tv_result.setText("交易成功");
                            tv_result.setTextColor(Color.parseColor("#33AA33"));


                            if (action.equals("deposit")) {
                                tv_ntdbalance.setText(String.valueOf(amount + ntd_balance));
                            } else if (action.equals("withdraw")) {
                                if (ntd_balance >= amount) {
                                    tv_ntdbalance.setText(String.valueOf(ntd_balance - amount));
                                } else {
                                    tv_result.setText("餘額不足，交易失敗");
                                    tv_result.setTextColor(Color.parseColor("#AA3333"));
                                }
                            } else if (action.equals("toNTD")) {
                                double rate = result.getData().getDoubleExtra("RATE", 0);
                                if (coin.equals("USD")) {
                                    TextView tv_usdbalance = (TextView) findViewById(R.id.usdbalance);
                                    double usdbalance = Double.parseDouble(tv_ntdbalance.getText().toString());

                                    if (usdbalance >= amount) {
                                        double r = (usdbalance - amount) * rate;
                                        tv_ntdbalance.setText(String.valueOf(ntd_balance + r));
                                        tv_usdbalance.setText(String.valueOf(usdbalance - amount));
                                    } else {
                                        tv_result.setText("餘額不足,交易失敗");
                                        tv_result.setTextColor(Color.parseColor("#AA3333"));
                                    }
                                } else {
                                    TextView tv_jpybalance = (TextView) findViewById(R.id.jpybalance);
                                    double jpyBalance = Double.parseDouble(tv_jpybalance.getText().toString());

                                    if (jpyBalance >= amount) {
                                        double r = (jpyBalance - amount) * rate;
                                        tv_ntdbalance.setText(String.valueOf(ntd_balance + r));
                                        tv_jpybalance.setText(String.valueOf(jpyBalance - amount));
                                    } else {
                                        tv_result.setText("餘額不足，交易失敗！");
                                        tv_result.setTextColor(Color.parseColor("#AA3333"));
                                    }
                                }

                            } else {
                                double rate = result.getData().getDoubleExtra("RATE", 0);
                                if (coin.equals("USD")) {
                                    TextView tv_usdbalance = (TextView) findViewById(R.id.usdbalance);
                                    double usdBalance = Double.parseDouble(tv_usdbalance.getText().toString());

                                    if (ntd_balance >= amount) {
                                        double r = amount / rate;
                                        tv_ntdbalance.setText(String.valueOf(ntd_balance - amount));
                                        tv_usdbalance.setText(String.valueOf(usdBalance + r));
                                    } else {
                                        tv_result.setText("餘額不足，交易失敗！");
                                        tv_result.setTextColor(Color.parseColor("#AA3333"));
                                    }
                                } else {
                                    TextView tv_jpybalance = (TextView) findViewById(R.id.jpybalance);
                                    double jpyBalance = Double.parseDouble(tv_jpybalance.getText().toString());

                                    if (ntd_balance >= amount) {
                                        double r = amount / rate;
                                        tv_ntdbalance.setText(String.valueOf(ntd_balance - amount));
                                        tv_jpybalance.setText(String.valueOf(jpyBalance + r));
                                    } else {
                                        tv_result.setText("餘額不足，交易失敗！");
                                        tv_result.setTextColor(Color.parseColor("#AA3333"));
                                    }
                                }
                            }
                        }
                    }
                }
        );
    }

    public void GotoNTD(View view) {
        Intent i = new Intent(this, NTDActivity.class);
        intentActivityResultLauncher.launch(i);
    }

    public void Exchange(View view) {
        // 1.按下按鈕Ａ,傳送Apple 2.按下按鈕Ｂ,傳送Banana
        String coin;
        if (view.getId() == R.id.usdbutton) {
            coin = "USD";
        } else {
            coin = "JPY";
        }
        Intent intent = new Intent(this, ExchangeActivity.class);

        // 設定一個bundle來放資料
        Bundle bundle = new Bundle();
        bundle.putString("COIN", coin);

        // 利用intent攜帶bundle資料
        intent.putExtras(bundle);
        intentActivityResultLauncher.launch(intent);

    }


}