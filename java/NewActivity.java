package com.example.cateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cateapp.data.AppData;
import com.example.cateapp.entity.Product;
import com.example.cateapp.fragment.SettlementFragment;


import java.io.FileOutputStream;


public class NewActivity extends AppCompatActivity {
    private AppData app;
    private int num=0;//下单数量
    private int  pricesum;//下单的总价钱
    private TextView foodname;//菜名
    private    int[] image={R.mipmap.a8,R.mipmap.a4,R.mipmap.a1,R.mipmap.a2,R.mipmap.a3,R.mipmap.a5,
            R.mipmap.a6,R.mipmap.a7};
    private  String[] text={"正宗北京烤鸭","特色粽子","嘎嘣脆香煎饼果子","鲜嫩多汁小笼包","鲜嫩龙虾奶油组合","豆浆果果",
            "美味披萨","鲜嫩豆腐"};
    private String[]  text2={"注意：1.鸭是为餐桌上的上乘肴馔，也是人们进补的优良食品；但不宜多吃。2.鸭肉性凉，脾胃阴虚、经常腹泻者忌用。3.鸭肉不能与龟肉、鳖肉同食。",
            "1.糯米中支链淀粉的含量高，升血糖较快，所以慎吃；2.吃粽子要有量的概念，需相应减少主食摄入量；" +
                    "3.凉的粽子生糖指数比热粽子要低，如果你的胃肠消化功能足够强大的话，可以选择吃凉粽子；" +
                    "4.还要注意搭配蔬菜一起食用，保证膳食纤维摄入，以降低一餐总的生糖指数5、老年朋友，特别是平时胃肠消化功能不好的人，" +
                    "尽量选择素粽子，量不要超过一个，并且要热着吃。","煎饼馃子搭配一碗锅巴菜。将锅巴(即薄煎饼)切成柳叶细片，放到卤内，卤是用清油煸茴香、葱姜末，加盐、酱油、芡粉、水制成卤汁，纯素，保证清晨的肠胃不至于受到油腻的\"骚扰\"。然后盛碗，再加麻酱、腐乳、辣椒、芫荽、香干制成。\n" +
            "\n" +
            "煎饼馃子与豆浆(天津人一般叫 \"浆子\")可搭配一起喝。" ,"由于小笼包中含有大量汤水，所以吃起来务必要小心。\n" +
            "1.首先，将小笼包夹入小碟中，要小心不要将皮夹破。\n" +
            "2.在小笼包侧面咬开一小口，略微吹凉一些。（小笼包汤汁较烫，最好不要直接入口，可用筷子夹住后吹一下汤汁，但千万不可倒入小碟中。）\n" +
            "3.流传的吃法先喝汤，这个理念是正确的，小笼包的精髓就在于汤，" +
            "保证小笼包美味不流失的方法是待汤汁稍凉之后，将整个汤汁送入口中，让汤汁在嘴中完全包住小笼包，封住美味，毫不流失。" +
            "不过不能着急，不然会烫着，然后再慢慢享用。","水产品的营养素种类与含量都不亚于畜禽肉，而各种虾体内的营养成分几乎是一致的。各种虾体内含的都是高蛋白、低脂肪，" +
            "蛋白含量占总体的16%~20%左右，脂肪含量不到0.2%。而且所含的脂肪主要是由不饱合脂肪酸组成的，宜于人体吸收。" +
            "虾肉内锌、碘、硒等微量元素的含量要高于其它食品，同时，它的肌纤维细嫩，易于消化吸收。 龙虾不仅是肉洁白细嫩，" +
            "味道鲜美，高蛋白，低脂肪，营养丰富，还有药用价值，能化痰止咳，促进手术后的伤口生肌愈合。" ,"豆浆含有丰富的植物蛋白和磷脂，还含有维生素B1.B2和烟酸。此外，豆浆还含有铁、钙等矿物质，尤其是其所含的钙，非常适合于各种人群，包括老人、成年人、青少年、儿童等等。\n" +
            "\n" +
            "鲜豆浆四季都可饮用。春秋饮豆浆，滋阴润燥，调和阴阳;夏饮豆浆，消热防暑，生津解渴;冬饮豆浆，祛寒暖胃，滋养进补。","披萨的原料丰富，既含有肉类又含有蔬菜，而且在制作的过程中，还会添加牛奶" +
            "、芝士等食材，不仅营养成分比较全面，可以为人体提供丰富的营养物质，而且吃起来口感也是非常不错的。","豆腐为补益清热养生食品，常食可补中益气、清热润燥、生津止渴、清洁肠胃。更适于热性体质、" +
            "口臭口渴、肠胃不清、热病后调养者食用。现代医学证实，豆腐除有增加营养、帮助消化、增进食欲的功能外，对齿、骨骼" +
            "的生长发育也颇为有益(有此功能的还有果中钙王--酸角)，在造血功能中可增加血液中铁的含量;豆腐不含胆固醇，是高血压、高血脂、高胆固醇症及动脉硬化、冠心病患者的药膳佳肴" };
    private int position ;//判断是哪个菜
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


