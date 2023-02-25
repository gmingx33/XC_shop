package com.example.cateapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cateapp.adapter.ProductRightAdapter;
import com.example.cateapp.adapter.RecordAdapter;
import com.example.cateapp.data.AppData;
import com.example.cateapp.entity.Product;
import com.example.cateapp.fragment.UserFragment;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


public class RecordActivity extends AppCompatActivity {
    private AppData app;

  String []image2={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String []name2={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String []price2={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String []time2={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
  int num=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Button recordbutton=findViewById(R.id.recordbutton);
        recordbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                app= (AppData) RecordActivity.this.getApplication();              //获得全局类
                Intent i=new Intent();
                i.setClass(RecordActivity.this, MainActivity.class);//设置跳转的界面
                i.putExtra("account",app.account);
                i.putExtra("state",app.check);
                startActivity(i);
                finish();
            }
        });
        find();//查找购买记录
        //look();
    }
//    public void look(){//输入流
//        app = (AppData) RecordActivity.this.getApplication();              //获得全局类
//        String filename=app.account+".txt";//文件名称
//        String content="";
//        FileInputStream fis=null;
//        try{
//            fis=openFileInput(filename);
//            byte[] buffer=new byte[fis.available()];
//            fis.read(buffer);
//            content=new String(buffer);
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }finally {
//            try {
//                if (fis != null) {
//                    fis.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
    public static class MyHelper extends SQLiteOpenHelper{

        public MyHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


//     public MyHelper(Context context){
//
//            super(context,"itcast.db",null,2);
//     }



        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE information(_id INTEGER PRIMARY KEY AUTOINCREMENT,image INTEGER,name VARCHAR(20),price VARCHAR(20),time1 VARCHAR(30))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

    public void find(){
        app = (AppData) RecordActivity.this.getApplication();              //获得全局类
      //  Toast.makeText(RecordActivity.this, app.account+"message", Toast.LENGTH_SHORT).show();
     MyHelper helper=new MyHelper(RecordActivity.this,app.account+".db",null,1);
        SQLiteDatabase  db=helper.getReadableDatabase();
        Cursor cursor=db.query("information",null,null,null,null,null,null);
       if(cursor.getCount()!=0){
            while (cursor.moveToNext()){

                  String _id = cursor.getString(cursor.getColumnIndex("_id"));
                  String image = cursor.getString(cursor.getColumnIndex("image"));
                  String name = cursor.getString(cursor.getColumnIndex("name"));
                  String price = cursor.getString(cursor.getColumnIndex("price"));
                  String time1 = cursor.getString(cursor.getColumnIndex("time1"));
                  name2[num]=name;
                  image2[num]=image;
                  price2[num]=price;
                  time2[num]=time1;
                  num++;
             //   Toast.makeText(RecordActivity.this, app.account+"message"+message[0], Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }

       // int image3=Integer.parseInt(image2[0]);
        //   Toast.makeText(RecordActivity.this, image2, Toast.LENGTH_SHORT).show();


        int[] Image =new int[num];
        for(int i=0;i<num;i++){
            Image[i]=Integer.parseInt(image2[i]);
        }
        List<Product>  listright1=getList(Image, name2, price2,time2);
        RecyclerView rv=findViewById(R.id.recordrecyclerView);               //获得右边列表控件对象
        //设置为上下结构的LinearLayout布局
        rv.setLayoutManager(new LinearLayoutManager(RecordActivity.this,RecyclerView.VERTICAL,false));
        //调用右边列表适配器类
       RecordAdapter recoderAdapter=new  RecordAdapter (RecordActivity.this,listright1);

        rv.setAdapter(recoderAdapter);                          //把适配器设置给右边列表控件
    }

    public List<Product> getList(int[] iamges, String[] names, String[] prices,String time[]){
        List<Product> listright=new ArrayList<>();                 //存放右边列表所有数据
        Product product;                                      //用于存放一个选项数据
        for(int i=0;i<iamges.length;i++){
            product=new Product();
            product.setImage(iamges[i]);
            product.setName(names[i]);
            product.setPrice(prices[i]);
            product.setTime(time[i]);
            listright.add(product);
        }
        return listright;
    }
}