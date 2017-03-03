package com.wximp.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager vp;
    private LinearLayout ll;
    private ArrayList<View> list;
    private ImageView[] imageViews;
    private ImageView[] bannners;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.vp);
        ll = (LinearLayout) findViewById(R.id.ll);
        bt = (Button) findViewById(R.id.button);
       wx(0);
        bt.setAlpha(0.58f);
        list = new ArrayList<View>();
        ImageView img1=new ImageView(getApplicationContext());
        img1.setImageResource(R.drawable.a);
        ImageView img2=new ImageView(getApplicationContext());
        img2.setImageResource(R.drawable.b);
        ImageView img3=new ImageView(getApplicationContext());
        img3.setImageResource(R.drawable.c);
        img1.setScaleType(ImageView.ScaleType.FIT_XY);
        img2.setScaleType(ImageView.ScaleType.FIT_XY);
        img3.setScaleType(ImageView.ScaleType.FIT_XY);
        list.add(img1);
        list.add(img2);
        list.add(img3);
        vp.setAdapter(new MyAdapter(list));
        bannners = new ImageView[list.size()];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20, 20);
        lp.setMargins(0, 0, 20, 0);
        for(int i=0;i<list.size();i++) {
            ImageView temp = new ImageView(getApplicationContext());
            temp.setBackgroundResource(R.drawable.gray);

            bannners[i]=temp;
            ll.addView(temp,lp);
        }
        bannners[0].setBackgroundResource(R.drawable.white);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                wx(position);

                bannners[position].setImageResource(R.drawable.white);
                bannners[(position+1)%list.size()].setImageResource(R.drawable.gray);
                bannners[(position+2)%list.size()].setImageResource(R.drawable.gray);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
   public void wx(final int i){
       if(i==0){
           bt.setVisibility(View.VISIBLE);
           bt.setText("跳过");


       }
       else if(i==1) {
           bt.setVisibility(View.GONE);

       }
      else if(i==2) {
           bt.setVisibility(View.VISIBLE);
           bt.setText("进入主界面");

       }
       bt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(i==0){

                   vp.setCurrentItem(2);

               }

              else if(i==2){

                   Intent intent=new Intent(getApplicationContext(),FirstActivity.class);
                   startActivity(intent);
                   finish();

               }

           }
       });
   }
    public class MyAdapter extends PagerAdapter{
    private ArrayList<View> page_list;


        public MyAdapter(ArrayList list){
            page_list=list;

        }

        @Override
        public int getCount() {
            return page_list.size();

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
          container.removeView(page_list.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(page_list.get(position));
            return page_list.get(position);
        }
    }

}
