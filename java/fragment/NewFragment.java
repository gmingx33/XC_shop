package com.example.cateapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.cateapp.MainActivity;
import com.example.cateapp.R;
import com.example.cateapp.adapter.NewAdapter;

public class NewFragment  extends Fragment {
   // private RecyclerView.Adapter adatper;
  public int[] light={R.mipmap.a0,R.mipmap.a00,R.mipmap.chi};
    public int n=0;

    @Nullable
   @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.new_fragment,container,false);          //关联布局文件

        top( view);
        initRecyclerView(view);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        MainActivity.initHomeBtnColor();



    }    
    public void top( View view){
       
        final ImageView iv=view.findViewById(R.id.imageView);
        new Thread(new Runnable() {                                   //创建并启动子线程
            @Override

            public void run() {
                while (true) {
                    for (int i = 0; i < light.length; i++) {
                        n = i;
                        getActivity().runOnUiThread(new Runnable() { //调用主线程
                            @Override
                            public void run() {
                                iv.setImageResource(light[n]);      //刷新代码
                            }
                        });
                        try {
                            Thread.sleep(1000);                         //暂停100毫秒
                        } catch (Exception e) {
                        }
                    }
                }
            }


        }).start();
    }





    private void initRecyclerView(View view){
        RecyclerView  rv=view.findViewById(R.id.newRecycler);       //获得RecyclerView控件对象                                                                              														   //设置为StaggeredGridLayout流式布局
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,RecyclerView.VERTICAL));                                                                              														   //定义新品小吃图片数组
//        int[] image={R.mipmap.bf11,R.mipmap.bf3,R.mipmap.om1,R.mipmap.om7,R.mipmap.yc1,
//                R.mipmap.nf10,R.mipmap.nf8};定义新品小吃说明数组
        int[] image={R.mipmap.a8,R.mipmap.a4,R.mipmap.a1,R.mipmap.a2,R.mipmap.a3,R.mipmap.a5,
                R.mipmap.a6,R.mipmap.a7};
        String[] text={"正宗北京烤鸭","特色粽子","嘎嘣脆香煎饼果子","鲜嫩多汁小笼包","鲜嫩龙虾奶油组合","豆浆果果",
                "美味披萨","鲜嫩豆腐"};

        NewAdapter adapter=new NewAdapter(getActivity(),image,text); //调用适配器

        rv.setAdapter(adapter);                             //设置适配器到RecyclerView
    }
}
