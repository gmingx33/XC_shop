package com.example.cateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StartupActivity extends AppCompatActivity {
    TextView t;
    int n=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        start2();
    }
    public void start2() {
      t=findViewById(R.id.textView2);
        new Thread(new Runnable() {                                   //创建并启动子线程
            @Override

            public void run() {

                    for (int i = 0; i < 3; i++) {

                      final String[] s= new String[]{"3", "2", "1"};
                        StartupActivity.this.runOnUiThread(new Runnable() { //调用主线程
                            @Override
                            public void run() {
                               t.setText(s[n]);        //刷新代码
                                n++;
                            }
                        });
                        try {
                            Thread.sleep(1000);                         //暂停100毫秒
                    if(n==3){
                        Intent it=new Intent(StartupActivity.this,LoginActivity.class);
                        startActivity(it);
                        finish();
                    }
                        } catch (Exception e) {
                        }

                }
            }


        }).start();
    }
}
