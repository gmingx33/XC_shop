package com.example.cateapp.adapter;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cateapp.LoginActivity;
import com.example.cateapp.MainActivity;
import com.example.cateapp.NewActivity;
import com.example.cateapp.R;
import com.example.cateapp.fragment.ProducstFragment;

public  class  NewAdapter  extends  RecyclerView.Adapter<NewAdapter.ViewHolder > {
    private FragmentActivity activity;
    private int[] image;
    private String[] text;

    public NewAdapter(FragmentActivity activity, int[] image, String[] text) {
        this.activity=activity;
        this.image=image;
        this.text=text;


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.new_item,parent,false);
        ViewHolder holder=new ViewHolder(view);                     //调用内部类ViewHolder
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //异步加载选项数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if((image.length!=0)&&(text.length!=0)){
                            holder.iv.setImageResource(image[position]);
                            holder.tv.setText(text[position]);
                        }
                    }
                });
            }
        }).start();
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=  holder.tv.getText().toString();//获取被点击的文字
                Intent i=new Intent();
                i.setClass(activity, NewActivity.class);//设置跳转的界面
                i.putExtra("name",s);

               // Toast.makeText(activity,s,Toast.LENGTH_SHORT).show();
                activity.startActivity(i);
                //点击左列表选项，加载对应右列表数据。
              //  ProducstFragment.rightrv.setAdapter(new ProductRightAdapter(activity, ProducstFragment.list.get(position)));
            }
        });
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=  holder.tv.getText().toString();//获取被点击的文字
                Intent i=new Intent();
                i.setClass(activity, NewActivity.class);//设置跳转的界面
                i.putExtra("name",s);

                // Toast.makeText(activity,s,Toast.LENGTH_SHORT).show();
                activity.startActivity(i);
                //点击左列表选项，加载对应右列表数据。
                //  ProducstFragment.rightrv.setAdapter(new ProductRightAdapter(activity, ProducstFragment.list.get(position)));
            }
        });






    }


    @Override
    public int getItemCount() {
      return image.length ;                            	//获得图片数组长度
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.newImage);               //获得列表选项图片控件
            tv=itemView.findViewById(R.id.newText);                 //获得列表选项文本控件
        }
    }
}
