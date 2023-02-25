package com.example.cateapp.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.cateapp.LoginActivity;
import com.example.cateapp.MainActivity;
import com.example.cateapp.R;
import com.example.cateapp.RecordActivity;
import com.example.cateapp.data.AppData;

import static com.example.cateapp.MainActivity.*;

public class UserFragment   extends Fragment {
    private String s1="";                                     //用于存放是否不要葱
    private String s2="";                                    //用于存放是否不要香菜
    private String s3="";                                    //用于存放是否不要蒜
    private String s4="";                                    //用于存放是否不要动物油
    private String s5="";                                    //用于存放是否不要醋
    private String s6="";                                    //用于存放辣味
    private AppData app;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.user_fragment,container,false);          //关联布局文件

        app= (AppData) getActivity().getApplication();      		    //获得全局类
        initView(view);
        initCheckBox( view);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        initUserBtnColor();
    }


    private  void initView(View view){
      TextView loginID=view.findViewById(R.id.loginID);
      loginID.setText("账号:"+app.account);
        app= (AppData) getActivity().getApplication();              //获得全局类
          //登录按钮
        Button login =view.findViewById(R.id.loginbutton);
        login.setText(app.check);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              if(app.check.equals("已登录")){
                  Toast.makeText(getActivity(),"您已登录",Toast.LENGTH_SHORT).show();
                }
                if(app.check.equals("未登录")){
                   Intent i=new Intent(getActivity(), LoginActivity.class);
                    startActivity(i);
                  //getActivity().finish();

                }

            }
        });

        //点击购物评价入口
        CardView evaluate=view.findViewById(R.id.evaluate);
        evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"暂不提供",Toast.LENGTH_SHORT).show();
            }
        });
        //点击购买记录入口
        CardView record=view.findViewById(R.id.record);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(getActivity(),"暂不提供",Toast.LENGTH_SHORT).show();
                Intent in=new Intent(getActivity(), RecordActivity.class);
                startActivity(in);

            }
        });
        //点击收货地址入口
        CardView adress=view.findViewById(R.id.adress);
        adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"暂不提供",Toast.LENGTH_SHORT).show();
            }
        });
        //点击联系客服入口
        CardView server=view.findViewById(R.id.server);
        server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getActivity(),"暂不提供",Toast.LENGTH_SHORT).show();
                call("tel:"+"10086");
            }
        });
    }

    private void initCheckBox(final View view){
        //点击不要葱多选框
        final CheckBox cb1=view.findViewById(R.id.checkBox1);
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){                                       //如果选中，
                    s1="-"+cb1.getText().toString();              //就获得选框里的值
                }
                else{                                       //如果没选中
                    s1="";                                  //就赋值为空
                }
                app.countremarks=s1+s2+s3+s4+s5+s6;            //把当前所选口味添加到总备注
            }
        });
        //点击不要香菜多选框
        final CheckBox cb2=view.findViewById(R.id.checkBox2);
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    s2="-"+cb2.getText().toString();
                }
                else{
                    s2="";
                }
                app.countremarks=s1+s2+s3+s4+s5+s6;            //把当前所选口味添加到总备注
            }
        });
        //点击不要蒜多选框
        final CheckBox cb3=view.findViewById(R.id.checkBox3);
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    s3="-"+cb3.getText().toString();
                }
                else{
                    s3="";
                }
                app.countremarks=s1+s2+s3+s4+s5+s6;            //把当前所选口味添加到总备注
            }
        });
        //点击不要动物油多选框
        final CheckBox cb4=view.findViewById(R.id.checkBox4);
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    s4="-"+cb4.getText().toString();
                }
                else{
                    s4="";
                }
                app.countremarks=s1+s2+s3+s4+s5+s6;            //把当前所选口味添加到总备注
            }
        });

        //点击不要醋多选框
        final CheckBox cb5=view.findViewById(R.id.checkBox5);
        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    s5="-"+cb5.getText().toString();
                }
                else{
                    s5="";
                }
                app.countremarks=s1+s2+s3+s4+s5+s6;            //把当前所选口味添加到总备注
            }
        });

        RadioGroup radioGroup=view.findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton=view.findViewById(i);       //获得选中的单选框
                s6="-"+radioButton.getText().toString();			    //获得选框里的数据
                app.countremarks=s1+s2+s3+s4+s5+s6;            //把当前所选口味添加到总备注
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
        if (ContextCompat.checkSelfPermission(getActivity(), string_permission) == PackageManager.PERMISSION_GRANTED) {//已有权限
            flag = true;
        } else {//申请权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{string_permission}, request_code);
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
                    Toast.makeText(getActivity(),"请允许拨号权限后再试",Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(telPhone));
            startActivity(intent);
        }
    }
}
