package com.etouchsky.wisdom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.etouchsky.GViewerXApplication;
import com.etouchsky.adapter.ViewPagerAdapter;
import com.etouchsky.fragment.Fragment1;
import com.etouchsky.fragment.Fragment2;
import com.etouchsky.fragment.Fragment3;
import com.etouchsky.util.CacheUtils;
import com.etouchsky.util.Config;

import java.util.ArrayList;
import java.util.List;

public class Welcome extends FragmentActivity {

    private ViewPager viewPager;
    private List<View> listImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Config.setNoTheme(this);
        if (CacheUtils.getBoolean(this,CacheUtils.IS_OPEN_LOGIN_PAGE,false)) {
            Intent intent = new Intent(Welcome.this, MainActivity.class);
            intent.putExtra("call","");
            startActivityForResult(intent, 1);
            this.finish();
        } else {
            setContentView(R.layout.welcome);
            initView();
        }


    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), showView()));
        listImg = new ArrayList<View>();
        listImg.add(findViewById(R.id.y1));
        listImg.add(findViewById(R.id.y2));
        listImg.add(findViewById(R.id.y3));
        viewPager.setOnPageChangeListener(showPageChange);
    }


    ViewPager.OnPageChangeListener showPageChange = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < listImg.size(); i++) {
                if (i == arg0) {
                    listImg.get(arg0).setBackgroundResource(R.drawable.y_focused);
                } else {
                    listImg.get(i).setBackgroundResource(R.drawable.y_normal);
                }
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 1) {
                finish();
            }
        }
    }


    private List<Fragment> showView() {
        List<Fragment> listView = new ArrayList<Fragment>();
        listView.add(new Fragment1());
        listView.add(new Fragment2());
        listView.add(new Fragment3());
        return listView;
    }

}
