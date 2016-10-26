package com.bwie.youbiao;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.List;
/*
* author:于远航
* Date:16/10/26
*
* Type:主界面
* */


public class MainActivity extends FragmentActivity {

    //封装属性
    private ViewPager vp;
    private List<Fragment>list;
    private String[] str;
    TabLayout tablayout;
    TextView tv1,tv2,tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取控件ID
        vp= (ViewPager) findViewById(R.id.vp2);
        tv1= (TextView) findViewById(R.id.tv1);
        tv2= (TextView) findViewById(R.id.tv2);
        tv3= (TextView) findViewById(R.id.tv3);


        //获取游标ID
        tablayout= (TabLayout) findViewById(R.id.tablelayout);
        //初始化控件
        init();
        inif();
        initData();

        //循环遍历，添加游标条目数
        for (int i=0;i<str.length;i++){

            //给tablelayout添加导航条目
            tablayout.addTab(tablayout.newTab().setText(str[i]));

        }

    }

    //设置Viewpager的设配器
    private void inif() {

       vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
           @Override
           public Fragment getItem(int position) {
               return list.get(position);
           }

           @Override
           public int getCount() {
               return list.size();
           }
       });

        //添加游标到ViewPager实现滑动显示
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                //循环遍历得到长度
                for (int i=0;i<list.size();i++){
                    LinearLayout ll= (LinearLayout) findViewById(R.id.ll);
                    TextView tv= (TextView) ll.getChildAt(i);
                    //设置首个游标的颜色
                    if (position==i){
                        tv.setTextColor(Color.RED);
                    }else{
                        tv.setTextColor(Color.BLACK);

                    }

                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //设置TextView的点击事件
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(0);
                tablayout.setVisibility(View.VISIBLE);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(1);
                tablayout.setVisibility(View.VISIBLE);
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(2);
                tablayout.setVisibility(View.VISIBLE);
            }
        });


    }

    //添加Fragment
    private void init() {
        list=new ArrayList<Fragment>();
        Fragment_gr f1=new Fragment_gr();
        Fragment_li f2=new Fragment_li();
        Fragment_yu f3=new Fragment_yu();
        list.add(f1);
        list.add(f2);
        list.add(f3);
    }

    private void initData() {
        str=new String[]{" "," "," "};
    }
}
