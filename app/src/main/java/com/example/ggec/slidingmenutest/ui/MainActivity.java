package com.example.ggec.slidingmenutest.ui;

//import com.example.ggec.slidingmenutest.R;

import com.example.ggec.slidingmenutest.fragment.MainFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.util.Log;

//public class MainActivity extends ActionBarActivity {
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private LayoutInflater inflator;
    public static FragmentManager fm;
    private ActionBar actionBar;
    private SlidingMenu menu;
    private ListView menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inflator = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setMenu();
        setActionBar();
        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();//用MainFragment替换原来的content_fram
    }

    private void setActionBar() {//自定义actionbar
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);   //使左上角图标是否显示，如果为false，则没有程序图标，仅仅就一个标题
        actionBar.setDisplayShowCustomEnabled(true);    //使自定义的普通View能在Title栏显示，即actionBar.setCustomView能起作用
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);     // 给左上角图标的左边加上一个返回的

        View view = inflator.inflate(R.layout.layout_actionbar, null);
//        ActionBar.LayoutParams layout = new ActionBar.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
//        actionBar.setCustomView(view, layout);
        actionBar.setCustomView(view);
    }

    @SuppressLint("InflateParams")
    private void setMenu() {
        menu = new SlidingMenu(this);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindWidthRes(R.dimen.slidingmenu_width);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);    //使SlidingMenu附加在Activity上

        View view = inflator.inflate(R.layout.layout_menu, null);//设置menu的list及点击监听
        menuList = (ListView) view.findViewById(R.id.list_menu);
        final String[] listItems = getResources().getStringArray(R.array.menu_item);
        menuList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_menu, R.id.text1, listItems));
        menuList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                menu.toggle();
                String title;
                if (position != 3) {
                    title = listItems[position].charAt(0) + "  " + listItems[position].charAt(1);
                } else {
                    title = listItems[position];
                }
                Log.i(TAG,"title="+title);
                switch (position) {
                    case 0:
//					fm.beginTransaction().replace(R.id.content_frame,new TrackFragment()).commit();
                        break;
                    case 1:
//					fm.beginTransaction().replace(R.id.content_frame,new TrafficFragment()).commit();
                        break;
                    case 2:
//					fm.beginTransaction().replace(R.id.content_frame,new SearchFragment()).commit();
                        break;
                    case 3:
//					fm.beginTransaction().replace(R.id.content_frame,new PhoneBookFragment()).commit();
                        break;
                    case 4:
//					fm.beginTransaction().replace(R.id.content_frame,new SettingFragment()).commit();
                        break;
                }
            }

        });
        menu.setMenu(view);
    }

    public void onClickMenu(View view) {//这个是在layout_actionbar中说好的
        menu.toggle();
    }
}