        Button newrerurn=findViewById(R.id.newreturn);
        newrerurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                app= (AppData) NewActivity.this.getApplication();              //获得全局类
                Intent i=new Intent();
                i.setClass(NewActivity.this,MainActivity.class);//设置跳转的界面
                i.putExtra("account",app.account);
                i.putExtra("state",app.check);
                startActivity(i);
                finish();
            }
        });
        menu();
        xiadan();
        
        
        
    }
    public void xiadan(){
        Button foodorder=findViewById(R.id.foodorder);
        //添加食物下单数量
        foodorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
              String num2=Integer.toString(num);
              TextView foodnum=findViewById(R.id.foodnum);
                foodnum.setText(num2);

         //  TextView foodprice=findViewById(R.id.foodprice);
         //  String pricesum= foodprice.getText().toString();
        //    int  pricesum2=Integer.parseInt(pricesum)*num;
                 pricesum=10*num;
            TextView foodpricesum=findViewById(R.id.foodpricesum);
           foodpricesum.setText(Integer.toString(pricesum));
                foodpricesum.setTextColor(Color.rgb(240,161,76));
            }
        });
        //减少食物下单数量
        Button foodredure=findViewById(R.id.foodredure);
        foodredure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    num--;
                String num2=Integer.toString(num);
                TextView foodnum=findViewById(R.id.foodnum);

                if(num>=0) {
                    foodnum.setText(num2);
                }
                if(num<0){
                   num=0;
               }
                int  pricesum=10*num;
                TextView foodpricesum=findViewById(R.id.foodpricesum);
                foodpricesum.setText(Integer.toString(pricesum));
                foodpricesum.setTextColor(Color.rgb(240,161,76));
            }

        });

        Button buttonorder=findViewById(R.id.buttonorder);
        buttonorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                app = (AppData) NewActivity.this.getApplication();              //获得全局类
                if (app.check.equals("已登录")) {
                    if (num >= 1) {
                        String pricesum2 = Integer.toString(pricesum);
                        //获取菜名，价格，数量，并添加的集合里。
                        Product product = new Product();
                        product.setImage(image[position]);
                        product.setName(text[position]);
                        product.setPrice(pricesum2);
                        product.setNumber(num);


                        app.countprice = pricesum;
                        app.selectedproduct.add(product);  //添加当前选项数据到集合
                        Toast.makeText(NewActivity.this, "下单成功", Toast.LENGTH_SHORT).show();
                       // save();
                    } else {
                        Toast.makeText(NewActivity.this, "你还没有选择数量", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(NewActivity.this, "你还没有登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
//    public void  save() {//文件输出流，用于保存下单信息
//
//        String pricesum2 = Integer.toString(pricesum);
//        String image2 = Integer.toString(image[position]);
//
//        String filename=app.account+".txt";//文件名称
//        String content =image2+"#"+text[position]+"#"+pricesum2+"#";  //保存数据
//        FileOutputStream fos=null;
//        try{
//
//            // 写入方式，支持两种方式：
//            //     *                   Context.MODE_PRIVATE;私有覆盖型
//            //     *                   Context.MODE_APPEND;私有追加型
//            //     */
//            // fos=openFileOutput(filename,MODE_PRIVATE);
//            fos=openFileOutput(filename, MODE_APPEND);
//
//            fos.write(content.getBytes());//将数据写入文件
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try{
//                if(fos!=null) {
//                    fos.close();
//                }
//            }catch (Exception e) {
//                    e.printStackTrace();
//            }
//        }
//
//
//
//    }

    
    public  void menu(){


        
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");//获取菜名

        for(int i=0;i<text.length;i++){

            if(text[i].equals(name)){
               position=i;
               break;
        }


        }
      // Toast.makeText(NewActivity.this,text.length,Toast.LENGTH_SHORT).show();
        ImageView foodimage=findViewById(R.id.foodimage);
        foodimage.setImageResource(image[position]);//设置食物图片
        TextView  foodid=findViewById(R.id.foodid);
        foodid.setText(text[position]);

       foodname=findViewById(R.id.foodname);
        foodname.setText(text2[position]);
    }


}