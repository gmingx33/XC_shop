package com.example.cateapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//直接跳过
                Intent i=new Intent();
                i.setClass(LoginActivity.this,MainActivity.class);//设置跳转的界面
                i.putExtra("state","未登录");
                startActivity(i);
                finish();


              //  call("tel:"+"10086");



            }
        });
                Button button=findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {//登录


                EditText ed=findViewById(R.id.editTextNumber);
                String s=ed.getText().toString();//获取登录的账号
                EditText ed2=findViewById(R.id.editTextTextPassword);
                String s2=ed2.getText().toString() ;//获取登录的密码
                SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
                String account=sp.getString(s,"mistake");
                String password=sp.getString(s2,"mistake");
                if(s.equals("")||s2.equals("")) {
                            Toast.makeText(LoginActivity.this,"账号或密码不能为空",Toast.LENGTH_SHORT).show();
                        }
                if(s.equals(account)&&s2.equals(password)){
                //    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                  Intent i=new Intent();
                  i.setClass(LoginActivity.this,MainActivity.class);//设置跳转的界面
                    i.putExtra("account",s);
                    i.putExtra("state","已登录");
                    startActivity(i);
                    finish();

                }
                        if(!s.equals(account)||!s2.equals(password)){
                            Toast.makeText(LoginActivity.this,"账号或密码不正确",Toast.LENGTH_SHORT).show();
                        }
               // Toast.makeText(LoginActivity.this,s,Toast.LENGTH_SHORT).show();


            }
        });
        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//注册
                Intent i2=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i2);
                finish();
            }
        });



    }



    public static final int REQUEST_CALL_PERMISSION = 10111; //拨号请求码

    /**
     * 判断是否有某项权限
     * @param string_permission 权限
     * @param request_code 请求码
     * @return
     */
    public boolean checkReadPermission(String string_permission,int request_code) {
        boolean flag = false;
        if (ContextCompat.checkSelfPermission(this, string_permission) == PackageManager.PERMISSION_GRANTED) {//已有权限
            flag = true;
        } else {//申请权限
            ActivityCompat.requestPermissions(this, new String[]{string_permission}, request_code);
        }
        return flag;
    }

    /**
     * 检查权限后的回调
     * @param requestCode 请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL_PERMISSION: //拨打电话
                if (permissions.length != 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {//失败
                    Toast.makeText(this,"请允许拨号权限后再试",Toast.LENGTH_SHORT).show();
                } else {//成功
                    call("tel:"+"10086");
                }
                break;
        }
    }

    /**
     * 拨打电话（直接拨打）
     * @param telPhone 电话
     */
    public void call(String telPhone){
        if(checkReadPermission(Manifest.permission.CALL_PHONE,REQUEST_CALL_PERMISSION)){
            Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse(telPhone));
            startActivity(intent);
        }
    }
}