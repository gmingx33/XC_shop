package com.example.cateapp.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cateapp.MainActivity;
import com.example.cateapp.R;
import com.example.cateapp.adapter.SettlementAdapter;
import com.example.cateapp.data.AppData;
import com.example.cateapp.entity.Product;

import java.io.FileOutputStream;
import java.util.Date;

import static android.content.Context.MODE_APPEND;

public class SettlementFragment   extends Fragment {
    private AppData app;
    private RecyclerView rv;
    private TextView noproduct;
    private ScrollView scrollView;
    private TextView countprice;
    private EditText countremarks;
    private Button btnsettlement;
    @Nullable
   @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.settlement_fragment,container,false);          //关联布局文件

        app= (AppData) getActivity().getApplication();              //获得全局类
        rv=view.findViewById(R.id.selectedRecycler);               //获得列表控件对象
        noproduct=view.findViewById(R.id.noProduct);             //获得没点菜时的控件对象
        scrollView=view.findViewById(R.id.scrollView);              //获得ScrollView控件对象
        countprice=view.findViewById(R.id.countPirce);             //获得总价控件对象
        countremarks=view.findViewById(R.id.countRemarks);        //获得总备注控件对象
        btnsettlement=view.findViewById(R.id.settlement);       //获得下单按钮对象
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        MainActivity.initSettlementBtnColor();



        if(app.selectedproduct.size()!=0){              //如果已选小吃集合长度不等于0
            noproduct.setVisibility(View.GONE);       //就隐藏没点菜控件
            scrollView.setVisibility(View.VISIBLE);      //就显示已点菜控件
            initView();
        }
        else {                                    //否则
            noproduct.setVisibility(View.VISIBLE);      //就显示没点菜控件
            scrollView.setVisibility(View.GONE);       //就隐藏已点菜控件
        }
    }

    private  void initView(){
        countprice.setText(app.countprice+"");                       //显示已选小吃总价

        countremarks.setText(app.countremarks);                 //把总备注显示到控件里

        //设置列表为上下结构的LinearLayout布局
        rv.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        //调用适配器
        SettlementAdapter adapter=new SettlementAdapter(getActivity(),app.selectedproduct);
        rv.setAdapter(adapter);                     //把适配器设置给列表控件


        //点击下单按钮
        btnsettlement.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                //提示框提示“暂时无法下单”
                // Toast.makeText(getActivity(),"暂时无法下单", Toast.LENGTH_SHORT).show();
                app= (AppData) getActivity().getApplication();              //获得全局类
                if (app.check.equals("已登录")) {

                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    builder.setIcon(R.mipmap.ic_launcher);
                    builder.setTitle("提示");
                    builder.setMessage("你确定要下单吗");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int in) {
                            Toast.makeText(getActivity(), "下单成功", Toast.LENGTH_SHORT).show();
                            save();
                            app = (AppData) getActivity().getApplication();              //获得全局类
                            for (int i = 0; i < app.selectedproduct.size(); i++) {
                                app.selectedproduct.remove(i);
                            }
                            //  onResume();
                            app.countprice = 0;
                            initView();
                            noproduct.setVisibility(View.VISIBLE);      //就显示没点菜控件
                            scrollView.setVisibility(View.GONE);       //就隐藏已点菜控件
                        }
                    });
                    builder.setNegativeButton("取消",null);
                    builder.show();


                }
                else {

                    Toast.makeText(getActivity(), "你还没有登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    //保存信息
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public  void save(){
        for(int i=0;i<app.selectedproduct.size();i++) {
            Product product = app.selectedproduct.get(i);
            String pricesum = Double.toString(app.countprice);
            String content =product.getImage()+product.getName()+pricesum;   //保存数据
            String filename=app.account+".txt";//文件名称
           Date time=new Date();
           String time2=String.format("%tc",time);

      //   MyHelper m=new MyHelper(getActivity(),app.account+".db",null,1);

       //  SQLiteDatabase db = m.getWritableDatabase();
        insert(product.getImage(),product.getName(),pricesum,time2);



        }

    }
//SQLite数据库的创建
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


public void insert(int image,String name,String price,String time1){

    MyHelper m=new MyHelper(getActivity(),app.account+".db",null,1);
    SQLiteDatabase db = m.getWritableDatabase();//获取可读写SQLiteDatabse对象
    ContentValues values=new ContentValues();
    values.put("image",image);
    values.put("name",name);
    values.put("price",price);
    values.put("time1",time1);
     long id=db.insert("information",null,values);
     db.close();


    }




    }

   



