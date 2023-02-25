package com.example.cateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button button5=findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
              startActivity(i);
              finish();
            }
        });

        Button button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ed=findViewById(R.id.editTextNumber2);
                String s=ed.getText().toString();
                EditText ed2=findViewById(R.id.editTextTextPassword2);
                String s2=ed2.getText().toString();
                EditText ed3=findViewById(R.id.editTextTextPassword3);
                String s3=ed3.getText().toString();
                if(s.equals("")||s2.equals("")||s3.equals("")) {
                    Toast.makeText(RegisterActivity.this,"账号或密码不能为空",Toast.LENGTH_SHORT).show();
                }
                if(!s2.equals(s3)) {
                    Toast.makeText(RegisterActivity.this,"两次密码输入不一样，请再次输入",Toast.LENGTH_SHORT).show();
                }
                if(!s.equals("")&&!s2.equals("")&&!s3.equals("")){
                if(s2.equals(s3)) {
                    SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);  //获取SharedPreferences对象
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(s, s);
                    editor.putString(s2, s2);
                    editor.commit();
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    ed.setText("");
                    ed2.setText("");
                    ed3.setText("");
                }
            }}

        });
    }
}