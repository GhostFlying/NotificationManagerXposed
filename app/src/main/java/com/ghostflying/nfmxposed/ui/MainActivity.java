package com.ghostflying.nfmxposed.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.ghostflying.nfmxposed.R;
import com.ghostflying.nfmxposed.ui.fragment.PriorityFragment;
import com.ghostflying.nfmxposed.ui.fragment.VibrateFragment;
import com.ghostflying.nfmxposed.ui.fragment.VisibilityFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity{
    private static final int NUM_PAGES = 3;

    private String[] pageTitles;
    private FragmentPagerAdapter mPagerAdapter;

    @InjectView(R.id.viewpager)
    ViewPager mPager;
    @InjectView(R.id.pager_tab_strip)
    PagerTabStrip mTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageTitles = getResources().getStringArray(R.array.viewpager_titles);
        ButterKnife.inject(this);
        initView();
    }

    private void initView(){
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mTabStrip.setTabIndicatorColorResource(R.color.pageTabIndicator);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            Intent mIntent = new Intent(this, HelpActivity.class);
            startActivity(mIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return PriorityFragment.newInstance();
                case 1:
                    return VisibilityFragment.newInstance();
                case 2:
                default:
                    return VibrateFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitles[position];
        }
    }
}
