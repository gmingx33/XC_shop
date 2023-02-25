package com.example.cateapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cateapp.R;
import com.example.cateapp.RecordActivity;
import com.example.cateapp.data.AppData;
import com.example.cateapp.entity.Product;
import com.example.cateapp.fragment.ProducstFragment;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter< RecordAdapter.ViewHolder> {
    private FragmentActivity activity;
    private List<Product> list;
    private AppData app;                                   //定义为成员变量

    public RecordAdapter(FragmentActivity activity, List<Product> list){
        this.activity=activity;
        this.list=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        app=(AppData) parent.getContext().getApplicationContext();   //获得全局类
        //获得选项布局对象
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.record_fragment,parent,false);
        ViewHolder holder=new ViewHolder(view);                  //调用内部类ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Product product=list.get(position);           //获得选项数据集合
        //异步加载选项数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if((list.size()!=0)){
                            holder.iv.setImageResource(product.getImage());
                            holder.nametv.setText(product.getName());
                            holder.pricetv.setText(" "+product.getPrice());
                           // holder.pricetv.setText(product.getTime());
                            holder.time.setText("下单时间: "+product.getTime());


                        }
                    }
                });
            }
        }).start();


        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder=new AlertDialog.Builder(activity);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("提示");
                builder.setMessage("你确定要删除该条记录吗");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name=  holder.nametv.getText().toString();//获取被点击的文字
                        delete(name);
                        list.remove(position);
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();

            }
        });

        holder.nametv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(activity);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("提示");
                builder.setMessage("你确定要删除该条记录吗");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name=  holder.nametv.getText().toString();//获取被点击的文字
                        delete(name);
                        list.remove(position);
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();
            }
        });
        holder.pricetv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(activity);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("提示");
                builder.setMessage("你确定要删除该条记录吗");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name=  holder.nametv.getText().toString();//获取被点击的文字
                        delete(name);
                        list.remove(position);
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();
            }
        });
        holder.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(activity);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("提示");
                builder.setMessage("你确定要删除该条记录吗");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name=  holder.nametv.getText().toString();//获取被点击的文字
                        delete(name);
                        list.remove(position);
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return  list.size();                                          //获得选项数据集合长度;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView nametv;
        TextView pricetv;
        TextView time;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            iv=itemView.findViewById(R.id.recordImage);           //获得选项放小吃图片的控件
            nametv=itemView.findViewById(R.id.recordName);      //获得选项放小吃名称的控件
            pricetv=itemView.findViewById(R.id.recordPrice);        //获得选项放小吃价格的控件
            time=itemView.findViewById(R.id.recordtime);          //获得选项添加按钮控件


        }
    }

    public static class MyHelper extends SQLiteOpenHelper {

        public MyHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE information(_id INTEGER PRIMARY KEY AUTOINCREMENT,image INTEGER,name VARCHAR(20),price VARCHAR(20),time1 VARCHAR(30))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

    public void delete(String name){//删除购买记录
       MyHelper helper=new MyHelper(activity,app.account+".db",null,1);
        SQLiteDatabase db=helper.getWritableDatabase();
        int number=db.delete("information","name=?",new String[]{name});
        db.close();


        //设置为上下结构的LinearLayout布局
        RecyclerView rv=activity.findViewById(R.id.recordrecyclerView);               //获得右边列表控件对象
        rv.setLayoutManager(new LinearLayoutManager(activity,RecyclerView.VERTICAL,false));
        RecordAdapter recoderAdapter=new  RecordAdapter (activity,list);
        rv.setAdapter(recoderAdapter);                          //把适配器设置给右边列表控件

    }


}
